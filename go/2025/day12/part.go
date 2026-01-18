package main

import (
	"fmt"
	"log"
	"path/filepath"
	"runtime"
	"strconv"
	"strings"

	"github.com/alex/adventofcode/go/utils"
)

func main() {
	part1()
}

func part1() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	isPart1 := true
	fields := 0
	var presents []int
	var ans int
	for _, line := range data {
		if line == "part2" {
			isPart1 = false
			continue
		}

		if isPart1 {
			if line == "" {
				// fmt.Printf("fields: %v\n", fields)
				presents = append(presents, fields)
				fields = 0
				continue
			}
			fields += strings.Count(line, "#")

			//

			// fmt.Println("p1")
			// fmt.Println(line)
		} else {
			p2 := strings.Split(line, ":")
			matrix := strings.Split(p2[0], "x")

			r, _ := strconv.Atoi(matrix[0])
			c, _ := strconv.Atoi(matrix[1])
			totalGridSize := r * c

			totalPresentSize := 0
			for i, v := range strings.Split(strings.Trim(p2[1], " "), " ") {

				n, _ := strconv.Atoi(v)
				p := presents[i]
				totalPresentSize += n * p

			}
			// fmt.Printf("gridSize: %v\n", totalGridSize)
			// fmt.Printf("totalPresentSize: %v\n", totalPresentSize)
			// fmt.Println()

			if float64(totalPresentSize)*1.2 < float64(totalGridSize) {
				ans += 1
			} else if totalPresentSize > totalGridSize {
				continue
			} else {
				fmt.Printf("hard: totalGridSize: %v totalPresentSize: %v\n", totalGridSize, totalPresentSize)
			}
			// fmt.Println(line)
		}

	}
	fmt.Printf("ans: %v\n", ans)
}
