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
            output.showMark(tasks, this.index);
        }

    }
}
