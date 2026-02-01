package foybot.instructions;

import foybot.exception.FoyBotException;

import foybot.io.FoyBotOutput;

import foybot.tools.TaskList;

public class UnmarkInstruction extends Instruction {
    protected Integer index;

    public UnmarkInstruction(String cleanInput) {
        this.index = Integer.parseInt(cleanInput) - 1;
    }

    @Override
    public void execute(TaskList tasks, FoyBotOutput output) throws FoyBotException {
        if (tasks.size() == 0) {
            throw new FoyBotException("There are no tasks to unmark.");
        } else if (index > tasks.size() - 1 || index < 0) {
            throw new FoyBotException("Index out of bounds.");
        } else {
            tasks.get(index).markUndone();
            output.showLine();
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("        " + tasks.get(index));
            output.showLine();
        }

    }

    @Override
    public boolean isMutating() {
        return true;
    }

}