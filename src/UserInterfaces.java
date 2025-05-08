class UserInterfaces {

    public void login() {
        System.out.println("Welcome to Library Management System !");
        //Instructions for navigating through the function
        System.out.println("\t*exit* -To exit Programme in SAFE MODE give *exit* in Any Input Prompt\n\t* -To Return to previous step give *\n");

        // Login Interface accepts and verifies user details and further navigates to requisite Interface
        while (true) {
            String userId = Input.takeLine("Enter your ID to login : ");
            if (userId.contains("*"))return;
            else if (userId.equals("l1")) {
                //Finds User, If not found asks until not found
                Librarian loginUser = (Librarian) Main.allLibrarians.findById(userId);
                if (loginUser == null || (!loginUser.verify())) {
                    System.out.println("Your ID cannot be retrieved, Try AGAIN");
                    continue;
                }
                adminInterface(loginUser);
            }
            else if (userId.startsWith("r")) {
                //Finds User, If not found asks until not found
                Reader loginUser = (Reader) Main.allReaders.findById(userId);
                if (loginUser == null || (!loginUser.verify())) {
                    System.out.println("Your ID cannot be retrieved, Try AGAIN");
                    continue;
                }
                readerInterface(loginUser);
            } else if (userId.startsWith("l")) {
                //Finds User, If not found asks until not found
                Librarian loginUser = (Librarian) Main.allLibrarians.findById(userId);
                if (loginUser == null || (!loginUser.verify())) {
                    System.out.println("Your ID cannot be retrieved, Try AGAIN");
                    continue;
                }
                librarianInterface(loginUser);
            }
            else {
                System.out.println("Invalid format !");
            }
        }
    }

    public void readerInterface(Reader reader) {
        //Reader Interface Provides Menu for all the Options available for this Specific userRole
        while(true){
            System.out.println("Your Interface : ");
            System.out.println("\t1.)Search Books\n\t2.)View Your Account\n\t3.)Manage Your Account");
            String firstSelection = Input.takeLine("Enter your Choice : ");
            if (firstSelection.toUpperCase().contains("*"))return;
            //SearchBooks
            else if(firstSelection.startsWith("1")){
                searchBooks();
            }
            //Display Associated Account
            else if(firstSelection.contains("2")){
                reader.account.display();
            }
            //Update Credentials or Passwords
            else if (firstSelection.contains("3")) {
                updateUser(reader);
            }
        }
    }
    public void searchBooks(){
        while(true){
            String searchInput = Input.takeLine("Search by ID/AuthorName/Title : ");
            if(searchInput.equalsIgnoreCase("*")) return;
            Books searched = Main.allBooks.findByTags(searchInput);
            if(searched==null){
                System.out.println("Nothing Found ! Try Something Else.");
                continue;
            }
            else if(searched.getCount()<=0){
                System.out.println("Nothing Found ! Try Something Else");
                continue;
            }
            searched.displayAll();
        }
    }

    public void updateUser(User user){
        String updateSelection = Input.takeLine("\tAccount Update Section :\n\t\t1.)Name and Contact\n\t\t2.)Name\n\t\t3.)Contact\n\t\t4.)Password\n\tEnter you Selection : ");
        if(updateSelection.contains("*")){
            return;
        }
        else if(updateSelection.contains("1")){
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
    }

    public void librarianInterface(Librarian librarian) {
        while(true){
            System.out.println("Your Interface : ");
            System.out.println("\t1.)Book Management\n\t2.)User Management\n\t3.)Issues Management\n\t4.)Update My Account");
            String firstSelection = Input.takeLine("Enter your Choice : ");
            if(firstSelection.contains("*"))return;
            else if(firstSelection.contains("1")){
                bookManagement();
            }
            else if(firstSelection.contains("2")){
                userManagement();
            }
            else if (firstSelection.contains("3")) {
                issuesManagement();
            }
            else if (firstSelection.contains("4")) {
                updateUser(librarian);
            }
            else {
                System.out.println("Try Again Invalid Choice");
            }
        }
    }
    public void bookManagement(){
        while(true){
            System.out.println("Select Book Management Options :\n\t1.)Add new book\n\t2.)View All Books\n\t3.)Search Book\n\t4.)Update Book\n\t5.)Delete Book");
            String secondSelection = Input.takeLine("Enter Your Option : ");
            if(secondSelection.toUpperCase().contains("*")) return;
            else if(secondSelection.contains("1")){
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
                if(input.contains("*")){
                    return;
                }
                Main.allBooks.deleteById(input);
            }
        }
    }
    public void adminInterface(Librarian librarian){
        while(true){
            System.out.println("Your Interface : ");
            System.out.println("\t1.)Book Management\n\t2.)User Management\n\t3.)Issues Management\n\t4.)Staff Management\n\t5.)Update My Account");
            String firstSelection = Input.takeLine("Enter your Choice : ");
            if(firstSelection.contains("*"))return;
            else if(firstSelection.contains("1")){
                bookManagement();
            }
            else if(firstSelection.contains("2")){
                userManagement();
            }
            else if (firstSelection.contains("3")) {
                issuesManagement();
            } else if (firstSelection.contains("4")) {
                staffManagement();
            } else if (firstSelection.contains("5")) {
                updateUser(librarian);
            }
            else {
                System.out.println("Try Again Invalid Choice");
            }
        }
    }
    public void staffManagement(){
        System.out.println("Select User Management Options :\n\t1.)Add New Staff\n\t2.)View All Staff\n\t3.)Search Staff\n\t4.)Update Staff\n\t5.)Delete Staff\n\t6.)ReCreate Staff Password");
        String secondSelection = Input.takeLine("Enter Your Option : ");
        if(secondSelection.contains("*"))return;
        else if(secondSelection.contains("1")){
            Main.allLibrarians.add();
        }
        else if(secondSelection.contains("2")){
            Main.allLibrarians.displayAll();
        } else if (secondSelection.contains("3")) {
            searchReaders();
        } else if (secondSelection.contains("4")) {
            String userId = Input.takeLine("Enter User ID to Update : ");
            Entity user = Main.allLibrarians.findById(userId);
            if(user==null){
                System.out.println("User can not be retrieved to Update !");
            }
            updateUser((User)user);
        }
        else if (secondSelection.contains("5")){
            Main.allLibrarians.deleteById(Input.takeLine("Enter Reader Id to Delete : "));
        }
        else if (secondSelection.contains("6")){
            String userId = Input.takeLine("Enter User Id to ReCreate Password : ");
            Librarian user = (Librarian) Main.allLibrarians.findById(userId);
            if(user==null) {
                System.out.println("Cannot Find ! Retry ");
            }
            user.createKey();
        }
    }
    public void userManagement(){
            System.out.println("Select User Management Options :\n\t1.)Add New Reader\n\t2.)View All Readers\n\t3.)Search Reader\n\t4.)Update Reader\n\t5.)Delete Reader\n\t6.)ReCreate User Password");
            String secondSelection = Input.takeLine("Enter Your Option : ");
            if(secondSelection.contains("*"))return;
            else if(secondSelection.contains("1")){
                Main.allReaders.add();
            }
            else if(secondSelection.contains("2")){
                Main.allReaders.displayAll();
            } else if (secondSelection.contains("3")) {
                searchReaders();
            } else if (secondSelection.contains("4")) {
                String userId = Input.takeLine("Enter User ID to Update : ");
                Entity user = Main.allBooks.findById(userId);
                if(user==null){
                    System.out.println("User can not be retrieved to Update !");
                }
                updateUser((User)user);
            }
            else if (secondSelection.contains("5")){
                Main.allReaders.deleteById(Input.takeLine("Enter Reader Id to Delete : "));
            }
            else if (secondSelection.contains("6")){
                String userId = Input.takeLine("Enter User Id to ReCreate Password : ");
                Reader user = (Reader) Main.allReaders.findById(userId);
                if(user==null) {
                    System.out.println("Cannot Find ! Retry ");
                }
                user.createKey();
            }
    }
    public void searchReaders(){
        while(true){
            String searchInput = Input.takeLine("Search by User ID/Name/Contact : ");
            if(searchInput.contains("*")) return;
            Readers searched = Main.allReaders.findByTags(searchInput);
            if(searched==null){
                continue;
            }
            searched.displayAll();
        }
    }
    public void issuesManagement(){
        System.out.println("Select Issues Management Options :\n\t1.)New Issue\n\t2.)Return Book\n\t3.)View All Issues\n\t4.)View Reader Vise Issues\n\t5.)X to return");
        String secondSelection = Input.takeLine("Enter Your Option : ");
        if(secondSelection.contains("1")){
            Main.allIssues.add(Input.takeLine("Enter Book id : "),Input.takeLine("Enter Reader ID : "),Input.takeInt("Enter the No. Of Days to issue : "));
        }
        else if(secondSelection.contains("2")){
            Book toReturn = (Book)Main.allBooks.findById(Input.takeLine("Enter the Book Id to search : "));
            if(toReturn==null) {
                System.out.println("Book Not Found ! ");
            }
            else toReturn.returnBook();
        }
        else if(secondSelection.contains("3")){
            Main.allIssues.displayAll();
        }
        else if (secondSelection.contains("4")) {
            while(true) {
                String readerId = Input.takeLine("Enter the Reader Id to view issues : ");
                Reader toView = (Reader) Main.allReaders.findById(readerId);
                if(toView==null){
                    System.out.println("Reader Id not Fount ! Try Again");
                    return;
                }
                toView.account.display();
            }
        }
        else if(secondSelection.toUpperCase().contains("X")){
            return;
        }
    }
}

