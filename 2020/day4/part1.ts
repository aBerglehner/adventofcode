const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

const allPassportsLists: string[][] = [];
for (let i = 0, curPassportList: string[] = []; i < inputArr.length; i += 1) {
    const line: string = inputArr[i];
    if (line !== '') {
        curPassportList.push(
            ...line.split(' ').map((data: string) => data.split(':')[0])
        );
    }
    if (line === '' || i === inputArr.length - 1) {
        allPassportsLists.push([...curPassportList]);
        curPassportList.length = 0;
    }
}

// console.log('allPassportsList : ', allPassportsLists);

let result: number = 0;

for (const passwort of allPassportsLists) {
    const credentialsMap: { [key: string]: boolean } = {
        byr: false,
        iyr: false,
        eyr: false,
        hgt: false,
        hcl: false,
        ecl: false,
        pid: false,
    };
    passwort.forEach((value: string) => (credentialsMap[value] = true));
    result += Object.values(credentialsMap).every((e) => e) ? 1 : 0;
}

console.log('result: ', result);
