package project.xyz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Option extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, User inUse) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Option frame = new Option(inUse);
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
	public Option(User inUse) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBuyTicket = new JButton("Buy Ticket");
		btnBuyTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to Select Movie Frame
				dispose();
				SelectMovie selectMovie = new SelectMovie(inUse);
				selectMovie.setVisible(true);
			}
		});
		btnBuyTicket.setBounds(174, 90, 117, 25);
		contentPane.add(btnBuyTicket);
		
		JButton btnViewTicket = new JButton("View Ticket");
		btnViewTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to Available Ticket Frame
				dispose();
				AvailableTicket viewTicket = new AvailableTicket(inUse);
				viewTicket.setVisible(true);
			}
		});
		btnViewTicket.setBounds(174, 137, 117, 25);
		contentPane.add(btnViewTicket);
		
		JLabel lblOption = new JLabel("Option");
		lblOption.setHorizontalAlignment(SwingConstants.CENTER);
		lblOption.setBounds(90, 30, 280, 20);
		contentPane.add(lblOption);
		
		JLabel lblHellopleaseSelect = new JLabel("Hello "+inUse.getEmail()+", please select the options.");
		lblHellopleaseSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblHellopleaseSelect.setBounds(90, 210, 276, 15);
		contentPane.add(lblHellopleaseSelect);
	}

}
