/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintapplication;

import java.io.Serializable;

/**
 *
 * @author dlsqo
 */
public class Coordinate implements Serializable {

    public int x, y;

    public Coordinate() {
    }

    public Coordinate(Coordinate c) {
        x = c.x;
        y = c.y;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void translate(int xvalue, int yvalue) {
        x += xvalue;
        y += yvalue;
    }

    public void ZeroCoordinate(Coordinate center) // translated coordinate by [center coordinate->(0,0)] translating
    {
        x -= center.x;
        y -= center.y;
    }

    public Coordinate getZeroCoordinate(Coordinate center) // translated coordinate by [center coordinate->(0,0)] translating
    {
        Coordinate result = new Coordinate();
        result.x = x - center.x;
        result.y = y - center.y;

        return result;
    }

    public Coordinate getMulMtxtoCoor(TwoDimentionalMatrix rotmat) {
        Coordinate result = new Coordinate();
        result.x = (int) (rotmat.m11 * x + rotmat.m12 * y);
        result.y = (int) (rotmat.m21 * x + rotmat.m22 * y);

        return result;
    }

    public void mulMtxtoCoor(TwoDimentionalMatrix rotmat) {
        Coordinate result = new Coordinate();
        result.x = (int) (rotmat.m11 * x + rotmat.m12 * y);
        result.y = (int) (rotmat.m21 * x + rotmat.m22 * y);
        this.x = result.x;
        this.y = result.y;
    }
}
