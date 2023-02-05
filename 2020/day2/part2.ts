const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: string[] = input.split('\r\n');

// console.log(inputArr);

let result: number = 0;

for (const row of inputArr) {
    let [minMax, searchStr, pw]: string[] = row.split(' ');
    const [contains, notContains]: number[] = minMax
        .split('-')
        .map((e: string) => parseInt(e, 10));
    searchStr = searchStr[0];
    // if (searchStr.length > notContains) continue;

    // console.log(pw[contains - 1]);
    // console.log(pw[notContains - 1]);

    if (pw[contains - 1] === searchStr && pw[notContains - 1] !== searchStr) {
        result += 1;
    }
    if (pw[contains - 1] !== searchStr && pw[notContains - 1] === searchStr) {
        result += 1;
    }
}

console.log('result: ', result);
