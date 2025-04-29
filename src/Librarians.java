public class Librarians {
    class LibrarianNode {
        Librarian librarian;
        LibrarianNode previousLibrarian;
        LibrarianNode nextLibrarian;

        LibrarianNode() {
            String name = Input.takeLine("Enter Name: ");
            String contact = Input.takeLine("Enter Contact: ");
            this.librarian = new Librarian(name, contact);
        }
    }

    private LibrarianNode head;
    private LibrarianNode tail;
    static int librarianCount = 0;

    public void addLibrarian() {
        LibrarianNode newNode = new LibrarianNode();
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.nextLibrarian = newNode;
            newNode.previousLibrarian = tail;
            tail = newNode;
        }
        librarianCount++;
    }

    public boolean removeLibrarian(int index) {
        if (index < 0 || index >= librarianCount) {
            System.out.println("Invalid index!");
            return false;
        }

        LibrarianNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.nextLibrarian;
        }

        if (current.previousLibrarian == null) {
            head = current.nextLibrarian;
        } else {
            current.previousLibrarian.nextLibrarian = current.nextLibrarian;
        }

        if (current.nextLibrarian == null) {
            tail = current.previousLibrarian;
        } else {
            current.nextLibrarian.previousLibrarian = current.previousLibrarian;
        }

        librarianCount--;
        return true;
    }

    public void displayLibrarians() {
        if (head == null) {
            System.out.println("No librarians in the list.");
            return;
        }

        LibrarianNode current = head;
        while (current != null) {
            System.out.println("Librarian Name: " + current.librarian.getUserName());
            System.out.println("Contact: " + current.librarian.getUserContact());
            System.out.println("User ID: " + current.librarian.getUserID());
            current = current.nextLibrarian;
        }
    }

    public Librarian findLibrarianByName(String name) {
        LibrarianNode current = head;
        while (current != null) {
            if (current.librarian.getUserName().equals(name)) {
                return current.librarian;
            }
            current = current.nextLibrarian;
        }
        return null;
    }

    public Librarian findLibrarianByID(String userID) {
        LibrarianNode current = head;
        while (current != null) {
            if (current.librarian.getUserID().equals(userID)) {
                return current.librarian;
            }
            current = current.nextLibrarian;
        }
        return null;
    }

    public int getLibrarianCount() {
        return librarianCount;
    }
}
