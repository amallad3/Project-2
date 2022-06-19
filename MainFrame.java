import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * This is the main GUI class. It creates the frame, and adds the
 * panels + buttons on the screen.
 *
 * @author Darius Morar
 * @version 06.18.2022
 */

public class MainFrame extends JFrame implements ActionListener, MouseListener {

	private JFrame frame = new JFrame();
	private JPanel panelDots = new JPanel();
	private JPanel panelButtons = new JPanel();
	private JButton run = new JButton();
	private JCheckBox cluster = new JCheckBox();
	private JCheckBox line = new JCheckBox();
	
	
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
	}
	
	
	public MainFrame() {
		
		//frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		frame.setSize(600, 400);
		frame.setTitle("Dots Project");
		setLayout(new BorderLayout());
		
		//workarea
		panelDots.setBackground(Color.gray);
		
		//button options
		GridLayout vertical = new GridLayout(8, 0);
		panelButtons.setLayout(vertical);
		cluster.setText("Cluster - K-means");
		line.setText("Line - Nearest Neighbor");
		run.setText("Run");
		panelButtons.add(cluster);
		panelButtons.add(line);
		panelButtons.add(run);
		
		//lsteners
		panelDots.addMouseListener(this);
		
		//add panels tp frame
		frame.add(panelDots, BorderLayout.CENTER);
		frame.add(panelButtons, BorderLayout.WEST);

	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
//		Graphics g = getGraphics();
//		g.setColor(Color.black);
//		g.fillOval(e.getX(), e.getY(), 10, 10);
		
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
