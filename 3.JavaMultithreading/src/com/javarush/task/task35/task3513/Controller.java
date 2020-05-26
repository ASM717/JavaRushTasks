package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private Model model;
    private View view;
    private static final int WINNING_TILE = 2048;

    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }


    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public int getScore() {
        return model.score;
    }

    //сброс игры
    public void resetGame() {
        model.resetGameTiles();
        model.score = 0;
        view.isGameWon = false;
        view.isGameLost = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            resetGame();
        } else if (model.canMove() == false) {
            view.isGameLost = true;
        } else if (!view.isGameLost && !view.isGameWon) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT : model.left();
                break;
                case KeyEvent.VK_RIGHT : model.right();
                break;
                case KeyEvent.VK_UP : model.up();
                break;
                case KeyEvent.VK_DOWN : model.down();
                break;
                case KeyEvent.VK_Z : model.rollback();
                break;
                case KeyEvent.VK_R : model.randomMove();
                break;
                case KeyEvent.VK_A : model.autoMove();
            }
        }
        if (model.maxTile == WINNING_TILE) {
            view.isGameWon = true;
        }
        view.repaint();
    }
}
