package foybot.io;

import foybot.exception.FoyBotException;
import foybot.instructions.ByeInstruction;
import foybot.instructions.DeadlineInstruction;
import foybot.instructions.DeleteInstruction;
import foybot.instructions.EventInstruction;
import foybot.instructions.FindInstruction;
import foybot.instructions.Instruction;
import foybot.instructions.ListInstruction;
import foybot.instructions.MarkInstruction;
import foybot.instructions.TodoInstruction;
import foybot.instructions.UnmarkInstruction;

public class FoyBotParser {

    public Instruction parse(String input) throws FoyBotException {
        input = input.trim();

        if (input.isEmpty()) {
            throw new FoyBotException("No task found. Please let me do something for you!");
        }

        String[] parts = input.split(" ", 2);
        String keyword = parts[0];
        String rest = (parts.length < 2) ? "" : parts[1].trim();

        switch (keyword) {
        case "bye":
            return new ByeInstruction();

        case "todo":
            if (rest.isEmpty()) {
                throw new FoyBotException("todo...what exactly? No todo task description found!");
            }
            return new TodoInstruction(rest);

        case "deadline":
            if (rest.isEmpty()) {
                throw new FoyBotException("deadline or no-line? No deadline task description found!");
            }
            return new DeadlineInstruction(rest);

        case "event":
            if (rest.isEmpty()) {
                throw new FoyBotException("am i not invited? No event task description found!");
            }
            return new EventInstruction(rest);

        case "list":
            if (!rest.isEmpty()) {
                throw new FoyBotException("im confused! To list, just type: list.");
            }
            return new ListInstruction();

        case "mark":
            if (rest.isEmpty()) {
                throw new FoyBotException("im confused! State which task to mark.");
            }
            return new MarkInstruction(rest);

        case "unmark":
            if (rest.isEmpty()) {
                throw new FoyBotException("im confused! State which task to unmark.");
            }
            return new UnmarkInstruction(rest);

        case "delete":
            if (rest.isEmpty()) {
                throw new FoyBotException("im confused! State which task to delete.");
            }
            return new DeleteInstruction(rest);

        case "find":
            if (rest.isEmpty()) {
                throw new FoyBotException("im confused! State a keyword that identify the tasks.");
            }
            return new FindInstruction(rest);

        default:
            throw new FoyBotException("OOPS!!! I don't understand this instruction yet :-(");
        }
    }
}

