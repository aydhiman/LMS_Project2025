import java.time.LocalDate;
import java.util.Locale;

interface Entity {
    void display();
    void update();
//    String serialize();
}

abstract class User implements Entity {
    public String userId;
    private String userKey;
    private String userName;
    private String userContact;

    public User() {
        this.userName = Input.takeLine("Enter Name: ");
        this.userContact = Input.takeLine("Enter Contact: ");
    }

    public User(String userName, String userContact, String userKey) {
        this.userName = userName;
        this.userContact = userContact;
        this.userKey = userKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public void createKey() {
        String key = Input.takeLine("Enter Key: ");
        if (key != null && !key.isEmpty()) {
            this.userKey = key;
            System.out.println("Key Created for " + userId);
        }
    }

    public boolean verify() {
        return verify(Input.takeLine("Enter your Key: "));
    }

    public boolean verify(String key) {
        if (key.equals(userKey)) {
            System.out.println("Verified");
            return true;
        } else {
            System.out.println("Bad Password. Try Again.");
            return false;
        }
    }

    public void setKey() {
        if (userKey == null) {
            createKey();
        } else if (verify()) {
            String key = Input.takeLine("Enter New Key: ");
            if (key != null && !key.isEmpty()) {
                this.userKey = key;
                System.out.println("Key Updated for " + userId);
            }
        } else {
            System.out.println("Exiting. Try Again!");
        }
    }

    @Override
    public void display() {
        System.out.println("User ID: " + userId);
        System.out.println("Username: " + userName);
        System.out.println("User Contact: " + userContact);
    }

    @Override
    public void update() {
        if (!verify()) return;
        this.userName = Input.takeLine("Enter new user name: ");
        this.userContact = Input.takeLine("Enter new contact: ");
    }

    public void update(String userName, String userContact) {
        this.userName = userName;
        this.userContact = userContact;
    }
}

class Reader extends User {
    public static int readerCount = 0;
    public Account account;

    public Reader() {
        super();
        this.userId = "r" + (++readerCount);
        createKey();
        this.account = new Account(this);
        System.out.println("Account Created for Reader " + getUserName() + " with User ID " + userId);
    }

    public Reader(String userName, String contact) {
        super(userName, contact, "1");
        this.userId = "r" + (++readerCount);
        this.account = new Account(this);
    }

    public boolean relevant(String searchKey) {
        return userId.toLowerCase().contains(searchKey.toLowerCase()) ||
                getUserName().toLowerCase().contains(searchKey.toLowerCase()) ||
                getUserContact().toLowerCase().contains(searchKey.toLowerCase());
    }
}

class Librarian extends User {
    public static int librarianCount = 0;

    public Librarian() {
        super();
        this.userId = "l" + (++librarianCount);
        createKey();
        System.out.println("Account Created for Librarian " + getUserName() + " with User ID " + userId);
    }
    public Librarian(String userName,String userContact){
        super(userName,userContact,"1");
        this.userId = "l" + (++librarianCount);
        System.out.println("Account Created for Librarian " + getUserName() + " with User ID " + userId);
    }
}

class Book implements Entity {
    private static int bookCount = 0;
    public final String bookId;
    private String bookName;
    private String author;
    private boolean isIssued = false;
    private Issue currentIssue;

    public Book() {
        this(Input.takeLine("Enter Book Name: "), Input.takeLine("Enter Author Name: "));
    }

    public Book(String bookName, String bookAuthor) {
        this.bookId = "" + (++bookCount);
        this.bookName = bookName;
        this.author = bookAuthor;
        System.out.println("Book added with ID: " + bookId);
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
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
        this.currentIssue = newIssue;
    }

    public void issue(String readerId, String returnDate) {
        if (!isIssued) {
            isIssued = true;
            currentIssue = new Issue(bookId, readerId, java.time.LocalDate.now().toString(), returnDate);
        }
    }
    public void issue(Issue issue) {
        if (!isIssued) {
            isIssued = true;
            currentIssue = issue;
        }
    }

    public void returnBook() {
        if(!isIssued){
            System.out.println("Book is not Issued");
        }
        isIssued = false;
        currentIssue.returnBook();
        currentIssue = null;
    }
    @Override
    public void display() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Name: " + bookName);
        System.out.println("Author: " + author);
        System.out.println("Issued: " + isIssued);
    }

    @Override
    public void update() {
        this.bookName = Input.takeLine("Enter New Book Name: ");
        this.author = Input.takeLine("Enter New Author Name: ");
        System.out.println("Book updated with ID: " + bookId);
        display();
    }

    public void update(String bookName, String bookAuthor) {
        this.bookName = bookName;
        this.author = bookAuthor;
        System.out.println("Book updated with ID: " + bookId);
        display();
    }

    public boolean relevant(String searchKey) {
        return bookId.toLowerCase().contains(searchKey.toLowerCase()) ||
                bookName.toLowerCase().contains(searchKey.toLowerCase()) ||
                author.toLowerCase().contains(searchKey.toLowerCase());
    }
}

class Issue implements Entity {
    private static int issuesCount = 0;
    public final String issueId;
    public final String bookId;
    public final String readerId;
    public final String issueDate;
    public final String returnDate;
    public String returnedDate = "n";
    boolean isReturned = false;
    public Issue(String bookId, String readerId, String issueDate, String returnDate) {
        this.issueId = "i" + (++issuesCount);
        this.bookId = bookId;
        this.readerId = readerId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }
    @Override
    public void display() {
        System.out.println("Issue ID: " + issueId);
        System.out.println("Book ID: " + bookId);
        System.out.println("Reader ID: " + readerId);
        System.out.println("Issue Date: " + issueDate);
        System.out.println("Return Date: " + returnDate);
    }
    public void returnBook(){
        returnedDate = LocalDate.now().toString();
    }
    public void update() {
    }
    public boolean isOverDue() {
        String[] arr = returnDate.split("-");
        LocalDate toReturn = LocalDate.of(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
        if(LocalDate.now().isAfter(toReturn))return true;
        return false;
    }

    public boolean isPending() {
        if(returnedDate.equals("n")) return true;
        return false;
    }
}