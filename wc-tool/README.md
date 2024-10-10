# WC-TOOL

A simple command line tool in NodeJS to find the size of the file in bytes, number of lines, number of words and simple combination of commands.
This challenge corresponds to the first part of the Coding Challenges series by John Crickett https://codingchallenges.fyi/challenges/challenge-wc.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)

## Installation

Install NodeJS

## Usage

To run the tool:
```
node index.js [option] [filepath]
```

The following commands are supported:
```
-c: print file size in bytes
-l: print the number of lines in the file
-w: print the number of words in the file
-m: print the number of characters in the file(including other locales)
default: print the lines, words and size
```

This tool also supports
```
node index.js "cat [filename] | [option]"
```
