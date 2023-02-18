const fs = require('fs').promises;
export const x = '';

const readLines = async () => {
    const data = await fs.readFile('./input.txt', { encoding: 'utf-8' });
    return data.split('\r\n');
};

const solve = async () => {
    const lines: string[] = await readLines();
    const instructions: {
        op: string;
        val: number;
    }[] = lines.map(parseLine);
    const start = Date.now();
    const result: number = lastAcc(instructions);
    const end = Date.now();
    console.log(`finished in ${end - start}ms.`);
    return result;
};

const parseLine = (line: string) => {
    const [op, val] = line.split(' ');
    return { op, val: Number(val) };
};

const lastAcc = (
    instructions: {
        op: string;
        val: number;
    }[],
    i = 0,
    visited = new Set(),
    flipAvailable = true
): number => {
    if (i === instructions.length) return 0;

    if (i < 0 || i > instructions.length || visited.has(i)) return -Infinity;

    visited.add(i);

    const { op, val } = instructions[i];
    const newVisited = new Set(visited);

    if (op === 'jmp') {
        const paths = [lastAcc(instructions, i + val, newVisited, flipAvailable)];
        if (flipAvailable) paths.push(lastAcc(instructions, i + 1, newVisited, false));
        return Math.max(...paths);
    } else if (op === 'nop') {
        const paths = [lastAcc(instructions, i + 1, newVisited, flipAvailable)];
        if (flipAvailable) paths.push(lastAcc(instructions, i + val, newVisited, false));
        return Math.max(...paths);
    } else {
        return val + lastAcc(instructions, i + 1, newVisited, flipAvailable);
    }
};

solve().then(console.log);
