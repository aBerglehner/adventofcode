const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

const CRT: string[][] = Array.from({ length: 6 }, () => new Array(40).fill('?'));
//arr row[cycle // 40][cycle % 40] = Math.abs(registerSum - (cycle % 40)) <= 1 ? '#' : ' '

let cycle: number = 0;
let registerSum: number = 1;

for (const input of inputArr) {
    if (cycle > 240) break;
    const inputCycle: string[] = input.split(' ');
    // input 'noop' takes one cycle
    if (inputCycle.length === 1) {
        CRT[Math.floor(cycle / 40)][cycle % 40] =
            Math.abs(registerSum - (cycle % 40)) <= 1 ? '#' : ' ';
        cycle += 1;
        // length is 2 === addx X
        // takes 2 cycles and after that increase registerSum by X
    } else if (inputCycle.length > 1) {
        const value: number = +inputCycle[1];
        let counter: number = 2;
        while (counter) {
            CRT[Math.floor(cycle / 40)][cycle % 40] =
                Math.abs(registerSum - (cycle % 40)) <= 1 ? '#' : ' ';
            cycle += 1;
            counter -= 1;
        }
        registerSum += value;
    }
}

console.log('cycle: ', cycle);
console.log('registerSum: ', registerSum);

for (const row of CRT) {
    console.log(row.join(''));
}
