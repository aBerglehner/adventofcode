const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: number[][] = input
    .split('\r\n')
    .map((line: string) => line.split('').map((c) => +c));

// console.log(inputArr);

const rows: number = inputArr.length; //?
const cols: number = inputArr[0].length; //?

// top + bottom + (left - 2) + (right - 2)
const defaultVisible: number = rows + rows + (cols - 2) + (cols - 2);

const viewTop = (cur: number, row: number, col: number): boolean => {
    while (row >= 0) {
        if (cur <= inputArr[row][col]) return false;
        row -= 1;
    }
    return true;
};
const viewDown = (cur: number, row: number, col: number): boolean => {
    while (row < rows) {
        if (cur <= inputArr[row][col]) return false;
        row += 1;
    }
    return true;
};
const viewLeft = (cur: number, row: number, col: number): boolean => {
    while (col >= 0) {
        if (cur <= inputArr[row][col]) return false;
        col -= 1;
    }
    return true;
};
const viewRight = (cur: number, row: number, col: number): boolean => {
    while (col < cols) {
        if (cur <= inputArr[row][col]) return false;
        col += 1;
    }
    return true;
};

let counter: number = 0;
for (let row = 1; row < rows - 1; row += 1) {
    for (let col = 1; col < cols - 1; col += 1) {
        const cur: number = inputArr[row][col];
        if (
            viewTop(cur, row - 1, col) ||
            viewDown(cur, row + 1, col) ||
            viewLeft(cur, row, col - 1) ||
            viewRight(cur, row, col + 1)
        ) {
            counter += 1;
        }
    }
}
const result: number = defaultVisible + counter;
console.log(result);
