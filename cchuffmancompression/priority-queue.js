class PQ {
  list = [];

  getParentIndex(i) {
    return Math.floor((i - 1) / 2);
  }

  getLeftChildIndex(i) {
    return 2 * i + 1;
  }

  getRightChildIndex(i) {
    return 2 * i + 2;
  }

  hasParent(i) {
    return this.getParentIndex(i) >= 0;
  }

  hasLeftChild(i) {
    return this.getLeftChildIndex(i) < this.list.length;
  }

  hasRightChild(i) {
    return this.getRightChildIndex(i) < this.list.length;
  }

  getParent(i) {
    return this.list[this.getParentIndex(i)];
  }

  getLeftChild(i) {
    return this.list[this.getLeftChildIndex(i)];
  }

  getRightChild(i) {
    return this.list[this.getRightChildIndex(i)];
  }

  swap(index1, index2) {
    let temp = this.list[index1];

    this.list[index1] = this.list[index2];

    this.list[index2] = temp;
  }

  enqueue(data) {
    this.list.push(data);
    this.heapifyUp();
  }

  dequeue() {
    let data = this.list[0];

    this.list[0] = this.list[this.list.length - 1];

    this.list.pop();

    this.heapifyDown();

    return data;
  }

  heapifyUp() {
    let i = this.list.length - 1;

    while (this.hasParent(i) && this.getParent(i).freq > this.list[i].freq) {
      this.swap(this.getParentIndex(i), i);

      i = this.getParentIndex(i);
    }
  }

  heapifyDown() {
    let i = 0;

    while (this.hasLeftChild(i)) {
      let smallestIndex = this.getLeftChildIndex(i);

      if (
        this.hasRightChild(i) &&
        this.getRightChild(i).freq < this.list[i].freq
      ) {
        smallestIndex = this.getRightChildIndex(i);
      }

      this.swap(smallestIndex, i);
      i = smallestIndex;
    }
  }
}

module.exports = { PQ };
