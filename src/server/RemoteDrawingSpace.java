// Tis file contains the implementation of the remote interface IRemoteDrawingSpace.
// It extends UnicastRemoteObject and implements the methods defined in the interface.
// It also contains a list of clients and a shared image that is updated when a client
// draws on the whiteboard.

package server;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;

import client.WhiteboardClient;
import remote.ChatMessage;
import remote.IRemoteDrawingSpace;
import remote.SerializableBufferedImage;

/**
 * Server side implementation of the remote interface.
 * Must extend UnicastRemoteObject, to allow the JVM to create a
 * remote proxy/stub.
 *
 */
public class RemoteDrawingSpace extends UnicastRemoteObject implements IRemoteDrawingSpace {
    private List<WhiteboardClient> clients = new ArrayList<>();
    private SerializableBufferedImage sharedImage;
    private Graphics2D g2d;
    private final CopyOnWriteArrayList<ChatMessage> chatClients = new CopyOnWriteArrayList<>();

    protected RemoteDrawingSpace(BufferedImage initialImage) throws RemoteException {
        super();
        this.sharedImage = new SerializableBufferedImage(initialImage);
//        this.g2d = initialImage.createGraphics();
//        this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    // Initialize shared image with a default size and type
    public RemoteDrawingSpace() throws RemoteException {
        this(new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB));
    }

    // Whiteboard
    private synchronized void updateSharedImage(SerializableBufferedImage image) {
        this.sharedImage = image;
//        this.g2d = sharedImage.getImage().createGraphics();
//        this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    public synchronized void updateImage(SerializableBufferedImage image) throws RemoteException {
        updateSharedImage(image);
        updateClients(image);
    }

    private void updateClients(SerializableBufferedImage updatedImage) throws RemoteException {
        for (WhiteboardClient client : clients) {
            client.updateImage(updatedImage);
        }
    }

    @Override
    public synchronized void registerClient(WhiteboardClient client) throws RemoteException {
        clients.add(client);
        if (clients.size() > 1){
            client.updateImage(sharedImage);
        }
    }

    // Chat
    @Override
    public void registerChatClient(ChatMessage client) throws RemoteException {
        chatClients.add(client);
        System.out.println("Registered a chat client.\n");

    }

    @Override
    public void broadcastMessage(String message) throws RemoteException {
        System.out.println("broadcasting message-----");

        for (ChatMessage client : chatClients) {
            client.showMessage(message);
        }
    }

    // Unused methods
    @Override
    public synchronized void drawLine(int x1, int y1, int x2, int y2) throws RemoteException {
        for (WhiteboardClient client : clients) {
            client.drawLine(x1, y1, x2, y2);
        }
        updateClients(sharedImage);
    }

    @Override
    public void drawRectangle(int x, int y, int width, int height) throws RemoteException {
        for (WhiteboardClient client : clients) {
            client.drawRectangle(x, y, width, height);
            updateClients(sharedImage);
        }
    }

    @Override
    public void drawCircle(int x, int y, int radius) throws RemoteException {
        for (WhiteboardClient client : clients) {
            client.drawCircle(x, y, radius);
        }
        updateClients(sharedImage);
    }

    @Override
    public void drawOval(int x, int y, int width, int height) throws RemoteException {
        for (WhiteboardClient client : clients) {
            client.drawOval(x, y, width, height);
        }
        updateClients(sharedImage);
    }

    @Override
    public void erase(int x1, int y1, int x2, int y2) throws RemoteException {
        for (WhiteboardClient client : clients) {
            client.erase(x1, y1, x2, y2);
        }
        updateClients(sharedImage);
    }

    @Override
    public void drawText(String text, int x, int y) throws RemoteException {
        for (WhiteboardClient client : clients) {
            client.drawText(text, x, y);
        }
        updateClients(sharedImage);
    }



    @Override
    public SerializableBufferedImage getSharedImage() throws RemoteException {
        return sharedImage;
    }



    @Override
    public boolean hasSharedImage() throws RemoteException {
        return sharedImage != null;
    }

}
