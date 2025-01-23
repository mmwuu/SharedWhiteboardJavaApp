//// Shared Whiteboard
//// Create a whiteboard and be able to draw on it.
//// Written by Michael Yixiao Wu | 1388097

import server.RemoteDrawingSpace;

import javax.swing.*;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CreateWhiteBoard {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("The command is: java CreateWhiteBoard <serverIPAddress> <serverPort> username");
            return;
        }

        String serverIPAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);
        String username = args[2];

        try {
            // Start the server
            Registry registry = LocateRegistry.createRegistry(serverPort);
            RemoteDrawingSpace remoteDrawingSpace = new RemoteDrawingSpace();
            registry.bind("DrawingSpace", remoteDrawingSpace);
            System.out.println("Drawing space ready");

            // Create the first whiteboard
            MainWindow mainWindow = new MainWindow(remoteDrawingSpace);
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainWindow.setSize(900, 600);
            mainWindow.setVisible(true);
            mainWindow.setTitle("Shared Whiteboard");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}