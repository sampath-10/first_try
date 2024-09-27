import java.rmi.Naming;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            // Look up the remote object (CalculatorService) from the RMI registry
            Calculator calculator = (Calculator) Naming.lookup("rmi://localhost/CalculatorService");

            // Perform calculations using the remote object
            double a = 10.0;
            double b = 5.0;

            System.out.println("Addition: " + calculator.add(a, b));
            System.out.println("Subtraction: " + calculator.subtract(a, b));
            System.out.println("Multiplication: " + calculator.multiply(a, b));
            System.out.println("Division: " + calculator.divide(a, b));
        } catch (Exception e) {
            System.err.println("CalculatorClient exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
