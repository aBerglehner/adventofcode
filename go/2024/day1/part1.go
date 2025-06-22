package main

import (
	"fmt"
	"log"
	"math"
	"sort"
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
	right := []int{}
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
		right = append(right, index1)

		fmt.Println("--------")
	}
	fmt.Println("--------")
	sort.Ints(left)
	sort.Ints(right)
	fmt.Println(left)
	fmt.Println(right)
	fmt.Println("--------")

	distance := []int{}
	for i := 0; i < len(left); i++ {
		dis := math.Abs(float64(left[i]) - float64(right[i]))
		distance = append(distance, int(dis))
	}
	fmt.Println(distance)
	sum := 0
	for _, v := range distance {
		sum += v
	}
	fmt.Println("--------")
	fmt.Println(sum)
}
