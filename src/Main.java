public class Main {

    //Library Data
    static Issues allIssues ;
    static Readers allReaders ;
    static Librarians allLibrarians;
    static Books allBooks ;

    //Starting Point of the Programme
    public static void main(String[] args){

        //Function call to Load data from files
        FileStorage.load();

        // Creating an Interface for the User and Implementing to Console
        new UserInterfaces().login();

        //Function Call to Save data to files
        FileStorage.save();

        //Status 1l denotes data to be successfully Stored
        System.exit(1);
    }
}