public abstract class Instruction {
    public abstract void execute(TaskList tasks, FoyBotOutput output) throws FoyBotException;

    public boolean isExit() {
        return false;
    }
}
