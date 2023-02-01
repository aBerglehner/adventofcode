const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

const matrix: number[][] = inputArr.map((row: string) =>
    row.split('').map((num: string) => parseInt(num, 10))
);
// console.log(matrix);

const rows: number = matrix.length; //?
const cols: number = matrix[0].length; //?

const basinsList: number[] = [];
const visited = new Set<string>();

function getBasinSize(r: number, c: number): number {
    let counter: number = 0;
    const queue: number[][] = [[r, c]];
    const cur: string = `r${r}c${c}`;
    visited.add(cur);
    while (queue.length) {
        counter += 1;
        const [row, col]: number[] = queue.shift()!;
        const topStr: string = `r${row - 1}c${col}`;
        const bottomStr: string = `r${row + 1}c${col}`;
        const leftStr: string = `r${row}c${col - 1}`;
        const rightStr: string = `r${row}c${col + 1}`;
        // top
        if (row - 1 >= 0 && matrix[row - 1][col] !== 9 && !visited.has(topStr)) {
            queue.push([row - 1, col]);
            visited.add(topStr);
        }
        // bottom
        if (row + 1 < rows && matrix[row + 1][col] !== 9 && !visited.has(bottomStr)) {
            queue.push([row + 1, col]);
            visited.add(bottomStr);
        }
        // left
        if (col - 1 >= 0 && matrix[row][col - 1] !== 9 && !visited.has(leftStr)) {
            queue.push([row, col - 1]);
            visited.add(leftStr);
        }
        // right
        if (col + 1 < cols && matrix[row][col + 1] !== 9 && !visited.has(rightStr)) {
            queue.push([row, col + 1]);
            visited.add(rightStr);
        }
    }
    return counter;
}

for (let row = 0; row < rows; row += 1) {
    for (let col = 0; col < cols; col += 1) {
        const cur: string = `r${row}c${col}`;
        if (visited.has(cur)) continue;
        if (matrix[row][col] !== 9) {
            basinsList.push(getBasinSize(row, col));
        }
    }
}

console.log('basinsList: ', basinsList);

const result: number = [...basinsList]
    .sort((a, b) => b - a)
    .slice(0, 3)
    .reduce((acc, cur) => (acc *= cur), 1);
console.log('result: ', result);
