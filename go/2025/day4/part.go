package main

import (
	"fmt"
	"log"
	"path/filepath"
	"runtime"
	"slices"
	"strings"
	"sync"

	"github.com/alex/adventofcode/go/utils"
)

func main() {
	// part1()
	// part1Para()
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
		// fmt.Println(line)
		row := strings.Split(line, "")
		matrix = append(matrix, row)
	}
	// utils.PrintMatrix(matrix)
	rows := len(matrix)
	cols := len(matrix[0])
	ans := 0
	for row := range matrix {
		for col := range matrix[row] {
			if matrix[row][col] != "@" {
				continue
			}
			adjacent := 0
			for _, dir := range utils.GetDirsAlsoDiagonal() {
				rr := row + dir.Row
				if rr < 0 || rr >= rows {
					continue
				}
				cc := col + dir.Col
				if cc < 0 || cc >= cols {
					continue
				}
				if matrix[rr][cc] == "@" {
					adjacent++
				}
			}
			if adjacent < 4 {
				ans++
			}
		}
	}
	// fmt.Printf("ans: %v\n", ans)
}

func part1Para() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	var matrix [][]string
	for _, line := range data {
		// fmt.Println(line)
		row := strings.Split(line, "")
		matrix = append(matrix, row)
	}
	// utils.PrintMatrix(matrix)
	rows := len(matrix)
	cols := len(matrix[0])
	var wg sync.WaitGroup
	ch := make(chan int)
	for row := range matrix {
		wg.Add(1)
		go findForRow(matrix, row, rows, cols, ch, &wg)
	}
	go func() {
		wg.Wait()
		close(ch)
	}()
	ans := 0
	for v := range ch {
		ans += v
	}
	// fmt.Printf("ans: %v\n", ans)
}

func findForRow(matrix [][]string, row int, rows int, cols int, ch chan<- int, wg *sync.WaitGroup) {
	defer wg.Done()
	ans := 0
	for col := range matrix[row] {
		if matrix[row][col] != "@" {
			continue
		}
		adjacent := 0
		for _, dir := range utils.GetDirsAlsoDiagonal() {
			rr := row + dir.Row
			if rr < 0 || rr >= rows {
				continue
			}
			cc := col + dir.Col
			if cc < 0 || cc >= cols {
				continue
			}
			if matrix[rr][cc] == "@" {
				adjacent++
			}
		}
		if adjacent < 4 {
			ans++
		}
	}
	ch <- ans
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
		// fmt.Println(line)
		row := strings.Split(line, "")
		matrix = append(matrix, row)
	}
	// utils.PrintMatrix(matrix)

	totalAns := 0
	for {
		ans, pos := findValidOnes(matrix)
		totalAns += ans
		if ans > 0 {
			matrix = getNewMatrix(matrix, pos)
		} else {
			break
		}
	}
	fmt.Printf("totalAns: %v\n", totalAns)
}

func getNewMatrix(matrix [][]string, pos []utils.Pos) [][]string {
	var result [][]string
	for row := range matrix {
		var newRow []string
		for col := range matrix {
			cur := utils.Pos{row, col}
			if slices.Contains(pos, cur) {
				newRow = append(newRow, ".")
			} else {
				newRow = append(newRow, matrix[row][col])
			}
		}
		result = append(result, newRow)
	}
	return result
}

func findValidOnes(matrix [][]string) (ans int, pos []utils.Pos) {
	rows := len(matrix)
	cols := len(matrix[0])
	for row := range matrix {
		for col := range matrix[row] {
			if matrix[row][col] != "@" {
				continue
			}
			adjacent := 0
			for _, dir := range utils.GetDirsAlsoDiagonal() {
				rr := row + dir.Row
				if rr < 0 || rr >= rows {
					continue
				}
				cc := col + dir.Col
				if cc < 0 || cc >= cols {
					continue
				}
				if matrix[rr][cc] == "@" {
					adjacent++
				}
			}
			if adjacent < 4 {
				pos = append(pos, utils.Pos{row, col})
				ans++
			}
		}
	}
	return ans, pos
}
