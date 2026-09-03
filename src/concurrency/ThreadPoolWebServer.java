import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolWebServer {

    // Thread Pool Configuration for High Performance & Bounded Resource Control
    private static final int CORE_POOL_SIZE = 4;
    private static final int MAX_POOL_SIZE = 10;
    private static final long KEEP_ALIVE_TIME = 60L;
    private static final int QUEUE_CAPACITY = 100; // Bounded queue to prevent memory explosion

    public static void main(String[] args) throws IOException {
        System.out.println("=== Production-Grade Thread Pool Web Server Initialized ===");
        
        ServerSocket socket = new ServerSocket(8081);

        // Using ThreadPoolExecutor instead of spawning a new Thread per request
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(QUEUE_CAPACITY),
            new ThreadPoolExecutor.CallerRunsPolicy() // Backpressure handling policy
        );

        while (true) {
            Socket connection = socket.accept();
            
            // ✅ Reusing threads from the pool instead of creating new ones
            executor.submit(() -> {
                handleRequest(connection);
            });
        }
    }

    private static void handleRequest(Socket connection) {
        try {
            System.out.println("Handled by thread: " + Thread.currentThread().getName() + 
                               " | Client: " + connection.getRemoteSocketAddress());
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}