package com.kodilla.checkers;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<BoardRow> rows = new ArrayList<>();
    private FigureColor whoseMove = FigureColor.WHITE;

    public Board() {
        for (int row = 0; row < 8; row++)
            rows.add(new BoardRow());
    }

    public Figure getFigure(int col, int row) {
        return rows.get(row).getCols().get(col);
    }

    public Figure setFigure(int col, int row, Figure figure) {
        return rows.get(row).getCols().set(col, figure);
    }

    public boolean move(int col1, int row1, int col2, int row2) {
        boolean result = true;

        result = result && isRightPlayerMoving(whoseMove, col1, row1);
        System.out.println("Check if rightPlayer moving." + result);
        result = result && isRightDirection(whoseMove, row1, row2);
        System.out.println("Check if right direction." + result);
        result = result && isMoveDiagonal(col1, row1, col2, row2);
        System.out.println("Check if move diagonal." + result);
        result = result && isPieceMovingOnEmptyField(col2, row2);
        System.out.println("Check if moving on empty." + result);
        result = result && isMoveOnBoard(col2, row2);
        System.out.println("Check if move is on board." + result);
        if (result) {
            if (isMoveByOne(col1, row1, col2, row2)) {
                doMove(col1, row1, col2, row2);
            } else {
                result = isMoveByTwo(col1, row1, col2, row2);
                result = result && isMoveWithHit(col1, row1, col2, row2);
                if (result) {
                    doMoveWithHit(col1, row1, col2, row2);
                }
            }
        }
        return result;
    }

    private void doMoveWithHit(int col1, int row1, int col2, int row2) {
        Figure figure = getFigure(col1, row1);
        setFigure(col2, row2, figure);
        setFigure(col1, row1, new None());
        int deltaCol = (col2 > col1) ? 1 : -1;
        int deltaRow = (row2 > row1) ? 1 : -1;
        //usuwanie zbitej figury
        setFigure(col1 + deltaCol, row1 + deltaRow, new None());
        whoseMove = oppositePlayer(whoseMove);
    }

    private boolean isMoveByTwo(int col1, int row1, int col2, int row2) {
        return Math.abs(col2 - col1) == 2 && Math.abs(row2 - row1) == 2;
    }

    private boolean isMoveByOne(int col1, int row1, int col2, int row2) {
        return Math.abs(col2 - col1) == 1 && Math.abs(row2 - row1) == 1;
    }

    private boolean isMoveOnBoard(int col2, int row2) {
        return (col2 < 8) && (row2 < 8) && (col2 >= 0) && (row2 >= 0);
    }

    private boolean isPieceMovingOnEmptyField(int col2, int row2) {
        return getFigure(col2, row2).getColor() == FigureColor.NONE;
    }

    private boolean isMoveWithHit(int col1, int row1, int col2, int row2) {
        int deltaCol = (col2 > col1) ? 1 : -1;
        int deltaRow = (row2 > row1) ? 1 : -1;

        return getFigure(col1 + deltaCol, row1 + deltaRow).getColor() == oppositePlayer(whoseMove);
    }

    private boolean isMoveDiagonal(int col1, int row1, int col2, int row2) {
        int deltaCol = col1 - col2;
        int deltaRow = row1 - row2;
        return Math.abs(deltaCol) == Math.abs(deltaRow);
    }

    private boolean isRightPlayerMoving(FigureColor whoseMove, int col1, int row1) {
        return getFigure(col1, row1).getColor() == whoseMove;
    }

    private boolean isRightDirection(FigureColor whoseMove, int row1, int row2) {
        if (whoseMove == FigureColor.WHITE) {
            return row2 > row1;
        } else {
            return row1 > row2;
        }
    }

    private void doMove(int col1, int row1, int col2, int row2) {
        Figure figure = getFigure(col1, row1);
        setFigure(col2, row2, figure);
        setFigure(col1, row1, new None());
        whoseMove = oppositePlayer(whoseMove);
    }

    private FigureColor oppositePlayer(FigureColor player) {
        return (player == FigureColor.WHITE) ? FigureColor.BLACK : FigureColor.WHITE;
    }

    @Override
    public String toString() {
        String s = "|--|--|--|--|--|--|--|--|\n";
        for (int row = 0; row < 8; row++)
            s += rows.get(row).toString();
        s += "|--|--|--|--|--|--|--|--|\n";
        return s;
    }

    public void init() {
        setFigure(0, 0, new Pawn(FigureColor.WHITE));
        setFigure(1, 1, new Pawn(FigureColor.WHITE));
        setFigure(2, 0, new Pawn(FigureColor.WHITE));
        setFigure(3, 1, new Pawn(FigureColor.WHITE));
        setFigure(4, 0, new Pawn(FigureColor.WHITE));
        setFigure(5, 1, new Pawn(FigureColor.WHITE));
        setFigure(6, 0, new Pawn(FigureColor.WHITE));
        setFigure(7, 1, new Pawn(FigureColor.WHITE));
        setFigure(0, 2, new Pawn(FigureColor.WHITE));
        setFigure(2, 2, new Pawn(FigureColor.WHITE));
        setFigure(4, 2, new Pawn(FigureColor.WHITE));
        setFigure(6, 2, new Pawn(FigureColor.WHITE));


        //Dokończ
        setFigure(0, 6, new Pawn(FigureColor.BLACK));
        setFigure(1, 7, new Pawn(FigureColor.BLACK));
        setFigure(2, 6, new Pawn(FigureColor.BLACK));
        setFigure(3, 7, new Pawn(FigureColor.BLACK));
        setFigure(4, 6, new Pawn(FigureColor.BLACK));
        setFigure(5, 7, new Pawn(FigureColor.BLACK));
        setFigure(6, 6, new Pawn(FigureColor.BLACK));
        setFigure(7, 7, new Pawn(FigureColor.BLACK));
        setFigure(1, 5, new Pawn(FigureColor.BLACK));
        setFigure(3, 5, new Pawn(FigureColor.BLACK));
        setFigure(5, 5, new Pawn(FigureColor.BLACK));
        setFigure(7, 5, new Pawn(FigureColor.BLACK));
    }
}

