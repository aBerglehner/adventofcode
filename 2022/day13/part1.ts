const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: any = input.split('\r\n');
// console.log(inputArr);

const pairsArr = inputArr.reduce(
    (acc: any, cur: any) => {
        if (cur !== '') {
            if (acc[acc.length - 1].length < 2) {
                acc[acc.length - 1].push(JSON.parse(cur));
            } else {
                acc.push([JSON.parse(cur)]);
            }
        }
        return acc;
    },
    [[]]
);

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

    while (first.length && second.length) {
        const left: number = first.shift();
        const right: number = second.shift();
        const v = compare(left, right);
        if (v) {
            return v;
        }
    }

    return first.length - second.length;
}

const result: number[] = [];

for (let i = 0; i < pairsArr.length; i += 1) {
    const pair = pairsArr[i];
    const [first, second] = pair;

    if (compare(first, second) < 0) {
        result.push(i + 1);
    }
}

console.log('-----------------------');
console.log(result);
const sumResult: number = result.reduce((acc, cur) => (acc += cur), 0);
console.log('sum: ', sumResult);
