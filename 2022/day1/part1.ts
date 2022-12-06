const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

const caloriesEachElf: number[] = [];
let sum: number = 0;
for (const num of inputArr) {
    if (num === '') {
        caloriesEachElf.push(sum);
        sum = 0;
    } else {
        sum += parseInt(num, 10);
    }
}
caloriesEachElf.sort((a, b) => a - b);

console.log(caloriesEachElf[caloriesEachElf.length - 1]);
