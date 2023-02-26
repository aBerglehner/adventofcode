const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

type DataType = {
    [key: string]: any;
    north: number;
    south: number;
    east: number;
    west: number;
    waypointSN: string;
    waypointSNValue: number;
    waypointEW: string;
    waypointEWValue: number;
};

const data: DataType = {
    north: 0,
    south: 0,
    east: 0,
    west: 0,
    waypointSN: 'north',
    waypointSNValue: 1,
    waypointEW: 'east',
    waypointEWValue: 10,
};

const getNewWayPoint = (
    rotation: string,
    degrees: number,
    southNorth: string,
    southNorthValue: number,
    eastWest: string,
    eastWestValue: number
): {
    southNorth: string;
    southNorthValue: number;
    eastWest: string;
    eastWestValue: number;
} => {
    if (rotation === 'R') {
        while (degrees > 0) {
            if (southNorth === 'north' && eastWest === 'east') {
                southNorth = 'south';
            } else if (eastWest === 'east' && southNorth === 'south') {
                eastWest = 'west';
            } else if (southNorth === 'south' && eastWest === 'west') {
                southNorth = 'north';
            } else if (eastWest === 'west' && southNorth === 'north') {
                eastWest = 'east';
            }
            [eastWestValue, southNorthValue] = [southNorthValue, eastWestValue];
            degrees -= 90;
        }
    } else {
        while (degrees > 0) {
            if (southNorth === 'north' && eastWest === 'east') {
                eastWest = 'west';
            } else if (eastWest === 'east' && southNorth === 'south') {
                southNorth = 'north';
            } else if (southNorth === 'south' && eastWest === 'west') {
                eastWest = 'east';
            } else if (eastWest === 'west' && southNorth === 'north') {
                southNorth = 'south';
            }
            [eastWestValue, southNorthValue] = [southNorthValue, eastWestValue];
            degrees -= 90;
        }
    }
    return { southNorth, southNorthValue, eastWest, eastWestValue };
};

for (const input of inputArr) {
    const action: string = input.slice(0, 1);
    const value: number = +input.slice(1);
    if (action === 'F') {
        if (data.waypointSN === 'south') {
            const acc = data.waypointSNValue * value;
            data.south += acc;
        } else {
            const acc = data.waypointSNValue * value;
            data.north += acc;
        }
        if (data.waypointEW === 'east') {
            const acc = data.waypointEWValue * value;
            data.east += acc;
        } else {
            const acc = data.waypointEWValue * value;
            data.west += acc;
        }
    } else if (action === 'L' || action === 'R') {
        const { southNorth, southNorthValue, eastWest, eastWestValue } = getNewWayPoint(
            action,
            value,
            data.waypointSN,
            data.waypointSNValue,
            data.waypointEW,
            data.waypointEWValue
        );
        data.waypointSN = southNorth;
        data.waypointSNValue = southNorthValue;
        data.waypointEW = eastWest;
        data.waypointEWValue = eastWestValue;
    } else {
        if (action === 'N') {
            if (data.waypointSN === 'north') {
                data.waypointSNValue += value;
            } else {
                const newValue = data.waypointSNValue - value;
                if (newValue < 0) {
                    data.waypointSN = 'south';
                }
                data.waypointSNValue = Math.abs(newValue);
            }
        }
        if (action === 'S') {
            if (data.waypointSN === 'south') {
                data.waypointSNValue += value;
            } else {
                const newValue = data.waypointSNValue - value;
                if (newValue < 0) {
                    data.waypointSN = 'north';
                }
                data.waypointSNValue = Math.abs(newValue);
            }
        }
        if (action === 'E') {
            if (data.waypointEW === 'east') {
                data.waypointEWValue += value;
            } else {
                const newValue = data.waypointEWValue - value;
                if (newValue < 0) {
                    data.waypointSN = 'west';
                }
                data.waypointEWValue = Math.abs(newValue);
            }
        }
        if (action === 'W') {
            if (data.waypointEW === 'west') {
                data.waypointEWValue += value;
            } else {
                const newValue = data.waypointEWValue - value;
                if (newValue < 0) {
                    data.waypointEW = 'east';
                }
                data.waypointEWValue = Math.abs(newValue);
            }
        }
    }
}

console.log('data: ', data);

const absEastWest = Math.abs(data.east - data.west);
const absSouthNorth = Math.abs(data.south - data.north);

console.log('result: ', absEastWest + absSouthNorth);
