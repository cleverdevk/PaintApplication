/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintapplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author dlsqo
 */
public class Status implements Serializable {

    private ArrayList<Figure> figures = new ArrayList<>();
    private String path = new String();
    private FileOutputStream fos = null;
    private ObjectOutputStream oos = null;
    boolean statusChanged;

    public Status() {
        statusChanged = false;
    }

    public Status(Status s) {
        figures = (ArrayList<Figure>) s.getFigures().clone();
        statusChanged = s.isStatusChanged();
    }

    public Status(ArrayList<Figure> al) {
        figures = al;
        statusChanged = false;
    }

    public void savePaint(String savepath) throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            fos = new FileOutputStream(savepath);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(figures);
            oos.close();
            fos.close();
        } catch (NotSerializableException e) {
            e.printStackTrace();
        }
    }

    public void readPaint(String readpath) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(readpath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        figures = (ArrayList) ois.readObject();
    }

    public Figure getFigure(int idx) {
        return figures.get(idx);
    }

    public void addFigure(Figure fg) {
        figures.add(fg);
    }

    public void delFigure(int idx) {
        figures.remove(idx);
    }

    public void updateFigure(int idx) {

    }

    public int length() {
        return figures.size();
    }

    public ArrayList<Figure> getFigures() {
        return figures;
    }

    public void setFigures(ArrayList<Figure> figures) {
        this.figures = figures;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileOutputStream getFos() {
        return fos;
    }

    public void setFos(FileOutputStream fos) {
        this.fos = fos;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public boolean isStatusChanged() {
        return statusChanged;
    }

    public void setStatusChanged(boolean statusChanged) {
        this.statusChanged = statusChanged;
    }

}
