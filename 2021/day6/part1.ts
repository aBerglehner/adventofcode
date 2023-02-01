const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

interface Dic {
    [key: number]: number;
}

const daysOfFish: number[] = inputArr[0].split(',').map((e: string) => parseInt(e, 10));
let dic: Dic = {};

for (const day of daysOfFish) {
    dic[day] = (dic[day] || 0) + 1;
}

// console.log(daysOfFish);
// console.log(dic);
// console.log(Object.entries(dic));

// let days: number = 80;
let days: number = 256;

while (days) {
    const len: number = daysOfFish.length;
    const y: Dic = {};
    for (let [key, cnt] of Object.entries(dic)) {
        const x: number = +key;
        if (x === 0) {
            y[6] = (y[6] || 0) + cnt;
            y[8] = (y[8] || 0) + cnt;
        } else {
            y[x - 1] = (y[x - 1] || 0) + cnt;
        }
    }
    dic = y;
    days -= 1;
}

console.log('-----------------');
console.log('dic: ', dic);
console.log(
    'sum: ',
    Object.values(dic).reduce((acc, cur) => (acc += cur), 0)
);
