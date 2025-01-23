// Text tool
//// Written by Michael Yixiao Wu | 1388097

import remote.IRemoteDrawingSpace;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
public class TextTool extends MouseAdapter {
    private DrawingSpace drawingSpace;
    private IRemoteDrawingSpace remoteDrawingSpace;
    private Graphics2D g2d;

    public TextTool(DrawingSpace drawingSpace, Graphics2D g2d, IRemoteDrawingSpace remoteDrawingSpace) {
        this.drawingSpace = drawingSpace;
        this.remoteDrawingSpace = remoteDrawingSpace;
        this.g2d = g2d;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        String text = JOptionPane.showInputDialog("Enter Text: ");
        if (text != null && !text.isEmpty()) {
            try {
                drawingSpace.drawText(text, e.getX(), e.getY());
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
