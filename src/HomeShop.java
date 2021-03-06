import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeShop extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeShop frame = new HomeShop();
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
	public HomeShop() {
		setTitle("Home");
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomeShop.class.getResource("/images/plug.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1350, 22);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Invoices");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Create Invoice");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//open create invoice page
				new CreateBillShop().setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Update Payment");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//open update payment page
				new UpdateBill().setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Delete Invoice");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//open delete invoice page
				new DeleteBillShop().setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Invoice Status");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//open bill status page
				new BillStatus().setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_10);
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_5 = new JMenu("Custom Invoices");
		menuBar.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Create Custom Invoice");
		mntmNewMenuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//open create custom invoice page
				new CustomInvoce().setVisible(true);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_13);
		
		JMenuItem mntmNewMenuItem_14 = new JMenuItem("Custom Invoices List");
		mntmNewMenuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//open Custom invoices list page
				new CustomInvoicesList().setVisible(true);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_14);
		
		JMenu mnNewMenu_1 = new JMenu("Stock");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Add Stock");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//open add stock page
				new AddStockShop().setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Stock List");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//open stock list page
				new StockListShop().setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_2 = new JMenu("Dealers");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Add Dealer");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//open add dealer page
				new AddInvoice().setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Dealer Payments status");
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//open dealer payment status page
				new DealerBillStatus().setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Dealer List");
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//open dealer list page
				new DealerList().setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_12);
		mnNewMenu_2.add(mntmNewMenuItem_11);
		
		JMenu mnNewMenu_3 = new JMenu("Complaints");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Add Complaints");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//open add complaints page
				new AddComplaintsShop().setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_4 = new JMenu("Report");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Income");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//open income page
				new IncomeShop().setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Reset Password");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//open reset password page
				new ResetPasswordShop().setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Logout");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//for logout
				new LoginShop().setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_9);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
	}
}
