const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: any = input.split('\r\n');
// console.log(inputArr);

const pairsArr = inputArr.reduce((acc: any, cur: any) => {
    if (cur !== '') {
        acc.push(JSON.parse(cur));
    }
    return acc;
}, []);

function zip(...arrays: any): any {
    const length = Math.min(...arrays.map((arr) => arr.length));
    return Array.from({ length }, (_, index) => arrays.map((array) => array[index]));
}

function compare(first: any, second: any): number {
    if (typeof first === 'number') {
        if (typeof second === 'number') {
            return first - second;
            // first === number second === array
        } else {
            return compare([first], second);
        }
    } else {
        // second === number first === array
        if (typeof second === 'number') {
            return compare(first, [second]);
        }
    }

    for (const [left, right] of zip(first, second)) {
        const v = compare(left, right);
        if (v) {
            return v;
        }
    }

    return first.length - second.length;
}
console.log(pairsArr);

let i2: number = 1;
let i6: number = 2;

for (let i = 0; i < pairsArr.length; i += 1) {
    const packet = pairsArr[i];

    if (compare(packet, [[2]]) < 0) {
        i2 += 1;
        i6 += 1;
    } else if (compare(packet, [[6]]) < 0) {
        i6 += 1;
    }
}

console.log('-----------------------');

console.log(i2, i6);
console.log(i2 * i6);
