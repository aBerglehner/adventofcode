const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

interface Monkey {
    inspects: number;
    startingItems?: number[];
    operation?: { [key: string]: string };
    test?: { [key: string]: number };
}
interface Monkeys {
    [key: string]: Monkey;
}
// object monkeys setup
const monkeys: Monkeys = {};
let counter: number = 0;
let curMonkey: number = 0;
for (let i = 0; i < inputArr.length; i += 1) {
    let curLine: string = inputArr[i].trim();
    if (curLine.length === 0) {
        continue;
    }
    if (counter === 6) {
        counter = 0;
    }

    if (counter === 0) {
        curMonkey = +curLine.split(' ')[1][0];
        monkeys[curMonkey] = { inspects: 0 };
    }
    if (counter >= 1) {
        // startingItems
        let attribute: string = curLine.split(':')[1].trim();
        if (counter === 1) {
            monkeys[curMonkey]['startingItems'] = attribute
                .split(',')
                .map((e) => +e.trim());
        }
        // operation
        if (counter === 2) {
            attribute = attribute.split('=')[1].trim();
            const [first, operator, last] = attribute.split(' ');
            monkeys[curMonkey]['operation'] = { first, operator, last };
        }
        // test divisible by X
        if (counter === 3) {
            attribute = attribute.split(' ')[2];
            monkeys[curMonkey]['test'] = { asset: +attribute };
        }
        // test true case
        if (counter === 4) {
            attribute = attribute.split(' ')[3];
            monkeys[curMonkey]['test'] = {
                ...monkeys[curMonkey]['test'],
                true: +attribute,
            };
        }
        // test false case
        if (counter === 5) {
            attribute = attribute.split(' ')[3];
            monkeys[curMonkey]['test'] = {
                ...monkeys[curMonkey]['test'],
                false: +attribute,
            };
        }
    }

    counter += 1;
}

const getOperation = (cur: number, operator: string, last: string): number => {
    const lastNum = last === 'old' ? cur : +last;
    if (operator === '*') return cur * lastNum;
    if (operator === '/') return cur / lastNum;
    if (operator === '+') return cur + lastNum;
    return cur - lastNum;
};

let rounds: number = 20;

while (rounds) {
    for (const monkey in monkeys) {
        const cur = monkeys[monkey];
        while (cur.startingItems?.length) {
            cur.inspects += 1;
            const curItem = cur.startingItems?.shift();
            let value = Math.floor(
                getOperation(curItem!, cur.operation!.operator, cur.operation!.last) / 3
            );

            // test === true
            if (value % cur.test!.asset === 0) {
                monkeys[cur.test!.true].startingItems?.push(value);
                // test === false
            } else {
                monkeys[cur.test!.false].startingItems?.push(value);
            }
        }
    }
    rounds -= 1;
}

console.log(monkeys);

const inspectsSorted: number[] = [];
for (const monkey in monkeys) {
    inspectsSorted.push(monkeys[monkey].inspects);
    inspectsSorted.sort((a, b) => b - a);
}

console.log('inspectsSorted: ', inspectsSorted);
console.log('result: ', inspectsSorted[0] * inspectsSorted[1]);
