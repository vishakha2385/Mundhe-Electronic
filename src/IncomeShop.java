import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
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

public class IncomeShop extends JFrame {

	private JPanel contentPane;
	private JTextField txtDate;
	private JTable table;
	private JTextField txtIncome;
	private JTextField txtGrandTotal;
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
					IncomeShop frame = new IncomeShop();
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
	public IncomeShop() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IncomeShop.class.getResource("/images/plug.png")));
		setTitle("Income");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel.setBounds(24, 21, 50, 24);
		contentPane.add(lblNewLabel);
		
		txtDate = new JTextField();
		txtDate.setForeground(new Color(0, 0, 128));
		txtDate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtDate.setColumns(10);
		txtDate.setBounds(95, 23, 117, 25);
		contentPane.add(txtDate);
		
		JButton btnNewButton = new JButton("Income");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//get income according to the date
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					String sql="select sum(Total) from ProductsData where Date=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,txtDate.getText());
					rs=ps.executeQuery();
					if(rs.next())
					{
						String total=rs.getString("sum(Total)");
						txtIncome.setText(String.valueOf(total));
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Invalid Date!");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
				//set data of products which are sold according to the date to the JTable
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					String sql="SELECT `Product_No`,`Sr_No`, `Products`, `Serial_No`, `Module_No`, `Rate_Rs`, `CGST(%)`, `CGST(Rs)`, `SGST(%)`, `SGST(Rs)`, `GST(%)`, `GST(Rs)`, `Actual_Price`, `Discount(%)`, `Discount(Rs)`, `Quantity`, `Discount_Price`, `Total` FROM `mundheelectronics1`.`productsdata` WHERE Date=? ORDER BY Time DESC";
					ps=con.prepareStatement(sql);
					ps.setString(1,txtDate.getText());
					rs=ps.executeQuery();
					if(rs.next())
					{
					table.setModel(DbUtils.resultSetToTableModel(rs));
					}
					else
					{
					JOptionPane.showMessageDialog(null,"Invalid Date!");	
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
				
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnNewButton.setBounds(222, 20, 120, 28);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//go to the home page on cancel action
				new HomeShop().setVisible(true);
			}
		});
		btnCancel.setForeground(new Color(0, 0, 128));
		btnCancel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnCancel.setBounds(353, 20, 104, 28);
		contentPane.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 98, 1307, 598);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblIncome = new JLabel("Income:");
		lblIncome.setForeground(Color.WHITE);
		lblIncome.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblIncome.setBounds(24, 64, 72, 24);
		contentPane.add(lblIncome);
		
		txtIncome = new JTextField();
		txtIncome.setForeground(new Color(0, 0, 128));
		txtIncome.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtIncome.setColumns(10);
		txtIncome.setBounds(95, 64, 120, 25);
		contentPane.add(txtIncome);
		
		JLabel lblRs = new JLabel("Rs");
		lblRs.setForeground(Color.WHITE);
		lblRs.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs.setBounds(222, 64, 30, 24);
		contentPane.add(lblRs);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
	
	}
}
