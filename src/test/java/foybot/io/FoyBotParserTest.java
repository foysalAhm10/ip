package foybot.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import foybot.exception.FoyBotException;
import foybot.instructions.ByeInstruction;
import foybot.instructions.DeadlineInstruction;
import foybot.instructions.DeleteInstruction;
import foybot.instructions.EventInstruction;
import foybot.instructions.Instruction;
import foybot.instructions.ListInstruction;
import foybot.instructions.MarkInstruction;
import foybot.instructions.TodoInstruction;
import foybot.instructions.UnmarkInstruction;

public class FoyBotParserTest {

    private final FoyBotParser parser = new FoyBotParser();

    private void assertParseThrows(String input, String expectedMessage) {
        FoyBotException ex = assertThrows(FoyBotException.class, () -> parser.parse(input));
        assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    public void parse_emptyString_throwsException() {
        assertParseThrows(
                "",
                "No task found. Please let me do something for you!"
        );
    }

    @Test
    public void parse_whitespaceOnly_throwsException() {
        assertParseThrows(
                "     ",
                "No task found. Please let me do something for you!"
        );
    }

    @Test
    public void parse_bye_returnsByeInstruction() throws Exception {
        Instruction instruction = parser.parse("bye");
        assertInstanceOf(ByeInstruction.class, instruction);
    }

    @Test
    public void parse_list_returnsListInstruction() throws Exception {
        Instruction instruction = parser.parse("list");
        assertInstanceOf(ListInstruction.class, instruction);
    }

    @Test
    public void parse_listWithExtraArgs_throwsException() {
        assertParseThrows(
                "list now",
                "im confused! To list, just type: list."
        );
    }

    @Test
    public void parse_todoWithDesc_returnsTodoInstruction() throws Exception {
        Instruction instruction = parser.parse("todo read book");
        assertInstanceOf(TodoInstruction.class, instruction);
    }

    @Test
    public void parse_todoNoDesc_throwsException() {
        assertParseThrows(
                "todo",
                "todo...what exactly? No todo task description found!"
        );
    }

    @Test
    public void parse_deadlineWithArgs_returnsDeadlineInstruction() throws Exception {
        Instruction instruction = parser.parse("deadline submit report /by 2026-02-10");
        assertInstanceOf(DeadlineInstruction.class, instruction);
    }

    @Test
    public void parse_deadlineNoDesc_throwsException() {
        assertParseThrows(
                "deadline",
                "deadline or no-line? No deadline task description found!"
        );
    }

    @Test
    public void parse_eventWithArgs_returnsEventInstruction() throws Exception {
        Instruction instruction = parser.parse("event camp /from 2026-02-10 /to 2026-02-12");
        assertInstanceOf(EventInstruction.class, instruction);
    }

    @Test
    public void parse_eventNoDesc_throwsException() {
        assertParseThrows(
                "event",
                "am i not invited? No event task description found!"
        );
    }

    @Test
    public void parse_markWithIndex_returnsMarkInstruction() throws Exception {
        Instruction instruction = parser.parse("mark 1");
        assertInstanceOf(MarkInstruction.class, instruction);
    }

    @Test
    public void parse_markNoIndex_throwsException() {
        assertParseThrows(
                "mark",
                "im confused! State which task to mark."
        );
    }

    @Test
    public void parse_unmarkWithIndex_returnsUnmarkInstruction() throws Exception {
        Instruction instruction = parser.parse("unmark 2");
        assertInstanceOf(UnmarkInstruction.class, instruction);
    }

    @Test
    public void parse_unmarkNoIndex_throwsException() {
        assertParseThrows(
                "unmark",
                "im confused! State which task to unmark."
        );
    }

    @Test
    public void parse_deleteWithIndex_returnsDeleteInstruction() throws Exception {
        Instruction instruction = parser.parse("delete 3");
        assertInstanceOf(DeleteInstruction.class, instruction);
    }

    @Test
    public void parse_deleteNoIndex_throwsException() {
        assertParseThrows(
                "delete",
                "im confused! State which task to delete."
        );
    }

    @Test
    public void parse_unknownKeyword_throwsException() {
        assertParseThrows(
                "banana",
                "OOPS!!! I don't understand this instruction yet :-("
        );
    }

    @Test
    public void parse_trimsInput_stillParsesCorrectly() throws Exception {
        Instruction instruction = parser.parse("   todo   read book   ");
        assertInstanceOf(TodoInstruction.class, instruction);
    }
}
