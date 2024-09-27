import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            // Create an instance of the Calculator implementation
            CalculatorImpl calculator = new CalculatorImpl();

            // Create and export the registry on the default port (1099)
            LocateRegistry.createRegistry(1099);

            // Bind the remote object to the RMI registry with the name "CalculatorService"
            Naming.rebind("CalculatorService", calculator);

            System.out.println("CalculatorServer is ready.");
        } catch (Exception e) {
            System.err.println("CalculatorServer exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
