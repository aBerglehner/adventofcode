package main

import (
	"fmt"
	"log"
	"slices"
	"strconv"
	"strings"

	"github.com/alex/adventofcode/go/utils"
)

type Pos struct {
	row int8
	col int8
}
type WalkingQueue struct {
	curPos Pos
	steps  int16
}

func main() {
	lines, err := utils.ReadLines("input.txt")
	if err != nil {
		log.Fatal(err)
	}

	// size := int8(7)
	size := int8(71)
	endPos := size - 1
	matrix := make([][]int, size)
	for i := range matrix {
		matrix[i] = make([]int, size)
	}

	allCorr := []Pos{}
	for _, line := range lines {
		linePos := strings.Split(line, ",")
		row, _ := strconv.Atoi(linePos[1])
		col, _ := strconv.Atoi(linePos[0])
		newPos := Pos{int8(row), int8(col)}
		allCorr = append(allCorr, newPos)

		fmt.Println(line)
	}
	utils.PrintMatrix(matrix)
	fmt.Println("-----")
	fmt.Println("-----")

	rows := int8(len(matrix))
	cols := int8(len(matrix[0]))
	dirs := []Pos{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}

	for curLimit := 1024; curLimit < len(allCorr); curLimit++ {
		fmt.Println("curLimit: ", curLimit-1)
		fmt.Println("corrdinates: ", allCorr[curLimit-1])
		corrupted := make(map[Pos]bool)
		for j := 0; j < curLimit; j++ {
			corrupted[allCorr[j]] = true
		}

		visited := []Pos{}
		pointer := 0
		queue := []WalkingQueue{{Pos{0, 0}, 0}}
		res := []WalkingQueue{}
		var minSteps int16 = 32000
		for pointer < len(queue) {

			cur := queue[pointer]
			// queue = queue[1:]
			pointer++

			if cur.curPos.row == endPos && cur.curPos.col == endPos {
				res = append(res, cur)
			}
			alreadyVisted := slices.Contains(visited, cur.curPos)
			if alreadyVisted {
				continue
			}
			visited = append(visited, Pos{cur.curPos.row, cur.curPos.col})

			for _, dir := range dirs {
				nextRow := cur.curPos.row + dir.row
				if nextRow < 0 || nextRow >= rows {
					continue
				}
				nextCol := cur.curPos.col + dir.col
				if nextCol < 0 || nextCol >= cols {
					continue
				}

				newPos := Pos{nextRow, nextCol}

				// fmt.Println("corrupted: ", corrupted)
				// fmt.Println("queue len: ", len(queue))
				// fmt.Println("pointer: ", pointer)
				// fmt.Println("curPos ", cur.curPos)
				// fmt.Println("newPos ", newPos)
				// fmt.Println("visted before: ", cur.visited)
				// fmt.Println("-------------")

				nextCur := WalkingQueue{newPos, cur.steps + 1}
				if _, exist := corrupted[newPos]; !exist {
					if nextCur.steps < minSteps {
						queue = append(queue, nextCur)
					}
				}

			}
		}
		fmt.Println("res: ", res)
		fmt.Println("res len: ", len(res))
		fmt.Println("min steps: ", minSteps)
		fmt.Println("---------------------------")
		if len(res) == 0 {
			break
		}
	}
}
