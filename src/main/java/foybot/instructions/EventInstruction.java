package foybot.instructions;

import foybot.exception.FoyBotException;
import foybot.io.Ui;
import foybot.tasks.EventTask;
import foybot.tools.TaskList;

public class EventInstruction extends Instruction {
    protected EventTask eventTask;

    public EventInstruction(String cleanInput) throws FoyBotException {
        // format of instruction: project meeting /from Mon 2pm /to 4pm
        String[] taskArr1 = cleanInput.split("/from");
        if (taskArr1.length != 2) {
            throw new FoyBotException("Invalid deadline task format! " +
                    "Use: event <description> /from <start> /to <end>");
        }

        String description = taskArr1[0].trim();
        if (description.isEmpty()) {
            throw new FoyBotException("Event description cannot be empty.");
        }

        String[] taskArr2 = taskArr1[1].trim().split("/to");
        if (taskArr2.length != 2) {
            throw new FoyBotException("Invalid deadline task format! " +
                    "Use: event <description> /from <start> /to <end>");
        }

        String fromRaw = taskArr2[0].trim();
        String toRaw = taskArr2[1].trim();
        this.eventTask = new EventTask(description, fromRaw, toRaw);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(this.eventTask);
        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("    " + this.eventTask.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    @Override
    public boolean isMutating() {
        return true;
    }

}
