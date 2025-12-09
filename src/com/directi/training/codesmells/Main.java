package com.directi.training.codesmells;

import com.directi.training.codesmells.refactor.Position;
import com.directi.training.codesmells.refactor.chess.GameEngine;
import com.directi.training.codesmells.refactor.chess.Player;

import java.util.Scanner;

public class Main
{
    private static final Scanner scanner = new Scanner(System.in);
    private static GameEngine gameEngine;

    public static void main(String[] args)
    {
        initGameEngine();
        //Loop game
        while(true) {
            gameEngine.initGame();
            printGameStart();
            
            // The "Turn" loop
            while (!gameEngine.isGameEnded()) {
                System.out.println(gameEngine.getChessBoard());
                playTurn();
            }
            
            printGameEnd();
            
            break;
        }
    }
    // Syso / scanner dari game engine pindahin ke sini buat GUI
    private static void playTurn() {
        Player currentPlayer = gameEngine.getCurrentPlayer();
        System.out.println("Next move is of " + currentPlayer.getName()
                           + " [" + currentPlayer.getColor() + "]");
        
        Position from = inputPosition("Enter position (row col) of piece to move: ");
        Position to = inputPosition("Enter destination position: ");

        if (gameEngine.isValidMove(from, to)) {
            gameEngine.makeMove(from, to);
        } else {
            System.out.println("Invalid move!");
        }
    }

    private static Position inputPosition(String prompt)
    {
        System.out.print(prompt);
        int row = scanner.nextInt() - 1;
        int col = scanner.nextInt() - 1;
        return new Position(row, col);
    }

    private static void initGameEngine()
    {
        System.out.print("Enter Player 1 Name: ");
        Player player1 = new Player(scanner.nextLine());
        System.out.print("Enter Player 2 Name: ");
        Player player2 = new Player(scanner.nextLine());
        gameEngine = new GameEngine(player1, player2);
    }
    
    private static void printGameStart() {
        System.out.println("\nGame initialized");
        System.out.println("Player " + gameEngine.getCurrentPlayer().getName() + " starts.");
    }

    private static void printGameEnd() {
        System.out.println(gameEngine.getChessBoard());
        System.out.println("Game Ended");
        Player winner = gameEngine.getCurrentPlayer();
        winner.increase();
        System.out.println("WINNER - " + winner + "\n\n");
    }
}