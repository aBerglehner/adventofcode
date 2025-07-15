package main

import (
	"fmt"
	"log"
	"strings"

	"github.com/alex/adventofcode/go/utils"
)

func main() {
	lines, err := utils.ReadLines("test_input.txt")
	if err != nil {
		log.Fatal(err)
	}

	matrix := [][]string{}
	for _, line := range lines {
		fmt.Println(line)
		row := strings.Split(line, "")
		matrix = append(matrix, row)
	}
	type Pos struct {
		row int
		col int
	}

	posMap := make(map[string][]Pos)
	utils.PrintMatrix(matrix)
	for rowPos, row := range matrix {
		for colPos, col := range row {
			if col == "." {
				continue
			}
			_, exit := posMap[col]
			// fmt.Println(cur)
			if !exit {
				posMap[col] = []Pos{}
			}
			posMap[col] = append(posMap[col], Pos{rowPos, colPos})
		}
	}
	fmt.Println("-------")
	fmt.Println("posMap: ", posMap)
	count := 0
	for _, value := range posMap {
		for start, curPos := range value {
			for i := start + 1; i < len(value); i++ {
				laterOne := value[i]
				rowPosNew := laterOne.row - curPos.row
				colPosNew := laterOne.col - curPos.col
				posDiff := Pos{rowPosNew, colPosNew}
				fmt.Println("posDiff: ", posDiff)
				rowPosUp := curPos.row - posDiff.row
				colPosUp := curPos.col - posDiff.col
				posUp := Pos{rowPosUp, colPosUp}
				fmt.Println("posUp: ", posUp)
				if rowPosUp >= 0 && colPosUp >= 0 && rowPosUp < len(matrix) && colPosUp < len(matrix[0]) {
					if matrix[rowPosUp][colPosUp] == "." {
						matrix[rowPosUp][colPosUp] = "#"
						count++
					} else if matrix[rowPosUp][colPosUp] != "#" {
						count++
					}
				}

				rowPosDown := laterOne.row + posDiff.row
				colPosDown := laterOne.col + posDiff.col
				posDown := Pos{rowPosDown, colPosDown}
				fmt.Println("posDown: ", posDown)

				if rowPosDown >= 0 && colPosDown >= 0 && rowPosDown < len(matrix) && colPosDown < len(matrix[0]) {
					if matrix[rowPosDown][colPosDown] == "." {
						matrix[rowPosDown][colPosDown] = "#"
						count++
					} else if matrix[rowPosDown][colPosDown] != "#" {
						count++
					}
				}
				fmt.Println("--------")
			}
			fmt.Println("----------------")
		}
	}
	fmt.Println("--------")
	utils.PrintMatrix(matrix)
	fmt.Println("-------")
	fmt.Println("count: ", count)

	// for _, row := range matrix {
	// 	for _, col := range row {
	// 		if col == "#" {
	// 			count++
	// 		}
	// 	}
	// }
}

// 296 too high

//go through it and subtract later one from first one
//than generate 2 new signals "#"
//if underlying is . change to "#" else skip
//{r:3, c:4}, {r:5, c:5}
//r: 2, c:1 -> down add -> r:7, c:6
//r: 2, c:1 -> up subtract -> r:1, c:3
//
//{r:3, c:4}, {r:4 ,c:8}
//r:1, c:4 -> down add
//r:1, c:4 -> up subtract -> r:2, c:0
//
//{r:3, c:4}, {r:5,c:2}
//r:2, c:-2 -> down add -> r:7, c:0
//r:2, c:-2 -> up subtract -> r:1, c: 6
//
