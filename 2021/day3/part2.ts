const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

const rows: number = inputArr.length; //?
const cols: number = inputArr[0].length; //?

const getRating = (ratingNeeded: string): number => {
    let rowList: number[] = Array.from({ length: rows }, (_, i) => i);
    for (let col = 0; col < cols; col += 1) {
        if (rowList.length === 1) break;
        const halfRowsLength: number = rowList.length / 2; //?
        let bitCounter: number = 0;
        const counter: { [key: string]: number[] } = { bitCounter: [], zeroCounter: [] };
        for (const row of rowList) {
            if (inputArr[row][col] === '1') {
                bitCounter += 1;
                counter.bitCounter.push(row);
            } else {
                counter.zeroCounter.push(row);
            }
        }
        // mostCommonBit
        if (ratingNeeded === 'oxygenRating') {
            rowList =
                bitCounter >= halfRowsLength ? counter.bitCounter : counter.zeroCounter;
            // leastCommonBit
        } else {
            rowList =
                bitCounter < halfRowsLength ? counter.bitCounter : counter.zeroCounter;
        }
    }
    return rowList[0];
};

const oxygenRating: number = parseInt(inputArr[getRating('oxygenRating')], 2); //?
const co2Rating: number = parseInt(inputArr[getRating('co2Rating')], 2); //?

const result: number = oxygenRating * co2Rating;
console.log(result);
