const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

const crabPosition: number[] = inputArr[0].split(',').map((e: string) => parseInt(e, 10));

// console.log(crabPosition);

const sum: number = crabPosition.reduce((acc, cur) => (acc += cur), 0);
console.log('sum: ', sum);
console.log('crabPosition length: ', crabPosition.length);
console.log('avg: ', sum / crabPosition.length);

let left: number = Math.min(...crabPosition);
let right: number = Math.max(...crabPosition);

const moveCostsMap = new Map<number, number>();

function getCosts(goalPosition: number): number {
    let result: number = 0;
    for (const position of crabPosition) {
        result += Math.abs(position - goalPosition);
    }
    moveCostsMap.set(goalPosition, result);
    return result;
}

while (left < right) {
    const mid = left + Math.floor((right - left) / 2);
    const leftMoves = moveCostsMap.get(left) || getCosts(left);
    const rightMoves = moveCostsMap.get(right) || getCosts(right);
    const midMoves = moveCostsMap.get(mid) || getCosts(mid);
    if (Math.abs(midMoves - leftMoves) < Math.abs(midMoves - rightMoves)) {
        right = mid;
    } else {
        left = mid;
    }
}

const minMoveCost: number = Math.min(...Array.from(moveCostsMap.values())); //?
console.log('moveCostsMap: ', moveCostsMap);
console.log('minMoveCost: ', minMoveCost);
