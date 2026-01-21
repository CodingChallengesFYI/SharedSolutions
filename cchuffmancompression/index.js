const fs = require("fs");

const [filename, command] = process.argv.slice(2);

class PriorityQueue {
  constructor() {
    this.heap = [];
  }

  _parent(i) {
    return Math.floor((i - 1) / 2);
  }
  _left(i) {
    return 2 * i + 1;
  }
  _right(i) {
    return 2 * i + 2;
  }

  _heapifyUp() {
    let i = this.heap.length - 1;
    while (i > 0 && this.heap[this._parent(i)].freq > this.heap[i].freq) {
      [this.heap[i], this.heap[this._parent(i)]] = [
        this.heap[this._parent(i)],
        this.heap[i],
      ];
      i = this._parent(i);
    }
  }

  _heapifyDown() {
    let i = 0;
    while (this._left(i) < this.heap.length) {
      let smaller = this._left(i);
      if (
        this._right(i) < this.heap.length &&
        this.heap[this._right(i)].freq < this.heap[smaller].freq
      ) {
        smaller = this._right(i);
      }
      if (this.heap[i].freq <= this.heap[smaller].freq) break;
      [this.heap[i], this.heap[smaller]] = [this.heap[smaller], this.heap[i]];
      i = smaller;
    }
  }

  enqueue(node) {
    this.heap.push(node);
    this._heapifyUp();
  }

  dequeue() {
    if (this.heap.length === 1) return this.heap.pop();
    const min = this.heap[0];
    this.heap[0] = this.heap.pop();
    this._heapifyDown();
    return min;
  }

  size() {
    return this.heap.length;
  }
}

class Node {
  constructor(char = "", freq = 0, left = null, right = null) {
    this.char = char;
    this.freq = freq;
    this.left = left;
    this.right = right;
  }
}

const generateCodes = (node, path = "", codes = {}) => {
  if (!node.left && !node.right) {
    codes[node.char] = path || "0"; // Handle edge case for single character
    return codes;
  }
  if (node.left) generateCodes(node.left, path + "0", codes);
  if (node.right) generateCodes(node.right, path + "1", codes);
  return codes;
};

const serialize = (node) => {
  if (!node.left && !node.right) return `1${node.char}`;
  return `0${serialize(node.left)}${serialize(node.right)}`;
};

const deserialize = (data) => {
  let index = 0;
  const traverse = () => {
    if (index >= data.length) return null;
    const bit = data[index++];
    if (bit === "1") {
      return new Node(data[index++]);
    }
    return new Node("", 0, traverse(), traverse());
  };
  return traverse();
};

const compress = (content) => {
  const freqMap = {};
  for (const char of content) freqMap[char] = (freqMap[char] || 0) + 1;

  const pq = new PriorityQueue();
  for (const char in freqMap) {
    pq.enqueue(new Node(char, freqMap[char]));
  }

  while (pq.size() > 1) {
    const n1 = pq.dequeue(),
      n2 = pq.dequeue();
    pq.enqueue(new Node("", n1.freq + n2.freq, n1, n2));
  }

  const tree = pq.dequeue();
  const codes = generateCodes(tree);
  const encoded = content
    .split("")
    .map((c) => codes[c])
    .join("");
  const serializedTree = serialize(tree);

  fs.writeFileSync(
    "compressed.txt",
    `${serializedTree}---END---${encoded}`,
    "utf-8"
  );
  console.log("Compression complete. Output: compressed.txt");
};

const decompress = (content) => {
  const [serializedTree, encoded] = content.split("---END---");
  const tree = deserialize(serializedTree);

  let decoded = "";
  let node = tree;
  for (const bit of encoded) {
    node = bit === "0" ? node.left : node.right;
    if (!node.left && !node.right) {
      decoded += node.char;
      node = tree;
    }
  }

  fs.writeFileSync("decompressed.txt", decoded, "utf-8");
  console.log("Decompression complete. Output: decompressed.txt");
};

// Read file and execute command
fs.readFile(filename, "utf-8", (err, content) => {
  if (err) return console.error("Error reading file:", err);
  if (command === "--compress") compress(content);
  else if (command === "--decompress") decompress(content);
  else console.error("Invalid command. Use --compress or --decompress");
});
