var fs = require('fs');
var readLine = require('../readline.js');
var test = require("tap").test;

test("test reading lines",function(t){
   console.error("reading large file line by line asserts may take a while");
   var rl = readLine('./fixtures/afile.txt');
   rl.on("line", function (line,linecount){
     t.ok(null !== line && undefined !== line);
   });
   rl.on("end",function (){
   	t.end();
   });

});

test("numbers", function (t){
   var rl = readLine('./fixtures/nmbr.txt');
   var answer = 28;
   var i=0;
   rl.on("line", function (line){
   	 	var num = Number(line);
   	 	console.error(num);
        i+=num;

   });
   rl.on("end", function (){
   	console.error(i,answer);
   t.ok(answer === i, "answered");
   t.end();
   });
});


test("errors", function (t){
	var rl = readLine("./Idontexist");
    rl.on('error', function (e){
      t.ok(e);
      t.end();
    });
    rl.on('end', function (){
    	t.end();
    });
    rl.on('close', function(){
     t.end();
    });
});


test("line count", function(t){
  var rl = readLine('./fixtures/nmbr.txt');
  var expect = 7;
  var actual = 0;
  rl.on("line", function (line, ln){
    console.log("line",line,ln);
    actual=ln;
  });
  rl.on("end", function (){
    t.ok(actual === expect,"line count is correct");
    t.end();
  });
});

test("byte count after first line", function(t){
  var rl = readLine('./fixtures/nmbr.txt');
  var actual = 0;
  var expect;
  rl.on("line", function (line, ln, byteCount){
    if (expect === undefined) {
      expect = line.length;
      console.log("byte count",byteCount);
      actual=byteCount;

      t.ok(actual === expect,"byte count is correct");
      t.end();
    }
  });
});

test("byte count", function(t){
  var rl = readLine('./fixtures/nmbr.txt');
  var expect = fs.statSync('./fixtures/nmbr.txt').size;
  var actual = 0;
  rl.on("line", function (line, ln, byteCount){
    console.log("byte count",byteCount);
    actual=byteCount;
  });
  rl.on("end", function (){
    t.ok(actual === expect,"byte count is correct");
    t.end();
  });
});

test("processing error passed on", function(t){
  var rl = readLine('./fixtures/nmbr.txt');
  var lastError;
  var lineCalls = 0;

  rl.on("line", function (line, ln, byteCount){
    lineCalls++;
    if (ln === 7) {
      throw new Error('fake error');
    }
  });
  rl.on("error", function (err){
    if (!lastError) {
      lastError = err;
    }
  });

  rl.on("end", function (){
    t.ok(lastError.message === 'fake error','error is passed on');
    t.ok(lineCalls === 7, 'line count ok');
    t.end();
  });
});

test("test ascii file reading",function(t){
  var iconv = require('iconv-lite');
  var testFileValidationKeywords = {
    1: 'папка',
    3: 'телефон',
    11: 'электричество',
    14: 'дерево'
  };

  var rl = readLine('./fixtures/file-in-win1251.txt', {
    retainBuffer: true
  });
  rl.on("line", function (data,linecount){
    var line = iconv.decode(data, 'win1251');
    t.ok(!testFileValidationKeywords[linecount] || line.indexOf(testFileValidationKeywords[linecount]) > -1);
  });
  rl.on("end",function (){
    t.end();
  });

});