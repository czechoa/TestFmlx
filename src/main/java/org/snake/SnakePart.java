package org.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light;
import javafx.scene.paint.Color;

public class SnakePart extends Point {

    SnakePart(int xPosition, int yPosition) {
        super(xPosition, yPosition);
    }

    @Override
    public void paint(GraphicsContext graphics){
        graphics.setFill(Color.WHITE);
        graphics.fillRect(xPosition, yPosition,size, size);
        graphics.setFill(Color.GRAY);
        graphics.fillRect(xPosition + 1, yPosition + 1, size - 2, size - 2);
    }

}
