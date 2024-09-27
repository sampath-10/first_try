import java.io.*;
import java.net.*;

public class TimeClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            // Read the server's response
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = in.readLine();

            // Display the server's response
            System.out.println("Server says: " + serverResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
