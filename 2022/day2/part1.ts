const fs = require('fs');
export const x = '';

const input = fs.readFileSync('./input.txt', 'utf8');

const inputArr = input.split('\r\n');

interface Guide {
    [key: string]: string;
}
interface Score {
    [key: string]: number;
}

const getScoreResult = (shape: string, result: string): number => {
    const scoreOfShape: Score = { Rock: 1, Paper: 2, Scissors: 3 };
    const scoreOfResult: Score = { win: 6, draw: 3, lose: 0 };
    return scoreOfShape[shape] + scoreOfResult[result];
};

const getRoundScore = (enemy: string, own: string): number => {
    if (enemy === own) return getScoreResult(own, 'draw');
    if (
        (own === 'Rock' && enemy === 'Scissors') ||
        (own === 'Scissors' && enemy === 'Paper') ||
        (own === 'Paper' && enemy === 'Rock')
    ) {
        return getScoreResult(own, 'win');
    }
    return getScoreResult(own, 'lose');
};

const enemyGuide: Guide = { A: 'Rock', B: 'Paper', C: 'Scissors' };
const ownGuide: Guide = { X: 'Rock', Y: 'Paper', Z: 'Scissors' };
let sum: number = 0;

for (const round of inputArr) {
    const [enemy, own]: string = round.split(' ');
    sum += getRoundScore(enemyGuide[enemy], ownGuide[own]);
}

console.log(sum);
