function generateTable(message) {
  let table = {};
  for (let char of message) {
    if (table[char]) {
      table[char]++;
    } else {
      table[char] = 1;
    }
  }

  return table;
}

module.exports = { generateTable };
