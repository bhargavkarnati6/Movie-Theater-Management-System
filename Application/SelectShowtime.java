package project.xyz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

public class SelectShowtime extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int premiumShowIndex;
	private int premiumScreenIndex;
	private int standardShowIndex ;
	private int standardScreenIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, User inUse, Movie selected, ArrayList<Showtime> available) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectShowtime frame = new SelectShowtime(inUse, selected, available);
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
	public SelectShowtime(User inUse, Movie selected, ArrayList<Showtime> available) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectShowtime = new JLabel("Select Showtime");
		lblSelectShowtime.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectShowtime.setBounds(90, 30, 280, 20);
		contentPane.add(lblSelectShowtime);
		
		JTextPane movieText = new JTextPane();
		movieText.setText(selected.displayInfo());
		movieText.setBounds(12, 62, 416, 88);
		contentPane.add(movieText);
		
		JLabel lblPremiumScreen = new JLabel("Premium Screen");
		lblPremiumScreen.setBounds(12, 178, 122, 15);
		contentPane.add(lblPremiumScreen);
		
		JLabel lblStandardScreen = new JLabel("Standard Screen");
		lblStandardScreen.setBounds(12, 225, 122, 15);
		contentPane.add(lblStandardScreen);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to SelectMovie Frame
				dispose();
				SelectMovie selectMovie = new SelectMovie(inUse);
				selectMovie.setVisible(true);
			}
		});
		btnBack.setBounds(12, 28, 117, 25);
		contentPane.add(btnBack);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(157, 178, 70, 15);
		contentPane.add(lblTime);
		
		JComboBox<String> premiumBox = new JComboBox<>();
		premiumBox.setBounds(205, 173, 122, 24);
		contentPane.add(premiumBox);
		ArrayList<Integer> premiumShowIndexList = new ArrayList<>();
		ArrayList<Integer> premiumScreenIndexList = new ArrayList<>();
		for (int i=0; i<available.size(); i++) {
			Showtime showtime = available.get(i);
			for (int j=0; j<showtime.getMovies().size(); j++) {
				Movie inShow = showtime.getMovies().get(j);
				Screen useShow = showtime.getScreens().get(j);
				if((selected.getTitle().equals(inShow.getTitle()))&&(useShow.getScreenId()==1)) {
					premiumBox.addItem(DatePrint.getTimeString(showtime.getTime()));
					premiumShowIndexList.add(i);
					premiumScreenIndexList.add(j);
				}
			}
		}
		premiumBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = premiumBox.getSelectedIndex();
				premiumShowIndex = premiumShowIndexList.get(index);
				premiumScreenIndex = premiumScreenIndexList.get(index);
			}
		});
		
		JComboBox<String> standardBox = new JComboBox<>();
		standardBox.setBounds(205, 220, 122, 24);
		contentPane.add(standardBox);
		ArrayList<Integer> standardShowIndexList = new ArrayList<>();
		ArrayList<Integer> standardScreenIndexList = new ArrayList<>();
		for (int i=0; i<available.size(); i++) {
			Showtime showtime = available.get(i);
			for (int j=0; j<showtime.getMovies().size(); j++) {
				Movie inShow = showtime.getMovies().get(j);
				Screen useShow = showtime.getScreens().get(j);
				if((selected.getTitle().equals(inShow.getTitle()))&&(useShow.getScreenId()!=1)) {
					standardBox.addItem(DatePrint.getTimeString(showtime.getTime()));
					standardShowIndexList.add(i);
					standardScreenIndexList.add(j);
				}
			}
		}
		standardBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = standardBox.getSelectedIndex();
				standardShowIndex = standardShowIndexList.get(index);
				standardScreenIndex = standardScreenIndexList.get(index);
			}
		});
		
		JLabel lblTime_1 = new JLabel("Time:");
		lblTime_1.setBounds(157, 225, 70, 15);
		contentPane.add(lblTime_1);
		
		JButton premiumSelect = new JButton("Select");
		premiumSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to SelectSeat Frame
				dispose();
				SelectSeat selectSeat = new SelectSeat(inUse, selected, available, premiumShowIndex, premiumScreenIndex);
				selectSeat.setVisible(true);
			}
		});
		premiumSelect.setBounds(350, 174, 78, 25);
		contentPane.add(premiumSelect);
		
		JButton standardSelect = new JButton("Select");
		standardSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to SelectSeat Frame
				dispose();
				SelectSeat selectSeat = new SelectSeat(inUse, selected, available, standardShowIndex, standardScreenIndex);
				selectSeat.setVisible(true);
			}
		});
		standardSelect.setBounds(350, 221, 78, 25);
		contentPane.add(standardSelect);
		
	}
}
