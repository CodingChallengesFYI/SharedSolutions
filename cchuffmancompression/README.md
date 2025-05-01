# Challenge 3 - Write Your Own Compression Tool

This challenge corresponds to the third part of the Coding Challenges series by John Crickett https://codingchallenges.fyi/challenges/challenge-huffman.

## Description

This is a command line tool used to compress a given file using the [Huffman coding algorithm](https://en.wikipedia.org/wiki/Huffman_coding).

- `priority-queue.js` - This file contains the class definition for the prority queue.

- `create-node.js` - This file contains the class definition for the node in the huffman tree.

- `create-frequency-table.js` - This file contains the function for the frequency count for each character.

- `build-tree.js` - This file contains the function used to build the huffman tree. Each internal node has a left child, a right child and a frequency count. Each leaf node has a frequency count, and a value determining the character.

- `create-codes` - This file contains the function for generating prefix codes for all the characters in the input file.

- `index.ts` - This file contains the main function which is the entry point of the command line tool.

## Usage

You can use node to run the command line tool as follows

```bash
# To compress a file
node index.js --compress <input-file> <output-file>
```

Where the `input-file` corresponds to the file to be compressed and the `output-file` corresponds to the compressed file.

```bash
# To decompress a file
node index.js --decompress <compressed-file> <output-file>
```

Where the `compressed-file` corresponds to the compressed input file and the `output-file` corresponds to the decompressed file.
