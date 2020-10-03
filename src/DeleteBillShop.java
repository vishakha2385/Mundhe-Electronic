import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeleteBillShop extends JFrame {

	private JPanel contentPane;
	private JTextField txtInvoiceNo;
	private JTextField txtDate;
	private JTextField txtTime;
	private JTextField txtSearch;
	private JTextField txtAddress;
	private JTextField txtContact;
	private JTextField txtTotalAmmount;
	private JTextField txtPaidAmmount;
	private JTextField txtPendingAmmount;
	private JTable table;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	private JTable tblCustomer;
	
	public void total()
	{
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select sum(Total) from ProductsData where Invoice_No=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1,txtInvoiceNo.getText());
			rs=ps.executeQuery();
			if(rs.next())
			{
				String sum=rs.getString("sum(Total)");
				
				txtTotalAmmount.setText(sum);
				
			}
			
		} 
		catch(NumberFormatException | SQLException e) 
		{
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	public void ShowDataInvoiceNo()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select Product_No,Sr_No,Category,Products,Serial_No,Module_No,Rate_Rs,Discount,Quantity,Discount_Price,Total from ProductsData where Invoice_No=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,txtInvoiceNo.getText());
			rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ShowCustomerInvoices()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select * from CustomerData where Customer_Name=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,txtSearch.getText());
			rs=ps.executeQuery();
			tblCustomer.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteBillShop frame = new DeleteBillShop();
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
	public DeleteBillShop() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteBillShop.class.getResource("/images/plug.png")));
		setTitle("Delete Invoices");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInvoiceNo = new JLabel("Invoice No:");
		lblInvoiceNo.setForeground(Color.WHITE);
		lblInvoiceNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblInvoiceNo.setBounds(22, 93, 106, 24);
		contentPane.add(lblInvoiceNo);
		
		txtInvoiceNo = new JTextField();
		txtInvoiceNo.setForeground(new Color(0, 0, 128));
		txtInvoiceNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtInvoiceNo.setColumns(10);
		txtInvoiceNo.setBounds(189, 88, 159, 25);
		contentPane.add(txtInvoiceNo);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ShowCustomerInvoices();

			}
		});
		
		btnSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnSearch.setBounds(498, 20, 117, 28);
		contentPane.add(btnSearch);
		
		JLabel lblNewLabel = new JLabel("Date:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel.setBounds(22, 54, 50, 24);
		contentPane.add(lblNewLabel);
		
		txtDate = new JTextField();
		txtDate.setForeground(new Color(0, 0, 128));
		txtDate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtDate.setColumns(10);
		txtDate.setBounds(82, 58, 100, 25);
		contentPane.add(txtDate);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTime.setBounds(189, 55, 50, 24);
		contentPane.add(lblTime);
		
		txtTime = new JTextField();
		txtTime.setForeground(new Color(0, 0, 128));
		txtTime.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTime.setColumns(10);
		txtTime.setBounds(244, 55, 104, 25);
		contentPane.add(txtTime);
		
		JLabel lblCustomerName = new JLabel("Customer:");
		lblCustomerName.setForeground(Color.WHITE);
		lblCustomerName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblCustomerName.setBounds(22, 21, 143, 24);
		contentPane.add(lblCustomerName);
		
		txtSearch = new JTextField();
		txtSearch.setForeground(new Color(0, 0, 128));
		txtSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSearch.setColumns(10);
		txtSearch.setBounds(188, 20, 300, 25);
		contentPane.add(txtSearch);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblAddress.setBounds(22, 123, 143, 24);
		contentPane.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setForeground(new Color(0, 0, 128));
		txtAddress.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtAddress.setColumns(10);
		txtAddress.setBounds(188, 123, 427, 25);
		contentPane.add(txtAddress);
		
		JLabel lblContact = new JLabel("Contact:");
		lblContact.setForeground(Color.WHITE);
		lblContact.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblContact.setBounds(22, 159, 143, 24);
		contentPane.add(lblContact);
		
		txtContact = new JTextField();
		txtContact.setForeground(new Color(0, 0, 128));
		txtContact.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtContact.setColumns(10);
		txtContact.setBounds(188, 159, 199, 25);
		contentPane.add(txtContact);
		
		JLabel lblTotalAmmount = new JLabel("Total Amount:");
		lblTotalAmmount.setForeground(Color.WHITE);
		lblTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTotalAmmount.setBounds(22, 193, 134, 24);
		contentPane.add(lblTotalAmmount);
		
		txtTotalAmmount = new JTextField();
		txtTotalAmmount.setForeground(new Color(0, 0, 128));
		txtTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTotalAmmount.setColumns(10);
		txtTotalAmmount.setBounds(188, 193, 104, 25);
		contentPane.add(txtTotalAmmount);
		
		JLabel lblRs = new JLabel("Rs");
		lblRs.setForeground(Color.WHITE);
		lblRs.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs.setBounds(296, 193, 26, 24);
		contentPane.add(lblRs);
		
		JLabel lblPaidAmmount = new JLabel("Paid Amount:");
		lblPaidAmmount.setForeground(Color.WHITE);
		lblPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPaidAmmount.setBounds(22, 227, 143, 24);
		contentPane.add(lblPaidAmmount);
		
		txtPaidAmmount = new JTextField();
		txtPaidAmmount.setForeground(new Color(0, 0, 128));
		txtPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPaidAmmount.setColumns(10);
		txtPaidAmmount.setBounds(188, 227, 104, 25);
		contentPane.add(txtPaidAmmount);
		
		JLabel lblRs_2 = new JLabel("Rs");
		lblRs_2.setForeground(Color.WHITE);
		lblRs_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_2.setBounds(296, 227, 26, 24);
		contentPane.add(lblRs_2);
		
		JLabel lblPendingAmmount = new JLabel("Pending Amount:");
		lblPendingAmmount.setForeground(Color.WHITE);
		lblPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPendingAmmount.setBounds(22, 258, 171, 24);
		contentPane.add(lblPendingAmmount);
		
		txtPendingAmmount = new JTextField();
		txtPendingAmmount.setForeground(new Color(0, 0, 128));
		txtPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPendingAmmount.setColumns(10);
		txtPendingAmmount.setBounds(188, 258, 104, 25);
		contentPane.add(txtPendingAmmount);
		
		JLabel lblRs_3 = new JLabel("Rs");
		lblRs_3.setForeground(Color.WHITE);
		lblRs_3.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3.setBounds(296, 258, 26, 24);
		contentPane.add(lblRs_3);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int a=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Invoice","Delete this Record?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			    if(a==JOptionPane.YES_OPTION)
			    {
			    	try 
					{
					String sql2="DELETE FROM CustomerData WHERE Invoice_No=?";	
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					ps=con.prepareStatement(sql2);
					ps.setString(1,txtInvoiceNo.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Invoice deleted succefully!");
					txtInvoiceNo.setText("");
					txtDate.setText("");
					txtTime.setText("");
					txtSearch.setText("");
					txtAddress.setText("");
					txtContact.setText("");
					txtTotalAmmount.setText("");
					txtPaidAmmount.setText("");
					txtPendingAmmount.setText("");
					}
					catch(Exception e1) {}
			    	
			    	try 
					{
					String sql2="DELETE FROM ProductsData WHERE Invoice_No=?";	
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					ps=con.prepareStatement(sql2);
					ps.setString(1,txtInvoiceNo.getText());
					ps.executeUpdate();
			//		JOptionPane.showMessageDialog(null,"successfully deleted from Products");
					}
					catch(Exception e1) {}
			    	
			    	try 
					{
					String sql2="DELETE FROM BillsData WHERE Invoice_No=?";	
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					ps=con.prepareStatement(sql2);
					ps.setString(1,txtInvoiceNo.getText());
					ps.executeUpdate();
		//			JOptionPane.showMessageDialog(null,"successfully deleted from Bills");
					}
					catch(Exception e1) {}
			    }
				
			    ShowDataInvoiceNo();
			    ShowCustomerInvoices();
			}
		});
		btnDelete.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnDelete.setBounds(189, 299, 117, 28);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new HomeShop().setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnCancel.setBounds(316, 299, 117, 28);
		contentPane.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 338, 1308, 372);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPaneCustomer = new JScrollPane();
		scrollPaneCustomer.setBounds(625, 22, 705, 302);
		contentPane.add(scrollPaneCustomer);
		
		tblCustomer = new JTable();
		tblCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				DefaultTableModel model=(DefaultTableModel)tblCustomer.getModel();
				int i=tblCustomer.getSelectedRow();
				txtInvoiceNo.setText(model.getValueAt(i,0).toString());
				txtDate.setText(model.getValueAt(i,1).toString());
				txtTime.setText(model.getValueAt(i,2).toString());
				txtSearch.setText(model.getValueAt(i,2).toString());
				txtAddress.setText(model.getValueAt(i,2).toString());
				txtContact.setText(model.getValueAt(i,2).toString());
				txtTotalAmmount.setText(model.getValueAt(i,4).toString());
				txtPaidAmmount.setText(model.getValueAt(i,5).toString());
				txtPendingAmmount.setText(model.getValueAt(i,6).toString());
				
				ShowDataInvoiceNo();
				
//				try {
//					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
//					String s="select *from ProductsData where Invoice_No=?";
//				    ps=con.prepareStatement(s);
//				    ps.setString(1,txtInvoiceNo.getText());
//				    rs=ps.executeQuery();
//				    if(rs.next())
//				    {
//				    	String Date=rs.getString("Date");
//						txtDate.setText(Date);
//						String Time=rs.getString("Time");
//						txtTime.setText(Time);
//						String Customer_Name=rs.getString("Customer_Name");
//						txtSearch.setText(Customer_Name);
//						String Address=rs.getString("Address");
//						txtAddress.setText(Address);
//						String Contact=rs.getString("Contact");
//						txtContact.setText(Contact);
//						String Total_Ammount=rs.getString("Total_Ammount");
//						txtTotalAmmount.setText(Total_Ammount);
//						String Pending_Ammount=rs.getString("Pending_Ammount");
//						txtPendingAmmount.setText(Pending_Ammount);
//						String Paid_Ammount=rs.getString("Paid_Ammount");
//				//		System.out.println(Paid_Ammount);
//						txtPaidAmmount.setText(Paid_Ammount);
//					
//				    }
//				    else
//				    {
//				    //	JOptionPane.showMessageDialog(null,"Sorry! this Invoice is not Exist.");	
//				    }
//				    
//				} catch (SQLException e3) {
//					// TODO Auto-generated catch block
//					e3.printStackTrace();
//				}
		//		ShowDataInvoiceNo();
		//		ShowDataCustomer();
			}
		});
		scrollPaneCustomer.setViewportView(tblCustomer);
		
//		ShowDataCustomer();
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
	
		
	}
}
