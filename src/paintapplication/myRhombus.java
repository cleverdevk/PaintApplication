/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintapplication;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import sun.java2d.loops.DrawPolygons;

/**
 *
 * @author dlsqo
 */
public class myRhombus extends Figure {

    private Coordinate p2, p3, p4;

    public myRhombus(Coordinate x1, Coordinate x2, Coordinate x3, Coordinate x4) {
        setP1(x1);
        p2 = x2;
        p3 = x3;
        p4 = x4;
        setPg(new Polygon(getArrayX(), getArrayY(), 4));
    }

    public Figure getCloneFigure(Figure f) {
        myRhombus c = (myRhombus) f;
        myRhombus result = new myRhombus(new Coordinate(c.getP1()), new Coordinate(c.getP2()), new Coordinate(c.getP3()), new Coordinate(c.getP4()));
        result.setColor(c.getColor());
        result.setIsColored(c.getIsColored());
        result.setRotationhistory(c.getRotationhistory());
        return result;
    }

    public void setAttribute(Figure f) {
        myRhombus c = (myRhombus) f;
        setP1(c.getP1());
        setP2(c.getP2());
        setP3(c.getP3());
        setP4(c.getP4());
        setCenter();
        this.setIsColored(c.getIsColored());
        setPg(new Polygon(getArrayX(), getArrayY(), 4));
    }

    public int[] getArrayX() {
        int[] result = new int[4];
        result[0] = getP1().x;
        result[1] = p2.x;
        result[2] = p3.x;
        result[3] = p4.x;

        return result;
    }

    public int[] getArrayY() {
        int[] result = new int[4];
        result[0] = getP1().y;
        result[1] = p2.y;
        result[2] = p3.y;
        result[3] = p4.y;

        return result;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(getColor());
        g2d.setColor(getColor());
        if (getIsColored()) {
            g2d.fillPolygon(getPg());
        } else {
            g.drawPolygon(getPg());
        }
        if (getSelected()) {
            paintVertax(g);
        }
    }

    public void color(boolean isColored, Color c, Graphics g) {
        setIsColored(isColored);
        setColor(c);
    }

    public void rotate(float radian) {

        setCenter(); // set Center Coordinate
        // translate to (0,0)
        getP1().ZeroCoordinate(getCenter());
        p2.ZeroCoordinate(getCenter());
        p3.ZeroCoordinate(getCenter());
        p4.ZeroCoordinate(getCenter());
        // multiply the rotating matrix
        getP1().mulMtxtoCoor(new TwoDimentionalMatrix(radian * Math.PI));
        p2.mulMtxtoCoor(new TwoDimentionalMatrix(radian * Math.PI));
        p3.mulMtxtoCoor(new TwoDimentionalMatrix(radian * Math.PI));
        p4.mulMtxtoCoor(new TwoDimentionalMatrix(radian * Math.PI));
        // translating to original position
        getP1().translate(getCenter().x, getCenter().y);
        p2.translate(getCenter().x, getCenter().y);
        p3.translate(getCenter().x, getCenter().y);
        p4.translate(getCenter().x, getCenter().y);
        setPg(new Polygon(getArrayX(), getArrayY(), 4));
    }

    public void translate(int changedX1, int changedY1) {
        getP1().translate(changedX1, changedY1);
        p2.translate(changedX1, changedY1);
        p3.translate(changedX1, changedY1);
        p4.translate(changedX1, changedY1);
        setPg(new Polygon(getArrayX(), getArrayY(), 4));
    }

    public void scale(int scaledX1, int scaledY1, int idx) {
        if (idx == 0) {
            setP1(new Coordinate(getP1().x, scaledY1));
            p2.y = (getP1().y + p3.y) / 2;
            p4.y = (getP1().y + p3.y) / 2;
        }
        if (idx == 1) {
            p2.x = scaledX1;
            setP1(new Coordinate((p2.x + p4.x) / 2, getP1().y));
            p3.x = (p2.x + p4.x) / 2;
        }
        if (idx == 2) {
            p3.y = scaledY1;
            p2.y = (getP1().y + p3.y) / 2;
            p4.y = (getP1().y + p3.y) / 2;
        }
        if (idx == 3) {
            p4.x = scaledX1;
            setP1(new Coordinate((p2.x + p4.x) / 2, getP1().y));
            p3.x = (p2.x + p4.x) / 2;
        }    
        setPg(new Polygon(getArrayX(), getArrayY(), 4));
    }

    public void setCenter() {
        int cx, cy;
        cx = (getP1().x + p2.x + p3.x + p4.x) / 4;
        cy = (getP1().y + p2.y + p3.y + p4.y) / 4;
        setCenter(new Coordinate(cx, cy));
    }

    public void paintVertax(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(getP1().x - 1, getP1().y, 5, 5);
        g2d.fillRect(p2.x - 1, p2.y, 5, 5);
        g2d.fillRect(p3.x - 1, p3.y, 5, 5);
        g2d.fillRect(p4.x - 1, p4.y - 2, 5, 5);
        g.setColor(getColor());
    }

    public boolean isInBound(Coordinate c) {
        return getPg().contains(c.x, c.y);
    }

    public Coordinate aboutCorner(Coordinate c) {
        if (Math.abs(c.x - getP1().x) < 20 && Math.abs(c.y - getP1().y) < 20) {
            return getP1();
        }
        if (Math.abs(c.x - p2.x) < 5 && Math.abs(c.y - p2.y) < 5) {
            return p2;
        }
        if (Math.abs(c.x - p3.x) < 5 && Math.abs(c.y - p3.y) < 5) {
            return p3;
        }
        if (Math.abs(c.x - p4.x) < 5 && Math.abs(c.y - p4.y) < 5) {
            return p4;
        }
        return null;
    }

    //***************************getters & setters***************************
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
