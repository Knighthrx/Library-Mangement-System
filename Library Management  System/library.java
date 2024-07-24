import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class library {
    private HashMap<String, book> books;
    private HashMap<String, Member> members;
    private HashMap<String, List<book>> borrowedBooks;

    public library() {
        books = new HashMap<>();
        members = new HashMap<>();
        borrowedBooks = new HashMap<>();
    }

    // Book Management
    public void addBook(book book) {
        if (books.containsKey(book.getIsbn())) {
            books.get(book.getIsbn()).setQuantity(books.get(book.getIsbn()).getQuantity() + book.getQuantity());
        } else {
            books.put(book.getIsbn(), book);
        }
    }

    public void removeBook(String isbn) {
        books.remove(isbn);
    }

    public List<book> searchBook(String title, String author, String isbn) {
        List<book> results = new ArrayList<>();
        for (book book : books.values()) {
            if ((title != null && book.getTitle().contains(title)) ||
                (author != null && book.getAuthor().contains(author)) ||
                (isbn != null && book.getIsbn().equals(isbn))) {
                results.add(book);
            }
        }
        return results;
    }

    // Member Management
    public void addMember(Member member) {
        members.put(member.getMemberId(), member);
    }

    public void removeMember(String memberId) {
        members.remove(memberId);
    }

    public List<Member> searchMember(String name, String memberId) {
        List<Member> results = new ArrayList<>();
        for (Member member : members.values()) {
            if ((name != null && member.getName().contains(name)) ||
                (memberId != null && member.getMemberId().equals(memberId))) {
                results.add(member);
            }
        }
        return results;
    }

    // Borrowing System
    public boolean borrowBook(String memberId, String isbn) {
        if (books.containsKey(isbn) && books.get(isbn).getQuantity() > 0) {
            if (!borrowedBooks.containsKey(memberId)) {
                borrowedBooks.put(memberId, new ArrayList<>());
            }
            borrowedBooks.get(memberId).add(books.get(isbn));
            books.get(isbn).setQuantity(books.get(isbn).getQuantity() - 1);
            return true;
        }
        return false;
    }

    public boolean returnBook(String memberId, String isbn) {
        if (borrowedBooks.containsKey(memberId)) {
            List<book> memberBooks = borrowedBooks.get(memberId);
            for (book book : memberBooks) {
                if (book.getIsbn().equals(isbn)) {
                    memberBooks.remove(book);
                    books.get(isbn).setQuantity(books.get(isbn).getQuantity() + 1);
                    return true;
                }
            }
        }
        return false;
    }

    // Inventory Management
    public int checkInventory(String isbn) {
        if (books.containsKey(isbn)) {
            return books.get(isbn).getQuantity();
        }
        return 0;
    }

    public List<book> notifyLowStock(int threshold) {
        List<book> lowStockBooks = new ArrayList<>();
        for (book book : books.values()) {
            if (book.getQuantity() < threshold) {
                lowStockBooks.add(book);
            }
        }
        return lowStockBooks;
    }
}
