package project.xyz;

import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
//import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;

public class Confirmation extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, User inUse, Movie selected, ArrayList<Showtime> available, int showIndex, int screenIndex, int seatIndex) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Confirmation frame = new Confirmation(inUse, selected, available, showIndex, screenIndex, seatIndex);
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
	public Confirmation(User inUse, Movie selected, ArrayList<Showtime> available, int showIndex, int screenIndex, int seatIndex) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConfirmationAndPayment = new JLabel("Confirmation and Payment");
		lblConfirmationAndPayment.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmationAndPayment.setBounds(90, 30, 280, 20);
		contentPane.add(lblConfirmationAndPayment);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Dialog", Font.BOLD, 14));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to SelectSeat Frame
				dispose();
				SelectSeat selectSeat = new SelectSeat(inUse, selected, available, showIndex, screenIndex);
				selectSeat.setVisible(true);
			}
		});
		btnBack.setBounds(12, 28, 117, 25);
		contentPane.add(btnBack);
		
		Seat hold = available.get(showIndex).getScreens().get(screenIndex).getSeats()[seatIndex];
		String ticketText = "\tMovie:"+selected.getTitle()+"\n"+hold.displayInTicket()+"\n\tDetail: "+selected.getDescription();
		JTextPane txtpnAbc = new JTextPane();
		txtpnAbc.setText(ticketText);
		txtpnAbc.setBounds(12, 70, 416, 130);
		contentPane.add(txtpnAbc);
		
		JLabel lblPrice = new JLabel("Price: $");
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPrice.setBounds(29, 227, 70, 15);
		contentPane.add(lblPrice);
		
		JButton btnConfirmpay = new JButton("Confirm/Pay");
		btnConfirmpay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//purchasing complete
				Ticket newTicket = new Ticket(inUse.getUserId(), selected, hold);
				inUse.addTicket(newTicket);
				/*try {
					WriteOutput.addUser(inUse);
				}
				catch (IOException err) {
					System.out.println(err.getMessage());
				}
				catch (ClassNotFoundException err) {
					System.out.println(err.getMessage());
				}*/
				hold.setHolder(inUse.getUserId());
				JOptionPane.showMessageDialog(null, "Purchase success.");
				//go to Option Frame
				dispose();
				Option option = new Option(inUse);
				option.setVisible(true);
			}
		});
		btnConfirmpay.setBounds(279, 222, 138, 25);
		contentPane.add(btnConfirmpay);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.BOLD, 14));
		textField.setText(""+hold.getTotalPrice());
		textField.setBounds(94, 225, 63, 19);
		contentPane.add(textField);
		textField.setColumns(10);
	}

}
