const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

const matrix: number[][] = inputArr.map((row: string) =>
    row.split('').map((num: string) => parseInt(num, 10))
);

function joinMatrix(matrix: number[][]): void {
    for (const row of matrix) {
        console.log(row.join(''));
    }
    console.log('---------------');
}

joinMatrix([...matrix]);

const rows: number = matrix.length;
const cols: number = matrix[0].length;

let steps: number = 1;
const goal: number = 400;

// console.log('after steps: ', steps);

while (steps <= goal) {
    let flashedCounter: number = 0;
    const octopusesNumber: number = rows * cols;
    const flashStack: number[][] = [];
    const alreadyFlashed = new Set<string>();
    for (let row = 0; row < rows; row += 1) {
        for (let col = 0; col < cols; col += 1) {
            matrix[row][col] += 1;
            if (matrix[row][col] > 9) {
                flashStack.push([row, col]);
                const curStr = `r${row}c${col}`;
                alreadyFlashed.add(curStr);
                flashedCounter += 1;
            }
        }
    }
    // console.log('flashStack: ', flashStack);

    while (flashStack.length) {
        const [row, col]: number[] = flashStack.pop()!;
        const curFlashedStr: string = `r${row}c${col}`;
        alreadyFlashed.add(curFlashedStr);
        matrix[row][col] = 0;
        // top,bottom
        const topStr: string = `r${row - 1}c${col}`;
        const bottomStr: string = `r${row + 1}c${col}`;
        // left,right
        const leftStr: string = `r${row}c${col - 1}`;
        const rightStr: string = `r${row}c${col + 1}`;
        // dia
        const topLeft: string = `r${row - 1}c${col - 1}`;
        const topRight: string = `r${row - 1}c${col + 1}`;
        const leftBottom: string = `r${row + 1}c${col - 1}`;
        const rightBottom: string = `r${row + 1}c${col + 1}`;

        function flashAround(row: number, col: number): void {
            matrix[row][col] += 1;
            if (matrix[row][col] > 9) {
                const curStr = `r${row}c${col}`;
                flashStack.push([row, col]);
                alreadyFlashed.add(curStr);
                matrix[row][col] = 0;
                flashedCounter += 1;
            }
        }
        function notAlreadyFlashed(direction: string): boolean {
            return !alreadyFlashed.has(direction);
        }
        // top, bottom
        if (row - 1 >= 0 && notAlreadyFlashed(topStr)) {
            flashAround(row - 1, col);
        }
        if (row + 1 < rows && notAlreadyFlashed(bottomStr)) {
            flashAround(row + 1, col);
        }
        // left, right
        if (col - 1 >= 0 && notAlreadyFlashed(leftStr)) {
            flashAround(row, col - 1);
        }
        if (col + 1 < cols && notAlreadyFlashed(rightStr)) {
            flashAround(row, col + 1);
        }
        // dia
        // topLeft, topRight
        if (row - 1 >= 0 && col - 1 >= 0 && notAlreadyFlashed(topLeft)) {
            flashAround(row - 1, col - 1);
        }
        if (row - 1 >= 0 && col + 1 < cols && notAlreadyFlashed(topRight)) {
            flashAround(row - 1, col + 1);
        }
        // leftBottom, rightBottom
        if (row + 1 < rows && col - 1 >= 0 && notAlreadyFlashed(leftBottom)) {
            flashAround(row + 1, col - 1);
        }
        if (row + 1 < rows && col + 1 < cols && notAlreadyFlashed(rightBottom)) {
            flashAround(row + 1, col + 1);
        }
    }

    console.log('after steps: ', steps);
    joinMatrix([...matrix]);
    if (octopusesNumber === flashedCounter) {
        break;
    }

    steps += 1;
}

// joinMatrix([...matrix]);
