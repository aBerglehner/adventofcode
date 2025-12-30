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

func part1() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	cols := 0
	for _, line := range data {
		fmt.Println(line)
		s := strings.Split(line, " ")

		for _, v := range s {
			if v != "" {
				cols++
			}
		}
		fmt.Printf("columns: %v\n", cols)
		break
	}
	fmt.Printf("\n")
	fmt.Printf("----------")
	fmt.Printf("\n\n")

	var matrix [][]int
	for i := 0; i < cols; i++ {
		matrix = append(matrix, []int{})
	}
	for _, line := range data {
		strs := strings.Split(line, " ")
		var cols []int
		for _, str := range strs {
			if str != "" {
				v := strings.Trim(str, "")
				n, err := strconv.Atoi(v)
				if err == nil {
					cols = append(cols, n)
				}
			}
		}
		for col := range cols {
			matrix[col] = append(matrix[col], cols[col])
		}
	}
	fmt.Printf("matrix: %v\n", matrix)
	fmt.Println("")

	var signs []string
	for i, line := range data {
		if i != len(data)-1 {
			continue
		}
		strs := strings.Split(line, " ")
		for _, str := range strs {
			if str != "" {
				v := strings.Trim(str, "")
				signs = append(signs, v)
			}
		}
	}
	fmt.Printf("signs: %v\n", signs)
	ans := 0
	for i, sign := range signs {
		if sign == "*" {
			ans += multi(matrix[i])
		} else {
			ans += add(matrix[i])
		}
	}
	fmt.Printf("ans: %v\n", ans)
}

func multi(row []int) int {
	ans := 1
	for _, v := range row {
		ans *= v
	}
	return ans
}

func add(row []int) int {
	ans := 0
	for _, v := range row {
		ans += v
	}
	return ans
}

func part2() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	cols := 0
	for _, line := range data {
		fmt.Println(line)
		s := strings.Split(line, " ")

		for _, v := range s {
			if v != "" {
				cols++
			}
		}
		fmt.Printf("columns: %v\n", cols)
		break
	}
	fmt.Printf("\n")
	fmt.Printf("----------")
	fmt.Printf("\n\n")

	var signs []string
	for i, line := range data {
		if i != len(data)-1 {
			continue
		}
		strs := strings.Split(line, " ")
		for _, str := range strs {
			if str != "" {
				v := strings.Trim(str, "")
				signs = append(signs, v)
			}
		}
	}
	fmt.Printf("signs: %v\n", signs)

	var matrix [][]string
	for _, line := range data {
		strs := strings.Split(line, "")
		matrix = append(matrix, strs)
	}
	utils.PrintMatrix(matrix)

	ans := 0
	curSign := len(signs) - 1
	var nums []int
	for i := len(matrix[0]) - 1; i >= 0; i-- {
		num := 0
		for j := 0; j < len(matrix)-1; j++ {
			cur := matrix[j][i]
			n, err := strconv.Atoi(cur)
			if err == nil {
				num = num*10 + n
			}
		}

		if num == 0 {
			sign := signs[curSign]
			if sign == "*" {
				res := multi(nums)
				fmt.Printf("res: %v\n", res)
				ans += res
			} else {
				res := add(nums)
				fmt.Printf("res: %v\n", res)
				ans += res
			}
			curSign--
			nums = make([]int, 0)
			fmt.Println("")
			// break
		} else {
			fmt.Printf("num: %v\n", num)
			nums = append(nums, num)
		}
	}
	sign := signs[curSign]
	if sign == "*" {
		res := multi(nums)
		fmt.Printf("res: %v\n", res)
		ans += res
	} else {
		res := add(nums)
		fmt.Printf("res: %v\n", res)
		ans += res
	}
	fmt.Println("")
	fmt.Printf("ans: %v\n", ans)
}
