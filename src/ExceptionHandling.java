class ExceptionHandling {

}
class userNotFoundException extends Exception{
    String message = "User Not Found ! ";
    public String toString(){
        return message;
    }
    userNotFoundException(){
        System.out.println("|| " +message+" ||");
    }
}
class bookNotFoundException extends Exception{
    String message = "User Not Found ! ";
    public String toString(){
        return message;
    }
    bookNotFoundException(){
        System.out.println("|| " +message+" ||");
    }
}
