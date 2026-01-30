package foybot.instructions;

import foybot.io.FoyBotOutput;

import foybot.tasks.EventTask;

import foybot.tools.TaskList;

public class EventInstruction extends Instruction {
    protected EventTask eventTask;

    public EventInstruction(String cleanInput) {
        // format of instruction: project meeting /from Mon 2pm /to 4pm
        String[] taskArr1 = cleanInput.split("/from");
        String[] taskArr2 = taskArr1[1].trim().split("/to");
        String description = taskArr1[0].trim();
        String fromTime = taskArr2[0].trim();
        String toTime = taskArr2[1].trim();
        this.eventTask = new EventTask(description, fromTime, toTime);
    }

    @Override
    public void execute(TaskList tasks, FoyBotOutput output) {
        tasks.addTask(this.eventTask);
        output.showLine();
        output.showMessage("Got it. I've added this task:");
        output.showMessage(this.eventTask.toString());
        output.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        output.showLine();
    }
}
