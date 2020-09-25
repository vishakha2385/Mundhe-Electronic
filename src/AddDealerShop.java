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

public class AddDealerShop extends JFrame {

	private JPanel contentPane;
	private JTextField txtInvoiceNo;
	private JTextField txtDate;
	private JTextField txtDealerName;
	private JTextField txtTotalAmmount;
	private JTextField txtPaidAmmount;
	private JTextField txtPendingAmmount;
	private JTextField txtSearch;
	private JTextField txtDealer;
	private JTable table;
	private JComboBox CategorycomboBox;
	Connection con;
	PreparedStatement ps;
    ResultSet rs;
    
    public void DealerData()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			DatabaseMetaData d=con.getMetaData();
			ResultSet rs=d.getTables(null,null,"DealerData",null);
			if(rs.next())
			{
	//			JOptionPane.showMessageDialog(null,"DealerData table exist");
			}
			else 
			{
				String Create_Table="create table DealerData(Invoice_No varchar(50),Date varchar(30),Dealer varchar(100),Category varchar(30),Total_Ammount int,Paid_Ammount int,Pending_Ammount int)";
				PreparedStatement ps=con.prepareStatement(Create_Table);
				ps.executeUpdate();
	//			JOptionPane.showMessageDialog(null,"DealerData created successfully!");
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
			String sql="select *from DealerData";
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
					AddDealerShop frame = new AddDealerShop();
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
	public AddDealerShop()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			
		} 
		catch(Exception e)
		{
			
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddDealerShop.class.getResource("/images/logoShop.jpg")));
		setTitle("Dealer Invoices");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInvoiceNo = new JLabel("Invoice No:");
		lblInvoiceNo.setForeground(Color.WHITE);
		lblInvoiceNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblInvoiceNo.setBounds(30, 29, 144, 29);
		contentPane.add(lblInvoiceNo);
		
		txtInvoiceNo = new JTextField();
		txtInvoiceNo.setForeground(new Color(0, 0, 128));
		txtInvoiceNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtInvoiceNo.setColumns(10);
		txtInvoiceNo.setBounds(199, 29, 119, 25);
		contentPane.add(txtInvoiceNo);
		
		txtDate = new JTextField();
		txtDate.setForeground(new Color(0, 0, 128));
		txtDate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtDate.setColumns(10);
		txtDate.setBounds(199, 68, 119, 25);
		contentPane.add(txtDate);
		
		JLabel lblDealer = new JLabel("Date:");
		lblDealer.setForeground(Color.WHITE);
		lblDealer.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblDealer.setBounds(30, 68, 144, 29);
		contentPane.add(lblDealer);
		
		txtDealerName = new JTextField();
		txtDealerName.setForeground(new Color(0, 0, 128));
		txtDealerName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtDealerName.setColumns(10);
		txtDealerName.setBounds(199, 107, 426, 25);
		contentPane.add(txtDealerName);
		
		JLabel lblDealer_1 = new JLabel("Dealer:");
		lblDealer_1.setForeground(Color.WHITE);
		lblDealer_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblDealer_1.setBounds(30, 107, 144, 29);
		contentPane.add(lblDealer_1);
		
		txtTotalAmmount = new JTextField();
		txtTotalAmmount.setForeground(new Color(0, 0, 128));
		txtTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTotalAmmount.setColumns(10);
		txtTotalAmmount.setBounds(199, 144, 183, 25);
		contentPane.add(txtTotalAmmount);
		
		JLabel lblTotalAmmount = new JLabel("Total Amount:");
		lblTotalAmmount.setForeground(Color.WHITE);
		lblTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTotalAmmount.setBounds(30, 144, 144, 29);
		contentPane.add(lblTotalAmmount);
		
		txtPaidAmmount = new JTextField();
		txtPaidAmmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				String t1=txtTotalAmmount.getText();
				int total=Integer.parseInt(t1);
				
				String p1=txtPaidAmmount.getText();
				int paid=Integer.parseInt(p1);
				
				int pending=total-paid;
				
				txtPendingAmmount.setText(String.valueOf(pending));
			}
		});
		txtPaidAmmount.setForeground(new Color(0, 0, 128));
		txtPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPaidAmmount.setColumns(10);
		txtPaidAmmount.setBounds(199, 181, 183, 25);
		contentPane.add(txtPaidAmmount);
		
		JLabel lblPaidAmmount = new JLabel("Paid Amount:");
		lblPaidAmmount.setForeground(Color.WHITE);
		lblPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPaidAmmount.setBounds(30, 181, 144, 29);
		contentPane.add(lblPaidAmmount);
		
		txtPendingAmmount = new JTextField();
		txtPendingAmmount.setForeground(new Color(0, 0, 128));
		txtPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPendingAmmount.setColumns(10);
		txtPendingAmmount.setBounds(199, 218, 183, 25);
		contentPane.add(txtPendingAmmount);
		
		JLabel lblPendingAmmount = new JLabel("Pending Amount:");
		lblPendingAmmount.setForeground(Color.WHITE);
		lblPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPendingAmmount.setBounds(30, 218, 159, 29);
		contentPane.add(lblPendingAmmount);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblCategory.setBounds(30, 255, 144, 29);
		contentPane.add(lblCategory);
		
		JComboBox CategorycomboBox = new JComboBox();
		CategorycomboBox.setForeground(new Color(0, 0, 128));
		CategorycomboBox.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		CategorycomboBox.setBounds(199, 257, 119, 25);
		CategorycomboBox.addItem("Paid");
		CategorycomboBox.addItem("Pending");
		contentPane.add(CategorycomboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Invoice No:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(975, 29, 103, 29);
		contentPane.add(lblNewLabel_1);
		
		txtSearch = new JTextField();
		txtSearch.setForeground(new Color(0, 0, 128));
		txtSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSearch.setColumns(10);
		txtSearch.setBounds(1076, 31, 133, 25);
		contentPane.add(txtSearch);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					String sql5="select *from DealerData where Invoice_No=?";
					ps=con.prepareStatement(sql5);
					ps.setString(1,txtSearch.getText());
					rs=ps.executeQuery();
					if(rs.next())
					{
						String Ino=rs.getString("Invoice_No");
						txtInvoiceNo.setText(Ino);
						String Date=rs.getString("Date");
						txtDate.setText(Date);
						String Cname=rs.getString("Dealer");
						txtDealerName.setText(Cname);
						String Category=rs.getString("Category");
						CategorycomboBox.setSelectedItem(Category);
						String Total_Ammount=rs.getString("Total_Ammount");
						txtTotalAmmount.setText(Total_Ammount);
						String Paid_Ammount=rs.getString("Paid_Ammount");
						txtPaidAmmount.setText(Paid_Ammount);
						String Pending_Ammount=rs.getString("Pending_Ammount");
						txtPendingAmmount.setText(Pending_Ammount);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Sorry! this Invoice is not Exist.");
					}
				} 
				catch(Exception e1)
				{
					
				}
			}
		});
		btnSearch.setForeground(new Color(0, 0, 128));
		btnSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnSearch.setBounds(1219, 29, 103, 28);
		contentPane.add(btnSearch);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
					String sql1="insert into DealerData values(?,?,?,?,?,?,?)";	
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					ps=con.prepareStatement(sql1);
					ps.setString(1,txtInvoiceNo.getText());
		//			System.out.println(txtInvoiceNo.getText());
					ps.setString(2,txtDate.getText());
		//			System.out.println(txtDate.getText());
					ps.setString(3,txtDealerName.getText());
		//			System.out.println(txtDealerName.getText());
					ps.setString(5,txtTotalAmmount.getText());
		//			System.out.println(txtTotalAmmount.getText());
					ps.setString(6,txtPaidAmmount.getText());
		//			System.out.println(txtPaidAmmount.getText());
					ps.setString(7,txtPendingAmmount.getText());
		//			System.out.println(txtPendingAmmount.getText());
					String Category=(String) CategorycomboBox.getSelectedItem();
		//			System.out.println(Category);
					ps.setString(4,Category);
					ps.executeUpdate();
		//			JOptionPane.showMessageDialog(null,"inserted");
					
					txtInvoiceNo.setText("");
					txtDate.setText("");
					txtDealerName.setText("");
					txtTotalAmmount.setText("");
					txtPaidAmmount.setText("");
					txtPendingAmmount.setText("");
					CategorycomboBox.setSelectedItem("Paid");
				}
				catch(Exception e1) 
				{
					JOptionPane.showMessageDialog(null,e1);	
				}
				ShowData();
			}
		});
		btnAdd.setForeground(new Color(0, 0, 128));
		btnAdd.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnAdd.setBounds(199, 299, 103, 28);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
				String sql3="UPDATE DealerData SET Invoice_No=?,Date=?,Dealer=?,Category=?,Total_Ammount=?,Paid_Ammount=?,Pending_Ammount=? WHERE Invoice_No=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql3);
				ps.setString(8,txtInvoiceNo.getText());
				ps.setString(1,txtInvoiceNo.getText());
				ps.setString(2,txtDate.getText());
				ps.setString(3,txtDealerName.getText());
				ps.setString(5,txtTotalAmmount.getText());
				ps.setString(6,txtPaidAmmount.getText());
				ps.setString(7,txtPendingAmmount.getText());
				
				String s1=(String) CategorycomboBox.getSelectedItem();
				ps.setString(4,s1);
				
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Invoice updated successfully!");

				txtInvoiceNo.setText("");
				txtDate.setText("");
				txtDealerName.setText("");
				txtTotalAmmount.setText("");
				txtPaidAmmount.setText("");
				txtPendingAmmount.setText("");
				CategorycomboBox.setSelectedItem("Paid");
				}
				catch(Exception e1) {}

				ShowData();
			}
		});
		btnUpdate.setForeground(new Color(0, 0, 128));
		btnUpdate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnUpdate.setBounds(312, 299, 103, 28);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int a=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Invoice?","Delete this Record?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			    if(a==JOptionPane.YES_OPTION)
			    {
				try 
				{
				String sql2="DELETE FROM DealerData WHERE Invoice_No=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql2);
				ps.setString(1,txtInvoiceNo.getText());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Invoice deleted successfully!");

				txtInvoiceNo.setText("");
				txtDate.setText("");
				txtDealerName.setText("");
				txtTotalAmmount.setText("");
				txtPaidAmmount.setText("");
				txtPendingAmmount.setText("");
				CategorycomboBox.setSelectedItem("Paid");
				}
				catch(Exception e1) {}
			    }
				ShowData();
			}
		});
		btnDelete.setForeground(new Color(0, 0, 128));
		btnDelete.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnDelete.setBounds(425, 299, 103, 28);
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
		btnCancel.setBounds(538, 299, 103, 28);
		contentPane.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 341, 1292, 380);
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
				txtDealerName.setText(model.getValueAt(i,2).toString());
				CategorycomboBox.setSelectedItem(model.getValueAt(i,3).toString());
				txtTotalAmmount.setText(model.getValueAt(i,4).toString());
				txtPaidAmmount.setText(model.getValueAt(i,5).toString());
				txtPendingAmmount.setText(model.getValueAt(i,6).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblRs = new JLabel("Rs");
		lblRs.setForeground(Color.WHITE);
		lblRs.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs.setBounds(392, 140, 159, 29);
		contentPane.add(lblRs);
		
		JLabel lblRs_1 = new JLabel("Rs");
		lblRs_1.setForeground(Color.WHITE);
		lblRs_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_1.setBounds(392, 181, 159, 29);
		contentPane.add(lblRs_1);
		
		JLabel lblRs_2 = new JLabel("Rs");
		lblRs_2.setForeground(Color.WHITE);
		lblRs_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_2.setBounds(392, 218, 159, 29);
		contentPane.add(lblRs_2);
		
		ShowData();
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
		
		DealerData();
	}
}
