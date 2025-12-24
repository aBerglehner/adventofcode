package main

import (
	"testing"
)

func Benchmark_Part1_Normal(b *testing.B) {
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		part1()
	}

	b.StopTimer()

	nsPerOp := float64(b.Elapsed().Nanoseconds()) / float64(b.N)

	b.ReportMetric(nsPerOp/1e6, "ms/op")
}

func Benchmark_Part1_Para(b *testing.B) {
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		part1Para()
	}

	b.StopTimer()

	nsPerOp := float64(b.Elapsed().Nanoseconds()) / float64(b.N)

	b.ReportMetric(nsPerOp/1e6, "ms/op")
}
