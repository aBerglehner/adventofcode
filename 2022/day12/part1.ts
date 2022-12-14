const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: string[] = input.split('\r\n');

console.log(inputArr);

const matrix: string[][] = inputArr.map((e) => e.split(''));

const rows: number = matrix.length;
const cols: number = matrix[0].length;
// [row,col]
const startPoint: number[] = [];
const endPoint: number[] = [];

for (let row = 0; row < rows; row += 1) {
    for (let col = 0; col < cols; col += 1) {
        if (matrix[row][col] === 'S') startPoint.push(row, col);
        if (matrix[row][col] === 'E') endPoint.push(row, col);
    }
}

console.log('startPoint: ', startPoint);
console.log('endPoint: ', endPoint);

// Up, Down, Right, Left
const directions: number[][] = [
    [1, 0],
    [-1, 0],
    [0, 1],
    [0, -1],
];

const abc: string = 'abcdefghijklmnopqrstuvwxyz';

matrix[startPoint[0]][startPoint[1]] = 'a';

interface Queue {
    row: number;
    col: number;
    steps: number;
    visited: Set<string>;
}
[];
const queue: Queue[] = [
    {
        row: startPoint[0],
        col: startPoint[1],
        steps: 0,
        visited: new Set<string>(['r0c0']),
    },
];

let result: number[] = [];
while (queue.length) {
    const { row, col, steps, visited }: Queue = queue.shift()!;
    console.log('queue.shift: ', row, col, steps);

    if (row === endPoint[0] && col === endPoint[1] && matrix[row][col] === 'E') {
        console.log('steps: ', steps);
        break;
    }

    for (const [directionRow, directionCol] of directions) {
        const newRow: number = row + directionRow;
        const newCol: number = col + directionCol;

        // if invalid skip
        if (newRow < 0 || newRow >= rows) continue;
        if (newCol < 0 || newCol >= cols) continue;
        // check for invalid
        const visit: string = `r${newRow}c${newCol}`;
        if (visited.has(visit)) continue;

        const oldHeight: string = matrix[row][col];
        const curHeight: string = matrix[newRow][newCol];
        console.log('oldHeight: ', oldHeight);
        console.log('curHeight: ', curHeight);

        // if oldHeight === z and curHeight is E push
        if (oldHeight === 'z' && curHeight === 'E') {
            queue.push({ row: newRow, col: newCol, steps: steps + 1, visited });
            result.push(steps + 1);
        }

        // if step up over 1 letter in between continue a -> c
        const diff: number = abc.indexOf(curHeight) - abc.indexOf(oldHeight);
        if (diff > 1) continue;
        // if curHeight would be E but the move before would not be z
        if (oldHeight !== 'z' && curHeight === 'E') continue;

        // else queue.push(newRow, newCol, steps + 1)
        queue.push({ row: newRow, col: newCol, steps: steps + 1, visited });

        // set cur invalid
        visited.add(visit);
    }
}

console.log('#######################');
console.log('result: ', result);
