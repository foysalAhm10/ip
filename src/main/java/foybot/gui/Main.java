package foybot.gui;

import java.io.IOException;

import foybot.FoyBot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for FoyBot using FXML.
 */
public class Main extends Application {

    private final FoyBot foyBot = new FoyBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinWidth(500.0);
            stage.setMinHeight(600.0);
            fxmlLoader.<MainWindow>getController().setFoyBot(foyBot); // inject the FoyBot instance
            stage.setTitle("FoyBot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
