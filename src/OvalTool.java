// Oval tool
//// Written by Michael Yixiao Wu | 1388097

import remote.IRemoteDrawingSpace;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;


public class OvalTool extends MouseAdapter {
    private int startX, startY;
    private DrawingSpace drawingSpace;
    private IRemoteDrawingSpace remoteDrawingSpace;
    private Graphics2D g2d;

    public OvalTool(DrawingSpace drawingSpace, Graphics2D g2d, IRemoteDrawingSpace remoteDrawingSpace){
        this.drawingSpace = drawingSpace;
        this.remoteDrawingSpace = remoteDrawingSpace;
        this.g2d = g2d;
    }

    @Override
    public void mousePressed(MouseEvent e){
        startX = e.getX();
        startY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int endX = e.getX();
        int endY = e.getY();
        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);
        try {
            drawingSpace.drawOval(Math.min(startX, endX), Math.min(startY, endY), width, height);
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }
}
