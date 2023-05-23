package com.chess.Pieces;

import com.chess.Move;
import com.chess.Piece;
import com.chess.Player;
import com.chess.Position;

public class Bishop extends Piece {
    public Bishop(Player player) {
        super(player);
    }
    @Override
    public String getSymbol() {
        return "B"; // 비숍의 기호를 나타내는 값 반환
    }

    @Override
    public boolean isValidMove(Move move, Piece[][] board) {
        Position source = move.getSourcePosition();
        Position destination = move.getDestinationPosition();

        int rowDiff = Math.abs(destination.getRow() - source.getRow());
        int colDiff = Math.abs(destination.getCol() - source.getCol());

        // 비숍의 이동은 대각선으로만 가능
        return rowDiff == colDiff;
    }
}
