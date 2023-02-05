const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);
type Passport = Map<string, string>;
const allPassportsLists: Passport[] = [];

for (let i = 0, valueMap = new Map<string, string>(); i < inputArr.length; i += 1) {
    const line: string = inputArr[i];
    if (line !== '') {
        line.split(' ').forEach((e) => {
            const [name, value]: string[] = e.split(':');
            valueMap.set(name, value);
        });
    }
    if (line === '' || i === inputArr.length - 1) {
        const clone = new Map(valueMap);
        allPassportsLists.push(clone);
        valueMap.clear();
    }
}

// console.log('allPassportsList : ', allPassportsLists);

const checker: { [key: string]: (v: string) => boolean } = {
    byr: (value: string): boolean => {
        return Number(value) >= 1920 && Number(value) <= 2002;
    },
    iyr: (value: string) => {
        return Number(value) >= 2010 && Number(value) <= 2020;
    },
    eyr: (value: string) => {
        return Number(value) >= 2020 && Number(value) <= 2030;
    },
    hgt: (value: string) => {
        if (!value.includes('in') && !value.includes('cm')) return false;

        if (value.includes('in')) {
            const height: number = Number(value.split('i')[0]);
            return height >= 59 && height <= 76;
        }
        const height: number = Number(value.split('c')[0]);
        return height >= 150 && height <= 193;
    },
    hcl: (value: string) => {
        if (value[0] !== '#') return false;
        return value.slice(1).replace(/[a-z0-9]/g, '').length === 0;
    },
    ecl: (value: string) => {
        const searchList: string[] = ['amb', 'blu', 'brn', 'gry', 'grn', 'hzl', 'oth'];
        return searchList.includes(value);
    },
    pid: (value: string) => {
        if (value.length !== 9) return false;
        const testValue: boolean = /^[0-9]+$/.test(value);
        return testValue;
    },
};

const checkIfValid = (key: string, value: string): boolean => {
    return checker[key](value);
};

const checkPassport = (passportMap: Passport): number => {
    let isValid = true;
    passportMap.forEach((value: string, key: string) => {
        console.log(checkIfValid(key, value));

        if (!checkIfValid(key, value)) {
            isValid = false;
            // return;
        }
    });
    return isValid ? 1 : 0;
};

let result: number = 0;

console.log('####################');

for (const passport of Object.values(allPassportsLists)) {
    if (passport.has('cid')) passport.delete('cid');

    if (passport.size !== 7) continue;
    console.log('passport: ', passport);

    result += checkPassport(passport);
    console.log('result: ', result);
    console.log('----------------------');
}

console.log('end Result: ', result);
