package foybot.tasks;

/**
 * Represents a generic task with a description and completion status.
 * Intended to be extended by specific task types.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        assert description != null : "Task description should not be null";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return True if the task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the string representation of the task.
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X]" : "[ ]")
                + " " + this.description;
    }
}
