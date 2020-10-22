import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JDesktopPane;
import javax.swing.JButton;

public class DealerList extends JFrame {

	private JPanel contentPane;
	private JComboBox cbName;
	private JTable tblDealers;
	private JLabel lblDate;
	private JLabel lblDealer;
	private JLabel lblTypesOfPayment;
	private JLabel lblPaymentId;
	private JLabel lblTotalAmmount;
	private JLabel lblPaidAmmount;
	private JLabel lblPendingAmmount;
	private JLabel lblPDate;
	private JLabel lblDealerName;
	private JLabel lblType;
	private JLabel lblId;
	private JLabel lblTotal;
	private JLabel lblDate_2;
	private JLabel lblDate_6;
	private JLabel lblPaid;
	private JLabel lblDate_8;
	private JLabel lblPending;
	private ImageIcon format;
	private JLabel lblImage;
	
    String pname;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	String s;
	
	//show details of dealer's payment
	public void showDealers()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select Dealer from DealersPaymentData ORDER BY Dealer ASC";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			tblDealers.setModel(DbUtils.resultSetToTableModel(rs));	
		}
		catch (Exception e) 
		{	
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
					DealerList frame = new DealerList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DealerList()  {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DealerList.class.getResource("/images/plug.png")));
		setTitle("Dealer List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JScrollPane scrollPaneDealers = new JScrollPane();
		scrollPaneDealers.setBounds(10, 26, 494, 683);
		contentPane.add(scrollPaneDealers);
		
		tblDealers = new JTable();
		tblDealers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				//show dealer's details
				DefaultTableModel model=(DefaultTableModel)tblDealers.getModel();
				int i=tblDealers.getSelectedRow();
				 String s=model.getValueAt(i,0).toString();
				 try {
						con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
						String sql="select *from DealersPaymentData where Dealer=?";
						ps=con.prepareStatement(sql);
						ps.setString(1, s);
						rs=ps.executeQuery();
						while(rs.next())
						{
							String Dealer=rs.getString("Dealer");
							lblDealerName.setText(Dealer);
							String Date=rs.getString("Date");
							lblPDate.setText(Date);
							String Type_of_Payment=rs.getString("Type_of_Payment");
							lblType.setText(Type_of_Payment);
							String Payment_Id=rs.getString("Payment_Id");
							lblId.setText(Payment_Id);
							String Total_Amount=rs.getString("Total_Amount");
							lblTotal.setText(Total_Amount);
							String Paid_Amount=rs.getString("Paid_Amount");
							lblPaid.setText(Paid_Amount);
							String Pending_Amount=rs.getString("Pending_Amount");
							lblPending.setText(Pending_Amount);
						}	
					}
					catch (Exception e2) 
					{	
						e2.printStackTrace();
					}
			}
		});
		scrollPaneDealers.setViewportView(tblDealers);
		
		lblDate = new JLabel("Payment Date:");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblDate.setBounds(524, 111, 187, 25);
		contentPane.add(lblDate);
		
		lblDealer = new JLabel("Dealer:");
		lblDealer.setForeground(Color.WHITE);
		lblDealer.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblDealer.setBounds(524, 71, 187, 25);
		contentPane.add(lblDealer);
		
		lblTypesOfPayment = new JLabel("Type of Payment:");
		lblTypesOfPayment.setForeground(Color.WHITE);
		lblTypesOfPayment.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTypesOfPayment.setBounds(524, 151, 195, 23);
		contentPane.add(lblTypesOfPayment);
		
		lblPaymentId = new JLabel("Payment ID:");
		lblPaymentId.setForeground(Color.WHITE);
		lblPaymentId.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPaymentId.setBounds(524, 191, 242, 25);
		contentPane.add(lblPaymentId);
		
		lblTotalAmmount = new JLabel("Total Amount:");
		lblTotalAmmount.setForeground(Color.WHITE);
		lblTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTotalAmmount.setBounds(524, 231, 242, 25);
		contentPane.add(lblTotalAmmount);
		
		lblPaidAmmount = new JLabel("Paid Amount:");
		lblPaidAmmount.setForeground(Color.WHITE);
		lblPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPaidAmmount.setBounds(524, 271, 269, 25);
		contentPane.add(lblPaidAmmount);
		
		lblPendingAmmount = new JLabel("Pending Amount:");
		lblPendingAmmount.setForeground(Color.WHITE);
		lblPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPendingAmmount.setBounds(524, 311, 269, 25);
		contentPane.add(lblPendingAmmount);
		
		JLabel lblAddress = new JLabel("New label");
		lblAddress.setBounds(22, 641, 157, 80);
		contentPane.add(lblAddress);
		
		lblPDate = new JLabel("");
		lblPDate.setForeground(Color.WHITE);
		lblPDate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPDate.setBounds(699, 111, 187, 25);
		contentPane.add(lblPDate);
		
		lblDealerName = new JLabel("");
		lblDealerName.setForeground(Color.WHITE);
		lblDealerName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblDealerName.setBounds(699, 71, 643, 25);
		contentPane.add(lblDealerName);
		
		lblType = new JLabel("");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblType.setBounds(699, 151, 187, 25);
		contentPane.add(lblType);
		
		lblId = new JLabel("");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblId.setBounds(699, 191, 187, 25);
		contentPane.add(lblId);
		
		lblTotal = new JLabel("");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTotal.setBounds(729, 231, 157, 25);
		contentPane.add(lblTotal);
		
		lblDate_2 = new JLabel("Rs.");
		lblDate_2.setForeground(Color.WHITE);
		lblDate_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblDate_2.setBounds(699, 231, 30, 25);
		contentPane.add(lblDate_2);
		
		lblDate_6 = new JLabel("Rs.");
		lblDate_6.setForeground(Color.WHITE);
		lblDate_6.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblDate_6.setBounds(699, 271, 30, 25);
		contentPane.add(lblDate_6);
		
		lblPaid = new JLabel("");
		lblPaid.setForeground(Color.WHITE);
		lblPaid.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPaid.setBounds(729, 271, 157, 25);
		contentPane.add(lblPaid);
		
		lblDate_8 = new JLabel("Rs.");
		lblDate_8.setForeground(Color.WHITE);
		lblDate_8.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblDate_8.setBounds(699, 311, 30, 25);
		contentPane.add(lblDate_8);
		
		lblPending = new JLabel("");
		lblPending.setForeground(Color.WHITE);
		lblPending.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPending.setBounds(729, 311, 157, 25);
		contentPane.add(lblPending);
		
		JLabel lblNewLabel = new JLabel("Dealer's Payment Deatails:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblNewLabel.setBounds(524, 28, 377, 35);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//go to the home page on cancel action
				new HomeShop().setVisible(true);
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnNewButton.setBounds(524, 367, 137, 30);
		contentPane.add(btnNewButton);
		
		showDealers();
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
	}
}
