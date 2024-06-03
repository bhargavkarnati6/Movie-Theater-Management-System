package project.xyz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SelectMovie extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int x = 0;
	private int y = 0;
	private ArrayList<Showtime> showtimeList = null;
	private ArrayList<Movie> movieList = null;
	private String inAdvText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, User inUse) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectMovie frame = new SelectMovie(inUse);
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
	public SelectMovie(User inUse) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectMovie = new JLabel("Select Movie");
		lblSelectMovie.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectMovie.setBounds(91, 12, 280, 20);
		contentPane.add(lblSelectMovie);
		
		JLabel lblNowShowing = new JLabel("Now Showing");
		lblNowShowing.setBounds(22, 44, 108, 15);
		contentPane.add(lblNowShowing);
		
		JLabel lblAdvanceTicket = new JLabel("Advance Ticket");
		lblAdvanceTicket.setBounds(22, 150, 108, 15);
		contentPane.add(lblAdvanceTicket);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setBounds(134, 150, 70, 15);
		contentPane.add(lblDate);
		
		//import info
		LocalDate today = LocalDate.now();
		LocalTime current = LocalTime.now();
		ArrayList<Showtime> todayList = new ArrayList<>();//
		ArrayList<Showtime> laterList = new ArrayList<>();//
		ArrayList<Movie> todayMovie = new ArrayList<>();
		ArrayList<Movie> laterMovie = new ArrayList<>();
		
		try {
			movieList = ReadInput.availableMovie();
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			showtimeList = ReadInput.availableShowtime();
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e);
		}
		//extract into now list
		for (int i=0; i<showtimeList.size(); i++) {
			Showtime inList = showtimeList.get(i);
			if ((inList.getDate().equals(today))&&(inList.getTime().isAfter(current))) {
				todayList.add(inList);
			}
		}
		for (int i=0; i<movieList.size(); i++) {
			String titleA = movieList.get(i).getTitle();
			for (int j=0; j<todayList.size(); j++) {
				for (int k=0; k<todayList.get(j).getMovies().size(); k++) {
					String titleB = todayList.get(j).getMovies().get(k).getTitle();
					if (titleA.equals(titleB)) {
						todayMovie.add(movieList.get(i));
						break;
					}
				}
			}
		}
		
		//set text in now showing
		String inNowText = "Now showing list is unavailable.";
		if (!todayMovie.isEmpty()) {
			inNowText = todayMovie.get(x).displayInfo();
		}
		JTextPane nowText = new JTextPane();
		nowText.setFont(new Font("Dialog", Font.PLAIN, 11));
		nowText.setText(inNowText);
		nowText.setBounds(12, 71, 309, 67);
		contentPane.add(nowText);
		
		JButton nowNext = new JButton("Next");
		nowNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (x<todayMovie.size()-1) {
					x++;
					nowText.setText(todayMovie.get(x).displayInfo());
				}
				else {
					x = 0;
					nowText.setText(todayMovie.get(x).displayInfo());
				}
			}
		});
		nowNext.setBounds(350, 113, 78, 25);
		contentPane.add(nowNext);
		
		JButton nowSelect = new JButton("Select");
		nowSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to SelectShowtime Frame
				dispose();
				SelectShowtime selectShowtime = new SelectShowtime(inUse, todayMovie.get(x), todayList);
				selectShowtime.setVisible(true);
			}
		});
		nowSelect.setBounds(350, 71, 78, 25);
		contentPane.add(nowSelect);

		inAdvText = "Please select advance booking date.";
		JTextPane advanceText = new JTextPane();
		advanceText.setFont(new Font("Dialog", Font.PLAIN, 11));
		advanceText.setText(inAdvText);
		advanceText.setBounds(12, 177, 309, 67);
		contentPane.add(advanceText);
		
		Date start = new Date();
		JSpinner spinner = new JSpinner(new SpinnerDateModel(start, null, null, Calendar.MONTH));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yy");
		spinner.setEditor(editor);
		spinner.setBounds(213, 148, 108, 20);
		contentPane.add(spinner);
		ChangeListener spinnerChangeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				laterList.clear();
				laterMovie.clear();
				Date selectedDate = (Date) spinner.getValue();
				LocalDate fromSelect = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
				//extract into adv list
				for (int i=0; i<showtimeList.size(); i++) {
					Showtime inList = showtimeList.get(i);
					if (inList.getDate().equals(fromSelect)) {
						laterList.add(inList);
					}
				}
				for (int i=0; i<movieList.size(); i++) {
					String titleA = movieList.get(i).getTitle();
					for (int j=0; j<laterList.size(); j++) {
						for (int k=0; k<laterList.get(j).getMovies().size(); k++) {
							String titleB = laterList.get(j).getMovies().get(k).getTitle();
							if (titleA.equals(titleB)) {
								laterMovie.add(movieList.get(i));
								break;
							}
						}
					}
				}
				
				//set text in adv ticket
				inAdvText = "Advance Ticket list is unavailable.";
				if (!laterMovie.isEmpty()) {
					inAdvText = laterMovie.get(y).displayInfo();
				}
				advanceText.setText(inAdvText);
			}
		};
		spinner.addChangeListener(spinnerChangeListener);
		
		JButton advNext = new JButton("Next");
		advNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (y<laterMovie.size()-1) {
					y++;
					advanceText.setText(laterMovie.get(y).displayInfo());
				}
				else {
					y = 0;
					advanceText.setText(laterMovie.get(y).displayInfo());
				}
			}
		});
		advNext.setBounds(350, 219, 78, 25);
		contentPane.add(advNext);
		
		JButton advSelect = new JButton("Select");
		advSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to SelectShowtime Frame
				dispose();
				SelectShowtime selectShowtime = new SelectShowtime(inUse, laterMovie.get(y), laterList);
				selectShowtime.setVisible(true);
			}
		});
		advSelect.setBounds(350, 177, 78, 25);
		contentPane.add(advSelect);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to Option Frame
				dispose();
				Option option = new Option(inUse);
				option.setVisible(true);
			}
		});
		btnBack.setBounds(12, 10, 117, 25);
		contentPane.add(btnBack);
		
	}
}
