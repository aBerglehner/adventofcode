const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

let templateStr: string = inputArr[0]; //?
const pairsObj: { [key: string]: string } = {};

for (let i = 2; i < inputArr.length; i += 1) {
    const [key, value] = inputArr[i].split(' -> ');
    // console.log(value);
    pairsObj[key] = value;
}

console.log('pairsObj: ', pairsObj);

const goalSteps: number = 10;
let steps: number = 1;

while (steps <= goalSteps) {
    console.log('steps: ', steps + ' | templateStr length: ', templateStr.length);

    // const len: number = s
    let newStr: string = templateStr[0];
    for (let i = 1; i < templateStr.length; i += 1) {
        const firstStr: string = templateStr[i - 1];
        const secondStr: string = templateStr[i];
        const pairStr: string = `${firstStr}${secondStr}`;
        const insert: string = pairsObj[pairStr]; //?
        newStr += insert + secondStr;
    }
    newStr; //?
    templateStr = newStr;
    steps += 1;
}

// console.log('startStr: ', templateStr);

const templateCountObj: { [key: string]: number } = {};

for (const str of templateStr) {
    templateCountObj[str] = (templateCountObj[str] || 0) + 1;
}

console.log('templateCountObj: ', templateCountObj);

const sortedCountObj: number[] = Object.values(templateCountObj).sort((a, b) => a - b);
console.log(sortedCountObj);

const result: number = sortedCountObj[sortedCountObj.length - 1] - sortedCountObj[0];
console.log('result: ', result);
