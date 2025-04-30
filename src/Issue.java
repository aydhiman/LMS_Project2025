class Issues {
    private IssueNode head = null;
    private IssueNode tail = null;
    public String addIssue(Issue issue) {
        IssueNode node = new IssueNode(issue);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        return node.issue.issueId;
    }

    public void removeIssue(String issueId) {
        IssueNode curr = head;
        while (curr != null) {
            if (curr.issue.issueId.equals(issueId)) {
                if (curr == head) head = curr.next;
                if (curr == tail) tail = curr.prev;
                if (curr.prev != null) curr.prev.next = curr.next;
                if (curr.next != null) curr.next.prev = curr.prev;
                return;
            }
            curr = curr.next;
        }
    }
    public Issue findIssueById(String id) {
        IssueNode curr = head;
        while (curr != null) {
            if (curr.issue.issueId.equals(id)) return curr.issue;
            curr = curr.next;
        }
        return null;
    }

    public void displayAll() {
        IssueNode curr = head;
        while (curr != null) {
            curr.issue.display();
            curr = curr.next;
        }
    }

    public Issues findIssuedByReader(String id) {
        IssueNode curr = head;
        Issues res = new Issues();
        while (curr != null) {
            if (curr.issue.readerId.equals(id)){
                res.addIssue(curr.issue);
            }
            curr = curr.next;
        }
        return res;
    }
}

class Issue {
    public static int issuesCount = 0;
    public final String issueId;
    public final String bookId;
    public final String readerId;
    public final String issueDate;
    public final String returnDate;

    Issue(String bookId, String readerId, String issueDate, String returnDate) {
        this.issueId = "i" + (++issuesCount);
        this.bookId = bookId;
        this.readerId = readerId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }
    public void display() {
        System.out.println("Issue ID: " + issueId);
        System.out.println("Book ID: " + bookId);
        System.out.println("Reader ID: " + readerId);
        System.out.println("Issue Date: " + issueDate);
        System.out.println("Return Date: " + returnDate);
    }
}
class IssueNode {
    Issue issue;
    IssueNode next, prev;

    IssueNode(Issue issue) {
        this.issue = issue;
    }
}
