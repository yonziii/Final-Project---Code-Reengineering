package com.directi.training.codesmells.refactor.pieces;

import com.directi.training.codesmells.refactor.Color;
import com.directi.training.codesmells.refactor.Position;
import com.directi.training.codesmells.refactor.chess.ChessBoard;

public class Queen extends Piece
{
    public Queen(Color color)
    {
        super(color);
    }

    @Override
    public String toString()
    {
        return "q";
    }

	@Override
	public boolean isValidMove(Position from, Position to, ChessBoard board) {
		return Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getColumn() - to.getColumn())
				|| from.getRow() == to.getRow() || from.getColumn() == to.getColumn();
	}
}
