package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;  

import common.DrawableGroup;
import controller.ClassifierController;

/**
 * This is the main GUI class. It creates the frame, and adds the
 * panels + buttons on the screen.
 *
 * @author Darius Morar
 * @version 06.19.2022
 */

public class MainFrame extends JFrame implements ClassifierView {
	
    private OptionPanel optionPanel;
    private BoardPanel boardPanel;

    public MainFrame() {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
		this.setSize(800, 500);
		this.setTitle("Dots Project");

        this.optionPanel = new OptionPanel();
        this.add(optionPanel, BorderLayout.WEST);

        this.boardPanel = new BoardPanel();
        this.add(boardPanel, BorderLayout.CENTER);

    }

    @Override
    public void addListners(ClassifierController controller) {
        this.optionPanel.addListener(controller);
        this.boardPanel.addListener(controller);
    }

    @Override
    public void render(DrawableGroup group) {
        this.boardPanel.render(group);
    }
}
