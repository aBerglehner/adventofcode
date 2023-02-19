const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr: number[] = input.split('\r\n').map((num: string) => parseInt(num, 10));

// console.log(inputArr);

const mySet = new Set<number>();

const getInvalidNumber = (searchList: number[], searchSize: number): number => {
    for (let i = 0; i < searchList.length; i += 1) {
        const num: number = searchList[i];
        if (mySet.size === searchSize) {
            let notFound = true;
            for (let it = mySet.values(), val = null; (val = it.next().value); ) {
                if (mySet.has(num - val) && num - val !== val) {
                    notFound = false;
                    break;
                }
            }
            if (notFound) {
                return num;
            }

            const firstSetNum: number = mySet.values().next().value;
            mySet.delete(firstSetNum);
        }
        mySet.add(num);
    }
    return -Infinity;
};

const invalidNumber = getInvalidNumber(inputArr, 25);
console.log('searchedIndex: ', invalidNumber);

let head: number = 0;
let result: number = inputArr[0];

for (let i = 0; i < inputArr.length; i += 1) {
    while (result < invalidNumber) {
        head += 1;
        result += inputArr[head];
    }

    if (result === invalidNumber) {
        console.log('i: ', i, ' | head: ', head);
        const resultList = inputArr.slice(i, head + 1);
        const minResultList = Math.min(...resultList);
        const maxResultList = Math.max(...resultList);
        console.log(
            'minResultList: ',
            minResultList,
            ' | maxResultList: ',
            maxResultList
        );
        console.log('sum: ', minResultList + maxResultList);

        break;
    }
    while (result > invalidNumber) {
        result -= inputArr[head];
        head -= 1;
    }
    result -= inputArr[i];
}
