import { isWhiteSpaceLike } from 'typescript';

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
    } else {
        // console.log('1: ', x1, y1);
        // console.log('2: ', x2, y2);
        // console.log('------------');

        // increment x1/y1 or x2/y2
        if ((x1 < x2 && y1 < y2) || (x1 > x2 && y1 > y2)) {
            let minX = Math.min(x1, x2);
            let minY = Math.min(y1, y2);
            let maxX = Math.max(x1, x2);
            let maxY = Math.max(y1, y2);
            while (minX <= maxX && minY <= maxY) {
                const cur: string = `x${minX}y${minY}`;
                diagramMap.set(cur, (diagramMap.get(cur) || 0) + 1);
                minX += 1;
                minY += 1;
            }
        } else {
            // decrease x1 and increase y1
            if (x1 > x2) {
                let newX1 = x1;
                let newY1 = y1;
                while (newX1 >= x2) {
                    const cur: string = `x${newX1}y${newY1}`;
                    diagramMap.set(cur, (diagramMap.get(cur) || 0) + 1);
                    newX1 -= 1;
                    newY1 += 1;
                }
                // decrease x2 and increase y2
            } else {
                let newX2 = x2;
                let newY2 = y2;
                while (newX2 >= x1) {
                    const cur: string = `x${newX2}y${newY2}`;
                    diagramMap.set(cur, (diagramMap.get(cur) || 0) + 1);
                    newX2 -= 1;
                    newY2 += 1;
                }
            }
        }
    }
}

diagramMap.forEach((val, key) => {
    if (val < 2) diagramMap.delete(key);
});

// console.log('diagramMap: ', diagramMap);
console.log('result: ', diagramMap.size);
