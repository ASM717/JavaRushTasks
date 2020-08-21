package com.javarush.task.task35.task3513;


import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        JFrame game = new JFrame();
        game.setTitle("2048");
        game.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("2048.png")));
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(780, 800);
        game.setResizable(false);

        game.add(controller.getView());

        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}
