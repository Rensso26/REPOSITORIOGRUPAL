package models;

import Actions.Drawable;

import java.awt.*;

public class Line implements Drawable {

    @Override
    public void draw(Graphics graphics) {

        graphics.setColor(Color.RED);
        graphics.drawLine(0, (int) ((600)*0.66), 800, (int) (600*0.66));

    }
}
