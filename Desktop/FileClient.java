//FILECLIENT
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileClient {
    public static void main(String[] args) {
       
        try (Socket socket = new Socket("localhost", 5000)) {
            System.out.println("Connected to the server.");

           
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the file name you want from the server:");
            String requestedFileName = sc.nextLine();

           
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(requestedFileName);

           
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = reader.readLine();

            if ("File found".equals(serverResponse)) {
               
                System.out.println("File found on server. Enter the name to save as:");
                String saveAsFilename = sc.nextLine();

               
                InputStream is = socket.getInputStream();
                FileOutputStream fos = new FileOutputStream(saveAsFilename);
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }

               
                bos.close();
                System.out.println("File received and saved as: " + saveAsFilename);
            } else {
                System.out.println("The requested file was not found on the server.");
            }

           
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

