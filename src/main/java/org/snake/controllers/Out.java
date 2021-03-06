package org.snake.controllers;

import javafx.stage.Stage;

public class Out implements StartScene {
    @Override
    public void start(Stage stage) {
        Main.loadScene("out");
    }

    public void handleButtonYesClick() {
        System.exit(0);
    }

    public void handleButtonNoClick() {
        Main.setScene(new Menu());
    }

}
