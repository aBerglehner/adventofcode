const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: number[] = input.split(',').map(Number);

// console.log(inputArr);
inputArr[1] = 12;
inputArr[2] = 2;

for (let i = 0; i < inputArr.length - 3; i += 4) {
    const cur = inputArr[i];

    if (cur === 99) break;
    if (cur === 1) {
        const firstNum = inputArr[inputArr[i + 1]];
        const secondNum = inputArr[inputArr[i + 2]];

        inputArr[inputArr[i + 3]] = firstNum + secondNum;
    }
    if (cur === 2) {
        const firstNum = inputArr[inputArr[i + 1]];
        const secondNum = inputArr[inputArr[i + 2]];
        inputArr[inputArr[i + 3]] = firstNum * secondNum;
    }
}

console.log(inputArr);
