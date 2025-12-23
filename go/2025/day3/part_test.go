package main

import (
	"testing"
)

func BenchmarkNormal(b *testing.B) {
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		part1()
	}

	b.StopTimer()

	nsPerOp := float64(b.Elapsed().Nanoseconds()) / float64(b.N)

	b.ReportMetric(nsPerOp/1e6, "ms/op")
}

func BenchmarkPara(b *testing.B) {
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		part1Para()
	}

	b.StopTimer()

	nsPerOp := float64(b.Elapsed().Nanoseconds()) / float64(b.N)

	b.ReportMetric(nsPerOp/1e6, "ms/op")
}

func BenchmarkParaBufferedCh(b *testing.B) {
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		part1ParaBufferedCh()
	}

	b.StopTimer()

	nsPerOp := float64(b.Elapsed().Nanoseconds()) / float64(b.N)

	b.ReportMetric(nsPerOp/1e6, "ms/op")
}

func BenchmarkParaChunks(b *testing.B) {
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		part1ParaChunks()
	}

	b.StopTimer()

	nsPerOp := float64(b.Elapsed().Nanoseconds()) / float64(b.N)

	b.ReportMetric(nsPerOp/1e6, "ms/op")
}

func BenchmarkParaChunksMutex(b *testing.B) {
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		part1ParaChunks()
	}

	b.StopTimer()

	nsPerOp := float64(b.Elapsed().Nanoseconds()) / float64(b.N)

	b.ReportMetric(nsPerOp/1e6, "ms/op")
}
