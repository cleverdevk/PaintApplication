/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintapplication;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author dlsqo
 */
public class ControllerClass {

    private View myView;

    public ControllerClass(View view) {
        this.myView = view;

        //DEFAULT SETTINGS
        myView.setVisible(true);
        myView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        myView.setTitle("Paint Application");
        myView.setSize(1500, 820);
        myView.setLocationRelativeTo(null);
        myView.setResizable(false);
        myView.pnMain.setBackground(Color.white);
        myView.g = myView.getGraphics();
        myView.setCurrentColor(new Color(0, 0, 0));

        //EVENT LISTENER
        myView.btCircle.addMouseListener(new ChangingModeMouseListener());
        myView.btRectangle.addMouseListener(new ChangingModeMouseListener());
        myView.btRhombus.addMouseListener(new ChangingModeMouseListener());
        myView.btTriangle.addMouseListener(new ChangingModeMouseListener());
        myView.btFreecurve.addMouseListener(new ChangingModeMouseListener());
        myView.btLine.addMouseListener(new ChangingModeMouseListener());
        myView.btSelect.addMouseListener(new ChangingModeMouseListener());
        myView.btColor.addMouseListener(new ColorMouseListener());
        myView.btRemoveColor.addMouseListener(new ColorMouseListener());
        myView.btRemove.addMouseListener(new RemoveMousListener());
        myView.pnMain.addMouseListener(new PanelMouseListener());
        myView.pnMain.addMouseMotionListener(new PanelMouseMotionListener());
        myView.btRotate.addMouseListener(new RotateMousListener());
        myView.btSave.addMouseListener(new SaveOpenMousListener());
        myView.btOpen.addMouseListener(new SaveOpenMousListener());
        myView.btUndo.addMouseListener(new UndoMouseListener());
        myView.lbOtherColor.addMouseListener(new ChangingColorByRGBPopupMouseListener());
        myView.btOk.addMouseListener(new ChangingColorByRGBMouseListener());
        myView.btCancel.addMouseListener(new ChangingColorByRGBMouseListener());
        myView.fdRead = new FileDialog(myView, "Choose File", FileDialog.LOAD);
        myView.fdWrite = new FileDialog(myView, "Save File", FileDialog.SAVE);
        for (int i = 0; i < 10; i++) {
            myView.lbColorsArray[i].addMouseListener(new ChangingColorMouseListener());
        }

    }

    public class ChangingModeMouseListener implements MouseListener {

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            JButton btClicked = (JButton) e.getSource();
            myView.currentMode = btClicked.getText();
            myView.lbMode.setText(myView.currentMode);
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    public class ChangingColorMouseListener implements MouseListener {

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            JLabel lbClicked = (JLabel) e.getSource();
            myView.setCurrentColor(lbClicked.getBackground());
            myView.lbColor.setBackground(myView.getCurrentColor());
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    public class ChangingColorByRGBPopupMouseListener implements MouseListener {

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            myView.tfR.setText(Integer.toString(myView.R));
            myView.tfG.setText(Integer.toString(myView.G));
            myView.tfB.setText(Integer.toString(myView.B));
            myView.lbColor.setBackground(new Color(myView.R, myView.G, myView.B));
            myView.fmColor.setVisible(true);
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    public class ChangingColorByRGBMouseListener implements MouseListener {

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            JButton b = (JButton) e.getSource();

            if (b.getText().equals("OK")) {
                try {
                    myView.R = Integer.parseInt(myView.tfR.getText());
                    myView.G = Integer.parseInt(myView.tfG.getText());
                    myView.B = Integer.parseInt(myView.tfB.getText());
                    if (myView.R > 255 || myView.G > 255 || myView.B > 255) {
                        JOptionPane.showMessageDialog(null, "Enter an integer(0~255)");
                    }
                    myView.lbColor.setBackground(new Color(myView.R, myView.G, myView.B));
                    myView.setCurrentColor(new Color(myView.R, myView.G, myView.B));
                    myView.g.setColor(myView.getCurrentColor());
                    myView.fmColor.setVisible(false);
                } catch (NumberFormatException E) {
                    JOptionPane.showMessageDialog(null, "Enter an integer(0~255)");
                }

            } else {
                myView.fmColor.setVisible(false);
            }
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    public class RemoveMousListener implements MouseListener {

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            if (myView.currentMode.equals("Select")) {
                for (int i = 0; i < myView.getCurrentStatus().length(); i++) {
                    myView.setPreviousStatus(new Status(myView.getCurrentStatus()));
                    if (myView.getCurrentStatus().getFigure(i).isSelected()) {
                        myView.getCurrentStatus().delFigure(i);
                    }
                }
                myView.pnMain.repaint();
            }
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    public class RotateMousListener implements MouseListener {

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            if (myView.currentMode.equals("Select")) {
                myView.previousFigure = myView.selectedFigure.getCloneFigure(myView.selectedFigure);
                myView.selectedFigure.rotate(Float.parseFloat(myView.tfRotate.getText()));
                myView.selectedFigure.setRotationhistory(myView.selectedFigure.getRotationhistory() + Float.parseFloat(myView.tfRotate.getText()));
                myView.pnMain.repaint();
            }
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    public class SaveOpenMousListener implements MouseListener {

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            String str = new String();
            if (b.getText().equals("Save")) {
                try {
                    myView.fdWrite.setVisible(true);
                    str = myView.fdWrite.getDirectory() + myView.fdWrite.getFile();
                    myView.getCurrentStatus().savePaint(str);
                    System.out.println(str);
                } catch (IOException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    myView.fdRead.setVisible(true);
                    str = myView.fdRead.getDirectory() + myView.fdRead.getFile();
                    myView.getCurrentStatus().readPaint(str);
                    myView.setPreviousStatus(myView.getCurrentStatus());
                    System.out.println(str);
                    myView.pnMain.repaint();
                } catch (IOException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    public class ColorMouseListener implements MouseListener {

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            if (myView.currentMode.equals("Select")) {
                if (b.getText().equals("Color")) {
                    myView.previousFigure = myView.selectedFigure.getCloneFigure(myView.selectedFigure);
                    myView.selectedFigure.color(true, myView.getCurrentColor(), myView.g);
                    myView.pnMain.repaint();
                } else {
                    myView.previousFigure = myView.selectedFigure.getCloneFigure(myView.selectedFigure);
                    myView.selectedFigure.color(false, myView.getCurrentColor(), myView.g);
                    myView.pnMain.repaint();
                }
            }
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    public class UndoMouseListener implements MouseListener {

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            if (b.getText().equals("Undo")) {
                myView.setCurrentStatus(myView.getPreviousStatus());
                if (myView.selectedFigure != null && myView.previousFigure != null) {
                    myView.selectedFigure.setAttribute(myView.previousFigure);
                }
                myView.pnMain.repaint();
            }
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    public class PanelMouseListener implements MouseListener {

        public void mousePressed(MouseEvent e) {
            myView.tmpFreecurve = new myFreecurve();
            myView.setPreviousStatus(new Status(myView.getCurrentStatus()));
            if (myView.selectedFigure != null) {
                myView.setPreviousFigure(myView.selectedFigure.getCloneFigure(myView.selectedFigure));
            }
            myView.getCurrentStatus().addFigure(myView.tmpFreecurve);
            myView.start.x = e.getX();
            myView.start.y = e.getY();
            myView.dragging.x = e.getX();
            myView.dragging.y = e.getY();

            if (myView.selectedFigure != null) {
                if (myView.selectedFigure.aboutCorner(new Coordinate(e.getX(), e.getY())) != null) {
                    myView.dragmode = true;
                } else {
                    myView.dragmode = false;
                }
            }
            if (myView.currentMode.equals("Select")) {
                for (int i = 0; i < myView.getCurrentStatus().length(); i++) {
                    if (myView.getCurrentStatus().getFigure(i).isInBound(new Coordinate(e.getX(), e.getY()))) {
                        for (int j = 0; j < myView.getCurrentStatus().length(); j++) {
                            myView.getCurrentStatus().getFigure(j).setSelected(false);
                        }
                        myView.selectedFigure = myView.getCurrentStatus().getFigure(i);
                        myView.selectedFigure.setSelected(true);
                        myView.previousFigure = myView.selectedFigure.getCloneFigure(myView.selectedFigure);
                        myView.setCurrentColor(myView.selectedFigure.getColor());
                        myView.lbColor.setBackground(myView.getCurrentColor());
                        break;
                    } else {
                        myView.selectedFigure = null;
                        for (int j = 0; j < myView.getCurrentStatus().length(); j++) {
                            myView.getCurrentStatus().getFigure(j).setSelected(false);
                        }
                    }
                }
                myView.pnMain.repaint();
            }

            System.out.println("pressed working" + myView.start.x + ", " + myView.start.y);
        }

        public void mouseReleased(MouseEvent e) {
            myView.end.x = e.getX();
            myView.end.y = e.getY();

            if (Math.abs(myView.start.x - myView.end.x) > 10 && Math.abs(myView.start.y - myView.end.y) > 10) {
                switch (myView.currentMode) {
                    case "Rectangle":
                        myView.med1.x = myView.end.x;
                        myView.med1.y = myView.start.y;
                        myView.med2.x = myView.start.x;
                        myView.med2.y = myView.end.y;
                        myRectangle tmpRectangle = new myRectangle(new Coordinate(myView.start), new Coordinate(myView.med1), new Coordinate(myView.end), new Coordinate(myView.med2));
                        tmpRectangle.setColor(myView.getCurrentColor());
                        myView.getCurrentStatus().addFigure(tmpRectangle);
                        myView.pnMain.repaint();
                        break;
                    case "Triangle":
                        myView.med1.x = (myView.start.x + myView.end.x) / 2;
                        myView.med1.y = myView.start.y;
                        myView.med2.x = myView.start.x;
                        myView.med2.y = myView.end.y;
                        myTriangle tmpTriangle = new myTriangle(new Coordinate(myView.med1), new Coordinate(myView.end), new Coordinate(myView.med2));
                        tmpTriangle.setColor(myView.getCurrentColor());
                        myView.getCurrentStatus().addFigure(tmpTriangle);
                        myView.pnMain.repaint();
                        break;
                    case "Rhombus":
                        myView.med1.x = (myView.start.x + myView.end.x) / 2;
                        myView.med1.y = myView.start.y;
                        myView.med2.x = myView.end.x;
                        myView.med2.y = (myView.start.y + myView.end.y) / 2;
                        myView.med3.x = (myView.start.x + myView.end.x) / 2;
                        myView.med3.y = myView.end.y;
                        myView.med4.x = myView.start.x;
                        myView.med4.y = (myView.start.y + myView.end.y) / 2;
                        myRhombus tmpRhombus = new myRhombus(new Coordinate(myView.med1), new Coordinate(myView.med2), new Coordinate(myView.med3), new Coordinate(myView.med4));
                        tmpRhombus.setColor(myView.getCurrentColor());
                        myView.getCurrentStatus().addFigure(tmpRhombus);
                        myView.pnMain.repaint();
                        break;
                    case "Circle":
                        myCircle tmpCircle;
                        int r1 = Math.abs(myView.start.x - myView.end.x);
                        int r2 = Math.abs(myView.start.y - myView.end.y);
                        
                        if (r1 > r2) {
                            tmpCircle = new myCircle(new Coordinate(myView.start), r1);
                            tmpCircle.setColor(myView.getCurrentColor());
                        } else {
                            tmpCircle = new myCircle(new Coordinate(myView.start), r1);
                            tmpCircle.setColor(myView.getCurrentColor());
                        }
                        myView.getCurrentStatus().addFigure(tmpCircle);
                        myView.pnMain.repaint();
                        break;
                    case "Line":
                        myLine tmpLine = new myLine(new Coordinate(myView.start), new Coordinate(myView.end));
                        tmpLine.setColor(myView.getCurrentColor());
                        myView.getCurrentStatus().addFigure(tmpLine);
                        myView.pnMain.repaint();
                        break;
                    case "Free Curve":
                        myView.pnMain.repaint();
                        myView.tmpFreecurve.updateFreecurve();
                        myView.pnMain.repaint();
                }
                System.out.println("released working" + myView.end.x + ", " + myView.end.y);
            }

        }

        public void mouseClicked(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {
            JPanel pn = (JPanel) e.getSource();

        }

        public void mouseExited(MouseEvent e) {
            // Change cursor
            JPanel pn = (JPanel) e.getSource();
            pn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public class PanelMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent me) {

            if (myView.currentMode.equals("Select") && myView.dragmode && myView.selectedFigure != null) {
                myView.selectedFigure.scale(me.getX(), me.getY(), myView.resizeClicked);

                myView.pnMain.repaint();
            }
            if (myView.currentMode.equals("Select") && !myView.dragmode && myView.selectedFigure != null) {
                System.out.println("translate Working");
                myView.selectedFigure.translate(me.getX() - myView.dragging.x, me.getY() - myView.dragging.y);
                myView.dragging.x = me.getX();
                myView.dragging.y = me.getY();
                myView.pnMain.repaint();
            }
            if (myView.currentMode.equals("Free Curve")) {
                myView.tmpFreecurve.setColor(myView.getCurrentColor());
                myView.tmpFreecurve.addLine(new myLine(new Coordinate(me.getX() - 1, me.getY() - 1), new Coordinate(me.getX(), me.getY())));
                myView.pnMain.repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent me) {
            myView.current.x = me.getX();
            myView.current.y = me.getY();
            //lbColor.setText(current.x + ", " + current.y);
            // Change Cursor
            if (myView.currentMode.equals("Select")) {
                myView.pnMain.setCursor(new Cursor(Cursor.HAND_CURSOR));
                if (myView.selectedFigure != null && myView.selectedFigure.aboutCorner(myView.current) != null) {
                    Coordinate clicked = myView.selectedFigure.aboutCorner(myView.current);
                    int[] arrx = new int[4];
                    int[] arry = new int[4];
                    for (int i = 0; i < myView.selectedFigure.getPg().xpoints.length; i++) {
                        arrx[i] = myView.selectedFigure.getPg().xpoints[i];
                        arry[i] = myView.selectedFigure.getPg().ypoints[i];
                    }
                    for (int i = 0; i < myView.selectedFigure.getPg().xpoints.length; i++) {
                        if (arrx[i] == clicked.x && arry[i] == clicked.y) {
                            myView.resizeClicked = i;
                        }
                    }
                    if (myView.selectedFigure instanceof myRectangle) {
                        if (myView.resizeClicked == 0 || myView.resizeClicked == 2) {
                            myView.pnMain.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                        }
                        if (myView.resizeClicked == 1 || myView.resizeClicked == 3) {
                            myView.pnMain.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
                        }
                    } else if (myView.selectedFigure instanceof myTriangle) {
                        if (myView.resizeClicked == 0) {
                            myView.pnMain.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                        }
                        if (myView.resizeClicked == 1) {
                            myView.pnMain.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                        }
                        if (myView.resizeClicked == 2) {
                            myView.pnMain.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
                        }
                    } else if (myView.selectedFigure instanceof myRhombus) {
                        if (myView.resizeClicked == 0 || myView.resizeClicked == 2) {
                            myView.pnMain.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                        }
                        if (myView.resizeClicked == 1 || myView.resizeClicked == 3) {
                            myView.pnMain.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                        }
                    }
                } else {
                    myView.pnMain.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            } else if (!myView.currentMode.equals("NONE")) {
                myView.pnMain.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        View v = new View();
        ControllerClass c = new ControllerClass(v);
    }
}
