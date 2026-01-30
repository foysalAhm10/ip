public class TodoInstruction extends Instruction {
    protected TodoTask todoTask;

    public TodoInstruction(String cleanInput) {
        this.todoTask = new TodoTask(cleanInput);
    }

    @Override
    public void execute(TaskList tasks, FoyBotOutput output) {
        tasks.addTask(todoTask);
        output.showLine();
        output.showMessage("Got it. I've added this task:");
        output.showMessage(todoTask.toString());
        output.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        output.showLine();
    }
}
