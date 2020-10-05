import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import CustomInvoice.BillPrintable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.table.DefaultTableModel;

public class CustomInvoce extends JFrame {

	private JPanel contentPane;
	private JTextField txtDate;
	private JTextField txtTime;
	private JTextField txtInvoiceNo;
	private JTextField txtCustomerName;
	private JTextField txtAddress;
	private JTextField txtContact;
	private JTextField txtSerialNo;
	private JTextField txtModuleNo;
	private JTextField txtRateRs;
	private JTextField txtDiscount;
	private JTextField txtQuantity;
	private JTextField txtDiscountPrice;
	private JTextField txtTotal;
	private JTextField txtPaidAmmount;
	private JTextField txtPendingAmmount;
	private JTable table;
	private JTextField txtProductName;
	private JTextField txtTotalAmmount;
	private JComboBox cbCategory;
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public void CustomInvoicesData()
	{
		try {
			DatabaseMetaData d=con.getMetaData();
			ResultSet rs=d.getTables(null,null,"CustomInvoicesData",null);
			if(rs.next())
			{
	//			JOptionPane.showMessageDialog(null,"CustomInvoicesData table exist");
			}
			else 
			{
				String Create_Table="CREATE TABLE `mundheelectronics1`.`CustomInvoicesData` (\r\n"
						+ "  `Invoice_No` INT NOT NULL AUTO_INCREMENT,\r\n"
						+ "  `Date` VARCHAR(45) NOT NULL,\r\n"
						+ "  `Time` VARCHAR(45) NOT NULL,\r\n"
						+ "  `Customer_Name` VARCHAR(100) NOT NULL,\r\n"
						+ "  `Address` VARCHAR(100) NOT NULL,\r\n"
						+ "  `Contact` VARCHAR(45) NOT NULL,\r\n"
						+ "  `Total_Amount` DOUBLE NOT NULL,\r\n"
						+ "  `Paid_Amount` DOUBLE NOT NULL,\r\n"
						+ "  `Pending_Amount` DOUBLE NOT NULL,\r\n"
						+ "  PRIMARY KEY (`Invoice_No`));\r\n"
						+ "";
				PreparedStatement ps=con.prepareStatement(Create_Table);
				ps.executeUpdate();
	//			JOptionPane.showMessageDialog(null,"CustomInvoicesData created successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void InvoiceNo()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
	//		JOptionPane.showMessageDialog(null,"connected");
			String sql="select max(Invoice_No) from CustomInvoicesData";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next())
			{
				String n=rs.getString("max(Invoice_No)");
				int n1=Integer.parseInt(n);
		        int n2=n1+1;
	//			System.out.println(n);
				txtInvoiceNo.setText(String.valueOf(n2));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void date()
	{
	
		Date date=new Date();
		SimpleDateFormat d=new SimpleDateFormat("dd/MM/YYYY");
		txtDate.setText(d.format(date));
	}
	
	public void clock()
	{
		Thread clock=new Thread()
				{
			public void run()
			{
				try 
				{
					for(;;) {
					Calendar cal=new GregorianCalendar();
					
					
					int sec=cal.get(Calendar.SECOND);
					
					int min=cal.get(Calendar.MINUTE);
					
					int hour=cal.get(Calendar.HOUR_OF_DAY);
					
					String t=hour+":"+min+":"+sec;
					
					txtTime.setText(t);
					sleep(1000);
					}
				} 
				catch(Exception e)
				{
					
				}
			}
				};
		
				clock.start();
		
	}
	
	private void clear()
    {
    cbCategory.setSelectedItem("Electronics");
    txtQuantity.setText("");
    txtDiscountPrice.setText("");
    txtTotal.setText("");
    txtDiscount.setText("");
    txtSerialNo.setText("");
    txtModuleNo.setText("");
    }
    


public PageFormat getPageFormat(PrinterJob pj)
{

PageFormat pf = pj.defaultPage();
Paper paper = pf.getPaper();    

double bodyHeight = bHeight;  
double headerHeight = 5.0;                  
double footerHeight = 100.0;        
double width = cm_to_pp(100); 
double height = cm_to_pp(headerHeight+bodyHeight+footerHeight); 
paper.setSize(width, height);
paper.setImageableArea(0,10,width,height - cm_to_pp(1));  
        
pf.setOrientation(PageFormat.PORTRAIT);  
pf.setPaper(paper);    

return pf;
}



protected static double cm_to_pp(double cm)
{            
        return toPPI(cm * 0.393600787);            
}

protected static double toPPI(double inch)
{            
        return inch * 72d;            
}

public class BillPrintable implements Printable {

  public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {    
      
      int r= itemName.size();
      ImageIcon icon=new ImageIcon("C:\\Users\\Vishakha Hande\\eclipse-workspace\\Invoice Test\\src\\images\\logoShop.jpg"); 
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    
            double width = pageFormat.getImageableWidth();                               
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 



          //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
        
        try{
            int y=30;
            int z=550;
            int yShift = 15;
            int headerRectHeight=25;
           // int headerRectHeighta=40;
            
                
            g2d.setFont(new Font("Baskerville Old Face",Font.BOLD,20));
            g2d.drawString("                                    Mundhe Electronics                           ",108,y);y+=yShift;
            g2d.setFont(new Font("Century 20",Font.PLAIN,13));
            g2d.drawString("                                                        OPP-Janata Sahakari Bank,Otur                     ",108,y);y+=yShift;
            g2d.drawString("                                                          Tal-Junnar,Dist-Pune 409-412                      ",108,y);y+=yShift;
            g2d.drawString("                                                     Email-mundheelectronics@gmail.com                   ",108,y);y+=yShift;
            g2d.drawString("                                                        Contact:9673590202,9860223356                     ",108,y);y+=yShift;
            g2d.drawString("_______________________________________________________________________________________",108,y);y+=headerRectHeight;
            g2d.drawString(" Invoice No:",108,y);g2d.drawString(txtInvoiceNo.getText(),190,y);g2d.drawString(" Date:",498,y);g2d.drawString(txtDate.getText(),540,y);y+=yShift;
      
            g2d.drawString("                                                                      ",108,y);y+=headerRectHeight;
            g2d.setFont(new Font("Century 20",Font.BOLD,13));
            g2d.drawString(" Customer Details:",108,y);y+=yShift;
            g2d.setFont(new Font("Century 20",Font.PLAIN,13));
            g2d.drawString(" Customer Name:",108,y);g2d.drawString(txtCustomerName.getText(),233,y);y+=yShift;
            g2d.drawString(" Address:",108,y);g2d.drawString(txtAddress.getText(),233,y);y+=yShift;
            g2d.drawString(" Contact:",108,y);g2d.drawString(txtContact.getText(),233,y);y+=yShift;
            
            g2d.drawString("_______________________________________________________________________________________",108,215);y+=yShift;
            
          //  g2d.drawString("                                                                           ",108,y);y+=headerRectHeight;
            g2d.setFont(new Font("Century 20",Font.BOLD,15));
            g2d.drawString(" Item Name                                   Rate Rs          Discount          Quantity          Amount Rs   ",108,y);y+=yShift;
            g2d.setFont(new Font("Century 20",Font.PLAIN,15));
            g2d.drawString("__________________________________________________________________________",108,260);y+=headerRectHeight;
            
            
            for(int s=0; s<r; s++)
            {g2d.setFont(new Font("Century 20",Font.BOLD,15));
            g2d.drawString(" "+itemName.get(s),108,y);g2d.setFont(new Font("Century 20",Font.PLAIN,13));g2d.drawString(itemPrice.get(s),340,y);g2d.drawString(Discount.get(s)+"%",435,y);g2d.drawString(quantity.get(s),549,y);g2d.drawString(subtotal.get(s),645,y);y+=yShift;
            g2d.drawString(" "+SerialNo.get(s),108,y);y+=yShift;
            g2d.drawString(" "+ModuleNo.get(s),108,y);y+=yShift;
            g2d.drawString("                                                                           ",108,y);y+=headerRectHeight;

            }
          
            g2d.drawString("__________________________________________________________________________",108,545);y+=yShift;
            g2d.drawString("                                                                           ",108,555);y+=headerRectHeight;
            g2d.drawString(" Total Ammount:",458,580);g2d.drawString(txtTotalAmmount.getText()+" Rs",580,580);y+=yShift;
    //        g2d.drawString(" Paid Ammount:",260,y);g2d.drawString(txtPaidAmmount.getText()+" Rs",343,y);y+=yShift;
   //         g2d.drawString(" Pending Ammount:",260,y);g2d.drawString(txtPendingAmmount.getText()+" Rs",343,y);y+=yShift;
            g2d.drawString("__________________________________________________________________________",108,600);y+=yShift;
            g2d.drawString("                                                                           ",108,611);y+=headerRectHeight;
            g2d.drawString(" GSTIN No:27AMBPM6213C1ZI",108,620);y+=yShift;
            g2d.drawString(" CGST & SGST both are added.",108,635);y+=yShift;
            g2d.drawString("                                                                      ",108,645);y+=yShift;
            g2d.drawString("                                                                      ",108,655);y+=yShift;
            g2d.drawString("                                                                                                                Authorised Signatory",108,670);y+=yShift;
            g2d.drawString("                                                                                                                 Mundhe Electronics",108,685);y+=yShift;
            g2d.drawString("***********************************************************************************************************",108,700);y+=yShift;
            g2d.drawString("                                                               THANK YOU!                             ",108,720);y+=yShift;
    }
    catch(Exception e){
    e.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }
   }


Double totalAmount=0.0;
Double cash=0.0;
Double balance=0.0;
Double bHeight=0.0;

ArrayList<String> itemName = new ArrayList<>();
ArrayList<String> quantity = new ArrayList<>();
ArrayList<String> itemPrice = new ArrayList<>();
ArrayList<String> subtotal = new ArrayList<>();
ArrayList<String> Discount = new ArrayList<>();
ArrayList<String> SerialNo = new ArrayList<>();
ArrayList<String> ModuleNo = new ArrayList<>();

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomInvoce frame = new CustomInvoce();
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
	public CustomInvoce()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(CustomInvoce.class.getResource("/images/plug.png")));
		setTitle("Create Custome Invoice");
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			
		} 
		catch(Exception e)
		{
			
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(0, 0, 1352, 731);
		contentPane.add(contentPane_1);
		
		JLabel lblNewLabel = new JLabel("Date:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel.setBounds(29, 64, 50, 24);
		contentPane_1.add(lblNewLabel);
		
		txtDate = new JTextField();
		txtDate.setText("04/10/2020");
		txtDate.setForeground(new Color(0, 0, 128));
		txtDate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtDate.setColumns(10);
		txtDate.setBounds(82, 66, 104, 25);
		contentPane_1.add(txtDate);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTime.setBounds(216, 64, 50, 24);
		contentPane_1.add(lblTime);
		
		JLabel lblInvoiceNo = new JLabel("Invoice No:");
		lblInvoiceNo.setForeground(Color.WHITE);
		lblInvoiceNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblInvoiceNo.setBounds(29, 98, 106, 24);
		contentPane_1.add(lblInvoiceNo);
		
		txtTime = new JTextField();
		txtTime.setForeground(new Color(0, 0, 128));
		txtTime.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTime.setColumns(10);
		txtTime.setBounds(271, 64, 104, 25);
		contentPane_1.add(txtTime);
		
		txtInvoiceNo = new JTextField();
		txtInvoiceNo.setForeground(new Color(0, 0, 128));
		txtInvoiceNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtInvoiceNo.setColumns(10);
		txtInvoiceNo.setBounds(173, 98, 202, 25);
		contentPane_1.add(txtInvoiceNo);
		
		JLabel lblCustomerName = new JLabel("Customer:");
		lblCustomerName.setForeground(Color.WHITE);
		lblCustomerName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblCustomerName.setBounds(29, 132, 143, 24);
		contentPane_1.add(lblCustomerName);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setForeground(new Color(0, 0, 128));
		txtCustomerName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(173, 132, 357, 25);
		contentPane_1.add(txtCustomerName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblAddress.setBounds(29, 166, 143, 24);
		contentPane_1.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setForeground(new Color(0, 0, 128));
		txtAddress.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtAddress.setColumns(10);
		txtAddress.setBounds(173, 166, 357, 25);
		contentPane_1.add(txtAddress);
		
		JLabel lblContact = new JLabel("Contact:");
		lblContact.setForeground(Color.WHITE);
		lblContact.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblContact.setBounds(29, 202, 143, 24);
		contentPane_1.add(lblContact);
		
		txtContact = new JTextField();
		txtContact.setForeground(new Color(0, 0, 128));
		txtContact.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtContact.setColumns(10);
		txtContact.setBounds(173, 202, 199, 25);
		contentPane_1.add(txtContact);
		
		JLabel lblAddCustomerDetails = new JLabel("Add Customer Details");
		lblAddCustomerDetails.setForeground(Color.WHITE);
		lblAddCustomerDetails.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblAddCustomerDetails.setBounds(82, 22, 260, 30);
		contentPane_1.add(lblAddCustomerDetails);
		
		JLabel lblAddProductDetails = new JLabel("Add Product Details");
		lblAddProductDetails.setForeground(Color.WHITE);
		lblAddProductDetails.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblAddProductDetails.setBounds(82, 264, 260, 30);
		contentPane_1.add(lblAddProductDetails);
		
		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setForeground(Color.WHITE);
		lblProductName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblProductName.setBounds(29, 330, 134, 24);
		contentPane_1.add(lblProductName);
		
		JLabel lblSerialNo = new JLabel("Serial No:");
		lblSerialNo.setForeground(Color.WHITE);
		lblSerialNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblSerialNo.setBounds(29, 364, 106, 24);
		contentPane_1.add(lblSerialNo);
		
		txtSerialNo = new JTextField();
		txtSerialNo.setForeground(new Color(0, 0, 128));
		txtSerialNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSerialNo.setColumns(10);
		txtSerialNo.setBounds(173, 364, 266, 25);
		contentPane_1.add(txtSerialNo);
		
		JLabel lblModuleNo = new JLabel("Module No:");
		lblModuleNo.setForeground(Color.WHITE);
		lblModuleNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblModuleNo.setBounds(29, 398, 106, 24);
		contentPane_1.add(lblModuleNo);
		
		txtModuleNo = new JTextField();
		txtModuleNo.setForeground(new Color(0, 0, 128));
		txtModuleNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtModuleNo.setColumns(10);
		txtModuleNo.setBounds(173, 398, 266, 25);
		contentPane_1.add(txtModuleNo);
		
		JLabel lblRateRs = new JLabel("Rate Rs:");
		lblRateRs.setForeground(Color.WHITE);
		lblRateRs.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRateRs.setBounds(29, 432, 106, 24);
		contentPane_1.add(lblRateRs);
		
		txtRateRs = new JTextField();
		txtRateRs.setForeground(new Color(0, 0, 128));
		txtRateRs.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtRateRs.setColumns(10);
		txtRateRs.setBounds(173, 432, 104, 25);
		contentPane_1.add(txtRateRs);
		
		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setForeground(Color.WHITE);
		lblDiscount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblDiscount.setBounds(29, 466, 106, 24);
		contentPane_1.add(lblDiscount);
		
		txtDiscount = new JTextField();
		txtDiscount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				try {
					String r1=txtRateRs.getText();
					int rate=Integer.parseInt(r1);
					
					String d1=txtDiscount.getText();
					int discountInPer=Integer.parseInt(d1);
					
					String q1=txtQuantity.getText();
					int quantity=Integer.parseInt(q1);
					
					int discount=(discountInPer*rate)/100;
					int unitDiscount=rate-discount;
					
					txtDiscountPrice.setText(String.valueOf(unitDiscount));
					int total=unitDiscount*quantity;
					
					txtTotal.setText(String.valueOf(total));
					}
					catch(NumberFormatException e2)
					{
						
					}
			}
		});
		txtDiscount.setForeground(new Color(0, 0, 128));
		txtDiscount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(173, 466, 104, 25);
		contentPane_1.add(txtDiscount);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setForeground(Color.WHITE);
		lblQuantity.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblQuantity.setBounds(29, 500, 106, 24);
		contentPane_1.add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				String r1=txtRateRs.getText();
				int rate=Integer.parseInt(r1);
				
				String d1=txtDiscount.getText();
				int discountInPer=Integer.parseInt(d1);
				
				String q1=txtQuantity.getText();
				int quantity=Integer.parseInt(q1);
				
				int discount=(discountInPer*rate)/100;
				int unitDiscount=rate-discount;
				
				txtDiscountPrice.setText(String.valueOf(unitDiscount));
				int total=unitDiscount*quantity;
				
				txtTotal.setText(String.valueOf(total));
			}
		});
		txtQuantity.setForeground(new Color(0, 0, 128));
		txtQuantity.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(173, 500, 104, 25);
		contentPane_1.add(txtQuantity);
		
		JLabel lblDiscountPrice = new JLabel("Discount Price:");
		lblDiscountPrice.setForeground(Color.WHITE);
		lblDiscountPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblDiscountPrice.setBounds(29, 534, 143, 24);
		contentPane_1.add(lblDiscountPrice);
		
		txtDiscountPrice = new JTextField();
		txtDiscountPrice.setForeground(new Color(0, 0, 128));
		txtDiscountPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtDiscountPrice.setColumns(10);
		txtDiscountPrice.setBounds(173, 534, 104, 25);
		contentPane_1.add(txtDiscountPrice);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTotal.setBounds(29, 565, 106, 24);
		contentPane_1.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setForeground(new Color(0, 0, 128));
		txtTotal.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTotal.setColumns(10);
		txtTotal.setBounds(173, 565, 104, 25);
		contentPane_1.add(txtTotal);
		
		JLabel lblRs = new JLabel("Rs");
		lblRs.setForeground(Color.WHITE);
		lblRs.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs.setBounds(281, 432, 106, 24);
		contentPane_1.add(lblRs);
		
		JLabel lblRs_1 = new JLabel("%");
		lblRs_1.setForeground(Color.WHITE);
		lblRs_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_1.setBounds(281, 466, 106, 24);
		contentPane_1.add(lblRs_1);
		
		JLabel lblUnits = new JLabel("Units");
		lblUnits.setForeground(Color.WHITE);
		lblUnits.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblUnits.setBounds(281, 500, 106, 24);
		contentPane_1.add(lblUnits);
		
		JLabel lblRs_2 = new JLabel("Rs");
		lblRs_2.setForeground(Color.WHITE);
		lblRs_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_2.setBounds(281, 534, 106, 24);
		contentPane_1.add(lblRs_2);
		
		JLabel lblRs_3 = new JLabel("Rs");
		lblRs_3.setForeground(Color.WHITE);
		lblRs_3.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3.setBounds(281, 565, 106, 24);
		contentPane_1.add(lblRs_3);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					itemName.add(txtProductName.getText());
			        quantity.add(txtQuantity.getText());
			        Discount.add(txtDiscount.getText());
			        SerialNo.add(txtSerialNo.getText());
			        ModuleNo.add(txtModuleNo.getText());
			        itemPrice.add(txtDiscountPrice.getText());
			        subtotal.add(txtTotal.getText());
			        totalAmount = totalAmount+ Double.valueOf(txtTotal.getText());
			        txtTotalAmmount.setText(totalAmount+"");
			         clear();
				}catch(Exception e4) {}
				
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				model.addRow(new Object[] {txtProductName.getText(),txtSerialNo.getText(),txtModuleNo.getText(),txtRateRs.getText(),txtDiscount.getText(),txtQuantity.getText(),txtDiscountPrice.getText(),txtTotal.getText()});
				
				txtProductName.setText("");
				txtSerialNo.setText(null);
				txtModuleNo.setText(null);
				txtRateRs.setText(null);
				txtDiscount.setText(null);
				txtQuantity.setText(null);
				txtDiscountPrice.setText(null);
				txtTotal.setText(null);	
			}
		});
		btnAdd.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnAdd.setBounds(173, 627, 134, 28);
		contentPane_1.add(btnAdd);
		
		JButton btnNewInvoice = new JButton("New Invoice");
		btnNewInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new CustomInvoce().setVisible(true);
			}
		});
		btnNewInvoice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnNewInvoice.setBounds(605, 627, 177, 28);
		contentPane_1.add(btnNewInvoice);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new HomeShop().setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnCancel.setBounds(792, 627, 134, 28);
		contentPane_1.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(540, 22, 802, 534);
		contentPane_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Products", "Serial No", "Module no", "Rate Rs", "Discount %", "Qauntity", "Discount Price", "Total"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(109);
		scrollPane.setViewportView(table);
		
		JLabel lblTotalAmmount = new JLabel("Total Amount:");
		lblTotalAmmount.setForeground(Color.WHITE);
		lblTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTotalAmmount.setBounds(1042, 566, 134, 24);
		contentPane_1.add(lblTotalAmmount);
		
		JLabel lblRs_4 = new JLabel("Rs");
		lblRs_4.setForeground(Color.WHITE);
		lblRs_4.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_4.setBounds(1316, 566, 26, 24);
		contentPane_1.add(lblRs_4);
		
		JLabel lblPaidAmmount = new JLabel("Paid Amount:");
		lblPaidAmmount.setForeground(Color.WHITE);
		lblPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPaidAmmount.setBounds(1042, 600, 143, 24);
		contentPane_1.add(lblPaidAmmount);
		
		txtPaidAmmount = new JTextField();
		txtPaidAmmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				String p1=txtPaidAmmount.getText();
				double paid=Double.parseDouble(p1);
				String t5=txtTotalAmmount.getText();
				double total2=Double.parseDouble(t5);
				double pending=total2-paid;
				txtPendingAmmount.setText(String.valueOf(pending));
			}
		});
		txtPaidAmmount.setForeground(new Color(0, 0, 128));
		txtPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPaidAmmount.setColumns(10);
		txtPaidAmmount.setBounds(1208, 600, 104, 25);
		contentPane_1.add(txtPaidAmmount);
		
		JLabel lblRs_2_1 = new JLabel("Rs");
		lblRs_2_1.setForeground(Color.WHITE);
		lblRs_2_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_2_1.setBounds(1316, 600, 26, 24);
		contentPane_1.add(lblRs_2_1);
		
		JLabel lblPendingAmmount = new JLabel("Pending Amount:");
		lblPendingAmmount.setForeground(Color.WHITE);
		lblPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPendingAmmount.setBounds(1042, 631, 171, 24);
		contentPane_1.add(lblPendingAmmount);
		
		txtPendingAmmount = new JTextField();
		txtPendingAmmount.setForeground(new Color(0, 0, 128));
		txtPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPendingAmmount.setColumns(10);
		txtPendingAmmount.setBounds(1208, 631, 104, 25);
		contentPane_1.add(txtPendingAmmount);
		
		JLabel lblRs_3_1 = new JLabel("Rs");
		lblRs_3_1.setForeground(Color.WHITE);
		lblRs_3_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3_1.setBounds(1316, 631, 26, 24);
		contentPane_1.add(lblRs_3_1);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				bHeight = Double.valueOf(itemName.size());
		        //JOptionPane.showMessageDialog(rootPane, bHeight);
		        
		        PrinterJob pj = PrinterJob.getPrinterJob();        
		        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
		        try {
		             pj.print();
		          
		        }
		         catch (PrinterException ex) {
		                 ex.printStackTrace();
		        }
				
				try 
				{
				String sql1="insert into CustomInvoicesData values(?,?,?,?,?,?,?,?,?)";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql1);
				ps.setString(1,txtInvoiceNo.getText());
				ps.setString(2,txtDate.getText());
				ps.setString(3,txtTime.getText());
				ps.setString(4,txtCustomerName.getText());
				ps.setString(5,txtAddress.getText());
				ps.setString(6,txtContact.getText());
				ps.setString(7,txtTotalAmmount.getText());
				ps.setString(8,txtPaidAmmount.getText());
				ps.setString(9,txtPendingAmmount.getText());
				
				ps.executeUpdate();
		//		JOptionPane.showMessageDialog(null,"inserted into CustomInvoicesData");
				new CustomInvoce().setVisible(true);
				txtCustomerName.setText("");
				txtContact.setText("");
				txtTotalAmmount.setText("");
				txtPaidAmmount.setText("");
				txtPendingAmmount.setText("");
				txtAddress.setText("");
				
				
				}
				
				catch(Exception e1) 
				{
	//				JOptionPane.showMessageDialog(null,e1);	
				}
				InvoiceNo();
			}
		});
		btnPrint.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnPrint.setBounds(1143, 670, 134, 28);
		contentPane_1.add(btnPrint);
		
		txtProductName = new JTextField();
		txtProductName.setForeground(new Color(0, 0, 128));
		txtProductName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtProductName.setColumns(10);
		txtProductName.setBounds(173, 329, 266, 25);
		contentPane_1.add(txtProductName);
		
		txtTotalAmmount = new JTextField();
		txtTotalAmmount.setForeground(new Color(0, 0, 128));
		txtTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTotalAmmount.setColumns(10);
		txtTotalAmmount.setBounds(1208, 569, 104, 25);
		contentPane_1.add(txtTotalAmmount);
		
		JButton btnDiscard = new JButton("Discard");
		btnDiscard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int a=JOptionPane.showConfirmDialog(null,"Are you sure you want to discard this Product?","Delete this Record?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			    if(a==JOptionPane.YES_OPTION)
			    {
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int SelectedRowIndex=table.getSelectedRow();
				model.removeRow(SelectedRowIndex);
				txtProductName.setText("");
				txtSerialNo.setText(null);
				txtModuleNo.setText(null);
				txtRateRs.setText(null);
				txtDiscount.setText(null);
				txtQuantity.setText(null);
				txtDiscountPrice.setText(null);
				txtTotal.setText(null);	
			    }
			}
		});
		btnDiscard.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnDiscard.setBounds(317, 627, 134, 28);
		contentPane_1.add(btnDiscard);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{			   
				txtProductName.setText("");
				txtSerialNo.setText(null);
				txtModuleNo.setText(null);
				txtRateRs.setText(null);
				txtDiscount.setText(null);
				txtQuantity.setText(null);
				txtDiscountPrice.setText(null);
				txtTotal.setText(null);	
			}
		});
		btnReset.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnReset.setBounds(461, 627, 134, 28);
		contentPane_1.add(btnReset);
	clock();
	date();
	CustomInvoicesData();
	InvoiceNo();
	
	

	
	}
}