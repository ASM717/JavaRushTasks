package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;

    private Stack<Integer> previousScores = new Stack<>();
    private Stack<Tile[][]> previousStates = new Stack<>();
    private boolean isSaveNeeded = true;

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public void printTiles() {
        for (int i = 0; i < gameTiles.length; i++) {
            Tile[] gameTile = gameTiles[i];
            for (int j = 0; j < gameTile.length; j++) {
                Tile tile = gameTile[j];
                System.out.print(tile.value + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    public void resetGameTiles() {

        gameTiles = new Tile[Model.FIELD_WIDTH][Model.FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();

    }

    public Model() {
        resetGameTiles();
        score = 0;
        maxTile = 2;

    }

    private List<Tile> getEmptyTiles() {
        List<Tile> listEmptyTiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == 0) listEmptyTiles.add(gameTiles[i][j]);
            }
        }
        return listEmptyTiles;
    }

    public void addTile() {
        List<Tile> listEmptyTiles = getEmptyTiles();
        if (listEmptyTiles.size() > 0) {
            int randomIndex = (int) (listEmptyTiles.size() * Math.random());
            int randomWeight = Math.random() < 0.9 ? 2 : 4;
            listEmptyTiles.get(randomIndex).value = randomWeight;
        }
    }

    //private
    private boolean compressTiles(Tile[] tiles) {
        Tile[] tempTiles = tiles.clone();
        ArrayList<Tile> tempArray = new ArrayList<>();
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].value > 0) {
                tempArray.add(tiles[i]);
            }
        }
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (i < tempArray.size()) {
                tiles[i] = tempArray.get(i);
            } else {
                tiles[i] = new Tile();
            }
        }
        for (int i = 0; i < tempTiles.length; i++) {
            if (tempTiles[i].value != tiles[i].value) return true;
        }
        return false;
    }

    //private
    private boolean mergeTiles(Tile[] tiles) {
        Tile[] tempTile = tiles.clone();
        ArrayList<Tile> tempArray = new ArrayList<>();
        for (int i = 0; i < tiles.length; i++) {
            tempArray.add(tiles[i]);

        }
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tempArray.get(i).equals(tempArray.get(i + 1))) {
                tempArray.get(i).value = 2 * tempArray.get(i).value;
                tempArray.remove(i + 1);
                tempArray.add(new Tile());
                score += tempArray.get(i).value;
                if (tempArray.get(i).value > maxTile) maxTile = tempArray.get(i).value;
            }
        }
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = tempArray.get(i);
        }
        for (int i = 0; i < tempTile.length; i++) {
            if (tempTile[i].value != tiles[i].value) return true;
        }
        return false;

    }

    private boolean move() {
        boolean triggerChanged = false;
        for (int i = 0; i < gameTiles.length; i++) {
            if (compressTiles(getGameTiles()[i])) {
                triggerChanged = true;
            }
            if (mergeTiles(getGameTiles()[i])) {
                triggerChanged = true;
            }
        }
        return triggerChanged;
    }

    void left() {
        if (move()) {
            addTile();
        }
    }


    public void right() {
        rotate();
        rotate();
        if (move()) {
            addTile();
        }
        rotate();
        rotate();
    }

    public void up() {
        rotate();
        rotate();
        rotate();
        if (move()) {
            addTile();
        }
        rotate();

    }

    public void down() {
        rotate();
        if (move()) {
            addTile();
        }
        rotate();
        rotate();
        rotate();
    }

    public void  rotate() {
        /*Поворачиваем массив против часовой стрелки */
        Tile[][] tempTile = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles.length; j++) {
                tempTile[i][FIELD_WIDTH - 1 - j] = gameTiles[j][i];
            }
        }
        gameTiles = tempTile.clone();
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty())
            return true;
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j - 1].value)
                    return true;
            }
        }
        for (int j = 0; j < gameTiles.length; j++) {
            for (int i = 1; i < gameTiles.length; i++) {
                if (gameTiles[i][j].value == gameTiles[i - 1][j].value)
                    return true;
            }
        }
        return false;
    }

    private void saveState (Tile[][] tiles) {

        Tile[][] tiles1 = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                tiles1[i][j] = new Tile(tiles[i][j].value);
            }
        }

        previousStates.push(tiles1);

        previousScores.push(score);

        isSaveNeeded = false;

    }

    public void rollback() {

        if (!previousStates.empty() & !previousScores.empty()) {
            this.gameTiles = previousStates.pop();
            this.score = previousScores.pop();
        }
    }
}


