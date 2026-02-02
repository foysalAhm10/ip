package foybot.instructions;

import foybot.exception.FoyBotException;

import foybot.io.Ui;

import foybot.tools.TaskList;

public abstract class Instruction {
    public abstract void execute(TaskList tasks, Ui ui) throws FoyBotException;

    public boolean isExit() {
        return false;
    }

    public boolean isMutating() {
        return false;
    }
}
