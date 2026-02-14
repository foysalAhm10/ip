package foybot.instructions;

import foybot.exception.FoyBotException;
import foybot.io.Ui;
import foybot.tools.TaskList;

/**
 * Represents an instruction that marks a task as completed.
 */
public class MarkInstruction extends Instruction {
    protected Integer index;

    /**
     * Creates a mark instruction for the task at the given index.
     *
     * @param cleanInput Index of the task to be marked.
     */
    public MarkInstruction(String cleanInput) {
        this.index = Integer.parseInt(cleanInput) - 1;
    }

    /**
     * {@inheritDoc} <p>
     * Marks the specified task as completed and displays a confirmation.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws FoyBotException {
        if (tasks.size() == 0) {
            throw new FoyBotException("There are no tasks to mark.");
        } else if (index > tasks.size() - 1 || index < 0) {
            throw new FoyBotException("Index out of bounds.");
        } else {
            tasks.get(index).markDone();
            ui.showLine();
            ui.showMessage("Nice! I've marked this task as done:");
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
