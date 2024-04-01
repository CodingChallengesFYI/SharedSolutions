import os
import argparse
import sys


def parse_arguments():
    """
    Parse command line arguments using ArgumentParser.

    Returns:
        argparse.Namespace: Parsed arguments.
    """
    # Create ArgumentParser object
    parser = argparse.ArgumentParser(prog='cccat', description='Process cat file or input.')

    # Add arguments
    parser.add_argument('filename', type=str, nargs='*', help='The name of file to display its content')
    parser.add_argument('-', '--read', action='store_true', help='Read the input from standard in')
    parser.add_argument('-n', '--number', action='store_true', help='Number the lines printed out including non-blank lines')
    parser.add_argument('-b', '--bnumber', action='store_true', help='Number the lines printed out excluding non-blank lines')
    
    return parser.parse_args()


def get_filepath(filename):
    """
    Get the absolute file path.

    Args:
        filename (str): Name of the file.

    Returns:
        str: Absolute file path.
    """
    return os.path.join(os.getcwd(), filename)


def read_file_content(filename):
    """
    Read and return the content of a file.

    Args:
        filename (str): Name of the file.

    Returns:
        str: Content of the file.
    """
    try:
        with open(filename, 'r') as file:
            return file.read()
    except FileNotFoundError:
        print(f"Error: {filename} not found")


def read_input():
    """
    Read and return input from standard input.

    Returns:
        str: Input from standard input.
    """
    return sys.stdin.read()


def read_input_numbered_lines():
    """
    Read input from standard input and number the lines.

    Returns:
        str: Input from standard input with numbered lines.
    """
    return ''.join((f'{index+1} {line}' for index, line in enumerate(sys.stdin))) 


def read_input_bnumbered_lines():
    """
    Read input from standard input and number the lines excluding non-blank lines.

    Returns:
        str: Input from standard input with numbered lines excluding non-blank lines.
    """
    num_line = 1
    result = ''
    for line in sys.stdin:
        if line.strip():
            result += f'{num_line} {line}'
            num_line += 1 
        else:
            result += line
    return result


def cc_cat(args):
    """
    Execute the 'cccat' command based on the provided arguments.

    Args:
        args (argparse.Namespace): Parsed command line arguments.

    Returns:
        str: Output of the 'cccat' command.
    """
    if args.filename:
        return ''.join(read_file_content(filename) for filename in args.filename)
    elif args.read or not any(vars(args).values()): # If no arguments are provided, execute the 'read' command by default
        return read_input()
    elif args.number:
        return read_input_numbered_lines()
    elif args.bnumber:
        return read_input_bnumbered_lines()


if __name__ == "__main__":
    args = parse_arguments()
    print(cc_cat(args))
