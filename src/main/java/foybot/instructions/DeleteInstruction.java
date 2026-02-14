package foybot.instructions;

import foybot.exception.FoyBotException;
import foybot.io.Ui;
import foybot.tasks.Task;
import foybot.tools.TaskList;

/**
 * Represents an instruction that removes a task from the task list.
 */
public class DeleteInstruction extends Instruction {
    protected Integer index;

    /**
     * Creates a delete instruction for the task at the given index.
     *
     * @param cleanInput Index of the task to be deleted.
     */
    public DeleteInstruction(String cleanInput) {
        this.index = Integer.parseInt(cleanInput) - 1;
    }

    /**
     * {@inheritDoc} <p>
     * Removes the specified task from the task list and displays a confirmation.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws FoyBotException {
        if (tasks.size() == 0) {
            throw new FoyBotException("There are no tasks to delete.");
        } else if (index > tasks.size() - 1 || index < 0) {
            throw new FoyBotException("Index out of bounds.");
        } else {
            int oldSize = tasks.size();
            Task deletedTask = tasks.get(index);
            tasks.delete(index);
            assert tasks.size() == oldSize - 1 : "Task list size should decrease by 1 after delete";
            ui.showLine();
            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage(Ui.INDENT + deletedTask.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            ui.showLine();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMutating() {
        return true;
    }
}
