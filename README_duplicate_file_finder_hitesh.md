# Duplicate File Finder
This Python script identifies and removes duplicate files in a specified directory using file size, MD5 checksum, and byte-by-byte comparison to ensure accuracy.

## Features
- **Find Duplicates by File Size**: Quickly identifies potential duplicates by comparing file sizes.
- **MD5 Checksum Comparison**: Flags duplicates by generating MD5 hashes.
- **Byte-by-Byte File Comparison**: Confirms duplicates by comparing file contents byte by byte.
- **Multithreading**: Speeds up file comparison using concurrent threads.
- **Customizable File Size Filtering**: Use the `--minsize` option to filter out small files from comparison.

## Prerequisites
1. **Python 3.x**
2. **Shell Script**: To generate test files, you'll first need to run the provided shell script.

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/duplicate_file_finder.git
2. Navigate to the directory:
   ```bash
   cd duplicate_file_finder

## Generating Test Files:
Before using the duplicate file finder, generate random files in both the root directory and a subdirectory using the provided shell script.

1. Run the script in the root directory:
   ```bash
   ./duplicate_file_generator.sh

This will create 20 random files in the current directory.

2. To create duplicate files in a subdirectory, create a new directory, navigate to it, and run the script again:
   ```bash
   mkdir duplicate_level_2
   cd duplicate_level_2
   ../duplicate_file_generator.sh

This will create another set of random files in the duplicate_level_2 subdirectory.

## Usage:
After generating test files, run the Python script to find duplicates:

`python3 duplicate_file_finder.py <directory> --minsize=<size_in_bytes>`

## Arguments:
- `<directory>`: The directory to search for duplicate files.
- `--minsize`: (Optional) Minimum file size (in bytes) to include in the comparison.

Example:

`python3 duplicate_file_finder.py /path/to/directory --minsize=1000`

This command will search for duplicate files larger than 1000 bytes in the specified directory.

## How It Works:
1. **File Listing**: Lists all files in the directory, excluding specified subdirectories.
2. **Duplicate Detection by MD5 Checksum**: Flags potential duplicates by comparing MD5 checksums.
3. **Byte-by-Byte Comparison**: Confirms if files with matching MD5 checksums are true duplicates by comparing them byte by byte.
4. **Deletion**: The script prompts the user to choose which duplicate files to delete.

## Functions Overview:
- `find_potential_duplicates_using_MD5_checksum(directory, directory_to_skip, min_size)`: Finds potential duplicates based on MD5 checksums.
- `compare_two_files(file1, file2)`: Compares two files byte by byte.
- `compare_files_per_byte(files)`: Uses multithreading to compare files concurrently.
- `list_files(directory, directory_to_skip)`: Lists files in the directory, excluding specified subdirectories.

## License:
This project is licensed under the MIT License.
