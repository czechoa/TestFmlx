package org.snake;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class App extends Application {
    private int boardXSize = 500;
    private int boardYSize = 500;
    @Override
    public void start(Stage stage) {
        HBox hBox = new HBox();
        Canvas canvas = new Canvas(boardXSize,boardYSize);

        hBox.getChildren().add(canvas);
        Snake snake = new Snake(boardXSize,boardYSize);
        RunLoop runLoop = new RunLoop(canvas,snake);
        runLoop.start();


        Scene scene = new Scene(hBox,boardXSize,boardYSize);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.W) {
                snake.setDirection(Snake.Direction.UP);
            }
            if (key.getCode() == KeyCode.A) {
                snake.setDirection(Snake.Direction.LEFT);
            }
            if (key.getCode() == KeyCode.S) {
                snake.setDirection(Snake.Direction.DOWN);
            }
            if (key.getCode() == KeyCode.D) {
                snake.setDirection(Snake.Direction.RIGHT);
            }

        });
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}