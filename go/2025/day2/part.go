package main

import (
	"log"
	"path/filepath"
	"runtime"
	"strconv"
	"strings"

	"github.com/alex/adventofcode/go/utils"
)

func main() {
	// part1()
	// part1Para()
	part2()
	part2para()
}

type number struct {
	start int
	end   int
}

func part1() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	var numbers []number
	for _, line := range data {
		// fmt.Println(line)
		for v := range strings.SplitSeq(line, ",") {
			n := strings.Split(v, "-")
			numbers = append(numbers, tN(n[0], n[1]))
		}
	}
	// fmt.Printf("numbers: %v\n", numbers)
	// goal find repeat twice 55 -> 5,5 ; 1212 -> 12,12 ; 6464 -> 64,64
	ans := 0
	for _, n := range numbers {
		start := n.start
		end := n.end
		for i := start; i <= end; i++ {
			s := strconv.Itoa(i)
			l := len(s)
			if l < 1 && l%2 != 0 {
				continue
			}

			firstS := s[0 : l/2]
			secondS := s[l/2:]
			if firstS == secondS {
				ans += i
			}
		}
	}
	// fmt.Printf("ans part1: %v\n", ans)
}

func tN(a string, b string) number {
	aN, _ := strconv.Atoi(a)
	bN, _ := strconv.Atoi(b)
	return number{aN, bN}
}

func part1Para() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	var numbers []number
	for _, line := range data {
		// fmt.Println(line)
		for v := range strings.SplitSeq(line, ",") {
			n := strings.Split(v, "-")
			numbers = append(numbers, tN(n[0], n[1]))
		}
	}
	numCPU := runtime.NumCPU()

	chunks := splitTasks(numbers, numCPU)
	ch := make(chan int, len(chunks))
	for _, chunk := range chunks {
		go findAns(chunk, ch)
	}

	ans := 0
	for i := 0; i < len(chunks); i++ {
		ans += <-ch
	}
	// fmt.Printf("ans para: %v\n", ans)
}

func splitTasks[T any](tasks []T, n int) [][]T {
	var chunks [][]T
	length := len(tasks)
	if n <= 0 {
		n = 1
	}
	for i := 0; i < n; i++ {
		start := i * length / n
		end := (i + 1) * length / n
		chunks = append(chunks, tasks[start:end])
	}
	return chunks
}

func findAns(numbers []number, ch chan<- int) {
	ans := 0
	for _, n := range numbers {
		start := n.start
		end := n.end
		for i := start; i <= end; i++ {
			s := strconv.Itoa(i)
			l := len(s)
			if l < 1 && l%2 != 0 {
				continue
			}

			firstS := s[0 : l/2]
			secondS := s[l/2:]
			if firstS == secondS {
				ans += i
			}
		}
	}
	ch <- ans
}

func part2() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	var numbers []number
	for _, line := range data {
		// fmt.Println(line)
		for v := range strings.SplitSeq(line, ",") {
			n := strings.Split(v, "-")
			numbers = append(numbers, tN(n[0], n[1]))
		}
	}
	// fmt.Printf("numbers: %v\n", numbers)
	// fmt.Println("")
	// goal find repeat twice 55 -> 5,5 ; 1212 -> 12,12 ; 6464 -> 64,64
	ans := 0
	for _, n := range numbers {
		start := n.start
		end := n.end
	outer:
		for i := start; i <= end; i++ {
			s := strconv.Itoa(i)
			l := len(s)
			if l < 2 && l%2 != 0 {
				continue
			}
			// check if string could be splitted into equal pairs
			for j := 1; j < l; j++ {
				if l%j != 0 {
					continue
				}
				isPairEqual := true
				firstS := s[0:j]
				for pair := j; pair < l; pair += j {
					nextS := s[pair : pair+j]
					if firstS != nextS {
						isPairEqual = false
						continue
					}
				}
				if isPairEqual {
					// fmt.Printf("i: %v\n", i)
					ans += i
					continue outer
				}
			}
			cur := s[0]
			// fmt.Printf("cur: %v\n", string(cur))
			allEqual := true
			for m := 1; m < l; m++ {
				next := s[m]
				if cur != next {
					allEqual = false
					break
				}
			}
			if allEqual {
				// fmt.Printf("i bottom: %v\n", i)
				ans += i
			}

		}
	}
	// fmt.Println("")
	// fmt.Printf("ans2 normal: %v\n", ans)
}

func part2para() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	var numbers []number
	for _, line := range data {
		// fmt.Println(line)
		for v := range strings.SplitSeq(line, ",") {
			n := strings.Split(v, "-")
			numbers = append(numbers, tN(n[0], n[1]))
		}
	}
	// fmt.Printf("numbers: %v\n", numbers)
	// fmt.Println("")
	// goal find repeat twice 55 -> 5,5 ; 1212 -> 12,12 ; 6464 -> 64,64
	// fmt.Println("")
	// fmt.Printf("ans: %v\n", ans)

	numCPU := runtime.NumCPU()

	chunks := splitTasks(numbers, numCPU*2)
	ch := make(chan int, len(chunks))
	for _, chunk := range chunks {
		go ansParaPart2(chunk, ch)
	}

	ans := 0
	for i := 0; i < len(chunks); i++ {
		ans += <-ch
	}
	// fmt.Printf("ans2 para: %v\n", ans)
}

func ansParaPart2(numbers []number, ch chan<- int) {
	ans := 0
	for _, n := range numbers {
		start := n.start
		end := n.end
	outer:
		for i := start; i <= end; i++ {
			s := strconv.Itoa(i)
			l := len(s)
			if l < 2 && l%2 != 0 {
				continue
			}
			// check if string could be splitted into equal pairs
			for j := 1; j < l; j++ {
				if l%j != 0 {
					continue
				}
				isPairEqual := true
				firstS := s[0:j]
				for pair := j; pair < l; pair += j {
					nextS := s[pair : pair+j]
					if firstS != nextS {
						isPairEqual = false
						continue
					}
				}
				if isPairEqual {
					// fmt.Printf("i: %v\n", i)
					ans += i
					continue outer
				}
			}
			cur := s[0]
			// fmt.Printf("cur: %v\n", string(cur))
			allEqual := true
			for m := 1; m < l; m++ {
				next := s[m]
				if cur != next {
					allEqual = false
					break
				}
			}
			if allEqual {
				// fmt.Printf("i bottom: %v\n", i)
				ans += i
			}

		}
	}
	ch <- ans
}
