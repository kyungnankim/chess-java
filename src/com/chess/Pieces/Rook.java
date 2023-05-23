package com.chess.Pieces;

import com.chess.Move;
import com.chess.Piece;
import com.chess.Player;
import com.chess.Position;

public class Rook extends Piece {
    public Rook(Player player) {
        super(player);
    }
    @Override
    public String getSymbol() {
        return "R"; // Rook의 기호를 나타내는 값 반환
    }
    @Override
    public boolean isValidMove(Move move, Piece[][] board) {
        Position source = move.getSourcePosition();
        Position destination = move.getDestinationPosition();

        // 룩의 이동은 수평 또는 수직 방향으로만 가능
        if (!source.isInStraightLineWith(destination)) {
            return false;
        }

        // 이동 경로에 다른 말이 있는지 확인
        if (isPathBlocked(source, destination, board)) {
            return false;
        }

        // 목적지 위치에 적 말이 있는지 확인
        Piece destinationPiece = board[destination.getRow()][destination.getCol()];
        if (destinationPiece != null && destinationPiece.getPlayer() == getPlayer()) {
            return false; // 목적지에 같은 플레이어의 말이 있는 경우
        }

        return true; // 유효한 이동인 경우
    }

    private boolean isPathBlocked(Position source, Position destination, Piece[][] board) {
        int startRow = source.getRow();
        int startCol = source.getCol();
        int destRow = destination.getRow();
        int destCol = destination.getCol();

        // 수평 이동
        if (startRow == destRow) {
            int step = (destCol - startCol) / Math.abs(destCol - startCol);
            for (int col = startCol + step; col != destCol; col += step) {
                if (board[startRow][col] != null) {
                    return true; // 이동 경로에 다른 말이 있는 경우
                }
            }
        }
        // 수직 이동
        else if (startCol == destCol) {
            int step = (destRow - startRow) / Math.abs(destRow - startRow);
            for (int row = startRow + step; row != destRow; row += step) {
                if (board[row][startCol] != null) {
                    return true; // 이동 경로에 다른 말이 있는 경우
                }
            }
        }

        return false; // 이동 경로에 다른 말이 없는 경우
    }


}

