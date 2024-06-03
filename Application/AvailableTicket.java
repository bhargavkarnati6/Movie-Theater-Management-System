package project.xyz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

public class AvailableTicket extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Ticket> list;
	private int i = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, User inUse) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AvailableTicket frame = new AvailableTicket(inUse);
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
	public AvailableTicket(User inUse) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAvailableticket = new JLabel("AvailableTicket");
		lblAvailableticket.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvailableticket.setBounds(90, 30, 280, 20);
		contentPane.add(lblAvailableticket);
		
		list = inUse.getTickets();
		JLabel feedbackText = new JLabel();
		feedbackText.setBounds(90, 210, 276, 15);
		contentPane.add(feedbackText);
		if (list == null) {
			feedbackText.setText("There is no ticket in your inventory");
		}
		else {
			feedbackText.setText("The number of ticket(s): "+list.size());
		}
		
		if (!list.isEmpty()) {
			JTextPane ticketPane = new JTextPane();
			ticketPane.setText(list.get(i).printDetail());
			ticketPane.setBounds(12, 67, 399, 93);
			contentPane.add(ticketPane);
			
			JButton refund = new JButton("Refund");
			refund.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					list.remove(i);
					i--;
					if (list.isEmpty()) {
						dispose();
						AvailableTicket viewTicket = new AvailableTicket(inUse);
						viewTicket.setVisible(true);
					}
					else {
						ticketPane.setText(list.get(i).printDetail());
						feedbackText.setText("The number of ticket(s): "+list.size());
					}
				}
			});
			refund.setBounds(12, 172, 117, 25);
			contentPane.add(refund);

			JButton btnNext = new JButton("Next");
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (i<list.size()-1) {
						i++;
						ticketPane.setText(list.get(i).printDetail());
					}
					else {
						i = 0;
						ticketPane.setText(list.get(i).printDetail());
					}
				}
			});
			btnNext.setBounds(294, 172, 117, 25);
			contentPane.add(btnNext);
		}
			
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to Option Frame
				dispose();
				Option option = new Option(inUse);
				option.setVisible(true);
			}
		});
		btnBack.setBounds(12, 28, 117, 25);
		contentPane.add(btnBack);
		
	}
}