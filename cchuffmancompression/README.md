# 🗜️ Huffman Compression Tool in Node.js

This project implements a basic **file compressor and decompressor** using the [Huffman coding algorithm](https://en.wikipedia.org/wiki/Huffman_coding), built entirely in **Node.js**.

> 💡 This project was created as part of [John Crickett’s]() _"Coding Challenges"_ challenge series.

---

## 📦 Features

- 📊 Builds a Huffman tree from character frequencies
- 🔢 Generates binary codes for each character
- 💾 Compresses and decompresses `.txt` files
- 🧠 Minimal implementation without external libraries
- ⚡ Optimized with priority queues and efficient tree traversal

---

## 🚀 Getting Started

### Prerequisites

- [Node.js](https://nodejs.org/) (v14 or newer recommended)

### Clone the Repository

```bash
git clone https://github.com/your-username/huffman-compressor.git
cd huffman-compressor
```

### Usage

#### 1. Compress a file

```bash
node index..js input.txt --compress
```

- Creates a file called `compressed.txt`

#### 2. Decompress a file

```bash
node index.js compressed.txt --decompress
```

- Creates a file called `decompressed.txt`

---

## 🧠 How It Works

### Compression

1. Count character frequencies
2. Build a min-heap (priority queue)
3. Construct a binary Huffman tree
4. Generate binary codes for each character
5. Encode message using generated codes
6. Serialize the tree + encoded string to a file

### Decompression

1. Deserialize the Huffman tree
2. Traverse the tree according to each bit
3. Recover original characters and write output

---

## 📁 Project Structure

```
huffman-compressor/
│
├── index.js           # Main script (compress/decompress)
├── input.txt            # Sample input file
├── compressed.txt       # Output after compression
├── decompressed.txt     # Output after decompression
└── README.md            # This file
```

---

## 🔧 Example

```
Input:        hello huffman
Compressed:   (binary string + serialized tree)
Decompressed: hello huffman
```

---
