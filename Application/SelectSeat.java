package project.xyz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JComboBox;

public class SelectSeat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private int seatIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, User inUse, Movie selected, ArrayList<Showtime> available, int showIndex, int screenIndex) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectSeat frame = new SelectSeat(inUse, selected, available, showIndex, screenIndex);
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
	public SelectSeat(User inUse, Movie selected, ArrayList<Showtime> available, int showIndex, int screenIndex) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectSeat = new JLabel("Select Seat");
		lblSelectSeat.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectSeat.setBounds(91, 12, 280, 20);
		contentPane.add(lblSelectSeat);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to SelectShowtime Frame
				dispose();
				SelectShowtime selectShowtime = new SelectShowtime(inUse, selected, available);
				selectShowtime.setVisible(true);
			}
		});
		btnBack.setBounds(12, 10, 117, 25);
		contentPane.add(btnBack);
		
		JLabel lblSelectedSeat = new JLabel("Selected Seat:");
		lblSelectedSeat.setBounds(12, 243, 117, 15);
		contentPane.add(lblSelectedSeat);
		
		JLabel lblPrice = new JLabel("Price: $");
		lblPrice.setBounds(192, 243, 61, 15);
		contentPane.add(lblPrice);
		
		textField = new JTextField();
		textField.setText("");
		textField.setBounds(253, 241, 46, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to Confirmation
				dispose();
				Confirmation confirm = new Confirmation(inUse, selected, available, showIndex, screenIndex, seatIndex);
				confirm.setVisible(true);
			}
		});
		btnContinue.setBounds(311, 238, 117, 25);
		contentPane.add(btnContinue);
		
		String showLayout = available.get(showIndex).getScreens().get(screenIndex).displaySeats();
		JTextPane layout = new JTextPane();
		layout.setFont(new Font("Nimbus Mono PS", Font.PLAIN, 16));
		layout.setText(showLayout);
		layout.setBounds(46, 44, 354, 182);
		contentPane.add(layout);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(121, 238, 61, 24);
		contentPane.add(comboBox);
		
		ArrayList<Integer> seatIndexList = new ArrayList<>();
		Seat[] seatArray = available.get(showIndex).getScreens().get(screenIndex).getSeats();
		for (int i=0; i<seatArray.length; i++) {
			if (seatArray[i].getHolder()==0) {
				comboBox.addItem(seatArray[i].getSeatId());
				seatIndexList.add(i);
			}
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				seatIndex = seatIndexList.get(index);
				textField.setText(""+seatArray[index].getTotalPrice());
			}
		});
	}
}
