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
    public static void main(String[] args) {
        Ui ui = new Ui();
        FoyBotParser parser = new FoyBotParser();
        TaskList tasks;
        Scanner scanner = new Scanner(System.in);

        Storage storage = new Storage("data", "foybotSaved.txt");

        try {
            tasks = new TaskList(storage.load());
        } catch (FoyBotException e) {
            System.out.println("Error: " + e.getMessage());
            tasks = new TaskList();
            ui.showError("Could not load saved tasks. Starting fresh.");
        }

        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            String input = scanner.nextLine();

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
        }

        scanner.close();
    }
}
