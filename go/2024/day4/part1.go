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
			if v == "X" {
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
		count += checkRight(e, matrix)
		count += checkLeft(e, matrix)
		count += checkDown(e, matrix)
		count += checkUp(e, matrix)
		count += checkDiagonalDownRight(e, matrix)
		count += checkDiagonalDownLeft(e, matrix)
		count += checkDiagonalUpRight(e, matrix)
		count += checkDiagonalUpLeft(e, matrix)
	}
	fmt.Println("count: ", count)
}

type Matrix struct {
	row int
	col int
}

func checkRight(pos Matrix, matrix [][]string) int {
	colLength := len(matrix[0])
	if colLength-pos.col < 4 {
		return 0
	}
	res := []string{}
	for i := pos.col; i < pos.col+4; i++ {
		res = append(res, matrix[pos.row][i])
	}
	joinedStr := strings.Join(res, "")
	fmt.Println("joinedStr: ", joinedStr)

	if joinedStr == "XMAS" {
		return 1
	}
	return 0
}

func checkLeft(pos Matrix, matrix [][]string) int {
	// left check
	if pos.col-3 < 0 {
		return 0
	}
	res := []string{}
	for i := pos.col; i > pos.col-4; i-- {
		res = append(res, matrix[pos.row][i])
	}
	joinedStr := strings.Join(res, "")
	fmt.Println("joinedStr: ", joinedStr)
	if joinedStr == "XMAS" {
		return 1
	}
	return 0
}

func checkDown(pos Matrix, matrix [][]string) int {
	rows := len(matrix)
	if rows-pos.row < 4 {
		return 0
	}
	res := []string{}
	for i := pos.row; i < pos.row+4; i++ {
		res = append(res, matrix[i][pos.col])
	}
	joinedStr := strings.Join(res, "")
	fmt.Println("joinedStr: ", joinedStr)

	if joinedStr == "XMAS" {
		return 1
	}
	return 0
}

func checkUp(pos Matrix, matrix [][]string) int {
	// up check
	if pos.row-3 < 0 {
		return 0
	}
	res := []string{}
	for i := pos.row; i > pos.row-4; i-- {
		res = append(res, matrix[i][pos.col])
	}
	joinedStr := strings.Join(res, "")
	fmt.Println("joinedStr: ", joinedStr)
	if joinedStr == "XMAS" {
		return 1
	}
	return 0
}

func checkDiagonalDownRight(pos Matrix, matrix [][]string) int {
	// right check
	colLength := len(matrix[0])
	if colLength-pos.col < 4 {
		return 0
	}
	// down check
	rows := len(matrix)
	if rows-pos.row < 4 {
		return 0
	}
	res := []string{}
	curRow := 0
	for i := pos.col; i < pos.col+4; i++ {
		res = append(res, matrix[pos.row+curRow][i])
		curRow++
	}
	joinedStr := strings.Join(res, "")
	fmt.Println("joinedStr: ", joinedStr)

	if joinedStr == "XMAS" {
		return 1
	}
	return 0
}

func checkDiagonalUpRight(pos Matrix, matrix [][]string) int {
	// fmt.Println("pos: ", pos)
	// right check
	colLength := len(matrix[0])
	if colLength-pos.col < 4 {
		return 0
	}
	// up check
	if pos.row-3 < 0 {
		return 0
	}
	res := []string{}
	curRow := 0
	for i := pos.col; i < pos.col+4; i++ {
		res = append(res, matrix[pos.row-curRow][i])
		curRow++
	}
	joinedStr := strings.Join(res, "")
	fmt.Println("joinedStr: ", joinedStr)

	if joinedStr == "XMAS" {
		return 1
	}
	return 0
}

func checkDiagonalUpLeft(pos Matrix, matrix [][]string) int {
	// fmt.Println("pos: ", pos)
	// left check
	if pos.col-3 < 0 {
		return 0
	}
	// up check
	if pos.row-3 < 0 {
		return 0
	}
	res := []string{}
	curRow := 0
	for i := pos.col; i > pos.col-4; i-- {
		res = append(res, matrix[pos.row-curRow][i])
		curRow++
	}
	joinedStr := strings.Join(res, "")
	fmt.Println("joinedStr: ", joinedStr)

	if joinedStr == "XMAS" {
		return 1
	}
	return 0
}

func checkDiagonalDownLeft(pos Matrix, matrix [][]string) int {
	// left check
	if pos.col-3 < 0 {
		return 0
	}
	// down check
	rows := len(matrix)
	if rows-pos.row < 4 {
		return 0
	}
	res := []string{}
	curCol := 0
	for i := pos.row; i < pos.row+4; i++ {
		res = append(res, matrix[i][pos.col-curCol])
		curCol++
	}
	joinedStr := strings.Join(res, "")
	fmt.Println("joinedStr: ", joinedStr)

	if joinedStr == "XMAS" {
		return 1
	}
	return 0
}
