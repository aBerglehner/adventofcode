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

	stones := make(map[int]int)
	for _, line := range lines {
		fmt.Println(line)
		splitL := strings.FieldsSeq(line)
		for s := range splitL {
			v, err := strconv.Atoi(s)
			if err == nil {
				_, ok := stones[v]
				if !ok {
					stones[v] = 0
				}
				stones[v] = stones[v] + 1
			}
		}
	}
	fmt.Println("-----")
	fmt.Println("stones: ", stones)

	count := 75
	for count > 0 {
		count--
		newStones := copyMap(stones)
		for key, value := range stones {
			// fmt.Println("key: ", key)
			// rule1: if 0 -> 1
			if key == 0 && value > 0 {
				newStones[key] = newStones[key] - value
				newStones[1] = newStones[1] + value

			} else if strLen := len(strconv.Itoa(key)); value > 0 && strLen%2 == 0 {
				// rule2: even digits -> split in middle into 2 parts -> trim leading zeroes -> math.Log10(n)
				newStones[key] = newStones[key] - value
				left, right := splitNumber(key, strLen)
				newStones[left] = newStones[left] + value
				newStones[right] = newStones[right] + value

			} else if value > 0 {
				// rule3: else -> digit * 2024
				newStones[key] = newStones[key] - value
				newV := key * 2024
				newStones[newV] = newStones[newV] + value
			}
		}
		stones = newStones
		fmt.Println("------")
		fmt.Println("count: ", count)
		// fmt.Println("stones: ", stones)
		totalStones := countMapValues(stones)
		// fmt.Printf("totalStones: %v | map: %v\n", totalStones, stones)
		fmt.Printf("totalStones: %v \n", totalStones)
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

func countMapValues(stones map[int]int) int {
	res := 0
	for _, value := range stones {
		res += value
	}
	return res
}

func copyMap(original map[int]int) map[int]int {
	newMap := make(map[int]int, len(original))
	for key, value := range original {
		newMap[key] = value
	}
	return newMap
}
