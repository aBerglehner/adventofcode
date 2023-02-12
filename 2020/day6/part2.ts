const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: string[] = input.split('\r\n');

let result: number = 0;
const myMap = new Map<string, number>();
let counter = 0;

for (const line of inputArr) {
    if (line === '') {
        // console.log('result: ', result);
        // console.log('counter: ', counter);

        myMap.forEach((value, key) => {
            // console.log(value, key);
            if (value === counter) result += 1;
        });

        myMap.clear();
        counter = 0;
        continue;
    }
    for (const char of line) {
        myMap.set(char, (myMap.get(char) || 0) + 1);
    }
    counter += 1;
}
myMap.forEach((value, key) => {
    // console.log(value, key);
    if (value === counter) result += 1;
});

console.log('result: ', result);
