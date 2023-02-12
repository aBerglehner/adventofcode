const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: string[] = input.split('\r\n');

let result: number = 0;

const mySet = new Set<string>();

for (const line of inputArr) {
    if (line === '') {
        // console.log('mySet: ', mySet);
        result += mySet.size;
        mySet.clear();
    }
    for (const char of line) {
        mySet.add(char);
    }
}

result += mySet.size;

console.log('result: ', result);
