public abstract class User {
    public static int idCount = 0;
    private final String userName;
    private final String userID;
    private String userContact;
    private String key;
    public String role;

    public User(String userName, String userContact, String role) {
        this.userID = "" + ++idCount;
        this.userName = userName;
        this.userContact = userContact;
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact){
        this.userContact = userContact;
    }

    public String getRole() {
        return role;
    }

    public boolean verify(String userID) {
        String enteredPassword = Input.takeLine("Enter the Current Password : ");
        return enteredPassword.equals(key);
    }

    public boolean changeKey(String userID) {
        if (!this.userID.equals(userID)) {
            System.out.println("Unknown User");
            return false;
        }
        if (!verify(userID)) {
            return false;
        }
        this.key = Input.takeLine("Input the new key : ");
        return true;
    }

    public void setKey(String key) {
        this.key = key;
        System.out.println("Your key has been Changed Now");
    }
}
