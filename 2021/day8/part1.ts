const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

const uniqueNumbers: { [key: number]: number } = { 2: 1, 4: 4, 3: 7, 7: 8 };

let counter: number = 0;
for (const line of inputArr) {
    const outputValues = line.split('|')[1].trim().split(' ');
    console.log(outputValues);
    for (const pattern of outputValues) {
        if (pattern.length in uniqueNumbers) {
            counter += 1;
        }
    }
}
console.log('result: ', counter);
