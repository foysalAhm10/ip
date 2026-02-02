package foybot.tasks;

import foybot.exception.FoyBotException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    protected LocalDate deadline;

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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
