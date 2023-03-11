const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

// console.log(inputArr);

for (let x1 = 0; x1 < 100; x1 += 1) {
    for (let x2 = 0; x2 < 100; x2 += 1) {
        const inputArr: number[] = input.split(',').map(Number);
        inputArr[1] = x1;
        inputArr[2] = x2;
        for (let i = 0; i < inputArr.length - 3; i += 4) {
            const cur = inputArr[i];

            if (cur === 99) break;
            if (cur === 1) {
                const firstNum = inputArr[inputArr[i + 1]];
                const secondNum = inputArr[inputArr[i + 2]];

                inputArr[inputArr[i + 3]] = firstNum + secondNum;
            }
            if (cur === 2) {
                const firstNum = inputArr[inputArr[i + 1]];
                const secondNum = inputArr[inputArr[i + 2]];
                inputArr[inputArr[i + 3]] = firstNum * secondNum;
            }
        }
        if (inputArr[0] === 19690720) {
            console.log(x1, x2);
            console.log('result: ', 100 * x1 + x2);
        }
    }
}

// console.log(inputArr);
