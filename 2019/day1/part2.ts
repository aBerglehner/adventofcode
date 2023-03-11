const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);
const sumList = (acc: number, cur: number) => (acc += cur);
const getNewMass = (mass: number) => {
    const requiredMass: number[] = [];
    while (Math.floor(Number(mass) / 3) - 2 > 0) {
        mass = Math.floor(Number(mass) / 3) - 2;
        requiredMass.push(mass);
    }
    return requiredMass.reduce(sumList, 0);
};

const mapToNum: number[] = inputArr.map(Number);
const findNewMass: number[] = mapToNum.map(getNewMass);
const sumNewMass = findNewMass.reduce(sumList, 0);

console.log('sumNewMass: ', sumNewMass);

// 5101069
