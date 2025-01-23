// line tool
//// Written by Michael Yixiao Wu | 1388097

import remote.IRemoteDrawingSpace;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class LineTool extends MouseAdapter {

    private int startX, startY;
    private boolean isDrawing;
    private DrawingSpace drawingSpace;
    private IRemoteDrawingSpace remoteDrawingSpace;
    private Graphics2D g2d;

    public LineTool(DrawingSpace drawingSpace, Graphics2D g2d, IRemoteDrawingSpace remoteDrawingSpace){
        this.drawingSpace = drawingSpace;
        this.remoteDrawingSpace = remoteDrawingSpace;
        this.g2d = g2d;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!isDrawing) {
            startX = e.getX();
            startY = e.getY();
            isDrawing = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (startX == e.getX() && startY == e.getY()) {
            isDrawing = false;
        }
        if (isDrawing) {
            try {
                drawingSpace.drawLine(startX, startY, e.getX(), e.getY());
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            isDrawing = false;
            drawingSpace.sendImageUpdate();

        }
    }
}
