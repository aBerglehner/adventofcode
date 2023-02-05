const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

function countTrees(right: number, down: number): number {
    let matrix: string[][] = inputArr.map((row: string) => row.split(''));
    const rows: number = matrix.length; //?
    const neededLength: number = rows * right; //?
    const multiply: number = Math.ceil(neededLength / matrix[0].length); //?
    matrix = matrix.map((row) => row.join('').repeat(multiply).split(''));
    const cols: number = matrix[0].length;

    let result: number = 0;
    let col = right;
    let row = down;

    while (row < rows && col < cols) {
        // console.log(matrix[row][col]);
        // console.log(counter);
        if (matrix[row][col] === '#') result += 1;
        row += down;
        col += right;
    }
    return result;
}

let result: number = 1;
result *= countTrees(1, 1);
console.log(countTrees(1, 1));

result *= countTrees(3, 1);
console.log(countTrees(3, 1));

result *= countTrees(5, 1);
console.log(countTrees(5, 1));

result *= countTrees(7, 1);
console.log(countTrees(7, 1));

result *= countTrees(1, 2);
console.log(countTrees(1, 2));

console.log('result: ', result);
