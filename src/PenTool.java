// Pen tool
//// Written by Michael Yixiao Wu | 1388097

import remote.IRemoteDrawingSpace;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

class PenTool extends MouseAdapter {
    private int x, y;
    private boolean isDrawing;
    private DrawingSpace drawingSpace;
    private IRemoteDrawingSpace remoteDrawingSpace;

    public PenTool(DrawingSpace drawingSpace, Graphics2D g2d, IRemoteDrawingSpace remoteDrawingSpace){
        this.drawingSpace = drawingSpace;
        this.remoteDrawingSpace = remoteDrawingSpace;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        isDrawing = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isDrawing) {
            int newX = e.getX();
            int newY = e.getY();
            try {
                drawingSpace.drawLine(x, y, newX, newY);
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
            x = newX;
            y = newY;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isDrawing = false;
        drawingSpace.sendImageUpdate();
    }
}
