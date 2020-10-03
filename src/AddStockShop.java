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
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddStockShop extends JFrame {

	private JPanel contentPane;
	private JTextField txtProductName;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	private JTable table;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStockShop frame = new AddStockShop();
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
	   private JTextField ProductSearchtextField;
	   private JTextField txtSerialNo;
	   private JTextField txtModuleNo;
	   
	   public void StockData()
		{
			try {
				DatabaseMetaData d=con.getMetaData();
				ResultSet rs=d.getTables(null,null,"StockData",null);
				if(rs.next())
				{
		//			JOptionPane.showMessageDialog(null,"StockData table exist");
				}
				else 
				{
					String Create_Table="create table StockData(Product_Name varchar(100),Category varchar(30),Price int,Quantity int,Serial_No varchar(50),Module_No varchar(50))";
					PreparedStatement ps=con.prepareStatement(Create_Table);
					ps.executeUpdate();
			//		JOptionPane.showMessageDialog(null,"StockData created successfully!");
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
				String sql="select *from StockData ORDER BY Product_Name ASC";
				ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
			}
			catch (Exception e) 
			{
				
				e.printStackTrace();
			}
		}

	/**
	 * Create the frame.
	 */
	public AddStockShop() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddStockShop.class.getResource("/images/plug.png")));
		setTitle("Add Stock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product Name:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel.setBounds(25, 27, 132, 23);
		contentPane.add(lblNewLabel);
		
		txtProductName = new JTextField();
		txtProductName.setForeground(new Color(0, 0, 128));
		txtProductName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtProductName.setBounds(151, 27, 361, 25);
		contentPane.add(txtProductName);
		txtProductName.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblCategory.setBounds(25, 69, 132, 21);
		contentPane.add(lblCategory);
		
		JComboBox cbCategory = new JComboBox();
		cbCategory.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		cbCategory.setForeground(new Color(0, 0, 128));
		cbCategory.setBounds(151, 67, 125, 25);
		cbCategory.addItem("Electronics");
		cbCategory.addItem("Electricals");
		contentPane.add(cbCategory);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPrice.setBounds(25, 101, 132, 22);
		contentPane.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setForeground(new Color(0, 0, 128));
		txtPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPrice.setColumns(10);
		txtPrice.setBounds(151, 102, 125, 25);
		contentPane.add(txtPrice);
		
		txtQuantity = new JTextField();
		txtQuantity.setForeground(new Color(0, 0, 128));
		txtQuantity.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(151, 137, 125, 25);
		contentPane.add(txtQuantity);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setForeground(Color.WHITE);
		lblQuantity.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblQuantity.setBounds(25, 140, 132, 20);
		contentPane.add(lblQuantity);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 304, 1294, 394);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int i=table.getSelectedRow();
				txtProductName.setText(model.getValueAt(i,0).toString());
				cbCategory.setSelectedItem(model.getValueAt(i,1).toString());
				txtPrice.setText(model.getValueAt(i,2).toString());
				txtQuantity.setText(model.getValueAt(i,3).toString());
				txtSerialNo.setText(model.getValueAt(i,4).toString());
				txtModuleNo.setText(model.getValueAt(i,5).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
				String sql1="insert into StockData values(?,?,?,?,?,?)";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql1);
				ps.setString(1,txtProductName.getText());
				String Category=(String) cbCategory.getSelectedItem();
		
				ps.setString(2,Category);
				ps.setString(3,txtPrice.getText());
				ps.setString(4,txtQuantity.getText());
				ps.setString(5,txtSerialNo.getText());
				ps.setString(6,txtModuleNo.getText());
				
				ps.executeUpdate();
				txtProductName.setText("");
				txtPrice.setText("");
				txtQuantity.setText("");
				txtSerialNo.setText("");
				txtModuleNo.setText("");
				
				}
				catch(Exception e1) 
				{
		//			JOptionPane.showMessageDialog(null,e1);	
				}
				ShowData();
			}
		});
		btnAdd.setForeground(new Color(0, 0, 128));
		btnAdd.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnAdd.setBounds(151, 254, 100, 28);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
				String sql3="UPDATE StockData SET Category=?,Price=?,Quantity=?,Serial_No=?,Module_No=? WHERE Product_Name=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql3);
				ps.setString(6,txtProductName.getText());
				String s1=(String) cbCategory.getSelectedItem();
				ps.setString(1,s1);
				ps.setString(2,txtPrice.getText());
				ps.setString(3,txtQuantity.getText());
				ps.setString(4,txtSerialNo.getText());
				ps.setString(5,txtModuleNo.getText());
				
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Product updated succefully!");

				txtProductName.setText("");
				txtPrice.setText("");
				txtQuantity.setText("");
				txtSerialNo.setText("");
				txtModuleNo.setText("");
				}
				catch(Exception e1) {}
				ShowData();
			}
		});
		btnUpdate.setForeground(new Color(0, 0, 128));
		btnUpdate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnUpdate.setBounds(268, 254, 100, 28);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int a=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Product?","Delete this Record?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			    if(a==JOptionPane.YES_OPTION)
			    {
				try 
				{
				String sql2="DELETE FROM StockData WHERE Product_Name=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql2);
				ps.setString(1,txtProductName.getText());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Product deleted succefully!");
				txtProductName.setText("");
				txtPrice.setText("");
				txtQuantity.setText("");
				txtSerialNo.setText("");
				txtModuleNo.setText("");
				}
				catch(Exception e1) {}
			    }
				ShowData();
			}
		});
		btnDelete.setForeground(new Color(0, 0, 128));
		btnDelete.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnDelete.setBounds(386, 254, 100, 28);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new HomeShop().setVisible(true);
			}
		});
		btnCancel.setForeground(new Color(0, 0, 128));
		btnCancel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnCancel.setBounds(503, 254, 100, 28);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_1 = new JLabel("Product Name:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(729, 27, 132, 22);
		contentPane.add(lblNewLabel_1);
		
		txtSearch = new JTextField();
		txtSearch.setForeground(new Color(0, 0, 128));
		txtSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSearch.setColumns(10);
		txtSearch.setBounds(858, 26, 361, 25);
		contentPane.add(txtSearch);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					String sql5="select *from StockData where Product_Name=?";
					ps=con.prepareStatement(sql5);
					ps.setString(1,txtSearch.getText());
					rs=ps.executeQuery();
					if(rs.next())
					{
						String Product_Name=rs.getString("Product_Name");
						txtProductName.setText(Product_Name);
						String Category=rs.getString("Category");
						cbCategory.setSelectedItem(Category);
						String Unit_Price=rs.getString("Price");
						txtPrice.setText(Unit_Price);
						String Quantity=rs.getString("Quantity");
						txtQuantity.setText(Quantity);
						String SerialNo=rs.getString("Serial_No");
						txtSerialNo.setText(SerialNo);
						String ModuleNo=rs.getString("Module_No");
						txtModuleNo.setText(ModuleNo);
						
					}
					else
					{
						String pname=txtSearch.getText();
						String msg="Sorry! No any product exist like "+"'"+pname+"'";
						JOptionPane.showMessageDialog(null,msg);
					}
				} 
				catch(Exception e2)
				{
					
				}
			}
		});
		btnSearch.setForeground(new Color(0, 0, 128));
		btnSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnSearch.setBounds(1229, 25, 90, 28);
		contentPane.add(btnSearch);
		
		JLabel lblRs = new JLabel("Rs");
		lblRs.setForeground(Color.WHITE);
		lblRs.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs.setBounds(278, 101, 33, 26);
		contentPane.add(lblRs);
		
		JLabel lblUnits = new JLabel("Units");
		lblUnits.setForeground(Color.WHITE);
		lblUnits.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblUnits.setBounds(278, 137, 47, 23);
		contentPane.add(lblUnits);
		
		JLabel lblSerialNo = new JLabel("Serial No:");
		lblSerialNo.setForeground(Color.WHITE);
		lblSerialNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblSerialNo.setBounds(25, 173, 132, 20);
		contentPane.add(lblSerialNo);
		
		txtSerialNo = new JTextField();
		txtSerialNo.setForeground(new Color(0, 0, 128));
		txtSerialNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSerialNo.setColumns(10);
		txtSerialNo.setBounds(151, 170,230, 25);
		contentPane.add(txtSerialNo);
		
		JLabel lblModuleNo = new JLabel("Module No:");
		lblModuleNo.setForeground(Color.WHITE);
		lblModuleNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblModuleNo.setBounds(25, 208, 132, 20);
		contentPane.add(lblModuleNo);
		
		txtModuleNo = new JTextField();
		txtModuleNo.setForeground(new Color(0, 0, 128));
		txtModuleNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtModuleNo.setColumns(10);
		txtModuleNo.setBounds(151, 205,230, 25);
		contentPane.add(txtModuleNo);
		
		ShowData();
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
		
		StockData();
	}
}
