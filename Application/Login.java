package project.xyz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.util.*;
import java.io.IOException;


public class Login {

	private JFrame frame;
	private JTextField emailField;
	private JTextField passwordField;
	private User inUse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public User getInUse() {
		return inUse;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("TMS");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		emailField = new JTextField();
		emailField.setBounds(180, 70, 190, 20);
		frame.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Email:");
		lblUsername.setBounds(90, 70, 100, 20);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(90, 100, 100, 20);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(90, 30, 280, 20);
		frame.getContentPane().add(lblLogin);
		
		passwordField = new JTextField();
		passwordField.setBounds(180, 100, 190, 20);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to Register Frame
				frame.dispose();
				Register register = new Register();
				register.setVisible(true);
			}
		});
		btnRegister.setBounds(250, 154, 120, 25);
		frame.getContentPane().add(btnRegister);
		
		JLabel feedbackText = new JLabel("Welcome to Theatre Application");
		feedbackText.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackText.setBounds(90, 210, 276, 15);
		frame.getContentPane().add(feedbackText);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email, password;
				email = emailField.getText();
				password = passwordField.getText();
				ArrayList<User> users = null;
				try {
					users = ReadInput.userList();
				}
				catch (IOException err) {
					System.out.println(err.getMessage());
				}
				catch (ClassNotFoundException err) {
					System.out.println(err.getMessage());
				}
				for (int i=0; i<users.size(); i++) {
					if (users.get(i).getEmail().equals(email)) {
						inUse = users.get(i);
						break;
					}
				}
				if (inUse == null) {
					feedbackText.setText("Invalid email. Please try  again.");
				}
				else {
					if (inUse.checkPassword(PasswordEncryption.encryptPassword(password))) { // Bharghav
						//go to Option Frame
						frame.dispose();
						Option option = new Option(inUse);
						option.setVisible(true);
					}
					else {
						feedbackText.setText("Incorrect password. Please try again.");
					}
				}
			}
		});
		btnLogin.setBounds(90, 154, 117, 25);
		frame.getContentPane().add(btnLogin);
		
	}
}
