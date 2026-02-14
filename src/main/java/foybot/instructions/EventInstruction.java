package foybot.instructions;

import foybot.exception.FoyBotException;
import foybot.io.Ui;
import foybot.tasks.EventTask;
import foybot.tools.TaskList;

/**
 * Represents an instruction that adds an event task to the task list.
 */
public class EventInstruction extends Instruction {
    protected EventTask eventTask;

    /**
     * Creates an event instruction and prepares the corresponding event task
     * from the given input.
     *
     * @param cleanInput User input containing the task description and time period.
     * @throws FoyBotException If the input format is invalid.
     */
    public EventInstruction(String cleanInput) throws FoyBotException {
        // format of instruction: project meeting /from Mon 2pm /to 4pm
        String[] taskArr1 = cleanInput.split("/from");
        if (taskArr1.length != 2) {
            throw new FoyBotException("Invalid deadline task format! "
                    + "Use: event <description> /from <start> /to <end>");
        }

        String description = taskArr1[0].trim();
        if (description.isEmpty()) {
            throw new FoyBotException("Event description cannot be empty.");
        }

        String[] taskArr2 = taskArr1[1].trim().split("/to");
        if (taskArr2.length != 2) {
            throw new FoyBotException("Invalid deadline task format! "
                    + "Use: event <description> /from <start> /to <end>");
        }

        String fromRaw = taskArr2[0].trim();
        String toRaw = taskArr2[1].trim();
        this.eventTask = new EventTask(description, fromRaw, toRaw);
    }

    /**
     * {@inheritDoc} <p>
     * Adds the prepared event task to the task list and displays a confirmation.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(this.eventTask);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(Ui.INDENT + this.eventTask.toString());
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
