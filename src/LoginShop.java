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
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class LoginShop extends JFrame {

	private JPanel contentPane;

	
	
	protected Component frame;
    private JButton btnLogin;
    private JButton btnCancel;
    private JButton btnResetPassword;
    private JTextField txtUserId;
    private JPasswordField txtPassword;
    
    Connection con;
    java.sql.Statement stmt;
    ResultSet rs;
    PreparedStatement ps;
    
    //create table LoginData
    public void createTableLoginData()
	{
		try {
			DatabaseMetaData d=con.getMetaData();
			ResultSet rs=d.getTables(null,null,"LoginData",null);
			if(rs.next())
			{
			//	JOptionPane.showMessageDialog(null,"LoginData table exist");
			}
			else 
			{
				String Create_Table="create table LoginData(id varchar(50),Password varchar(50))";
				PreparedStatement ps=con.prepareStatement(Create_Table);
				ps.executeUpdate();
			//	JOptionPane.showMessageDialog(null,"LoginData created successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    //insert id and password into LoginData table
    public void insertLoginData()
    {
    	try 
		{
		String sql1="insert into LoginData values(?,?)";	
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
		ps=con.prepareStatement(sql1);
		ps.setString(1,"ME");	
		ps.setString(2,"ME");	
		ps.executeUpdate();
//		JOptionPane.showMessageDialog(null,"inserted");
		}
		catch(Exception e1) 
		{
	//		JOptionPane.showMessageDialog(null,e1);	
		}
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginShop frame = new LoginShop();
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
	public LoginShop()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
		//	JOptionPane.showMessageDialog(null,"connected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		setTitle("Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginShop.class.getResource("/images/plug.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(LoginShop.class.getResource("/images/login icontest.png")));
		lblNewLabel.setBounds(611, 141, 145, 140);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User ID:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(462, 330,100,40);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(458, 393, 100, 40);
		contentPane.add(lblNewLabel_1_1);
		
		txtUserId = new JTextField();
		txtUserId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					txtPassword.requestFocus();
				}
			}
		});
		txtUserId.setForeground(new Color(0, 0, 128));
		txtUserId.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtUserId.setBounds(636, 332, 260, 28);
		contentPane.add(txtUserId);
		txtUserId.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(LoginShop.class.getResource("/images/user id iconShop.jpg")));
		lblNewLabel_2.setBounds(586, 327, 40, 35);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("New label");
		lblNewLabel_2_1.setIcon(new ImageIcon(LoginShop.class.getResource("/images/passowrd iconShop.jpg")));
		lblNewLabel_2_1.setBounds(586, 394, 40, 35);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//fetch login details from database to make login
				try {
                    String d1, d2;
                    d1 = txtUserId.getText();
                    d2 = new String(txtPassword.getText());
                    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
                    stmt =con.createStatement();
                    rs = ((java.sql.Statement) stmt).executeQuery("select * from LoginData");
                    while (rs.next()) {
                        String uname = rs.getString(1);
                        String pass = rs.getString(2);
                        if (uname.equals(d1) && pass.equals(d2))
                        {
                           new HomeShop().setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid User Id and Password!");
                            txtPassword.setText("");
                        }
                    }
               
				} catch (Exception ie) {
                    ie.printStackTrace();
                }
			}
		});
		btnLogin.setForeground(new Color(0, 0, 128));
		btnLogin.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnLogin.setBounds(520, 469, 130, 30);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//go to the home page on cancel action
				MundheElectronicsShop window = new MundheElectronicsShop();
				window.frmWelcome.setVisible(true);
			}
		});
		btnCancel.setForeground(new Color(0, 0, 128));
		btnCancel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnCancel.setBounds(690, 469, 130, 30);
		contentPane.add(btnCancel);
		
		JButton btnReset = new JButton("Reset Password");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//open reset password page
				ResetPasswordShop frame = new ResetPasswordShop();
				frame.setVisible(true);
			}
		});
		btnReset.setForeground(new Color(0, 0, 128));
		btnReset.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnReset.setBounds(560, 536,240, 30);
		contentPane.add(btnReset);
		
		txtPassword = new JPasswordField();
		txtPassword.setForeground(new Color(0, 0, 128));
		txtPassword.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPassword.setBounds(636, 397, 260, 28);
		contentPane.add(txtPassword);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
		
		createTableLoginData();
		insertLoginData();
	}
}
