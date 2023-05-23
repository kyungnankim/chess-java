package com.chess;
import com.chess.Pieces.*;
public class Board {
    private Piece[][] pieces;

    public Board() {
    	//pieces = new Piece[8][8];

        initializeBoard();
    }

    private void initializeBoard() {
        // 말의 초기 위치를 설정하는 로직을 구현
        // 말의 객체를 생성하여 pieces 배열에 배치
    	pieces = new Piece[8][8];

        // 흰색 말 배치
        pieces[0][0] = new Rook(Player.WHITE);
        pieces[0][1] = new Knight(Player.WHITE);
        pieces[0][2] = new Bishop(Player.WHITE);
        pieces[0][3] = new Queen(Player.WHITE);
        pieces[0][4] = new King(Player.WHITE);
        pieces[0][5] = new Bishop(Player.WHITE);
        pieces[0][6] = new Knight(Player.WHITE);
        pieces[0][7] = new Rook(Player.WHITE);
        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new Pawn(Player.WHITE);
        }

        // 검은색 말 배치
        pieces[7][0] = new Rook(Player.BLACK);
        pieces[7][1] = new Knight(Player.BLACK);
        pieces[7][2] = new Bishop(Player.BLACK);
        pieces[7][3] = new Queen(Player.BLACK);
        pieces[7][4] = new King(Player.BLACK);
        pieces[7][5] = new Bishop(Player.BLACK);
        pieces[7][6] = new Knight(Player.BLACK);
        pieces[7][7] = new Rook(Player.BLACK);
        for (int i = 0; i < 8; i++) {
            pieces[6][i] = new Pawn(Player.BLACK);
        }

        // 나머지 위치는 빈 공간으로 설정
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i][j] = new Empty();
            }
        }
    }

    public void printBoard() {
        // 보드 상태를 콘솔에 출력하는 로직을 구현
        System.out.println("  A B C D E F G H");
        System.out.println(" ┌───────────────┐");
        for (int row = 0; row < 8; row++) {
            System.out.print(row + 1 + "│ ");
            for (int col = 0; col < 8; col++) {
                Piece piece = pieces[row][col];
                System.out.print(piece.getSymbol() + " ");
            }
            System.out.println("│" + (row + 1));
        }
        System.out.println(" └───────────────┘");
        System.out.println("  A B C D E F G H");
    }

        // 유효한 이동 규칙 검사 로직...

        // 유효한 이동이라면 true 반환
        // 그렇지 않다면 false 반환
    	public boolean isValidMove(Move move, Player currentPlayer) {
	//public boolean isValidMove(Move move, Piece[][] pieces) {
		    int sourceRow = move.getSourcePosition().getRow();
		    int sourceCol = move.getSourcePosition().getColumn();
		    int destRow = move.getDestinationPosition().getRow();
		    int destCol = move.getDestinationPosition().getColumn();
		    Piece sourcePiece = pieces[sourceRow][sourceCol];
	   	 	Piece destPiece = pieces[destRow][destCol];
        // 해당 움직임이 유효한지 검사하는 로직 구현
        // 체스 규칙에 따라 움직일 수 있는지 확인
    	

		    if (sourcePiece.isEmpty() || sourcePiece.getPlayer() != currentPlayer) {
		        // 움직일 말이 현재 플레이어의 말이 아니거나, 빈 공간일 경우 유효하지 않음
		        return false;
		    }
	
		    if (sourcePiece.isValidMove(sourceRow, sourceCol, destRow, destCol, destPiece)) {
		        // 해당 말의 유효한 움직임인 경우 유효함
		        return true;
		    }
		   

		    return false;
    	}

		private int getRowFromPosition(String position) {
		    return 8 - Character.getNumericValue(position.charAt(1));
		}

		private int getColumnFromPosition(String position) {
		    return position.charAt(0) - 'A';
	    }
	
	    public void makeMove(Move move) {
	        // 움직임을 수행하는 로직을 구현
	        // 말의 위치를 변경하고, 기타 필요한 상태를 업데이트
	    	int sourceRow = getRowFromPosition(move.getSourcePosition());
		    int sourceCol = getColumnFromPosition(move.getSourcePosition());
		    int destRow = getRowFromPosition(move.getDestinationPosition());
		    int destCol = getColumnFromPosition(move.getDestinationPosition());
	
		    Piece sourcePiece = pieces[sourceRow][sourceCol];
		    Piece destPiece = pieces[destRow][destCol];
	
		    // 움직일 말을 대상 위치로 이동
		    pieces[destRow][destCol] = sourcePiece;
		    pieces[sourceRow][sourceCol] = new Empty();
	
		    // 특수한 움직임인 경우 추가 로직 수행
		    if (isCastleMove(move)) {
		        makeCastleMove(move);
		    }
	
		/* 기타 게임 상태 업데이트
		    //앙파상
		     * 
		    //폰 프로모션
		     * 
		    // 플레이어 변경 등 게임 진행
		     * 
		    // 체크/체크메이트/스테일메이트 상태 확인
		     * 
		    // 필요한 상태 업데이트 및 게임 진행
		    
		*/
	    }
    private int getRowFromPosition(Position position) {
        return position.getRow();
    }
    private int getColumnFromPosition(Position position) {
        return position.getColumn();
    }
    public boolean isCastleMove(Move move) {
        // 해당 움직임이 캐슬링(Castling)인지 확인 구현하기
    	Position sourcePosition = move.getSourcePosition();
    	int sourceRow = getRowFromPosition(sourcePosition);
    	int sourceCol = getColumnFromPosition(sourcePosition);
    	int destRow = move.getDestinationPosition().getRow();
    	int destCol = move.getDestinationPosition().getColumn();

    	//int destCol = getColumnFromPosition(move.getDestinationPosition());


	    Piece sourcePiece = pieces[sourceRow][sourceCol];

	    // 움직이는 말이 킹인지 확인
	    if (!(sourcePiece instanceof King)) {
	        return false;
	    }

	    // 킹의 움직임이 두 칸 옆으로 이동하는 경우인지 확인
	    if (Math.abs(sourceCol - destCol) != 2) {
	        return false;
	    }

	    // 움직이는 플레이어에게서 킹과 룩이 움직일 수 있는 상태인지 확인
	    Player currentPlayer = sourcePiece.getPlayer();
	    if (!isValidKingForCastle(currentPlayer) || !isValidRookForCastle(currentPlayer, destCol)) {
	        return false;
	    }

	    // 움직임이 유효하며 캐슬링 조건을 모두 만족하는 경우
	    return true;
	}

    	private boolean isValidKingForCastle(Player currentPlayer) {
    	    /*  킹이 이미 움직였는지 확인(킹의 초기 위치와 현재 위치를 비교하여 움직였는지 판단)
    	    	킹 주변에 아무런 말이 없는지 확인(킹이 이동할 경로에 다른 말이 없어야 함)
    	    	킹이 체크 상태인지 확인(킹을 공격할 수 있는 말이 있는지 확인)

	    	     위 조건을 만족하면 true, 그렇지 않으면 false 반환
	    	     필요에 따라 추가적인 조건을 검사할 수 있음
    	     */
    	    int kingRow = -1;
    	    int kingCol = -1;

    	    // 킹의 위치를 찾음
    	    for (int row = 0; row < 8; row++) {
    	        for (int col = 0; col < 8; col++) {
    	            Piece piece = pieces[row][col];
    	            if (piece instanceof King && piece.getPlayer() == currentPlayer) {
    	                kingRow = row;
    	                kingCol = col;
    	                break;
    	            }
    	        }
    	        if (kingRow != -1 && kingCol != -1) {
    	            break;
    	        }
    	    }

    	    // 킹이 이미 움직였는지 확인
    	    if (((King) pieces[kingRow][kingCol]).hasMoved()) {
    	        return false;
    	    }

    	    // 킹 주변에 아무런 말이 없는지 확인
    	    int[][] surroundingOffsets = {
    	        {-1, -1}, {-1, 0}, {-1, 1},
    	        {0, -1},           {0, 1},
    	        {1, -1},  {1, 0},  {1, 1}
    	    };
    	    
    	    
    	    
    	    for (int[] offset : surroundingOffsets) {
    	        int newRow = kingRow + offset[0];
    	        int newCol = kingCol + offset[1];
    	        /*
    	        if (!isValidPosition(newRow, newCol) || !pieces[newRow][newCol].isEmpty()) {
    	            return false;
    	        }
    	        */
    	    }

    	    // 킹이 체크 상태인지 확인
    	    if (isCheck(currentPlayer)) {
    	        return false;
    	    }

    	    return true;
    	}

    	private boolean isValidRookForCastle(Player currentPlayer, int destCol) {
    	    // 룩이 이미 움직였는지 확인
    	    // 예: 룩의 초기 위치와 현재 위치를 비교하여 움직였는지 판단

    	    // 룩과 킹 사이에 아무런 말이 없는지 확인
    	    // 예: 룩과 킹 사이의 경로에 다른 말이 없어야 함

    	    // 위 조건을 만족하면 true, 그렇지 않으면 false 반환
    	    // 필요에 따라 추가적인 조건을 검사할 수 있음
    		 int rookRow = -1;
    		    int rookCol = -1;

    		    // 룩의 위치를 찾음
    		    for (int row = 0; row < 8; row++) {
    		        for (int col = 0; col < 8; col++) {
    		            Piece piece = pieces[row][col];
    		            if (piece instanceof Rook && piece.getPlayer() == currentPlayer) {
    		                rookRow = row;
    		                rookCol = col;
    		                break;
    		            }
    		        }
    		        if (rookRow != -1 && rookCol != -1) {
    		            break;
    		        }
    		    }

    		    // 룩이 이미 움직였는지 확인
    		    if (((Rook) pieces[rookRow][rookCol]).hasMoved()) {
    		        return false;
    		    }

    		    // 룩과 킹 사이에 아무런 말이 없는지 확인
    		    int startCol = Math.min(rookCol, destCol) + 1;
    		    int endCol = Math.max(rookCol, destCol) - 1;
    		    for (int col = startCol; col <= endCol; col++) {
    		        if (!pieces[rookRow][col].isEmpty()) {
    		            return false;
    		        }
    		    }

    		    return true;
    }

    public void makeCastleMove(Move move) {
        // 캐슬링(Castling) 움직임을 수행
    	   int sourceRow = getRowFromPosition(move.getSourcePosition());
    	    int sourceCol = getColumnFromPosition(move.getSourcePosition());
    	    int destRow = getRowFromPosition(move.getDestinationPosition());
    	    int destCol = getColumnFromPosition(move.getDestinationPosition());

    	    Piece sourcePiece = pieces[sourceRow][sourceCol];
    	    Piece destPiece = pieces[destRow][destCol];

    	    // 킹과 룩의 움직임에 따라 말의 위치를 변경
    	    if (destCol == 2) {
    	        // 왼쪽 캐슬링
    	        pieces[sourceRow][sourceCol - 2] = sourcePiece;
    	        pieces[sourceRow][sourceCol] = new Empty();
    	        pieces[sourceRow][sourceCol - 1] = destPiece;
    	        pieces[sourceRow][0] = new Empty();
    	    } else if (destCol == 6) {
    	        // 오른쪽 캐슬링
    	        pieces[sourceRow][sourceCol + 2] = sourcePiece;
    	        pieces[sourceRow][sourceCol] = new Empty();
    	        pieces[sourceRow][sourceCol + 1] = destPiece;
    	        pieces[sourceRow][7] = new Empty();
    	    }
    }

    public boolean isCheckmate(Player currentPlayer) {
        // 체크메이트 상태인지 확인
        // 현재 플레이어가 체크 상태인지 확인
        if (!isCheck(currentPlayer)) {
            return false; // 체크 상태가 아니라면 체크메이트가 아님
        }

        // 현재 플레이어의 모든 말에 대해 유효한 움직임을 검사하여 체크를 풀 수 있는지 확인
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = pieces[row][col];
                if (!piece.isEmpty() && piece.getPlayer() == currentPlayer) {
                    for (int destRow = 0; destRow < 8; destRow++) {
                        for (int destCol = 0; destCol < 8; destCol++) {
                        	/* error 해결하기
                        	Move move = new Move(getPositionFromRowCol(row, col), getPositionFromRowCol(destRow, destCol));

                            //Move move = new Move(getPositionFromRowCol(row, col), getPositionFromRowCol(destRow, destCol));
                            if (isValidMove(move, currentPlayer)) {
                                // 유효한 움직임이 존재하면 체크를 풀 수 있음
                                return false;
                            }
                             */
                        }
                    }
                }
            }
        }

        return true; // 체크 상태이며 유효한 움직임이 없는 경우 체크메이트
    }
    //public boolean isCheck(Player player) {
    	public boolean isCheck(Player currentPlayer) {
        // 현재 플레이어의 킹의 위치 찾기
        int kingRow = -1;
        int kingCol = -1;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = pieces[row][col];
                if (piece instanceof King && piece.getPlayer() == currentPlayer) {
                    kingRow = row;
                    kingCol = col;
                    break;
                }
            }
        }

        // 상대 플레이어의 말들에 대해 킹을 공격할 수 있는지 확인
        Player opponentPlayer = (currentPlayer == Player.WHITE) ? Player.BLACK : Player.WHITE;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = pieces[row][col];
                /* error 해결하기
                if (!piece.isEmpty() && piece.getPlayer() == opponentPlayer) {
                    if (isValidMove(new Move(getPositionFromRowCol(row, col), getPositionFromRowCol(kingRow, kingCol)), opponentPlayer)) {
                        return true; // 킹을 공격할 수 있는 말이 존재하면 체크 상태
                    }
                }
                */
            }
        }

        return false; // 킹을 공격할 수 있는 말이 없으면 체크 아님
    }

    private String getPositionFromRowCol(int row, int col) {
        char colChar = (char) ('A' + col);
        int rowNumber = 8 - row;
        return String.valueOf(colChar) + rowNumber;
    }

    public boolean isStalemate(Player currentPlayer) {
    	  // 현재 플레이어가 체크 상태인지 확인
        if (isCheck(currentPlayer)) {
            return false; // 체크 상태라면 스테일메이트가 아님
        }

        // 현재 플레이어의 모든 말에 대해 유효한 움직임을 검사
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = pieces[row][col];
                if (!piece.isEmpty() && piece.getPlayer() == currentPlayer) {
                    for (int destRow = 0; destRow < 8; destRow++) {
                        for (int destCol = 0; destCol < 8; destCol++) {
                        	/* error 해결하기
                            Move move = new Move(getPositionFromRowCol(row, col), getPositionFromRowCol(destRow, destCol));
                           
                            if (isValidMove(move, currentPlayer)) {
                                // 유효한 움직임이 존재하면 스테일메이트가 아님
                                return false;
                            }
                            */
                        }
                    }
                }
            }
        }

        return true; // 체크 상태가 아니며 유효한 움직임이 없는 경우 스테일메이트
    }

}
