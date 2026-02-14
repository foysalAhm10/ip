package foybot.tools;

import java.util.ArrayList;
import java.util.stream.Collectors;

import foybot.tasks.Task;

/**
 * Represents a collection of tasks and provides operations on them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        // Constructor should never receive a null backing list.
        assert tasks != null : "TaskList backing list should not be null";
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        assert task != null : "Task to add should not be null";
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void delete(int i) {
        tasks.remove(i);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns tasks whose descriptions contain the given keyword.
     *
     * @param keyword Keyword used to search for tasks.
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null : "Find keyword should not be null";
        // filter tasks that match the keyword (case-insensitive), then collect into a new list.
        return tasks.stream()
                .filter(t -> t.toString().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
