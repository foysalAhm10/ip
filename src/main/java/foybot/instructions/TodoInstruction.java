package foybot.instructions;

import foybot.io.Ui;
import foybot.tasks.TodoTask;
import foybot.tools.TaskList;

/**
 * Represents an instruction that adds a todo task to the task list.
 */
public class TodoInstruction extends Instruction {
    protected TodoTask todoTask;

    /**
     * Creates a todo instruction and prepares the corresponding todo task
     * from the given input.
     *
     * @param cleanInput Description of the todo task.
     */
    public TodoInstruction(String cleanInput) {
        this.todoTask = new TodoTask(cleanInput);
    }

    /**
     * {@inheritDoc} <p>
     * Adds the prepared todo task to the task list and displays a confirmation.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(todoTask);
        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("    " + todoTask.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMutating() {
        return true;
    }
}
