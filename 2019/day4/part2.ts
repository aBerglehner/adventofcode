const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

console.log('inputArr: ', inputArr);

const [startNum, endNum]: number[] = inputArr[0].split('-').map(Number);
console.log('startNum: ', startNum);
console.log('endNum: ', endNum);

const numList = Array.from({ length: endNum - startNum + 1 }, (_, i) => startNum + i);
const ascending = (str: string) => str === str.split('').sort().join('');
const getCharCount = (acc: { [key: string]: number }, char: string) => {
    acc[char] = (acc[char] || 0) + 1;
    return acc;
};
const withDouble = (str: string) => {
    const charCount = [...str].reduce(getCharCount, {});
    return Object.values(charCount).find((tuple) => tuple === 2);
};

const result = numList.map(String).filter(ascending).filter(withDouble).length;

console.log('result: ', result);
