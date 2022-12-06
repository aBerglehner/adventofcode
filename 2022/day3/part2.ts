const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

const priority: string = '#abcdefghijklmnopqrstovwxyzABCDEFGHIJKLMNOPQRSTOVWXYZ';

let sum: number = 0;

for (let i = 0; i < inputArr.length; i += 3) {
    let first = inputArr[i];
    let second = inputArr[i + 1];
    let third = inputArr[i + 2];

    const findIntersection: string = [...first].find(
        (char) => second.includes(char) && third.includes(char)
    );
    sum += priority.indexOf(findIntersection);
}

console.log('sum', sum);
