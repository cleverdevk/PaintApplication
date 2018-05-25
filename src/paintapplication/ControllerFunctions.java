/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintapplication;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author dlsqo
 */
public interface ControllerFunctions {

    public void paint(Graphics g);

    public void translate(int changedX1, int changedY1);

    public void scale(int scaledX1, int scaledY1, int idx);

    public void rotate(float radian);

    public void color(boolean isColored, Color c, Graphics g);
}
