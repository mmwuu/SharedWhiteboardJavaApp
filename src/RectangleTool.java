// Rectangle tool
//// Written by Michael Yixiao Wu | 1388097

import remote.IRemoteDrawingSpace;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;
import java.rmi.RemoteException;

public class RectangleTool extends MouseAdapter {
    private int startX, startY;
    private boolean isDrawing;
    private DrawingSpace drawingSpace;
    private IRemoteDrawingSpace remoteDrawingSpace;
    private Graphics2D g2d;

    public RectangleTool(DrawingSpace drawingSpace, Graphics2D g2d, IRemoteDrawingSpace remoteDrawingSpace){
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
            int width = Math.abs(startX - e.getX());
            int height = Math.abs(startY - e.getY());
            int x = Math.min(startX, e.getX());
            int y = Math.min(startY, e.getY());
            try {
                drawingSpace.drawRectangle(x, y, width, height);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            isDrawing = false;
        }


    }


}
