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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    private JComboBox cbCategory;
    private JTextField txtComplaintNo;
	
    Connection con;
	PreparedStatement ps;
	ResultSet rs; 
	String search;
	
	// create table ComplaintsData in MySql Database where database name is MundheElectronics1 

	public void createTableComplaintsData()
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
	
	// fetch data from ComplaintsData table from database and show that data in JTable
	   
		public void showData()
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
		
		//create method clear() for clear the JTextField on particular actions
		
		public void clear()
		{
			txtCustomerName.setText("");
			txtAddress.setText("");
			txtContact.setText("");
			txtProduct.setText("");
			txtSerialNo.setText("");
			txtModuleNo.setText("");
			txtComplaintNo.setText("");
			CategorycomboBox.setSelectedItem("Sent");
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
			catch(Exception e) {}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddComplaintsShop.class.getResource("/images/plug.png")));
		setTitle("Complaints");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer:");
		lblNewLabel.setBounds(30, 29, 298, 29);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		txtCustomerName = new JTextField();
		txtCustomerName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					txtAddress.requestFocus();
				}
			}
		});
		txtCustomerName.setBounds(242, 31, 426, 25);
		txtCustomerName.setForeground(new Color(0, 0, 128));
		txtCustomerName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		contentPane.add(txtCustomerName);
		txtCustomerName.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					txtContact.requestFocus();
				}
			}
		});
		txtAddress.setBounds(242, 70, 426, 25);
		txtAddress.setForeground(new Color(0, 0, 128));
		txtAddress.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtAddress.setColumns(10);
		contentPane.add(txtAddress);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(30, 68, 144, 29);
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		contentPane.add(lblAddress);
		
		txtContact = new JTextField();
		txtContact.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					txtProduct.requestFocus();
				}
			}
		});
		txtContact.setBounds(242, 109, 183, 25);
		txtContact.setForeground(new Color(0, 0, 128));
		txtContact.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtContact.setColumns(10);
		contentPane.add(txtContact);
		
		JLabel lblContact = new JLabel("Contact:");
		lblContact.setBounds(30, 107, 144, 29);
		lblContact.setForeground(Color.WHITE);
		lblContact.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		contentPane.add(lblContact);
		
		txtProduct = new JTextField();
		txtProduct.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					txtSerialNo.requestFocus();
				}
			}
		});
		txtProduct.setBounds(242, 146, 426, 25);
		txtProduct.setForeground(new Color(0, 0, 128));
		txtProduct.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtProduct.setColumns(10);
		contentPane.add(txtProduct);
		
		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setBounds(30, 144, 246, 29);
		lblProductName.setForeground(Color.WHITE);
		lblProductName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		contentPane.add(lblProductName);
		
		txtSerialNo = new JTextField();
		txtSerialNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					txtModuleNo.requestFocus();
				}
			}
		});
		txtSerialNo.setBounds(242, 183, 426, 25);
		txtSerialNo.setForeground(new Color(0, 0, 128));
		txtSerialNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSerialNo.setColumns(10);
		contentPane.add(txtSerialNo);
		
		JLabel lblSerialNo = new JLabel("Serial No:");
		lblSerialNo.setBounds(30, 181, 144, 29);
		lblSerialNo.setForeground(Color.WHITE);
		lblSerialNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		contentPane.add(lblSerialNo);
		
		txtModuleNo = new JTextField();
		txtModuleNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					txtComplaintNo.requestFocus();
				}
			}
		});
		txtModuleNo.setBounds(242, 220, 426, 25);
		txtModuleNo.setForeground(new Color(0, 0, 128));
		txtModuleNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtModuleNo.setColumns(10);
		contentPane.add(txtModuleNo);
		
		JLabel lblModuleNo = new JLabel("Module No:");
		lblModuleNo.setBounds(30, 218, 144, 29);
		lblModuleNo.setForeground(Color.WHITE);
		lblModuleNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		contentPane.add(lblModuleNo);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(30, 294, 144, 29);
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		contentPane.add(lblCategory);
		
		CategorycomboBox = new JComboBox();
		CategorycomboBox.setBounds(242, 298, 183, 25);
		CategorycomboBox.setForeground(new Color(0, 0, 128));
		CategorycomboBox.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		CategorycomboBox.addItem("Send");
		CategorycomboBox.addItem("Unsent");
		contentPane.add(CategorycomboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Customer :");
		lblNewLabel_1.setBounds(704, 29, 144, 29);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(801, 30, 426, 25);
		txtSearch.setForeground(new Color(0, 0, 128));
		txtSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSearch.setColumns(10);
		contentPane.add(txtSearch);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(1239, 31, 103, 28);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// search data from database
				try 
				{  
					String sql="select *from ComplaintsData where Customer_Name=?";
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setString(1,txtSearch.getText());	
					ResultSet rs=ps.executeQuery();	
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
				catch(Exception e1){}
				}
		});
		btnSearch.setForeground(new Color(0, 0, 128));
		btnSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		contentPane.add(btnSearch);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(242, 335, 103, 28);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//add data to the ComplaintsData table in database
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
				clear();
				
				}
				catch(Exception e1) 
				{
					JOptionPane.showMessageDialog(null,e1);	
				}
	
				showData();
			}
		});
		btnAdd.setForeground(new Color(0, 0, 128));
		btnAdd.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(355, 335, 103, 28);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Update data in database
				try 
				{
				String sql3="UPDATE ComplaintsData SET Customer_Name=?,Address=?,Contact=?,Product=?,Serial_No=?,Module_No=?,Complaint_No=?,Category=? WHERE Customer_Name=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql3);
				ps.setString(9,txtCustomerName.getText());
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
				clear();
				}
				catch(Exception e1) {}
				showData();
			}
		});
		btnUpdate.setForeground(new Color(0, 0, 128));
		btnUpdate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		contentPane.add(btnUpdate);
		
		JButton Delete = new JButton("Delete");
		Delete.setBounds(468, 335, 103, 28);
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//delete data from database
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

					clear();
					}
					catch(Exception e1) {}
			    }
				showData();
			}
		});
		Delete.setForeground(new Color(0, 0, 128));
		Delete.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		contentPane.add(Delete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(695, 335, 103, 28);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//go to the home page on cancel action
				new HomeShop().setVisible(true);
			}
		});
		btnCancel.setForeground(new Color(0, 0, 128));
		btnCancel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		contentPane.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 373, 1292, 348);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				//set JTable to the JTextFields
				
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int i=table.getSelectedRow();
				
				txtCustomerName.setText(model.getValueAt(i,0).toString());
				txtAddress.setText(model.getValueAt(i,1).toString());
				txtContact.setText(model.getValueAt(i,2).toString());
				txtProduct.setText(model.getValueAt(i,3).toString());
				txtSerialNo.setText(model.getValueAt(i,4).toString());
				txtModuleNo.setText(model.getValueAt(i,5).toString());
				txtComplaintNo.setText(model.getValueAt(i,6).toString());
				CategorycomboBox.setSelectedItem(model.getValueAt(i,7).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblComplaintNo = new JLabel("Complaint No:");
		lblComplaintNo.setBounds(30, 255, 246, 29);
		lblComplaintNo.setForeground(Color.WHITE);
		lblComplaintNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		contentPane.add(lblComplaintNo);
		
		txtComplaintNo = new JTextField();
		txtComplaintNo.setBounds(242, 257, 183, 25);
		txtComplaintNo.setForeground(new Color(0, 0, 128));
		txtComplaintNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtComplaintNo.setColumns(10);
		contentPane.add(txtComplaintNo);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//open the new fresh page
				new AddComplaintsShop().setVisible(true);
			}
		});
		btnReset.setForeground(new Color(0, 0, 128));
		btnReset.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnReset.setBounds(581, 335, 103, 28);
		contentPane.add(btnReset);
		
	    createTableComplaintsData();
		showData();
		
		JLabel lblNewLabel_3 = new JLabel("Background image");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
	
	}
}
