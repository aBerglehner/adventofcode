const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: number[] = input.split('\r\n').map((num: string) => parseInt(num, 10));

const mySet = new Set<number>();

const searchSize: number = 25;
let result: number = -Infinity;

for (const num of inputArr) {
    if (mySet.size === searchSize) {
        let notFound = true;
        for (let it = mySet.values(), val = null; (val = it.next().value); ) {
            if (mySet.has(num - val) && num - val !== val) {
                notFound = false;
                break;
            }
        }
        if (notFound) {
            result = num;
            break;
        }

        const firstSetNum: number = mySet.values().next().value;
        mySet.delete(firstSetNum);
    }
    mySet.add(num);
}

console.log('result: ', result);
