package foybot.instructions;

import foybot.io.Ui;
import foybot.tools.TaskList;

/**
 * Represents an instruction that shows help text for supported instructions.
 */
public class HelpInstruction extends Instruction {
    /**
     * {@inheritDoc} <p>
     * Displays app summary and supported instructions formats.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showLine();
        ui.showMessage("You asked for help? That's my favorite activity!");
        ui.showMessage("FoyBot helps you track tasks and deadlines.\n");

        ui.showMessage("Supported instructions:");

        ui.showMessage("1. list");
        ui.showMessage(Ui.INDENT + "- show all current tasks");

        ui.showMessage("2. todo <description>");
        ui.showMessage(Ui.INDENT + "- add a todo task");

        ui.showMessage("3. deadline <description> /by <yyyy-mm-dd>");
        ui.showMessage(Ui.INDENT + "- add a task with a deadline");

        ui.showMessage("4. event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
        ui.showMessage(Ui.INDENT + "- add an event task");

        ui.showMessage("5. mark <task number>");
        ui.showMessage(Ui.INDENT + "- mark a task as done");

        ui.showMessage("6. unmark <task number>");
        ui.showMessage(Ui.INDENT + "- mark a task as not done");

        ui.showMessage("7. delete <task number>");
        ui.showMessage(Ui.INDENT + "- remove a task");

        ui.showMessage("8. find <keyword>");
        ui.showMessage(Ui.INDENT + "- search tasks by keyword");

        ui.showMessage("9. help");
        ui.showMessage(Ui.INDENT + "- show this help message");

        ui.showMessage("10. bye");
        ui.showMessage(Ui.INDENT + "- exit FoyBot\n");

        ui.showMessage("Use one of the instructions now!");
        ui.showLine();
    }
}
