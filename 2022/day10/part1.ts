const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

const cycleStrengthArr: number[] = [20, 60, 100, 140, 180, 220];

let cycle: number = 0;
let registerSum: number = 1;
const registerSumArr: number[] = [];

for (const input of inputArr) {
    if (cycle > 220) break;
    const inputCycle: string[] = input.split(' ');
    // input 'noop' takes one cycle
    if (inputCycle.length === 1) {
        cycle += 1;
        if (cycleStrengthArr.includes(cycle)) {
            registerSumArr.push(registerSum * cycle);
        }
        // length is 2 === addx X
        // takes 2 cycles and after that increase registerSum by X
    } else if (inputCycle.length > 1) {
        const value: number = +inputCycle[1];
        let counter: number = 2;
        while (counter) {
            cycle += 1;
            if (cycleStrengthArr.includes(cycle)) {
                registerSumArr.push(registerSum * cycle);
            }
            counter -= 1;
        }
        registerSum += value;
    }
}

console.log('cycle: ', cycle);
console.log('registerSum: ', registerSum);
console.log('registerSumArr: ', registerSumArr);

const result = registerSumArr.reduce((acc, cur) => (acc += cur), 0);
console.log('result: ', result);
