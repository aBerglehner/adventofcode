const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n')[0];

const packetSize: number = 14;
const packet = new Map<string, number>(); //?
for (let i = 0; i < packetSize; i += 1) {
    const cur: string = inputArr[i];
    packet.set(cur, (packet.get(cur) || 0) + 1);
}

const getMarker = (): number => {
    let left: number = 0;
    let right: number = packetSize;
    while (right < inputArr.length) {
        const leftStr: string = inputArr[left];
        const rightStr: string = inputArr[right];
        if (packet.size === packetSize) return right;

        //del
        packet.set(leftStr, packet.get(leftStr)! - 1);
        if (packet.get(leftStr) === 0) packet.delete(leftStr);
        //add
        packet.set(rightStr, (packet.get(rightStr) || 0) + 1);

        left += 1;
        right += 1;
    }
    return right;
};

console.log(getMarker());
