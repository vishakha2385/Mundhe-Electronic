import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StockListShop extends JFrame {

	private JPanel contentPane;
	private JTable table;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockListShop frame = new StockListShop();
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
	public StockListShop() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StockListShop.class.getResource("/images/logoShop.jpg")));
		setTitle("Stock List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(225,0,950,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Category:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel.setBounds(29, 29, 74, 26);
		contentPane.add(lblNewLabel);
		
		JButton btnElectronics = new JButton("Electronics");
		btnElectronics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					String sql="select *from StockData where Category='Electronics' ORDER BY Product_Name ASC";
					ps=con.prepareStatement(sql);
					rs=ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		btnElectronics.setForeground(new Color(0, 0, 128));
		btnElectronics.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnElectronics.setBounds(113, 29, 146, 28);
		contentPane.add(btnElectronics);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 66, 874, 631);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new HomeShop().setVisible(true);
			}
		});
		btnCancel.setForeground(new Color(0, 0, 128));
		btnCancel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnCancel.setBounds(423, 29, 146, 28);
		contentPane.add(btnCancel);
		
		JButton btnElectricals = new JButton("Electricals");
		btnElectricals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					String sql="select *from StockData where Category='Electricals' ORDER BY Product_Name ASC";
					ps=con.prepareStatement(sql);
					rs=ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		btnElectricals.setForeground(new Color(0, 0, 128));
		btnElectricals.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnElectricals.setBounds(268, 30, 146, 28);
		contentPane.add(btnElectricals);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
	}
}
