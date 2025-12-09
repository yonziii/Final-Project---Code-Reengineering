package com.directi.training.codesmells.refactor.pieces;

import com.directi.training.codesmells.refactor.Color;
import com.directi.training.codesmells.refactor.Position;
import com.directi.training.codesmells.refactor.chess.ChessBoard;

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
		int columnDiff = Math.abs(to.getColumn() - from.getColumn());
		int rowDiff = Math.abs(to.getRow() - from.getRow());
		return (columnDiff == 2 && rowDiff == 1) || (columnDiff == 1 && rowDiff == 2);
	}
}
