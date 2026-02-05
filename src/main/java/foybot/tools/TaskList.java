package foybot.tools;

import java.util.ArrayList;
import java.util.stream.Collectors;

import foybot.tasks.Task;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
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

    public ArrayList<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(t -> t.toString().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
