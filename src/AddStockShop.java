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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddStockShop extends JFrame {

	private JPanel contentPane;
	private JTextField txtProductName;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	private JTable table;
	private JTextField txtSearch;
	private JTextField ProductSearchtextField;
	private JTextField txtSerialNo;
	private JTextField txtModuleNo;
	private JTextField txtCGST;
	private JTextField txtSGST;
	private JTextField txtGST;
	private JTextField txtActualPrice;
	private JTextField txtGSTPrice;
	private JTextField txtSGSTPrice;
	private JTextField txtCGSTPrice;
	private JComboBox cbCategory;
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	//create table StockData in database
	   public void stockData()
		{
			try {
				DatabaseMetaData d=con.getMetaData();
				ResultSet rs=d.getTables(null,null,"StockData",null);
				if(rs.next())
				{
			//		JOptionPane.showMessageDialog(null,"StockData table exist");
				}
				else 
				{
					String Create_Table="create table StockData(Product_Name varchar(100),Category varchar(30),Price float,CGST float,CGST_Price float,SGST float,SGST_Price float,GST float,GST_Price float,Actual_Price float,Quantity int,Serial_No varchar(50),Module_No varchar(50))";
					PreparedStatement ps=con.prepareStatement(Create_Table);
					ps.executeUpdate();
			//		JOptionPane.showMessageDialog(null,"StockData created successfully!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	   
	   //fetch data from database of StockData table and show in JTable
		public void showData()
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
		
		//create method clear
		public void clear()
		{
			txtProductName.setText("");
			cbCategory.setSelectedItem("Electronics");
			txtPrice.setText("");
			txtCGSTPrice.setText("");
			txtCGST.setText("");
			txtSGSTPrice.setText("");
			txtSGST.setText("");
			txtGST.setText("");
			txtGSTPrice.setText("");
			txtActualPrice.setText("");
			txtQuantity.setText("");
			txtSerialNo.setText("");
			txtModuleNo.setText("");
		}
		
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
		lblNewLabel.setBounds(25, 20, 132, 25);
		contentPane.add(lblNewLabel);
		
		txtProductName = new JTextField();
		txtProductName.setForeground(new Color(0, 0, 128));
		txtProductName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtProductName.setBounds(151, 20, 361, 25);
		contentPane.add(txtProductName);
		txtProductName.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblCategory.setBounds(25, 49, 132, 26);
		contentPane.add(lblCategory);
		
		JComboBox cbCategory = new JComboBox();
		cbCategory.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		cbCategory.setForeground(new Color(0, 0, 128));
		cbCategory.setBounds(151, 50, 125, 25);
		cbCategory.addItem("Electronics");
		cbCategory.addItem("Electricals");
		contentPane.add(cbCategory);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPrice.setBounds(25, 80, 132, 22);
		contentPane.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setForeground(new Color(0, 0, 128));
		txtPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPrice.setColumns(10);
		txtPrice.setBounds(151, 80, 125, 25);
		contentPane.add(txtPrice);
		
		txtQuantity = new JTextField();
		txtQuantity.setForeground(new Color(0, 0, 128));
		txtQuantity.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(151, 230, 125, 25);
		contentPane.add(txtQuantity);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setForeground(Color.WHITE);
		lblQuantity.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblQuantity.setBounds(25, 235, 132, 20);
		contentPane.add(lblQuantity);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 362, 1294, 336);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				//set JTable data to the JTextFields and JComboBox
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int i=table.getSelectedRow();
				txtProductName.setText(model.getValueAt(i,0).toString());
				cbCategory.setSelectedItem(model.getValueAt(i,1).toString());
				txtPrice.setText(model.getValueAt(i,2).toString());
				txtCGST.setText(model.getValueAt(i,3).toString());
				txtCGSTPrice.setText(model.getValueAt(i,4).toString());
				txtSGST.setText(model.getValueAt(i,5).toString());
				txtSGSTPrice.setText(model.getValueAt(i,6).toString());
				txtGST.setText(model.getValueAt(i,7).toString());
				txtGSTPrice.setText(model.getValueAt(i,8).toString());
				txtActualPrice.setText(model.getValueAt(i,9).toString());
				txtQuantity.setText(model.getValueAt(i,10).toString());
				txtSerialNo.setText(model.getValueAt(i,11).toString());
				txtModuleNo.setText(model.getValueAt(i,12).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//insert data into the StockData in database
				try 
				{
				String sql1="insert into StockData values(?,?,?,?,?,?,?,?,?,?,?,?,?)";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql1);
				ps.setString(1,txtProductName.getText());
				String Category=(String) cbCategory.getSelectedItem();
				ps.setString(2,Category);
				ps.setString(3,txtPrice.getText());
				ps.setString(4,txtCGST.getText());
				ps.setString(5,txtCGSTPrice.getText());
				ps.setString(6,txtSGST.getText());
				ps.setString(7,txtSGSTPrice.getText());
				ps.setString(8,txtGST.getText());
				ps.setString(9,txtGSTPrice.getText());
				ps.setString(10,txtActualPrice.getText());
				ps.setString(11,txtQuantity.getText());
				ps.setString(12,txtSerialNo.getText());
				ps.setString(13,txtModuleNo.getText());
				
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
		btnAdd.setBounds(151, 325, 100, 28);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//update data in database
				try 
				{
				String sql3="UPDATE StockData SET Category=?,Price=?,CGST=?,CGST_Price=?,SGST=?,SGST_Price=?,GST=?,GST_Price=?,Actual_Price=?,Quantity=?,Serial_No=?,Module_No=? WHERE Product_Name=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql3);
				ps.setString(13,txtProductName.getText());
				String s1=(String) cbCategory.getSelectedItem();
				ps.setString(1,s1);
				ps.setString(2,txtPrice.getText());
				ps.setString(3,txtCGST.getText());
				ps.setString(4,txtCGSTPrice.getText());
				ps.setString(5,txtSGST.getText());	
				ps.setString(6,txtSGSTPrice.getText());
				ps.setString(7,txtGST.getText());
				ps.setString(8,txtGSTPrice.getText());
				ps.setString(9,txtActualPrice.getText());
				ps.setString(10,txtQuantity.getText());	
				ps.setString(11,txtSerialNo.getText());
				ps.setString(12,txtModuleNo.getText());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Product updated succefully!");
				clear();
				}
				catch(Exception e1) {}
				showData();
			}
		});
		btnUpdate.setForeground(new Color(0, 0, 128));
		btnUpdate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnUpdate.setBounds(268, 325, 100, 28);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//delete data from database
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
				clear();
				}
				catch(Exception e1) {}
			    }
				showData();
			}
		});
		btnDelete.setForeground(new Color(0, 0, 128));
		btnDelete.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnDelete.setBounds(386, 325, 100, 28);
		contentPane.add(btnDelete);
		
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
		btnCancel.setBounds(624, 325, 100, 28);
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
				//search data from database and fetch data then show in JTextFields and JComboBox
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
						String CGST=rs.getString("CGST");
						txtCGST.setText(CGST);
						String CGSTPrice=rs.getString("CGST_Price");
						txtCGSTPrice.setText(CGSTPrice);
						String SGST=rs.getString("SGST");
						txtSGST.setText(SGST);
						String SGSTPrice=rs.getString("SGST_Price");
						txtSGSTPrice.setText(SGSTPrice);
						String GST=rs.getString("GST");
						txtGST.setText(GST);
						String GSTPrice=rs.getString("GST_Price");
						txtGSTPrice.setText(GSTPrice);
						String ActualPrice=rs.getString("Actual_Price");
						txtActualPrice.setText(ActualPrice);
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
				catch(Exception e2){}
			}
		});
		btnSearch.setForeground(new Color(0, 0, 128));
		btnSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnSearch.setBounds(1229, 25, 90, 28);
		contentPane.add(btnSearch);
		
		JLabel lblRs = new JLabel("Rs");
		lblRs.setForeground(Color.WHITE);
		lblRs.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs.setBounds(278, 80, 33, 26);
		contentPane.add(lblRs);
		
		JLabel lblUnits = new JLabel("Units");
		lblUnits.setForeground(Color.WHITE);
		lblUnits.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblUnits.setBounds(278, 232, 47, 23);
		contentPane.add(lblUnits);
		
		JLabel lblSerialNo = new JLabel("Serial No:");
		lblSerialNo.setForeground(Color.WHITE);
		lblSerialNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblSerialNo.setBounds(24, 260, 132, 25);
		contentPane.add(lblSerialNo);
		
		txtSerialNo = new JTextField();
		txtSerialNo.setForeground(new Color(0, 0, 128));
		txtSerialNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSerialNo.setColumns(10);
		txtSerialNo.setBounds(151, 260,230, 25);
		contentPane.add(txtSerialNo);
		
		JLabel lblModuleNo = new JLabel("Module No:");
		lblModuleNo.setForeground(Color.WHITE);
		lblModuleNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblModuleNo.setBounds(24, 290, 132, 25);
		contentPane.add(lblModuleNo);
		
		txtModuleNo = new JTextField();
		txtModuleNo.setForeground(new Color(0, 0, 128));
		txtModuleNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtModuleNo.setColumns(10);
		txtModuleNo.setBounds(151, 290,230, 25);
		contentPane.add(txtModuleNo);
		
		JLabel lblCgst = new JLabel("CGST:");
		lblCgst.setForeground(Color.WHITE);
		lblCgst.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblCgst.setBounds(23, 110, 132, 22);
		contentPane.add(lblCgst);
		
		txtCGST = new JTextField();
		txtCGST.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				//show automated CGST,SGST and Total GST
				String price=txtPrice.getText();
				float Price1=Float.parseFloat(price);
				txtSGST.setText(txtCGST.getText());
				String CGST=txtCGST.getText();
				float cgst=Float.parseFloat(CGST);
				float gst=2*cgst;
				txtGST.setText(String.valueOf(gst));
				float GSTPrice=(gst/100)*Price1;
				txtGSTPrice.setText(String.valueOf(GSTPrice));
				float CgstPrice=GSTPrice/2;
				txtCGSTPrice.setText(String.valueOf(CgstPrice));
				txtSGSTPrice.setText(String.valueOf(CgstPrice));
				float ActualPrice=Price1-GSTPrice;
				txtActualPrice.setText(String.valueOf(ActualPrice));
			}
		});
		txtCGST.setForeground(new Color(0, 0, 128));
		txtCGST.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtCGST.setColumns(10);
		txtCGST.setBounds(151, 110, 125, 25);
		contentPane.add(txtCGST);
		
		JLabel lblRs_1 = new JLabel("%");
		lblRs_1.setForeground(Color.WHITE);
		lblRs_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_1.setBounds(278, 110, 33, 26);
		contentPane.add(lblRs_1);
		
		JLabel lblSgst = new JLabel("SGST");
		lblSgst.setForeground(Color.WHITE);
		lblSgst.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblSgst.setBounds(23, 140, 132, 22);
		contentPane.add(lblSgst);
		
		txtSGST = new JTextField();
		txtSGST.setForeground(new Color(0, 0, 128));
		txtSGST.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSGST.setColumns(10);
		txtSGST.setBounds(151, 140, 125, 25);
		contentPane.add(txtSGST);
		
		JLabel lblRs_2 = new JLabel("%");
		lblRs_2.setForeground(Color.WHITE);
		lblRs_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_2.setBounds(278, 140, 33, 26);
		contentPane.add(lblRs_2);
		
		JLabel lblGst = new JLabel("GST");
		lblGst.setForeground(Color.WHITE);
		lblGst.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblGst.setBounds(23, 170, 132, 22);
		contentPane.add(lblGst);
		
		txtGST = new JTextField();
		txtGST.setForeground(new Color(0, 0, 128));
		txtGST.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtGST.setColumns(10);
		txtGST.setBounds(151, 170, 125, 25);
		contentPane.add(txtGST);
		
		JLabel lblRs_3 = new JLabel("%");
		lblRs_3.setForeground(Color.WHITE);
		lblRs_3.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3.setBounds(278, 170, 33, 26);
		contentPane.add(lblRs_3);
		
		JLabel lblActualPrice = new JLabel("Actual Price:");
		lblActualPrice.setForeground(Color.WHITE);
		lblActualPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblActualPrice.setBounds(25, 200, 170, 26);
		contentPane.add(lblActualPrice);
		
		txtActualPrice = new JTextField();
		txtActualPrice.setForeground(new Color(0, 0, 128));
		txtActualPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtActualPrice.setColumns(10);
		txtActualPrice.setBounds(151, 200, 125, 25);
		contentPane.add(txtActualPrice);
		
		JLabel lblRs_4 = new JLabel("Rs");
		lblRs_4.setForeground(Color.WHITE);
		lblRs_4.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_4.setBounds(278, 206, 33, 26);
		contentPane.add(lblRs_4);
		
		txtGSTPrice = new JTextField();
		txtGSTPrice.setForeground(new Color(0, 0, 128));
		txtGSTPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtGSTPrice.setColumns(10);
		txtGSTPrice.setBounds(309, 170, 125, 25);
		contentPane.add(txtGSTPrice);
		
		JLabel lblRs_3_1 = new JLabel("Rs");
		lblRs_3_1.setForeground(Color.WHITE);
		lblRs_3_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3_1.setBounds(435, 170, 33, 24);
		contentPane.add(lblRs_3_1);
		
		txtSGSTPrice = new JTextField();
		txtSGSTPrice.setForeground(new Color(0, 0, 128));
		txtSGSTPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSGSTPrice.setColumns(10);
		txtSGSTPrice.setBounds(309, 140, 125, 25);
		contentPane.add(txtSGSTPrice);
		
		JLabel lblRs_3_1_1 = new JLabel("Rs");
		lblRs_3_1_1.setForeground(Color.WHITE);
		lblRs_3_1_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3_1_1.setBounds(435, 140, 33, 24);
		contentPane.add(lblRs_3_1_1);
		
		txtCGSTPrice = new JTextField();
		txtCGSTPrice.setForeground(new Color(0, 0, 128));
		txtCGSTPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtCGSTPrice.setColumns(10);
		txtCGSTPrice.setBounds(309, 110, 125, 25);
		contentPane.add(txtCGSTPrice);
		
		JLabel lblRs_3_1_2 = new JLabel("Rs");
		lblRs_3_1_2.setForeground(Color.WHITE);
		lblRs_3_1_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3_1_2.setBounds(435, 110, 33, 24);
		contentPane.add(lblRs_3_1_2);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//open new fresh page for adding new product purpose
			new	AddStockShop().setVisible(true);
			}
		});
		btnReset.setForeground(new Color(0, 0, 128));
		btnReset.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnReset.setBounds(505, 324, 100, 28);
		contentPane.add(btnReset);
		
		showData();
		stockData();
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
	}
}
