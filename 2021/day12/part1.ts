const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

// console.log(inputArr);

const adjacency: { [key: string]: string[] } = {};

for (const line of inputArr) {
    const [a, b]: string[] = line.split('-');
    // console.log(a, b);
    if (!(a in adjacency)) {
        adjacency[a] = [];
    }
    if (!(b in adjacency)) {
        adjacency[b] = [];
    }
    adjacency[a].push(b);
    adjacency[b].push(a);
}

console.log('adjacency: ', adjacency);

let result: number = 0;
const visited = new Set<string>();

function isSmall(cave: string): boolean {
    return cave.toLowerCase() === cave;
}

function dfs(cave: string): void {
    if (cave === 'end') {
        result += 1;
        return;
    }
    if (isSmall(cave) && visited.has(cave)) return;
    if (isSmall(cave)) visited.add(cave);

    for (const neighbour of adjacency[cave]) {
        if (neighbour === 'start') continue;
        dfs(neighbour);
    }

    if (isSmall(cave)) visited.delete(cave);
}

dfs('start');

console.log('result: ', result);
