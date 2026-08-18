import java.util.ArrayList;
import java.util.List;

public class Library {
    // 1. Two main collections for internal record keeping
    private ArrayList<Book> books;
    private ArrayList<Member> members;

    // 2. Constructor - Initializes empty lists ready for records
    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    // 3. Adding & Registering with Duplicate ID Enforcement
    public boolean addBook(Book newBook) {
        if (newBook == null || findBookById(newBook.getId()) != null) {
            return false; // Rejects null or duplicate Book IDs
        }
        books.add(newBook);
        return true;
    }

    public boolean registerMember(Member newMember) {
        if (newMember == null || findMemberById(newMember.getMemberId()) != null) {
            return false; // Rejects null or duplicate Member IDs
        }
        members.add(newMember);
        return true;
    }

    // 4. Helper Search Methods (Return null if no match found)
    public Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(bookId)) {
                return book;
            }
        }
        return null;
    }

    public Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equalsIgnoreCase(memberId)) {
                return member;
            }
        }
        return null;
    }

    public List<Book> searchByTitle(String keyword) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    // 5. Core Borrowing Logic (Orchestrator)
    public boolean borrowBook(String bookId, String memberId) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        // System Checks: both entities must exist and the book must be available
        if (book == null || member == null || book.isBorrowed()) {
            return false;
        }

        // Delegate state changes to the respective domain objects
        boolean statusChanged = book.borrowBook(); 
        if (statusChanged) {
            member.addBook(book);
            return true;
        }

        return false;
    }

    // 6. Core Returning Logic
    public boolean returnBook(String bookId, String memberId) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        // System Checks: both must exist and the book must currently be borrowed
        if (book == null || member == null || !book.isBorrowed()) {
            return false;
        }

        // Delegate state changes to domain objects
        boolean statusChanged = book.returnBook();
        if (statusChanged) {
            member.removeBook(book);
            return true;
        }

        return false;
    }

    // 7. Display Methods
    public void listAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books currently registered in the library.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void listAvailableBooks() {
        boolean foundAny = false;
        for (Book book : books) {
            if (!book.isBorrowed()) {
                System.out.println(book);
                foundAny = true;
            }
        }
        if (!foundAny) {
            System.out.println("No books currently available.");
        }
    }
}