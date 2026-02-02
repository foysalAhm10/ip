package foybot.tasks;

import foybot.exception.FoyBotException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    protected LocalDate fromTime;
    protected LocalDate toTime;

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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(fromTime) + " to: " + formatDate(toTime) + ")";
    }
}