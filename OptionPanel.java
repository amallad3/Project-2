import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;


/**
 * The panel for the buttons
 * 
 * @author Darius Morar
 * @version 06.20.2022
 */
public class OptionPanel extends JPanel {
   
    private BoardPanel boardPanel;

    private JButton runButton = new JButton();
	private JCheckBox clusterBox = new JCheckBox();
	private JCheckBox lineBox = new JCheckBox();

    public OptionPanel(BoardPanel boardPanel) {
    	GridLayout vertical = new GridLayout(9, 0);
		setLayout(vertical);
		clusterBox.setText("Cluster - K-means");
		lineBox.setText("Line - Nearest Neighbor");
		runButton.setText("Run");
		add(clusterBox);
		add(lineBox);
		add(runButton);

        this.boardPanel = boardPanel;
    	
        this.runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	ClassifierModel model = ClassifierModel.getInstance();


                if (clusterBox.isSelected() && lineBox.isSelected()) {
                    return;
                } 
                else if (clusterBox.isSelected()) {
                	List<Point> points = model.calculateCluster();
                    boardPanel.drawPoints(points);
                }
                else if (lineBox.isSelected()) {
                    List<Line> lines = model.calculateLines();
                    boardPanel.drawLines(lines);
                }
            }
        });
    }
}
