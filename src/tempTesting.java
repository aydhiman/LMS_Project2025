class Testing1 {
    Books books = Main.allBooks;
    Readers readers = Main.allReaders;
    Issues issues = Main.allIssues;
    public void interfaceFor(){
        while(true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add Book\n2. View Books\n3. Delete Book\n4. Add Patron\n5. View Patrons\n6. Delete Patron\n7. Borrow Book\n8. View Transactions\n9. Update Book\n0. Exit");
            System.out.print("Enter your choice: ");
            int choice = Input.takeInt("");

            switch (choice) {
                case 1 -> books.add();
//                case 2 -> books.findById(Input.takeLine("Enter Book Id"));
                case 2 -> books.displayAll();
                case 3 -> books.deleteById(Input.takeLine("Enter Book Id"));
                case 4 -> readers.add();
                case 5 -> readers.displayAll();
                case 6 -> readers.deleteById("Enter id to delete : ");
//                case 7 -> issues.add(new Issue(Input.takeLine("Enter Book Id"),Input.takeLine("Enter reader Id"),Input.takeLine("Enter issue date"),Input.takeLine("Enter Return Date")));
                case 7 -> newIssue();
                case 8 -> issues.displayAll();
                case 9 -> books.updateById(Input.takeLine("Enter the Book ID to update"));
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    int i = 0;
    public void newIssue(){
        Reader reader = (Reader)readers.findById(Input.takeLine("Enter reader id : ")); // Get reader at index i
        Book book = (Book)books.findById(Input.takeLine("Enter book id : "));     // Get book from the other end

        if (!book.isIssued()) {
            String issueDate = "2025-04-0" + i++ ;
            String returnDate = "2025-05-0" + (i + 1);
            Issue issue = new Issue(book.bookId, reader.userId, issueDate, returnDate);

            issues.add(issue);
            book.setIssued(true);
            book.setCurrentIssue(issue);

            System.out.println("Issued Book " + book.bookId + " to Reader " + reader.userId);
        }
    }
}

//    static void addBook(java.io.BufferedReader reader) throws java.io.IOException {
//        System.out.print("Enter Book ID: ");
//        int id = Integer.parseInt(reader.readLine());
//        System.out.print("Enter Title: ");
//        String title = reader.readLine();
//        System.out.print("Enter Author: ");
//        String author = reader.readLine();
//        System.out.print("Enter Publication Date: ");
//        String date = reader.readLine();
//
//        BookNode newNode = new BookNode(new Bookk(id, title, author, date));
//        newNode.next = bookHead;
//        bookHead = newNode;
//        System.out.println("Book added.");
//    }
//
//    static void viewBooks() {
//        BookNode current = bookHead;
//        while (current != null) {
//            Bookk b = current.book;
//            System.out.println("ID: " + b.id + ", Title: " + b.title + ", Author: " + b.author + ", Published: " + b.publicationDate + ", Available: " + b.isAvailable);
//            current = current.next;
//        }
//    }
//
//    static void deleteBook(java.io.BufferedReader reader) throws java.io.IOException {
//        System.out.print("Enter Book ID to delete: ");
//        int id = Integer.parseInt(reader.readLine());
//        BookNode current = bookHead, prev = null;
//
//        while (current != null) {
//            if (current.book.id == id) {
//                if (prev == null) {
//                    bookHead = current.next;
//                } else {
//                    prev.next = current.next;
//                }
//                System.out.println("Book deleted.");
//                return;
//            }
//            prev = current;
//            current = current.next;
//        }
//        System.out.println("Book not found.");
//    }
//
//    static void addPatron(java.io.BufferedReader reader) throws java.io.IOException {
//        System.out.print("Enter Patron ID: ");
//        int id = Integer.parseInt(reader.readLine());
//        System.out.print("Enter Name: ");
//        String name = reader.readLine();
//
//        PatronNode newNode = new PatronNode(new Patron(id, name));
//        newNode.next = patronHead;
//        patronHead = newNode;
//        System.out.println("Patron added.");
//    }
//
//    static void viewPatrons() {
//        PatronNode current = patronHead;
//        while (current != null) {
//            System.out.println("ID: " + current.patron.id + ", Name: " + current.patron.name);
//            current = current.next;
//        }
//    }
//
//    static void deletePatron(java.io.BufferedReader reader) throws java.io.IOException {
//        System.out.print("Enter Patron ID to delete: ");
//        int id = Integer.parseInt(reader.readLine());
//        PatronNode current = patronHead, prev = null;
//
//        while (current != null) {
//            if (current.patron.id == id) {
//                if (prev == null) {
//                    patronHead = current.next;
//                } else {
//                    prev.next = current.next;
//                }
//                System.out.println("Patron deleted.");
//                return;
//            }
//            prev = current;
//            current = current.next;
//        }
//        System.out.println("Patron not found.");
//    }
//
//    static void borrowBook(java.io.BufferedReader reader) throws java.io.IOException {
//        System.out.print("Enter Patron ID: ");
//        int patronId = Integer.parseInt(reader.readLine());
//        System.out.print("Enter Book ID: ");
//        int bookId = Integer.parseInt(reader.readLine());
//
//        BookNode bookCurrent = bookHead;
//        while (bookCurrent != null) {
//            if (bookCurrent.book.id == bookId && bookCurrent.book.isAvailable) {
//                bookCurrent.book.isAvailable = false;
//                PatronNode patronCurrent = patronHead;
//                while (patronCurrent != null) {
//                    if (patronCurrent.patron.id == patronId) {
//                        BookNode borrowed = new BookNode(bookCurrent.book);
//                        borrowed.next = patronCurrent.patron.borrowedBooksHead;
//                        patronCurrent.patron.borrowedBooksHead = borrowed;
//
//                        String currentDate = java.time.LocalDate.now().toString();
//                        transactions.add(new Transaction(patronId, bookId, currentDate));
//                        System.out.println("Book borrowed.");
//                        return;
//                    }
//                    patronCurrent = patronCurrent.next;
//                }
//                return;
//            }
//            bookCurrent = bookCurrent.next;
//        }
//        System.out.println("Book not found or unavailable.");
//    }
//
//    static void updateBook(java.io.BufferedReader reader) throws java.io.IOException {
//        System.out.print("Enter Book ID to update: ");
//        int id = Integer.parseInt(reader.readLine());
//        BookNode current = bookHead;
//        while (current != null) {
//            if (current.book.id == id) {
//                System.out.print("Enter new Title: ");
//                current.book.title = reader.readLine();
//                System.out.print("Enter new Author: ");
//                current.book.author = reader.readLine();
//                System.out.print("Enter new Publication Date: ");
//                current.book.publicationDate = reader.readLine();
//                System.out.println("Book details updated.");
//                return;
//            }
//            current = current.next;
//        }
//        System.out.println("Book not found.");
//    }}