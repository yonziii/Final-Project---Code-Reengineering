package com.directi.training.codesmells.refactor.pieces;

import com.directi.training.codesmells.refactor.Color;
import com.directi.training.codesmells.refactor.Position;
import com.directi.training.codesmells.refactor.chess.ChessBoard;

public class Pawn extends Piece
{
    public Pawn(Color color)
    {
        super(color);
    }
    
    @Override
    public boolean isValidMove(Position from, Position to, ChessBoard board)
    {
        // Kalkulasi yang tadinya ada di ChessBoard
        boolean atInitialPosition = isAtInitialPosition(from);
        boolean opponentPieceAtForwardLeft = hasOpponentAt(from, -1, board);
        boolean opponentPieceAtForwardRight = hasOpponentAt(from, 1, board);


        return isForwardMove(from, to)
               && isTakingAllowedNumberOfForwardSteps(from, to, atInitialPosition)
               && isTakingAllowedNumberOfSidewaysSteps(from, to, opponentPieceAtForwardLeft, opponentPieceAtForwardRight);
    }

    //Fungsi kalkulasi yanag diatas

    private boolean isAtInitialPosition(Position from) {
        return from.getRow() == ((getColor() == Color.BLACK) ? 1 : 6);
    }


    private boolean hasOpponentAt(Position from, int colOffset, ChessBoard board) {
        // Calculate the target position
        int forwardRow = from.getRow() + ((getColor() == Color.BLACK) ? 1 : -1);
        int forwardCol = from.getColumn() + (getColor() == Color.WHITE ? colOffset : -colOffset);
        Position target = new Position(forwardRow, forwardCol);

        // Use getPiece() which handles bounds checking for us
        // If target is off-board or empty, getPiece returns null
        Piece piece = board.getPiece(target);

        return piece != null && piece.getColor() != getColor();
    }
    private boolean isForwardMove(Position from, Position to)
    {
        switch (getColor()) {
            case WHITE:
                return to.getRow() < from.getRow();
            case BLACK:
                return to.getRow() > from.getRow();
            default:
                return false;
        }
    }



    private boolean isTakingAllowedNumberOfForwardSteps(Position from, Position to, boolean atInitialPosition)
    {
        int rowsAbsDiff = Math.abs(to.getRow() - from.getRow());
        return rowsAbsDiff > 0 && (rowsAbsDiff <= (atInitialPosition ? 2 : 1));
    }

    private boolean isTakingAllowedNumberOfSidewaysSteps(Position from,
                                                         Position to,
                                                         boolean opponentPieceAtForwardLeft,
                                                         boolean opponentPieceAtForwardRight)
    {
        int columnsDiff = to.getColumn() - from.getColumn();
        if (columnsDiff == -1)
            return (opponentPieceAtForwardLeft && getColor() == Color.WHITE)
                   || (opponentPieceAtForwardRight && getColor() == Color.BLACK);
        if (columnsDiff == 1) {
            return (opponentPieceAtForwardRight && getColor() == Color.WHITE)
                   || (opponentPieceAtForwardLeft && getColor() == Color.BLACK);
        }
        return columnsDiff == 0;
    }

    @Override
    public String toString()
    {
        return "p";
    }
}
