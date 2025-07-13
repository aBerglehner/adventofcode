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
	lines, err := utils.ReadLines("test_input.txt")
	if err != nil {
		log.Fatal(err)
	}

	toReturn := []int{}
	for _, line := range lines {
		fmt.Println("-----------")
		fmt.Println(line)
		parts := strings.Split(line, ":")
		part2 := strings.Fields(parts[1])
		nums := []int{}
		for _, v := range part2 {
			n, _ := strconv.Atoi(v)
			nums = append(nums, n)
		}
		solution, _ := strconv.Atoi(parts[0])
		fmt.Println("solution: ", solution)
		fmt.Println("nums: ", nums)

		if len(nums) < 2 {
			fmt.Println("-------")
			fmt.Println("helpp")
			fmt.Println("-------")
			break
		}

		results := []int{}
		results = append(results, nums[0]+nums[1])
		results = append(results, nums[0]*nums[1])
		if slices.Contains(results, solution) {
			toReturn = append(toReturn, solution)
			fmt.Println("early")
			continue
		}

		found := false
		for i := 2; i < len(nums); i++ {
			num := nums[i]
			newRes := []int{}
			for _, res := range results {
				first := res + num
				if first == solution {
					toReturn = append(toReturn, solution)
					found = true
					break
				} else if first < solution {
					newRes = append(newRes, first)
				}

				second := res * num
				if second == solution {
					toReturn = append(toReturn, solution)
					found = true
					break
				} else if second < solution {
					newRes = append(newRes, second)
				}
			}
			if found {
				break
			}
			results = newRes
		}
		fmt.Println("results: ", results)
	}
	fmt.Println("------------")
	fmt.Println("toReturn: ", toReturn)
	sum := 0
	for _, v := range toReturn {
		sum += v
	}
	fmt.Println("------------")
	fmt.Println("sum: ", sum)
}

// too high 267566106986
