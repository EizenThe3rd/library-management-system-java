# Library Management System (Java)

A console-based Library Management System built in Java as a foundational OOP project. It demonstrates core object-oriented principles — encapsulation, delegation, and state management — through a simple book-borrowing workflow.

## Features

- **Add books** to the library catalog with duplicate-ID protection
- **Register members** with duplicate-ID protection
- **Borrow books** with validation (checks that the book and member exist, and that the book isn't already checked out)
- **Return books** with validation (checks the book was actually borrowed)
- **List all books** in the catalog with their current status (Available / Checked Out)
- **Crash-proof menu input** — gracefully handles invalid (non-numeric) menu selections instead of terminating

## Project Structure

```
librarysystem/
├── Book.java           # Represents a single book (title, author, ID, borrowed status)
├── Member.java         # Represents a library member and their borrowed books
├── Library.java        # Manages the collections of books/members and core borrow/return logic
└── LibrarySystem.java  # Entry point — console menu and user interaction loop
```

## Design Notes

- **Encapsulation:** All class fields are `private`. Access is controlled through getters and, where appropriate, setters.
- **Guarded state changes:** Rather than exposing a raw `setBorrowed(boolean)`, `Book` exposes intentional actions — `borrowBook()` and `returnBook()` — that enforce valid state transitions and return a `boolean` indicating success.
- **Delegation:** `Library` orchestrates the borrow/return workflow (finding the right `Book` and `Member`, checking preconditions) but delegates the actual state change to the `Book` and `Member` objects themselves, avoiding duplicated logic.
- **Input safety:** The main menu wraps `Scanner.nextInt()` in a `try/catch` for `InputMismatchException`, flushing the invalid token from the buffer so the program can recover instead of crashing.

## How to Run

1. Open the project in Apache NetBeans.
2. Run `LibrarySystem.java` (right-click → Run File, or use the main Run button if it's set as the main class).
3. Use the on-screen menu (options 1–6) to add books, register members, and borrow/return books.

## Example Session

```
=== LIBRARY MANAGEMENT SYSTEM ===
1. Add Book
2. Register Member
3. Borrow Book
4. Return Book
5. List All Books
6. Exit
Select an option (1-6): 1
Enter Book ID: 101
Enter Title: Alice in Wonderland
Enter Author: Lewis Carroll
Book added successfully!
```

## Possible Future Improvements

- Persist data to a file or database (currently all data is in-memory and resets on exit)
- Add a borrow limit per member
- Add search by title/author from the menu
- Migrate to a GUI (JavaFX) or add a database layer (JDBC)

## Author

Van
