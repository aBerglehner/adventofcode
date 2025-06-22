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

	left := []int{}
	right := make(map[int]int)
	for _, line := range lines {
		fmt.Println(line)
		splitS := strings.Split(line, " ")
		fmt.Printf("splitS: %v \n", splitS)
		holder := []string{}
		for _, v := range splitS {
			if v != "" {
				holder = append(holder, v)
			}
		}
		fmt.Printf("holder: %v \n", holder)

		index0, _ := strconv.Atoi(holder[0])
		left = append(left, index0)
		index1, _ := strconv.Atoi(holder[1])
		cur := right[index1]
		if cur == 0 {
			right[index1] = 0
		}
		right[index1] = right[index1] + 1

		fmt.Println("--------")
	}
	fmt.Println("--------")
	fmt.Println(left)
	fmt.Println(right)
	fmt.Println("--------")
	res := []int{}
	for _, v := range left {
		cur := right[v]
		res = append(res, v*cur)
	}
	fmt.Println(res)
	fmt.Println("--------")
	sum := 0
	for _, v := range res {
		sum += v
	}
	fmt.Println(sum)
}
