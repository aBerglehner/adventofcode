const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input
    .split('\r\n')
    .map((e: string) => e.split('->').map((t) => t.trim()));

// console.log(inputArr);

let minCols: number = Infinity;
let maxCols: number = -Infinity;
let maxRows: number = -Infinity;
for (const line of inputArr) {
    for (let i = 0; i < line.length; i += 1) {
        let [col, row]: number[] = line[i].split(',').map((e: string) => +e);
        minCols = Math.min(minCols, col);
        maxCols = Math.max(maxCols, col);
        maxRows = Math.max(maxRows, row);
    }
}

const rows: number = maxRows + 3;
const cols: number = (maxCols - minCols) * 8;
const delimiter: number = Math.floor((maxCols + minCols) / 2) - Math.floor(cols / 2); //?
const matrix = Array.from({ length: rows }, () => new Array(cols).fill('.'));
let maxColHit: number = -Infinity;
let minColHit: number = Infinity;
let maxRowHit: number = -Infinity;
// start point sand
const startRow: number = 0;
const startCol: number = 500 - delimiter;
matrix[startRow][startCol] = '+';
let last: number[] = [];
for (const line of inputArr) {
    for (let i = 0; i < line.length; i += 1) {
        let [col, row]: number[] = line[i].split(',').map((e: string) => +e);
        maxColHit = Math.max(maxColHit, col - delimiter);
        minColHit = Math.min(minColHit, col - delimiter);
        maxRowHit = Math.max(maxRowHit, row);
        col -= delimiter;
        // console.log('row: ', row, 'col: ', col);
        if (i !== 0) {
            // draw line from last to cur
            const [lastCol, lastRow]: number[] = last;
            // y need to be filled out
            if (lastRow === row) {
                const minCol: number = Math.min(lastCol, col); //?
                const maxCol: number = Math.max(lastCol, col); //?
                for (let curCol = minCol + 1; curCol < maxCol; curCol += 1) {
                    matrix[row][curCol] = '#';
                }
                // x need to be filled out
            } else {
                const minRow: number = Math.min(lastRow, row); //?
                const maxRow: number = Math.max(lastRow, row); //?
                for (let curRow = minRow + 1; curRow < maxRow; curRow += 1) {
                    matrix[curRow][col] = '#';
                }
            }
        }
        matrix[row][col] = '#';
        last = [col, row];
    }
}
// draw floor
matrix[rows - 1] = matrix[rows - 1].map((e) => '#');

function printMatrix(): void {
    for (const row of matrix) {
        console.log(row.join(''));
    }
}

printMatrix();

function moveDown(row: number, col: number): boolean {
    if (outOfBounds(row, col)) return false;
    return matrix[row][col] === '.';
}

function moveDiaLeft(row: number, col: number): boolean {
    if (outOfBounds(row, col)) return false;
    return matrix[row][col] === '.';
}

function moveDiaRight(row: number, col: number): boolean {
    if (outOfBounds(row, col)) {
        return false;
    }
    return matrix[row][col] === '.';
}

function outOfBounds(row: number, col: number): boolean {
    if (row >= rows || col < 0 || col >= cols) return true;
    return false;
}

let fallenSand: number = 0;
let sandPosition: number[] = [startRow, startCol];

let counter: number = 0;
while (true) {
    counter += 1;
    const [row, col]: number[] = sandPosition;
    // console.log('sandPosition: ', sandPosition);

    // row +1
    if (moveDown(row + 1, col)) {
        sandPosition[0] += 1;
        // col -1
    } else if (moveDiaLeft(row + 1, col - 1)) {
        sandPosition[0] += 1;
        sandPosition[1] -= 1;
        // col +1
    } else if (moveDiaRight(row + 1, col + 1)) {
        sandPosition[0] += 1;
        sandPosition[1] += 1;
        // sand comes to rest
    } else {
        if (matrix[startRow][startCol] === 'o') break;
        matrix[row][col] = 'o';
        sandPosition = [startRow, startCol];
        fallenSand += 1;
    }
}

console.log('');
console.log('##########################');
console.log('');

printMatrix();
console.log('fallenSand: ', fallenSand);

console.log('loops through while: ', counter);
