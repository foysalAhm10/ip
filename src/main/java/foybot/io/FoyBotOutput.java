package foybot.io;

import foybot.tools.TaskList;

public class FoyBotOutput {
    private static final String LINE = "    ____________________________________________________________";

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        showLine();
        System.out.println("    Hello! I'm FoyBot!");
        System.out.println("    What can I do for you?");
        showLine();
    }

    public void showBye() {
        System.out.println("    Bye. Hope to see you again soon!");
        showLine();
    }

    public void showList(TaskList tasks) {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i+1) + ". " + tasks.get(i));
        }
    }

    public void showError(String message) {
        showLine();
        System.out.println("     " + message);
        showLine();
    }

    public void showMessage(String message) {
        System.out.println("    " + message);
    }
}

