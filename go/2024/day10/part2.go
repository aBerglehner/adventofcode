package main

import (
	"fmt"
	"log"
	"strconv"
	"strings"

	"github.com/alex/adventofcode/go/utils"
)

func main() {
	lines, err := utils.ReadLines("input.txt")
	if err != nil {
		log.Fatal(err)
	}
	type Pos struct {
		row int
		col int
	}

	matrix := [][]int{}
	for _, line := range lines {
		fmt.Println(line)
		row := strings.Split(line, "")
		intRow := []int{}
		for _, v := range row {
			cur, err := strconv.Atoi(v)
			if err == nil {
				intRow = append(intRow, cur)
			}
		}
		matrix = append(matrix, intRow)
	}
	utils.PrintMatrix(matrix)

	startPos := []Pos{}
	for rowPos, row := range matrix {
		for colPos, v := range row {
			if v == 0 {
				startPos = append(startPos, Pos{rowPos, colPos})
			}
		}
	}
	fmt.Println("startPos: ", startPos)

	dirs := []Pos{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}
	rows := len(matrix)
	cols := len(matrix[0])
	res := []int{}

	for _, curPos := range startPos {
		queue := []Pos{curPos}
		queuePos := 0
		count := 0
		for len(queue) > queuePos {
			cur := queue[queuePos]
			queuePos++
			for _, dir := range dirs {
				nextRow := cur.row + dir.row
				if nextRow < 0 || nextRow >= rows {
					continue
				}
				nextCol := cur.col + dir.col
				if nextCol < 0 || nextCol >= cols {
					continue
				}
				curVal := matrix[cur.row][cur.col]
				nextVal := matrix[nextRow][nextCol]
				newPos := Pos{nextRow, nextCol}
				if curVal == 8 && nextVal == 9 {
					count++
				} else if nextVal-curVal == 1 {
					queue = append(queue, newPos)
				}
			}
		}
		res = append(res, count)
	}
	fmt.Println("-----")
	fmt.Println("res: ", res)
	fmt.Println("-----")
	sum := 0
	for _, v := range res {
		sum += v
	}
	fmt.Println(sum)
}
