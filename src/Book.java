class Books {
    class BookNode {
        Book book;
        BookNode prev, next;

        BookNode() {
            this.book = new Book();
        }
        BookNode(Book bookToAdd){
            this.book = bookToAdd;
        }
    }

    private BookNode head, tail;
    private int count = 0;

    public void addBook() {
        BookNode node = new BookNode();
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        count++;
    }

    public void addBook(Book bookToAdd) {
        BookNode node = new BookNode(bookToAdd);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        count++;
    }

    public Book findBookByID(String id) {
        BookNode curr = head;
        while (curr != null) {
            if (curr.book.bookId.equals(id)) return curr.book;
            curr = curr.next;
        }
        return null;
    }

    public void displayBooks() {
        BookNode curr = head;
        while (curr != null) {
            System.out.println("Book ID: " + curr.book.bookId +
                    ", Title: " + curr.book.getBookName() +
                    ", Author: " + curr.book.getAuthor());
            curr = curr.next;
        }
    }
}
class Book {
    static int bookCount = 0;
    public final String bookId;
    private String bookName;
    private String author;
    private boolean isIssued = false;
    private Issue currentIssue;

    public Book(String bookName, String bookAuthor ) {
        this.bookId = "" + (++bookCount);;
        this.bookName=bookName;
        this.author=bookAuthor;
    }

    public boolean getIsIssued() {
        return isIssued;
    }
    public void setIssued(boolean isIssued) {
        this.isIssued = isIssued;
    }
    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public Issue getCurrentIssue() {
        return currentIssue;
    }
    public void setCurrentIssue(Issue newIssue) {
        currentIssue = newIssue;
    }

    public Book() {
        this.bookName = Input.takeLine("Enter Book Name: ");
        this.author = Input.takeLine("Enter Author Name: ");
        this.bookId = "" + (++bookCount);
        System.out.println("Book added with ID: " + bookId);
    }

    public void issueTo(String readerId, String returnDate) {
        if (!isIssued) {
            isIssued = true;
            currentIssue = new Issue(bookId, readerId, java.time.LocalDate.now().toString(), returnDate);
        }
    }

    public void returnBook() {
        isIssued = false;
        currentIssue = null;
    }

    public void showDetails() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Name: " + bookName);
        System.out.println("Author: " + author);
        System.out.println("Issued: " + isIssued);
    }
}
