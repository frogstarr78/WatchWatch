import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class Logique extends JFrame {

	public JLabel errorLabel;

	public JLabel UsernameLabel;
	public JTextField UsernameField;
	public JLabel PasswordLabel;
	public JPasswordField PasswordField;

	public JButton loginButton;
	public JButton cancelButton;

	public JPanel defaultPanel;

	public Logique() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	

		int width = 200;
		int height = 180;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width)/2;
		int y = (screen.height-height)/2;
		setBounds(x,y,width,height);
		setTitle("Login?");
		// frame settings
		//
		// Employee loading

		// panel stuff
		LayoutManager layoutManager = new GridLayout(4, 2, 5, 7);
		defaultPanel = new JPanel();
		defaultPanel.setPreferredSize(new Dimension(200, 120));
		defaultPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		defaultPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		defaultPanel.setLayout(layoutManager);

		errorLabel = new JLabel(" ");	
		add(errorLabel, BorderLayout.NORTH);

		// Labels and text fields
		UsernameLabel = new JLabel("Username:", JLabel.LEADING);
		UsernameLabel.setDisplayedMnemonic('U');

		UsernameField = new JTextField();
		UsernameLabel.setLabelFor(UsernameField);
		UsernameField.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(UsernameField.getText().length() > 3) {
//						Employees.findByName(UsernameField.getText());
					}
				}
			}
		);

		//add username field/label
		defaultPanel.add(UsernameLabel);
		defaultPanel.add(UsernameField, BorderLayout.SOUTH);


		// password field/label
		PasswordLabel = new JLabel("Password:", JLabel.LEADING);
		PasswordLabel.setDisplayedMnemonic('P');

		PasswordField = new JPasswordField();
		PasswordLabel.setLabelFor(PasswordField);

		//add password field/label
		defaultPanel.add(PasswordLabel);
		defaultPanel.add(PasswordField, BorderLayout.SOUTH);


		//buttons
		this.loginButton = new JButton("Login");	
		loginButton.setMnemonic('L');
//		loginButton.addActionListener(
//			new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					login();
//				}
//			}
//		);

		cancelButton = new JButton("Cancel");
		cancelButton.setMnemonic('C');
		cancelButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			}
		);

		//add buttons
		defaultPanel.add(loginButton, BorderLayout.SOUTH);
		getRootPane().setDefaultButton(loginButton);
		defaultPanel.add(cancelButton, BorderLayout.SOUTH);

		add(defaultPanel, BorderLayout.SOUTH);
		pack();
	}

	public String getErrorString() { return this.errorLabel.getText(); }
	public void setErrorString(String error) { this.errorLabel.setText(error); }
	public JButton getLoginButton() {
		return this.loginButton;
	}

	public String getUsername() {
		return this.UsernameField.getText();	
	}
	public String getPassword() {
		char[] chars = this.PasswordField.getPassword();
		String reallyRealPassword = "";
		for(int i=0; i<chars.length; i++) {
			reallyRealPassword += chars[i];	
		}
		return reallyRealPassword;	
	}

	public static void main(String[] args) {
		Logique logique = new Logique();
		logique.setVisible(true);
	}
}
