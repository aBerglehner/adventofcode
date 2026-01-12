package main

import (
	"fmt"
	"log"
	"path/filepath"
	"runtime"
	"strings"

	"github.com/alex/adventofcode/go/utils"
)

func main() {
	part2()
}

// start at "YOU"
// find all paths that will follow to "OUT"
func part1() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	adj := make(map[string][]string)
	for _, line := range data {
		fmt.Println(line)
		var strs []string
		for v := range strings.SplitSeq(line, ":") {
			for s := range strings.SplitSeq(v, " ") {
				if s != "" {
					strs = append(strs, strings.Trim(s, " "))
				}
			}
		}
		l := strs[0]
		rest := strs[1:]

		adj[l] = rest
	}
	fmt.Println()
	fmt.Printf("adj: %v\n", adj)
	fmt.Println()
	ans := dfs("you", adj, 0)
	fmt.Println()
	fmt.Printf("ans: %v\n", ans)
}

func dfs(cur string, adj map[string][]string, count int) int {
	// fmt.Printf("cur: %v\n", cur)
	if cur == "out" {
		fmt.Printf("cur: %v\n", cur)
		return 1
	}

	for _, child := range adj[cur] {
		count += dfs(child, adj, 0)
	}

	return count
}

func part2() {
	_, filename, _, _ := runtime.Caller(0)
	baseDir := filepath.Dir(filename)

	inputPath := filepath.Join(baseDir, "input")
	data, err := utils.ReadLines(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	adj := make(map[string][]string)
	for _, line := range data {
		// fmt.Println(line)
		var strs []string
		for v := range strings.SplitSeq(line, ":") {
			for s := range strings.SplitSeq(v, " ") {
				if s != "" {
					strs = append(strs, strings.Trim(s, " "))
				}
			}
		}
		l := strs[0]
		rest := strs[1:]

		adj[l] = rest
	}
	fmt.Println()
	// fmt.Printf("adj: %v\n", adj)
	fmt.Println("waiting")
	fmt.Println()

	memo := make(map[State]int)
	visited := make(map[string]bool)

	result := dfs2("svr", adj, false, false, memo, visited)
	fmt.Printf("result: %v\n", result)
}

type State struct {
	Cur     string
	SeenDAC bool
	SeenFFT bool
}

func dfs2(
	cur string,
	adj map[string][]string,
	seenDAC bool,
	seenFFT bool,
	memo map[State]int,
	visited map[string]bool,
) int {
	if cur == "dac" {
		seenDAC = true
	}
	if cur == "fft" {
		seenFFT = true
	}

	state := State{cur, seenDAC, seenFFT}
	if v, ok := memo[state]; ok {
		return v
	}

	if visited[cur] {
		return 0
	}
	visited[cur] = true
	defer delete(visited, cur)

	if cur == "out" {
		if seenDAC && seenFFT {
			return 1
		}
		return 0
	}

	count := 0
	for _, next := range adj[cur] {
		count += dfs2(next, adj, seenDAC, seenFFT, memo, visited)
	}

	memo[state] = count
	return count
}
