import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class LibraryImpl extends UnicastRemoteObject implements Library {
    private List<String> books;

    protected LibraryImpl() throws RemoteException {
        books = new ArrayList<>();
        books.add("Java Basics");
        books.add("Data Structures");
        books.add("Algorithms");
    }

    public List<String> getAvailableBooks() throws RemoteException {
        return books;
    }

    public boolean issueBook(String title) throws RemoteException {
        return books.remove(title);
    }

    public boolean returnBook(String title) throws RemoteException {
        return books.add(title);
    }
}

