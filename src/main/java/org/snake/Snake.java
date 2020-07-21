package org.snake;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

public class Snake {

    private final int snakeStartLength = 800;
    private final int amountSnakePartInStart = 10; // to 33
    private final int snakeItemSize;
    private final int boardWidth;
    private final int boardHeight;
    private final ArrayList<SnakePart> snake = new ArrayList<>();
    Fruit fruit;
    private Direction direction = Direction.LEFT;
    private int moveX;
    private int moveY;
    private boolean alive = true;

    Snake(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        snakeItemSize = snakeStartLength / amountSnakePartInStart;
        createSnake();
    }

    private final void createSnake() {
        for (int i = 0; i < amountSnakePartInStart; i++) {
            SnakePart snakePart;
            try {
                SnakePart snakePartLast = snake.get(i - 1);
                snakePart = new SnakePart(snakePartLast.xPosition + snakeItemSize, snakePartLast.yPosition, snakeItemSize);//right
                if (outOfBoard(snakePart) || collisionWithAllPart(snakePart)) {
                    snakePart = new SnakePart(snakePartLast.xPosition, snakePartLast.yPosition + snakeItemSize, snakeItemSize);//down
                    if (outOfBoard(snakePart) || collisionWithAllPart(snakePart)) {
                        snakePart = new SnakePart(snakePartLast.xPosition - snakeItemSize, snakePartLast.yPosition, snakeItemSize);//left
                        if (outOfBoard(snakePart) || collisionWithAllPart(snakePart)) {
                            snakePart = new SnakePart(snakePartLast.xPosition, snakePartLast.yPosition - snakeItemSize, snakeItemSize);//up
                        }
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                snakePart = new SnakePart(boardWidth / 2 , boardHeight / 2, snakeItemSize); // head

            }
            snake.add(snakePart);
        }
        fruit = new Fruit(1, 1);
    }

    private boolean collisionWithAllPart(SnakePart givenSnakePart) {
        for(SnakePart snakePart : snake){
            if(collision(givenSnakePart,snakePart)){
                return true;
            }
        }
        return false;
    }

    public void tick(GraphicsContext graphics) {
        setSnakeItemSizePosition();
        SnakePart head = snake.get(0);

        if (outOfBoard(head)) {
            alive = false;
            return;
        }
        if (collision(head, fruit)) {
            fruit = fruit.makeNewFruit(boardWidth, boardHeight);
            int lastSnakeItem = snake.size() - 1;
            int xPosition = snake.get(lastSnakeItem).getxPosition();
            int yPosition = snake.get(lastSnakeItem).getyPosition();
            snake.add(new SnakePart(xPosition, yPosition, snakeItemSize));
        }
        boolean notFirst = false;
        for (SnakePart point : snake) {
            if (notFirst) {
                if (collision(head, point)) {
                    alive = false;
                    return;
                }
            } else {
                notFirst = true;
            }
            point.paint(graphics);
        }
        fruit.paint(graphics);
    }


    private void setSnakeItemSizePosition() {
        for (int i = snake.size() - 1; i >= 0; i--) {
            SnakePart point = snake.get(i);
            if (i == 0) { // head
                chooseMove();
                point.setxPosition(point.getxPosition() + moveX);
                point.setyPosition(point.getyPosition() + moveY);

            } else {
                point.setxPosition(snake.get(i - 1).getxPosition());
                point.setyPosition(snake.get(i - 1).getyPosition());
            }

        }
    }

    private boolean outOfBoard(SnakePart head) {
        int x = head.getxPosition();
        int y = head.getyPosition();
        return x < 0 || (x + snakeItemSize) > boardWidth || y < 0 || (y + snakeItemSize) > boardHeight;
    }

    private boolean collision(Point head, Point point) {
        if (head.getxPosition() + snakeItemSize > point.getxPosition()) {
            if (head.getxPosition() < point.getxPosition() + snakeItemSize) {
                if (head.getyPosition() + snakeItemSize > point.getyPosition()) {
                    if(head.getyPosition() < point.getyPosition() + snakeItemSize){
                        System.out.println("head x "+ head.getxPosition() + " y" + head.getyPosition() );
                        System.out.println("Point x "+ head.getxPosition() + " y" + head.getyPosition() );
                        return true;
                    }

                }
            }
        }
        return false;
    }

    private void chooseMove() {
        moveX = 0;
        moveY = 0;
        if (direction == Direction.LEFT) {
            moveX -= snakeItemSize;
        } else if (direction == Direction.RIGHT) {
            moveX += snakeItemSize;
        } else if (direction == Direction.DOWN) {
            moveY += snakeItemSize;
        } else if (direction == Direction.UP) {
            moveY -= snakeItemSize;
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getSnakeSize() {
        return snake.size();
    }
}
