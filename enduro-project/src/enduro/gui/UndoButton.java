package enduro.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Button for undo in GUI
 * 
 */
@SuppressWarnings("serial")
public class UndoButton extends JButton implements ActionListener {
	private StoredTime storedTime;
	private RegistrationTextField registrationTextField;
		/**
		 * Creates a new RegretButton with the provided text, stored time 
		 * @param text
		 * @param storedTime
		 * @param registrationTextField
		 * 
		 * 
		 */
	public UndoButton(String text, StoredTime storedTime, RegistrationTextField registrationTextField) {
		super(text);
		this.storedTime = storedTime;
		this.registrationTextField = registrationTextField;
		addActionListener(this);
		setFont(new Font(null, Font.PLAIN, 40));
	}

	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
		storedTime.empty();
		registrationTextField.requestFocus();
		registrationTextField.deleteStoredTimeFile();
	}

}
