package main

import (
	"fmt"
	"log"
	"math"
	"strconv"
	"strings"

	"github.com/alex/adventofcode/go/utils"
)

func main() {
	lines, err := utils.ReadLines("input.txt")
	if err != nil {
		log.Fatal(err)
	}

	stones := []int{}
	for _, line := range lines {
		fmt.Println(line)
		splitL := strings.FieldsSeq(line)
		for s := range splitL {
			v, err := strconv.Atoi(s)
			if err == nil {
				stones = append(stones, v)
			}
		}
	}
	fmt.Println("-----")
	fmt.Println("stones: ", stones)

	count := 25
	for count > 0 {
		count--
		newStones := []int{}
		for _, s := range stones {
			// rule1: if 0 -> 1
			if s == 0 {
				newStones = append(newStones, 1)
			} else if mathRes := math.Log10(float64(s)); (int(mathRes)+1)%2 == 0 {
				// rule2: even digits -> split in middle into 2 parts -> trim leading zeroes -> math.Log10(n)
				left, right := splitNumber(s, int(mathRes)+1)
				newStones = append(newStones, left, right)
			} else {
				// rule3: else -> digit * 2024
				newStones = append(newStones, s*2024)
			}
		}
		stones = newStones
		fmt.Println("------")
		// fmt.Println("stones: ", stones)
		fmt.Printf("stones %v \n", len(stones))
	}
}

func splitNumber(n int, digits int) (left int, right int) {
	remover := 1
	for i := 0; i < digits/2; i++ {
		remover = remover * 10
	}
	left = n / remover
	right = n % remover
	return left, right
}
