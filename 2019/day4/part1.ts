const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

console.log('inputArr: ', inputArr);

const [startNum, endNum]: number[] = inputArr[0].split('-').map(Number);
console.log('startNum: ', startNum);
console.log('endNum: ', endNum);

const matching: string[] = [];
for (let i = startNum; i <= endNum; i += 1) {
    const strNum = i.toString();
    const strNumList = strNum.split('');
    if (/(.)\1/.test(strNum) && strNum === strNumList.sort().join('')) {
        matching.push(strNum);
    }
}
console.log('matching: ', matching.length);

const numList = Array.from({ length: endNum - startNum + 1 }, (_, i) => startNum + i);
const regexWithDouble = /(.)\1/;
const withDouble = (str: string) => regexWithDouble.test(str);
const ascending = (str: string) => str === str.split('').sort().join('');

const result = numList.map(String).filter(ascending).filter(withDouble).length;

console.log('result: ', result);
