package jvm;

import java.util.ArrayList;
import java.util.List;

public class HFTMemoryDemo {

    private static final int ITERATIONS = 5_000_000;
    private static final int[] PRE_ALLOCATED_PRIMITIVE_ARRAY = new int[ITERATIONS];

    public static void main(String[] args) {
        System.out.println("=== HFT & Low-Latency JVM Memory Demonstration ===");
        System.out.println("Max Heap Memory: " + (Runtime.getRuntime().maxMemory() / 1024 / 1024) + " MB\n");

        runLowLatencyZeroGC();

        long startTime = System.nanoTime();
        runHighAllocationGarbagePath();
        long highAllocTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        runLowLatencyZeroGC();
        long zeroGCTime = System.nanoTime() - startTime;

        System.out.println("--------------------------------------------------");
        System.out.printf("❌ High-Allocation Path Time (ArrayList/Boxing): %.2f ms\n", highAllocTime / 1_000_000.0);
        System.out.printf("✅ Zero-GC / Low-Latency Path Time (Primitive[]): %.2f ms\n", zeroGCTime / 1_000_000.0);
        System.out.println("--------------------------------------------------");
    }

    public static void runHighAllocationGarbagePath() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < ITERATIONS; i++) {
            list.add(i);
        }
    }

    public static void runLowLatencyZeroGC() {
        for (int i = 0; i < ITERATIONS; i++) {
            PRE_ALLOCATED_PRIMITIVE_ARRAY[i] = i;
        }
    }
}