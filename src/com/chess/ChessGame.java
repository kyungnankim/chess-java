package com.chess;
import java.util.Scanner;

public class ChessGame {
    private Board board;
    private Player currentPlayer;
    private boolean gameOver;
    private Scanner scanner;

    public ChessGame() {
        board = new Board();
        currentPlayer = Player.WHITE;
        gameOver = false;
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        while (!gameOver) {
            board.printBoard();
            System.out.println("Current player: " + currentPlayer);

            System.out.print("Enter source position (e.g., A2): ");
            String sourcePosition = scanner.nextLine();
            System.out.print("Enter destination position (e.g., A4): ");
            String destinationPosition = scanner.nextLine();

            if (isValidInput(sourcePosition, destinationPosition)) {
            	Move move = new Move(sourcePosition, destinationPosition);

                if (board.isValidMove(move, currentPlayer)) {
                    if (board.isCastleMove(move)) {
                        board.makeCastleMove(move);
                    } else {
                        board.makeMove(move);
                    }

                    if (board.isCheckmate(currentPlayer)) {
                        gameOver = true;
                        System.out.println("Checkmate! Player " + currentPlayer + " wins!");
                    } else if (board.isStalemate(currentPlayer)) {
                        gameOver = true;
                        System.out.println("Stalemate! The game is a draw.");
                    } else if (board.isCheck(currentPlayer)) {
                        System.out.println("Check!");
                    }

                    currentPlayer = currentPlayer.opposite();
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }

        scanner.close();
    }

    private boolean isValidInput(String sourcePosition, String destinationPosition) {
        if (sourcePosition.length() != 2 || destinationPosition.length() != 2) {
            return false;
        }

        char sourceFile = Character.toUpperCase(sourcePosition.charAt(0));
        char sourceRank = sourcePosition.charAt(1);
        char destFile = Character.toUpperCase(destinationPosition.charAt(0));
        char destRank = destinationPosition.charAt(1);

        if (sourceFile < 'A' || sourceFile > 'H' || sourceRank < '1' || sourceRank > '8'
                || destFile < 'A' || destFile > 'H' || destRank < '1' || destRank > '8') {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        game.startGame();
    }
}
