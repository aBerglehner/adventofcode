const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');
console.time('time');

// `x${x}y${y}` pre set default position
const visited = new Set<string>(['x0y0']);
//[x,y]
let ropeArr: { [key: string]: number }[] = Array.from({ length: 10 }, () => {
    return { x: 0, y: 0 };
});

const moveHead: { [key: string]: () => void } = {
    R: () => (ropeArr[0]['x'] += 1),
    L: () => (ropeArr[0]['x'] -= 1),
    U: () => (ropeArr[0]['y'] += 1),
    D: () => (ropeArr[0]['y'] -= 1),
};

for (const move of inputArr) {
    let [direction, steps]: [string, number] = move
        .split(' ')
        .map((e: any) => (!isNaN(e) ? +e : e));

    while (steps) {
        for (let i = 0; i < ropeArr.length; i += 1) {
            //move head if first position of arr
            if (i === 0) {
                moveHead[direction]();
                continue;
            }

            // head of cur knot
            const { x: headX, y: headY } = ropeArr[i - 1];
            // cur knot
            let { x: curX, y: curY } = ropeArr[i];
            const diffX: number = Math.abs(headX - curX);
            const diffY: number = Math.abs(headY - curY);

            function setTail(axis: string, input: number, diff: number): number {
                if (diff === 2) {
                    if (axis === 'x') {
                        return Math.floor((headX + curX) / 2);
                    }
                    return Math.floor((headY + curY) / 2);
                    // it is a diagonal move so set it to head where x or y === 1
                } else if (diff === 1) {
                    if (axis === 'x') {
                        return headX;
                    }
                    return headY;
                    // no move
                } else {
                    return input;
                }
            }

            if (diffX === 2 || diffY === 2) {
                ropeArr[i]['x'] = setTail('x', curX, diffX);
                ropeArr[i]['y'] = setTail('y', curY, diffY);
            }
        }

        //set visited for tail (last position of ropeArr)
        let { x: curX, y: curY } = ropeArr[ropeArr.length - 1];
        const curTailPosition: string = `x${curX}y${curY}`;
        visited.add(curTailPosition);
        steps -= 1;
    }
}

// console.log('visited: ', visited);
console.log('head: ', ropeArr);
console.log('visited positions: ', visited.size);
console.timeEnd('time');
