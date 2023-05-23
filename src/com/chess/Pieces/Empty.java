package com.chess.Pieces;
import com.chess.Move;
import com.chess.Piece;
import com.chess.Player;
import com.chess.Position;

public class Empty extends Piece {
    public Empty() {
        super(null);
    }
    @Override
    public String getSymbol() {
        return "-"; // 빈 공간의 기호를 나타내는 값 반환
    }
    @Override
    public boolean isValidMove(Move move, Piece[][] board) {
        // 빈 위치는 유효한 이동을 할 수 없음
        return false;
    }
}
