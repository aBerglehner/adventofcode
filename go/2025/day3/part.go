package main

import (
	"log"
	"path/filepath"
	"runtime"
	"strconv"
	"sync"

	"github.com/alex/adventofcode/go/utils"
)

func main() {
	part2()
	part2Para()
}

func part1() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	ans := 0
	for _, line := range data {
		// fmt.Println(line)
		ans += getHighest(line)
	}
	// fmt.Printf("ans: %v\n", ans)
}

func getHighest(line string) int {
	highest := 0
	highestAfter := 0
	for i, v := range line {
		n, _ := strconv.Atoi(string(v))
		if n > highest && i+1 != len(line) {
			highest = n
			highestAfter = 0
		} else if n > highestAfter {
			highestAfter = n
		}
	}
	return highest*10 + highestAfter
}

func part1Para() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	var wg sync.WaitGroup
	ansStream := make(chan int)
	for _, line := range data {
		wg.Add(1)
		// fmt.Println(line)
		go getHighestPara(line, ansStream, &wg)
	}
	go func() {
		wg.Wait()
		close(ansStream)
	}()

	ans := 0
	for n := range ansStream {
		ans += n
	}
	// fmt.Printf("ans: %v\n", ans)
}

func getHighestPara(line string, ch chan<- int, wg *sync.WaitGroup) {
	defer wg.Done()
	highest := 0
	highestAfter := 0
	for i, v := range line {
		n, _ := strconv.Atoi(string(v))
		if n > highest && i+1 != len(line) {
			highest = n
			highestAfter = 0
		} else if n > highestAfter {
			highestAfter = n
		}
	}
	ch <- highest*10 + highestAfter
}

func part1ParaBufferedCh() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	var wg sync.WaitGroup
	ansStream := make(chan int, len(data))
	for _, line := range data {
		wg.Add(1)
		// fmt.Println(line)
		go getHighestPara(line, ansStream, &wg)
	}
	wg.Wait()
	close(ansStream)

	ans := 0
	for n := range ansStream {
		ans += n
	}
	// fmt.Printf("ans: %v\n", ans)
}

func part1ParaChunks() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	tasks := utils.SplitTasks(data, runtime.NumCPU())
	var wg sync.WaitGroup
	ansStream := make(chan int, len(tasks))
	for _, lines := range tasks {
		wg.Add(1)
		go batchPara(lines, &wg, ansStream)
	}
	wg.Wait()
	close(ansStream)

	ans := 0
	for n := range ansStream {
		ans += n
	}
	// fmt.Printf("ans: %v\n", ans)
}

func batchPara(data []string, wg *sync.WaitGroup, ch chan<- int) {
	defer wg.Done()
	ans := 0
	for _, line := range data {
		ans += getHighest(line)
	}
	ch <- ans
}

func part1ParaChunksMutex() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	tasks := utils.SplitTasks(data, runtime.NumCPU())
	var wg sync.WaitGroup
	var lock sync.RWMutex
	ans := 0
	for _, lines := range tasks {
		wg.Add(1)
		go batchParaMutex(lines, &wg, &ans, &lock)
	}
	wg.Wait()

	// fmt.Printf("ans: %v\n", ans)
}

func batchParaMutex(data []string, wg *sync.WaitGroup, ans *int, lock *sync.RWMutex) {
	defer wg.Done()
	res := 0
	for _, line := range data {
		res += getHighest(line)
	}
	lock.Lock()
	*ans += res
	lock.Unlock()
}

func part2() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	ans := 0
	for _, line := range data {
		highest := findHighestNum(line)
		ans += highest
	}
	// fmt.Printf("ans: %v\n", ans)
}

func findHighestNum(line string) int {
	var res []int
	index := -1
	var cur int
	for i := 12; i > 0; i-- {
		line = line[index+1:]
		cur, index = getHighestPart2(line, i)
		res = append(res, cur)
	}
	return createNum(res)
}

func createNum(res []int) int {
	ans := 0
	for _, v := range res {
		ans = ans*10 + v
	}
	return ans
}

func getHighestPart2(line string, numberNeeded int) (highest int, index int) {
	for i, v := range line {
		n, _ := strconv.Atoi(string(v))
		if n > highest {
			index = i
			highest = n
		}
		if len(line)-i == numberNeeded {
			break
		}
	}
	return highest, index
}

func part2Para() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	tasks := utils.SplitTasks(data, runtime.NumCPU())
	var wg sync.WaitGroup
	ch := make(chan int)
	for _, task := range tasks {
		wg.Add(1)
		go batchParaPart2(task, ch, &wg)

	}
	go func() {
		wg.Wait()
		close(ch)
	}()
	ans := 0
	for c := range ch {
		ans += c
	}
	// fmt.Printf("ans: %v\n", ans)
}

func batchParaPart2(data []string, ch chan<- int, wg *sync.WaitGroup) {
	defer wg.Done()
	ans := 0
	for _, line := range data {
		highest := findHighestNum(line)
		ans += highest
	}
	ch <- ans
}
