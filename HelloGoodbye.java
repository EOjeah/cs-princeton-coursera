public class HelloGoodbye {
  public static void main(String[] args) {
    String firstArg = args[0];
    String secondArg = args[1];
    System.out.println("Hello "+ firstArg + " and " + secondArg + ".");
    System.out.println("Goodbye "+ secondArg + " and " + firstArg + ".");
  }
}