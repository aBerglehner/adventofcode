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

// printMatrix(inputArr);

const noOccupiedSeats = (matrix: string[][], row: number, col: number): boolean => {
    //up
    if (row - 1 >= 0 && matrix[row - 1][col] === '#') return false;
    //down
    if (row + 1 < rows && matrix[row + 1][col] === '#') return false;
    //left
    if (col - 1 >= 0 && matrix[row][col - 1] === '#') return false;
    //right
    if (col + 1 < cols && matrix[row][col + 1] === '#') return false;
    //up left
    if (row - 1 >= 0 && col - 1 >= 0 && matrix[row - 1][col - 1] === '#') return false;
    //up right
    if (row - 1 >= 0 && col + 1 < cols && matrix[row - 1][col + 1] === '#') return false;
    //down left
    if (row + 1 < rows && col - 1 >= 0 && matrix[row + 1][col - 1] === '#') return false;
    //down right
    if (row + 1 < rows && col + 1 < cols && matrix[row + 1][col + 1] === '#')
        return false;
    return true;
};

const fourAdjacentSeats = (matrix: string[][], row: number, col: number): boolean => {
    let occupiedSeats = 0;
    //up
    if (row - 1 >= 0 && matrix[row - 1][col] === '#') occupiedSeats += 1;
    //down
    if (row + 1 < rows && matrix[row + 1][col] === '#') occupiedSeats += 1;
    //left
    if (col - 1 >= 0 && matrix[row][col - 1] === '#') occupiedSeats += 1;
    //right
    if (col + 1 < cols && matrix[row][col + 1] === '#') occupiedSeats += 1;
    //up left
    if (row - 1 >= 0 && col - 1 >= 0 && matrix[row - 1][col - 1] === '#')
        occupiedSeats += 1;
    //up right
    if (row - 1 >= 0 && col + 1 < cols && matrix[row - 1][col + 1] === '#')
        occupiedSeats += 1;
    //down left
    if (row + 1 < rows && col - 1 >= 0 && matrix[row + 1][col - 1] === '#')
        occupiedSeats += 1;
    //down right
    if (row + 1 < rows && col + 1 < cols && matrix[row + 1][col + 1] === '#')
        occupiedSeats += 1;
    return occupiedSeats >= 4;
};

let changes = 0;
let newRound = true;
let occupiedSeats = 0;

let matrix = [...inputArr];
let rows = matrix.length;
let cols = matrix[0].length;

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
    if (changes === 0) newRound = false;
}
// printMatrix(matrix);

console.log('occupiedSeats: ', occupiedSeats);
