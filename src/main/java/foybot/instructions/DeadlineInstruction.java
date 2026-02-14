package foybot.instructions;

import foybot.exception.FoyBotException;
import foybot.io.Ui;
import foybot.tasks.DeadlineTask;
import foybot.tools.TaskList;

/**
 * Represents an instruction that adds a valid deadline task to the task list.
 */
public class DeadlineInstruction extends Instruction {
    protected DeadlineTask deadlineTask;

    /**
     * Creates a deadline instruction and prepares the corresponding deadline task
     * from the given input.
     *
     * @param cleanInput User input containing the task description and deadline.
     * @throws FoyBotException If the input format is invalid.
     */
    public DeadlineInstruction(String cleanInput) throws FoyBotException {
        // format of instruction: create website /by 2026-03-01
        String[] parts = cleanInput.split("/by");

        if (parts.length != 2) {
            throw new FoyBotException("Invalid deadline task format! "
                    + "Use: deadline <description> /by <date>");
        }

        String description = parts[0].trim();
        String deadlineRaw = parts[1].trim();

        if (description.isEmpty() || deadlineRaw.isEmpty()) {
            throw new FoyBotException("Description or deadline missing!");
        }

        this.deadlineTask = new DeadlineTask(description, deadlineRaw);
    }

    /**
     * {@inheritDoc} <p>
     * Adds the prepared deadline task to the task list and displays a confirmation.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(this.deadlineTask);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(Ui.INDENT + this.deadlineTask.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMutating() {
        return true;
    }
}
