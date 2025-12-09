package com.directi.training.codesmells.refactor.chess;

import com.directi.training.codesmells.refactor.Color;
import com.directi.training.codesmells.refactor.Position;
import com.directi.training.codesmells.refactor.pieces.*;

public class GameEngine
{
    private final ChessBoard _chessBoard;
    private Player _player1, _player2;
    private Player _currentPlayer;
    private boolean _isGameEnded = false;

    public GameEngine(Player player1, Player player2)
    {
        _chessBoard = new ChessBoard();
        _player1 = player1;
        _player2 = player2;
    }

    public void initGame()
    {
        if (_currentPlayer == null || _player1.getColor() == Color.BLACK) {
            _currentPlayer = _player1;
            _player1.setColor(Color.WHITE);
            _player2.setColor(Color.BLACK);
        } else {
            _currentPlayer = _player2;
            _player1.setColor(Color.BLACK);
            _player2.setColor(Color.WHITE);
        }
        _chessBoard.resetBoard();
        _isGameEnded = false;
    }

    public void makeMove(Position from, Position to)
    {
    	Piece targetPiece = _chessBoard.getPiece(to);
        if (targetPiece instanceof King) {
            _isGameEnded = true;
        }
        
        _chessBoard.movePiece(from, to);
        
        if (!isGameEnded()) {
            _currentPlayer = getOtherPlayer();
        }
    }

    public boolean isValidMove(Position from, Position to)
    {
        return isPlayerMovingItsOwnColoredPiece(from)
               && _chessBoard.isValidMove(from, to);
    }

    private boolean isPlayerMovingItsOwnColoredPiece(Position from) {
        return !_chessBoard.isEmpty(from)
               && _chessBoard.getPiece(from).getColor() == _currentPlayer.getColor();
    }

    // NEW: Expose game status so Main knows when to stop
    public boolean isGameEnded() {
        return _isGameEnded;
    }

    public Player getCurrentPlayer() {
        return _currentPlayer;
    }

    public ChessBoard getChessBoard()
    {
        return _chessBoard;
    }
    
    private Player getOtherPlayer()
    {
        return _player1 == _currentPlayer ? _player2 : _player1;
    }
}