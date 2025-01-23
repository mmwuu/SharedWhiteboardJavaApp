// This file contains the implementation of the remote interface IRemoteDrawingSpace.
// It extends Remote and contains methods that can be called remotely by clients.


package remote;
import client.WhiteboardClient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.awt.image.BufferedImage;

/**
 * RMI Remote interface - must be shared between client and server.
 * All methods must throw RemoteException.
 * All parameters and return types must be either primitives or Serializable.
 *
 * Any object that is a remote object must implement this interface.
 * Only those methods specified in a "remote interface" are available remotely.
 */
public interface IRemoteDrawingSpace extends Remote {

    // Whiteboard
    void updateImage(SerializableBufferedImage image) throws RemoteException;
    void registerClient(WhiteboardClient client) throws RemoteException;

    void broadcastMessage(String message) throws RemoteException;
    // Chat
    void registerChatClient(ChatMessage client) throws RemoteException;

    // Unused methods
    void drawLine(int x1, int y1, int x2, int y2) throws RemoteException;
    void drawRectangle(int x, int y, int width, int height) throws RemoteException;
    void drawCircle(int x, int y, int radius) throws RemoteException;
    void drawOval(int x, int y, int width, int height) throws RemoteException;
    void erase(int x1, int y1, int x2, int y2) throws RemoteException;
    // void setStrokeSize(int size) throws RemoteException;
    void drawText(String text, int x, int y) throws RemoteException;
    SerializableBufferedImage getSharedImage() throws RemoteException;
    boolean hasSharedImage() throws RemoteException;

    // void setColor(Color color) throws RemoteException;


}
