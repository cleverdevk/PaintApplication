/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintapplication;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 *
 * @author dlsqo
 */
public class myLine extends Figure {

    private Coordinate p2;

    public myLine(Coordinate x1, Coordinate x2) {
        setP1(x1);
        p2 = x2;
    }

    public void paint(Graphics g) {
        if(getIsColored()){
            g.setColor(getColor());
            g.drawLine(getP1().x, getP1().y, p2.x, p2.y);
        }
        else
        {
            g.setColor(Color.BLACK);
            g.drawLine(getP1().x, getP1().y, p2.x, p2.y);
        }
    }

    public void rotate(float radian) {
        setCenter((getP1().x + p2.x) / 2, (getP1().y + p2.y) / 2); // set Center Coordinate
        // translate to (0,0)
        getP1().ZeroCoordinate(getCenter());
        p2.ZeroCoordinate(getCenter());
        // multiply the rotating matrix
        getP1().mulMtxtoCoor(new TwoDimentionalMatrix(radian * Math.PI));
        p2.mulMtxtoCoor(new TwoDimentionalMatrix(radian * Math.PI));
        // translating to original position
        getP1().translate(getCenter().x, getCenter().y);
        p2.translate(getCenter().x, getCenter().y);
        setPg(new Polygon(getArrayX(), getArrayY(), 2));
    }
    
    public void color(boolean isColored, Color c, Graphics g) {
        setIsColored(isColored);
        setColor(c);
    }

    public void translate(int changedX1, int changedY1) {
        getP1().translate(changedX1, changedY1);
        p2.translate(changedX1, changedY1);
    }

    
    public int[] getArrayX() {
        int[] result = new int[2];
        result[0] = getP1().x;
        result[1] = p2.x;

        return result;
    }

    public int[] getArrayY() {
        int[] result = new int[2];
        result[0] = getP1().y;
        result[1] = p2.y;

        return result;
    }

    //***************************getters & setters***************************
    public Coordinate getP2() {
        return p2;
    }

    public void setP2(Coordinate p2) {
        this.p2 = p2;
    }

}
