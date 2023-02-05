const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: number[] = input.split('\r\n').map((e: string) => parseInt(e, 10));

console.log(inputArr);

const saveSet = new Set<number>();
let result: number = -Infinity;
for (const num of inputArr) {
    saveSet.forEach((setNum) => {
        const searchedNum: number = 2020 - num - setNum;
        if (saveSet.has(searchedNum)) {
            result = num * setNum * searchedNum;
        }
    });
    saveSet.add(num);
}

console.log('saveSet: ', saveSet);
console.log('result: ', result);
