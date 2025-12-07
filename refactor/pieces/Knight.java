package com.directi.training.codesmells.smelly.pieces;

import com.directi.training.codesmells.smelly.Color;
import com.directi.training.codesmells.smelly.Position;
import com.directi.training.codesmells.smelly.chess.ChessBoard;

public class Knight extends Piece
{
    public Knight(Color color)
    {
        super(color);
    }

    @Override
    public String toString()
    {
        return "k";
    }

	@Override
	public boolean isValidMove(Position from, Position to, ChessBoard board) {
	      return (Math.abs(from.getRow() - to.getRow()) == 1) && (Math.abs(from.getColumn() - to.getColumn()) == 1);
	}
}
