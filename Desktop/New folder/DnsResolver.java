import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class DnsResolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Detecting system DNS servers...");
            detectAndDisplayDnsServers();
            
            System.out.print("Enter hostname to resolve: ");
            String hostname = scanner.nextLine();
            resolveAndDisplayIp(hostname);
        } finally {
            scanner.close();
        }
    }

    private static void detectAndDisplayDnsServers() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("ipconfig", "/all");
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            System.out.println("Detected DNS Servers:");
            StringBuilder dnsServersBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.contains("DNS Servers")) {
                    String[] parts = line.split(":");
                    if (parts.length > 1) {
                        dnsServersBuilder.append(parts[1].trim()).append(" ");
                    }
                } else if (line.trim().matches("^\\s*\\d+\\.\\d+\\.\\d+\\.\\d+")) {
                    dnsServersBuilder.append(line.trim()).append(" ");
                }
            }

            reader.close();
            String[] dnsServers = dnsServersBuilder.toString().split("\\s+");
            for (String dnsServer : dnsServers) {
                if (!dnsServer.isEmpty()) {
                    try {
                        InetAddress[] addresses = InetAddress.getAllByName(dnsServer);
                        for (InetAddress address : addresses) {
                            String serverHostname = address.getHostName();
                            System.out.printf("DNS Server Name: %s%n", serverHostname);
                            System.out.printf("DNS Server IP: %s%n", address.getHostAddress());
                        }
                    } catch (UnknownHostException e) {
                        System.out.printf("Unable to resolve DNS server: %s%n", dnsServer);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error executing ipconfig command.");
            e.printStackTrace();
        }
    }

    private static void resolveAndDisplayIp(String hostname) {
        try {
            InetAddress[] addresses = InetAddress.getAllByName(hostname);
            System.out.println("Hostname: " + hostname);
            for (InetAddress address : addresses) {
                System.out.println("IP Address: " + address.getHostAddress());
            }
        } catch (UnknownHostException e) {
            System.out.println("Unable to resolve hostname: " + hostname);
            e.printStackTrace();
        }
    }
}
