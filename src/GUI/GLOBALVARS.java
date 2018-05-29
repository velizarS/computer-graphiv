/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author Velizar
 */
public class GLOBALVARS {

    public static ArrayList<PRIMITIVE> figures = new ArrayList<PRIMITIVE>();
    public static Point startPoint;
    public static Graphics2D graphics2D;
    public static int lastX = 0;
    public static int lastY = 0;
    public static int lastWidth = 0;
    public static int lastHeight = 0;
    public static Timer drawTimer;
    public static int drawInterval = 100; // милисекунди
    public static Point canvasSize = new Point();
    public static boolean ctrlPressed;
    public static JPanel jPanelObjectColor;
    public static Point moveLastPoint = new Point();
    public static JSlider jSliderRotation;
    public static int sliderLastPosition = 0;
    public static JList jListFigures;

    public static void clearLastRect() {
        graphics2D.clearRect(lastX-1, lastY-1, lastWidth+2, lastHeight+2);
    }

    public static void drawFigures() {
        graphics2D.clearRect(1, 1, canvasSize.x-2, canvasSize.y-2);

        int selectedIndices[] = new int[figures.size()];
        Arrays.fill(selectedIndices, -1);
        int j = 0;

        for (int i = 0; i < figures.size(); i++) {
            PRIMITIVE primitive = figures.get(i);
            primitive.draw(graphics2D);
            if(figures.get(i).isSelected()) {
                 selectedIndices[j++] = i;
            }
        }

        jListFigures.setListData(figures.toArray());
        jListFigures.setSelectedIndices(selectedIndices);
    }

    public static void select(Rectangle2D rectangle2D) {
        boolean area = true;
        if(rectangle2D.getWidth() < 2 && rectangle2D.getHeight() < 2) {
            area = false;
        }

        if(area) {
            for (int i = 0; i < figures.size(); i++) {
                PRIMITIVE primitive = figures.get(i);
                Rectangle2D primitiveRectangle2D = (Rectangle2D)figures.get(i);

                AffineTransform at = AffineTransform.getRotateInstance(
                        Math.toRadians(primitive.getAngleOfRotation()),
                        primitiveRectangle2D.getCenterX(),
                        primitiveRectangle2D.getCenterY()
                        );

                boolean toBeSelected = Path2D.contains(
                        rectangle2D.getPathIterator(at),
                        primitiveRectangle2D
                        );

                if(toBeSelected) {
                    primitive.setSelected(true);
                    jPanelObjectColor.setBackground(primitive.getColor());
                    sliderLastPosition = (int)primitive.getAngleOfRotation();
                    jSliderRotation.setValue((int)primitive.getAngleOfRotation());
                } else if(!ctrlPressed) {
                    primitive.setSelected(false);
                }
            }
        } else {
            if(!ctrlPressed) deselectAll();
            for (int i = 0; i < figures.size(); i++) {
                PRIMITIVE primitive = figures.get(i);
                Rectangle2D primitiveRectangle2D = (Rectangle2D)figures.get(i);

                AffineTransform at = AffineTransform.getRotateInstance(
                        Math.toRadians(primitive.getAngleOfRotation()),
                        primitiveRectangle2D.getCenterX(),
                        primitiveRectangle2D.getCenterY()
                        );

                boolean toBeSelected = Path2D.contains(
                        primitiveRectangle2D.getPathIterator(at),
                        new Point((int)rectangle2D.getX(), (int)rectangle2D.getY())
                        );

                if(toBeSelected) {
                    primitive.setSelected(true);
                    jPanelObjectColor.setBackground(primitive.getColor());
                    sliderLastPosition = (int)primitive.getAngleOfRotation();
                    jSliderRotation.setValue((int)primitive.getAngleOfRotation());
                    break;
                } else if(!ctrlPressed) {
                    primitive.setSelected(false);
                }
            }
        }
    }

    public static void deselectAll() {
        for (int i = 0; i < figures.size(); i++) {
            figures.get(i).setSelected(false);
        }
    }
}
