//
// Main drawing canvas and logic for drawing
//// Written by Michael Yixiao Wu | 1388097


import client.WhiteboardClient;
import remote.IRemoteDrawingSpace;
import remote.SerializableBufferedImage;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class DrawingSpace extends JPanel implements WhiteboardClient {

    private BufferedImage image;
    private Graphics2D g2d;
    private IRemoteDrawingSpace remoteDrawingSpace;
    private int strokeSize = 2;
    private Color currentColor = Color.black;






    public DrawingSpace() {

        // Initialise the drawing space.
        setPreferredSize(new Dimension(800, 600));
        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRect(0, 0, 800, 600);
        g2d.setPaint(Color.white);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        repaint();
        setTool("Pen");
        setStrokeSize(2);
        setColor(Color.black);
        setDoubleBuffered(false);

        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            //Retrieve the stub/proxy for the remote math object from the registry
            remoteDrawingSpace = (IRemoteDrawingSpace) registry.lookup("DrawingSpace");
            UnicastRemoteObject.exportObject(this, 0);
            remoteDrawingSpace.registerClient(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // update the server with the initial image
        sendImageUpdate();
    }



    public void clear() {
        g2d.setPaint(Color.white);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        repaint();
        sendImageUpdate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public void setTool(String tool) {
        MouseAdapter mouseAdapter = switch (tool) {
            case "Pen" -> new PenTool(this, g2d, remoteDrawingSpace);
            case "Eraser" -> new EraserTool(this, g2d, remoteDrawingSpace);
            case "Line" -> new LineTool(this, g2d, remoteDrawingSpace);
            case "Rectangle" -> new RectangleTool(this, g2d, remoteDrawingSpace);
            case "Circle" -> new CircleTool(this, g2d, remoteDrawingSpace);
            case "Oval" -> new OvalTool(this, g2d, remoteDrawingSpace);
            case "Text" -> new TextTool(this, g2d, remoteDrawingSpace);
            default -> new PenTool(this, g2d, remoteDrawingSpace);
        };
        removeListeners();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    private void removeListeners() {
        for (MouseListener listener : getMouseListeners()) {
            removeMouseListener(listener);
        }
        for (MouseMotionListener listener : getMouseMotionListeners()) {
            removeMouseMotionListener(listener);
        }
    }

    @Override
    public synchronized void drawLine(int x1, int y1, int x2, int y2) throws RemoteException {
        g2d.setPaint(currentColor);
        g2d.drawLine(x1, y1, x2, y2);
        repaint();
        // sendImageUpdate();
        // send image update moved to the tool classes to avoid sending image updates for every point drawn.
    }

    public synchronized void drawRectangle(int x, int y, int width, int height) throws RemoteException {
        g2d.setPaint(currentColor);
        g2d.fillRect(x, y, width, height);
        repaint();
        sendImageUpdate();
    }

    public synchronized void drawCircle(int x, int y, int radius) throws RemoteException {
        g2d.setPaint(currentColor);
        g2d.fillOval(x, y, radius, radius);
        repaint();
        sendImageUpdate();
    }

    public synchronized void drawOval(int x, int y, int width, int height) throws RemoteException {
        g2d.setPaint(currentColor);
        g2d.fillOval(x, y, width, height);
        repaint();
        sendImageUpdate();
    }

    public synchronized void erase(int x1, int y1, int x2, int y2) throws RemoteException {
        g2d.setPaint(Color.white);
        g2d.drawLine(x1, y1, x2, y2);
        repaint();
//        sendImageUpdate();
    }

    public synchronized void drawText(String text, int x, int y) throws RemoteException{
        g2d.setPaint(currentColor);
        g2d.drawString(text, x, y);
        repaint();
        sendImageUpdate();
    }

    public void setStrokeSize(int size) {
        this.strokeSize = size;
        g2d.setStroke(new BasicStroke(strokeSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public void setColor(Color color){
        currentColor = color;
        g2d.setPaint(currentColor);
    }

//    @Override
//    public void updateImage(SerializableBufferedImage newImage) throws RemoteException {
//        g2d.drawImage(newImage.getImage(), 0, 0, null);
//        repaint();
//    }
//
//
//    public void sendImageUpdate() {
//        try {
//            SerializableBufferedImage serializableImage = new SerializableBufferedImage(image);
//            remoteDrawingSpace.updateImage(serializableImage);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void updateImage(SerializableBufferedImage newImage) throws RemoteException {
        this.image = newImage.getImage();
        g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Restore settings
        setStrokeSize(this.strokeSize);
        setColor(this.currentColor);

        repaint();
    }

    public void sendImageUpdate() {
        try {
            SerializableBufferedImage serializableImage = new SerializableBufferedImage(image);
            remoteDrawingSpace.updateImage(serializableImage);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}

