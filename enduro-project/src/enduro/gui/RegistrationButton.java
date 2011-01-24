package enduro.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class RegistrationButton extends JButton implements ActionListener {
	private RegistrationTextField registrationTextField;

	/**
	 * Creates a new RegistrationButton with the specified name, font and
	 * reference to the RegistrationTextField.
	 * 
	 * @param name
	 *            The text on the button.
	 * @param font
	 *            The font to use on this button.
	 * @param registrationTextField
	 *            The RegistrationTextField to trigger actionPerformed on if the
	 *            button is clicked.
	 */
	public RegistrationButton(String name, Font font,
			RegistrationTextField registrationTextField) {
		super(name);
		addActionListener(this);
		setFont(font);
		this.registrationTextField = registrationTextField;
	}

	public void actionPerformed(ActionEvent ae) {
		registrationTextField.actionPerformed(ae);
	}

}
