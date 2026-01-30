import foybot.exception.FoyBotException;

import foybot.instructions.Instruction;

import foybot.io.FoyBotOutput;
import foybot.io.FoyBotParser;

import foybot.tools.TaskList;

import java.util.Scanner;

public class FoyBot {
    public static void main(String[] args) {
        FoyBotOutput output = new FoyBotOutput();
        FoyBotParser parser = new FoyBotParser();
        TaskList tasks = new TaskList();
        Scanner scanner = new Scanner(System.in);

        output.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            String input = scanner.nextLine();

            try {
                Instruction instruction = parser.parse(input);
                instruction.execute(tasks, output);
                isExit = instruction.isExit();
            } catch (FoyBotException fbe) {
                output.showError(fbe.getMessage());
            } catch (Exception e) {
                output.showError("FoyBot missed this error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
