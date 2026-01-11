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
	part2()
}

type pos struct {
	x int
	y int
}

func part1() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	var distances []pos
	for _, line := range data {
		// fmt.Println(line)
		strs := strings.Split(line, ",")
		x, _ := strconv.Atoi(strs[0])
		y, _ := strconv.Atoi(strs[1])
		distances = append(distances, pos{x, y})
	}
	// fmt.Printf("distances: %v\n", distances)
	max := 0
	for _, a := range distances {
		for _, b := range distances {
			if a == b {
				continue
			}
			cur := calcArea(a, b)
			if cur > max {
				max = cur
			}
		}
	}
	fmt.Printf("max: %v\n", max)
}

func calcArea(a pos, b pos) int {
	return (abs(a.x, b.x) + 1) * (abs(a.y, b.y) + 1)
}

func abs(a int, b int) int {
	if a > b {
		return a - b
	}
	return b - a
}

func part2() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	var distances []pos
	for _, line := range data {
		// fmt.Println(line)
		strs := strings.Split(line, ",")
		x, _ := strconv.Atoi(strs[0])
		y, _ := strconv.Atoi(strs[1])
		distances = append(distances, pos{x, y})
	}
	// fmt.Printf("distances: %v\n", distances)
	max := 0
	for _, a := range distances {
		for _, b := range distances {
			if a == b {
				continue
			}
			cur := calcArea(a, b)
			if cur > max {
				max = cur
			}
		}
	}
	fmt.Printf("max: %v\n", max)
}
