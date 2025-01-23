// Chat panel UI and functionality
//// Written by Michael Yixiao Wu | 1388097

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import remote.ChatMessage;
import remote.IRemoteDrawingSpace;
import server.RemoteDrawingSpace;

public class ChatPanel extends JPanel implements ChatMessage {
    private JTextArea messagesArea;
    private IRemoteDrawingSpace remoteDrawingSpace;

    public ChatPanel(IRemoteDrawingSpace remoteDrawingSpace) {
        this.remoteDrawingSpace = remoteDrawingSpace;
        try {
            remoteDrawingSpace.registerChatClient(this);
            System.out.println("Chat client registered: chatpanel");

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 300));

        messagesArea = new JTextArea();
        messagesArea.setEditable(false);
        messagesArea.setLineWrap(true);
        messagesArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(messagesArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JTextField inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(0, 30));

        ActionListener send = e -> {
            sendMessage(inputField.getText());
            inputField.setText("");
        };

        // Allows user to hit enter key to send.
        inputField.addActionListener(send);

        add(scrollPane, BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);
    }

    @Override
    public void showMessage(String message) {
        if (messagesArea == null) {
            System.out.println("messagesArea is null when showMessage is called");
        } else {
            messagesArea.append(message + "\n");
        }    }

    @Override
    public void sendMessage(String message) {
        if (!message.isEmpty()) {
            try {
                remoteDrawingSpace.broadcastMessage(message);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
//            showMessage("You: " + message);

        }
    }

}



