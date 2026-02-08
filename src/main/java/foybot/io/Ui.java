package foybot.io;

import foybot.tools.TaskList;

/**
 * Handles all user interface output for FoyBot.
 */
public class Ui {
    private static final String LINE = "    ____________________________________________________________";

    /**
     * Displays a separator line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("    Hello! I'm FoyBot!");
        System.out.println("    What can I do for you?");
        showLine();
    }

    /**
     * Displays the farewell message to the user.
     */
    public void showBye() {
        System.out.println("    Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays all tasks in the given task list.
     *
     * @param tasks The task list to be displayed.
     */
    public void showList(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        showLine();
        System.out.println("     " + message);
        showLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println("    " + message);
    }
}

