package foybot.instructions;

import foybot.io.Ui;
import foybot.tools.TaskList;

/**
 * Represents an instruction that displays all tasks in the task list.
 */
public class ListInstruction extends Instruction {
    /**
     * {@inheritDoc} <p>
     * Displays the tasks currently in the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        if (tasks.size() < 1) {
            ui.showMessage("No tasks in the list currently.");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            ui.showList(tasks);
        }
    }
}
