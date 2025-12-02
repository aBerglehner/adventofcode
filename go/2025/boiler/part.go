package main

import (
	"fmt"
	"log"

	"github.com/alex/adventofcode/go/utils"
)

func main() {
	part1()
}

func part1() {
	data, err := utils.ReadLines("test")
	if err != nil {
		log.Fatal(err)
	}

	for _, line := range data {
		fmt.Println(line)
	}
}
