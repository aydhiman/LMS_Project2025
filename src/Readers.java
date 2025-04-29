public class Readers {
    class ReaderNode {
        Reader reader;
        ReaderNode previousReader;
        ReaderNode nextReader;

        ReaderNode() {
            String name = Input.takeLine("Enter Name: ");
            String contact = Input.takeLine("Enter Contact: ");
            this.reader = new Reader(name, contact);
        }
    }

    private ReaderNode head;
    private ReaderNode tail;
    static int readersCount = 0;

    public void addReader() {
        ReaderNode newNode = new ReaderNode();
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.nextReader = newNode;
            newNode.previousReader = tail;
            tail = newNode;
        }
        readersCount++;
    }

    public boolean removeReader(int index) {
        if (index < 0 || index >= readersCount) {
            System.out.println("Invalid index!");
            return false;
        }

        ReaderNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.nextReader;
        }

        if (current.previousReader == null) {
            head = current.nextReader;
        } else {
            current.previousReader.nextReader = current.nextReader;
        }

        if (current.nextReader == null) {
            tail = current.previousReader;
        } else {
            current.nextReader.previousReader = current.previousReader;
        }

        readersCount--;
        return true;
    }

    public void displayReaders() {
        if (head == null) {
            System.out.println("No readers in the list.");
            return;
        }

        ReaderNode current = head;
        while (current != null) {
            System.out.println("Reader Name: " + current.reader.getUserName());
            System.out.println("Contact: " + current.reader.getUserContact());
            current = current.nextReader;
        }
    }

    public int getReaderCount() {
        return readersCount;
    }

    public Reader findReaderByName(String name) {
        ReaderNode current = head;
        while (current != null) {
            if (current.reader.getUserName().equals(name)) {
                return current.reader;
            }
            current = current.nextReader;
        }
        return null;
    }

    public Reader findReaderByID(String userID) {
        ReaderNode current = head;
        while (current != null) {
            if (current.reader.getUserID().equals(userID)) {
                return current.reader;
            }
            current = current.nextReader;
        }
        return null;
    }

}
