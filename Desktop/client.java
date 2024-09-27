import java.io.*;
import java.net.*;

public class client{
    public static void main(String[] args) {
        String host = "127.0.0.1"; 
        int port = 12345;

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("Server is reachable.");
            String response = in.readLine();
            System.out.println("Received: " + response);
        } catch (Exception e) {
            System.err.println("Server not reachable: " );
         } //catch (IOException e) {
        //     System.err.println("Error connecting to server: " + e.getMessage());
        // }
    }
}