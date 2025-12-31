package main

import (
	"fmt"
	"log"
	"path/filepath"
	"runtime"
	"strings"

	"github.com/alex/adventofcode/go/utils"
)

type Pos struct {
	row int
	col int
}

func main() {
	part2()
}

func part1() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	var matrix [][]string
	for _, line := range data {
		matrix = append(matrix, strings.Split(line, ""))
	}
	// utils.PrintMatrix(matrix)

	startPos := findStart(matrix, "S")
	fmt.Printf("startPos: %v\n", startPos)
	// count splits
	queue := []Pos{startPos}
	rows := len(matrix)
	cols := len(matrix[0])
	splits := 0
	visited := make(map[Pos]bool)
	for len(queue) > 0 {
		curPos := queue[0]
		queue = queue[1:]
		rr := curPos.row + 1
		if rr >= rows {
			continue
		}
		nextPos := Pos{rr, curPos.col}
		marked := visited[nextPos]
		if marked {
			continue
		}
		visited[nextPos] = true
		next := matrix[rr][curPos.col]
		if next == "." {
			//. will just move down
			queue = append(queue, Pos{rr, curPos.col})
		} else if next == "^" {
			// fmt.Printf("row: %v col: %v\n", rr, curPos.col)
			splits++
			//^ will split it to left and right
			ccMinus := curPos.col - 1
			if ccMinus >= 0 {
				queue = append(queue, Pos{rr, ccMinus})
			}

			ccPlus := curPos.col + 1
			if ccPlus < cols {
				queue = append(queue, Pos{rr, ccPlus})
			}
		}
	}
	fmt.Printf("splits: %v\n", splits)
}

func findStart(matrix [][]string, search string) Pos {
	for row := range matrix {
		for col := range matrix[row] {
			if matrix[row][col] == search {
				return Pos{row, col}
			}
		}
	}
	return Pos{}
}

func part2() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	var matrix [][]string
	for _, line := range data {
		matrix = append(matrix, strings.Split(line, ""))
	}
	// utils.PrintMatrix(matrix)

	startPos := findStart(matrix, "S")
	fmt.Printf("startPos: %v\n", startPos)
	// count splits
	rows := len(matrix)

	dp := score(matrix, rows, startPos.row, startPos.col, make(map[Pos]int))
	fmt.Printf("dp: %v\n", dp)
}

func score(matrix [][]string, rows int, r int, c int, cache map[Pos]int) int {
	if c < 0 || c >= len(matrix[0]) {
		return 0
	}
	pos := Pos{r, c}
	if v, ok := cache[pos]; ok {
		return v
	}
	var result int
	if r+1 == rows {
		result = 1
	} else if matrix[r+1][c] == "^" {
		result = score(matrix, rows, r+1, c+1, cache) + score(matrix, rows, r+1, c-1, cache)
	} else {
		result = score(matrix, rows, r+1, c, cache)
	}
	cache[pos] = result
	return result
}
