package foybot.io;

import foybot.tools.TaskList;

/**
 * Handles all user interface output for foybot.FoyBot.
 */
public class Ui {
    public static final String INDENT = "    ";
    private static final String LINE = INDENT + "____________________________________________________________";
    private final StringBuilder output = new StringBuilder();

    private void appendLine(String line) {
        output.append(line).append(System.lineSeparator());
    }

    /**
     * Displays a separator line.
     */
    public void showLine() {
        appendLine(LINE);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        appendLine(INDENT + "Hello! I'm FoyBot!");
        appendLine(INDENT + "What can I do for you?");
        showLine();
    }

    /**
     * Displays the farewell message to the user.
     */
    public void showBye() {
        appendLine(INDENT + "Bye. Hope to see you again soon!");
        showLine();
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
        showLine();
        appendLine(INDENT + message);
        showLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        appendLine(INDENT + message);
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
