// Package utils
package utils

import (
	"bufio"
	"fmt"
	"os"
)

func ReadLines(filepath string) ([]string, error) {
	file, err := os.Open(filepath)
	if err != nil {
		return nil, err
	}
	defer file.Close()

	var lines []string
	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		lines = append(lines, scanner.Text())
	}

	return lines, scanner.Err()
}

func PrintMatrix[T any](input []T, name ...string) {
	var matrixName string
	if len(name) != 0 {
		matrixName = name[0]
	}
	fmt.Println("---------------------------------")
	fmt.Println("matrix: ", matrixName)
	for _, row := range input {
		fmt.Println(row)
	}
	fmt.Println("---------------------------------")
}
