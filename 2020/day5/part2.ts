const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

let result: number[] = [];
for (const line of inputArr) {
    const row: number = binarySearch(line.slice(0, 7), 127);
    const col: number = binarySearch(line.slice(7), 7);
    const curId: number = row * 8 + col;
    result.push(curId);
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

result.sort((a, b) => a - b);
console.log('result: ', result);
for (let i = 1; i < result.length; i += 1) {
    if (result[i - 1] !== result[i] - 1) console.log(result[i] - 1);
}
