import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
public class ResetPasswordShop extends JFrame {

	private JPanel contentPane;
	private JTextField txtNewPassword;
    private JTextField txtExistingPassword;
    private JLabel lblUserId;
    private JButton btnCancel;
    private JButton btnReset;
    
    Connection con;
    PreparedStatement ps;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetPasswordShop frame = new ResetPasswordShop();
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
	public ResetPasswordShop() {
		
		//make connection to the database
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ResetPasswordShop.class.getResource("/images/passowrd iconShop.jpg")));
		setTitle("Change Password");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(325, 200, 690, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter New Password:");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel.setBounds(47, 80, 220, 33);
		contentPane.add(lblNewLabel);
		
		txtNewPassword = new JTextField();
		txtNewPassword.setForeground(new Color(0, 0, 128));
		txtNewPassword.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtNewPassword.setBounds(300, 80, 300, 28);
		contentPane.add(txtNewPassword);
		txtNewPassword.setColumns(10);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//update password for the reset password purpose
				int a=JOptionPane.showConfirmDialog(null,"Are you sure you want to reset your Password?","Confirm Reset Password",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			    if(a==JOptionPane.YES_OPTION)
			    {
				try 
				{
				String sql3="UPDATE LoginData SET Password=? WHERE Password=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql3);
				ps.setString(2,txtExistingPassword.getText());
				ps.setString(1,txtNewPassword.getText());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Password Changes Successfully!");
				LoginShop frame = new LoginShop();
				frame.setVisible(true);
				}
				catch(Exception e1) {}
			    }
			}
		});
		btnReset.setForeground(new Color(0, 0, 128));
		btnReset.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnReset.setBounds(214, 126, 110, 35);
		contentPane.add(btnReset);
		
		txtExistingPassword = new JTextField();
		txtExistingPassword.setForeground(new Color(0, 0, 128));
		txtExistingPassword.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtExistingPassword.setColumns(10);
		txtExistingPassword.setBounds(300, 34, 300, 28);
		contentPane.add(txtExistingPassword);
		
		lblUserId = new JLabel("Enter Existing Password:");
		lblUserId.setForeground(new Color(0, 0, 128));
		lblUserId.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblUserId.setBounds(47, 34, 249, 33);
		contentPane.add(lblUserId);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//close the reset password frame
				ResetPasswordShop.this.dispose();
			}
		});
		btnCancel.setForeground(new Color(0, 0, 128));
		btnCancel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnCancel.setBounds(352, 126,  110, 35);
		contentPane.add(btnCancel);
		
	}

}
