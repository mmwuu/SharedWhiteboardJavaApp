// This class creates the tool panel in the UI.
//// Written by Michael Yixiao Wu | 1388097

import javax.swing.*;
import java.awt.*;

public class ToolPanel extends JPanel{

    private DrawingSpace drawingSpace;

    public ToolPanel(DrawingSpace drawingSpace) {
        this.drawingSpace = drawingSpace;
        setLayout(new GridLayout(2, 0));

        // Add tools
        String[] tools = {"Pen", "Eraser", "Line", "Rectangle", "Circle", "Oval", "Text"};
        for (String tool : tools) {
            JButton button = new JButton(tool);
            button.addActionListener(e -> {
                if ("Clear".equals(e.getActionCommand())) {
                    drawingSpace.clear();
                } else {
                    drawingSpace.setTool(e.getActionCommand());
                }
            });
            this.add(button);
        }

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> drawingSpace.clear());
        this.add(clearButton);

        // Add brush size buttons
        int[] brushSizes = {2, 4, 8, 16};
        for (int size : brushSizes) {
            JButton sizeButton = new JButton(size + "pt");
            sizeButton.addActionListener(e -> {
                drawingSpace.setStrokeSize(size);
            });
            this.add(sizeButton);
        }
    }
}
