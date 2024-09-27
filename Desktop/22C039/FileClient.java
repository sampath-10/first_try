import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileClient {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the file to send: ");
        String inputFile = scanner.nextLine();

        Socket socket = new Socket("localhost", 8080);
        FileInputStream fileInput = new FileInputStream(inputFile);
        OutputStream output = socket.getOutputStream();
        
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileInput.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        fileInput.close();
        socket.close();
        System.out.println("File sent: " + inputFile);
    }
}