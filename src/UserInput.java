import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class UserInput {
    static Scanner input = new Scanner(System.in);

    public static String name() {
        while (true) {
            System.out.print("Enter Name: ");
            String inp = input.nextLine().trim();
            if (!inp.isEmpty()) return inp;
            System.out.println("Name cannot be empty. Try again.");
        }
    }

    public static String name(String attribute) {
        while (true) {
            System.out.print("Enter "+attribute + "Name: ");
            String inp = input.nextLine().trim();
            if (!inp.isEmpty()) return inp;
            System.out.println("Name cannot be empty. Try again.");
        }
    }

    public static String contact() {
        while (true) {
            System.out.print("Enter Contact Number (10 digits): ");
            String inp = input.nextLine().trim();
            if (inp.matches("\\d{10}")) return inp;

            if (inp.isEmpty()) {
                System.out.println("Contact cannot be empty. Try again.");
            } else {
                System.out.println("Invalid Mobile Number. It must be 10 digits.");
            }
        }
    }

    public static String password() {
        while (true) {
            System.out.print("Enter Your Password: ");
            String inp = input.nextLine().trim();
            if (!inp.isEmpty()) return inp;
            System.out.println("Password cannot be empty. Try again.");
        }
    }

    public static int randomInt() {
        return ThreadLocalRandom.current().nextInt(1000, 10000);
    }

    public static int index(int range) {
        while (true) {
            System.out.print("Choose an option (1 to " + range + "): ");
            if (input.hasNextInt()) {
                int inp = input.nextInt();
                input.nextLine(); // Clear buffer
                if (inp > 0 && inp <= range) return inp;
            } else {
                input.nextLine(); // Consume invalid input
            }
            System.out.println("Invalid choice. Try again.");
        }
    }

    public static String trimToLength(String text, int maxLength) {
        return text.length() > maxLength ? text.substring(0, maxLength) : text;
    }
}
