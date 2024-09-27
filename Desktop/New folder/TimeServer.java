import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {
    public static void main(String[] args) {
        // Port number where the server will listen for client connections
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Time server is listening on port " + port);

            while (true) {
                // Wait for a client to connect
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to client " + clientSocket.getInetAddress());

                // Get the current time
                String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                // Send the current time to the client
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(currentTime);

                // Close the connection with the client
                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}