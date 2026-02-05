package foybot.instructions;

import foybot.exception.FoyBotException;
import foybot.io.Ui;
import foybot.tasks.DeadlineTask;
import foybot.tools.TaskList;

public class DeadlineInstruction extends Instruction {
    protected DeadlineTask deadlineTask;

    public DeadlineInstruction(String cleanInput) throws FoyBotException {
        // format of instruction: create website /by 2026-03-01
        String[] parts = cleanInput.split("/by");

        if (parts.length != 2) {
            throw new FoyBotException("Invalid deadline task format! " +
                    "Use: deadline <description> /by <date>");
        }

        String description = parts[0].trim();
        String deadlineRaw = parts[1].trim();

        if (description.isEmpty() || deadlineRaw.isEmpty()) {
            throw new FoyBotException("Description or deadline missing!");
        }

        this.deadlineTask = new DeadlineTask(description, deadlineRaw);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(this.deadlineTask);
        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("    " + this.deadlineTask.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    @Override
    public boolean isMutating() {
        return true;
    }

}
