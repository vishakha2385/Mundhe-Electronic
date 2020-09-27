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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DealerBillStatus extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DealerBillStatus frame = new DealerBillStatus();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con;
	ResultSet rs;
    PreparedStatement ps;
	/**
	 * Create the frame.
	 */
	public DealerBillStatus() {
		setTitle("Dealer Invoices Status");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DealerBillStatus.class.getResource("/images/logoShop.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Invoice Status:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel.setBounds(21, 25, 300, 27);
		contentPane.add(lblNewLabel);
		
		JButton btnPaid = new JButton("Paid Invoices");
		btnPaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					String sql="select *from DealerData where Pending_Ammount=0";
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
		btnPaid.setBounds(178, 24, 176, 28);
		contentPane.add(btnPaid);
		
		JButton btnPending = new JButton("Pending Invoices");
		btnPending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					String sql="select *from DealerData where Pending_Ammount>0";
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
		btnPending.setBounds(369, 23, 221, 28);
		contentPane.add(btnPending);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new HomeShop().setVisible(true);
			}
		});
		Cancel.setForeground(new Color(0, 0, 128));
		Cancel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		Cancel.setBounds(604, 23, 176, 28);
		contentPane.add(Cancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 62, 1299, 636);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
	}
}
