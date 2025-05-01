let { PQ } = require("./priority-queue");
let { Node } = require("./create-node");

function buildTree(freqTable) {
  let pq = new PQ();

  for (let char in freqTable) {
    let node = new Node();
    node.freq = freqTable[char];
    node.char = char;
    pq.enqueue(node);
  }

  // console.log(pq.list);

  while (pq.list.length > 1) {
    let node1 = pq.dequeue();
    let node2 = pq.dequeue();
    let combined = new Node();
    combined.freq = node1.freq + node2.freq;
    combined.left = node1;
    combined.right = node2;
    pq.enqueue(combined);
  }

  return pq.dequeue();
}

module.exports = { buildTree };
