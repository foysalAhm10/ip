package foybot.instructions;

import foybot.io.Ui;
import foybot.tasks.TodoTask;
import foybot.tools.TaskList;

public class TodoInstruction extends Instruction {
    protected TodoTask todoTask;

    public TodoInstruction(String cleanInput) {
        this.todoTask = new TodoTask(cleanInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(todoTask);
        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("    " + todoTask.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    @Override
    public boolean isMutating() {
        return true;
    }

}
