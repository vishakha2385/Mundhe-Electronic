import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class MundheElectronicsShop {

	JFrame frmWelcome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MundheElectronicsShop window = new MundheElectronicsShop();
					window.frmWelcome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MundheElectronicsShop() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcome = new JFrame();
		frmWelcome.setTitle("Welcome");
		frmWelcome.setIconImage(Toolkit.getDefaultToolkit().getImage(MundheElectronicsShop.class.getResource("/images/plug.png")));
		frmWelcome.setBounds(0,0,1366,768);
		frmWelcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcome.getContentPane().setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new LoginShop().setVisible(true);
			}
		});
		btnStart.setForeground(new Color(0, 0, 128));
		btnStart.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnStart.setBounds(1185, 645, 89, 30);
		frmWelcome.getContentPane().add(btnStart);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MundheElectronicsShop.class.getResource("/images/wallpaper1Shoptest.jpg")));
		lblNewLabel.setBounds(0,0,1366,768);
		frmWelcome.getContentPane().add(lblNewLabel);
		
		
		
	}
}
