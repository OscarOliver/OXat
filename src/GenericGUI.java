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
		mainPanel.removeAll();
		mainPanel.add(component);
		//contentPanel.add(mainPanel, BorderLayout.CENTER);
		refresh();
	}

	public void addButton(JButton button) {
		JPanel panel = new JPanel();
		panel.add(button);
		buttonsPanel.add(panel);
		refresh();
	}

	public void addButton(JButton button, int pos) {
		JPanel panel = new JPanel();
		panel.add(button);
		buttonsPanel.add(panel, pos);
		refresh();
	}

	public void removeButtons() {
		buttonsPanel = new JPanel(new GridLayout(0, 1));
		refresh();
	}

	public void setMessage(String text) {
		missageLabel.setText(text);
	}

	public String getMessage() {
		return missageLabel.getText();
	}

	public void setHeadPanel(JComponent headPanel) {
		this.headPanel = headPanel;
		refresh();
	}

	public void setRightPanel(JComponent rightPanel) {
		this.rightPanel.removeAll();
		this.rightPanel.add(rightPanel);
		refresh();
	}

	public void refresh() {
		pack();
	}

	private void makeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(300, 300));
		setPreferredSize(new Dimension(500, 500));

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

		pack();
		setVisible(true);
	}
}
