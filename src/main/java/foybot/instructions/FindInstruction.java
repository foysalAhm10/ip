package foybot.instructions;

import java.util.ArrayList;

import foybot.exception.FoyBotException;
import foybot.io.Ui;
import foybot.tasks.Task;
import foybot.tools.TaskList;

/**
 * Represents an instruction that searches for tasks containing a keyword.
 */
public class FindInstruction extends Instruction {
    protected String keyword;

    /**
     * Creates a find instruction with the given keyword.
     *
     * @param keyword Keyword used to search for matching tasks.
     */
    public FindInstruction(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc} <p>
     * Displays tasks whose descriptions contain the given keyword.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws FoyBotException {
        ArrayList<Task> matches = tasks.findTasks(keyword);
        assert matches.stream().allMatch(
                t -> t.toString().toLowerCase().contains(keyword.toLowerCase()))
                : "All find results should contain the search keyword";

        if (matches.isEmpty()) {
            ui.showMessage("No matching tasks found.");
            return;
        }

        ui.showMessage("Here are the matching tasks in your list:");
        ui.showList(new TaskList(matches));
    }
}
