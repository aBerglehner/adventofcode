package main

import (
	"fmt"
	"log"
	"slices"
	"strconv"
	"strings"

	"github.com/alex/adventofcode/go/utils"
)

func main() {
	lines, err := utils.ReadLines("input.txt")
	if err != nil {
		log.Fatal(err)
	}

	leftMap := make(map[string][]string)
	pages := [][]string{}
	upperPart := true
	for _, line := range lines {
		fmt.Println(line)
		if line == "" {
			upperPart = false
			continue
		}
		if upperPart {
			// get right part and take this as key
			// left part will be the values
			parts := strings.Split(line, "|")
			values, ok := leftMap[parts[1]]
			if !ok {
				leftMap[parts[1]] = []string{}
			}
			leftMap[parts[1]] = append(values, parts[0])

		} else {
			splitLine := strings.Split(line, ",")
			pages = append(pages, splitLine)
		}
	}
	fmt.Println("-------")
	fmt.Println(leftMap)
	fmt.Println("-------")
	fmt.Println(pages)
	res := [][]string{}
	for _, page := range pages {
		exists := false
		for i, v := range page {
			exists = false
			mapValues, ok := leftMap[v]
			if i != 0 && !ok {
				exists = false
				break
			}
			for j := 0; j < i; j++ {
				cur := page[j]
				if slices.Contains(mapValues, cur) {
					exists = true
				} else {
					exists = false
				}
			}
			if i == 0 {
				exists = true
			}
			if !exists {
				break
			}
		}
		if !exists {
			res = append(res, page)
		}
	}
	fmt.Println("-------")
	fmt.Println("res: ", res)
	fmt.Println("-------")

	endRes := [][]string{}
	for _, page := range res {
		curCopy := make([]string, 100)
		for _, v := range page {
			mapValue, ok := leftMap[v]
			var pos int = 0
			if ok {
				pos = len(mapValue)
				for _, v := range mapValue {
					if slices.Contains(page, v) {
						pos = pos + 1
					}
				}
			}
			curCopy[pos] = v
		}
		cleaned := []string{}
		fmt.Println("curCopy: ", curCopy)
		for _, v := range curCopy {
			if v != "" {
				cleaned = append(cleaned, v)
			}
		}
		endRes = append(endRes, cleaned)
	}
	fmt.Println("-------")
	fmt.Println("endRes: ", endRes)

	sum := 0
	for _, v := range endRes {
		len := len(v)
		mid := len / 2
		cur, err := strconv.Atoi(v[mid])
		if err == nil {
			sum += cur
		}
	}
	fmt.Println("sum: ", sum)
	fmt.Println("-------")
}
