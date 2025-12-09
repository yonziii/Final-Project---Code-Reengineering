package com.directi.training.codesmells.refactor.pieces;

import com.directi.training.codesmells.refactor.Color;
import com.directi.training.codesmells.refactor.Position;
import com.directi.training.codesmells.refactor.chess.ChessBoard;

public abstract class Piece
{
    private Color _color;


    public Piece(Color color)
    {
        _color = color;
    }

    public Color getColor()
    {
        return _color;
    }

    
    public abstract boolean isValidMove(Position from, Position to, ChessBoard board);

    
    
}
