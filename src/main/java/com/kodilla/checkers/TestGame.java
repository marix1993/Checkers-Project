package com.kodilla.checkers;

public class TestGame {
    public static void main(String[] args) {
        Board board = new Board();
        board.setFigure(1, 1, new Pawn(FigureColor.WHITE));
        board.setFigure(2, 2, new Pawn(FigureColor.WHITE));
        board.setFigure(3, 1, new Pawn(FigureColor.WHITE));
        board.setFigure(2, 6, new Pawn(FigureColor.BLACK));
        System.out.println(board);

        boolean result = board.move(2, 2, 3, 3);
        System.out.println(result);
        System.out.println(board);

        result = board.move(2, 6, 1, 5);
        System.out.println(result);
        System.out.println(board);

        result = board.move(3, 3, 2, 4);
        System.out.println(result);
        System.out.println(board);

        result = board.move(1, 5, 3, 3);
        System.out.println(result);
        System.out.println(board);
    }
}
