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
	part1ParaChunksMutex()
	part1()
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
