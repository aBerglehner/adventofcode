package main

import (
	"fmt"
	"log"
	"strings"

	"github.com/alex/adventofcode/go/utils"
)

func main() {
	lines, err := utils.ReadLines("input.txt")
	if err != nil {
		log.Fatal(err)
	}

	matrix := [][]string{}
	for _, line := range lines {
		fmt.Println(line)
		row := strings.Split(line, "")
		fmt.Println("row: ", row)
		matrix = append(matrix, row)
	}
	utils.PrintMatrix(matrix)

	startPos := []Matrix{}
	for rowI, row := range matrix {
		for colI, v := range row {
			if v == "A" {
				startPos = append(startPos, Matrix{rowI, colI})
			}
		}
	}
	utils.PrintMatrix(startPos, "startPos")

	// todo
	// -try every startPos
	// -try to go in every direction and check if found 1 or more
	// -count them
	count := 0
	for _, e := range startPos {
		count += checkPart2(e, matrix)
	}
	fmt.Println("count: ", count)
}

type Matrix struct {
	row int
	col int
}

func checkPart2(pos Matrix, matrix [][]string) int {
	// up check
	if pos.row < 1 {
		return 0
	}
	// down check
	rows := len(matrix)
	if rows-pos.row < 2 {
		return 0
	}
	// left check
	if pos.col < 1 {
		return 0
	}
	// right check
	colLength := len(matrix[0])
	if colLength-pos.col < 2 {
		return 0
	}

	firstCheck := false
	rightDown := matrix[pos.row+1][pos.col+1]
	leftUp := matrix[pos.row-1][pos.col-1]
	firstFus := rightDown + leftUp
	if firstFus == "MS" || firstFus == "SM" {
		firstCheck = true
	}

	secondCheck := false
	leftDown := matrix[pos.row+1][pos.col-1]
	rightUp := matrix[pos.row-1][pos.col+1]
	secondFus := leftDown + rightUp
	if secondFus == "MS" || secondFus == "SM" {
		secondCheck = true
	}

	if firstCheck && secondCheck {
		return 1
	}

	return 0
}
