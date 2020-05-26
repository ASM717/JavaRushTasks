package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final  int AFTER = 1;

        if (this == o) return EQUAL;

        if (this.numberOfEmptyTiles > o.numberOfEmptyTiles) return AFTER;
        if (this.numberOfEmptyTiles < o.numberOfEmptyTiles) return BEFORE;

        if (this.numberOfEmptyTiles == o.numberOfEmptyTiles) {
            if (this.score > o.score) return AFTER;
            if (this.score < o.score) return BEFORE;
        }
        return EQUAL;
    }
}
