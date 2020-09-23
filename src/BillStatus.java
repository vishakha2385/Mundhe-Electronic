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
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BillStatus extends JFrame {

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
					BillStatus frame = new BillStatus();
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
	public BillStatus() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BillStatus.class.getResource("/images/logoShop.jpg")));
		setTitle("Invoices Status");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Invoice Status:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel.setBounds(29, 25, 124, 27);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 62, 1299, 636);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnPending = new JButton("Pending Invoices");
		btnPending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","vishakha");
					String sql="select *from CustomerInfo where Pending_Ammount>0";
					ps=con.prepareStatement(sql);
					rs=ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		btnPending.setForeground(new Color(0, 0, 128));
		btnPending.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnPending.setBounds(344, 25, 176, 23);
		contentPane.add(btnPending);
		
		JButton btnPaid = new JButton("Paid Invoices");
		btnPaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","vishakha");
					String sql="select *from CustomerInfo where Pending_Ammount=0";
					ps=con.prepareStatement(sql);
					rs=ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		});
		btnPaid.setForeground(new Color(0, 0, 128));
		btnPaid.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnPaid.setBounds(149, 25, 176, 23);
		contentPane.add(btnPaid);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new HomeShop().setVisible(true);
			}
		});
		Cancel.setForeground(new Color(0, 0, 128));
		Cancel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		Cancel.setBounds(539, 25, 176, 23);
		contentPane.add(Cancel);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);

		
	}
}
