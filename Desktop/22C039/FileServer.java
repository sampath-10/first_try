import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileServer {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the file to save: ");
        String outputFile = scanner.nextLine();

        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Waiting for client connection...");
        Socket socket = serverSocket.accept();
        InputStream input = socket.getInputStream();
        FileOutputStream fileOutput = new FileOutputStream(outputFile);
        
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            fileOutput.write(buffer, 0, bytesRead);
        }
        fileOutput.close();
        socket.close();
        serverSocket.close();
        System.out.println("File received and saved as: " + outputFile);
    }
}