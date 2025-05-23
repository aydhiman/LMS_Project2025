import java.time.LocalDate;
import java.io.Serializable;

interface Entity extends Serializable {
    void display();
    void update();
}

abstract class User implements Entity {
    public String userId;
    private String userKey;
    private String userName;
    private String userContact;

    public User() {
        this.userName = UserInput.name();
        this.userContact = UserInput.contact();
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
        this.userKey = userId + Input.takeRandom();
        System.out.println("Key Created for " + userId + " with Password : " + userKey);
    }

    public boolean verify() {
        return verify(UserInput.password());
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

//    @Override
//    public void display() {
//        System.out.println("User ID: " + userId);
//        System.out.println("Username: " + userName);
//        System.out.println("User Contact: " + userContact);
//    }
    public void display() {
        StringBuilder sb = new StringBuilder();

        sb.append("User ID: ").append(String.format("%-5s", this.userId));
        sb.append(" | Name: ").append(String.format("%-15s", this.userName.length() > 15 ? this.userName.substring(0, 15) : this.userName));
        sb.append(" | Contact: ").append(String.format("%-12s", this.userContact.length() > 12 ? this.userContact.substring(0, 12) : this.userContact));

        System.out.println(sb.toString());
    }

    //    public void display() {
//        String toString = new String("");
//        StringBuilder toAdd = new StringBuilder();
//        while(toAdd.length()<4){
//            toAdd = toAdd.insert(0,"0");
//        }
//        toString += "Book Id: " + toAdd;
//        toAdd = new StringBuilder(bookName);
//        if(toAdd.length()>15){
//            toAdd = new StringBuilder(toAdd.substring(0, 20));
//        }
//        while(toAdd.length()<15){
//            toAdd.append(" ");
//        }
//        toString += " | Book Title: " + toAdd;
//        toAdd = new StringBuilder(author);
//        if(toAdd.length()>8){
//            toAdd = new StringBuilder(toAdd.substring(0,8));
//        }
//        while(toAdd.length()<8){
//            toAdd.append(" ");
//        }
//        toString += " | Book Author: " + toAdd;
//        toString += " | Is Available: " + ((!isIssued)?"Available":"Not Available");
//        System.out.println(toString);
    @Override
    public void update() {
        if (!verify()) return;
        this.userName = UserInput.name();
        this.userContact = UserInput.contact();
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
    public static int bookCount = 0;
    public final String bookId;
    private String bookName;
    private String author;
    private boolean isIssued = false;
    private Issue currentIssue;

    public Book() {
        this(UserInput.name("Book "),
                UserInput.name("Author "));
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
        Issue issue = new Issue(bookId, readerId, java.time.LocalDate.now().toString(), returnDate);
        issue(issue);
    }

    public void issue(Issue issue) {
        if (!isIssued) {
            isIssued = true;
            currentIssue = issue;
        } else {
            System.out.println("Book is not available at this time");
        }
    }

    public void returnBook() {
        if (!isIssued || currentIssue == null) {
            System.out.println("Book is not issued.");
            return;
        }
        isIssued = false;
        currentIssue.returnBook();
        currentIssue = null;
    }

    public void display() {
        String toDisplay = "Book Id: " + getFormattedBookId();
        toDisplay += " | Book Title: " + padOrTrim(bookName, 15);
        toDisplay += " | Book Author: " + padOrTrim(author, 8);
        toDisplay += " | Is Available: " + (isIssued ? "Not Available" : "Available");
        System.out.println(toDisplay);
    }

    @Override
    public void update() {
        this.bookName = UserInput.name("Author ");
        this.author = UserInput.name("Author ");
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

    private String padOrTrim(String input, int length) {
        StringBuilder sb = new StringBuilder(input);
        if (sb.length() > length) {
            return sb.substring(0, length);
        }
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private String getFormattedBookId() {
        return String.format("%04d", Integer.parseInt(bookId));
    }
}


class Issue implements Entity {
    public static int issuesCount = 0;
    public final String issueId;
    public final String bookId;
    public final String readerId;
    public final String issueDate;
    public final String returnDate;
    public String returnedDate = "Not Returned";
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
        String toString = new String("");
        StringBuilder toAdd = new StringBuilder(issueId);
        while(toAdd.length()<4){
            toAdd = toAdd.insert(0,"0");
        }
        toString += "Issue ID: " + toAdd;
        toAdd = new StringBuilder(bookId);
        while(toAdd.length()<4){
            toAdd.insert(0,"0");
        }
        toString += " | BookId: " + toAdd;
        toAdd = new StringBuilder(readerId);
        while(toAdd.length()<4){
            toAdd.insert(0,"0");
        }
        toString += " | UserId: " + toAdd;
        toString += " | ReturnDate: " + (returnedDate);
        System.out.println(toString);
    }
    public void returnBook(){
         returnedDate = LocalDate.now().toString();
         display();
    }
    public void update() {
    }
    public boolean isOverDue() {
        try {
            LocalDate toReturn = LocalDate.parse(returnDate); // ISO-8601 format: "yyyy-MM-dd"
            return isPending() && LocalDate.now().isAfter(toReturn);
        } catch (Exception e) {
            System.out.println("Invalid return date format: " + returnDate);
            return false; // or true, depending on how you want to treat parsing failures
        }
    }

    public boolean isPending() {
        if(returnedDate.equals("n")) return true;
        return false;
    }

}
