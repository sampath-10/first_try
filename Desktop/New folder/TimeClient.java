import java.io.*;
import java.net.*;

public class TimeClient {
    public static void main(String[] args) {
        // Server IP address (localhost) and port number
        String hostname = "127.0.0.1";
        int port = 12345;

        try (Socket socket = new Socket(hostname, port)) {
            // Read the time sent by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String currentTime = in.readLine();

            // Display the received time
            System.out.println("Current time from server: " + currentTime);
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}