import unittest
from unittest.mock import patch
from io import StringIO
import os
import sys
import argparse

# Import the functions from my script cccat
from cccat import parse_arguments, read_file_content, read_input, read_input_numbered_lines, read_input_bnumbered_lines, cc_cat

class TestCccat(unittest.TestCase):
    
    # Test parse_arguments function with arguments patched
    @patch('sys.argv', ['cccat', 'file1.txt'])
    def test_parse_arguments_with_file(self):
        args = parse_arguments()
        self.assertEqual(args.filename, ['file1.txt'])
        self.assertFalse(args.read)
        self.assertFalse(args.number)
        self.assertFalse(args.bnumber)

    @patch('sys.argv', ['cccat', '-'])
    def test_parse_arguments_with_standard_input(self):
        args = parse_arguments()
        self.assertEqual(args.filename, [])
        self.assertTrue(args.read)
        self.assertFalse(args.number)
        self.assertFalse(args.bnumber)

    @patch('sys.argv', ['cccat'])
    def test_parse_arguments_with_no_arguments_passed(self):
        # Redirect stdin to provide input
        with patch('sys.stdin', StringIO("Test standard input")):
            args = parse_arguments()
            # Get the output directly from cc_cat
            output = cc_cat(args)
            # Check if no filename is provided
            self.assertEqual(args.filename, [])
            
            # Check if other flags are not set
            self.assertFalse(args.read)
            self.assertFalse(args.number)
            self.assertFalse(args.bnumber)
            
            # Check if the 'read' command is executed correctly
            self.assertEqual(output, "Test standard input")

    @patch('sys.argv', ['cccat', '-n'])
    def test_parse_arguments_with_numbered_lines(self):
        args = parse_arguments()
        self.assertEqual(args.filename, [])
        self.assertFalse(args.read)
        self.assertTrue(args.number)
        self.assertFalse(args.bnumber)

    @patch('sys.argv', ['cccat', '-b'])
    def test_parse_arguments_with_bnumbered_lines(self):
        args = parse_arguments()
        self.assertEqual(args.filename, [])
        self.assertFalse(args.read)
        self.assertFalse(args.number)
        self.assertTrue(args.bnumber)
    
    # Test read_file_content function
    def test_read_file_content(self):
        # Create a temporary file
        with open('test_file.txt', 'w') as f:
            f.write('Test content')
        
        content = read_file_content('test_file.txt')
        self.assertEqual(content, 'Test content')
        
        # Clean up
        os.remove('test_file.txt')

    # Test reading content from multiple files
    def test_cc_cat_multiple_files(self):
        # Create temporary files with content
        with open('file1.txt', 'w') as f1, open('file2.txt', 'w') as f2:
            f1.write('Content from file 1\n')
            f2.write('Content from file 2\n')
        
        # Call cc_cat with file names as arguments
        args = argparse.Namespace(filename=['file1.txt', 'file2.txt'], read=False, number=False, bnumber=False)
        output = cc_cat(args)
        
        # Verify that the concatenated content of both files is returned correctly
        expected_output = 'Content from file 1\nContent from file 2\n'
        self.assertEqual(output, expected_output)
        
        # Clean up temporary files
        os.remove('file1.txt')
        os.remove('file2.txt')
    
    # Test read_input function
    @patch('sys.stdin', StringIO('Test input'))
    def test_read_input(self):
        input_ = read_input()
        self.assertEqual(input_, 'Test input')
    
    # Test read_input_numbered_lines function
    @patch('sys.stdin', StringIO('Line 1\n\nLine 2\nLine 3\n'))
    def test_read_input_numbered_lines(self):
        numbered_lines = read_input_numbered_lines()
        self.assertEqual(numbered_lines, '1 Line 1\n2 \n3 Line 2\n4 Line 3\n')
    
    # Test read_input_bnumbered_lines function
    @patch('sys.stdin', StringIO('Line 1\n\nLine 2\n\nLine 3\n'))
    def test_read_input_bnumbered_lines(self):
        bnumbered_lines = read_input_bnumbered_lines()
        self.assertEqual(bnumbered_lines, '1 Line 1\n\n2 Line 2\n\n3 Line 3\n')

    # Test cc_cat function
    @patch('sys.stdin', StringIO('Line 1\nLine 2\nLine 3\n'))
    def test_cc_cat_read_input(self):
        args = argparse.Namespace(filename=[], read=True, number=False, bnumber=False)
        output = cc_cat(args)
        self.assertEqual(output, 'Line 1\nLine 2\nLine 3\n')
    
    @patch('sys.stdin', StringIO('Line 1\nLine 2\nLine 3\n'))
    def test_cc_cat_read_input_numbered_lines(self):
        args = argparse.Namespace(filename=[], read=False, number=True, bnumber=False)
        output = cc_cat(args)
        self.assertEqual(output, '1 Line 1\n2 Line 2\n3 Line 3\n' )

    @patch('sys.stdin', StringIO('Line 1\n\nLine 2\n\nLine 3\n'))
    def test_cc_cat_read_input_bnumbered_lines(self):
        args = argparse.Namespace(filename=[], read=False, number=False, bnumber=True)
        output = cc_cat(args)
        self.assertEqual(output, '1 Line 1\n\n2 Line 2\n\n3 Line 3\n')


if __name__ == '__main__':
    unittest.main()
