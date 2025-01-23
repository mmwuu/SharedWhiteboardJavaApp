// pLaceholder menu bar
//// Written by Michael Yixiao Wu | 1388097
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar implements ActionListener {
    private JMenu fileMenu;
    private JMenu editMenu;


    public MenuBar() {
        createMenus();
        addMenus();
    }

    private void createMenus() {

        // Create file menu
        fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        fileMenu.addSeparator();

        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        // Create edit menu
        editMenu = new JMenu("Edit");
        JMenuItem undoItem = new JMenuItem("Undo");
        JMenuItem redoItem = new JMenuItem("Redo");

        editMenu.add(undoItem);
        editMenu.add(redoItem);

        undoItem.addActionListener(this);
        redoItem.addActionListener(this);
    }

    private void addMenus() {
        add(fileMenu);
        add(editMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New":
                System.out.println("New pressed");
                //
                break;
            case "Open":
                System.out.println("Open pressed");
                break;
            case "Save":
                System.out.println("Save pressed");
                break;
            case "Exit":
                System.out.println("Exit pressed");
                break;
            case "Undo":
                System.out.println("Undo pressed");
                break;
            case "Redo":
                System.out.println("Redo pressed");
                break;
        }
    }


}
