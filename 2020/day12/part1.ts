const fs = require('fs');
export const x = '';

type DataType = {
    [key: string]: any;
    north: number;
    south: number;
    east: number;
    west: number;
};

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

const getNewFacing = (rotation: string, degrees: number, facing: string): string => {
    if (rotation === 'L') {
        while (degrees > 0) {
            if (facing === 'east') {
                facing = 'north';
            } else if (facing === 'north') {
                facing = 'west';
            } else if (facing === 'west') {
                facing = 'south';
            } else if (facing === 'south') {
                facing = 'east';
            }
            degrees -= 90;
        }
    } else {
        while (degrees > 0) {
            if (facing === 'east') {
                facing = 'south';
            } else if (facing === 'south') {
                facing = 'west';
            } else if (facing === 'west') {
                facing = 'north';
            } else if (facing === 'north') {
                facing = 'east';
            }
            degrees -= 90;
        }
    }
    return facing;
};

const data: DataType = {
    facing: 'east',
    north: 0,
    south: 0,
    east: 0,
    west: 0,
};

for (const input of inputArr) {
    const action: string = input.slice(0, 1);
    const value: number = +input.slice(1);
    if (action === 'F') {
        data[data.facing] += value;
    } else if (action === 'L' || action === 'R') {
        data.facing = getNewFacing(action, value, data.facing);
    } else {
        if (action === 'N') {
            data['north'] += value;
        }
        if (action === 'S') {
            data['south'] += value;
        }
        if (action === 'E') {
            data['east'] += value;
        }
        if (action === 'W') {
            data['west'] += value;
        }
    }
}

console.log('data: ', data);

const absEastWest = Math.abs(data.east - data.west);
const absSouthNorth = Math.abs(data.south - data.north);

console.log('result: ', absEastWest + absSouthNorth);
