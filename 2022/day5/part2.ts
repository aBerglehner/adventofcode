const fs = require('fs');
export const x = '';

//real input
const input = fs.readFileSync('./input.txt', 'utf8');

//sample
// const input = fs.readFileSync('./sample.txt', 'utf8');

const inputArr = input.split('\r\n');

const stacks: { [key: string]: string[] } = {
    stack1: 'FCJPHTW'.split(''),
    stack2: 'GRVFZJBH'.split(''),
    stack3: 'HPTR'.split(''),
    stack4: 'ZSNPHT'.split(''),
    stack5: 'NVFZHJCD'.split(''),
    stack6: 'PMGFWDZ'.split(''),
    stack7: 'MVZWSJDP'.split(''),
    stack8: 'NDS'.split(''),
    stack9: 'DZSFM'.split(''),
};

// const stacks: { [key: string]: string[] } = {
//     stack1: 'ZN'.split(''),
//     stack2: 'MCD'.split(''),
//     stack3: 'P'.split(''),
// };

for (const move of inputArr) {
    let [, count, , moveFrom, , moveTo]: number[] = move
        .split(' ')
        .map((e: any) => (isNaN(e) ? e : +e));
    const saver: string[] = [];
    while (count) {
        if (`stack${moveFrom}` in stacks && stacks[`stack${moveFrom}`]) {
            saver.unshift(stacks[`stack${moveFrom}`].pop()!);
        }
        count -= 1;
    }

    stacks[`stack${moveTo}`].push(...saver);
}

stacks; //?
const result: string[] = [];
for (const key in stacks) {
    if (stacks[key].length) {
        result.push(stacks[key].pop()!);
    }
}
console.log(result.join(''));
