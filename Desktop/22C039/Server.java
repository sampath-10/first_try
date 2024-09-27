import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {

    public static void main(String[] args) {
        int port = 12345; // Port number for the server

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                System.out.println("Waiting for a client connection...");

                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("Client connected from: " + clientSocket.getInetAddress());

                    // Read the command from the client
                    String command = in.readLine();
                    System.out.println("Received command from client: " + command);

                    // Process the command
                    if (command.equalsIgnoreCase("ECHO")) {
                        String message = in.readLine();
                        System.out.println("Echoing message: " + message);
                        out.println("ECHO: " + message);
                    } else if (command.equalsIgnoreCase("PING")) {
                        String hostname = in.readLine();
                        System.out.println("Pinging hostname: " + hostname);
                        String pingResult = executePing(hostname);
                        out.println("PING RESULT:\n" + pingResult);
                    } else if (command.equalsIgnoreCase("TIME")) {
                        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        System.out.println("Sending current time: " + currentTime);
                        out.println("TIME: " + currentTime);
                    } else {
                        System.out.println("Unknown command received");
                        out.println("UNKNOWN COMMAND");
                    }
                } catch (IOException e) {
                    System.err.println("Error handling client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    private static String executePing(String hostname) {
        StringBuilder result = new StringBuilder();
        ProcessBuilder processBuilder = new ProcessBuilder("ping", hostname);
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Read the output of the ping command
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }

            // Wait for the process to complete
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            result.append("Error executing ping command: ").append(e.getMessage());
        }
        return result.toString();
    }
}