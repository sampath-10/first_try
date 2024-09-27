import java.io.*;

public class NextHopRouterInfo {
    public static void main(String[] args) {
        try {
            // Execute shell command
            Process process = Runtime.getRuntime().exec("arp -n");

            // Read the command output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("172.17.13.153")) {  // Replace with your router's IP address
                    String[] parts = line.split("\\s+");
                    if (parts.length >= 3) {
                        String ipAddress = parts[0];
                        String macAddress = parts[2];
                        System.out.println("Router IP Address: " + ipAddress);
                        System.out.println("Router MAC Address: " + macAddress);
                        break;
                    }
                }
            }

            // Close the reader and wait for the process to terminate
            reader.close();
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            System.out.println("Error executing command: " + e.getMessage());
        }
    }
}
