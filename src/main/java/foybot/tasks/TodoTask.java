package foybot.tasks;

/**
 * Represents a todo task.
 */
public class TodoTask extends Task {
    /**
     * Creates a todo task with the given description.
     *
     * @param description Description of the task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * {@inheritDoc} <p>
     * Includes the task type in the string representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
