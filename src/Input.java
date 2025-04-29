import java.util.Scanner;

public abstract class Input {
    static Scanner input = new Scanner(System.in);
    public static String takeLine(String message){
        System.out.print(message);
        return input.nextLine();
    }
    public static int takeInt(String message){
        System.out.print(message);
        int res = input.nextInt();
        input.nextLine();
        return res;
    }
}
