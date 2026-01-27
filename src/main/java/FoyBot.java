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


            if (input.contains("deadline")) { // DeadlineTask
                String[] parts = input.split("/by");

                String firstPart = parts[0].trim();
                String deadline = parts[1].trim();

                String[] firstParts = firstPart.split(" ", 2);
                // String type = firstParts[0];
                String taskDescription = firstParts[1];

                Task task = new DeadlineTask(taskDescription, deadline);
                tasks.add(task);
                // System.out.println("    added: " + task.getDescription());
            } else if (input.contains("event")) { // EventTask
                String[] part1 = input.split("/from");
                String beforeFrom = part1[0].trim();
                String afterFrom = part1[1];

                String[] part2 = afterFrom.split("/to");
                String fromTime = part2[0].trim();
                String toTime = part2[1].trim();

                String[] firstParts = beforeFrom.split(" ", 2);
                // String type = firstParts[0];
                String taskDescription = firstParts[1];

                Task task = new EventTask(taskDescription, fromTime, toTime);
                tasks.add(task);
                // System.out.println("    added: " + task.getDescription());
            } else if (input.contains("todo")) { // TodoTask
                String[] parts = input.split(" ", 2);
                // String type = parts[0];
                String taskDescription = parts[1];

                Task task = new TodoTask(taskDescription);
                tasks.add(task);
                // System.out.println("    added: " + task.getDescription());
            }


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

            String[] parts = input.split(" ");
            String markCommand = parts[0];

            // ignores any commands that are not mark/unmark
            if (markCommand.equals("mark")) {
                int index = Integer.parseInt(parts[1]) - 1;  // list is 0-based, but users see it as 1-based
                tasks.get(index).markDone();
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      " + tasks.get(index));
                System.out.println("    ________________________________________________________");
                continue;
            }

            if (markCommand.equals("unmark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                tasks.get(index).markUndone();
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("      " + tasks.get(index));
                System.out.println("    ________________________________________________________");
                continue;
            }

            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + tasks.get(tasks.size() - 1));
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("    ________________________________________________________");
        }

        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ________________________________________________________");
        scanner.close();
    }
}
