const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

console.log(inputArr);

let counter: number = 0;
for (const pair of inputArr) {
    const [firstPair, secondPair] = pair.split(',');
    const [s1, e1, s2, e2] = [...firstPair.split('-'), ...secondPair.split('-')].map(
        (e) => parseInt(e, 10)
    );
    if ((s1 >= s2 && s1 <= e2) || (s2 >= s1 && s2 <= e1)) counter += 1;
}

console.log(counter);
