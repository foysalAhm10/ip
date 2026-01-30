public class ByeInstruction extends Instruction {
    @Override
    public void execute(TaskList tasks, FoyBotOutput output) {
        output.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
