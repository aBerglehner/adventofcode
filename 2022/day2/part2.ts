import { getAllJSDocTags } from 'typescript';

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

const getRoundScore = (enemy: string, decision: string): number => {
    const turns: Score = {
        lose: getLoseScore(enemy),
        draw: getDrawScore(enemy),
        win: getWinScore(enemy),
    };
    return turns[decision];
};

const getLoseScore = (enemy: string): number => {
    const getLoseElement: Guide = { Rock: 'Scissors', Scissors: 'Paper', Paper: 'Rock' };
    return getScoreResult(getLoseElement[enemy], 'lose');
};

const getWinScore = (enemy: string): number => {
    const getWinElement: Guide = { Rock: 'Paper', Scissors: 'Rock', Paper: 'Scissors' };
    return getScoreResult(getWinElement[enemy], 'win');
};

const getDrawScore = (enemy: string): number => {
    return getScoreResult(enemy, 'draw');
};

const getScoreResult = (shape: string, result: string): number => {
    const scoreOfShape: Score = { Rock: 1, Paper: 2, Scissors: 3 };
    const scoreOfResult: Score = { win: 6, draw: 3, lose: 0 };
    return scoreOfShape[shape] + scoreOfResult[result];
};

const enemyGuide: Guide = { A: 'Rock', B: 'Paper', C: 'Scissors' };
const decisionGuide: Guide = { X: 'lose', Y: 'draw', Z: 'win' };
let sum: number = 0;

for (const round of inputArr) {
    const [enemy, decision]: string = round.split(' ');
    sum += getRoundScore(enemyGuide[enemy], decisionGuide[decision]);
}

console.log(sum);
