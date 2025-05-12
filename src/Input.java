import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class Input{
    static Scanner input = new Scanner(System.in);

    //Calls to read a string after a message
    public static String takeLine(String message){
        System.out.print(message);
        String inp =  input.nextLine();
        if (inp.equals("*exit*")){
           forceExit();
        }
        return inp;
    }

    //Gives a random 4 digit string
    public static String takeRandom(){
        return Integer.toString(ThreadLocalRandom.current().nextInt(1000, 10000));
    }

    //Takes an integer input from user after taking input
    public static int takeInt(String message){
        System.out.print(message);
        int res = input.nextInt();
        input.nextLine();
        if(res<0){
            forceExit();
        }
        return res;
    }

    //Forcefully Exits if *exit*(force exit interrupt is given at any Point)
    public static void forceExit(){
        System.out.println("Force Exit ::: Saving State");
        FileStorage.save();
        StaticContext.saveStaticCounts();
        System.exit(1);
    }
}