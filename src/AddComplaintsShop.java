import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddComplaintsShop extends JFrame {

	private JPanel contentPane;
	private JTextField txtCustomerName;
	private JTextField txtAddress;
	private JTextField txtContact;
	private JTextField txtProduct;
	private JTextField txtSerialNo;
	private JTextField txtModuleNo;
	private JTextField txtSearch;
	private JTable table;
    private JComboBox CategorycomboBox;
    
	
	Connection con;
	   PreparedStatement ps;
	   ResultSet rs;
	   private JTextField txtComplaintNo;
	   
	   public void ComplaintsData()
		{
			try {
				DatabaseMetaData d=con.getMetaData();
				ResultSet rs=d.getTables(null,null,"ComplaintsData",null);
				if(rs.next())
				{
	//				JOptionPane.showMessageDialog(null,"ComplaintsData table exist");
				}
				else 
				{
					String Create_Table="create table ComplaintsData(Customer_Name varchar(100),Address varchar(100),Contact varchar(30),Product varchar(100),Serial_No varchar(50),Module_No varchar(50),Complaint_No varchar(50),Category varchar(30))";
					PreparedStatement ps=con.prepareStatement(Create_Table);
					ps.executeUpdate();
	//				JOptionPane.showMessageDialog(null,"ComplaintsData created successfully!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	   
		public void ShowData()
		{
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				String sql="select *from ComplaintsData";
				ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
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
					AddComplaintsShop frame = new AddComplaintsShop();
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
	public AddComplaintsShop()
	{
		
			try 
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				
			} 
			catch(Exception e)
			{
				
			}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddComplaintsShop.class.getResource("/images/logoShop.jpg")));
		setTitle("Complaints");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel.setBounds(30, 29, 298, 29);
		contentPane.add(lblNewLabel);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setForeground(new Color(0, 0, 128));
		txtCustomerName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtCustomerName.setBounds(242, 31, 426, 25);
		contentPane.add(txtCustomerName);
		txtCustomerName.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setForeground(new Color(0, 0, 128));
		txtAddress.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtAddress.setColumns(10);
		txtAddress.setBounds(242, 70, 426, 25);
		contentPane.add(txtAddress);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblAddress.setBounds(30, 68, 144, 29);
		contentPane.add(lblAddress);
		
		txtContact = new JTextField();
		txtContact.setForeground(new Color(0, 0, 128));
		txtContact.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtContact.setColumns(10);
		txtContact.setBounds(242, 109, 183, 25);
		contentPane.add(txtContact);
		
		JLabel lblContact = new JLabel("Contact:");
		lblContact.setForeground(Color.WHITE);
		lblContact.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblContact.setBounds(30, 107, 144, 29);
		contentPane.add(lblContact);
		
		txtProduct = new JTextField();
		txtProduct.setForeground(new Color(0, 0, 128));
		txtProduct.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtProduct.setColumns(10);
		txtProduct.setBounds(242, 146, 183, 25);
		contentPane.add(txtProduct);
		
		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setForeground(Color.WHITE);
		lblProductName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblProductName.setBounds(30, 144, 246, 29);
		contentPane.add(lblProductName);
		
		txtSerialNo = new JTextField();
		txtSerialNo.setForeground(new Color(0, 0, 128));
		txtSerialNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSerialNo.setColumns(10);
		txtSerialNo.setBounds(242, 183, 183, 25);
		contentPane.add(txtSerialNo);
		
		JLabel lblSerialNo = new JLabel("Serial No:");
		lblSerialNo.setForeground(Color.WHITE);
		lblSerialNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblSerialNo.setBounds(30, 181, 144, 29);
		contentPane.add(lblSerialNo);
		
		txtModuleNo = new JTextField();
		txtModuleNo.setForeground(new Color(0, 0, 128));
		txtModuleNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtModuleNo.setColumns(10);
		txtModuleNo.setBounds(242, 220, 183, 25);
		contentPane.add(txtModuleNo);
		
		JLabel lblModuleNo = new JLabel("Module No:");
		lblModuleNo.setForeground(Color.WHITE);
		lblModuleNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblModuleNo.setBounds(30, 218, 144, 29);
		contentPane.add(lblModuleNo);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblCategory.setBounds(30, 301, 144, 29);
		contentPane.add(lblCategory);
		
		CategorycomboBox = new JComboBox();
		CategorycomboBox.setForeground(new Color(0, 0, 128));
		CategorycomboBox.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		CategorycomboBox.setBounds(242, 305, 183, 25);
		CategorycomboBox.addItem("Send");
		CategorycomboBox.addItem("Unsent");
		contentPane.add(CategorycomboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Customer :");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(704, 29, 144, 29);
		contentPane.add(lblNewLabel_1);
		
		txtSearch = new JTextField();
		txtSearch.setForeground(new Color(0, 0, 128));
		txtSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSearch.setColumns(10);
		txtSearch.setBounds(803, 33, 426, 25);
		contentPane.add(txtSearch);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
					String sql5="select *from ComplaintsData where Customer_Name=?";
					ps=con.prepareStatement(sql5);
					ps.setString(1,txtSearch.getText());
					rs=ps.executeQuery();
					if(rs.next())
					{
						
						String Customer_Name=rs.getString("Customer_Name");
						txtCustomerName.setText(Customer_Name);
						String Address=rs.getString("Address");
						txtAddress.setText(Address);
						String Contact=rs.getString("Contact");
						txtContact.setText(Contact);
						String Product_Name=rs.getString("Product");
						txtProduct.setText(Product_Name);
						String Serial_No=rs.getString("Serial_No");
						txtSerialNo.setText(Serial_No);
						String Module_No=rs.getString("Module_No");
						txtModuleNo.setText(Module_No);
						String Complaint_No=rs.getString("Complaint_No");
						txtComplaintNo.setText(Complaint_No);
						String Category=rs.getString("Category");
						CategorycomboBox.setSelectedItem(Category);
						
					}
					else
					{
						String Cname=txtSearch.getText();
						String msg="No any complaint from "+"'"+Cname+"'"+"!";
						JOptionPane.showMessageDialog(null,msg);
					}
				} 
				catch(Exception e1)
				{
					
				}
		
			}
		});
		btnSearch.setForeground(new Color(0, 0, 128));
		btnSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnSearch.setBounds(1239, 31, 103, 28);
		contentPane.add(btnSearch);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
try 
				
				{
				
				String sql1="insert into ComplaintsData values(?,?,?,?,?,?,?,?)";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql1);
				ps.setString(1,txtCustomerName.getText());
		//		System.out.println(txtCustomerName.getText());
				ps.setString(2,txtAddress.getText());
		//		System.out.println(Address.getText());
				ps.setString(3,txtContact.getText());
		//		System.out.println(txtContact.getText());
				ps.setString(4,txtProduct.getText());
		//		System.out.println(txtProduct.getText());
				ps.setString(5,txtSerialNo.getText());
		//		System.out.println(txtSerialNo.getText());
				ps.setString(6,txtModuleNo.getText());
		//		System.out.println(txtModuleNo.getText());
				ps.setString(7,txtComplaintNo.getText());
				String Category=(String) CategorycomboBox.getSelectedItem();
		//		System.out.println(Category);
				ps.setString(8,Category);
				ps.executeUpdate();
				
				txtCustomerName.setText("");
				txtAddress.setText("");
				txtContact.setText("");
				txtProduct.setText("");
				txtSerialNo.setText("");
				txtModuleNo.setText("");
				txtComplaintNo.setText("");
				CategorycomboBox.setSelectedItem("Sent");
				}
				
				catch(Exception e1) 
				{
			//		JOptionPane.showMessageDialog(null,e1);	
				}
	
				ShowData();
			}
		});
		btnAdd.setForeground(new Color(0, 0, 128));
		btnAdd.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnAdd.setBounds(242, 349, 103, 28);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
				String sql3="UPDATE ComplaintsData SET Customer_Name=?,Address=?,Contact=?,Product=?,Serial_No=?,Module_No=?,Complaint_No=?,Category=? WHERE Customer_Name=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql3);
				ps.setString(8,txtCustomerName.getText());
				ps.setString(1,txtCustomerName.getText());
				ps.setString(2,txtAddress.getText());
				ps.setString(3,txtContact.getText());
				ps.setString(4,txtProduct.getText());
				ps.setString(5,txtSerialNo.getText());
				ps.setString(6,txtModuleNo.getText());
				ps.setString(7,txtComplaintNo.getText());
				String s1=(String) CategorycomboBox.getSelectedItem();
				ps.setString(8,s1);
				
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Complaint updated successfully!");
				txtCustomerName.setText("");
				txtAddress.setText("");
				txtContact.setText("");
				txtProduct.setText("");
				txtModuleNo.setText("");
				txtSerialNo.setText("");
				txtComplaintNo.setText("");
				CategorycomboBox.setSelectedItem("Sent");
				}
				catch(Exception e1) {}

				ShowData();
			}
		});
		btnUpdate.setForeground(new Color(0, 0, 128));
		btnUpdate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnUpdate.setBounds(355, 349, 103, 28);
		contentPane.add(btnUpdate);
		
		JButton Delete = new JButton("Delete");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int a=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Complaint?","Delete this Record?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			    if(a==JOptionPane.YES_OPTION)
			    {
			    	try 
					{
					String sql2="DELETE FROM ComplaintsData WHERE Customer_Name=?";	
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					ps=con.prepareStatement(sql2);
					ps.setString(1,txtCustomerName.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Complaint deleted successfully!");

					txtCustomerName.setText("");
					txtAddress.setText("");
					txtContact.setText("");
					txtSerialNo.setText("");
					txtModuleNo.setText("");
					txtProduct.setText("");
					txtComplaintNo.setText("");
					CategorycomboBox.setSelectedItem("Sent");
					}
					catch(Exception e1) {}
			    }
			
				ShowData();
			}
		});
		Delete.setForeground(new Color(0, 0, 128));
		Delete.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		Delete.setBounds(468, 349, 103, 28);
		contentPane.add(Delete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new HomeShop().setVisible(true);
			}
		});
		btnCancel.setForeground(new Color(0, 0, 128));
		btnCancel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnCancel.setBounds(581, 349, 103, 28);
		contentPane.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 387, 1292, 334);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int i=table.getSelectedRow();
				
				txtCustomerName.setText(model.getValueAt(i,0).toString());
				txtAddress.setText(model.getValueAt(i,1).toString());
				txtContact.setText(model.getValueAt(i,2).toString());
				txtProduct.setText(model.getValueAt(i,3).toString());
				txtSerialNo.setText(model.getValueAt(i,4).toString());
				txtModuleNo.setText(model.getValueAt(i,5).toString());
				txtComplaintNo.setText("");
				CategorycomboBox.setSelectedItem(model.getValueAt(i,7).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblComplaintNo = new JLabel("Complaint No:");
		lblComplaintNo.setForeground(Color.WHITE);
		lblComplaintNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblComplaintNo.setBounds(30, 262, 246, 29);
		contentPane.add(lblComplaintNo);
		
		txtComplaintNo = new JTextField();
		txtComplaintNo.setForeground(new Color(0, 0, 128));
		txtComplaintNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtComplaintNo.setColumns(10);
		txtComplaintNo.setBounds(242, 264, 183, 25);
		contentPane.add(txtComplaintNo);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
		
		
		ComplaintsData();
		ShowData();
	}
}
