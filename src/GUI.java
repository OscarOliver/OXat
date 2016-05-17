import terminalIO.TerminalIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Oscar Oliver on 13/05/16.
 */
public class GUI {
	private OXat xat;

	GenericGUI window;

	public GUI(String IP) {
		xat = new OXat(IP);
		xat.logIn("Oscar", "Oscar");
		makeFrame();
	}

	public static void main(String[] args) {
		TerminalIO.print('s');
		String IP;
		if (args.length == 0) IP = "127.0.0.1";
		else				  IP = args[0];
		new GUI(IP);
	}

	private void makeFrame() {
		window = new GenericGUI("OXat");

		window.setHeadPanel(new JLabel("Oscar"));

		JButton sendMessage = new JButton("Enviar missatges");
		sendMessage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				JPanel content = new JPanel(new BorderLayout());

				JTextField toField = new JTextField("Per a");
				toField.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						toField.setText("");
					}
				});
				content.add(toField, BorderLayout.NORTH);

				JTextArea textArea = new JTextArea();
				content.add(textArea, BorderLayout.CENTER);

				window.setMainPanel(content);

				JButton enviar = new JButton("Enviar");
				enviar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						String user = toField.getText();
						String message = textArea.getText();
						xat.sendMessage(user, message);
					}
				});
				window.setRightPanel(enviar);
				window.refresh();
			}
		});
		window.addButton(sendMessage);

		JButton showMessages = new JButton("Veure missatges");
		showMessages.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				ArrayList<String[]> messages = xat.getMessages();
				JTextArea textArea = new JTextArea();
				String text = "";
				for (String[] message : messages) {
					text += message[0] + " at " + message[1].substring(0, 16) + "\n" + message[2] + "\n\n";
				}
				textArea.setText(text);
				Debug.print(text);
				window.setMainPanel(textArea);
				window.refresh();
			}
		});
		window.addButton(showMessages);

		window.refresh();
		window.setVisible(true);
	}
}
