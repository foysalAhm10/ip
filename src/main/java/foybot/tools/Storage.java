package foybot.tools;

import foybot.exception.FoyBotException;

import foybot.tasks.Task;
import foybot.tasks.DeadlineTask;
import foybot.tasks.EventTask;
import foybot.tasks.TodoTask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String folder, String fileName) {
        this.filePath = Paths.get(folder, fileName);
    }

    public ArrayList<Task> load() throws FoyBotException {
        try {
            ensureFolderExists();

            // First run: no file yet
            if (!Files.exists(filePath)) {
                return new ArrayList<>();
            }

            List<String> lines = Files.readAllLines(filePath);
            ArrayList<Task> tasks = new ArrayList<>();

            for (String line : lines) {
                line = line.trim();
                if (!line.isEmpty()) {
                    tasks.add(parseLineToTask(line));
                }
            }

            return tasks;

        } catch (IOException e) {
            throw new FoyBotException("Error loading file: " + e.getMessage());
        }
    }

    public void save(ArrayList<Task> tasks) throws FoyBotException {
        try {
            ensureFolderExists();

            ArrayList<String> lines = new ArrayList<>();
            for (Task t : tasks) {
                lines.add(taskToLine(t));
            }

            Files.write(filePath, lines);

        } catch (IOException e) {
            throw new FoyBotException("Error saving file: " + e.getMessage());
        }
    }

    private void ensureFolderExists() throws IOException {
        Path parent = filePath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
    }

    private Task parseLineToTask(String line) throws FoyBotException {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            throw new FoyBotException("Corrupted line: " + line);
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String rest = parts[2];

        Task task;

        switch (type) {
        case "T":
            task = new TodoTask(rest);
            break;
        case "D":
            if (parts.length < 4) {
                throw new FoyBotException("Corrupted deadline task: " + line);
            }
            task = new DeadlineTask(rest, parts[3]);
            break;
        case "E":
            if (parts.length < 4) {
                throw new FoyBotException("Corrupted event task: " + line);
            }
            String[] duration = parts[3].split(" - ");
            if  (duration.length != 2) {
                throw new FoyBotException("Corrupted event task (event duration invalid): " + line);
            }
            task = new EventTask(rest, duration[0], duration[1]);
            break;
        default:
            throw new FoyBotException("Unknown task type: " + type);
        }

        if (isDone) {
            task.markDone();
        }

        return task;
    }

    private String taskToLine(Task task) throws FoyBotException {
        String doneFlag = task.isDone() ? "1" : "0";

        if (task instanceof TodoTask) {
            return "T | " + doneFlag + " | " + task.getDescription();
        }

        if (task instanceof DeadlineTask) {
            DeadlineTask dTask = (DeadlineTask) task;
            return "D | " + doneFlag + " | "
                    + dTask.getDescription() + " | " + dTask.getDeadline();
        }

        if (task instanceof EventTask) {
            EventTask eTask = (EventTask) task;
            return "E | " + doneFlag + " | " + eTask.getDescription() + " | " + eTask.getFromTo();
        }

        throw new FoyBotException("Unknown task type while saving.");
    }
}