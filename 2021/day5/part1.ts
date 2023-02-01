const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

const diagramMap = new Map<string, number>();

for (const coordinates of inputArr) {
    const [x, y]: [string, string] = coordinates.split('->').map((e: string) => e.trim());
    const [x1, y1]: number[] = x.split(',').map((e: string) => parseInt(e, 10));
    const [x2, y2]: number[] = y.split(',').map((e: string) => parseInt(e, 10));

    if (x1 === x2) {
        // console.log('1: ', x1, y1);
        // console.log('2: ', x2, y2);

        if (y1 < y2) {
            for (let newY1 = y1; newY1 <= y2; newY1 += 1) {
                const cur: string = `x${x1}y${newY1}`;
                diagramMap.set(cur, (diagramMap.get(cur) || 0) + 1);
            }
        } else {
            for (let newY2 = y2; newY2 <= y1; newY2 += 1) {
                const cur: string = `x${x1}y${newY2}`;
                diagramMap.set(cur, (diagramMap.get(cur) || 0) + 1);
            }
        }
    } else if (y1 === y2) {
        if (x1 < x2) {
            for (let newX1 = x1; newX1 <= x2; newX1 += 1) {
                const cur: string = `x${newX1}y${y1}`;
                diagramMap.set(cur, (diagramMap.get(cur) || 0) + 1);
            }
        } else {
            for (let newX2 = x2; newX2 <= x1; newX2 += 1) {
                const cur: string = `x${newX2}y${y1}`;
                diagramMap.set(cur, (diagramMap.get(cur) || 0) + 1);
            }
        }
    }
}

// console.log('diagramMap: ', diagramMap);
let result: number = 0;
diagramMap.forEach((val, key) => {
    if (val >= 2) result += 1;
});

console.log('result: ', result);
