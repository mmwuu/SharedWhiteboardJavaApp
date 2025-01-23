// Create the colour panel UI
//// Written by Michael Yixiao Wu | 1388097

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class ColorPanel extends JPanel {

    private DrawingSpace drawingSpace;

    public ColorPanel(DrawingSpace drawingSpace) {
        this.drawingSpace = drawingSpace;
        this.setLayout(new GridLayout(2, 10));

        Color[] colors = {
                Color.BLACK, Color.WHITE, Color.RED, Color.BLUE, Color.GREEN,
                Color.YELLOW, Color.ORANGE, Color.CYAN, Color.MAGENTA, Color.PINK,
                new Color(158, 1, 66), // Custom colors
                new Color(213, 62, 79),
                new Color(244, 109, 67),
                new Color(253, 174, 97),
                new Color(254, 224, 139),
                new Color(230, 245, 152),
                new Color(171, 221, 164),
                new Color(102, 194, 165),
                new Color(50, 136, 189),
                new Color(94, 79, 162)
        };
        for (Color color : colors) {
            JButton button = new JButton();
            button.setBackground(color);
            button.setPreferredSize(new Dimension(30, 30));
            button.addActionListener(e -> {
                drawingSpace.setColor(color);
            });
            this.add(button);
        }


    }
}
