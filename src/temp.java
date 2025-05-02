class Bookk {
    int id;
    String title;
    String author;
    String publicationDate;
    boolean isAvailable = true;

    Bookk(int id, String title, String author, String publicationDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
    }
}

class Patron {
    int id;
    String name;
    BookNode borrowedBooksHead = null;

    Patron(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Transaction {
    int patronId;
    int bookId;
    String date;
    Transaction next;

    Transaction(int patronId, int bookId, String date) {
        this.patronId = patronId;
        this.bookId = bookId;
        this.date = date;
        this.next = null;
    }
}

class BookNode {
    Bookk book;
    BookNode next;

    BookNode(Bookk book) {
        this.book = book;
        this.next = null;
    }
}

class PatronNode {
    Patron patron;
    PatronNode next;

    PatronNode(Patron patron) {
        this.patron = patron;
        this.next = null;
    }
}

class TransactionList {
    Transaction head = null;

    void add(Transaction transaction) {
        if (head == null) {
            head = transaction;
        } else {
            Transaction current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = transaction;
        }
    }

    void printAll() {
        Transaction current = head;
        while (current != null) {
            System.out.println("Patron ID: " + current.patronId + ", Book ID: " + current.bookId + ", Date: " + current.date);
            current = current.next;
        }
    }
}


class LibraryManagementSystem {
    static BookNode bookHead = null;
    static PatronNode patronHead = null;
    static TransactionList transactions = new TransactionList();

    public static void main(String[] args) throws java.io.IOException {
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        while (true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add Book\n2. View Books\n3. Delete Book\n4. Add Patron\n5. View Patrons\n6. Delete Patron\n7. Borrow Book\n8. View Transactions\n9. Update Book\n0. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1 -> addBook(reader);
                case 2 -> viewBooks();
                case 3 -> deleteBook(reader);
                case 4 -> addPatron(reader);
                case 5 -> viewPatrons();
                case 6 -> deletePatron(reader);
                case 7 -> borrowBook(reader);
                case 8 -> transactions.printAll();
                case 9 -> updateBook(reader);
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void addBook(java.io.BufferedReader reader) throws java.io.IOException {
        System.out.print("Enter Book ID: ");
        int id = Integer.parseInt(reader.readLine());
        System.out.print("Enter Title: ");
        String title = reader.readLine();
        System.out.print("Enter Author: ");
        String author = reader.readLine();
        System.out.print("Enter Publication Date: ");
        String date = reader.readLine();

        BookNode newNode = new BookNode(new Bookk(id, title, author, date));
        newNode.next = bookHead;
        bookHead = newNode;
        System.out.println("Book added.");
    }

    static void viewBooks() {
        BookNode current = bookHead;
        while (current != null) {
            Bookk b = current.book;
            System.out.println("ID: " + b.id + ", Title: " + b.title + ", Author: " + b.author + ", Published: " + b.publicationDate + ", Available: " + b.isAvailable);
            current = current.next;
        }
    }

    static void deleteBook(java.io.BufferedReader reader) throws java.io.IOException {
        System.out.print("Enter Book ID to delete: ");
        int id = Integer.parseInt(reader.readLine());
        BookNode current = bookHead, prev = null;

        while (current != null) {
            if (current.book.id == id) {
                if (prev == null) {
                    bookHead = current.next;
                } else {
                    prev.next = current.next;
                }
                System.out.println("Book deleted.");
                return;
            }
            prev = current;
            current = current.next;
        }
        System.out.println("Book not found.");
    }

    static void addPatron(java.io.BufferedReader reader) throws java.io.IOException {
        System.out.print("Enter Patron ID: ");
        int id = Integer.parseInt(reader.readLine());
        System.out.print("Enter Name: ");
        String name = reader.readLine();

        PatronNode newNode = new PatronNode(new Patron(id, name));
        newNode.next = patronHead;
        patronHead = newNode;
        System.out.println("Patron added.");
    }

    static void viewPatrons() {
        PatronNode current = patronHead;
        while (current != null) {
            System.out.println("ID: " + current.patron.id + ", Name: " + current.patron.name);
            current = current.next;
        }
    }

    static void deletePatron(java.io.BufferedReader reader) throws java.io.IOException {
        System.out.print("Enter Patron ID to delete: ");
        int id = Integer.parseInt(reader.readLine());
        PatronNode current = patronHead, prev = null;

        while (current != null) {
            if (current.patron.id == id) {
                if (prev == null) {
                    patronHead = current.next;
                } else {
                    prev.next = current.next;
                }
                System.out.println("Patron deleted.");
                return;
            }
            prev = current;
            current = current.next;
        }
        System.out.println("Patron not found.");
    }

    static void borrowBook(java.io.BufferedReader reader) throws java.io.IOException {
        System.out.print("Enter Patron ID: ");
        int patronId = Integer.parseInt(reader.readLine());
        System.out.print("Enter Book ID: ");
        int bookId = Integer.parseInt(reader.readLine());

        BookNode bookCurrent = bookHead;
        while (bookCurrent != null) {
            if (bookCurrent.book.id == bookId && bookCurrent.book.isAvailable) {
                bookCurrent.book.isAvailable = false;
                PatronNode patronCurrent = patronHead;
                while (patronCurrent != null) {
                    if (patronCurrent.patron.id == patronId) {
                        BookNode borrowed = new BookNode(bookCurrent.book);
                        borrowed.next = patronCurrent.patron.borrowedBooksHead;
                        patronCurrent.patron.borrowedBooksHead = borrowed;

                        String currentDate = java.time.LocalDate.now().toString();
                        transactions.add(new Transaction(patronId, bookId, currentDate));
                        System.out.println("Book borrowed.");
                        return;
                    }
                    patronCurrent = patronCurrent.next;
                }
                return;
            }
            bookCurrent = bookCurrent.next;
        }
        System.out.println("Book not found or unavailable.");
    }

    static void updateBook(java.io.BufferedReader reader) throws java.io.IOException {
        System.out.print("Enter Book ID to update: ");
        int id = Integer.parseInt(reader.readLine());
        BookNode current = bookHead;
        while (current != null) {
            if (current.book.id == id) {
                System.out.print("Enter new Title: ");
                current.book.title = reader.readLine();
                System.out.print("Enter new Author: ");
                current.book.author = reader.readLine();
                System.out.print("Enter new Publication Date: ");
                current.book.publicationDate = reader.readLine();
                System.out.println("Book details updated.");
                return;
            }
            current = current.next;
        }
        System.out.println("Book not found.");
    }

}


