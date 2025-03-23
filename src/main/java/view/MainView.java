package view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    public MainView() throws HeadlessException {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("My Swing App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setVisible(true);
        });
    }
}
