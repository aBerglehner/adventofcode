const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: string[] = input.split('\r\n');

// console.log(inputArr);

let result: number = 0;

for (const row of inputArr) {
    let [minMax, searchStr, pw]: string[] = row.split(' ');
    const [min, max]: number[] = minMax.split('-').map((e: string) => parseInt(e, 10));
    searchStr = searchStr[0];
    // console.log(min, max);
    // console.log(searchStr);
    // console.log(pw);
    const sumSearchStr: number = [...pw].filter((c: string) => c === searchStr).length;
    // console.log('sumSearchStr: ', sumSearchStr);

    sumSearchStr >= min && sumSearchStr <= max && (result += 1);
}

console.log('result: ', result);
