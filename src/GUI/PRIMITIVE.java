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
public interface PRIMITIVE {

    public void draw(Graphics graphics);

    public Color getColor();

    public void setColor(Color color);

    public boolean isSelected();

    public void setSelected(boolean selected);

    public double getAngleOfRotation();

    public void setAngleOfRotation(double angleOfRotation);

    public void addToAngleOfRotation(double angleOfRotation);
}
