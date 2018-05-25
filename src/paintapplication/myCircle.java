/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintapplication;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javafx.scene.shape.Circle;

/**
 *
 * @author dlsqo
 */
public class myCircle extends Figure {
    Coordinate p2,p3,p4;
    int r;

    public myCircle(Coordinate x, int r) {
        setP1(x);
        this.r = r;
    }

    public Figure getCloneFigure(Figure f) {
        myCircle c = (myCircle) f;
        myCircle result = new myCircle(new Coordinate(c.getP1()), c.getR());
        result.setColor(c.getColor());
        result.setIsColored(c.getIsColored());
        return result;
    }

    public void setAttribute(Figure f) {
        myCircle c = (myCircle) f;
        setP1(c.getP1());
        this.r = c.getR();
        this.setIsColored(c.getIsColored());
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(getColor());
        g2d.setColor(getColor());
        if (getIsColored()) {
            g2d.fillOval(getP1().x, getP1().y, r, r);
        } else {
            g.drawOval(getP1().x, getP1().y, r, r);
        }
        if (getSelected()) {
            paintVertax(g);
        }
    }

    public void paintVertax(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g2d.setColor(Color.BLACK);
        g2d.fillRect((getP1().x + r / 2) - 2, getP1().y, 5, 5);
        g2d.fillRect((getP1().x + r / 2) - 2, getP1().y + r, 5, 5);
        g2d.fillRect(getP1().x, getP1().y + r / 2, 5, 5);
        g2d.fillRect(getP1().x + r, getP1().y + r / 2, 5, 5);
        g.setColor(getColor());
    }
    

    public boolean isInBound(Coordinate c) {

        setCenter(getP1().x + r / 2, getP1().y + r / 2);
        double distance;
        distance = Math.sqrt(Math.pow(c.x - getCenter().x, 2) + Math.pow(c.y - getCenter().y, 2));
        if (distance < r / 2) {
            return true;
        } else {
            return false;
        }
    }

    public void translate(int changedX1, int changedY1) {
        getP1().translate(changedX1, changedY1);
    }

    public void color(boolean isColored, Color c, Graphics g) {
        setIsColored(isColored);
        setColor(c);
    }

    //***************************getters & setters***************************
    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public Coordinate getP2() {
        return p2;
    }

    public void setP2(Coordinate p2) {
        this.p2 = p2;
    }

    public Coordinate getP3() {
        return p3;
    }

    public void setP3(Coordinate p3) {
        this.p3 = p3;
    }

    public Coordinate getP4() {
        return p4;
    }

    public void setP4(Coordinate p4) {
        this.p4 = p4;
    }

}
