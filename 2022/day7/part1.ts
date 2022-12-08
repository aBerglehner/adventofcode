const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

const structure = new Map<string, string[]>();
const baseFolderSize: { [key: string]: number } = {};
const curFolder: string[] = [];

for (const command of inputArr) {
    // command either ls oder cd folder or cd ..
    if (command[0] === '$') {
        // cd ..
        if (command[5] === '.') {
            curFolder.pop();
            continue;
        }
        //changed to new folder
        if (command[2] === 'c') {
            const [, , folder] = command.split(' ');
            if (command[5] === '/') {
                curFolder.push(`${folder}.`);
            } else {
                curFolder.push(folder);
            }
            const folderName: string = curFolder.join('/');
            structure.set(folderName, structure.get(folderName) || [folderName]);
            baseFolderSize[folderName] = 0;
        }
        // list folder name
    } else if (command[0] === 'd') {
        const [, folder] = command.split(' ');
        const folderName: string = curFolder.join('/');
        structure.get(folderName)!.push(`${folderName}/${folder}`);
        // list file size in curFolder
    } else {
        const folderName: string = curFolder.join('/');
        const [size] = command.split(' ');
        baseFolderSize[folderName] += +size;
    }
}

const totalSumFolders: { [key: string]: number } = {};

structure.forEach((_, key) => {
    if (!(key in totalSumFolders)) {
        totalSumFolders[key] = 0;
    }
    const queue: string[] = [key];
    const visited = new Set<string>();
    while (queue.length) {
        const folder: string = queue.shift()!;
        const folderArr = structure.get(folder)!;
        for (const subFolder of folderArr) {
            if (!visited.has(subFolder)) {
                visited.add(subFolder);
                queue.push(subFolder);
                totalSumFolders[key] += baseFolderSize[subFolder];
            }
        }
    }
});

const lowerThan: number = 100_001;

let result: number = 0;
for (const key in totalSumFolders) {
    if (totalSumFolders[key] < lowerThan) {
        result += totalSumFolders[key];
    }
}

console.log(result);
