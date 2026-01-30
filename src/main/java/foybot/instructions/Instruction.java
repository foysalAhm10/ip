package foybot.instructions;

import foybot.exception.FoyBotException;

import foybot.io.FoyBotOutput;

import foybot.tools.TaskList;

public abstract class Instruction {
    public abstract void execute(TaskList tasks, FoyBotOutput output) throws FoyBotException;

    public boolean isExit() {
        return false;
    }
}
