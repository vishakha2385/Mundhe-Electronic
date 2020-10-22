import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateBill extends JFrame {

	private JPanel contentPane;
	private JTextField txtTime;
	private JTextField txtSearch;
	private JTextField txtAddress;
	private JTextField txtContact;
	private JLabel lblTotalAmmount;
	private JTextField txtTotalAmmount;
	private JLabel lblRs;
	private JLabel lblPaidAmmount;
	private JTextField txtPaidAmmount;
	private JLabel lblRs_2;
	private JLabel lblPendingAmmount;
	private JTextField txtPendingAmmount;
	private JLabel lblRs_3;
	private JButton btnUpdate;
	private JButton btnCancel;
	private JTable table;
    private JTextField txtInvoiceNo;
    private JTextField txtDate;
    private JTable tblCustomer;
    private JButton btnReset;
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
   
   //show data of products according to the invoice no 
	public void showDataInvoiceNo()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="SELECT `Sr_No`, `Products`, `Serial_No`, `Module_No`, `Rate_Rs`, `CGST(%)`, `CGST(Rs)`, `SGST(%)`, `SGST(Rs)`, `GST(%)`, `GST(Rs)`, `Actual_Price`, `Discount(%)`, `Discount(Rs)`, `Quantity`, `Discount_Price`, `Total` FROM `mundheelectronics1`.`productsdata` WHERE Invoice_No=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,txtInvoiceNo.getText());
			rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//set customer data to the table ordered by ascending
	public void showCustomerInvoices()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select * from CustomerData order by Customer_Name asc";
			ps=con.prepareStatement(sql);
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
					UpdateBill frame = new UpdateBill();
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
	public UpdateBill() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateBill.class.getResource("/images/plug.png")));
		setTitle("Update Payment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel.setBounds(21, 53, 50, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTime.setBounds(188, 54, 50, 24);
		contentPane.add(lblTime);
		
		txtTime = new JTextField();
		txtTime.setForeground(new Color(0, 0, 128));
		txtTime.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTime.setColumns(10);
		txtTime.setBounds(243, 54, 104, 25);
		contentPane.add(txtTime);
		
		JLabel lblInvoiceNo = new JLabel("Invoice No:");
		lblInvoiceNo.setForeground(Color.WHITE);
		lblInvoiceNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblInvoiceNo.setBounds(21, 87, 106, 24);
		contentPane.add(lblInvoiceNo);
		
		JLabel lblCustomerName = new JLabel("Customer:");
		lblCustomerName.setForeground(Color.WHITE);
		lblCustomerName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblCustomerName.setBounds(21, 19, 236, 24);
		contentPane.add(lblCustomerName);
		
		txtSearch = new JTextField();
		txtSearch.setForeground(new Color(0, 0, 128));
		txtSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSearch.setColumns(10);
		txtSearch.setBounds(187, 18, 304, 25);
		contentPane.add(txtSearch);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblAddress.setBounds(21, 122, 143, 24);
		contentPane.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setForeground(new Color(0, 0, 128));
		txtAddress.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtAddress.setColumns(10);
		txtAddress.setBounds(187, 122, 427, 25);
		contentPane.add(txtAddress);
		
		JLabel lblContact = new JLabel("Contact:");
		lblContact.setForeground(Color.WHITE);
		lblContact.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblContact.setBounds(21, 158, 143, 24);
		contentPane.add(lblContact);
		
		txtContact = new JTextField();
		txtContact.setForeground(new Color(0, 0, 128));
		txtContact.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtContact.setColumns(10);
		txtContact.setBounds(187, 158, 199, 25);
		contentPane.add(txtContact);
		
		lblTotalAmmount = new JLabel("Total Amount:");
		lblTotalAmmount.setForeground(Color.WHITE);
		lblTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTotalAmmount.setBounds(21, 192, 134, 24);
		contentPane.add(lblTotalAmmount);
		
		txtTotalAmmount = new JTextField();
		txtTotalAmmount.setForeground(new Color(0, 0, 128));
		txtTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTotalAmmount.setColumns(10);
		txtTotalAmmount.setBounds(187, 192, 104, 25);
		contentPane.add(txtTotalAmmount);
		
		lblRs = new JLabel("Rs");
		lblRs.setForeground(Color.WHITE);
		lblRs.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs.setBounds(295, 192, 26, 24);
		contentPane.add(lblRs);
		
		lblPaidAmmount = new JLabel("Paid Amount:");
		lblPaidAmmount.setForeground(Color.WHITE);
		lblPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPaidAmmount.setBounds(21, 226, 143, 24);
		contentPane.add(lblPaidAmmount);
		
		txtPaidAmmount = new JTextField();
		txtPaidAmmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				//find pending amount
				try {
					String p1=txtPaidAmmount.getText();
					int paid=Integer.parseInt(p1);
					String t5=txtTotalAmmount.getText();
					int total2=Integer.parseInt(t5);
					int pending=total2-paid;
					txtPendingAmmount.setText(String.valueOf(pending));
					}
					catch(NumberFormatException e5) {}
			}
		});
		
		txtPaidAmmount.setForeground(new Color(0, 0, 128));
		txtPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPaidAmmount.setColumns(10);
		txtPaidAmmount.setBounds(187, 226, 104, 25);
		contentPane.add(txtPaidAmmount);
		
		lblRs_2 = new JLabel("Rs");
		lblRs_2.setForeground(Color.WHITE);
		lblRs_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_2.setBounds(295, 226, 26, 24);
		contentPane.add(lblRs_2);
		
		lblPendingAmmount = new JLabel("Pending Amount:");
		lblPendingAmmount.setForeground(Color.WHITE);
		lblPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPendingAmmount.setBounds(21, 257, 171, 24);
		contentPane.add(lblPendingAmmount);
		
		txtPendingAmmount = new JTextField();
		txtPendingAmmount.setForeground(new Color(0, 0, 128));
		txtPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPendingAmmount.setColumns(10);
		txtPendingAmmount.setBounds(187, 257, 104, 25);
		contentPane.add(txtPendingAmmount);
		
		lblRs_3 = new JLabel("Rs");
		lblRs_3.setForeground(Color.WHITE);
		lblRs_3.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3.setBounds(295, 257, 26, 24);
		contentPane.add(lblRs_3);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			//	update customer details and payment also
				try 
				{
				String sql3="update CustomerData set Date=?,Time=?,Customer_Name=?,Address=?,Contact=?,Total_Ammount=?,Paid_Ammount=?,Pending_Ammount=? where Invoice_No=?";
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql3);
				ps.setString(9,txtInvoiceNo.getText());
				ps.setString(1,txtDate.getText());
				ps.setString(2,txtTime.getText());
				ps.setString(3,txtSearch.getText());
				ps.setString(4,txtAddress.getText());
				ps.setString(5,txtContact.getText());
				ps.setString(6,txtTotalAmmount.getText());
				ps.setString(7,txtPaidAmmount.getText());
				ps.setString(8,txtPendingAmmount.getText());

				ps.executeUpdate();
				showCustomerInvoices();
				JOptionPane.showMessageDialog(null,"Payment updated succefully!");
				
				}
				catch(Exception e1) {}
				showDataInvoiceNo();
				showCustomerInvoices();
			}
		});
		btnUpdate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnUpdate.setBounds(188, 298, 117, 28);
		contentPane.add(btnUpdate);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new HomeShop().setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnCancel.setBounds(442, 298, 117, 28);
		contentPane.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 337, 1308, 364);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//set customer invoices according to the customer name to the JTable
				showCustomerInvoices();
				try {
					
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					String s="select *from CustomerData where Customer_Name=?";
				    ps=con.prepareStatement(s);
				    ps.setString(1,txtSearch.getText());
				    rs=ps.executeQuery();
				    if(rs.next())
				    {
				    	tblCustomer.setModel(DbUtils.resultSetToTableModel(rs));
				    }
				    else
				    {
				    	JOptionPane.showMessageDialog(null,"Sorry! this Cutomer is not exist.");
				    }
				    
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
			}
		});
		btnSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnSearch.setBounds(497, 17, 117, 28);
		contentPane.add(btnSearch);
		
		txtInvoiceNo = new JTextField();
		txtInvoiceNo.setForeground(new Color(0, 0, 128));
		txtInvoiceNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtInvoiceNo.setBounds(188, 87, 133, 27);
		contentPane.add(txtInvoiceNo);
		txtInvoiceNo.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setForeground(new Color(0, 0, 128));
		txtDate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtDate.setBounds(81, 57, 100, 25);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		
		JScrollPane scrollPaneCustomer = new JScrollPane();
		scrollPaneCustomer.setBounds(624, 21, 705, 294);
		contentPane.add(scrollPaneCustomer);
		
		tblCustomer = new JTable();
		tblCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				//set JTable data to the JTextFields
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
				
				//set customer details to the JTextFields
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					String s="select *from CustomerData,ProductsData,BillsData where CustomerData.Invoice_No=BillsData.Invoice_No and ProductsData.Product_No=BillsData.Product_No and CustomerData.Invoice_No=?";
				    ps=con.prepareStatement(s);
				    ps.setString(1,txtInvoiceNo.getText());
				    rs=ps.executeQuery();
				    if(rs.next())
				    {
				    	String Date=rs.getString("Date");
						txtDate.setText(Date);
						String Time=rs.getString("Time");
						txtTime.setText(Time);
						String Customer_Name=rs.getString("Customer_Name");
						txtSearch.setText(Customer_Name);
						String Address=rs.getString("Address");
						txtAddress.setText(Address);
						String Contact=rs.getString("Contact");
						txtContact.setText(Contact);
						String Total_Ammount=rs.getString("Total_Ammount");
						txtTotalAmmount.setText(Total_Ammount);
						String Pending_Ammount=rs.getString("Pending_Ammount");
						txtPendingAmmount.setText(Pending_Ammount);
						String Paid_Ammount=rs.getString("Paid_Ammount");
				//		System.out.println(Paid_Ammount);
						txtPaidAmmount.setText(Paid_Ammount);
					
				    }
				    else
				    {
				    //	JOptionPane.showMessageDialog(null,"Sorry! this Invoice is not Exist.");	
				    }
				    
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				showDataInvoiceNo();
				showCustomerInvoices();
			}
		});
		scrollPaneCustomer.setViewportView(tblCustomer);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new UpdateBill().setVisible(true);
			}
		});
		btnReset.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnReset.setBounds(315, 298, 117, 28);
		contentPane.add(btnReset);
		
		//ShowDataCustomer();
		showCustomerInvoices();
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
	}
}
