import java.util.ArrayList;
import java.util.List;

public class Member {
    // 1 & 2. Private fields for encapsulation
    private String name;
    private String memberId;
    // Holds the actual Book objects currently borrowed by this member
    private ArrayList<Book> borrowedBooks;

    // 3. Constructor
    public Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
        // Instantiate an empty ArrayList so it is never null
        this.borrowedBooks = new ArrayList<>(); 
    }

    // 4. Getters
    public String getName() {
        return name;
    }

    public String getMemberId() {
        return memberId;
    }

    /* 
     * GETTER DECISION (Copy vs. Real List):
     * Returning a new ArrayList (a copy) prevents "escaping references".
     * If we returned borrowedBooks directly, outside code could do:
     * member.getBorrowedBooks().clear(); 
     * and bypass our class logic completely!
     */
    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    // 5. Controlled Mutator Methods (No blind setBorrowedBooks)
    public void setName(String name) {
        this.name = name;
    }

    // Add a book to the member's list
    public boolean addBook(Book book) {
        if (book != null && !borrowedBooks.contains(book)) {
            borrowedBooks.add(book);
            return true;
        }
        return false;
    }

    // Remove a book from the member's list
    public boolean removeBook(Book book) {
        return borrowedBooks.remove(book);
    }

    // 6. Helper Method: Borrowed Count
    // Member owns the list, so Member is responsible for providing the count.
    public int getBorrowedCount() {
        return borrowedBooks.size();
    }

    // 7. toString Method
    @Override
    public String toString() {
        return String.format("Member [ID: %s] %s | Books Borrowed: %d", 
                memberId, name, borrowedBooks.size());
    }
}
