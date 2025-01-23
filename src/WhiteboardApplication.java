//// Previously used for starting single user whiteboard.

// Whiteboard attempt 1 - basic functionalities
//// Create a whiteboard and be able to draw on it.
//// Written by Michael Yixiao Wu | 1388097
//
//import javax.swing.*;
//import java.awt.*;
//import java.rmi.RemoteException;
//
//public class WhiteboardApplication {
//
//    public static void main(String[] args) {
//
//        System.out.print("Hello and welcome!\n");
//        SwingUtilities.invokeLater(WhiteboardApplication::createAndShowGUI);
//    }
//
//    private static void createAndShowGUI() {
//
//        // Create the main window
//        MainWindow mainWindow = null;
//        try {
//            mainWindow = new MainWindow();
//        } catch (RemoteException e) {
//            throw new RuntimeException(e);
//        }
//        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // set the size of the window
//        mainWindow.setSize(900, 600);
//
//        // set the window to be visible
//        mainWindow.setVisible(true);
//
//        //set the title of the window
//        mainWindow.setTitle("Shared Whiteboard");
//    }
//}