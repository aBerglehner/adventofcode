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

	holder := []string{}
	for _, line := range lines {
		fmt.Println(line)

		left := 0
		for {
			fmt.Println("------------")
			startPos := strings.Index(line[left:], "mul(")
			if startPos == -1 {
				break
			} else {
				startPos += left
			}
			endPos := strings.Index(line[startPos:], ")")
			if endPos == -1 {
				break
			} else {
				endPos += startPos
			}

			left = endPos
			nextStartPos := strings.Index(line[startPos+1:], "mul(")
			if nextStartPos != -1 {
				nextStartPos += startPos + 1
				if nextStartPos < endPos {
					left = nextStartPos - 2
				}
			}

			fmt.Println("startPos: ", startPos)
			fmt.Println("endPos: ", endPos)
			fmt.Println("nextStartPos: ", nextStartPos)
			fmt.Println("line: ", line)
			curValue := line[startPos+4 : endPos]
			fmt.Println("test: ", curValue)
			holder = append(holder, curValue)
		}
		fmt.Println("--------------------------------")
		fmt.Println("holder: ", holder)
		fmt.Println("--------------------------------")

	}
	res := [][]int{}
	for _, v := range holder {
		fmt.Println("------------")
		fmt.Println("v: ", v)
		values := strings.Split(v, ",")
		if len(values) != 2 {
			continue
		}
		first := values[0]
		v1, err1 := strconv.Atoi(first)
		if err1 != nil {
			continue
		}
		second := values[1]
		v2, err2 := strconv.Atoi(second)
		if err2 != nil {
			continue
		}
		res = append(res, []int{v1, v2})
	}
	fmt.Println("res: ", res)
	fmt.Println("------")

	multiSlice := []int{}
	for _, v := range res {
		multiSlice = append(multiSlice, v[0]*v[1])
	}
	fmt.Println("multiSlice: ", multiSlice)
	fmt.Println("------")
	sum := 0
	for _, v := range multiSlice {
		sum += v
	}
	fmt.Println("sum: ", sum)
}

// too low 28858842
