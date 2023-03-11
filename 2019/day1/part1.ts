const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

console.log(inputArr);

const findNewMass: number[] = inputArr.map(
    (mass: string) => Math.floor(Number(mass) / 3) - 2
);
const sumNewMass = findNewMass.reduce((acc, cur) => (acc += cur), 0);

console.log('sumNewMass: ', sumNewMass);
