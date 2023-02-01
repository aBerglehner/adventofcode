const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

const matrix: number[][] = inputArr.map((row: string) =>
    row.split('').map((num: string) => parseInt(num, 10))
);

// console.log(matrix);

const rows: number = matrix.length; //?
const cols: number = matrix[0].length; //?

function isLowPoint(row: number, col: number): boolean {
    // search top
    if (row - 1 >= 0 && matrix[row - 1][col] <= matrix[row][col]) return false;
    // search bottom
    if (row + 1 < rows && matrix[row + 1][col] <= matrix[row][col]) return false;
    // search left
    if (col - 1 >= 0 && matrix[row][col - 1] <= matrix[row][col]) return false;
    // search right
    if (col + 1 < cols && matrix[row][col + 1] <= matrix[row][col]) return false;
    return true;
}

const lowPointsArr: number[] = [];
for (let row = 0; row < rows; row += 1) {
    for (let col = 0; col < cols; col += 1) {
        if (isLowPoint(row, col)) {
            lowPointsArr.push(matrix[row][col]);
        }
    }
}

// console.log('lowPointsArr: ', lowPointsArr);

const result: number = lowPointsArr.reduce((acc, cur) => (acc += cur + 1), 0);

console.log('result: ', result);
