package com.directi.training.codesmells.smelly.pieces;

import com.directi.training.codesmells.smelly.Color;
import com.directi.training.codesmells.smelly.Position;
import com.directi.training.codesmells.smelly.chess.ChessBoard;

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
		int columnDiff = Math.abs(to.getColumn() - from.getColumn());
		int rowDiff = Math.abs(to.getRow() - from.getRow());
		return (columnDiff == 2 && rowDiff == 1) || (columnDiff == 1 && rowDiff == 2);
	}
}
