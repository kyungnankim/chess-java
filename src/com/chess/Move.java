package com.chess;

public class Move {
    private Position source;
    private Position destination;
    private Position sourcePosition;
    private Position destinationPosition;
    
    public Move(Position source, Position destination,Position sourcePosition, Position destinationPosition) {
        this.source = source;
        this.destination = destination;
        this.sourcePosition = sourcePosition;
        this.destinationPosition = destinationPosition;
    }

    public Position getSourcePosition() {
        return sourcePosition;
    }

    public Position getDestinationPosition() {
        return destinationPosition;
    }
    public Position getSource() {
        return source;
    }

    public Position getDestination() {
        return destination;
    }
}