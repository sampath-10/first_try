import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class LibraryClient {
    public static void main(String[] args) {
        try {
            Library library = (Library) Naming.lookup("rmi://localhost:5000/Library");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. View available books");
                System.out.println("2. Issue a book");
                System.out.println("3. Return a book");
                System.out.println("4. Exit");
                System.out.print("Enter your choice (1-4): ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        List<String> books = library.getAvailableBooks();
                        System.out.println("Available books:");
                        for (String book : books) {
                            System.out.println("- " + book);
                        }
                        break;

                    case 2:
                        System.out.print("Enter the title of the book to issue: ");
                        String issueTitle = scanner.nextLine();
                        if (library.issueBook(issueTitle)) {
                            System.out.println("Book issued successfully!");
                        } else {
                            System.out.println("Book not available.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter the title of the book to return: ");
                        String returnTitle = scanner.nextLine();
                        library.returnBook(returnTitle);
                        System.out.println("Book returned successfully!");
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

