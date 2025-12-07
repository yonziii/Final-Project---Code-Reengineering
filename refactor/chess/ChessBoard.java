package com.directi.training.codesmells.smelly.chess;

import com.directi.training.codesmells.smelly.Color;
import com.directi.training.codesmells.smelly.Direction;
import com.directi.training.codesmells.smelly.Position;
import com.directi.training.codesmells.smelly.pieces.*;

public class ChessBoard
{
    private final Cell[][] _board;
    public boolean _kingDead;

    public ChessBoard()
    {
        _board = new Cell[8][8];
        initBoard();
        resetBoard();
    }

    private void initBoard()
    {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Color color = ((row + column) % 2 == 0) ? Color.WHITE : Color.BLACK;
                _board[row][column] = new Cell(color);
            }
        }
    }
    
 // Was inside GameEngine.java

    public void resetBoard() {

        setupRow(7, Color.WHITE);
        setupPawns(6, Color.WHITE);

        // Setup Black
        setupRow(0, Color.BLACK);
        setupPawns(1, Color.BLACK);
        
       _kingDead = false;
    }

    private void setupRow(int row, Color color) {
        Cell[][] board = getBoard();
        board[row][0].setPiece(new Rook(color));
        board[row][1].setPiece(new Knight(color));
        board[row][2].setPiece(new Bishop(color));
        board[row][3].setPiece(new King(color));
        board[row][4].setPiece(new Queen(color));
        board[row][5].setPiece(new Bishop(color));
        board[row][6].setPiece(new Knight(color));
        board[row][7].setPiece(new Rook(color));
    }

    private void setupPawns(int row, Color color) {
        for (int col = 0; col < 8; col++) {
            getBoard()[row][col].setPiece(new Pawn(color));
        }
    }

    public Cell[][] getBoard()
    {
        return _board;
    }

    private boolean isPositionOutOfBounds(Position position)
    {
        return (position.getRow() < 0
                || position.getRow() >= 8
                || position.getColumn() < 0
                || position.getColumn() >= 8);
    }

    public boolean isEmpty(Position position)
    {
        return isPositionOutOfBounds(position) || getCell(position).isEmpty();
    }

    private Cell getCell(Position position)
    {
        return _board[position.getRow()][position.getColumn()];
    }

    public Piece getPiece(Position position)
    {
        return isEmpty(position) ? null: getCell(position).getPiece();
    }
    
    //Ganti juga supaya pake tipe data position

    public boolean isValidMove(Position from, Position to)
    {
        return !from.equals(to)
               && !(isPositionOutOfBounds(from) || isPositionOutOfBounds(to))
               && !isEmpty(from)
               && (isEmpty(to) || getPiece(from).getColor() != getPiece(to).getColor())
               && getPiece(from).isValidMove(from, to, this)
               && hasNoPieceInPath(from, to);
    }

    private boolean hasNoPieceInPath(Position from, Position to)
    {
        if (getPiece(from) instanceof Knight)
            return true;
        if (!isStraightLineMove(from, to))
            return false;
        Direction direction = new Direction(cappedCompare(to.getRow(), from.getRow()), cappedCompare(to.getColumn(), from.getColumn()));
        from = from.translatedPosition(direction);
        while (!from.equals(to)) {
            if (!isEmpty(from))
                return false;
            from = from.translatedPosition(direction);
        }
        return true;
    }

    private boolean isStraightLineMove(Position from, Position to)
    {
        return Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getColumn() - to.getColumn())
                || from.getRow() == to.getRow()
                || from.getColumn() == to.getColumn();
    }

    private int cappedCompare(int x, int y)
    {
        return Math.max(-1, Math.min(1, Integer.compare(x, y)));
    }
    
    //Pindah translatedPosition ke position
    
    //Ganti movePiece, updateIsKingDead supaya pake tipe data Position
    public void movePiece(Position from, Position to)
    {
        updateIsKingDead(to);
        if (!getCell(to).isEmpty())
            getCell(to).removePiece();
        getCell(to).setPiece(getPiece(from));
        getCell(from).removePiece();
    }

    private void updateIsKingDead(Position to)
    {
        if (getPiece(to) instanceof King) {
            _kingDead = true;
        }
    }

    //isValidPawnMove pindah ke pawn jadi isValidMove

    public boolean isKingDead()
    {
        return _kingDead;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder(" ");
        for (int column = 0; column < 8; column++) {
            stringBuilder.append("  ")
                    .append(column + 1)
                    .append("  ");
        }
        stringBuilder.append("\n");

        for (int row = 0; row < 8; row++) {
            stringBuilder.append(row + 1);
            for (int column = 0; column < 8; column++) {
                stringBuilder.append(" ")
                        .append(_board[row][column])
                        .append(" ");
            }
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }
}
