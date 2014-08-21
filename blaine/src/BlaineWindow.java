import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BlaineWindow {

	private JFrame frame;
	JButton btnActivate;
	JLabel lblCurrentStatus;
	
	
	
	private BlaineBot blaine;
	
	public boolean botIsRunning() {
		return blaine.botIsRunning();
	}
	
	public String getCurrentStatus() {
		if(!blaine.botIsRunning()) {
			return "Idle";
		} else {
			return "Active";
		}
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlaineWindow window = new BlaineWindow();
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
	public BlaineWindow() {
		blaine = new BlaineBot(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Blaine");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		lblCurrentStatus = new JLabel("Current Status: " + getCurrentStatus());
		panel.add(lblCurrentStatus);
		
		btnActivate = new JButton("Activate");
		
		btnActivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!botIsRunning()) {
					activateBot();
				} else {
					deactivateBot();
				}
			}
		});
		frame.getContentPane().add(btnActivate, BorderLayout.SOUTH);
	}
	
	public void activateBot() {
		btnActivate.setText("Deactivate");
		
		// Activate the bot
		blaine.start();
		updateCurrentStatus();
	}
	
	public void deactivateBot() {
		btnActivate.setText("Activate");
		
		// Stop the bot
		blaine.stop();
		updateCurrentStatus();
	}
	
	public void updateCurrentStatus() {
		lblCurrentStatus.setText("Current Status: " + getCurrentStatus());
	}
	
	public void forceMinimize() {
		frame.setState(JFrame.ICONIFIED);
	}

}
