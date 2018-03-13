package com.zzx.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include("Perf")
				.exclude("Helloworld")
				.warmupIterations(10)
				.measurementIterations(10)
				.forks(3)
				.build();

		new Runner(opt).run();
	}
}
