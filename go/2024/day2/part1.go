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

		ruleInc := true
		ruleDec := true
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
				break
			}

			if i == len(lvlSlice)-1 {
				count++
			}
		}

	}
	fmt.Printf("count: %v\n", count)
}
