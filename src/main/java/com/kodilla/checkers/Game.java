package com.kodilla.checkers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.TRANSPARENT;

public class Game {
    private final Image white = new Image("file:src/main/resources/White.jpg");
    private final Image red = new Image("file:src/main/resources/Red.jpg");
    private Board board;
    private GridPane grid;

    private int oldX = -1;

    private int oldY = -1;

    public Game(Board board, GridPane grid) {

        this.board = board;
        this.grid = grid;
    }

    public void displayOnGrid() {
        grid.getChildren().clear();
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                Image image = null;
                Figure figure = board.getFigure(col, row);
                if (figure instanceof Pawn) {
                    if (figure.getColor() == FigureColor.WHITE) {
                        image = white;
                    } else {
                        image = red;
                    }
                }
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(85);
                imageView.setPreserveRatio(true);
                grid.add(imageView, col, row);
                if (col == oldX && row == oldY) {
                    Rectangle rectangle = new Rectangle(85, 85, RED);
                    rectangle.setFill(TRANSPARENT);
                    rectangle.setStroke(Color.RED);
                    rectangle.setStrokeWidth(5);
                    grid.add(rectangle, col, row);
                }
            }
        }
    }

    public void doClick(int col, int row) {
        if (oldX == -1) {
            oldX = col;
            oldY = row;
        } else {
            if (board.move(oldX, oldY, col, row)) {
                oldX = -1;
                oldY = -1;
            }
        }
        displayOnGrid();
    }
}
