/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Velizar
 */
public class Elipse extends ComonPrimitive implements PRIMITIVE {

    public Elipse(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        setName("Елипса");
    }

    public void draw(Graphics graphics) {
        doPreDraw(graphics);
        graphics.fillOval(x, y, width, height);
        doPostDraw(graphics);
    }
}
