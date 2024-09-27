import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Exp8 {

    public static void main(String[] args) {
        int port = 12345; // Port number for the server

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    // Read the command from the client
                    String command = in.readLine();

                    // Process the command
                    if (command.equalsIgnoreCase("ECHO")) {
                        String message = in.readLine();
                        out.println("ECHO: " + message);
                    } else if (command.equalsIgnoreCase("PING")) {
                        out.println("PONG");
                    } else if (command.equalsIgnoreCase("TIME")) {
                        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        out.println("TIME: " + currentTime);
                    } else {
                        out.println("UNKNOWN COMMAND");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}

