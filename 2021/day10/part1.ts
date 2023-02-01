const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

const closingBrackets: { [key: string]: string } = {
    ')': '(',
    ']': '[',
    '}': '{',
    '>': '<',
};

const bracketPoints: { [key: string]: number } = {
    ')': 3,
    ']': 57,
    '}': 1197,
    '>': 25137,
};

let result: number = 0;

const curruptedLines: string[] = inputArr.filter((line: string) => {
    const charList: string[] = line.split('');
    const stack: string[] = [];
    for (const char of charList) {
        if (char in closingBrackets) {
            const lastStackBracket: string = stack.pop()!;
            if (closingBrackets[char] !== lastStackBracket) {
                result += bracketPoints[char];
                return true;
            } // opening brackets
        } else {
            stack.push(char);
        }
    }
    return false;
});

console.log('curruptedLines: ', curruptedLines);
console.log('result: ', result);
