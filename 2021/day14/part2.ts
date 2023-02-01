const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

let templateStr: string = inputArr[0]; //?
const pairsObj: { [key: string]: string } = {};

for (let i = 2; i < inputArr.length; i += 1) {
    const [key, value] = inputArr[i].split(' -> ');
    pairsObj[key] = value;
}

console.log('pairsObj: ', pairsObj);

let pairCounterObj: { [key: string]: number } = {};
for (const key of Object.keys(pairsObj)) {
    pairCounterObj[key] = 0;
}
for (let i = 1; i < templateStr.length; i += 1) {
    const firstStr: string = templateStr[i - 1];
    const secondStr: string = templateStr[i];
    const pairStr: string = `${firstStr}${secondStr}`;
    pairCounterObj[pairStr] += 1;
}
console.log('pairCounterObj: ', pairCounterObj);

const goalSteps: number = 40;
let steps: number = 1;

while (steps <= goalSteps) {
    console.log('steps: ', steps + ' | templateStr length: ', templateStr.length);
    const copyPairCounterObj = { ...pairCounterObj };
    for (const key in pairCounterObj) {
        const value = pairCounterObj[key];
        const firstPair = `${key[0]}${pairsObj[key]}`;
        const secondPair = `${pairsObj[key]}${key[1]}`;
        if (value === 1) {
            copyPairCounterObj[firstPair];
            copyPairCounterObj[secondPair];
        }
        copyPairCounterObj[firstPair] += value;
        copyPairCounterObj[secondPair] += value;
        copyPairCounterObj[key] -= value;
    }
    pairCounterObj = { ...copyPairCounterObj };
    steps += 1;
}

console.log('pairCounterObj: ', pairCounterObj);

const minMaxPairs: { [key: string]: number[] } = {};
const minMaxSumChars: { [key: string]: { minSum: number; maxSum: number } } = {};

for (const key in pairCounterObj) {
    minMaxSumChars[key[0]] = { minSum: 0, maxSum: 0 };
    minMaxSumChars[key[1]] = { minSum: 0, maxSum: 0 };

    const reverseKey: string = `${key[1]}${key[0]}`;
    const sortedKey: string = Array.from(key).sort().join('');
    const min: number = Math.min(pairCounterObj[key], pairCounterObj[reverseKey]);
    const max: number = Math.max(pairCounterObj[key], pairCounterObj[reverseKey]);
    if (!(sortedKey in minMaxPairs)) {
        minMaxPairs[sortedKey] = [min, max];
    }
}

console.log('minMaxPairs: ', minMaxPairs);

for (const key in minMaxPairs) {
    const firstChar: string = key[0];
    const secondChar: string = key[1];
    const [min, max]: number[] = minMaxPairs[key];
    minMaxSumChars[firstChar].minSum += min;
    minMaxSumChars[firstChar].maxSum += max;
    // dont count it double (HH,CC)
    // would count 2 times minSum and 2 times MaxSum
    if (firstChar === secondChar) continue;
    minMaxSumChars[secondChar].minSum += min;
    minMaxSumChars[secondChar].maxSum += max;
}

console.log('minMaxSumChars: ', minMaxSumChars);

Object.entries(minMaxSumChars); //?
const sortedCharCounter: number[] = Object.entries(minMaxSumChars)
    .reduce((acc: number[], [_, obj]) => {
        const { minSum, maxSum } = obj;
        acc.push(Math.ceil((minSum + maxSum) / 2));
        return acc;
    }, [])
    .sort((a, b) => a - b);

console.log('sortedCharCounter: ', sortedCharCounter);

const result: number =
    sortedCharCounter[sortedCharCounter.length - 1] - sortedCharCounter[0];

console.log('result: ', result);
