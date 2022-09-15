package com.kodilla.checkers;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Checkers extends Application {

    private final Image board = new Image("file:src/main/resources/Board.jpg");


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BackgroundSize backgroundSize = new BackgroundSize(685, 685, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(board, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setBackground(background);
        for (int n = 0; n < 8; n++) {
            grid.getColumnConstraints().add(new ColumnConstraints(85));
            grid.getRowConstraints().add(new RowConstraints(85));
        }

        Scene scene = new Scene(grid, 685, 685);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();


        Board board = new Board();
        board.init();

        Game game = new Game(board, grid);
        game.displayOnGrid();

        grid.setOnMouseClicked(e -> {
            int col = (int) (e.getX() / 85);
            int row = (int) (e.getY() / 85);
            game.doClick(col, row);
        });
    }
}

