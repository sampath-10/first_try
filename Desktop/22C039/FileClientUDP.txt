import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileClientUDP {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the file to send: ");
        String inputFile = scanner.nextLine();

        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        byte[] buffer = new byte[1024];

        FileInputStream fileInput = new FileInputStream(inputFile);
        int bytesRead;

        while ((bytesRead = fileInput.read(buffer)) != -1) {
            DatagramPacket packet = new DatagramPacket(buffer, bytesRead, address, 8080);
            socket.send(packet);
        }

        // Send an empty packet to indicate the end of the file
        DatagramPacket endPacket = new DatagramPacket(new byte[0], 0, address, 8080);
        socket.send(endPacket);

        fileInput.close();
        socket.close();
        System.out.println("File sent: " + inputFile);
    }
}