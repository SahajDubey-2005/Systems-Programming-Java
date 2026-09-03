<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&customColor_list=15,23,42,30,58,138,79,70,229&height=220&section=header&text=Systems%20Programming%20in%20Java&fontSize=34&fontColor=ffffff&fontAlignY=38&desc=JVM%20Internals%20•%20Memory%20Management%20•%20High-Performance%20Concurrency&descSize=14&descAlignY=62&animation=fadeIn" width="100%" />

[![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![JVM Internals](https://img.shields.io/badge/Architecture-JVM%20Internals-blue?style=for-the-badge&logo=openjdk&logoColor=white)](https://github.com/)
[![Concurrency](https://img.shields.io/badge/Concurrency-Low%20Latency-success?style=for-the-badge)](https://github.com/)
[![Status](https://img.shields.io/badge/Status-Active%20Development-yellow?style=for-the-badge)](https://github.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)](LICENSE)

</div>

---

## 📌 About The Project

A hands-on repository exploring **Systems Programming, JVM internals, and Advanced Concurrency in Java**. This project focuses on understanding how Java works under the hood—optimizing memory management, avoiding garbage collection pressure, and building production-grade concurrent architectures.

---

## 📂 Current Repository Structure

```text
Systems-Programming-Java/
│
└── src/
    ├── concurrency/              # Thread models, Web Servers, and Thread Pools
    │   ├── ThreadPerTaskWebServer.java
    │   └── ThreadPoolWebServer.java
    │
    ├── distributed/              # Distributed systems patterns (Upcoming)
    │
    ├── jvm/                      # JVM Memory models, CompressedOops, and Class Loading
    │   ├── HFTMemoryDemo.java
    │   ├── JVMLifecycleAndMemoryDemo.java
    │   └── MemoryTest.java
    │
    └── matchingengine/           # Low-latency matching engine core (Upcoming)
```
🧠 Progress & Modules Implemented So Far
1. JVM Internals & Memory Architecture (/jvm)
•	Heap vs Stack vs Metaspace: Analyzed thread safety, memory allocation scopes, and failure modes (OutOfMemoryError, StackOverflowError).
•	CompressedOops: Studied 64-bit pointer compression optimizations using 8-bit object alignment and bit-shifting ($\ll 3$) under the 32GB heap limit threshold.
•	Class Loading Lifecycle: Explored Loading, Linking (Verification, Preparation, Resolution), and Initialization phases via custom static block triggers.

3. Concurrency & Network Servers (/concurrency)
•	Thread-Per-Task Anti-Pattern: Implemented a basic web server spawning a new thread per request to benchmark OS thread creation overhead and context-switching penalties.
•	Production-Grade Thread Pools: Built a scalable ThreadPoolExecutor server using bounded queues (ArrayBlockingQueue) and backpressure management (CallerRunsPolicy) to protect server resources.

