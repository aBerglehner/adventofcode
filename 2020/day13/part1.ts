const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

const depart: number = +inputArr[0];
console.log('depart: ', depart);

const busIdList: number[] = inputArr[1]
    .split(',')
    .filter((e: string) => e !== 'x')
    .map(Number);

console.log('busIdList: ', busIdList);

let cur = depart;

while (true) {
    const isCurBusId = busIdList.find((id) => cur % id === 0);
    if (isCurBusId) {
        console.log('cur: ', cur);
        console.log('isCurBusId: ', isCurBusId);
        const departTime = cur - depart;
        console.log('departTime: ', departTime);
        console.log('result: ', isCurBusId * departTime);
        break;
    }
    cur += 1;
}
