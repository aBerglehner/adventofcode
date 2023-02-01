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
    '(': 1,
    '[': 2,
    '{': 3,
    '<': 4,
};

const result: number[] = [];

inputArr.forEach((line: string) => {
    const charList: string[] = line.split('');
    const stack: string[] = [];
    for (const char of charList) {
        if (char in closingBrackets) {
            const lastStackBracket: string = stack.pop()!;
            if (closingBrackets[char] !== lastStackBracket) {
                // skips to next forEach loop
                // and not the for loop!!!
                return;
            } // opening brackets
        } else {
            stack.push(char);
        }
    }
    let score: number = 0;
    while (stack.length) {
        const bracket = stack.pop()!;
        score = score * 5 + bracketPoints[bracket];
    }
    result.push(score);
});

// console.log('incompleteLines: ', incompleteLines);
console.log('result: ', result);
result.sort((a, b) => a - b);
console.log('middle: ', result[Math.floor(result.length / 2)]);
