/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 *
 * @author Velizar
 */
public class Star extends ComonPrimitive implements PRIMITIVE {

    public Star(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        setName("Star");
    }

    public void draw(Graphics graphics) {
        doPreDraw(graphics);

        Polygon polygon = new Polygon();

        int numberOfVertices = 5;
        int alpha = 360 / numberOfVertices;
        int startAngle = 18;

        int outerXRadius = width / 2;
        int outerYRadius = height / 2;

        int innerXRadius = (int) (outerXRadius * Math.cos(Math.toRadians(alpha))
                / Math.cos(Math.toRadians(alpha / 2)));
        int innerYRadius = (int) (outerYRadius * Math.cos(Math.toRadians(alpha))
                / Math.cos(Math.toRadians(alpha / 2)));

        for (int i = startAngle; i < 360; i += alpha / 2) {
            int xRadius, yRadius;
            if ((i - startAngle) % alpha == 0) {
                xRadius = innerXRadius;
                yRadius = innerYRadius;
            } else {
                xRadius = outerXRadius;
                yRadius = outerYRadius;
            }

            polygon.addPoint(
                    x + width / 2 + (int) (Math.cos(Math.toRadians(i)) * xRadius),
                    y + height / 2 + (int) (Math.sin(Math.toRadians(i)) * yRadius)
            );
        }

        graphics.fillPolygon(polygon);

        doPostDraw(graphics);
    }
}
