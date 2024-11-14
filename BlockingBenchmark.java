package org.example;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BlockingBenchmark {

    @State(Scope.Thread)
    public static class Operands{
        private final int N = 1024;
        private final double[][] A = new double[N][N];
        private final double[][] B = new double[N][N];
        private final double[][] C1 = new double[N][N]; // For naive multiplication
        private final double[][] C2 = new double[N][N];

        @Setup
        public void setup() {
            Random random = new Random();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    A[i][j] = random.nextDouble();
                    B[i][j] = random.nextDouble();
                }
            }
        }

    }

    @Benchmark
    @Fork(value=1,warmups = 1)
    @Warmup(iterations = 3)


    public void multiplication(Operands operands){
        long memoryBefore = getUsedMemory();
        new MatrixMultiplication().execute(operands.A, operands.B,operands.C1,operands.N);
        long memoryAfter = getUsedMemory();
        System.out.println("Memory Usage before: " + memoryBefore / (1024 * 1024) + " MB");
        System.out.println("Memory Usage after: " + memoryAfter / (1024 * 1024) + " MB");
    }

    private long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
