package models;

import Actions.Contaible;
import Actions.Drawable;

import java.awt.*;

public class Score implements Drawable, Contaible {

    private int score;
    public Score(int score) {
        this.score = score;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.drawString("Score: " + score, 650, 20);
    }


    @Override
    public void plus(int i) {
        score += i;

    }

    @Override
    public void minus(int i) {
        score -= i;
    }
}
