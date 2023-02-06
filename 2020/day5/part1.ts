const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

console.log(inputArr);

let max: number = 0;
for (const line of inputArr) {
    const row: number = binarySearch(line.slice(0, 7), 127);
    const col: number = binarySearch(line.slice(7), 7);
    max = Math.max(row * 8 + col, max);
    console.log(row * 8 + col);
}

function binarySearch(row: string, right: number): number {
    let result: number = 0;
    let left: number = 0;
    for (let i = 0; i < row.length; i += 1) {
        const char: string = row[i];
        if (char === 'F' || char === 'L') {
            const mid: number = left + Math.floor((right - left) / 2);
            right = mid;
            result = left;
        } else {
            const mid: number = left + Math.ceil((right - left) / 2);
            left = mid;
            result = right;
        }
    }
    return result;
}

console.log('result: ', max);
