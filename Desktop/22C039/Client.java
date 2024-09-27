import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        String serverAddress = "localhost"; // Address of the server
        int port = 12345; // Port number used by the server

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Enter command (ECHO, PING, TIME) or EXIT to quit:");

            String command;
            while ((command = userInput.readLine()) != null) {
                if (command.equalsIgnoreCase("EXIT")) {
                    break;
                }

                out.println(command);

                // If command is ECHO, we need to send a message as well
                if (command.equalsIgnoreCase("ECHO")) {
                    System.out.println("Enter message to echo:");
                    String message = userInput.readLine();
                    out.println(message);
                } else if (command.equalsIgnoreCase("PING")) {
                    System.out.println("Enter hostname to ping:");
                    String hostname = userInput.readLine();
                    out.println(hostname);
                }

                // Read and print the server's response
                String responseLine;
                while ((responseLine = in.readLine()) != null) {
                    System.out.println("Server response: " + responseLine);
                    if (!in.ready()) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}