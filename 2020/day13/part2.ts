const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

const busIdList: number[] = inputArr[1]
    .split(',')
    .map((bus: string) => (bus === 'x' ? 1 : Number(bus)));

let time = 0;
let stepSize = busIdList[0];

for (let i = 1; i < busIdList.length; i += 1) {
    const bus = busIdList[i];

    while ((time + i) % bus !== 0) {
        time += stepSize;
    }

    stepSize *= bus;
}

console.log('time: ', time);
