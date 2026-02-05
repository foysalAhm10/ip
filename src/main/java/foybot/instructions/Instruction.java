package foybot.instructions;

import foybot.exception.FoyBotException;

import foybot.io.Ui;

import foybot.tools.TaskList;

/**
 * Represents a user instruction that can be executed on a task list.
 * Intended to be extended by specific instruction types.
 */
public abstract class Instruction {
    /**
     * Executes the instruction using the given task list and user interface.
     *
     * @param tasks The task list to operate on.
     * @param ui The user interface for displaying output.
     * @throws FoyBotException If the instruction cannot be executed.
     */
    public abstract void execute(TaskList tasks, Ui ui) throws FoyBotException;

    /**
     * Returns whether this instruction signals the program to exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns whether this instruction modifies the task list.
     */
    public boolean isMutating() {
        return false;
    }
}
