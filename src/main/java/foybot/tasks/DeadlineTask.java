package foybot.tasks;

import foybot.exception.FoyBotException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that must be completed by a specific deadline.
 */
public class DeadlineTask extends Task {
    protected LocalDate deadline;

    /**
     * Creates a deadline task with the given description and deadline.
     *
     * @param description Description of the task.
     * @param deadlineRaw Deadline of the task in yyyy-mm-dd format.
     * @throws FoyBotException If the deadline format is invalid.
     */
    public DeadlineTask(String description, String deadlineRaw) throws FoyBotException {
        super(description);

        try {
            this.deadline = LocalDate.parse(deadlineRaw);
        } catch (DateTimeParseException e) {
            throw new FoyBotException("Invalid date format for deadline! Use: yyyy-mm-dd");
        }

    }

    public String getDeadline() {
        return this.deadline.toString();
    }

    /**
     * {@inheritDoc} <p>
     * Includes the task type and deadline of the task in the string representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
