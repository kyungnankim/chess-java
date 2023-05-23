package com.chess.Pieces;

import com.chess.Move;
import com.chess.Piece;
import com.chess.Player;
import com.chess.Position;

public class King extends Piece {
	private boolean hasMoved;

    public King(Player player) {
        super(player);
        hasMoved = false;
    }
    public boolean hasMoved() {
        return hasMoved;
    }

    public void setMoved(boolean moved) {
        hasMoved = moved;
    }
    @Override
    public String getSymbol() {
        return "K"; // king의 기호를 나타내는 값 반환
    }

    @Override
    public boolean isValidMove(Move move, Piece[][] board) {
        Position source = move.getSourcePosition();
        Position destination = move.getDestinationPosition();

        int rowDiff = Math.abs(destination.getRow() - source.getRow());
        int colDiff = Math.abs(destination.getCol() - source.getCol());

        // 킹은 상하좌우 및 대각선으로 한 칸씩 이동 가능
        return (rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 1);
    }
}

