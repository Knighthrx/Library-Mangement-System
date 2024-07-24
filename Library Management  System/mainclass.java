import java.util.List;
import java.util.Scanner;

public class mainclass {
    public static void main(String[] args) {
        library library = new library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. Add Member");
            System.out.println("5. Remove Member");
            System.out.println("6. Search Member");
            System.out.println("7. Borrow Book");
            System.out.println("8. Return Book");
            System.out.println("9. Check Inventory");
            System.out.println("10. Notify Low Stock");
            System.out.println("11. Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    book book = new book(title, author, isbn, quantity);
                    library.addBook(book);
                    System.out.println("Book added successfully!");
                    break;

                case "2":
                    System.out.print("Enter book ISBN to remove: ");
                    isbn = scanner.nextLine();
                    library.removeBook(isbn);
                    System.out.println("Book removed successfully!");
                    break;

                case "3":
                    System.out.print("Enter book title to search: ");
                    title = scanner.nextLine();
                    List<book> books = library.searchBook(title, null, null);
                    for (book b : books) {
                        System.out.println("Title: " + b.getTitle() + ", Author: " + b.getAuthor() + ", ISBN: " + b.getIsbn() + ", Quantity: " + b.getQuantity());
                    }
                    break;

                case "4":
                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Enter member contact: ");
                    String contact = scanner.nextLine();
                    Member member = new Member(name, memberId, contact);
                    library.addMember(member);
                    System.out.println("Member added successfully!");
                    break;

                case "5":
                    System.out.print("Enter member ID to remove: ");
                    memberId = scanner.nextLine();
                    library.removeMember(memberId);
                    System.out.println("Member removed successfully!");
                    break;

                case "6":
                    System.out.print("Enter member name to search: ");
                    name = scanner.nextLine();
                    List<Member> members = library.searchMember(name, null);
                    for (Member m : members) {
                        System.out.println("Name: " + m.getName() + ", Member ID: " + m.getMemberId() + ", Contact: " + m.getContact());
                    }
                    break;

                case "7":
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextLine();
                    System.out.print("Enter book ISBN to borrow: ");
                    isbn = scanner.nextLine();
                    if (library.borrowBook(memberId, isbn)) {
                        System.out.println("Book borrowed successfully!");
                    } else {
                        System.out.println("Failed to borrow book!");
                    }
                    break;

                case "8":
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextLine();
                    System.out.print("Enter book ISBN to return: ");
                    isbn = scanner.nextLine();
                    if (library.returnBook(memberId, isbn)) {
                        System.out.println("Book returned successfully!");
                    } else {
                        System.out.println("Failed to return book!");
                    }
                    break;

                case "9":
                    System.out.print("Enter book ISBN to check inventory: ");
                    isbn = scanner.nextLine();
                    int availableQuantity = library.checkInventory(isbn);
                    System.out.println("Quantity available: " + availableQuantity);
                    break;

                case "10":
                    System.out.print("Enter low stock threshold: ");
                    int threshold = Integer.parseInt(scanner.nextLine());
                    List<book> lowStockBooks = library.notifyLowStock(threshold);
                    for (book b : lowStockBooks) {
                        System.out.println("Title: " + b.getTitle() + ", Author: " + b.getAuthor() + ", ISBN: " + b.getIsbn() + ", Quantity: " + b.getQuantity());
                    }
                    break;

                case "11":
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
