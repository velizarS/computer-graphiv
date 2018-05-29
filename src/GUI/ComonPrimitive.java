/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Velizar
 */
public class ComonPrimitive extends Rectangle {
 private Color color;
    private boolean selected;
    private double angleOfRotation = 0; // градуси
    private String name;

    public ComonPrimitive(int x, int y, int width, int height, Color color) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public double getAngleOfRotation() {
        return angleOfRotation;
    }

    private double calculateAngle(double angle) {
        if(angle > 90) return (angle % 90) - 90;
        if(angle < -90) return 90 + (angle % 90);
        return angle;
    }

    public void setAngleOfRotation(double angleOfRotation) {
        this.angleOfRotation = calculateAngle(angleOfRotation);
    }

    public void addToAngleOfRotation(double angleOfRotation) {
        this.angleOfRotation = calculateAngle(this.angleOfRotation + angleOfRotation);
    }

    public void drawBorder(Graphics graphics) {
        if(selected) {
            graphics.setColor(Color.BLACK);
            ((Graphics2D)graphics).setStroke(new BasicStroke(
                    2,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_BEVEL,
                    0,
                    new float[]{2, 2},
                    0));

            graphics.drawRect(x-1, y-1, width+2, height+2);
        }
    }

    public void doPreDraw(Graphics graphics) {
        graphics.setColor(getColor());
        ((Graphics2D)graphics).rotate(
            Math.toRadians(getAngleOfRotation()),
            getCenterX(),
            getCenterY()
            );
    }

    public void doPostDraw(Graphics graphics) {
        drawBorder(graphics);
        ((Graphics2D)graphics).rotate(
            -Math.toRadians(getAngleOfRotation()),
            getCenterX(),
            getCenterY()
            );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName() + " (" + x + "," + y + ")";
    }
}
