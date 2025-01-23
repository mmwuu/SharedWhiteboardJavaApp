//// Shared Whiteboard
//// Join a whiteboard and be able to draw on it.
//// Written by Michael Yixiao Wu | 1388097
import remote.IRemoteDrawingSpace;

import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class JoinWhiteBoard {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java JoinWhiteBoard <serverIPAddress> <serverPort> username");
            return;
        }

        String serverIPAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);
        String username = args[2];

        try {
            // Connect to the server
            Registry registry = LocateRegistry.getRegistry(serverIPAddress, serverPort);
            IRemoteDrawingSpace remoteDrawingSpace = (IRemoteDrawingSpace) registry.lookup("DrawingSpace");


            // Create a new whiteboard
            MainWindow mainWindow = new MainWindow(remoteDrawingSpace);
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainWindow.setSize(900, 600);
            mainWindow.setVisible(true);
            mainWindow.setTitle("Shared Whiteboard");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}