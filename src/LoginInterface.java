import javax.crypto.SealedObject;

//import java.util.ListResourceBundle;
//
//class userNotFoundException extends Exception{
//    String message = "User Not Found ! Check ID Again";
//    public String toString() {
//        return message;
//    }
//    public String getMessage() {
//        return message;
//    }
//}
//class notValidException extends Exception{
//
//}
class LoginInterface {
    //    public boolean()
    public void login() {
        System.out.println("Welcome to Kalo Majra Library !");
        boolean login = true;
        while (login) {
            String userId = Input.takeLine("Enter your ID to login : ");
            if (userId.equalsIgnoreCase("x")) {
                continue;
            } else if (userId.startsWith("r")) {
                System.out.println("Reader detecedted");
//                Main.allReaders.displayAll();
                Reader loginUser = (Reader) Main.allReaders.findById(userId);
                if (loginUser == null || (!loginUser.verify())) {
                    System.out.println("Not Found, Check UserId");
                    continue;
                }
                readerInterface(loginUser);
            } else if (userId.startsWith("l")) {
                Librarian loginUser = (Librarian) Main.allLibrarians.findById(userId);
                if (loginUser == null || (!loginUser.verify())) {
                    System.out.println("Not Found, Check UserId");
                    continue;
                }
                librarianInterface(loginUser);
            } else {
                System.out.println("Invalid ry Again");
            }
        }
    }

    public void updateUser(User user){
        while(true){
            String updateSelection = Input.takeLine("Account Update Section :\n\t1.)Name and Contact\n\t2.)Name\n\t3.)Contact\n\t4.)Password\nEnter you Selection : ");
            if(updateSelection.contains("1")){
                user.update();
            } else if (updateSelection.contains("2")) {
                user.setUserName(Input.takeLine("Enter New User Name : "));
            }
            else if (updateSelection.contains("3")){
                user.setUserName(Input.takeLine("Enter New Contact : "));
            }
            else if(updateSelection.contains("4")){
                user.setKey();
            }
            else if(updateSelection.toUpperCase().contains("X")){
                return;
            }
        }
    }
    public void searchBooks(){
        while(true){
            String searchInput = Input.takeLine("Enter the Book tags to search : ");
            if(searchInput.equalsIgnoreCase("X")) return;
            Books searched = Main.allBooks.findByTags(searchInput);
            if(searched==null){
                continue;
            }
            searched.displayAll();
        }
    }
    public void searchReaders(){
        while(true){
            String searchInput = Input.takeLine("Enter the Book tags to search : ");
            if(searchInput.equalsIgnoreCase("X")) return;
            Readers searched = Main.allReaders.findByTags(searchInput);
            if(searched==null){
                continue;
            }
            searched.displayAll();
        }
    }

    public void readerInterface(Reader reader) {
        boolean dashBoard = true;
        while(dashBoard){
            System.out.println("Select an option : ");
            System.out.println("\t1.)Search by Book\n\t2.)View Your Account\n\t3.)Manage Your Account)\n\t4.)To return to Login");
            String firstSelection = Input.takeLine("Enter your Choice : ");
            if(firstSelection.startsWith("1")){
                searchBooks();
            }
            else if(firstSelection.contains("2")){
                reader.account.display();
            } else if (firstSelection.contains("3")) {
                updateUser(reader);
            }
            else if (firstSelection.contains("4")){
                dashBoard = false;
                return;
            }
            else {
                System.out.println("Try Again Invalid Choice");
            }
        }
    }
    public void bookManagement(){
        while(true){
            System.out.println("Select Book Management Options :\n\t1.)Add new book\n\t2.)View All Books\n\t3.)Search Book\n\t4.)Update Book\n\t5.)Delete Book\n\t6.)X to return");
            String secondSelection = Input.takeLine("Enter Your Option : ");
            if(secondSelection.contains("1")){
                Main.allBooks.add();
            }
            else if(secondSelection.contains("2")){
                Main.allBooks.displayAll();
            } else if (secondSelection.contains("3")) {
                searchBooks();
            } else if (secondSelection.contains("4")) {
                Main.allBooks.updateById(Input.takeLine("Enter Book Id to Update : "));
            }
            else if (secondSelection.contains("5")){
                String input = Input.takeLine("Enter book id");
                if(input.contains("X")){
                    return;
                }
                Main.allBooks.deleteById(input);
            }
            else if(secondSelection.toUpperCase().contains("X")){
                return;
            }}
    }
    public void returnBook(){
        while (true){
            String input = Input.takeLine("Enter Book Id to return : ");
            if(input.toUpperCase().contains("X"))return;
            Book toReturn = (Book) Main.allBooks.findById(input);
            toReturn.returnBook();
        }
    }
    public void issuesManagement(){
        System.out.println("Select Issues Management Options :\n\t1.)New Issue\n\t2.)Return Book\n\t3.)View All Issues\n\t4.)View Reader Vise Issues\n\t5.)X to return");
        String secondSelection = Input.takeLine("Enter Your Option : ");
        if(secondSelection.contains("1")){
            Main.allReaders.add();
        }
        else if(secondSelection.contains("2")){

        } else if (secondSelection.contains("3")) {
            while(true) {
                String readerId = Input.takeLine("Enter the Reader Id to view issues : ");
                Reader toView = (Reader) Main.allReaders.findById(readerId);
                if(toView==null){
                    System.out.println("Reader Id not Fount ! Try Again");
                    continue;
                }
                toView.account.display();
            }
        }
        else if(secondSelection.toUpperCase().contains("X")){
            return;
        }
    }
    public void userManagement(){
        System.out.println("Select Book Management Options :\n\t1.)Add New Reader\n\t2.)View All Readers\n\t3.)Search Reader\n\t4.)Update Reader\n\t5.)Delete Reader\n\t6.)X to return");
        String secondSelection = Input.takeLine("Enter Your Option : ");
        if(secondSelection.contains("1")){
            Main.allReaders.add();
        }
        else if(secondSelection.contains("2")){
            Main.allReaders.displayAll();
        } else if (secondSelection.contains("3")) {
            searchReaders();
        } else if (secondSelection.contains("4")) {
            Main.allReaders.updateById(Input.takeLine("Enter Search Id to Update : "));
        }
        else if (secondSelection.contains("5")){
            Main.allReaders.deleteById("Enter Reader Id to Delete : ");
        }
        else if(secondSelection.toUpperCase().contains("X")){
            return;
        }
    }
    public void librarianInterface(Librarian librarian) {
        while(true){
            System.out.println("Select an option : ");
            System.out.println("\t1.)Book Management\n\tnt2.)User Management\n\t3.)Issues Management\n\t4.)Reports");
            String firstSelection = Input.takeLine("Enter your Choice : ");
            if(firstSelection.startsWith("1")){
                bookManagement();
            }
            else if(firstSelection.contains("2")){
                userManagement();
            } else if (firstSelection.contains("3")) {
                issuesManagement();
            }
            else if (firstSelection.contains("X")){
                return;
            }
            else {
                System.out.println("Try Again Invalid Choice");
            }
        }
    }
}
