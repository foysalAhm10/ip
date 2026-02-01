package foybot.instructions;

import foybot.exception.FoyBotException;
import foybot.io.FoyBotOutput;
import foybot.tasks.Task;
import foybot.tools.TaskList;

public class DeleteInstruction extends Instruction {
    protected Integer index;

    public DeleteInstruction(String cleanInput) {
        this.index = Integer.parseInt(cleanInput) - 1;
    }

    @Override
    public void execute(TaskList tasks, FoyBotOutput output) throws FoyBotException {
        if (tasks.size() == 0) {
            throw new FoyBotException("There are no tasks to delete.");
        } else if (index > tasks.size() - 1 || index < 0) {
            throw new FoyBotException("Index out of bounds.");
        } else {
            Task deletedTask = tasks.get(index);
            tasks.delete(index);
            output.showLine();
            output.showMessage("Noted. I've removed this task:");
            output.showMessage("    " + deletedTask.toString());
            output.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            output.showLine();
        }
    }

    @Override
    public boolean isMutating() {
        return true;
    }
}
