package com.chess.Pieces;

import com.chess.Move;
import com.chess.Piece;
import com.chess.Player;
import com.chess.Position;

public class Knight extends Piece {
    public Knight(Player player) {
        super(player);
    }
    @Override
    public String getSymbol() {
        return "KN"; // Knight 기호를 나타내는 값 반환
    }

    @Override
    public boolean isValidMove(Move move, Piece[][] board) {
        Position source = move.getSourcePosition();
        Position destination = move.getDestinationPosition();

        int rowDiff = Math.abs(destination.getRow() - source.getRow());
        int colDiff = Math.abs(destination.getCol() - source.getCol());

        // 나이트의 이동은 L 모양으로 이동하는 것이 유효
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}
