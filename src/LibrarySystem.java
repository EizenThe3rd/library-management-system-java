import java.util.Scanner;

public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. List All Books");
            System.out.println("6. Exit");
            System.out.print("Select an option (1-6): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Fixes the Scanner bug (consumes leftover newline)

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String bId = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();

                    if (library.addBook(new Book(title, author, bId))) {
                        System.out.println("Book added successfully!");
                    } else {
                        System.out.println("Failed to add book (duplicate ID or invalid data).");
                    }
                    break;

                case 2:
                    System.out.print("Enter Member ID: ");
                    String mId = scanner.nextLine();
                    System.out.print("Enter Member Name: ");
                    String name = scanner.nextLine();

                    if (library.registerMember(new Member(name, mId))) {
                        System.out.println("Member registered successfully!");
                    } else {
                        System.out.println("Failed to register member (duplicate ID).");
                    }
                    break;

                case 3:
                    System.out.print("Enter Book ID to borrow: ");
                    String borrowBId = scanner.nextLine();
                    System.out.print("Enter Member ID: ");
                    String borrowMId = scanner.nextLine();

                    if (library.borrowBook(borrowBId, borrowMId)) {
                        System.out.println("Book borrowed successfully!");
                    } else {
                        System.out.println("Borrowing failed (Book/Member not found or already checked out).");
                    }
                    break;

                case 4:
                    System.out.print("Enter Book ID to return: ");
                    String returnBId = scanner.nextLine();
                    System.out.print("Enter Member ID: ");
                    String returnMId = scanner.nextLine();

                    if (library.returnBook(returnBId, returnMId)) {
                        System.out.println("Book returned successfully!");
                    } else {
                        System.out.println("Return failed (Book/Member not found or book wasn't checked out).");
                    }
                    break;

                case 5:
                    System.out.println("\n--- Current Catalog ---");
                    library.listAllBooks();
                    break;

                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }
}