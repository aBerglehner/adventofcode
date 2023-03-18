const fs = require('fs');
export const x = '';
type Position = { [key: string]: number };
// type Cordinates = Set<string>;
type Cordinates = Map<string, number>;
type GetCordinates = {
    position: Position;
    cordinates: Cordinates;
};

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

const [firstWire, secondWire]: string[][] = inputArr.map((line: string) =>
    line.split(',')
);

const getCurData = (
    position: Position,
    cordinates: Cordinates,
    direction: string,
    value: number,
    steps: number
): GetCordinates => {
    if (value === 0) {
        console.log('end cordinates');
        const curCordinates = `x${position.x}y${position.y}`;
        cordinates.set(curCordinates, steps);
        return { position, cordinates };
    }
    const curCordinates = `x${position.x}y${position.y}`;
    const newMap: Cordinates = new Map();
    newMap.set(curCordinates, steps);
    const lookUp: { [key: string]: Position } = {
        U: { y: position['y'] + 1 },
        D: { y: position['y'] - 1 },
        L: { x: position['x'] - 1 },
        R: { x: position['x'] + 1 },
    };

    return getCurData(
        { ...position, ...lookUp[direction] },
        new Map([...cordinates, ...newMap]),
        direction,
        value - 1,
        steps + 1
    );
};

const getCordinates = (acc: GetCordinates, cur: string): GetCordinates => {
    console.log('getCordinates');
    const [direction, value] = [cur.slice(0, 1), +cur.slice(1)];
    const curCordinates = `x${acc.position.x}y${acc.position.y}`;
    const curSteps: number = acc.cordinates.has(curCordinates)
        ? acc.cordinates.get(curCordinates)!
        : 0;
    const { position, cordinates } = getCurData(
        acc.position,
        new Map(),
        direction,
        value,
        curSteps
    );

    acc.position = position;
    acc.cordinates = new Map([...acc.cordinates, ...cordinates]);
    return acc;
};

const wireData = (wireList: string[]) => {
    console.log('wire Data');
    return wireList.reduce(getCordinates, {
        position: { x: 0, y: 0 },
        cordinates: new Map(),
    } as GetCordinates);
};

const { cordinates: firstCordinates } = wireData(firstWire);
const { cordinates: secondCordinates } = wireData(secondWire);

// console.log('firstCordinates: ', firstCordinates);
// console.log('secondCordinates: ', secondCordinates);

const findIntersections = [...firstCordinates]
    .filter(([cordinates]) => cordinates !== 'x0y0')
    .filter(([cordinates]) => secondCordinates.has(cordinates))
    .map(([cordinates, steps]) => {
        return {
            cordinates: cordinates,
            steps: steps + secondCordinates.get(cordinates)!,
        };
    });

console.log('findIntersections: ', findIntersections);

const findClosestDistance = findIntersections
    .map(({ steps }) => steps)
    .sort((a, b) => a - b)[0];
console.log('findClosestDistance: ', findClosestDistance);
