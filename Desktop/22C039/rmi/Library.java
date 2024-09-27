import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Library extends Remote {
    List<String> getAvailableBooks() throws RemoteException;
    boolean issueBook(String title) throws RemoteException;
    boolean returnBook(String title) throws RemoteException;
}
