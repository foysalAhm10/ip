package foybot;

import java.util.Scanner;

import foybot.exception.FoyBotException;
import foybot.instructions.Instruction;
import foybot.io.FoyBotParser;
import foybot.io.Ui;
import foybot.tools.Storage;
import foybot.tools.TaskList;

/**
 * Entry point of the FoyBot application.
 * <p>
 * Coordinates user interaction, instruction execution, and task persistence.
 */
public class FoyBot {
    private final Ui ui;
    private final FoyBotParser parser;
    private final TaskList tasks;
    private final Storage storage;
    private boolean isExit;

    /**
     * Creates a new {@code FoyBot} instance and initializes its components.
     * <p>
     * Previously saved tasks are loaded from storage if available; otherwise,
     * the bot starts fresh with an empty task list.
     */
    public FoyBot() {
        ui = new Ui();
        parser = new FoyBotParser();
        storage = new Storage("data", "foybotSaved.txt");
        TaskList loadedTasks;

        try {
            loadedTasks = new TaskList(storage.load());
        } catch (FoyBotException e) {
            loadedTasks = new TaskList();
            ui.showMessage("Error: " + e.getMessage());
            ui.showError("Could not load saved tasks. Starting fresh.");
        }

        tasks = loadedTasks;
        ui.showWelcome();
    }

    /**
     * Returns whether the last command asked the app to exit.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Returns the welcome message.
     */
    public String getWelcomeMessage() {
        // Only used in the constructor of FoyBot.
        // In the constructor, only ui.showWelcome() appends the welcome text to the Ui buffer.
        // Guaranteed to have only the welcome message at this point of time.
        return ui.consumeOutput();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Instruction instruction = parser.parse(input);
            instruction.execute(tasks, ui);

            if (instruction.isMutating()) {
                storage.save(tasks.getTasks());
            }

            isExit = instruction.isExit();
        } catch (FoyBotException fbe) {
            ui.showError(fbe.getMessage());
        } catch (Exception e) {
            ui.showError("FoyBot missed this error: " + e.getMessage());
        }

        return ui.consumeOutput();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FoyBot foyBot = new FoyBot();

        foyBot.getWelcomeMessage();
        while (!foyBot.isExit()) {
            String input = scanner.nextLine();
            foyBot.getResponse(input);
        }

        scanner.close();
    }
}
