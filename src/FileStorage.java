import java.io.*;

class FileStorage {

    static void load() {
        StaticContext.readStaticContextFromFile();
        Main.allReaders = readReadersFromFile("readersFile.dat");
        Main.allBooks = readBooksFromFile("booksFile.dat");
        Main.allIssues = readIssuesListFromFile("issuesFile.dat");
        Main.allLibrarians = readLibrariansFromFile("librariansFile.dat");
    }

    static void save() {
        writeReadersToFile(Main.allReaders, "readersFile.dat");
        writeBooksToFile(Main.allBooks, "booksFile.dat");
        writeIssuesToFile(Main.allIssues, "issuesFile.dat");
        writeLibrariansToFile(Main.allLibrarians, "librariansFile.dat");

    }

    public static Books readBooksFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Books) in.readObject();
        } catch (FileNotFoundException e) {
            return new Books();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static void writeBooksToFile(Books list, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(list);
            System.out.println("Books saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Readers readReadersFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Readers) in.readObject();
        } catch (FileNotFoundException e) {
            return new Readers();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static void writeReadersToFile(Readers list, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(list);
            System.out.println("Readers saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Librarians readLibrariansFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Librarians) in.readObject();
        } catch (FileNotFoundException e) {
            Librarians list = new Librarians();
            list.add();
            return list;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static void writeLibrariansToFile(Librarians list, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(list);
            System.out.println("Librarians saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Issues readIssuesListFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Issues) in.readObject();
        } catch (FileNotFoundException e) {
            return new Issues();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static void writeIssuesToFile(Issues list, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(list);
            System.out.println("Issues saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Serializable class to store static counts
class StaticContext implements Serializable {
    private static final long serialVersionUID = 1L;

    public int bookCount;
    public int readerCount;
    public int librarianCount;
    public int issueCount;

    public StaticContext(int bookCount, int readerCount, int librarianCount, int issueCount) {
        this.bookCount = bookCount;
        this.readerCount = readerCount;
        this.librarianCount = librarianCount;
        this.issueCount = issueCount;
    }

    // Save current static values to file
    public static void writeStaticContextToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("staticContext.dat"))) {
            StaticContext ctx = new StaticContext(
                    Book.bookCount,
                    Reader.readerCount,
                    Librarian.librarianCount,
                    Issue.issuesCount
            );
            out.writeObject(ctx);
            System.out.println("Static context saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load static values from file and apply to classes
    public static void readStaticContextFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("staticContext.dat"))) {
            StaticContext ctx = (StaticContext) in.readObject();

            Book.bookCount = ctx.bookCount;
            Reader.readerCount = ctx.readerCount;
            Librarian.librarianCount = ctx.librarianCount;
            Issue.issuesCount = ctx.issueCount;

        } catch (IOException | ClassNotFoundException e) {
            System.out.print("");
        }
    }
}

