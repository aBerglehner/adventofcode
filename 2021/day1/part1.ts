const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

let result: number = 0;

for (let i = 1; i < inputArr.length; i += 1) {
    const prev: number = +inputArr[i - 1];
    const cur: number = +inputArr[i];
    if (cur > prev) result += 1;
}

console.log('result: ', result);
