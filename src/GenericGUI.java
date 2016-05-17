import javax.swing.*;
import java.awt.*;

/**
 * Created by Oscar Oliver on 15/05/16.
 */
public class GenericGUI extends JFrame {

	private JPanel contentPanel;

	private JComponent mainPanel;
	private JPanel buttonsPanel;
	private JComponent headPanel;
	private JLabel missageLabel;
	private JComponent rightPanel;

	public GenericGUI(String windowTitle) {
		super(windowTitle);
		makeFrame();
	}


	public void setMainPanel(JComponent component) {
		this.mainPanel = component;
		mainPanel.removeAll();
		contentPanel.add(mainPanel, BorderLayout.CENTER);
		refresh();
	}

	public void addButton(JButton button) {
		JPanel panel = new JPanel();
		panel.add(button);
		buttonsPanel.add(panel);
	}

	public void addButton(JButton button, int pos) {
		JPanel panel = new JPanel();
		panel.add(button);
		buttonsPanel.add(panel, pos);
	}

	public void removeButtons() {
		buttonsPanel = new JPanel(new GridLayout(0, 1));
	}

	public void setMissage(String text) {
		missageLabel.setText(text);
	}

	public String getMissage() {
		return missageLabel.getText();
	}

	public void setHeadPanel(JComponent headPanel) {
		this.headPanel = headPanel;
	}

	public void setRightPanel(JComponent rightPanel) {
		this.rightPanel = rightPanel;
	}

	public void refresh() {
		repaint();
	}

	private void makeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);

		contentPanel = (JPanel) getContentPane();
		contentPanel.setLayout(new BorderLayout());

		mainPanel = new JPanel();
		contentPanel.add(mainPanel, BorderLayout.CENTER);

		buttonsPanel = new JPanel(new GridLayout(0, 1));
		contentPanel.add(buttonsPanel, BorderLayout.WEST);

		missageLabel = new JLabel();
		contentPanel.add(missageLabel, BorderLayout.SOUTH);

		headPanel = new JPanel();
		contentPanel.add(headPanel, BorderLayout.NORTH);

		rightPanel = new JPanel();
		contentPanel.add(rightPanel, BorderLayout.EAST);

		setVisible(true);
	}
}
