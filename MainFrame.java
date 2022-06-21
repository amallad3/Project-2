import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;  


/**
 * This is the main GUI class. It creates the frame, and adds the
 * panels + buttons on the screen.
 *
 * @author Darius Morar
 * @version 06.20.2022
 */

public class MainFrame extends JFrame {
	
    private OptionPanel optionPanel;
    private BoardPanel boardPanel;

    public MainFrame() {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
		this.setSize(800, 500);
		this.setTitle("Dots Project");

        this.boardPanel = new BoardPanel();
        this.add(boardPanel, BorderLayout.CENTER);

        this.optionPanel = new OptionPanel(this.boardPanel);
        this.add(optionPanel, BorderLayout.WEST);
    }
}
