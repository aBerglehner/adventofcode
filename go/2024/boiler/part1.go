package main

import (
	"fmt"
	"log"

	"github.com/alex/adventofcode/go/utils"
)

func main() {
	lines, err := utils.ReadLines("test_input.txt")
	if err != nil {
		log.Fatal(err)
	}

	for _, line := range lines {
		fmt.Println(line)
	}
}
