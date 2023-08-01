const fs = require("fs");
export const x = "";

const input = fs.readFileSync("./input.txt", "utf8");

const inputArr = input.split("\r\n");

console.log(inputArr);

const foldList: string[] = [];
const coordinates: number[][] = [];
// y
let rows: number = 0;
// x
let cols: number = 0;
let isFoldArea: boolean = false;

for (const line of inputArr) {
  if (line === "") {
    isFoldArea = true;
    continue;
  }
  if (isFoldArea) {
    foldList.push(line.slice(11));
  } else {
    const [col, row]: number[] = line
      .split(",")
      .map((e: string) => parseInt(e, 10));
    coordinates.push([row, col]);
    rows = Math.max(rows, row);
    cols = Math.max(cols, col);
  }
}

console.log("foldList: ", foldList);
console.log("rows: ", rows);
console.log("cols: ", cols);

rows = rows + 1;
cols = cols + 1;
let matrix: string[][] = Array.from({ length: rows }, () =>
  new Array(cols).fill(".")
);

function joinMatrix(matrix: string[][]): string {
  let result: string = "";
  for (const row of matrix) {
    result += "\n" + row.join("");
  }
  console.log();
  return result;
}

console.log("coordinates: ", coordinates);

while (coordinates.length) {
  const [row, col]: number[] = coordinates.pop()!;
  matrix[row][col] = "#";
}

// console.log('matrix:\n', joinMatrix(matrix));

// fold at column
function foldMatrixOnX(
  matrix: string[][],
  rows: number,
  cols: number,
  fold: number
): string[][] {
  const result: string[][] = [...matrix];
  const lastColFirstHalf: number = fold - 1;
  const minColSecondHalf: number = fold + 1;
  for (let row = 0; row < rows; row += 1) {
    for (let col = fold; col < cols; col += 1) {
      const cur: string = result[row][col];
      if (cur === "#") {
        const curDiff: number = Math.abs(minColSecondHalf - col);
        const copyToCol: number = Math.abs(lastColFirstHalf - curDiff);
        result[row][copyToCol] = "#";
      }
    }
    result[row] = result[row].slice(0, fold);
  }

  return result;
}

// fold at row
function foldMatrixOnY(
  matrix: string[][],
  rows: number,
  cols: number,
  fold: number
): string[][] {
  let result: string[][] = [...matrix];
  const lastRowFirstHalf: number = fold - 1;
  const minRowSecondHalf: number = fold + 1;
  for (let row = fold; row < rows; row += 1) {
    for (let col = 0; col < cols; col += 1) {
      // console.log('result: ', result);
      // console.log('row,col: ', row, col);
      const cur: string = result[row][col];
      if (cur === "#") {
        const curDiff: number = Math.abs(minRowSecondHalf - row);
        const copyToRow: number = Math.abs(lastRowFirstHalf - curDiff);
        // console.log('copyToRow: ', copyToRow);

        result[copyToRow][col] = "#";
      }
    }
  }
  result = result.slice(0, fold);
  return result;
}

let counter: number = 1;
while (counter) {
  const [foldType, fold]: string[] = foldList.shift()!.split("=");
  const foldNum = parseInt(fold, 10);

  // console.log(foldType, foldNum);
  if (foldType === "y") {
    matrix = foldMatrixOnY(matrix, rows, cols, foldNum);
  } else {
    matrix = foldMatrixOnX(matrix, rows, cols, foldNum);
  }

  rows = matrix.length;
  cols = matrix[0].length;
  counter -= 1;
}

console.log("matrix: ", joinMatrix(matrix));

const countHash: number = matrix.reduce((acc, row) => {
  acc += row.filter((e) => e === "#").length;
  return acc;
}, 0);

console.log("countHash: ", countHash);
