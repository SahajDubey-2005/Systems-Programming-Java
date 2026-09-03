package jvm;

public class JVMLifecycleAndMemoryDemo {

    // Static variable for demonstrating Class Loading Preparation & Initialization phases
    public static int staticCounter;

    static {
        // This block runs during the "Initialization" phase of Class Loading
        staticCounter = 100;
        System.out.println("[Classloader Phase] JVMLifecycleAndMemoryDemo Initialized! staticCounter = " + staticCounter);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("=== JVM Memory & Class Loading Lifecycle Demo ===\n");

        // 1. Inspecting Heap Memory (CompressedOops context)
        long maxHeapBytes = Runtime.getRuntime().maxMemory();
        double maxHeapGB = maxHeapBytes / (1024.0 * 1024 * 1024);
        System.out.printf("Current Max Heap: %.2f GB\n", maxHeapGB);
        if (maxHeapGB <= 32.0) {
            System.out.println("-> CompressedOops Status: Likely ENABLED (Heap <= 32GB, pointers are 4 bytes instead of 8 bytes).\n");
        } else {
            System.out.println("-> CompressedOops Status: DISABLED (Heap > 32GB, pointers expanded to 8 bytes).\n");
        }

        // 2. Demonstrating Class Loading Lifecycle Trigger
        System.out.println("Triggering explicit Class Loading via Class.forName()...");
        
        // This triggers Loading and Linking, but initialization can be controlled or forced.
        Class<?> clazz = Class.forName("jvm.JVMLifecycleAndMemoryDemo");
        System.out.println("Loaded Class Name: " + clazz.getName());
        
        System.out.println("\nDemo completed successfully. Check Metaspace and Stack/Heap allocations under the hood!");
    }
}