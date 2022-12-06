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

// if the array is rly large I would do this
// sum = 0;
// for (let i = caloriesEachElf.length - 1; i >= caloriesEachElf.length - 3; i -= 1) {
//     sum += caloriesEachElf[i];
// }
// console.log(sum);

// but I went for a much more declarative way
const topElves: number = 3;
const topElvesCalories: number = caloriesEachElf
    .slice(-topElves)
    .reduce((acc, cur) => (acc += cur), 0);
console.log(topElvesCalories);
