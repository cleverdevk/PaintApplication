/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintapplication;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.Serializable;

/**
 *
 * @author dlsqo
 */
public abstract class Figure implements ControllerFunctions, Serializable {

    private Coordinate p1;
    private Coordinate center = new Coordinate();
    private boolean selected = false;
    private float thickness = 1;
    private boolean isColored = false;
    private Color color = Color.BLACK;
    private Polygon pg;
    private float rotationhistory = 0;

    //Constructors
    public Figure() {
    }

    public Figure(int X, int Y) {
        this.p1.x = X;
        this.p1.y = Y;
    }

    //implemented method
    public void translate(int changedX1, int changedY1) {

    }

    public void rotate(float radian) {

    }

    public void paint(Graphics g) {

    }

    public void scale(int scaledX1, int scaledY1, int idx) {

    }

    public void color(boolean isColored, Color c, Graphics g) {

    }

    // methods
    public Figure getCloneFigure(Figure f) {
        return null;
    }

    public void setAttribute(Figure f) {

    }

    public void paintVertax(Graphics g) // draw conner points method
    {

    }

    public boolean isInBound(Coordinate c) {
        return false;
    }

    public void setCoordinate(int X, int Y) {
        setP1(new Coordinate(X, Y));
    }

    public void setCenter(int cx, int cy) {
        center.x = cx;
        center.y = cy;
    }

    public Coordinate aboutCorner(Coordinate c) {
        return null;
    }
    //***************************getters & setters***************************

    public Coordinate getP1() {
        return p1;
    }

    public void setP1(Coordinate p1) {
        this.p1 = p1;
    }

    public Coordinate getCenter() {
        return center;
    }

    public void setCenter(Coordinate center) {
        this.center = center;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public float getThickness() {
        return thickness;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean getSelected() {
        return selected;
    }

    public Polygon getPg() {
        return pg;
    }

    public boolean getIsColored() {
        return isColored;
    }

    public void setIsColored(boolean isColored) {
        this.isColored = isColored;
    }

    public void setPg(Polygon pg) {
        this.pg = pg;
    }

    public float getRotationhistory() {
        return rotationhistory;
    }

    public void setRotationhistory(float rotationhistory) {
        this.rotationhistory = rotationhistory;
    }

}
