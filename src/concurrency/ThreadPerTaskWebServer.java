
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadPerTaskWebServer {

    public static void main(String[] args) throws IOException {
        System.out.println("=== Thread-Per-Task Web Server Initialized ===");
        ServerSocket socket = new ServerSocket(8081);
        
        while (true) {
            Socket connection = socket.accept();
            
            // ❌ HFT / Systems Anti-Pattern: Spawning a new thread per request
            // Causes high OS overhead, memory pressure from thread stacks, and expensive context switching.
            Runnable task = () -> {
                handleRequest(connection);
            };
            new Thread(task).start();
        }
    }

    private static void handleRequest(Socket connection) {
        try {
            // Simulate low-latency or request processing logic
            System.out.println("Handling request from: " + connection.getRemoteSocketAddress());
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}