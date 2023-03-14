const fs = require('fs');
export const x = '';
type Position = { [key: string]: number };
type Cordinates = Set<string>;
type GetCordinates = {
    position: Position;
    cordinates: Cordinates;
};

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

const [firstWire, secondWire]: string[][] = inputArr.map((line: string) =>
    line.split(',')
);
// console.log('firstWire: ', firstWire);
// console.log('secondWire: ', secondWire);

const getCurData = (
    position: Position,
    cordinates: Cordinates,
    direction: string,
    value: number
): GetCordinates => {
    if (value === 0) {
        console.log('end cordinates');
        return { position, cordinates };
    }
    const curCordinates = `x${position.x}y${position.y}`;
    const lookUp: { [key: string]: Position } = {
        U: { y: position['y'] + 1 },
        D: { y: position['y'] - 1 },
        L: { x: position['x'] - 1 },
        R: { x: position['x'] + 1 },
    };

    return getCurData(
        { ...position, ...lookUp[direction] },
        new Set([...cordinates, curCordinates]),
        direction,
        value - 1
    );
};

const getCordinates = (acc: GetCordinates, cur: string): GetCordinates => {
    console.log('getCordinates');
    const [direction, value] = [cur.slice(0, 1), +cur.slice(1)];
    const { position, cordinates } = getCurData(
        acc.position,
        new Set(),
        direction,
        value
    );

    // don't give it your acc.cordinates otherwise the linar spread escalate
    // const { position, cordinates } = getCurData(
    //     acc.position,
    //     // acc.cordinates,
    //     direction,
    //     value
    // );
    acc.position = position;
    acc.cordinates = new Set([...acc.cordinates, ...cordinates]);
    return acc;
};

const wireData = (wireList: string[]) => {
    console.log('wire Data');
    return wireList.reduce(getCordinates, {
        position: { x: 0, y: 0 },
        cordinates: new Set(),
    } as GetCordinates);
};

const { cordinates: firstCordinates } = wireData(firstWire);
const { cordinates: secondCordinates } = wireData(secondWire);

// console.log('cordinates: ', firstCordinates);
// console.log('secondCordinates: ', secondCordinates);

const findIntersections = [...firstCordinates]
    .filter((cor: string) => cor !== 'x0y0')
    .filter((cor: string) => secondCordinates.has(cor));

console.log('findIntersections: ', findIntersections);

const findClosestDistance = findIntersections.reduce((acc, cur) => {
    const [x, y] = cur
        .split('y')
        .map((e, i) => (i === 0 ? e.slice(1) : e))
        .map(Number);

    const disctance = Math.abs(x) + Math.abs(y);

    return disctance < acc ? disctance : acc;
}, Infinity);
console.log('findClosestDistance: ', findClosestDistance);
