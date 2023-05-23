package com.chess.Pieces;

import com.chess.Move;
import com.chess.Piece;
import com.chess.Player;
import com.chess.Position;

public class Pawn extends Piece {
	
	 private boolean moved;

	    public Pawn(Player player) {
	        super(player);
	        moved = false;
	    }

	    public boolean hasMoved() {
	        return moved;
	    }

	    public void setMoved(boolean moved) {
	        this.moved = moved;
	    }
	/*p 뒤 자릿수 추가하기*/   
    @Override
    public String getSymbol() {
        return "P"; // 폰의 기호를 나타내는 값 반환
    }
    
    @Override
    public boolean isValidMove(Move move, Piece[][] board) {
        Position source = move.getSourcePosition();
        Position destination = move.getDestinationPosition();

        int rowDiff = destination.getRow() - source.getRow();
        int colDiff = Math.abs(destination.getCol() - source.getCol());

        Player currentPlayer = getPlayer();

        // 플레이어에 따라 폰의 이동 방향이 다름
        int forwardDirection = (currentPlayer == Player.WHITE) ? -1 : 1;

        // 폰의 이동 규칙 검사
        if (colDiff == 0) {
            // 직진 이동
            if (rowDiff == forwardDirection) {
                // 이동할 위치가 비어있는지 확인
                if (board[destination.getRow()][destination.getCol()].isEmpty()) {
                    return true;
                }
            } else if (rowDiff == 2 * forwardDirection && !hasMoved()) {
                // 폰이 처음 이동하는 경우, 2칸 전진 가능
                int middleRow = source.getRow() + forwardDirection;
                if (board[middleRow][source.getCol()].isEmpty() && board[destination.getRow()][destination.getCol()].isEmpty()) {
                    return true;
                }
            }
        } else if (colDiff == 1 && rowDiff == forwardDirection) {
            // 대각선으로 상대 말을 잡을 수 있는 경우
            Piece destinationPiece = board[destination.getRow()][destination.getCol()];
            if (!destinationPiece.isEmpty() && destinationPiece.getPlayer() != currentPlayer) {
                return true;
            }
        }

        return false;
    }
}

