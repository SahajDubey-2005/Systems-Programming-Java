package jvm;

public class HFTMemoryDemo {

    // HFT Low-Latency Rule: Avoid Object allocations on the hot path.
    // Pre-allocate memory once during startup (Warmup phase) to keep Heap footprint flat.
    private static final int ITERATIONS = 5_000_000;
    private static final int[] PRE_ALLOCATED_PRIMITIVE_ARRAY = new int[ITERATIONS];

    public static void main(String[] args) {
        System.out.println("=== HFT & Low-Latency JVM Memory Demonstration ===");
        System.out.println("Max Heap Memory: " + (Runtime.getRuntime().maxMemory() / 1024 / 1024) + " MB\n");

        // Warmup JIT compiler
        runLowLatencyZeroGC();

        // Benchmark comparison
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
        System.out.println("Interview Key Takeaway: Primitive arrays use contiguous memory,");
        System.out.println("maximizing L1/L2 CPU Cache hits (>95%) and producing ZERO Garbage!");
    }

    // ❌ BAD PRACTICE IN HFT: Triggers Object Allocations, Boxing, and GC Pressure
    public static void runHighAllocationGarbagePath() {
        // ArrayList stores pointers to Integer objects scattered across the Heap (Pointer Chasing)
        java.util.List<Integer> list = new java.util.java.util.ArrayList<>(); // (or java.util.ArrayList)
        for (int i = 0; i < ITERATIONS; i++) {
            list.add(i); // Auto-boxing: int 'i' is converted to new Integer(i) on the Heap!
        }
    }

    // ✅ HFT GOLD STANDARD: Zero-Allocation & Contiguous Memory
    public static void runLowLatencyZeroGC() {
        // Direct primitive mutation inside a pre-allocated array (Contiguous RAM layout)
        for (int i = 0; i < ITERATIONS; i++) {
            PRE_ALLOCATED_PRIMITIVE_ARRAY[i] = i; // No object creation, zero heap pressure, zero GC!
        }
    }
}