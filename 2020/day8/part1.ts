const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

let acc: number = 0;
let i: number = 0;
const visited = new Set<string>();

while (i < inputArr.length) {
    const instruction: string = inputArr[i].split(' ')[0].trim();
    const sign: string = inputArr[i].split(' ')[1].slice(0, 1);
    const num: number = +inputArr[i].split(' ')[1].slice(1).trim();

    // console.log('visited: ', visited);
    // console.log(`inputArr[${i}]: `, inputArr[i]);
    // console.log('acc: ', acc);
    // console.log('-----------------');

    const identifier: string = `i${i}:${inputArr[i]}`;
    if (visited.has(identifier)) break;
    visited.add(identifier);

    if (instruction === 'acc') {
        if (sign === '+') {
            acc += num;
        } else {
            acc -= num;
        }
    } else if (instruction === 'jmp') {
        if (sign === '+') {
            i += num - 1;
        } else {
            i -= num + 1;
        }
    } else {
    }

    i += 1;
}

console.log('acc: ', acc);
