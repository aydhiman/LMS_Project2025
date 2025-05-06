import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class Input {
    static Scanner input = new Scanner(System.in);
    public static String takeLine(String message){
        System.out.print(message);
        String inp =  input.nextLine();
        if(inp.equals("*exit*")){
            System.out.println("Confirmed Exit\n || ::: Saving Files ::: ||");
            FileStorage.save();
            StaticContext.writeStaticContextToFile();
            System.out.println("Finally Exiting");
            System.exit(0);
        }
        return inp;
    }
    public static String takeRandom(){
        return Integer.toString(ThreadLocalRandom.current().nextInt(1000, 10000));
    }
    public static int takeInt(String message){
        System.out.print(message);
        int res = input.nextInt();
        input.nextLine();
        if(res<0){
            System.out.println("Confirmed Exit\n || ::: Saving Files ::: ||");
            FileStorage.save();
            System.out.println("Finally Exiting");
            System.exit(1);
        }
        return res;
    }
}