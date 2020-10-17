import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;

import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class CustomInvoicesList extends JFrame {

	private JPanel contentPane;
	private JTextField txtCustomerName;
	private JTextField txtDate;
	private JTextField txtTime;
	private JTextField txtInvoiceNo;
	private JTextField txtAddress;
	private JTextField txtContact;
	private JTextField txtTotalAmmount;
	private JTextField txtPaidAmmount;
	private JTextField txtPendingAmmount;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomInvoicesList frame = new CustomInvoicesList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public void ShowData()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select *from CustomInvoicesData";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public CustomInvoicesList() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CustomInvoicesList.class.getResource("/images/plug.png")));
		setTitle("Custom Invoices List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCustomerName = new JLabel("Customer:");
		lblCustomerName.setForeground(Color.WHITE);
		lblCustomerName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblCustomerName.setBounds(22, 51, 236, 24);
		contentPane.add(lblCustomerName);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setForeground(new Color(0, 0, 128));
		txtCustomerName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(188, 50, 648, 25);
		contentPane.add(txtCustomerName);
		
		JLabel lblNewLabel = new JLabel("Date:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel.setBounds(22, 15, 50, 24);
		contentPane.add(lblNewLabel);
		
		txtDate = new JTextField();
		txtDate.setForeground(new Color(0, 0, 128));
		txtDate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtDate.setColumns(10);
		txtDate.setBounds(80, 14, 100, 25);
		contentPane.add(txtDate);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTime.setBounds(182, 15, 50, 24);
		contentPane.add(lblTime);
		
		txtTime = new JTextField();
		txtTime.setForeground(new Color(0, 0, 128));
		txtTime.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTime.setColumns(10);
		txtTime.setBounds(242, 16, 104, 25);
		contentPane.add(txtTime);
		
		JLabel lblInvoiceNo = new JLabel("Invoice No:");
		lblInvoiceNo.setForeground(Color.WHITE);
		lblInvoiceNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblInvoiceNo.setBounds(22, 89, 106, 24);
		contentPane.add(lblInvoiceNo);
		
		txtInvoiceNo = new JTextField();
		txtInvoiceNo.setForeground(new Color(0, 0, 128));
		txtInvoiceNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtInvoiceNo.setColumns(10);
		txtInvoiceNo.setBounds(189, 89, 133, 27);
		contentPane.add(txtInvoiceNo);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblAddress.setBounds(22, 124, 143, 24);
		contentPane.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setForeground(new Color(0, 0, 128));
		txtAddress.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtAddress.setColumns(10);
		txtAddress.setBounds(188, 124, 648, 25);
		contentPane.add(txtAddress);
		
		JLabel lblContact = new JLabel("Contact:");
		lblContact.setForeground(Color.WHITE);
		lblContact.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblContact.setBounds(22, 160, 143, 24);
		contentPane.add(lblContact);
		
		txtContact = new JTextField();
		txtContact.setForeground(new Color(0, 0, 128));
		txtContact.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtContact.setColumns(10);
		txtContact.setBounds(188, 160, 199, 25);
		contentPane.add(txtContact);
		
		JLabel lblTotalAmmount = new JLabel("Total Amount:");
		lblTotalAmmount.setForeground(Color.WHITE);
		lblTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTotalAmmount.setBounds(22, 194, 134, 24);
		contentPane.add(lblTotalAmmount);
		
		txtTotalAmmount = new JTextField();
		txtTotalAmmount.setForeground(new Color(0, 0, 128));
		txtTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTotalAmmount.setColumns(10);
		txtTotalAmmount.setBounds(188, 194, 104, 25);
		contentPane.add(txtTotalAmmount);
		
		JLabel lblRs = new JLabel("Rs");
		lblRs.setForeground(Color.WHITE);
		lblRs.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs.setBounds(296, 194, 26, 24);
		contentPane.add(lblRs);
		
		JLabel lblPaidAmmount = new JLabel("Paid Amount:");
		lblPaidAmmount.setForeground(Color.WHITE);
		lblPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPaidAmmount.setBounds(22, 228, 143, 24);
		contentPane.add(lblPaidAmmount);
		
		txtPaidAmmount = new JTextField();
		txtPaidAmmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				String p1=txtPaidAmmount.getText();
				double paid=Double.parseDouble(p1);
				String t5=txtTotalAmmount.getText();
				double total2=Double.parseDouble(t5);
				double pending=total2-paid;
				txtPendingAmmount.setText(String.valueOf(pending));
			}
		});
		txtPaidAmmount.setForeground(new Color(0, 0, 128));
		txtPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPaidAmmount.setColumns(10);
		txtPaidAmmount.setBounds(188, 228, 104, 25);
		contentPane.add(txtPaidAmmount);
		
		JLabel lblRs_2 = new JLabel("Rs");
		lblRs_2.setForeground(Color.WHITE);
		lblRs_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_2.setBounds(296, 228, 26, 24);
		contentPane.add(lblRs_2);
		
		JLabel lblPendingAmmount = new JLabel("Pending Amount:");
		lblPendingAmmount.setForeground(Color.WHITE);
		lblPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPendingAmmount.setBounds(22, 259, 171, 24);
		contentPane.add(lblPendingAmmount);
		
		txtPendingAmmount = new JTextField();
		txtPendingAmmount.setForeground(new Color(0, 0, 128));
		txtPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPendingAmmount.setColumns(10);
		txtPendingAmmount.setBounds(188, 259, 104, 25);
		contentPane.add(txtPendingAmmount);
		
		JLabel lblRs_3 = new JLabel("Rs");
		lblRs_3.setForeground(Color.WHITE);
		lblRs_3.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3.setBounds(296, 259, 26, 24);
		contentPane.add(lblRs_3);
		
		JButton btnAdd = new JButton("Update");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
				String sql3="UPDATE CustomInvoicesData SET Invoice_No=?,Date=?,Time=?,Customer_Name=?,Address=?,Contact=?,Total_amount=?,Paid_Amount=?,Pending_Amount=? WHERE Customer_Name=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql3);
				ps.setString(10,txtCustomerName.getText());
				ps.setString(1,txtInvoiceNo.getText());
				ps.setString(2,txtDate.getText());
				ps.setString(3,txtTime.getText());
				ps.setString(4,txtCustomerName.getText());
				ps.setString(5,txtAddress.getText());
				ps.setString(6,txtContact.getText());
				ps.setString(7,txtTotalAmmount.getText());
				ps.setString(8,txtPaidAmmount.getText());
				ps.setString(9,txtPendingAmmount.getText());

				
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Invoice updated successfully!");
				txtCustomerName.setText("");
				txtAddress.setText("");
				txtContact.setText("");
				txtTotalAmmount.setText("");
				txtPaidAmmount.setText("");
				txtPendingAmmount.setText("");
				txtInvoiceNo.setText("");
				txtDate.setText("");
				txtTime.setText("");

				}
				catch(Exception e1) {}

				ShowData();
			}
		});
		btnAdd.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnAdd.setBounds(189, 300, 117, 28);
		contentPane.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int a=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Complaint?","Delete this Record?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			    if(a==JOptionPane.YES_OPTION)
			    {
			    	try 
					{
					String sql2="DELETE FROM CustomInvoicesData WHERE Customer_Name=?";	
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					ps=con.prepareStatement(sql2);
					ps.setString(1,txtCustomerName.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Invoice deleted successfully!");

					txtCustomerName.setText("");
					txtAddress.setText("");
					txtContact.setText("");
					txtTotalAmmount.setText("");
					txtPaidAmmount.setText("");
					txtPendingAmmount.setText("");
					txtInvoiceNo.setText("");
					txtDate.setText("");
					txtTime.setText("");
					}
					catch(Exception e1) {}
			    }
			
				ShowData();
			}
		});
		btnDelete.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnDelete.setBounds(316, 300, 117, 28);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new HomeShop().setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnCancel.setBounds(570, 300, 117, 28);
		contentPane.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 344, 1309, 361);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int i=table.getSelectedRow();
				
				txtInvoiceNo.setText(model.getValueAt(i,0).toString());
				txtDate.setText(model.getValueAt(i,1).toString());
				txtTime.setText(model.getValueAt(i,2).toString());
				txtCustomerName.setText(model.getValueAt(i,3).toString());
				txtAddress.setText(model.getValueAt(i,4).toString());
				txtContact.setText(model.getValueAt(i,5).toString());
				txtTotalAmmount.setText(model.getValueAt(i,6).toString());
				txtPaidAmmount.setText(model.getValueAt(i,7).toString());
				txtPendingAmmount.setText(model.getValueAt(i,8).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					String sql5="select *from CustomInvoicesData where Customer_Name=?";
					ps=con.prepareStatement(sql5);
					ps.setString(1,txtCustomerName.getText());
					rs=ps.executeQuery();
					if(rs.next())
					{
						String Invoice_No=rs.getString("Invoice_No");
						txtInvoiceNo.setText(Invoice_No);
						String Date=rs.getString("Date");
						txtDate.setText(Date);
						String Time=rs.getString("Time");
						txtTime.setText(Time);
						String Customer_Name=rs.getString("Customer_Name");
						txtCustomerName.setText(Customer_Name);
						String Address=rs.getString("Address");
						txtAddress.setText(Address);
						String Contact=rs.getString("Contact");
						txtContact.setText(Contact);
						String Total_Amount=rs.getString("Total_Amount");
						txtTotalAmmount.setText(Total_Amount);
						String Paid_Amount=rs.getString("Paid_Amount");
						txtPaidAmmount.setText(Paid_Amount);
						String Pending_Amount=rs.getString("Pending_Amount");
						txtPendingAmmount.setText(Pending_Amount);
						
					}
					else
					{
						String Cname=txtCustomerName.getText();
						String msg="No any complaint from "+"'"+Cname+"'"+"!";
						JOptionPane.showMessageDialog(null,msg);
					}
				} 
				catch(Exception e1)
				{
					
				}
		
			}
		});
		btnSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnSearch.setBounds(846, 51, 117, 28);
		contentPane.add(btnSearch);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new CustomInvoicesList().setVisible(true);
			}
		});
		btnReset.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnReset.setBounds(443, 300, 117, 28);
		contentPane.add(btnReset);
		
		ShowData();
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
	}
}
