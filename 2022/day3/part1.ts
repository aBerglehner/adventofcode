const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

const priority: string = '#abcdefghijklmnopqrstovwxyzABCDEFGHIJKLMNOPQRSTOVWXYZ';

let sum: number = 0;
for (const rucksack of inputArr) {
    const half: number = rucksack.length / 2;
    const firstHalf: string[] = rucksack.slice(0, half);
    const secondHalf: string[] = rucksack.slice(half);
    for (const char of firstHalf) {
        if (secondHalf.includes(char)) {
            sum += priority.indexOf(char);
            break;
        }
    }
}
console.log('sum', sum);
