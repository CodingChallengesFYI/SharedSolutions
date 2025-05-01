function generateCodes(tree, prefix, codesTable) {
  if (tree.char) {
    codesTable[tree.char] = prefix;
    return;
  }

  generateCodes(tree.left, prefix + "0", codesTable);
  generateCodes(tree.right, prefix + "1", codesTable);
}

module.exports = { generateCodes };
