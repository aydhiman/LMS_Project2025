import java.time.*;
import java.io.Serializable;

interface Entities extends Serializable {
    void add();
    void deleteById(String Id);
    void displayAll();
    Entity findById(String Id);
    void updateById(String Id);
}

class Readers implements Entities {
    class ReaderNode implements Serializable{
        Reader reader;
        ReaderNode prev, next;

        ReaderNode() {
            this.reader = new Reader();
        }

        ReaderNode(Reader reader) {
            this.reader = reader;
        }
    }

    private ReaderNode head, tail;
    private int count = 0;

    public void add() {
        add(new Reader());
    }

    public void add(Reader reader) {
        ReaderNode node = new ReaderNode(reader);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        count++;
    }

    public Entity findById(String id) {
        ReaderNode curr = head;
        while (curr != null) {
            if (curr.reader.userId.equals(id)){
                return curr.reader;
            }
            curr = curr.next;
        }
        System.out.println("User Not Found");
        return null;
    }
    public Readers findByTags(String key) {
        boolean isAny = false;
        Readers result = new Readers();
        Readers.ReaderNode current = head;
        while (current != null) {
            if (current.reader.relevant(key)) {
                result.add(current.reader);
                isAny = true;
            }
            current = current.next;
        }
        if(isAny)return result;
        return null;
    }
    public void updateById(String id) {
        Reader r = (Reader) findById(id);
        if (r != null) r.update();
    }

    public void updateById(String id, String name, String contact) {
        Reader r = (Reader) findById(id);
        if (r != null) r.update(name, contact);
    }

    public void deleteById(String id) {
        ReaderNode curr = head;
        while (curr != null) {
            if (curr.reader.userId.equals(id)) {
                if (curr == head) {
                    head = curr.next;
                    if (head != null) head.prev = null;
                } else {
                    if (curr.prev != null) curr.prev.next = curr.next;
                    if (curr.next != null) curr.next.prev = curr.prev;
                }
                count--;
                return;
            }
            curr = curr.next;
        }
    }

    public void displayAll() {
        boolean isAny = false;
        ReaderNode curr = head;
        while (curr != null) {
            isAny = true;
            curr.reader.display();
            curr = curr.next;
        }
        if(!isAny) System.out.println("Nothing Found !");
    }

    public int getCount() {
        return count;
    }

}

class Librarians implements Entities {
    class LibrarianNode implements Serializable {
        Librarian librarian;
        LibrarianNode prev, next;

        LibrarianNode() {
            this.librarian = new Librarian();
        }

        LibrarianNode(Librarian librarian) {
            this.librarian = librarian;
        }
    }

    private LibrarianNode head, tail;
    private int count = 0;

    public void add() {
        add(new Librarian());
    }

    public void add(Librarian librarian) {
        LibrarianNode node = new LibrarianNode(librarian);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        count++;
    }

    public Entity findById(String id) {
        LibrarianNode curr = head;
        while (curr != null) {
            if (curr.librarian.userId.equals(id)) return curr.librarian;
            curr = curr.next;
        }
        System.out.println("User Not Found");
        return null;
    }

    public void updateById(String id) {
        Librarian l = (Librarian) findById(id);
        if (l != null) l.update();
    }

    public void updateById(String id, String name, String contact) {
        Librarian l = (Librarian) findById(id);
        if (l != null) l.update(name, contact);
    }

    public void deleteById(String id) {
        LibrarianNode curr = head;
        while (curr != null) {
            if (curr.librarian.userId.equals(id)) {
                if (curr == head) {
                    head = curr.next;
                    if (head != null) head.prev = null;
                } else {
                    if (curr.prev != null) curr.prev.next = curr.next;
                    if (curr.next != null) curr.next.prev = curr.prev;
                }
                count--;
                return;
            }
            curr = curr.next;
        }
    }

    public void displayAll() {
        boolean isAny = false;
        LibrarianNode curr = head;
        while (curr != null) {
            isAny = true;
            curr.librarian.display();
            curr = curr.next;
        }
        if(!isAny) System.out.println("Nothing Found !");
    }

    public int getCount() {
        return count;
    }
}

class Books implements Entities {
    private class BookNode implements Serializable {
        Book book;
        BookNode prev, next;

        BookNode(Book book) {
            this.book = book;
        }

        BookNode() {
            this.book = new Book();
        }
    }

    private BookNode head, tail;
    private int count = 0;

    @Override
    public void add() {
        add(new Book());
    }

    public void add(Book bookToAdd) {
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

    @Override
    public void deleteById(String bookId) {
        BookNode current = head;
        while (current != null) {
            if (current.book.bookId.equals(bookId)) {
                if (current == head) {
                    head = current.next;
                    if (head != null) head.prev = null;
                } else {
                    if (current.prev != null) current.prev.next = current.next;
                    if (current.next != null) current.next.prev = current.prev;
                }
                count--;
                return;
            }
            current = current.next;
        }
    }

    @Override
    public void displayAll() {
        boolean isAny = false;
        BookNode current = head;
        while (current != null) {
            isAny = true;
            current.book.display();
            current = current.next;
        }
        if(!isAny) System.out.println("Nothing Found !");
    }

    public void displayByName(){

    }

    @Override
    public Entity findById(String bookId) {
        BookNode current = head;
        while (current != null) {
            if (current.book.bookId.equals(bookId)) {
                return current.book;
            }
            current = current.next;
        }
        return null;
    }

    public Books findByTags(String key) {
        boolean isAny = false;
        Books result = new Books();
        BookNode current = head;
        while (current != null) {
            if (current.book.relevant(key)) {
                result.add(current.book);
                isAny = true;
            }
            current = current.next;
        }
        if(isAny)return result;
        return null;
    }

    @Override
    public void updateById(String bookId) {
        Book book = (Book) findById(bookId);
        if (book != null) {
            book.update();
        } else {
            System.out.println("Book not found.");
        }
    }

    public void updateById(String bookId, String newName, String newAuthor) {
        Book book = (Book) findById(bookId);
        if (book != null) {
            book.update(newName, newAuthor);
        } else {
            System.out.println("Book not found.");
        }
    }

    public int getCount() {
        return count;
    }
}

class IssueNode implements Serializable {
    Issue issue;
    IssueNode next, prev;

    IssueNode(Issue issue) {
        this.issue = issue;
    }
}
class  Issues implements Serializable {
    private IssueNode head = null;
    private IssueNode tail = null;
    private int issueCount = 0;

    public void add(Issue issue) {
        if (issue == null) return;

        IssueNode node = new IssueNode(issue);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        issueCount++;
        issue.display();
    }

    public void add(String readerId, String bookId) {
        Book book = (Book) Main.allBooks.findById(bookId);
        Reader reader = (Reader) Main.allReaders.findById(readerId);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        if (reader == null) {
            System.out.println("Reader not found!");
            return;
        }
        if (book.isIssued()) {
            System.out.println("Book Not Available!");
            return;
        }

        int days = Input.takeInt("Enter the days to issue: ");
        Issue issue = new Issue(bookId, readerId, LocalDate.now().toString(), LocalDate.now().plusDays(days).toString());
        add(issue);
    }

    public void add(String bookId, String readerId, int days) {
        Book book = (Book) Main.allBooks.findById(bookId);
        Reader reader = (Reader) Main.allReaders.findById(readerId);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        if(reader == null){
            System.out.println("Book not found!");
            return;
        }
        if (book.isIssued()) {
            System.out.println("Book Not Available!");
            return;
        }
        Issue issue = new Issue(bookId, readerId, LocalDate.now().toString(), LocalDate.now().plusDays(days).toString());
        book.issue(issue);
        add(issue);
    }

    public void deleteById(String issueId) {
        IssueNode curr = head;
        while (curr != null) {
            if (curr.issue.issueId.equals(issueId)) {
                if (curr == head) head = curr.next;
                if (curr == tail) tail = curr.prev;
                if (curr.prev != null) curr.prev.next = curr.next;
                if (curr.next != null) curr.next.prev = curr.prev;
                issueCount--;
                return;
            }
            curr = curr.next;
        }
    }

    public Issue findById(String id) {
        for (IssueNode curr = head; curr != null; curr = curr.next) {
            if (curr.issue.issueId.equals(id)) return curr.issue;
        }
        return null;
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("No issues to display.");
            return;
        }

        for (IssueNode curr = head; curr != null; curr = curr.next) {
            curr.issue.display();
        }
    }

    public Issues findIssuesByReaderId(String readerId) {
        Issues result = new Issues();
        for (IssueNode curr = head; curr != null; curr = curr.next) {
            if (curr.issue.readerId.equals(readerId)) {
                result.add(curr.issue);
            }
        }
        return result;
    }

    public Issues findPendingIssues() {
        Issues result = new Issues();
        for (IssueNode curr = head; curr != null; curr = curr.next) {
            if (curr.issue.isPending()) {
                result.add(curr.issue);
            }
        }
        return result;
    }

    public Issues findOverDueIssues() {
        Issues result = new Issues();
        for (IssueNode curr = head; curr != null; curr = curr.next) {
            if (curr.issue.isOverDue()) {
                result.add(curr.issue);
            }
        }
        return result;
    }

    public Issues findIssuesByBookId(String bookId) {
        Issues result = new Issues();
        for (IssueNode curr = head; curr != null; curr = curr.next) {
            if (curr.issue.bookId.equals(bookId)) {
                result.add(curr.issue);
            }
        }
        return result;
    }

    public int getNumberOfIssues() {
        return issueCount;
    }

    public Issue getMostRecentIssue() {
        return tail != null ? tail.issue : null;
    }
}

