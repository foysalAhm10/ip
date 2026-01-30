package foybot.instructions;

import foybot.io.FoyBotOutput;

import foybot.tasks.DeadlineTask;

import foybot.tools.TaskList;

public class DeadlineInstruction extends Instruction {
    protected DeadlineTask deadlineTask;

    public DeadlineInstruction(String cleanInput) {
        // format of instruction: do homework /by no idea
        String[] parts = cleanInput.split("/by");
        String description = parts[0];
        String deadline = parts[1];
        this.deadlineTask = new DeadlineTask(description, deadline);
    }

    @Override
    public void execute(TaskList tasks, FoyBotOutput output) {
        tasks.addTask(this.deadlineTask);
        output.showLine();
        output.showMessage("Got it. I've added this task:");
        output.showMessage(this.deadlineTask.toString());
        output.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        output.showLine();
    }
}
