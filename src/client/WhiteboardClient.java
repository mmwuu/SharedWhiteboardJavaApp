// The whiteboard client interface is used to draw shapes on the whiteboard, erase shapes, and update the image on the
// whiteboard. The client interface extends the Remote interface and contains methods that can be called remotely by
// the server. The methods are used to draw shapes on the whiteboard, erase shapes, and update the image on the
// whiteboard.
//
// The client interface is implemented by the whiteboard client class, which is used by the server to
// communicate with the clients. The client interface is used to draw shapes on the whiteboard, erase shapes, and
// update the image on the whiteboard.
//
// The client interface is implemented by the whiteboard client class, which is
// used by the server to communicate with the clients. The client interface is used to draw shapes on the whiteboard,
// erase shapes, and update the image on the whiteboard. T
// he client interface is implemented by the whiteboard client
// class, which is used by the server to communicate with the clients. The client interface is used to draw shapes on
// the whiteboard, erase shapes, and update the image on the whiteboard. The client interface is implemented by the
// whiteboard client class, which is used by the server to communicate with the clients. The client interface is used
// to draw shapes on the whiteboard, erase shapes, and update the image on the whiteboard. The client interface is
// implemented by the whiteboard client class, which is used by the server to communicate with the clients. The client
// interface is used to draw shapes on the whiteboard, erase shapes, and update the image on the whiteboard. The client
// interface is implemented by the whiteboard client class, which is used by the server to communicate with the clients.
// The client interface is used to draw shapes on the whiteboard, erase shapes, and update the image on the whiteboard.
// The client interface is implemented by the whiteboard client class, which is used by the server to communicate with
// the clients. The client interface is used to draw shapes on the whiteboard, erase shapes, and update the image on the
// whiteboard. The client interface is implemented by the whiteboard client class, which is used by the server to
// communicate with the clients. The client interface is used to draw shapes on the whiteboard, erase shapes, and update
// the image on the whiteboard. The client interface is implemented by the whiteboard client class, which is used by the
// server to communicate with the clients. The client interface is used to draw shapes on the whiteboard, erase shapes, and
// update
package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.awt.image.BufferedImage;
import remote.SerializableBufferedImage;

public interface WhiteboardClient extends Remote {
    void drawLine(int x1, int y1, int x2, int y2) throws RemoteException;
    void drawRectangle(int x, int y, int width, int height) throws RemoteException;
    void drawCircle(int x, int y, int radius) throws RemoteException;
    void drawOval(int x, int y, int width, int height) throws RemoteException;
    void erase(int x1, int y1, int x2, int y2) throws RemoteException;
    void drawText(String text, int x, int y) throws RemoteException;
    void updateImage(SerializableBufferedImage image) throws RemoteException;

}
