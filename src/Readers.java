class Readers {
    class ReaderNode {
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

    public void addReader() {
        ReaderNode node = new ReaderNode();
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        count++;
    }

    public void addReader(Reader reader) {
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

    public void displayAllIssues(){
        ReaderNode curr = head;
        while (curr != null) {
            System.out.println("Reader ID: " + curr.reader.userId + ", Name: " + curr.reader.getUserName());
            try{
                curr.reader.account.issuedList.displayAll();
            }
            catch(Exception e){
                continue;
            }
            finally {
                curr = curr.next;
            }
        }
    }
    public Reader findReaderByID(String id) {
        ReaderNode curr = head;
        while (curr != null) {
            if (curr.reader.userId.equals(id)) return curr.reader;
            curr = curr.next;
        }
        return null;
    }

    public void displayReaders() {
        ReaderNode curr = head;
        while (curr != null) {
            System.out.println("Reader ID: " + curr.reader.userId + ", Name: " + curr.reader.getUserName());
            curr = curr.next;
        }
    }

    public int getCount() {
        return count;
    }
}
