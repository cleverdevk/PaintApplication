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
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author dlsqo
 */
public class myFreecurve extends Figure {

    private ArrayList<myLine> lines = new ArrayList<>();
    private Rectangle forBound = new Rectangle();

    public myFreecurve() {
    }

    public myFreecurve(ArrayList<myLine> lines) {
        this.lines = lines;
        setCenter((int) forBound.getCenterX(), (int) forBound.getCenterY());
    }

    public void updateFreecurve() {
        int maxx, minx, maxy, miny;
        maxx = lines.get(0).getP1().x;
        maxy = lines.get(0).getP1().y;
        minx = lines.get(0).getP1().x;
        miny = lines.get(0).getP1().y;
        for (myLine l : lines) {
            if (l.getP1().x > maxx) {
                maxx = l.getP1().x;
            }
            if (l.getP1().y > maxy) {
                maxy = l.getP1().y;
            }
            if (l.getP1().x < minx) {
                minx = l.getP1().x;
            }
            if (l.getP1().y < miny) {
                miny = l.getP1().y;
            }
        }
        forBound = new Rectangle(minx, miny, maxx - minx, maxy - miny);
        setCenter((int) forBound.getCenterX(), (int) forBound.getCenterY());
    }

    public void paint(Graphics g) {
        g.setColor(this.getColor());
        for (myLine l : lines) {
            l.paint(g);
        }
        if (getSelected()) {
            paintVertax(g);
        }
    }

    public void rotate(float radian) {
        for (myLine l : lines) {
            l.getP1().ZeroCoordinate(getCenter());
            l.getP2().ZeroCoordinate(getCenter());

            l.getP1().mulMtxtoCoor(new TwoDimentionalMatrix(radian * Math.PI));
            l.getP2().mulMtxtoCoor(new TwoDimentionalMatrix(radian * Math.PI));

            l.getP1().translate(getCenter().x, getCenter().y);
            l.getP2().translate(getCenter().x, getCenter().y);
            l.setPg(new Polygon(l.getArrayX(), l.getArrayY(), 2));
        }
        updateFreecurve();
    }
    
    public void color(boolean isColored, Color c, Graphics g) {
        setIsColored(isColored);
        g.setColor(c);
        for (myLine l : lines) {
            l.color(isColored, c, g);
        }
    }

    public boolean isInBound(Coordinate c) {
        return forBound.contains(c.x, c.y);
    }

    public void paintVertax(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect((int) forBound.getX(), (int) forBound.getY(), 5, 5);
        g2d.fillRect((int) (forBound.getX() + forBound.getWidth()), (int) forBound.getY(), 5, 5);
        g2d.fillRect((int) (forBound.getX() + forBound.getWidth()), (int) (forBound.getY() + forBound.getHeight()), 5, 5);
        g2d.fillRect((int) forBound.getX(), (int) (forBound.getY() + forBound.getHeight()), 5, 5);
        g.setColor(getColor());
    }

    public void translate(int changedX1, int changedY1) {
        for (myLine l : lines) {
            l.translate(changedX1, changedY1);
        }
        updateFreecurve();
    }

    public ArrayList<myLine> getLines() {
        return lines;
    }

    public void setLines(ArrayList<myLine> lines) {
        this.lines = lines;
    }

    public void addLine(myLine l) {
        l.setColor(getColor());
        lines.add(l);
    }

}
