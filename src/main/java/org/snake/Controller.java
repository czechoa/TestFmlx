package org.snake;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller implements Initializable {
    public Button button;
    public Button buttonOut;
    public Label firstLabel;
    String message = "";
    private Stage stage;

    public void handleButtonClick(ActionEvent actionEvent) throws IOException {
        App.setScene(new AppSnake());
    }

    public void handleButtonOutClick(ActionEvent actionEvent) throws IOException {
        System.exit(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("loading user data ..  "); // wykonuje sie od razu
    }
}
