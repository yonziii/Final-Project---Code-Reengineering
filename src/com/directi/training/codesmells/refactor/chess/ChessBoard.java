package com.directi.training.codesmells.refactor.chess;

import com.directi.training.codesmells.refactor.Color;
import com.directi.training.codesmells.refactor.Direction;
import com.directi.training.codesmells.refactor.Position;
import com.directi.training.codesmells.refactor.pieces.*;

public class ChessBoard
{
    private final Cell[][] _board;
    //kingDead itu gameLogic bukan board logic

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
    
 // Tadinya resetboard di game engine 

    public void resetBoard() {

        setupRow(7, Color.WHITE);
        setupPawns(6, Color.WHITE);

        // Setup Black
        setupRow(0, Color.BLACK);
        setupPawns(1, Color.BLACK);
        
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
        // Logic delegated to Position class
        if (!from.isStraightPathTo(to))
            return false;
            
        Direction direction = from.directionTo(to);
        from = from.translatedPosition(direction);
        while (!from.equals(to)) {
            if (!isEmpty(from))
                return false;
            from = from.translatedPosition(direction);
        }
        return true;
    }

    //Logika gerak pindahin ke position
    
    //Pindah translatedPosition ke position
    
    //Ganti movePiece, updateIsKingDead supaya pake tipe data Position
    public void movePiece(Position from, Position to)
    {
        // Removed updateIsKingDead (Referee logic moved to GameEngine)
        if (!getCell(to).isEmpty())
            getCell(to).removePiece();
        getCell(to).setPiece(getPiece(from));
        getCell(from).removePiece();
    }

    //isValidPawnMove pindah ke pawn jadi isValidMove

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