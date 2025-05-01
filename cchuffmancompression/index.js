const fs = require("fs");
const { generateTable } = require("./create-frequency-table");
const { buildTree } = require("./build-tree");
const { generateCodes } = require("./create-codes");

const cmdlineargs = process.argv.slice(2);

const ops = cmdlineargs[0];

const inFile = cmdlineargs[1];

const outFile = cmdlineargs[2];

if (ops == "--compress" && cmdlineargs.length == 3) {
  encode(inFile);
} else if (ops == "--decompress" && cmdlineargs.length == 3) {
  decode(inFile);
} else {
  console.log(`Invalid command`);
}

function encode(inFile) {
  const message = fs.readFileSync(inFile, "utf-8");

  const freqTable = generateTable(message);

  const tree = buildTree(freqTable);

  const codes = {};

  generateCodes(tree, "", codes);

  let encoded = "";

  for (let char of message) {
    encoded += codes[char];
  }

  let serialised = serialise(tree);

  fs.writeFileSync(outFile, serialised + "|" + encoded);

  return serialised + "|" + encoded;
}

function serialise(tree) {
  let str = "";

  function tranverse(root) {
    if (!root.left && !root.right) {
      str += `1${root.char},`;
      return;
    }

    str += "0,";

    tranverse(root.left);
    tranverse(root.right);
  }

  tranverse(tree);
  return str;
}

function deserialise(str) {
  let queue = str.split(",");

  function tranverse(q) {
    if (!q.length) return null;

    let n = q.shift();

    if (n.startsWith("1")) {
      return { char: n[1] };
    }

    return {
      left: tranverse(q),
      right: tranverse(q),
    };
  }

  return tranverse(queue);
}

function decode(inFile) {
  const input = fs.readFileSync(inFile, "utf-8");

  const serialisedTree = input.split("|")[0];

  const encoded = input.split("|")[1];

  const tree = deserialise(serialisedTree);

  const codes = {};

  generateCodes(tree, "", codes);

  let decoded = "";

  let root = tree;

  for (let bit of encoded) {
    root = bit == "0" ? root.left : root.right;

    if (root.char) {
      decoded += root.char;
      root = tree;
    }
  }

  fs.writeFileSync(outFile, decoded);

  return decoded;
}

module.exports = { encode, decode };
