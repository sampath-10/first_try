import java.net.InetAddress;

public class InetAddressExample {

    public static void main(String[] args) {
        try {
            String hostname = "DESKTOP-RPQETUT"; 
            InetAddress[] addresses = InetAddress.getAllByName(hostname);
            for (InetAddress addr : addresses) {
                System.out.println("IP Address of " + hostname + ": " + addr.getHostAddress());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
