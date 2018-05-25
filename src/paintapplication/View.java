/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintapplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author dlsqo
 */
public class View extends JFrame {

    private Status currentStatus = new Status();
    private Status previousStatus = new Status();

    //SWING LAYOUT VARIABLES
    MyPanel pnMain = new MyPanel();
    JButton btCircle = new JButton("Circle");
    JButton btRectangle = new JButton("Rectangle");
    JButton btTriangle = new JButton("Triangle");
    JButton btRhombus = new JButton("Rhombus");
    JButton btLine = new JButton("Line");
    JButton btFreecurve = new JButton("Free Curve");
    JButton btSelect = new JButton("Select");
    JButton btRotate = new JButton("Rotate");
    JButton btColor = new JButton("Color");
    JButton btRemoveColor = new JButton("RemoveColor");
    JButton btRemove = new JButton("Remove");
    JButton btSave = new JButton("Save");
    JButton btOpen = new JButton("Open");
    JButton btUndo = new JButton("Undo");
    JTextField tfRotate = new JTextField();
    JTextField tfPath = new JTextField();
    JLabel[] lbColorsArray = new JLabel[10];

    FileDialog fdRead, fdWrite;

    Container ctnMain = this.getContentPane();
    Container ctnNorth = new Container();
    Container ctnCenter = new Container();
    Container ctnSouth = new Container();

    //select RGB color FRAME & Component
    JFrame fmColor = new JFrame("Color Select");
    JLabel lbR = new JLabel("R : ");
    JLabel lbG = new JLabel("G : ");
    JLabel lbB = new JLabel("B : ");

    JTextField tfR, tfG, tfB;
    JButton btOk, btCancel;
    Container cnColorNorth, cnColorCenter, cnColorSouth, cnColorMain;
    int R, G, B;

    Graphics g;

    private Color currentColor;

    JLabel lbMode = new JLabel("NONE", JLabel.CENTER);
    JLabel lbColor = new JLabel();
    JLabel lbOtherColor = new JLabel();
    JLabel trim = new JLabel();
    JLabel trim2 = new JLabel();
    String currentMode = new String("NONE");
    Coordinate start, current, end, med1, med2, med3, med4, dragging = new Coordinate();
    int resizeClicked;
    boolean dragmode;

    Figure selectedFigure;
    Figure previousFigure;
    myFreecurve tmpFreecurve = new myFreecurve();

    Font font = new Font("맑은 고딕", Font.BOLD, 12);

    public View() {
        Color[] colors = {Color.BLACK, Color.WHITE, Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE, Color.YELLOW, Color.GRAY, Color.CYAN, Color.MAGENTA};
        start = new Coordinate();
        end = new Coordinate();
        med1 = new Coordinate();
        med2 = new Coordinate();
        med3 = new Coordinate();
        med4 = new Coordinate();
        current = new Coordinate();

        //CONTAINERS LAYOUT
        ctnMain.setLayout(new BorderLayout());
        ctnNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
        ctnCenter.setLayout(new FlowLayout());
        ctnSouth.setLayout(new FlowLayout(FlowLayout.LEFT));

        //SIZE
        ctnNorth.setPreferredSize(new Dimension(1500, 60));
        ctnSouth.setPreferredSize(new Dimension(1500, 60));
        ctnCenter.setPreferredSize(new Dimension(1500, 700));
        btCircle.setPreferredSize(new Dimension(60, 50));
        btFreecurve.setPreferredSize(new Dimension(60, 50));
        btLine.setPreferredSize(new Dimension(60, 50));
        btRectangle.setPreferredSize(new Dimension(60, 50));
        btRhombus.setPreferredSize(new Dimension(60, 50));
        btTriangle.setPreferredSize(new Dimension(60, 50));
        btSelect.setPreferredSize(new Dimension(60, 50));
        btRotate.setPreferredSize(new Dimension(60, 50));
        btColor.setPreferredSize(new Dimension(60, 50));
        btRemoveColor.setPreferredSize(new Dimension(60, 50));
        btRemove.setPreferredSize(new Dimension(60, 50));
        btSave.setPreferredSize(new Dimension(60, 50));
        btOpen.setPreferredSize(new Dimension(60, 50));
        btUndo.setPreferredSize(new Dimension(60, 50));
        tfRotate.setPreferredSize(new Dimension(50, 42));
        pnMain.setPreferredSize(new Dimension(1500, 700));
        lbMode.setPreferredSize(new Dimension(100, 42));
        lbColor.setPreferredSize(new Dimension(40, 42));
        trim.setPreferredSize(new Dimension(500, 50));
        trim2.setPreferredSize(new Dimension(20, 50));
        tfRotate.setHorizontalAlignment(JTextField.CENTER);

        //ColorPOPUP settings
        tfR = new JTextField();
        tfG = new JTextField();
        tfB = new JTextField();
        btOk = new JButton("OK");
        btCancel = new JButton("Cancel");
        fmColor.setSize(300, 100);
        fmColor.setLocationRelativeTo(null);
        tfR.setPreferredSize(new Dimension(40, 20));
        tfG.setPreferredSize(new Dimension(40, 20));
        tfB.setPreferredSize(new Dimension(40, 20));
        lbR.setPreferredSize(new Dimension(40, 20));
        lbG.setPreferredSize(new Dimension(40, 20));
        lbB.setPreferredSize(new Dimension(40, 20));
        tfR.setDocument(new JTextFieldLimit(3));
        tfG.setDocument(new JTextFieldLimit(3));
        tfB.setDocument(new JTextFieldLimit(3));
        cnColorMain = fmColor.getContentPane();
        cnColorNorth = new Container();
        cnColorSouth = new Container();
        cnColorCenter = new Container();
        cnColorMain.add(cnColorCenter, BorderLayout.CENTER);
        cnColorMain.add(cnColorNorth, BorderLayout.NORTH);
        cnColorMain.add(cnColorSouth, BorderLayout.SOUTH);
        cnColorCenter.setLayout(new FlowLayout(FlowLayout.LEFT));
        cnColorSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
        cnColorCenter.add(lbR);
        cnColorCenter.add(tfR);
        cnColorCenter.add(lbG);
        cnColorCenter.add(tfG);
        cnColorCenter.add(lbB);
        cnColorCenter.add(tfB);
        cnColorSouth.add(btOk);
        cnColorSouth.add(btCancel);

/////////////////////////////////button image icon///////////////////////////////////////////////////////////
        lbOtherColor.setIcon(new ImageIcon("./icon/othercolor.png"));
        lbOtherColor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbOtherColor.setOpaque(true);
        lbOtherColor.setText("");

        btCircle.setIcon(new ImageIcon("./icon/circle.png"));
        btCircle.setBorderPainted(false);
        btCircle.setOpaque(true);
        btCircle.setContentAreaFilled(false);
        btCircle.setFocusPainted(false);
        btCircle.setForeground(new Color(238, 238, 238));

        btRectangle.setIcon(new ImageIcon("./icon/rect.png"));
        btRectangle.setBorderPainted(false);
        btRectangle.setOpaque(true);
        btRectangle.setContentAreaFilled(false);
        btRectangle.setFocusPainted(false);
        btRectangle.setForeground(new Color(238, 238, 238));

        btRhombus.setIcon(new ImageIcon("./icon/rhombus.png"));
        btRhombus.setBorderPainted(false);
        btRhombus.setOpaque(true);
        btRhombus.setContentAreaFilled(false);
        btRhombus.setFocusPainted(false);
        btRhombus.setForeground(new Color(238, 238, 238));

        btTriangle.setIcon(new ImageIcon("./icon/triangle.png"));
        btTriangle.setBorderPainted(false);
        btTriangle.setOpaque(true);
        btTriangle.setContentAreaFilled(false);
        btTriangle.setFocusPainted(false);
        btTriangle.setForeground(new Color(238, 238, 238));

        btRotate.setIcon(new ImageIcon("./icon/rotate.png"));
        btRotate.setBorderPainted(false);
        btRotate.setOpaque(true);
        btRotate.setContentAreaFilled(false);
        btRotate.setFocusPainted(false);
        btRotate.setForeground(new Color(238, 238, 238));

        btColor.setIcon(new ImageIcon("./icon/color.png"));
        btColor.setBorderPainted(false);
        btColor.setOpaque(true);
        btColor.setContentAreaFilled(false);
        btColor.setFocusPainted(false);
        btColor.setForeground(new Color(238, 238, 238));

        btRemoveColor.setIcon(new ImageIcon("./icon/RemoveColor.png"));
        btRemoveColor.setBorderPainted(false);
        btRemoveColor.setOpaque(true);
        btRemoveColor.setContentAreaFilled(false);
        btRemoveColor.setFocusPainted(false);
        btRemoveColor.setForeground(new Color(238, 238, 238));

        btRemove.setIcon(new ImageIcon("./icon/Remove.png"));
        btRemove.setBorderPainted(false);
        btRemove.setOpaque(true);
        btRemove.setContentAreaFilled(false);
        btRemove.setFocusPainted(false);
        btRemove.setForeground(new Color(238, 238, 238));

        btRotate.setIcon(new ImageIcon("./icon/rotate.png"));
        btRotate.setBorderPainted(false);
        btRotate.setOpaque(true);
        btRotate.setContentAreaFilled(false);
        btRotate.setFocusPainted(false);
        btRotate.setForeground(new Color(238, 238, 238));

        btLine.setIcon(new ImageIcon("./icon/Line.png"));
        btLine.setBorderPainted(false);
        btLine.setOpaque(true);
        btLine.setContentAreaFilled(false);
        btLine.setFocusPainted(false);
        btLine.setForeground(new Color(238, 238, 238));

        btFreecurve.setIcon(new ImageIcon("./icon/Freecurve.png"));
        btFreecurve.setBorderPainted(false);
        btFreecurve.setOpaque(true);
        btFreecurve.setContentAreaFilled(false);
        btFreecurve.setFocusPainted(false);
        btFreecurve.setForeground(new Color(238, 238, 238));

        btSelect.setIcon(new ImageIcon("./icon/Select.png"));
        btSelect.setBorderPainted(false);
        btSelect.setOpaque(true);
        btSelect.setContentAreaFilled(false);
        btSelect.setFocusPainted(false);
        btSelect.setForeground(new Color(238, 238, 238));

        btUndo.setIcon(new ImageIcon("./icon/Undo.png"));
        btUndo.setBorderPainted(false);
        btUndo.setOpaque(true);
        btUndo.setContentAreaFilled(false);
        btUndo.setFocusPainted(false);
        btUndo.setForeground(new Color(238, 238, 238));

        btSave.setIcon(new ImageIcon("./icon/Save.png"));
        btSave.setBorderPainted(false);
        btSave.setOpaque(true);
        btSave.setContentAreaFilled(false);
        btSave.setFocusPainted(false);
        btSave.setForeground(new Color(238, 238, 238));

        btOpen.setIcon(new ImageIcon("./icon/Open.png"));
        btOpen.setBorderPainted(false);
        btOpen.setOpaque(true);
        btOpen.setContentAreaFilled(false);
        btOpen.setFocusPainted(false);
        btOpen.setForeground(new Color(238, 238, 238));
/////////////////////////////////button image icon///////////////////////////////////////////////////////////         

        //BORDER
        lbMode.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnMain.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbMode.setFont(font);
        lbMode.setBackground(Color.WHITE);
        lbMode.setOpaque(true);

        //MAIN CONTAINER
        ctnMain.add(ctnNorth, BorderLayout.NORTH);
        ctnMain.add(ctnCenter, BorderLayout.CENTER);
        ctnMain.add(ctnSouth, BorderLayout.SOUTH);

        //Color Labels Initialize
        lbColor.setBackground(currentColor);
        lbColor.setOpaque(true);
        for (int i = 0; i < 10; i++) {
            lbColorsArray[i] = new JLabel();
            lbColorsArray[i].setPreferredSize(new Dimension(42, 42));
            lbColorsArray[i].setBackground(colors[i]);
            lbColorsArray[i].setOpaque(true);
            lbColorsArray[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            ctnSouth.add(lbColorsArray[i]);
        }
        ctnSouth.add(lbOtherColor);
        ctnSouth.add(trim2);
        ctnSouth.add(btSave);
        ctnSouth.add(btOpen);

        //NORTH CONTAINER
        ctnNorth.setBackground(Color.WHITE);
        ctnNorth.add(btCircle);
        ctnNorth.add(btTriangle);
        ctnNorth.add(btRectangle);
        ctnNorth.add(btRhombus);
        ctnNorth.add(btLine);
        ctnNorth.add(btFreecurve);
        ctnNorth.add(lbMode);
        ctnNorth.add(lbColor);
        ctnNorth.add(trim);
        ctnNorth.add(btSelect);
        ctnNorth.add(tfRotate);
        ctnNorth.add(btRotate);
        ctnNorth.add(btColor);
        ctnNorth.add(btRemoveColor);
        ctnNorth.add(btRemove);
        ctnNorth.add(btUndo);

        //CENTER CONTAINER
        ctnCenter.add(pnMain);
    }

    class MyPanel extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < currentStatus.length(); i++) {
                currentStatus.getFigure(i).paint(g);

            }
        }
    }

    //***********************getters & setters****************************    
    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Status getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(Status previousStatus) {
        this.previousStatus = previousStatus;
    }

    public Figure getSelectedFigure() {
        return selectedFigure;
    }

    public void setSelectedFigure(Figure selectedFigure) {
        this.selectedFigure = selectedFigure;
    }

    public Figure getPreviousFigure() {
        return previousFigure;
    }

    public void setPreviousFigure(Figure previousFigure) {
        this.previousFigure = previousFigure;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

}
