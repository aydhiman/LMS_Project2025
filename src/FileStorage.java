import java.io.*;

//Class for Writing and Reading Data
class FileStorage {

    //Call Reads Everything from files
    static void load() {
        StaticContext.readStaticCounts();
        Main.allReaders = readReaders();
        Main.allBooks = readBooks();
        Main.allIssues = readIssues();
        Main.allLibrarians = readLibrarians();
    }

    //Call Saves Everything to files
    static void save() {
        saveReaders(Main.allReaders);
        saveBooks(Main.allBooks);
        saveIssues(Main.allIssues);
        saveLibrarians(Main.allLibrarians);
        StaticContext.saveStaticCounts();
    }

    //Calls to write and read books
    static Books readBooks() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("booksFile.dat"))) {
            return (Books) in.readObject();
        } catch (FileNotFoundException e) {
            return new Books(); // return empty if file not found
        } catch (Exception e) {
            System.out.println("Error loading books.");
            Input.forceExit();
            return null;
        }
    }
    static void saveBooks(Books books) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("booksFile.dat"))) {
            out.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving books.");
        }
    }

    //Calls to write and read readers
    static Readers readReaders() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("readersFile.dat"))) {
            return (Readers) in.readObject();
        } catch (FileNotFoundException e) {
            return new Readers();
        } catch (Exception e) {
            System.out.println("Error loading readers.");
            Input.forceExit();
            return null;
        }
    }
    static void saveReaders(Readers readers) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("readersFile.dat"))) {
            out.writeObject(readers);
            System.out.println("Readers saved.");
        } catch (IOException e) {
            System.out.println("Error saving readers.");
        }
    }

    //Calls to write and read librarians
    static Librarians readLibrarians() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("librariansFile.dat"))) {
            return (Librarians) in.readObject();
        } catch (FileNotFoundException e) {
            Librarians librarians = new Librarians();
            librarians.add(); // add default librarian
            return librarians;
        } catch (Exception e) {
            System.out.println("Error loading librarians.");
            Input.forceExit();
            return null;
        }
    }
    static void saveLibrarians(Librarians librarians) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("librariansFile.dat"))) {
            out.writeObject(librarians);
            System.out.println("Librarians saved.");
        } catch (IOException e) {
            System.out.println("Error saving librarians.");
        }
    }

    //Calls to write and read issues
    static Issues readIssues() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("issuesFile.dat"))) {
            return (Issues) in.readObject();
        } catch (FileNotFoundException e) {
            return new Issues();
        } catch (Exception e) {
            System.out.println("Error loading issues.");
            Input.forceExit();
            return null;
        }
    }
    static void saveIssues(Issues issues) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("issuesFile.dat"))) {
            out.writeObject(issues);
            System.out.println("Issues saved.");
        } catch (IOException e) {
            System.out.println("Error saving issues.");
        }
    }
}

// This class saves and loads static counters
class StaticContext implements Serializable {
    public int bookCount;
    public int readerCount;
    public int librarianCount;
    public int issueCount;

    //Constructor Called every time contexts are saved
    public StaticContext(int b, int r, int l, int i) {
        bookCount = b;
        readerCount = r;
        librarianCount = l;
        issueCount = i;
    }

    //To write current static state
    public static void saveStaticCounts() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("staticContext.dat"))) {
            StaticContext ctx = new StaticContext(
                    Book.bookCount,
                    Reader.readerCount,
                    Librarian.librarianCount,
                    Issue.issuesCount
            );
            out.writeObject(ctx);
        } catch (IOException e) {
            System.out.println("Error : Unable To Write Static Context Error\n\tDo Not Add any entity now, But You may perform read and delete operations until developer resolve these Issue.");
        }
    }

    // To read previous static state
    public static void readStaticCounts() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("staticContext.dat"))) {
            StaticContext ctx = (StaticContext) in.readObject();
            Book.bookCount = ctx.bookCount;
            Reader.readerCount = ctx.readerCount;
            Librarian.librarianCount = ctx.librarianCount;
            Issue.issuesCount = ctx.issueCount;
        } catch (Exception e) {
            //If file does not exist, statics are on default value 0
        }
    }
}
