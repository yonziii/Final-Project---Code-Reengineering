package com.directi.training.codesmells.refactor.pieces;

import com.directi.training.codesmells.refactor.Color;
import com.directi.training.codesmells.refactor.Position;
import com.directi.training.codesmells.refactor.chess.ChessBoard;

public class King extends Piece
{
    public King(Color color)
    {
        super(color);
    }

    @Override
    public String toString()
    {
        return "K";
    }
	
	@Override
	public boolean isValidMove(Position from, Position to, ChessBoard board) {
	      return (Math.abs(from.getRow() - to.getRow()) == 1) && (Math.abs(from.getColumn() - to.getColumn()) == 1);
	}
}
