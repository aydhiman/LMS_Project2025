import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Main {
    static Issues allIssues= new Issues();
    //    public static void main(String[] args) {
//        Readers libraryReaders = new Readers();
//        Books libraryBooks = new Books();
//        Books.add
//    }
//}
    public static void main(String[] args) {
        Readers readers = new Readers();
        Books books = new Books();

        // Add 20 Readers
        for (int i = 1; i <= 20; i++) {
            Reader reader = new Reader("Reader" + i, "99999999" + i);
            readers.addReader(reader);
        }

        // Add 20 Books
        for (int i = 1; i <= 20; i++) {
            Book book = new Book("Book" + i, "Author" + i);
            books.addBook(book);
        }

        // Issue 8 books to random readers
        for (int i = 1; i <= 8; i++) {
            Reader reader = readers.findReaderByID("r"+i); // Get reader at index i
            Book book = books.findBookByID(""+i);     // Get book from the other end

            if (!book.getIsIssued()) {
                String issueDate = "2025-04-0" + i;
                String returnDate = "2025-05-0" + (i + 1);
                Issue issue = new Issue(book.bookId, reader.userId, issueDate, returnDate);

                allIssues.addIssue(issue);
                book.setIssued(true);
                book.setCurrentIssue(issue);

                System.out.println("Issued Book " + book.bookId + " to Reader " + reader.userId);
            }
        }

        // Optional: display all readers
        System.out.println("\n==== Reader List ====");
        readers.displayReaders();
        readers.displayAllIssues();

        // Optional: display all books
        System.out.println("\n==== Book List ====");
        books.displayBooks();
        allIssues.displayAll();
    }
}

