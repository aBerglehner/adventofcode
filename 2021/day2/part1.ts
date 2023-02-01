const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: string[] = input.split('\r\n');

// console.log(inputArr);

const result: { [key: string]: number } = { horizontal: 0, depth: 0 };

const changePosition: { [key: string]: (e: number) => void } = {
    forward: (e: number) => (result.horizontal += e),
    down: (e) => (result.depth += e),
    up: (e) => (result.depth -= e),
};

for (const input of inputArr) {
    const move: string = input.split(' ')[0]; //?
    const value: number = +input.split(' ')[1]; //?
    changePosition[move](value);
}

console.log(result.horizontal * result.depth);
