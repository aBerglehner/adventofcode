const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// `x${x}y${y}`
const visited = new Set<string>(['x0y0']);
//[x,y]
let head: { [key: string]: number } = { x: 0, y: 0 };
let tail: { [key: string]: number } = { x: 0, y: 0 };
const moveHead: { [key: string]: () => void } = {
    R: () => (head['x'] += 1),
    L: () => (head['x'] -= 1),
    U: () => (head['y'] += 1),
    D: () => (head['y'] -= 1),
};

for (const move of inputArr) {
    let [direction, steps]: [string, number] = move
        .split(' ')
        .map((e: any) => (!isNaN(e) ? +e : e));

    while (steps) {
        //move head
        moveHead[direction]();

        const { x: headX, y: headY } = head;
        let { x: tailX, y: tailY } = tail;
        const diffX: number = Math.abs(headX - tailX);
        const diffY: number = Math.abs(headY - tailY);

        if (diffX === 2 || diffY === 2) {
            const twoDiff: string = diffX === 2 ? 'x' : 'y';
            const oneDiff: boolean = diffX === 1 || diffY === 1 ? true : false;

            function setTail(axis: string, input: number): number {
                //tail diff === 2 move it 1 step closer to head
                if (axis === twoDiff) {
                    if (twoDiff === 'x') {
                        return (headX + tailX) / 2;
                    }
                    return (headY + tailY) / 2;
                    // it is a diagonal move so set it to head where x or y === 1
                } else if (oneDiff) {
                    if (twoDiff === 'y') {
                        return headX;
                    }
                    return headY;
                    // no move
                } else {
                    return input;
                }
            }
            tailX = setTail('x', tailX);
            tailY = setTail('y', tailY);
            tail['x'] = tailX;
            tail['y'] = tailY;
            //set visited for tail
            const curTailPosition: string = `x${tailX}y${tailY}`;
            visited.add(curTailPosition);
        }

        steps -= 1;
    }
}

console.log('visited: ', visited);
console.log('head: ', head);
console.log('tail: ', tail);
console.log('visited positions: ', visited.size);
