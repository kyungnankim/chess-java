package com.chess;

public abstract class Piece {
    private Player player;
    private boolean isEmpty;

    public Piece(Player player) {
        this.player = player;
        this.isEmpty = false;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
    
    public abstract String getSymbol();

    public abstract boolean isValidMove(Move move, Piece[][] pieces);

}



