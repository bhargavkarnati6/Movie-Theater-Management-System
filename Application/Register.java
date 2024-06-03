package project.xyz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.io.IOException;
import javax.swing.SwingConstants;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		emailField = new JTextField();
		emailField.setBounds(180, 70, 190, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(180, 100, 190, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(90, 70, 100, 20);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(90, 100, 100, 20);
		contentPane.add(lblPassword);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setBounds(90, 30, 280, 20);
		contentPane.add(lblRegister);
		
		JLabel feedbackText = new JLabel("Please fill information in the fields");
		feedbackText.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackText.setBounds(90, 210, 276, 15);
		contentPane.add(feedbackText);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email, password;
				boolean except = true;
				email = emailField.getText();
				password = PasswordEncryption.encryptPassword(passwordField.getText()); // Bharghav
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
						feedbackText.setText("This email has been used. Please try again.");
						except = false;
						break;
					}
				}
				if (except) {
					User user = new User(email, password);
					try {
						WriteOutput.addUser(user);
					}
					catch (IOException err) {
						System.out.println(err.getMessage());
					}
					catch (ClassNotFoundException err) {
						System.out.println(err.getMessage());
					}
					JOptionPane.showMessageDialog(null, "Register success.");
					//go to Login Frame
					dispose();
					Login login = new Login();
					login.getFrame().setVisible(true);
				}
			}
		});
		btnConfirm.setBounds(170, 155, 117, 25);
		contentPane.add(btnConfirm);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to Login Frame
				dispose();
				Login login = new Login();
				login.getFrame().setVisible(true);
			}
		});
		btnBack.setBounds(12, 28, 117, 25);
		contentPane.add(btnBack);
		
	}

}
