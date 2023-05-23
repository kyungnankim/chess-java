package com.chess;

public class Position {
    private int row;
    private int col;
    private int column;
    
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position(String position) {
    	// position을 파싱하여 row와 col 설정
        // ex) "A1"이라는 문자열을 받으면 row=0, col=0으로 설정
        char columnChar = position.charAt(0);
        int columnValue = Integer.parseInt(position.substring(1)) - 1;

        this.row = columnChar - 'A';
        this.col = columnValue;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    public int getColumn() {
        return column;
    }
    
    public boolean isInStraightLineWith(Position position) {
        return this.getRow() == position.getRow() || this.getCol() == position.getCol() ||
                Math.abs(this.getRow() - position.getRow()) == Math.abs(this.getCol() - position.getCol());
    }
    /*
    private int getColumnFromPosition(Position position) {
        return position.getColumn();
    }
    */
    
}
