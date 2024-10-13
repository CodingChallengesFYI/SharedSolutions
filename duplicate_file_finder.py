import os
import sys
from collections import defaultdict
import hashlib
from concurrent.futures import ThreadPoolExecutor, as_completed
import argparse

def list_files(directory: str, directory_to_skip: str) -> None:
    # Traverse the directory and list all files
    for root, _, files in os.walk(directory):
        if directory_to_skip not in root:
            sp = (root.split(directory)[-1]).split('/')[-1]
            for file in files:
                if bool(sp):
                    print(sp+'/'+file)
                else:
                    print(file)

def find_potential_duplicates_using_size(directory: str, directory_to_skip: str) -> None:
    # Traverse the directory and list all files
    file_size_to_name_mapper = defaultdict(list)
    for root, _, files in os.walk(directory):
        if directory_to_skip not in root:
            sp = (root.split(directory)[-1]).split('/')[-1]
            # print('sp', sp)
            for file in files: 

                # Build the full file path
                file_path = os.path.join(root, file)

                # Remove the directory prefix for output
                if bool(sp):
                    relative_path = f"{sp}/{file}"
                else:
                    relative_path = file

                # Get the file size
                try:
                    file_size = os.path.getsize(file_path)
                    # Add file path to the list for that size
                    if file_size in file_size_to_name_mapper:
                        file_size_to_name_mapper[file_size].append(relative_path)
                        if len(file_size_to_name_mapper[file_size])>1:
                            print('Potential duplicates:', *file_size_to_name_mapper[file_size])
                    else:
                        file_size_to_name_mapper[file_size].append(relative_path)
                except OSError as e:
                    print(f"Error accessing file {file_path}: {e}")

def compute_md5(file_path: str) -> str:
    """Compute MD5 checksum for a given file."""
    hash_md5 = hashlib.md5()
    try:
        with open(file_path, "rb") as f:
            # Read file in chunks to avoid memory overload
            for chunk in iter(lambda: f.read(4096), b""):
                hash_md5.update(chunk)
    except Exception as e:
        print(f"Error reading file {file_path}: {e}")
        return None
    return hash_md5.hexdigest()

def find_potential_duplicates_using_MD5_checksum(directory: str, directory_to_skip: str, min_size: int) -> dict:
    potential_duplicates={}
    # Traverse the directory and list all files
    file_size_to_name_mapper = defaultdict(list)
    for root, _, files in os.walk(directory):
        if directory_to_skip not in root:
            sp = (root.split(directory)[-1]).split('/')[-1]
            # print('sp', sp)
            for file in files: 

                # Build the full file path
                file_path = os.path.join(root, file)

                # Remove the directory prefix for output
                if bool(sp):
                    relative_path = f"{sp}/{file}"
                else:
                    relative_path = file

                # Get the file hash
                try:
                    file_size = os.path.getsize(file_path)

                    if file_size < min_size:
                        continue

                    file_hash = compute_md5(file_path)

                    # Add file path to the list for that size
                    if file_hash in file_size_to_name_mapper:
                        file_size_to_name_mapper[file_hash].append(relative_path)
                        if len(file_size_to_name_mapper[file_hash])>1:
                            # print('Potential duplicates:', *file_size_to_name_mapper[file_hash])
                            potential_duplicates[file_hash] = file_size_to_name_mapper[file_hash]
                    else:
                        file_size_to_name_mapper[file_hash].append(relative_path)
                except OSError as e:
                    print(f"Error accessing file {file_path}: {e}")

    return potential_duplicates

def compare_two_files(file1: str, file2: str) -> bool:
    """Compare two files byte by byte."""
    try:
        with open(file1, 'rb') as f1, open(file2, 'rb') as f2:
            while True:
                b1 = f1.read(4096)
                b2 = f2.read(4096)
                if b1 != b2:
                    return False
                if not b1:  # End of file reached for both
                    break
        return True
    except Exception as e:
        print(f"Error comparing files {file1} and {file2}: {e}")
        return False

def compare_files_per_byte(files: list) -> bool:
    """Use multithreading to compare multiple files concurrently."""
    with ThreadPoolExecutor() as executor:
        # Create tasks for comparing each pair of files
        futures = []
        for i in range(len(files)):
            for j in range(i + 1, len(files)):
                futures.append(executor.submit(compare_two_files, files[i], files[j]))

        # Collect results as they are completed
        for future in as_completed(futures):
            result = future.result()
            if result:
                return True
    return False

if __name__ == "__main__":

    # Use argparse to parse command-line arguments
    parser = argparse.ArgumentParser(description="Find and compare duplicate files.")
    parser.add_argument("directory", type=str, help="Directory to search for duplicate files.")
    parser.add_argument("--minsize", type=int, default=0, help="Minimum file size (in bytes) to include in the comparison.")

    # Parse the arguments
    args = parser.parse_args()

    directory = args.directory
    min_size = args.minsize

    # Check if the provided argument is a valid directory
    if not os.path.isdir(directory):
        print(f"Error: {directory} is not a valid directory.")
        sys.exit(1)

    DIRECTORY_TO_SKIP = '.git'

    ######### Step 1
    # Call the function to list files
    # list_files(directory, directory_to_skip)

    ######### Step 2
    #find potential duplicates using file size
    # find_potential_duplicates_using_size(directory, directory_to_skip)

    ######### Step 3 and Step 5
    # find potential duplicates using file MD5 checksum
    potential_duplicates = find_potential_duplicates_using_MD5_checksum(directory, DIRECTORY_TO_SKIP, min_size)

    ######### Step 4
    #find duplicates by comparing files byte by byte
    duplicate_flag = False
    for _ , pot_dup_list in potential_duplicates.items():
        duplicates = compare_files_per_byte(pot_dup_list)
        if duplicates:
            duplicate_flag = True
            duplicate_str = ' '.join(pot_dup_list)
            print(f'Duplicates: {duplicate_str}')
            print('Which file should be deleted?')
            for idx, file in enumerate(pot_dup_list):
                print(str(idx+1)+') '+file)
            try:
                user_input=int(input())
                if user_input in range(1,len(pot_dup_list)+1):
                    os.remove(pot_dup_list[user_input-1]) ######### Step 6
                    print('File deleted')
                else:
                    print('Incorrect Input, hence keeping the files.')
            except Exception:
                print('Incorrect Input, hence keeping the files.')

    if not duplicate_flag:
        print('Congrats, No Duplicates Found.')

