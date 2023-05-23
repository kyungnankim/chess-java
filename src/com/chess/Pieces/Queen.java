package com.chess.Pieces;

import com.chess.Move;
import com.chess.Piece;
import com.chess.Player;
import com.chess.Position;

public class Queen extends Piece {
    public Queen(Player player) {
        super(player);
    }
    @Override
    public String getSymbol() {
        return "Q"; // Queen의 기호를 나타내는 값 반환
    }
    @Override
    public boolean isValidMove(Move move, Piece[][] board) {
        Position source = move.getSourcePosition();
        Position destination = move.getDestinationPosition();

        int rowDiff = Math.abs(destination.getRow() - source.getRow());
        int colDiff = Math.abs(destination.getCol() - source.getCol());

        // 퀸은 직선 및 대각선으로 이동 가능
        return rowDiff == 0 || colDiff == 0 || rowDiff == colDiff;
    }
}
