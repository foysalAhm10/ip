package foybot.io;

import foybot.tools.TaskList;

/**
 * Handles all user interface output for foybot.FoyBot.
 */
public class Ui {
    public static final String INDENT = "        ";
    public static final String ERROR_PREFIX = "[ERROR]";
    private final StringBuilder output = new StringBuilder();

    private void appendLine(String line) {
        output.append(line).append(System.lineSeparator());
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        appendLine("Hello! I'm FoyBot!");
        appendLine("What can I do for you?");
    }

    /**
     * Displays the farewell message to the user.
     */
    public void showBye() {
        appendLine("Bye. Hope to see you again soon!");
    }

    /**
     * Displays all tasks in the given task list.
     *
     * @param tasks The task list to be displayed.
     */
    public void showList(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            appendLine(INDENT + (i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        appendLine(ERROR_PREFIX + " " + message);
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        appendLine(message);
    }

    /**
     * Returns accumulated output and clears the buffer.
     *
     * @return Output text ready for display.
     */
    public String consumeOutput() {
        String content = output.toString();
        output.setLength(0);
        return content.stripTrailing();
    }
}
