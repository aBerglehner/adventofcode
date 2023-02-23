const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: number[] = input
    .split('\r\n')
    .map((e: string) => parseInt(e, 10))
    .sort((a: number, b: number) => a - b);

console.log(inputArr);

let oneDiff: number = 1;
let threeDiff: number = 1;
for (let i = 1; i < inputArr.length; i += 1) {
    if (inputArr[i] - inputArr[i - 1] === 1) {
        oneDiff += 1;
    } else if (inputArr[i] - inputArr[i - 1] === 3) {
        threeDiff += 1;
    }
}

console.log('oneDiff: ', oneDiff);
console.log('threeDiff: ', threeDiff);
console.log('result: ', oneDiff * threeDiff);
