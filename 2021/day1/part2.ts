const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

const listOfPairs: number[] = [];

for (let i = 0; i < inputArr.length; i += 1) {
    let sum: number = +inputArr[i];
    if (i + 2 < inputArr.length) {
        for (let j = i + 1; j < i + 3; j += 1) {
            sum += +inputArr[j];
        }
        listOfPairs.push(sum);
    }
}

console.log(listOfPairs);

let result: number = 0;
for (let i = 1; i < listOfPairs.length; i += 1) {
    const prev: number = +listOfPairs[i - 1];
    const cur: number = +listOfPairs[i];
    if (cur > prev) result += 1;
}
console.log('result: ', result);
