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

	count := 0
	for _, line := range lines {
		lvlSlice := strings.Fields(line)
		res := []bool{}
		for i := range lvlSlice {
			if i == 0 {
				// fmt.Println("in first")
				res = append(res, newFunction(lvlSlice[1:]))
			} else if i == len(lvlSlice)-1 {
				// fmt.Println("in last")
				res = append(res, newFunction(lvlSlice[0:i]))
			} else {
				// fmt.Println("in between")
				// fmt.Println("lvlSlice: ", lvlSlice)
				// newSlice := append(lvlSlice[0:i], lvlSlice[newT:]...)
				newSlice := []string{}
				for curI, v := range lvlSlice {
					if curI == i {
						continue
					}
					newSlice = append(newSlice, v)
				}
				res = append(res, newFunction(newSlice))
			}
			// fmt.Println("-------")
		}
		// fmt.Println("res: ", res)
		for _, v := range res {
			if v {
				count++
				break
			}
		}
	}
	fmt.Println("count: ", count)
}

func newFunction(lvlSlice []string) bool {
	// fmt.Println("slice: ", lvlSlice)
	ruleInc := true
	ruleDec := true
	ruleDif := true
	last, _ := strconv.Atoi(lvlSlice[0])

	for i, v := range lvlSlice {
		// fmt.Println("-------")
		cur, _ := strconv.Atoi(v)
		// fmt.Println("cur: ", cur)
		if i == 0 {
			continue
		}
		if ruleInc {
			ruleInc = cur > last
		}
		if ruleDec {
			ruleDec = cur < last
		}
		if ruleInc == false && ruleDec == false {
			break
		}
		dif := math.Abs(float64(last) - float64(cur))
		last = cur
		// fmt.Println("dif: ", dif)
		if dif < 1 || dif > 3 {
			ruleDif = false
			break
		}

		if i == len(lvlSlice)-1 {
			return true
		}
	}
	if ruleDif && (ruleInc || ruleDec) {
		return true
	}
	return false
}
