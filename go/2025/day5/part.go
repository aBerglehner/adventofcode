package main

import (
	"fmt"
	"log"
	"path/filepath"
	"runtime"
	"slices"
	"strconv"
	"strings"

	"github.com/alex/adventofcode/go/utils"
)

func main() {
	part2()
}

type pair struct {
	min int
	max int
}

func (e *pair) inBounds(cur int) bool {
	if cur >= e.min && cur <= e.max {
		return true
	}
	return false
}

func part1() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	pairs := []pair{}
	isFresh := true
	ans := 0
	for _, line := range data {
		// fmt.Println(line)
		if line == "" {
			isFresh = false
			continue
		}
		if isFresh {
			rangeIds := strings.Split(line, "-")
			min, _ := strconv.Atoi(rangeIds[0])
			max, _ := strconv.Atoi(rangeIds[1])
			pairs = append(pairs, pair{min, max})
		} else {
			n, _ := strconv.Atoi(line)
			ans += checkPairList(pairs, n)
		}
	}
	// fmt.Printf("myMap: %v\n", myMap)
	fmt.Printf("ans: %v\n", ans)
}

func checkPairList(pairs []pair, cur int) int {
	for _, v := range pairs {
		if v.inBounds(cur) {
			return 1
		}
	}
	return 0
}

func part2() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	pairs := []pair{}
	isFresh := true
	for _, line := range data {
		fmt.Println(line)
		if line == "" {
			isFresh = false
			break
		}
		if isFresh {
			rangeIds := strings.Split(line, "-")
			min, _ := strconv.Atoi(rangeIds[0])
			max, _ := strconv.Atoi(rangeIds[1])
			includePair(&pairs, min, max)
		}
	}
	fmt.Printf("pairs: %v\n", pairs)
	slices.SortFunc(pairs, func(a pair, b pair) int {
		return a.min - b.min
	})
	fmt.Printf("after sort pairs: %v\n", len(pairs))
	newPairs := []pair{}
	for _, pair := range pairs {
		includePair(&newPairs, pair.min, pair.max)
	}
	// fmt.Printf("newPairs: %v\n", newPairs)
	ans := 0
	for _, pair := range newPairs {
		//+1 cause we include start and end
		ans += pair.max - pair.min + 1
	}
	fmt.Printf("ans: %v\n", ans)
}

// if min/max is in existing pair expaind delete the pair and expend the new one
// if not add the new pair
func includePair(pairs *[]pair, curMin int, curMax int) {
	for i, curPair := range *pairs {
		if curMin >= curPair.min && curMin <= curPair.max {
			newMax := max(curPair.max, curMax)
			(*pairs)[i] = pair{curPair.min, newMax}
			// fmt.Println("first break")
			return
		}
		if curMax >= curPair.min && curMax <= curPair.max {
			newMin := min(curPair.min, curMin)
			(*pairs)[i] = pair{newMin, curPair.max}
			// fmt.Println("second break")
			return
		}
	}
	*pairs = append(*pairs, pair{curMin, curMax})
}
