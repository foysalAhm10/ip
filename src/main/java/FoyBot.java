import java.util.Scanner;
import java.util.ArrayList;

public class FoyBot {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> tasks = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm FoyBot!\n" +
                "What can I do for you?");
        System.out.println("____________________________________________________________");


        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 + ". " + tasks.get(i));
                }
                System.out.println("    ____________________________________________________________");
                continue;
            }

            System.out.println("    ____________________________________________________________");
            tasks.add(input);
            System.out.println("    added: " + input);
            System.out.println("    ____________________________________________________________");
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        scanner.close();
    }
}
