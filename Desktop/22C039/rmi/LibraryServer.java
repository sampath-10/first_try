import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LibraryServer {
    public static void main(String[] args) {
        try {
            Library library = new LibraryImpl();
            Registry registry = LocateRegistry.createRegistry(5000);
            registry.rebind("Library", library);
            System.out.println("Server is ready on port 5000.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

