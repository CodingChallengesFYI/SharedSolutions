# Building Your Own Cat-Tool
This challenge is to build your own version of the Unix Command line tool cat.

I use for this challenge **argparse module** to parse my cat command line.

## Table of Contents

- [Progress](#progress)
- [Get and Install my Cat Tool](#get-and-install-my-cat-tool)
- [Ready to use my cat-tool commands](#ready-to-use-my-cat-tool-commands)
  - [Command to know the different possible commands of my cat-tool](#command-to-know-the-different-possible-commands-of-my-cat-tool)
  - [Command to display content of one file](#command-to-display-content-of-one-file)
  - [Command to display content of many files](#command-to-display-content-of-many-files)
  - [Command to read input from standard in](#command-to-read-input-from-standard-in)
  - [Command to number all the lines as they’re printed out](#command-to-number-all-the-lines-as-theyre-printed-out)
  - [Command to number the lines as they’re printed out except the blank-lines](#command-to-number-the-lines-as-theyre-printed-out-except-the-blank-lines)

## Progress
0. Test files received to test solution with.
1. Open specific file on the command line and write its contents to standard out.
2. Reads input from standard in
3. Can concatenate files
4. Number the lines as they’re printed out
5. Number lines but exclude blank lines from being numbered in output

## Get and Install my Cat Tool
### 1. Clone the Repo :
```
gh repo clone oussy96/coding-challenges
```
### 2. Move to the Cat-Tool Project :
```
cd coding-challenges/cat-tool
```
### 3. Create a Vitual Environment (Recommended) :
```
python -m venv venv
```

### 4. Activate the Virtual Environment:
```
source venv/bin/activate
```

## Ready to use my cat-tool commands
### Command to know the different possible commands of my cat-tool
```
% python cccat.py --help
```
Output :
```
usage: cccat [-h] [-] [-n] [-b] [filename ...]

Process cat file.

positional arguments:
  filename       The name of file to display his content

options:
  -h, --help     show this help message and exit
  -, --read      Read the input from standard in
  -n, --number   Number the lines printed out including non-blank lines
  -b, --bnumber  Number the lines printed out excluding non-blank lines
```

### Command to display content of one file
```
python cccat.py test.txt
```
Output :
```
"Your heart is the size of an ocean. Go find yourself in its hidden depths."
"The Bay of Bengal is hit frequently by cyclones. The months of November and May, in particular, are dangerous in this regard."
"Thinking is the capital, Enterprise is the way, Hard Work is the solution."
"If You Can'T Make It Good, At Least Make It Look Good."
"Heart be brave. If you cannot be brave, just go. Love's glory is not a small thing."
"It is bad for a young man to sin; but it is worse for an old man to sin."
"If You Are Out To Describe The Truth, Leave Elegance To The Tailor."
"O man you are busy working for the world, and the world is busy trying to turn you out."
"While children are struggling to be unique, the world around them is trying all means to make them look like everybody else."
"These Capitalists Generally Act Harmoniously And In Concert, To Fleece The People."
```

### Command to display content of many files
```
python cccat.py test.txt test2.txt
```
Output :
```
"Your heart is the size of an ocean. Go find yourself in its hidden depths."
"The Bay of Bengal is hit frequently by cyclones. The months of November and May, in particular, are dangerous in this regard."
"Thinking is the capital, Enterprise is the way, Hard Work is the solution."
"If You Can'T Make It Good, At Least Make It Look Good."
"Heart be brave. If you cannot be brave, just go. Love's glory is not a small thing."
"It is bad for a young man to sin; but it is worse for an old man to sin."
"If You Are Out To Describe The Truth, Leave Elegance To The Tailor."
"O man you are busy working for the world, and the world is busy trying to turn you out."
"While children are struggling to be unique, the world around them is trying all means to make them look like everybody else."
"These Capitalists Generally Act Harmoniously And In Concert, To Fleece The People."
"I Don'T Believe In Failure. It Is Not Failure If You Enjoyed The Process."
"Do not get elated at any victory, for all such victory is subject to the will of God."
"Wear gratitude like a cloak and it will feed every corner of your life."
"If you even dream of beating me you'd better wake up and apologize."
"I Will Praise Any Man That Will Praise Me."
"One Of The Greatest Diseases Is To Be Nobody To Anybody."
"I'm so fast that last night I turned off the light switch in my hotel room and was in bed before the room was dark."
"People Must Learn To Hate And If They Can Learn To Hate, They Can Be Taught To Love."
"Everyone has been made for some particular work, and the desire for that work has been put in every heart."
"The less of the World, the freer you live."
```

### Command to read input from standard in
```
head -n2 test2.txt | python cccat.py -
```
OR
```
head -n2 test2.txt | python cccat.py
```
Output :
```
"I Don'T Believe In Failure. It Is Not Failure If You Enjoyed The Process."
"Do not get elated at any victory, for all such victory is subject to the will of God."
```

### Command to number all the lines as they’re printed out
```
% head -n3 test.txt | python cccat.py -n
```
Output :
```
1 "Your heart is the size of an ocean. Go find yourself in its hidden depths."
2 "The Bay of Bengal is hit frequently by cyclones. The months of November and May, in particular, are dangerous in this regard."
3 "Thinking is the capital, Enterprise is the way, Hard Work is the solution."
```
Also the blank-lines :
```
sed G test.txt | python cccat.py -n | head -n4
```
Output :
```
1 "Your heart is the size of an ocean. Go find yourself in its hidden depths."
2 
3 "The Bay of Bengal is hit frequently by cyclones. The months of November and May, in particular, are dangerous in this regard."
4 
```

### Command to number the lines as they’re printed out except the blank-lines
```
sed G test.txt | python cccat.py -b | head -n5
```
Output :
```
1 "Your heart is the size of an ocean. Go find yourself in its hidden depths."

2 "The Bay of Bengal is hit frequently by cyclones. The months of November and May, in particular, are dangerous in this regard."

3 "Thinking is the capital, Enterprise is the way, Hard Work is the solution."
```

## Test our project using the TestCccat unittest class

The `TestCccat` class contains unit tests for the `cccat` module, which is responsible for processing and displaying content from files or standard input.

## Table of Contents

- [Introduction](#introduction)
- [Setup](#setup)
- [Tests](#tests)
  - [parse_arguments Function](#parse_arguments-function)
  - [read_file_content Function](#read_file_content-function)
  - [cc_cat Function](#cc_cat-function)
  - [read_input Function](#read_input-function)
  - [read_input_numbered_lines Function](#read_input_numbered_lines-function)
  - [read_input_bnumbered_lines Function](#read_input_bnumbered_lines-function)
- [Conclusion](#conclusion)

### Introduction

This test suite verifies the functionality of various functions within the `cccat` module. It utilizes Python's `unittest` framework to define test cases covering different scenarios and edge cases.

### Setup

To run the tests, ensure that you have Python installed on your system. The tests can be executed using the following command:

```bash
python -m unittest -v tests/test_cccat.py
```

### Tests

#### parse_arguments Function

- Tests the `parse_arguments` function with different command-line arguments to ensure correct parsing and handling of options.

#### read_file_content Function

- Tests the `read_file_content` function to verify that it correctly reads and returns the content of a file.

#### cc_cat Function

- Tests the `cc_cat` function with various scenarios, including reading content from multiple files, standard input, and applying line numbering options.

#### read_input Function

- Tests the `read_input` function to ensure that it correctly reads and returns input from standard input.

#### read_input_numbered_lines Function

- Tests the `read_input_numbered_lines` function to verify that it correctly numbers lines from standard input.

#### read_input_bnumbered_lines Function

- Tests the `read_input_bnumbered_lines` function to ensure that it correctly numbers lines from standard input, excluding blank lines.

### Conclusion

The `TestCccat` test class provides comprehensive coverage of the `cccat` module's functionality, ensuring its reliability and correctness across various scenarios.