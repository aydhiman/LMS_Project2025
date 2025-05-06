class Account {
    private final Reader owner;
    private final Issues allIssues = new Issues();
    private Issues userIssues;

    public Account(Reader owner) {
        this.owner = owner;
        refreshAccounting();
    }

    private void refreshAccounting() {
        userIssues = Main.allIssues.findIssuesByReaderId(owner.userId);
    }

    public Issues getPendingIssues() {
        refreshAccounting();
        return userIssues.findPendingIssues();
    }

    public int getNumberOfIssues() {
        refreshAccounting();
        return userIssues.getNumberOfIssues();
    }

    public Issues getOverdueIssues() {
        refreshAccounting();
        return userIssues.findOverDueIssues();
    }

    public Issue getMostRecentIssue() {
        refreshAccounting();
        if (userIssues.getNumberOfIssues() == 0) {
            System.out.println("No issues found! ");
            return null;
        }
        return userIssues.getMostRecentIssue();
    }

    public void display() {
        refreshAccounting();

        // Display Pending Issues
        try {
            System.out.println("Your Pending Issues:");
            getPendingIssues().displayAll();
        } catch (Exception e) {
            System.out.println("No Pending Issues");
        }

        // Display Most Recent Issue
        try {
            System.out.println("\nYour Most Recent Issue:");
            Issue recent = getMostRecentIssue();
            if (recent != null) recent.display();
        } catch (Exception e) {
            System.out.println("No Issues Yet");
        }

        // Display Overdue Issues
        try {
            System.out.println("\nYour Overdue Issues:");
            getOverdueIssues().displayAll();
        } catch (Exception e) {
            System.out.println("No Overdue Issues");
        }
    }
}