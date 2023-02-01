import { getModeForResolutionAtIndex } from 'typescript';

const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

const bingoNumbers: number[] = inputArr[0].split(',').map((e: string) => parseInt(e, 10)); //?

const matrix3D: number[][][] = [];

let matrix: number[][] = [];

for (let i = 2; i < inputArr.length; i += 1) {
    if (inputArr[i] === '') {
        matrix3D.push([...matrix]);
        matrix.length = 0;
        continue;
    }
    matrix.push(
        inputArr[i]
            .split(' ')
            .filter((e: string) => e !== '')
            .map((e: string) => parseInt(e, 10))
    );
}
matrix3D.push([...matrix]);

console.log(matrix3D);

const getResult = (): number => {
    const isBingoRow = (matrix: number[][], isMarked: Set<number>): boolean => {
        for (let row = 0; row < 5; row += 1) {
            let counter: number = 0;
            for (let col = 0; col < 5; col += 1) {
                const cur: number = matrix[row][col];
                if (!isMarked.has(cur)) break;
                counter += 1;
            }
            if (counter === 5) return true;
        }
        return false;
    };
    const isBingoCol = (matrix: number[][], isMarked: Set<number>): boolean => {
        for (let col = 0; col < 5; col += 1) {
            let counter: number = 0;
            for (let row = 0; row < 5; row += 1) {
                const cur: number = matrix[row][col];
                if (!isMarked.has(cur)) break;
                counter += 1;
            }
            if (counter === 5) return true;
        }
        return false;
    };
    const getUnmarkedNumbers = (matrix: number[][], isMarked: Set<number>): number => {
        let result: number = 0;
        for (let row = 0; row < 5; row += 1) {
            for (let col = 0; col < 5; col += 1) {
                const cur: number = matrix[row][col];
                if (!isMarked.has(cur)) result += cur;
            }
        }
        return result;
    };

    const markedNumbers = new Set<number>();
    const alreadyWon = new Set<number>();
    const boards: number = matrix3D.length;

    for (const bingoNumber of bingoNumbers) {
        markedNumbers.add(bingoNumber);
        for (let i = 0; i < matrix3D.length; i += 1) {
            const matrix: number[][] = matrix3D[i];
            if (
                (!alreadyWon.has(i) && isBingoRow(matrix, markedNumbers)) ||
                isBingoCol(matrix, markedNumbers)
            ) {
                alreadyWon.add(i);
                if (alreadyWon.size === boards) {
                    return bingoNumber * getUnmarkedNumbers(matrix, markedNumbers);
                }
            }
        }
    }
    return -1;
};

const result: number = getResult(); //?

console.log('result: ', result);
