package foybot.gui;

import foybot.FoyBot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private FoyBot foyBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image foyBotImage = new Image(this.getClass().getResourceAsStream("/images/DaBot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the foybot.FoyBot instance */
    public void setFoyBot(FoyBot fb) {
        foyBot = fb;
        String welcome = foyBot.getWelcomeMessage();
        if (!welcome.isEmpty()) {
            dialogContainer.getChildren().add(
                    DialogBox.getFoyBotDialog(welcome, foyBotImage)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing FoyBot's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = foyBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFoyBotDialog(response, foyBotImage)
        );
        userInput.clear();

        if (foyBot.isExit()) {
            dialogContainer.getScene().getWindow().hide();
        }
    }
}
