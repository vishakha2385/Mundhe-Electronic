import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddInvoice extends JFrame {

	private JPanel contentPane;
    String filename;
    Connection con;
    PreparedStatement ps;
  
    private final ButtonGroup buttonGroup = new ButtonGroup();
    
    public void ShowDealersPaymentData()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select *from DealersPaymentData ORDER BY Dealer ASC";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
	}
 
    
    public void DealersPaymentData()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			DatabaseMetaData d=con.getMetaData();
			ResultSet rs=d.getTables(null,null,"DealersPaymentData",null);
			if(rs.next())
			{
		//		JOptionPane.showMessageDialog(null,"DealersPaymentData table exist");
			}
			else 
			{
				String Create_Table="CREATE TABLE `mundheelectronics1`.`DealersPaymentData` (\r\n"
						+ "  `Dealer` VARCHAR(100) NOT NULL,\r\n"
						+ "  `Date` VARCHAR(45) NOT NULL,\r\n"
						+ "  `Type_of_Payment` VARCHAR(45) NOT NULL,\r\n"
						+ "  `Payment_Id` VARCHAR(50) NOT NULL,\r\n"
						+ "  `Total_Amount` INT NOT NULL,\r\n"
						+ "  `Paid_Amount` INT NOT NULL,\r\n"
						+ "  `Pending_Amount` INT NOT NULL);";
				PreparedStatement ps=con.prepareStatement(Create_Table);
				ps.executeUpdate();
	//			JOptionPane.showMessageDialog(null,"DealersPaymentData created successfully!");
			}
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
					AddInvoice frame = new AddInvoice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	ResultSet rs;
	private JTextField txtDate;
	private JTextField txtDealer;
	private JTextField txtPaymentId;
	private JTextField txtTotalAmmount;
	private JTextField txtPaidAmmount;
	private JTextField txtPendingAmmount;
	private JTable table;
	

	/**
	 * Create the frame.
	 */
	public AddInvoice() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddInvoice.class.getResource("/images/plug.png")));
		setTitle("Add Dealers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddress = new JLabel("");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress.setBounds(10, 546, 341, 145);
		contentPane.add(lblAddress);
		
		JButton btnSave = new JButton("Add");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
                {
                	String sql1="insert into DealersPaymentData values(?,?,?,?,?,?,?)";	
    				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
    				ps=con.prepareStatement(sql1);
    				ps.setString(1,txtDealer.getText());
    				ps.setString(2,txtDate.getText());
    				Enumeration<AbstractButton> bg=buttonGroup.getElements();
    				while(bg.hasMoreElements())
    				{
    					JRadioButton jrd=(JRadioButton) bg.nextElement();
    					if(jrd.isSelected())
    					{
    				
    						String f=jrd.getText();
    	    				ps.setString(3,f);
    	    		//		System.out.println(f);
    					}	
    				}
    				ps.setString(4,txtPaymentId.getText());
    				ps.setString(5,txtTotalAmmount.getText());
    				ps.setString(6,txtPaidAmmount.getText());
    				ps.setString(7,txtPendingAmmount.getText());
    				ps.executeUpdate();
    				ShowDealersPaymentData();
    		//		JOptionPane.showMessageDialog(null,"inserted");
                }catch(Exception e3){}	
			}
		});
		btnSave.setForeground(new Color(0, 0, 128));
		btnSave.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		
		btnSave.setBounds(223, 280, 128, 30);
		contentPane.add(btnSave);
		
		JLabel lblDate = new JLabel("Payment Date:");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblDate.setBounds(26, 56, 187, 25);
		contentPane.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setForeground(new Color(0, 0, 128));
		txtDate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtDate.setColumns(10);
		txtDate.setBounds(223, 56, 144, 25);
		contentPane.add(txtDate);
		
		JLabel lblDealer = new JLabel("Dealer:");
		lblDealer.setForeground(Color.WHITE);
		lblDealer.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblDealer.setBounds(26, 26, 187, 25);
		contentPane.add(lblDealer);
		
		txtDealer = new JTextField();
		txtDealer.setForeground(new Color(0, 0, 128));
		txtDealer.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtDealer.setColumns(10);
		txtDealer.setBounds(223, 26, 680, 25);
		contentPane.add(txtDealer);
		
		JLabel lblTypesOfPayment = new JLabel("Types of Payment:");
		lblTypesOfPayment.setForeground(Color.WHITE);
		lblTypesOfPayment.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTypesOfPayment.setBounds(26, 87, 195, 23);
		contentPane.add(lblTypesOfPayment);
		
		JRadioButton rbtnCash = new JRadioButton("Cash");
		rbtnCash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(rbtnCash.isSelected())
				{
					txtPaymentId.setText("cash");
				}
			}
		});
		rbtnCash.setSelected(true);
		rbtnCash.setBackground(Color.BLACK);
		rbtnCash.setBorder(null);
		buttonGroup.add(rbtnCash);
		rbtnCash.setForeground(Color.WHITE);
		rbtnCash.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		rbtnCash.setBounds(226, 87, 144, 25);
		
		contentPane.add(rbtnCash);
		
		JRadioButton rbtnCheque = new JRadioButton("Cheque");
		rbtnCheque.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(rbtnCheque.isSelected())
				{
					txtPaymentId.setText("");
				}
			}
		});
		rbtnCheque.setBackground(Color.BLACK);
		buttonGroup.add(rbtnCheque);
		rbtnCheque.setForeground(Color.WHITE);
		rbtnCheque.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		rbtnCheque.setBounds(223, 109, 144, 25);
		contentPane.add(rbtnCheque);
		
		JRadioButton rbtnUpi = new JRadioButton("UPI");
		rbtnUpi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(rbtnUpi.isSelected())
				{
					txtPaymentId.setText("");
				}
			}
		});
		rbtnUpi.setBackground(Color.BLACK);
		buttonGroup.add(rbtnUpi);
		rbtnUpi.setForeground(Color.WHITE);
		rbtnUpi.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		rbtnUpi.setBounds(223, 132, 144, 25);
		contentPane.add(rbtnUpi);
		
		JLabel lblPaymentId = new JLabel("Payment ID:");
		lblPaymentId.setForeground(Color.WHITE);
		lblPaymentId.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPaymentId.setBounds(26, 155, 242, 25);
		contentPane.add(lblPaymentId);
		
		txtPaymentId = new JTextField();
		txtPaymentId.setText("cash");
		txtPaymentId.setForeground(new Color(0, 0, 128));
		txtPaymentId.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPaymentId.setColumns(10);
		txtPaymentId.setBounds(223, 155, 452, 25);
		contentPane.add(txtPaymentId);
		
		JLabel lblTotalAmmount = new JLabel("Total Amount:");
		lblTotalAmmount.setForeground(Color.WHITE);
		lblTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTotalAmmount.setBounds(26, 185, 242, 25);
		contentPane.add(lblTotalAmmount);
		
		txtTotalAmmount = new JTextField();
		txtTotalAmmount.setForeground(new Color(0, 0, 128));
		txtTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTotalAmmount.setColumns(10);
		txtTotalAmmount.setBounds(223, 185, 144, 25);
		contentPane.add(txtTotalAmmount);
		
		JLabel lblPaidAmmount = new JLabel("Paid Amount:");
		lblPaidAmmount.setForeground(Color.WHITE);
		lblPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPaidAmmount.setBounds(26, 215, 242, 25);
		contentPane.add(lblPaidAmmount);
		
		txtPaidAmmount = new JTextField();
		txtPaidAmmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				String p1=txtPaidAmmount.getText();
				int paid=Integer.parseInt(p1);
				String t5=txtTotalAmmount.getText();
				int total2=Integer.parseInt(t5);
				int pending=total2-paid;
				txtPendingAmmount.setText(String.valueOf(pending));
			}
		});
		txtPaidAmmount.setForeground(new Color(0, 0, 128));
		txtPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPaidAmmount.setColumns(10);
		txtPaidAmmount.setBounds(223, 215, 144, 25);
		
		contentPane.add(txtPaidAmmount);
		
		JLabel lblRs = new JLabel("Rs");
		lblRs.setForeground(Color.WHITE);
		lblRs.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs.setBounds(370, 185, 37, 25);
		contentPane.add(lblRs);
		
		JLabel lblRs_1 = new JLabel("Rs");
		lblRs_1.setForeground(Color.WHITE);
		lblRs_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_1.setBounds(370, 215, 37, 25);
		contentPane.add(lblRs_1);
		
		JLabel lblRs_1_1 = new JLabel("Rs");
		lblRs_1_1.setForeground(Color.WHITE);
		lblRs_1_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_1_1.setBounds(370, 245, 37, 25);
		contentPane.add(lblRs_1_1);
		
		txtPendingAmmount = new JTextField();
		txtPendingAmmount.setForeground(new Color(0, 0, 128));
		txtPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPendingAmmount.setColumns(10);
		txtPendingAmmount.setBounds(223, 245, 144, 25);
		contentPane.add(txtPendingAmmount);
		
		JLabel lblPendingAmmount = new JLabel("Pending Amount:");
		lblPendingAmmount.setForeground(Color.WHITE);
		lblPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPendingAmmount.setBounds(26, 245, 242, 25);
		contentPane.add(lblPendingAmmount);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 320, 1294, 401);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int i=table.getSelectedRow();
				txtDealer.setText(model.getValueAt(i,0).toString());
				txtDate.setText(model.getValueAt(i,1).toString());
				String type=model.getValueAt(i,2).toString();
				if(type.equals("Cash"))
				{
					rbtnCash.setSelected(true);
				}
				else if(type.equals("Cheque"))
				{
					rbtnCheque.setSelected(true);
				} 
				else if(type.equals("UPI"))
				{
					rbtnUpi.setSelected(true);
				}
				txtPaymentId.setText(model.getValueAt(i,3).toString());
				txtTotalAmmount.setText(model.getValueAt(i,4).toString());
				txtPaidAmmount.setText(model.getValueAt(i,5).toString());
				txtPendingAmmount.setText(model.getValueAt(i,6).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				txtDealer.setText("");
				txtDate.setText("");
				txtPaymentId.setText("cash");
				txtTotalAmmount.setText("");
				txtPaidAmmount.setText("");
				txtPendingAmmount.setText("");
                rbtnCash.setSelected(true);
             
			}
		});
		btnNew.setForeground(new Color(0, 0, 128));
		btnNew.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnNew.setBounds(361, 280, 128, 30);
		contentPane.add(btnNew);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
				String sql3="UPDATE DealersPaymentData SET Dealer=?,Date=?,Type_of_Payment=?,Payment_Id=?,Total_Amount=?,Paid_Amount=?,Pending_Amount=? WHERE Dealer=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql3);
				ps.setString(8,txtDealer.getText());
				ps.setString(1,txtDealer.getText());
				ps.setString(2,txtDate.getText());
				Enumeration<AbstractButton> bg=buttonGroup.getElements();
				while(bg.hasMoreElements())
				{
					JRadioButton jrd=(JRadioButton) bg.nextElement();
					if(jrd.isSelected())
					{
				
						String f=jrd.getText();
	    				ps.setString(3,f);
					}	
				}
				
				ps.setString(4,txtPaymentId.getText());
			    ps.setString(5,txtTotalAmmount.getText());
				ps.setString(6,txtPaidAmmount.getText());
				ps.setString(7,txtPendingAmmount.getText());
				ps.executeUpdate();
				ShowDealersPaymentData();
				JOptionPane.showMessageDialog(null,"Dealer updated successfully!");
            	}
				catch(Exception e1) {}
			}
		});
		btnUpdate.setForeground(new Color(0, 0, 128));
		btnUpdate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnUpdate.setBounds(499, 280, 128, 30);
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
				String sql2="DELETE FROM DealersPaymentData WHERE Dealer=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql2);
				ps.setString(1,txtDealer.getText());
				ps.executeUpdate();
				ShowDealersPaymentData();
				JOptionPane.showMessageDialog(null,"Dealer deleted successfully!");
				txtDealer.setText("");
				txtDate.setText("");
				txtPaymentId.setText("cash");
				txtTotalAmmount.setText("");
				txtPaidAmmount.setText("");
				txtPendingAmmount.setText("");
                rbtnCash.setSelected(true);
                
				}
				catch(Exception e1) {}
			    }
			}
		});
		btnDelete.setForeground(new Color(0, 0, 128));
		btnDelete.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnDelete.setBounds(637, 280, 128, 30);
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
		btnCancel.setBounds(775, 280, 128, 30);
		contentPane.add(btnCancel);
		
		DealersPaymentData();
		ShowDealersPaymentData();
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
		
	}
}
