const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

let mostCommonBit: string = '';
let leastCommonBit: string = '';

const rows: number = inputArr.length; //?
const cols: number = inputArr[0].length; //?
const halfRows: number = rows / 2; //?

for (let col = 0; col < cols; col += 1) {
    let bitCounter: number = 0;
    for (let row = 0; row < rows; row += 1) {
        if (inputArr[row][col] === '1') bitCounter += 1;
    }
    bitCounter;
    mostCommonBit += bitCounter > halfRows ? '1' : '0';
    leastCommonBit += bitCounter > halfRows ? '0' : '1';
}

mostCommonBit;
leastCommonBit;
const result: number = parseInt(mostCommonBit, 2) * parseInt(leastCommonBit, 2); //?
console.log(result);
