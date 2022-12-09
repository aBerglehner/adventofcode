const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: number[][] = input
    .split('\r\n')
    .map((line: string) => line.split('').map((c) => +c));

const rows: number = inputArr.length; //?
const cols: number = inputArr[0].length; //?

const scenicScoreArr: number[][] = Array.from({ length: rows }, () =>
    new Array(cols).fill(0)
);
console.log(scenicScoreArr);

const viewTop = (cur: number, row: number, col: number): number => {
    let result: number = 1;
    while (row > 0) {
        if (cur <= inputArr[row][col]) break;
        row -= 1;
        result += 1;
    }
    return result;
};
const viewDown = (cur: number, row: number, col: number): number => {
    let result: number = 1;
    while (row < rows - 1) {
        if (cur <= inputArr[row][col]) break;
        row += 1;
        result += 1;
    }
    return result;
};
const viewLeft = (cur: number, row: number, col: number): number => {
    let result: number = 1;
    while (col > 0) {
        if (cur <= inputArr[row][col]) break;
        col -= 1;
        result += 1;
    }
    return result;
};
const viewRight = (cur: number, row: number, col: number): number => {
    let result: number = 1;
    while (col < cols - 1) {
        if (cur <= inputArr[row][col]) break;
        col += 1;
        result += 1;
    }
    return result;
};

for (let row = 1; row < rows - 1; row += 1) {
    for (let col = 1; col < cols - 1; col += 1) {
        const cur: number = inputArr[row][col];

        const scenicScore: number =
            viewTop(cur, row - 1, col) *
            viewLeft(cur, row, col - 1) *
            viewDown(cur, row + 1, col) *
            viewRight(cur, row, col + 1);
        scenicScoreArr[row][col] = scenicScore;
        if (row === 3 && col === 2) {
            viewTop(cur, row - 1, col); //?
            viewLeft(cur, row, col - 1); //?
            viewDown(cur, row + 1, col); //?
            viewRight(cur, row, col + 1); //?
        }
    }
}

console.log(scenicScoreArr);
console.log('max', Math.max(...scenicScoreArr.map((arr) => Math.max(...arr))));
