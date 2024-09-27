import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileServerUDP {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the file to save: ");
        String outputFile = scanner.nextLine();

        DatagramSocket socket = new DatagramSocket(8080);
        byte[] buffer = new byte[1024];
        FileOutputStream fileOutput = new FileOutputStream(outputFile);

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        System.out.println("Waiting for file...");

        while (true) {
            socket.receive(packet);
            int bytesRead = packet.getLength();
            if (bytesRead == 0) break; // Empty packet indicates end of file
            fileOutput.write(buffer, 0, bytesRead);
        }

        fileOutput.close();
        socket.close();
        System.out.println("File received and saved as: " + outputFile);
    }
}
