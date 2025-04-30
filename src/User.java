abstract class User {
    public String userId;
    private String userKey;
    private String userName;
    private String userContact;

    public String getUserName() {
        return userName;
    }
    public void setUserName() {
        this.userName = Input.takeLine("Enter Name : ");
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserContact() {
        return userContact;
    }
    public void setUserContact() {
        this.userContact = Input.takeLine("Enter Contact : ");
    }
    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public boolean verify() {
        String key = Input.takeLine("Enter your Key : ");
        if (key.equals(userKey)) {
            System.out.println("Verified");
            return true;
        } else {
            System.out.println("Bad Password Try Again");
            return false;
        }
    }

    public void createKey() {
        String key = Input.takeLine("Enter Key : ");
        if (key != null && !key.isEmpty()) {
            userKey = key;
            System.out.println("Key Created for " + userId);
        }
    }
    public void createKey(String userKey) {
        if (userKey != null && !userKey.isEmpty()) {
            this.userKey = userKey;
            System.out.println("Key Created for " + userId);
        }
    }

    public void setKey() {
        if (userKey == null) {
            createKey();
            return;
        }
        if (!verify()) {
            System.out.println("Exiting, Try Again !");
            return;
        }
        String key = Input.takeLine("Enter New Key : ");
        if (key != null && !key.isEmpty()) {
            userKey = key;
            System.out.println("Key Updated for " + userId);
        }
    }

    User() {
        setUserName();
        setUserContact();
    }
    User(String userName, String userContact){
        userKey = "1";
        this.userName = userName;
        this.userContact = userContact;
    }
}

class Reader extends User {
    public static int readerCount = 0;
    public Account account;

    public Reader() {
        super();
        userId = "r" + (++readerCount);
        createKey();
        account = new Account(this);
        System.out.println("Account Created for Reader " + getUserName() + " with User ID " + userId);
    }
    public Reader(String userName, String Contact ){
        super(userName,Contact);
        account = new Account(this);
        this.userId = "r" + (++readerCount);
    }
}

class Librarian extends User {
    public static int librarianCount = 0;

    Librarian() {
        super();
        userId = "l" + (++librarianCount);
        createKey();
        System.out.println("Account Created for Librarian " + getUserName() + " with User ID " + userId);
    }
}