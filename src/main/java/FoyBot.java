import java.util.Scanner;
import java.util.ArrayList;

public class FoyBot {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("    ________________________________________________________");
        System.out.println("    Hello! I'm FoyBot!\n" +
                "    What can I do for you?");
        System.out.println("    ________________________________________________________");



        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String command = parts[0];

            System.out.println("    ________________________________________________________");

            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 + ". " + tasks.get(i));
                }
                System.out.println("    ________________________________________________________");
                continue;
            }

            if (command.equals("mark")) {
                int index = Integer.parseInt(parts[1]) - 1;  // list is 0-based, but users see it as 1-based
                tasks.get(index).markDone();
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      " + tasks.get(index));
                System.out.println("    ________________________________________________________");
                continue;
            }

            if (command.equals("unmark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                tasks.get(index).markUndone();
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("      " + tasks.get(index));
                System.out.println("    ________________________________________________________");
                continue;
            }

            Task task = new Task(input);
            tasks.add(task);
            System.out.println("    added: " + task.getDescription());
            System.out.println("    ________________________________________________________");
        }

        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ________________________________________________________");
        scanner.close();
    }
}
