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
// const visited = new Set<string>();
const visited: { [key: string]: number } = {};

function isSmall(cave: string): boolean {
    return cave.toLowerCase() === cave;
}

function dfs(cave: string): void {
    if (cave === 'end') {
        result += 1;
        return;
    }
    if (isSmall(cave)) {
        if (!(cave in visited)) {
            visited[cave] = 0;
        }

        visited[cave] += 1;
        // check how many small caves are visited more than once
        let moreThanOnce: number = 0;
        for (const small in visited) {
            if (visited[small] > 1) moreThanOnce += 1;

            if (visited[small] > 2) {
                visited[cave] -= 1;
                return;
            }
        }
        if (moreThanOnce > 1) {
            visited[cave] -= 1;
            return;
        }
    }

    for (const neighbour of adjacency[cave]) {
        if (neighbour === 'start') continue;
        dfs(neighbour);
    }

    if (isSmall(cave)) {
        visited[cave] -= 1;
    }
}

dfs('start');

console.log('result: ', result);
