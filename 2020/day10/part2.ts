const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: number[] = input
    .split('\r\n')
    .map((e: string) => parseInt(e, 10))
    .sort((a: number, b: number) => a - b);

console.log(inputArr);

const getCombs = (n: number): number => {
    if (n === 0) {
        return 1;
    } else if (n === 1) {
        return 1;
    } else if (n === 2) {
        return 2;
    } else {
        return getCombs(n - 1) + getCombs(n - 2) + getCombs(n - 3);
    }
};

let total: number = 1;
let streak: number = 0;
let prev: number = 0;
for (const num of inputArr) {
    if (num === prev + 1) {
        streak += 1;
    } else {
        total *= getCombs(streak);
        streak = 0;
    }
    prev = num;
}
total *= getCombs(streak);

console.log('total: ', total);
