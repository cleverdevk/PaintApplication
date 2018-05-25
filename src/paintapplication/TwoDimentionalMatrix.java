/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintapplication;

/**
 *
 * @author dlsqo
 */
public class TwoDimentionalMatrix {

    public double m11, m12, m21, m22;

    public TwoDimentionalMatrix(double x11, double x12, double x21, double x22) {
        m11 = x11;
        m12 = x12;
        m21 = x21;
        m22 = x22;
    }

    public TwoDimentionalMatrix(double radian) {
        m11 = Math.cos(radian);
        m12 = -Math.sin(radian);
        m21 = Math.sin(radian);
        m22 = Math.cos(radian);
    }
}
