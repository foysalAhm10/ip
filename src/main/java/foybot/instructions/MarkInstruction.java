package foybot.instructions;

import foybot.exception.FoyBotException;

import foybot.io.FoyBotOutput;

import foybot.tools.TaskList;

public class MarkInstruction extends Instruction {
    protected Integer index;

    public MarkInstruction(String cleanInput) {
        this.index = Integer.parseInt(cleanInput) - 1;
    }

    @Override
    public void execute(TaskList tasks, FoyBotOutput output) throws FoyBotException {
        if (tasks.size() == 0) {
            throw new FoyBotException("There are no tasks to mark.");
        } else if (index > tasks.size() - 1 || index < 0) {
            throw new FoyBotException("Index out of bounds.");
        } else {
            tasks.get(index).markDone();
            output.showLine();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("        " + tasks.get(index));
            output.showLine();
        }
    }
}
