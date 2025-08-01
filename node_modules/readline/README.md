## _readline_
> Read a file line by line.

## Install

## Important. In node 10 there is a core module named readline. Please use linebyline instead, it is the same module just renamed:
[Npm linebyline](https://www.npmjs.com/package/linebyline)

```sh
npm install linebyline
```

## Test
```sh
npm install .
npm test

```


## What's this?

Simple streaming readline module for NodeJS. Reads a file and buffers new lines emitting a _line_ event for each line.

## Usage
### Simple
```js
  var readline = require('linebyline'),
      rl = readline('./somefile.txt');
  rl.on('line', function(line, lineCount, byteCount) {
    // do something with the line of text
  })
  .on('error', function(e) {
    // something went wrong
  });
```

### ASCII file decoding
As the underlying `fs.createReadStream` doesn't care about the specific ASCII encoding of the file, an alternative way to decode the file is by telling the `readline` library to retain buffer and then decoding it using a converter (e.g. [`iconv-lite`](https://www.npmjs.com/package/iconv-lite)).
```js
  var readline = require('linebyline'),
      rl = readline('./file-in-win1251.txt', {
    retainBuffer: true //tell readline to retain buffer 
  });
  rl.on("line", function (data,linecount){
    var line = iconv.decode(data, 'win1251');
    // do something with the line of converted text
  });
```
##API
## readLine(readingObject[, options])
### Params:

* `readingObject` - file path or stream object
* `options` can include:
  * `maxLineLength` - override the default 4K buffer size (lines longer than this will not be read)
  * `retainBuffer` - avoid converting to String prior to emitting 'line' event; will pass raw buffer with encoded data to the callback

### Return:

* **EventEmitter** 


## License

BSD © [Craig Brookes](http://craigbrookes.com/)
