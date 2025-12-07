package com.directi.training.codesmells.smelly.pieces;

import com.directi.training.codesmells.smelly.Color;
import com.directi.training.codesmells.smelly.Position;
import com.directi.training.codesmells.smelly.chess.ChessBoard;

public class Bishop extends Piece
{

    public Bishop(Color color)
    {
        super(color);
    }

    @Override
    public String toString()
    {
        return "b";
    }
    
    @Override
    public boolean isValidMove(Position from, Position to, ChessBoard board)
    {
        return Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getColumn() - to.getColumn());
    }
}
