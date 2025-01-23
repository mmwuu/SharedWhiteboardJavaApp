// Creates main window of the whiteboard
//// Written by Michael Yixiao Wu | 1388097

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.rmi.RemoteException;

import remote.ChatMessage;
import remote.IRemoteDrawingSpace;

public class MainWindow extends JFrame {
    private MenuBar menuBar;
    private ToolPanel toolPanel;
    private ColorPanel colorPanel;
    private DrawingSpace drawingSpace;
    private JPanel bottomPanel;
    private ChatPanel chatPanel;

    private IRemoteDrawingSpace remoteDrawingSpace;
    // private UserListPanel userListPanel;

    public MainWindow(IRemoteDrawingSpace remoteDrawingSpace) throws RemoteException {
        this.remoteDrawingSpace = remoteDrawingSpace;

        initialiseComponents();
        layoutComponents();
    }

    private void initialiseComponents() throws RemoteException {
        // Initialize menu bar
        menuBar = new MenuBar();

        drawingSpace = new DrawingSpace();

        // Initialise ToolPanel which includes tools and brush sizes
        toolPanel = new ToolPanel(drawingSpace);

        // Initialise the color palette
        colorPanel = new ColorPanel(drawingSpace);

        // Initialise bottom panel for tools and color palette
        bottomPanel = new JPanel();

        // add the tool panel and color palette to the bottom panel
        bottomPanel.add(colorPanel, BorderLayout.WEST);
        bottomPanel.add(toolPanel, BorderLayout.EAST);

        // Initialise chat panel
        chatPanel = new ChatPanel(remoteDrawingSpace);

        // userListPanel = new UserListPanel();

    }

    private void layoutComponents() {

        // Set layout
        setLayout(new BorderLayout());
        setJMenuBar(menuBar);

        // Add drawing area to the center
        add(drawingSpace, BorderLayout.CENTER);

        // Place the bottom panel containing tools and color palette at the bottom
        add(bottomPanel, BorderLayout.SOUTH);

        // Add chat panel to the right
        add(chatPanel, BorderLayout.EAST);

        // add(userListPanel, BorderLayout.WEST);
    }

//    @Override
//    public void sendMessage(String message) throws RemoteException {
//        chatPanel.showMessage(message);
//    }
}