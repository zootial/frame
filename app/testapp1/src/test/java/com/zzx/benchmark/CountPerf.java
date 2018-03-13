package com.zzx.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

public class CountPerf {

	@Benchmark
//	@Fork(value = 1, warmups = 2)
    @BenchmarkMode(Mode.Throughput)
    public void count1() {
        for (int i = 0; i < 1_000_000; i++) {

        }
    }
	
	@Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void count2() {
        for (int i = 1_000_000; i > 0; i--) {

        }
    }
}
