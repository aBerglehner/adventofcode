const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');
// console.log(inputArr);

// x = Col | y = Row
let minCol: number = Infinity;
let maxCol: number = -Infinity;

function formatData(str: string): number[] {
    return str.split(',').map((e) => parseInt(e.trim().slice(2)), 10);
}

// format = [col,row], manhattenDis
const sensorDistance = new Map<number[], number>();

// x = Col | y = Row
for (const line of inputArr) {
    const [sensor, beacon]: string[] = line.split(':');
    const [sensorX, sensorY]: number[] = formatData(sensor.slice(10));
    const [beaconX, beaconY] = formatData(beacon.slice(22));
    minCol = Math.min(minCol, sensorX, beaconX);
    maxCol = Math.max(maxCol, sensorX, beaconX);
    const manhattanDis: number =
        Math.abs(sensorX - beaconX) + Math.abs(sensorY - beaconY);

    sensorDistance.set([sensorX, sensorY, beaconX, beaconY], manhattanDis);
}
console.log(sensorDistance);

const minAndMaxMaximizer: number = 2_000_000;
minCol -= minAndMaxMaximizer;
maxCol += minAndMaxMaximizer;

console.log('minCol: ', minCol, ' maxCol: ', maxCol);
let cannotPresent: number = 0;

// const questionRow: number = 10;
const questionRow: number = 2000000;
const visited = new Set<number>();

sensorDistance.forEach((distance, [sensorCol, sensorRow, beaconCol, beaconRow]) => {
    let questionCol: number = minCol;
    const beaconStr: string = `c${beaconCol}r${beaconRow}`;
    while (questionCol <= maxCol) {
        const questionStr: string = `c${questionCol}r${questionRow}`;
        const curDistance =
            Math.abs(sensorCol - questionCol) + Math.abs(sensorRow - questionRow);

        if (
            curDistance <= distance &&
            !visited.has(questionCol) &&
            beaconStr !== questionStr
        ) {
            cannotPresent += 1;
            visited.add(questionCol);
        }
        questionCol += 1;
    }
});

console.log('minCol/maxCol diff: ', Math.abs(minCol - maxCol));

console.log('resultRow: ', cannotPresent);
