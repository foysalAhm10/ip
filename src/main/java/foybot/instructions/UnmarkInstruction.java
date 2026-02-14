package foybot.instructions;

import foybot.exception.FoyBotException;
import foybot.io.Ui;
import foybot.tools.TaskList;

/**
 * Represents an instruction that marks a task as not completed.
 */
public class UnmarkInstruction extends Instruction {
    protected Integer index;

    /**
     * Creates an unmark instruction for the task at the given index.
     *
     * @param cleanInput Index of the task to be unmarked.
     */
    public UnmarkInstruction(String cleanInput) {
        this.index = Integer.parseInt(cleanInput) - 1;
    }

    /**
     * {@inheritDoc} <p>
     * Marks the specified task as not completed and displays a confirmation.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws FoyBotException {
        if (tasks.size() == 0) {
            throw new FoyBotException("There are no tasks to unmark.");
        } else if (index > tasks.size() - 1 || index < 0) {
            throw new FoyBotException("Index out of bounds.");
        } else {
            tasks.get(index).markUndone();
            ui.showLine();
            ui.showMessage("OK, I've marked this task as not done yet:");
            ui.showMessage(Ui.INDENT + tasks.get(index));
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
