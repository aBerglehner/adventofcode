const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: string[][] = input.split('\r\n').map((e: string) => e.split(''));

const printMatrix = (arr: string[][]): void => {
    for (const row of arr) {
        console.log(row.join(''));
    }
    console.log('--------------------');
};

printMatrix(inputArr);

let matrix = [...inputArr];
let rows = matrix.length;
let cols = matrix[0].length;

const scanUp = (matrix: string[][], row: number, col: number): boolean => {
    while (row - 1 >= 0 && matrix[row - 1][col] !== 'L') {
        if (matrix[row - 1][col] === '#') return true;
        row -= 1;
    }
    return false;
};
const scanDown = (matrix: string[][], row: number, col: number): boolean => {
    while (row + 1 < rows && matrix[row + 1][col] !== 'L') {
        if (matrix[row + 1][col] === '#') return true;
        row += 1;
    }
    return false;
};
const scanLeft = (matrix: string[][], row: number, col: number): boolean => {
    while (col - 1 >= 0 && matrix[row][col - 1] !== 'L') {
        if (matrix[row][col - 1] === '#') return true;
        col -= 1;
    }
    return false;
};
const scanRight = (matrix: string[][], row: number, col: number): boolean => {
    while (col + 1 < cols && matrix[row][col + 1] !== 'L') {
        if (matrix[row][col + 1] === '#') return true;
        col += 1;
    }
    return false;
};
const scanUpLeft = (matrix: string[][], row: number, col: number): boolean => {
    while (row - 1 >= 0 && col - 1 >= 0 && matrix[row - 1][col - 1] !== 'L') {
        if (matrix[row - 1][col - 1] === '#') return true;
        row -= 1;
        col -= 1;
    }
    return false;
};
const scanUpRight = (matrix: string[][], row: number, col: number): boolean => {
    while (row - 1 >= 0 && col + 1 < cols && matrix[row - 1][col + 1] !== 'L') {
        if (matrix[row - 1][col + 1] === '#') return true;
        row -= 1;
        col += 1;
    }
    return false;
};
const scanDownLeft = (matrix: string[][], row: number, col: number): boolean => {
    while (row + 1 < rows && col - 1 >= 0 && matrix[row + 1][col - 1] !== 'L') {
        if (matrix[row + 1][col - 1] === '#') return true;
        row += 1;
        col -= 1;
    }
    return false;
};
const scanDownRight = (matrix: string[][], row: number, col: number): boolean => {
    while (row + 1 < rows && col + 1 < cols && matrix[row + 1][col + 1] !== 'L') {
        if (matrix[row + 1][col + 1] === '#') return true;
        row += 1;
        col += 1;
    }
    return false;
};

const noOccupiedSeats = (matrix: string[][], row: number, col: number): boolean => {
    //up
    if (scanUp(matrix, row, col)) return false;
    //down
    if (scanDown(matrix, row, col)) return false;
    //left
    if (scanLeft(matrix, row, col)) return false;
    //right
    if (scanRight(matrix, row, col)) return false;
    //up left
    if (scanUpLeft(matrix, row, col)) return false;
    //up right
    if (scanUpRight(matrix, row, col)) return false;
    //down left
    if (scanDownLeft(matrix, row, col)) return false;
    //down right
    if (scanDownRight(matrix, row, col)) return false;
    return true;
};

const fourAdjacentSeats = (matrix: string[][], row: number, col: number): boolean => {
    let occupiedSeats = 0;
    if (scanUp(matrix, row, col)) occupiedSeats += 1;
    //down
    if (scanDown(matrix, row, col)) occupiedSeats += 1;
    //left
    if (scanLeft(matrix, row, col)) occupiedSeats += 1;
    //right
    if (scanRight(matrix, row, col)) occupiedSeats += 1;
    //up left
    if (scanUpLeft(matrix, row, col)) occupiedSeats += 1;
    //up right
    if (scanUpRight(matrix, row, col)) occupiedSeats += 1;
    //down left
    if (scanDownLeft(matrix, row, col)) occupiedSeats += 1;
    //down right
    if (scanDownRight(matrix, row, col)) occupiedSeats += 1;
    return occupiedSeats >= 5;
};

let changes = 0;
let newRound = true;
let occupiedSeats = 0;

while (newRound) {
    occupiedSeats = 0;
    changes = 0;
    const newMatrix = Array.from({ length: rows }, () => new Array(cols).fill(''));

    for (let row = 0; row < rows; row += 1) {
        for (let col = 0; col < cols; col += 1) {
            const cur: string = matrix[row][col];
            if (cur === 'L') {
                if (noOccupiedSeats(matrix, row, col)) {
                    newMatrix[row][col] = '#';
                    occupiedSeats += 1;
                    changes += 1;
                } else {
                    newMatrix[row][col] = 'L';
                }
            } else if (cur === '#') {
                if (fourAdjacentSeats(matrix, row, col)) {
                    newMatrix[row][col] = 'L';
                    changes += 1;
                } else {
                    newMatrix[row][col] = '#';
                    occupiedSeats += 1;
                }
            } else {
                newMatrix[row][col] = '.';
            }
        }
    }

    matrix = newMatrix;
    // printMatrix(matrix);
    if (changes === 0) newRound = false;
}
// printMatrix(matrix);

console.log('occupiedSeats: ', occupiedSeats);
