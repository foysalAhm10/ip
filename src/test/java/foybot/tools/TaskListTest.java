package foybot.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import foybot.tasks.Task;
import foybot.tasks.TodoTask;

public class TaskListTest {

    @Test
    public void addGetDelete_updatesListCorrectly() {
        TaskList list = new TaskList();
        Task task1 = new TodoTask("read book");
        Task task2 = new TodoTask("write tests");

        list.addTask(task1);
        list.addTask(task2);
        assertEquals(2, list.size());
        assertEquals(task1, list.get(0));
        assertEquals(task2, list.get(1));

        list.delete(0);
        assertEquals(1, list.size());
        assertEquals(task2, list.get(0));
    }

    @Test
    public void getTasks_returnsCopy() {
        TaskList list = new TaskList();
        list.addTask(new TodoTask("read book"));

        ArrayList<Task> snapshot = list.getTasks();
        assertNotSame(snapshot, list.getTasks());

        snapshot.clear();
        assertEquals(1, list.size());
    }

    @Test
    public void findTasks_caseInsensitiveAndMatchesDescriptions() {
        TaskList list = new TaskList();
        list.addTask(new TodoTask("Read Book"));
        list.addTask(new TodoTask("read notes"));
        list.addTask(new TodoTask("write tests"));

        ArrayList<Task> matches = list.findTasks("READ");
        assertEquals(2, matches.size());
    }
}
