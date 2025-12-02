package main

import (
	"fmt"
	"log"
	"strconv"

	"github.com/alex/adventofcode/go/utils"
)

func main() {
	part2()
}

func part1() {
	data, err := utils.ReadLines("./day1/input")
	if err != nil {
		log.Fatal(err)
	}

	ans := 0
	cur := 50
	for _, line := range data {
		dir := line[:1]
		disStr := line[1:]
		dis, _ := strconv.Atoi(disStr)
		cur = walk(dir, cur, dis)
		if cur == 0 {
			ans++
		}
		fmt.Printf("cur: %v\n", cur)
	}
	fmt.Printf("ans: %v\n", ans)
}

func walk(dir string, cur int, dis int) int {
	if dir == "R" {
		res := cur
		for dis > 0 {
			res += 1
			if res > 99 {
				res = 0
			}
			dis--
		}
		return res
	}
	if dir == "L" {
		res := cur
		for dis > 0 {
			res -= 1
			if res < 0 {
				res = 99
			}
			dis--
		}
		return res
	}
	return -1
}

type holder struct {
	cur       int
	zeroCount int
}

func part2() {
	data, err := utils.ReadLines("./day1/input")
	if err != nil {
		log.Fatal(err)
	}

	holder := holder{50, 0}
	for _, line := range data {
		dir := line[:1]
		disStr := line[1:]
		dis, _ := strconv.Atoi(disStr)
		walk2(&holder, dir, dis)
	}
	fmt.Printf("holder: %v\n", holder)
}

func walk2(h *holder, dir string, dis int) {
	if dir == "R" {
		zeroCount := 0
		res := h.cur
		for dis > 0 {
			res += 1
			if res > 99 {
				res = 0
				zeroCount++
			}
			dis--
		}
		h.cur = res
		h.zeroCount = h.zeroCount + zeroCount
	}
	if dir == "L" {
		zeroCount := 0
		res := h.cur
		for dis > 0 {
			res -= 1
			if res == 0 {
				zeroCount++
			}
			if res < 0 {
				res = 99
			}
			dis--
		}
		h.cur = res
		h.zeroCount = h.zeroCount + zeroCount
	}
}
