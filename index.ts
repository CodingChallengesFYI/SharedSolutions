#!/usr/bin/env node
const fs = require("node:fs");

const calculateFileBytes = (filePath: string, content: string = "") => {
  // check if filePath points to a valid file
  try {
    const data = content ? new Blob([content]) : fs.statSync(filePath);
    // get fileSize
    // log the fileSize in bytes
    return "  " + data.size;
  } catch (err) {
    console.error("Error occured while reading file:", filePath, "\n", err);
    return;
  }
};

const calculateFileLines = (filePath: string, content: string = "") => {
  // check if filePath points to a valid file
  try {
    const data = content ? content : fs.readFileSync(filePath, "utf-8");
    // get fileLines
    // log the fileLines
    return (
      "  " + data.split("\n").filter((val: string) => val.length > 0).length
    );
  } catch (err) {
    console.error("Error occured while reading file:", filePath, "\n", err);
    return;
  }
};

const calculateFileWords = (filePath: string, content: string = "") => {
  // check if filePath points to a valid file
  try {
    const data = content ? content : fs.readFileSync(filePath, "utf-8");
    // get fileWords
    // log the fileWords
    return (
      "  " +
      data.split(/[\n\r\s]+/).filter((val: string) => val.length > 0).length
    );
  } catch (err) {
    console.error("Error occured while reading file:", filePath, "\n", err);
    return;
  }
};

const calculateFileChars = (filePath: string, content: string = "") => {
  // check if filePath points to a valid file
  try {
    const data = content ? content : fs.readFileSync(filePath, "utf-8");
    // get fileChars
    // log the fileChars
    return "  " + data.split("").filter((val: string) => val.length > 0).length;
  } catch (err) {
    console.error("Error occured while reading file:", filePath, "\n", err);
  }
};

const performAction = (
  flag: string,
  filePath: string = "",
  content: string = ""
) => {
  switch (flag) {
    case "-c":
      console.log(calculateFileBytes(filePath, content), filePath);
      break;
    case "-l":
      console.log(calculateFileLines(filePath, content), filePath);
      break;
    case "-w":
      console.log(calculateFileWords(filePath, content), filePath);
      break;
    case "-m":
      console.log(calculateFileChars(filePath, content), filePath);
      break;
    case "":
      console.log(
        calculateFileLines(filePath, content),
        calculateFileWords(filePath, content),
        calculateFileBytes(filePath, content),
        filePath
      );
      break;
    default:
      console.log("Unknown flag:", flag);
  }
};

const args = process.argv.slice(2);
const flag = args[0] && args[0][0] === "-" ? args[0] : "";
const filePath = flag ? args[1] : args[0];

if (!filePath) {
  const stdin = process.stdin;
  let output = "";

  stdin.on("data", (chunk) => {
    output += chunk.toString();
  });

  stdin.on("end", () => {
    performAction(flag, filePath, output);
  });
} else {
  performAction(flag, filePath);
}
