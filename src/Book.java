public class Book {
    // 1 & 2. Private fields for encapsulation
    private String title;
    private String author;
    private String id; // String allows hyphens in ISBNs (e.g., "978-0-13-468599-1")
    private boolean isBorrowed;

    // 3. Constructor
    // Takes required info; isBorrowed defaults to false on creation.
    public Book(String title, String author, String id) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.isBorrowed = false; // Freshly created books start in the library
    }

    // 4. Getters for all fields
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    // 5. Controlled Setters / State Mutators
    // Title and Author can be corrected if mistyped.
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // ID should not change after creation (no setId)
    
    // Instead of a blind setBorrowed(boolean), state is changed 
    // via intentional domain actions that can enforce simple rules.
    public boolean borrowBook() {
        if (!isBorrowed) {
            isBorrowed = true;
            return true; // Successfully borrowed
        }
        return false; // Already borrowed
    }

    public boolean returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            return true; // Successfully returned
        }
        return false; // Wasn't borrowed
    }

    // 6. Overridden toString for clean debugging output
    @Override
    public String toString() {
        String status = isBorrowed ? "Checked Out" : "Available";
        return String.format("Book [ID: %s] \"%s\" by %s (%s)", id, title, author, status);
    }
}