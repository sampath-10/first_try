import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeServer {
    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

                    System.out.println("Connection from " + clientSocket.getInetAddress());

                    Thread clientListener = new Thread(() -> {
                        String clientMessage;
                        try {
                            while ((clientMessage = in.readLine()) != null) {
                                System.out.println("Client: " + clientMessage);
                                if (clientMessage.equalsIgnoreCase("time")) {
                                    String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                    out.println("Current date and time: " + currentTime);
                                } else {
                                    out.println("Server received: " + clientMessage);
                                }
                            }
                        } catch (IOException e) {
                            System.err.println("Error reading from client: " + e.getMessage());
                        }
                    });
                    clientListener.start();

                    String serverMessage;
                    while ((serverMessage = stdIn.readLine()) != null) {
                        out.println("Server: " + serverMessage);
                    }
                } catch (IOException e) {
                    System.err.println("Error handling client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }
}