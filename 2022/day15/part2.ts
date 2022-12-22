const fs = require('fs');
export const p = '';

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
// console.log(sensorDistance);

const posLines: number[] = [];
const negLines: number[] = [];

sensorDistance.forEach((distance, [sensorX, sensorY]) => {
    negLines.push(sensorX + sensorY - distance, sensorX + sensorY + distance);
    posLines.push(sensorX - sensorY - distance, sensorX - sensorY + distance);
});

let neg: any = Infinity;
let pos: any = Infinity;

for (let i = 0; i < sensorDistance.size * 2; i += 1) {
    for (let j = i + 1; j < sensorDistance.size * 2; j += 1) {
        const posI: number = posLines[i];
        const posJ: number = posLines[j];

        if (Math.abs(posI - posJ) === 2) {
            pos = Math.min(posI, posJ) + 1;
        }

        const negI: number = negLines[i];
        const negJ: number = negLines[j];
        if (Math.abs(negI - negJ) === 2) {
            neg = Math.min(negI, negJ) + 1;
        }
    }
}

let x: number = Math.floor((pos + neg) / 2);
let y: number = Math.floor((neg - pos) / 2);
console.log('x: ', x, ' y: ', y);

const result: number = x * 4_000_000 + y;
console.log('result: ', result);
