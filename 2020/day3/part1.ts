const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.Log(inputArr);

let matrix: string[][] = inputArr.map((row: string) => row.split(''));
const rows: number = matrix.length; //?
const neededLength: number = rows * 3; //?
const multiply: number = Math.ceil(neededLength / matrix[0].length); //?
matrix = matrix.map((row) => row.join('').repeat(multiply).split(''));

// console.log(matrix);

function printMatrix(matrix: string[][]) {
    for (const row of matrix) {
        console.log(row.join(''));
    }
}

// printMatrix(matrix);
const cols: number = matrix[0].length;
console.log('rows: ', rows);
console.log('cols: ', cols);

let result: number = 0;
let counter = 0;

let col = 3;
let row = 1;

while (row < rows && col < cols) {
    // console.log(matrix[row][col]);
    counter += 1;
    // console.log(counter);

    if (matrix[row][col] === '#') result += 1;
    row += 1;
    col += 3;
}

console.log('result: ', result);
