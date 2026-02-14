package foybot.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import foybot.exception.FoyBotException;
import foybot.tasks.DeadlineTask;
import foybot.tasks.EventTask;
import foybot.tasks.Task;
import foybot.tasks.TodoTask;

public class StorageTest {

    private static class StubTask extends Task {
        StubTask(String description) {
            super(description);
        }
    }

    @Test
    public void load_noFile_returnsEmptyList() throws Exception {
        Path tempDir = Files.createTempDirectory("foybot-test");
        Storage storage = new Storage(tempDir.toString(), "tasks.txt");

        ArrayList<Task> tasks = storage.load();
        assertEquals(0, tasks.size());
    }

    @Test
    public void saveAndLoad_roundTrip_preservesTaskData() throws Exception {
        Path tempDir = Files.createTempDirectory("foybot-test");
        Storage storage = new Storage(tempDir.toString(), "tasks.txt");

        ArrayList<Task> original = new ArrayList<>();
        TodoTask todo = new TodoTask("read book");
        DeadlineTask deadline = new DeadlineTask("submit report", "2026-02-10");
        EventTask event = new EventTask("camp", "2026-02-10", "2026-02-12");
        deadline.markDone();

        original.add(todo);
        original.add(deadline);
        original.add(event);

        storage.save(original);

        ArrayList<Task> loaded = storage.load();
        assertEquals(3, loaded.size());
        assertInstanceOf(TodoTask.class, loaded.get(0));
        assertInstanceOf(DeadlineTask.class, loaded.get(1));
        assertInstanceOf(EventTask.class, loaded.get(2));
        assertEquals("[T][ ] read book", loaded.get(0).toString());
        assertEquals("[D][X] submit report (by: Feb 10 2026)", loaded.get(1).toString());
        assertEquals("[E][ ] camp (from: Feb 10 2026 to: Feb 12 2026)", loaded.get(2).toString());
    }

    @Test
    public void load_corruptedLine_throwsException() throws Exception {
        Path tempDir = Files.createTempDirectory("foybot-test");
        Path filePath = tempDir.resolve("tasks.txt");
        Files.write(filePath, java.util.List.of("BAD LINE"));

        Storage storage = new Storage(tempDir.toString(), "tasks.txt");

        FoyBotException ex = assertThrows(FoyBotException.class, storage::load);
        assertEquals("Corrupted line: BAD LINE", ex.getMessage());
    }

    @Test
    public void load_unknownTaskType_throwsException() throws Exception {
        Path tempDir = Files.createTempDirectory("foybot-test");
        Path filePath = tempDir.resolve("tasks.txt");
        Files.write(filePath, java.util.List.of("X | 0 | unknown"));

        Storage storage = new Storage(tempDir.toString(), "tasks.txt");

        FoyBotException ex = assertThrows(FoyBotException.class, storage::load);
        assertEquals("Unknown task type: X", ex.getMessage());
    }

    @Test
    public void save_unknownTaskType_throwsException() throws Exception {
        Path tempDir = Files.createTempDirectory("foybot-test");
        Storage storage = new Storage(tempDir.toString(), "tasks.txt");

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new StubTask("unknown"));

        FoyBotException ex = assertThrows(FoyBotException.class, () -> storage.save(tasks));
        assertEquals("Unknown task type while saving.", ex.getMessage());
    }
}
