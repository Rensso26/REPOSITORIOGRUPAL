package models;

import Actions.Drawable;

import java.awt.*;

public class Life implements Drawable {
   int life;

    public Life(int life){
        this.life = life;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(20, 20, life*2, 15);
    }
}
