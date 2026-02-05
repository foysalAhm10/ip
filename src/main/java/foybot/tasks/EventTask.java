package foybot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import foybot.exception.FoyBotException;

/**
 * Represents a task that occurs over a period of time.
 */
public class EventTask extends Task {
    protected LocalDate fromTime;
    protected LocalDate toTime;

    /**
     * Creates an event task with the given description and time period.
     *
     * @param description Description of the task.
     * @param fromRaw     Start date of the event in yyyy-mm-dd format.
     * @param toRaw       End date of the event in yyyy-mm-dd format.
     * @throws FoyBotException If the date format is invalid or the time period is invalid.
     */
    public EventTask(String description, String fromRaw, String toRaw) throws FoyBotException {
        super(description);

        try {
            this.fromTime = LocalDate.parse(fromRaw);
            this.toTime = LocalDate.parse(toRaw);
        } catch (DateTimeParseException e) {
            throw new FoyBotException("Invalid date format for event time! Use: yyyy-mm-dd");
        }

        if (fromTime.isAfter(toTime)) {
            throw new FoyBotException("Event start date must be before or equal to end date.");
        }
    }

    public String getFromTo() {
        return this.fromTime + " - " + this.toTime;
    }

    private String formatDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * {@inheritDoc} <p>
     * Includes the task type and event time period in the string representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(fromTime) + " to: " + formatDate(toTime) + ")";
    }
}