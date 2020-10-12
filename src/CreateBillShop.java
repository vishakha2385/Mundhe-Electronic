import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import net.proteanit.sql.DbUtils;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;

public class CreateBillShop extends JFrame {

	private JPanel contentPane;
	private JTextField txtDate;
	private JTextField txtTime;
	private JTextField txtInvoiceNo;
	private JTextField txtCustomerName;
	private JTextField txtAddress;
	private JTextField Contact;
	private JTextField txtProductNo;
	private JTextField txtSrNo;
	private JTextField txtSerialNo;
	private JTextField txtModuleNo;
	private JTextField txtRateRs;
	private JTextField txtDiscount;
	private JTextField txtQuantity;
	private JTextField txtDiscountPrice;
	private JTextField txtTotal;
	private JTable table;
	private JTextField txtTotalAmmount;
	private JTextField txtPaidAmmount;
	private JTextField txtPendingAmmount;
	private JComboBox cbCategory;
	private JComboBox cbName;
	private JTextField txtDis;

    Connection con;
    ResultSet rs;
    PreparedStatement ps;
    Statement st;
    int getValue;
    long eventMask;
    
    public void CustomerData()
	{
		try {
			DatabaseMetaData d=con.getMetaData();
			ResultSet rs=d.getTables(null,null,"CustomerData",null);
			if(rs.next())
			{
		//		JOptionPane.showMessageDialog(null,"CustomerData table exist");
			}
			else 
			{
				String Create_Table="create table CustomerData(Invoice_No int not null auto_increment primary key,Date varchar(45),Time varchar(45),Customer_Name varchar(100),Address varchar(100),Contact varchar(45),Total_Ammount int,Paid_Ammount int,Pending_Ammount int)";
				PreparedStatement ps=con.prepareStatement(Create_Table);
				ps.executeUpdate();
		//		JOptionPane.showMessageDialog(null,"CustomerData created successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    public void BillsData()
	{
		try {
			DatabaseMetaData d=con.getMetaData();
			ResultSet rs=d.getTables(null,null,"BillsData",null);
			if(rs.next())
			{
	//			JOptionPane.showMessageDialog(null,"BillsData table exist");
			}
			else 
			{
				String Create_Table="create table BillsData(Invoice_No int references CustomerData,Product_No int references ProductsData)";
				PreparedStatement ps=con.prepareStatement(Create_Table);
				ps.executeUpdate();
	//			JOptionPane.showMessageDialog(null,"BillsData created successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    public void ProductsData()
	{
		try {
			DatabaseMetaData d=con.getMetaData();
			ResultSet rs=d.getTables(null,null,"ProductsData",null);
			if(rs.next())
			{
				JOptionPane.showMessageDialog(null,"ProductsData table exist");
			}
			else 
			{
				String Create_Table1="CREATE TABLE `mundheelectronics1`.`productsdata` (\r\n"
						+ "  `Product_No` INT NOT NULL AUTO_INCREMENT,\r\n"
						+ "  `Sr_No` INT NOT NULL,\r\n"
						+ "  `Category` VARCHAR(45) NOT NULL,\r\n"
						+ "  `Products` VARCHAR(100) NOT NULL,\r\n"
						+ "  `Serial_No` VARCHAR(50) NOT NULL,\r\n"
						+ "  `Module_No` VARCHAR(50) NOT NULL,\r\n"
						+ "  `Rate_Rs` FLOAT NOT NULL,\r\n"
						+ "  `Discount_Per` FLOAT NOT NULL,\r\n"
						+ "  `Quantity` INT NOT NULL,\r\n"
						+ "  `Discount_Price` FLOAT NOT NULL,\r\n"
						+ "  `Total` FLOAT NOT NULL,\r\n"
						+ "  `Invoice_No` VARCHAR(45) NOT NULL,\r\n"
						+ "  `Date` VARCHAR(45) NOT NULL,\r\n"
						+ "  `Time` VARCHAR(45) NOT NULL,\r\n"
						+ "  `Discount_in_Rs` FLOAT NOT NULL,\r\n"
						+ "  `CGST_in_per` FLOAT NOT NULL,\r\n"
						+ "  `CGST_in_Rs` FLOAT NOT NULL,\r\n"
						+ "  `SGST_in_per` FLOAT NOT NULL,\r\n"
						+ "  `SGST_in_Rs` FLOAT NOT NULL,\r\n"
						+ "  `GST_in_per` FLOAT NOT NULL,\r\n"
						+ "  `GST_in_Rs` FLOAT NOT NULL,\r\n"
						+ "  `Actual_Price` FLOAT NOT NULL,\r\n"
						+ "  PRIMARY KEY (`Product_No`));\r\n"
						+ "";
				PreparedStatement ps=con.prepareStatement(Create_Table1);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"ProductsData created successfully!");
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
			String sql="select max(Invoice_No) from CustomerData";
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
	
	public void ProductNo()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
	//		JOptionPane.showMessageDialog(null,"connected");
			String sql1="select max(Product_No) from ProductsData";
			ps=con.prepareStatement(sql1);
			rs=ps.executeQuery();
			if(rs.next())
			{
				String n=rs.getString("max(Product_No)");
				int n1=Integer.parseInt(n);
		        int n2=n1+1;
	//			System.out.println(n);
				txtProductNo.setText(String.valueOf(n2));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
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
       //     int z=550;
            int yShift = 15;
            int headerRectHeight=25;
           // int headerRectHeighta=40;
            
            g2d.drawLine(108,217,108,547);   
            g2d.drawLine(330,217,330,547);
            g2d.drawLine(431,217,431,547);
            g2d.drawLine(532,217,532,547);
            g2d.drawLine(633,217,633,547);
            g2d.drawLine(734,217,734,547);
            


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
            g2d.drawString(" Contact:",108,y);g2d.drawString(Contact.getText(),233,y);y+=yShift;

            g2d.drawString("_______________________________________________________________________________________",108,215);y+=yShift;
            
          //  g2d.drawString("                                                                           ",108,y);y+=headerRectHeight;
            g2d.setFont(new Font("Century 20",Font.BOLD,15));
            g2d.drawString(" Item Name                                   Rate Rs           Discount         Quantity          Amount Rs   ",108,y);y+=yShift;
            g2d.setFont(new Font("Century 20",Font.PLAIN,15));
            g2d.drawString("__________________________________________________________________________",108,260);y+=headerRectHeight;
            
            
            for(int s=0; s<r; s++)
            {g2d.setFont(new Font("Century 20",Font.BOLD,15));
            g2d.drawString(" "+itemName.get(s),108,y);g2d.setFont(new Font("Century 20",Font.PLAIN,13));g2d.drawString(itemPrice.get(s),340,y);g2d.drawString(Discount.get(s)+"%",442,y);g2d.drawString(quantity.get(s),544,y);g2d.drawString(subtotal.get(s),645,y);y+=yShift;
            g2d.drawString(" "+SerialNo.get(s),108,y);y+=yShift;
            g2d.drawString(" "+ModuleNo.get(s),108,y);y+=yShift;
            g2d.drawString("                                                                           ",108,y);y+=headerRectHeight;

            }
          
            g2d.drawString("_______________________________________________________________________________________",108,545);y+=yShift;
            g2d.drawString("                                                                           ",108,555);y+=headerRectHeight;
            g2d.drawString(" Total Discount:",458,580);g2d.drawString(textTotalDiscount.getText()+" Rs",580,580);y+=yShift;
            g2d.drawString(" Total Ammount:",458,600);g2d.drawString(txtTotalAmmount.getText()+" Rs",580,600);y+=yShift;
    //        g2d.drawString(" Paid Ammount:",260,y);g2d.drawString(txtPaidAmmount.getText()+" Rs",343,y);y+=yShift;
   //         g2d.drawString(" Pending Ammount:",260,y);g2d.drawString(txtPendingAmmount.getText()+" Rs",343,y);y+=yShift;
            g2d.drawString("_______________________________________________________________________________________",108,620);y+=yShift;
            g2d.drawString("                                                                           ",108,631);y+=headerRectHeight;
            g2d.drawString(" GSTIN No:27AMBPM6213C1ZI",108,640);y+=yShift;
            g2d.drawString(" CGST & SGST both are added.",108,655);y+=yShift;
            g2d.drawString("                                                                      ",108,680);y+=yShift;
            g2d.drawString("                                                                      ",108,675);y+=yShift;
            g2d.drawString("          Customer's Signatory                                                                  Authorised Signatory",108,750);y+=yShift;
            g2d.drawString("                                                                                                               Mundhe Electronics",108,765);y+=yShift;
            g2d.drawString("****************************************************************************************************************************",108,785);y+=yShift;
            g2d.drawString("                                                                            THANK YOU!                             ",108,820);y+=yShift;
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
private JTextField textTotalDiscount;
private JTextField txtCGST;
private JTextField txtCGSTPrice;
private JTextField txtSGST;
private JTextField txtSGSTPrice;
private JTextField txtGSTPrice;
private JTextField txtGST;
private JTextField txtActualPrice;
private JTextField txtTotalCGST;
private JTextField txtTotalSGST;
private JTextField txtTotalGST;
private JTextField txtTotalCGSTPrice;
private JTextField txtTotalSGSTPrice;
private JTextField txtTotalGSTPrice;


    public void total()
	{
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select sum(Total) from ProductsData where Invoice_No=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1,txtInvoiceNo.getText());
			rs=ps.executeQuery();
		

			if(rs.next())
			{
				String sum=rs.getString("sum(Total)");
				txtTotalAmmount.setText(sum);
				
			
		}
				
			
		} 
		catch(NumberFormatException | SQLException e) 
		{
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void totalDiscount()
	{
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select sum(Discount) from ProductsData where Invoice_No=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1,txtInvoiceNo.getText());
			rs=ps.executeQuery();
		

			if(rs.next())
			{
				String sum=rs.getString("sum(Discount)");
				textTotalDiscount.setText(sum);
				
			
		}
				
			
		} 
		catch(NumberFormatException | SQLException e) 
		{
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void totalCGST()
	{
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select sum(CGST(%)) from ProductsData where Invoice_No=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1,txtInvoiceNo.getText());
			rs=ps.executeQuery();
		

			if(rs.next())
			{
				String sum=rs.getString("sum(CGST(%))");
				txtTotalCGST.setText(sum);
				
			
		}
				
			
		} 
		catch(NumberFormatException | SQLException e) 
		{
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void totalSGST()
	{
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select sum(SGST(%)) from ProductsData where Invoice_No=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1,txtInvoiceNo.getText());
			rs=ps.executeQuery();
		

			if(rs.next())
			{
				String sum=rs.getString("sum(SGST(%))");
				txtTotalSGST.setText(sum);
				
			
		}
				
			
		} 
		catch(NumberFormatException | SQLException e) 
		{
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void totalSGSTPrice()
	{
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select sum(SGST(Rs)) from ProductsData where Invoice_No=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1,txtInvoiceNo.getText());
			rs=ps.executeQuery();
		

			if(rs.next())
			{
				String sum=rs.getString("sum(SGST(Rs))");
				txtTotalSGSTPrice.setText(sum);
				
			
		}
				
			
		} 
		catch(NumberFormatException | SQLException e) 
		{
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void totalCGSTPrice()
	{
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select sum(CGST(Rs)) from ProductsData where Invoice_No=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1,txtInvoiceNo.getText());
			rs=ps.executeQuery();
		

			if(rs.next())
			{
				String sum=rs.getString("sum(CGST(Rs))");
				txtTotalCGSTPrice.setText(sum);
				
			
		}
				
			
		} 
		catch(NumberFormatException | SQLException e) 
		{
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void totalGSTPrice()
	{
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select sum(GST(Rs)) from ProductsData where Invoice_No=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1,txtInvoiceNo.getText());
			rs=ps.executeQuery();
		

			if(rs.next())
			{
				String sum=rs.getString("sum(GST(Rs))");
				txtTotalGSTPrice.setText(sum);
				
			
		}
				
			
		} 
		catch(NumberFormatException | SQLException e) 
		{
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void totalGST()
	{
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select sum(GST(%)) from ProductsData where Invoice_No=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1,txtInvoiceNo.getText());
			rs=ps.executeQuery();
		

			if(rs.next())
			{
				String sum=rs.getString("sum(GST(%))");
				txtTotalGST.setText(sum);
				
			
		}
				
			
		} 
		catch(NumberFormatException | SQLException e) 
		{
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
 //   
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateBillShop frame = new CreateBillShop();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
    
	
	public void ShowDataInvoiceNo()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			String sql="select Sr_No,Products,Serial_No,Module_No,Rate_Rs,CGST_in_per,CGST_in_Rs,SGST_in_per,SGST_in_Rs,GST_in_per,GST_in_Rs,Actal_Price,Discount_in_per,Discount_in_Rs,Quantity,Discount_Price,Total from ProductsData where Invoice_No=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,txtInvoiceNo.getText());
			rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	

	/**
	 * Create the frame.
	 */
	public CreateBillShop()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
			
		} 
		catch(Exception e)
		{
			
		}
		
		setTitle("Create Invoice");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreateBillShop.class.getResource("/images/plug.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblNewLabel.setBounds(29, 39, 50, 24);
		contentPane.add(lblNewLabel);
		
		txtDate = new JTextField();
		txtDate.setForeground(new Color(0, 0, 128));
		txtDate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtDate.setBounds(82, 41, 104, 25);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setForeground(new Color(255, 255, 255));
		lblTime.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTime.setBounds(216, 39, 50, 24);
		contentPane.add(lblTime);
		
		JLabel lblInvoiceNo = new JLabel("Invoice No:");
		lblInvoiceNo.setForeground(new Color(255, 255, 255));
		lblInvoiceNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblInvoiceNo.setBounds(29, 70, 106, 24);
		contentPane.add(lblInvoiceNo);
		
		txtTime = new JTextField();
		txtTime.setForeground(new Color(0, 0, 128));
		txtTime.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTime.setColumns(10);
		txtTime.setBounds(271, 39, 104, 25);
		contentPane.add(txtTime);
		
		txtInvoiceNo = new JTextField();
		txtInvoiceNo.setForeground(new Color(0, 0, 128));
		txtInvoiceNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtInvoiceNo.setColumns(10);
		txtInvoiceNo.setBounds(173, 70, 202, 25);
		contentPane.add(txtInvoiceNo);
		
		JLabel lblCustomerName = new JLabel("Customer:");
		lblCustomerName.setForeground(new Color(255, 255, 255));
		lblCustomerName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblCustomerName.setBounds(29, 100, 143, 24);
		contentPane.add(lblCustomerName);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setForeground(new Color(0, 0, 128));
		txtCustomerName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(173, 100, 357, 25);
		contentPane.add(txtCustomerName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblAddress.setBounds(29, 130, 143, 24);
		contentPane.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setForeground(new Color(0, 0, 128));
		txtAddress.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtAddress.setColumns(10);
		txtAddress.setBounds(173, 130, 357, 25);
		contentPane.add(txtAddress);
		
		JLabel lblContact = new JLabel("Contact:");
		lblContact.setForeground(Color.WHITE);
		lblContact.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblContact.setBounds(29, 160, 143, 24);
		contentPane.add(lblContact);
		
		Contact = new JTextField();
		Contact.setForeground(new Color(0, 0, 128));
		Contact.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		Contact.setColumns(10);
		Contact.setBounds(173, 160, 199, 25);
		contentPane.add(Contact);
		
		JLabel lblAddCustomerDetails = new JLabel("Add Customer Details");
		lblAddCustomerDetails.setForeground(Color.WHITE);
		lblAddCustomerDetails.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblAddCustomerDetails.setBounds(82, 10, 260, 30);
		contentPane.add(lblAddCustomerDetails);
		
		JLabel lblAddProductDetails = new JLabel("Add Product Details");
		lblAddProductDetails.setForeground(Color.WHITE);
		lblAddProductDetails.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblAddProductDetails.setBounds(82, 194, 260, 30);
		contentPane.add(lblAddProductDetails);
		
		JLabel lblProductNo = new JLabel("Record No:");
		lblProductNo.setForeground(Color.WHITE);
		lblProductNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblProductNo.setBounds(29, 230, 134, 24);
		contentPane.add(lblProductNo);
		
		txtProductNo = new JTextField();
		txtProductNo.setForeground(new Color(0, 0, 128));
		txtProductNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtProductNo.setColumns(10);
		txtProductNo.setBounds(173, 230, 104, 25);
		contentPane.add(txtProductNo);
		
		JLabel lblSrno = new JLabel("No:");
		lblSrno.setForeground(Color.WHITE);
		lblSrno.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblSrno.setBounds(29, 260, 106, 24);
		contentPane.add(lblSrno);
		
		txtSrNo = new JTextField();
		txtSrNo.setForeground(new Color(0, 0, 128));
		txtSrNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSrNo.setColumns(10);
		txtSrNo.setBounds(173, 260, 104, 25);
		contentPane.add(txtSrNo);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblCategory.setBounds(29, 290, 106, 24);
		contentPane.add(lblCategory);
		
		
		
		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setForeground(Color.WHITE);
		lblProductName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblProductName.setBounds(29, 320, 134, 24);
		contentPane.add(lblProductName);
		
		JComboBox cbName = new JComboBox();
		cbName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
					String sql="SELECT * FROM StockData WHERE Product_Name=?";
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					PreparedStatement ps=con.prepareStatement(sql);
					String pname=(String) cbName.getSelectedItem();
					
                    ps.setString(1,pname);
                    ResultSet rs=ps.executeQuery();
					
					while(rs.next())
					{
						String Rate=rs.getString("Price");
						txtRateRs.setText(Rate);
						String SerialNoP=rs.getString("Serial_No");
						txtSerialNo.setText(SerialNoP);
						String ModuleNoP=rs.getString("Module_No");
						txtModuleNo.setText(ModuleNoP);
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
					}
					
				} 
				catch(Exception e1)
				{
					
				}
				
				try
				{
					
					String s="select Quantity from StockData where Product_Name=?";
					ps=con.prepareStatement(s);
					
					String prod1=(String) cbName.getSelectedItem();
					
					ps.setString(1,prod1);
					
					rs=ps.executeQuery();
					
					while(rs.next())
					{
						
						String Quan=rs.getString("Quantity");
						
						int q5=Integer.parseInt(Quan);
						
						if(q5<=0)
						{
							
							String msg="Sorry! "+prod1+" Not available.";
							
							JOptionPane.showMessageDialog(null, msg);
						}
					}
				} 
				catch(Exception e1) {}
			}
		});
		cbName.setForeground(new Color(0, 0, 128));
		cbName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		cbName.setBounds(173, 320, 266, 25);
		contentPane.add(cbName);
		
		
		JComboBox cbCategory = new JComboBox();
		cbCategory.setForeground(new Color(0, 0, 128));
		cbCategory.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		cbCategory.addItem("Electronics");
		cbCategory.addItem("Electricals");
		cbCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					String sql="SELECT Product_Name FROM StockData WHERE Quantity>0 and Category=? and Quantity>0 ORDER BY Product_Name ASC";
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					PreparedStatement ps=con.prepareStatement(sql);
					String sname=(String) cbCategory.getSelectedItem();
					ps.setString(1,sname);
					
					ResultSet rs=ps.executeQuery();
					cbName.removeAllItems();
					while(rs.next())
					{
					String a=rs.getString(1);
					cbName.addItem(a);
					
					}
					
				} 
				catch(Exception e)
				{
					
				}
			}
		});
		cbCategory.setBounds(173, 290, 134, 25);
		contentPane.add(cbCategory);
		
		JLabel lblSerialNo = new JLabel("Serial No:");
		lblSerialNo.setForeground(Color.WHITE);
		lblSerialNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblSerialNo.setBounds(29, 350, 106, 24);
		contentPane.add(lblSerialNo);
		
		txtSerialNo = new JTextField();
		txtSerialNo.setForeground(new Color(0, 0, 128));
		txtSerialNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSerialNo.setColumns(10);
		txtSerialNo.setBounds(173, 350, 266, 25);
		contentPane.add(txtSerialNo);
		
		JLabel lblModuleNo = new JLabel("Module No:");
		lblModuleNo.setForeground(Color.WHITE);
		lblModuleNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblModuleNo.setBounds(29, 380, 106, 24);
		contentPane.add(lblModuleNo);
		
		txtModuleNo = new JTextField();
		txtModuleNo.setForeground(new Color(0, 0, 128));
		txtModuleNo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtModuleNo.setColumns(10);
		txtModuleNo.setBounds(173, 380, 266, 25);
		contentPane.add(txtModuleNo);
		
		JLabel lblRateRs = new JLabel("Rate Rs:");
		lblRateRs.setForeground(Color.WHITE);
		lblRateRs.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRateRs.setBounds(29, 410, 106, 24);
		contentPane.add(lblRateRs);
		
		txtRateRs = new JTextField();
		txtRateRs.setForeground(new Color(0, 0, 128));
		txtRateRs.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtRateRs.setColumns(10);
		txtRateRs.setBounds(173, 410, 125, 25);
		contentPane.add(txtRateRs);
		
		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setForeground(Color.WHITE);
		lblDiscount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblDiscount.setBounds(29, 590, 209, 24);
		contentPane.add(lblDiscount);
		
		txtDiscount = new JTextField();
		txtDiscount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				
					String r1=txtRateRs.getText();
					float rate=Float.parseFloat(r1);
			//		JOptionPane.showMessageDialog(null,rate);
					
					String d1=txtDiscount.getText();
					float discountInPer=Float.parseFloat(d1);
			//		JOptionPane.showMessageDialog(null,discountInPer);
					
					String q1=txtQuantity.getText();
					float quantity=Float.parseFloat(q1);
			//		JOptionPane.showMessageDialog(null,quantity);

					
					float discount=(discountInPer*rate)/100;
			//		JOptionPane.showMessageDialog(null,discount);
                    txtDis.setText(String.valueOf(discount));

					float unitDiscount=rate-discount;
			//		JOptionPane.showMessageDialog(null,unitDiscount);

					
					txtDiscountPrice.setText(String.valueOf(unitDiscount));
					float total=unitDiscount*quantity;
			//		JOptionPane.showMessageDialog(null,total);

					
					txtTotal.setText(String.valueOf(total));
			//		txtDis.setText(String.valueOf(discount));
			}
		
		});
		txtDiscount.setForeground(new Color(0, 0, 128));
		txtDiscount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(173, 590, 125, 25);
		contentPane.add(txtDiscount);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setForeground(Color.WHITE);
		lblQuantity.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblQuantity.setBounds(29, 560, 106, 24);
		contentPane.add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				try
				{
					
					String s="select Quantity from StockData where Product_Name=?";
					ps=con.prepareStatement(s);
					
					String prod1=(String) cbName.getSelectedItem();
					
					ps.setString(1,prod1);
					
					rs=ps.executeQuery();
					
					while(rs.next())
					{
						
						String Quan=rs.getString("Quantity");
						
						int q5=Integer.parseInt(Quan);
						
						
						String q6=txtQuantity.getText();
						int quantity1=Integer.parseInt(q6);
						
						if(q5>=quantity1 && q5>=0)
						{
							String r1=txtRateRs.getText();
							float rate=Float.parseFloat(r1);
							
							String d1=txtDiscount.getText();
							float discountInPer=Float.parseFloat(d1);
							
							String q1=txtQuantity.getText();
							float quantity=Float.parseFloat(q1);
							
							float discount=(discountInPer*rate)/100;
							float unitDiscount=rate-discount;
							
							txtDiscountPrice.setText(String.valueOf(unitDiscount));
							float total=unitDiscount*quantity;
							
							txtTotal.setText(String.valueOf(total));
						}
						else if(q5>0 && q5<quantity1)
						{
							String msg2="Only "+q5+" Pieces are available.";
							JOptionPane.showMessageDialog(null,msg2);
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Unavailable");
						}
					}
					}
			
				catch(Exception e1) {}
			}
		});
		txtQuantity.setForeground(new Color(0, 0, 128));
		txtQuantity.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(173, 560, 125, 25);
		contentPane.add(txtQuantity);
		
		JLabel lblDiscountPrice = new JLabel("Discount Price:");
		lblDiscountPrice.setForeground(Color.WHITE);
		lblDiscountPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblDiscountPrice.setBounds(29, 620, 143, 24);
		contentPane.add(lblDiscountPrice);
		
		txtDiscountPrice = new JTextField();
		txtDiscountPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				try {
					String rr1=txtRateRs.getText();
					float rate=Float.parseFloat(rr1);
					
					String dd1=txtDiscountPrice.getText();
					float discountPrice=Float.parseFloat(dd1);
					
					float discount=rate-discountPrice;
					txtDis.setText(String.valueOf(discount));
					float DiscountInPercentage=(discount*100)/rate;
					
					txtDiscount.setText(String.valueOf(DiscountInPercentage));
					
					String quantity1=txtQuantity.getText();
					float quantity2=Float.parseFloat(quantity1);
					
					float total1=discountPrice*quantity2;
					txtTotal.setText(String.valueOf(total1));

					}
					catch(NumberFormatException e2)
					{
						
					}
			}
		});
		txtDiscountPrice.setForeground(new Color(0, 0, 128));
		txtDiscountPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtDiscountPrice.setColumns(10);
		txtDiscountPrice.setBounds(173, 620, 125, 25);
		contentPane.add(txtDiscountPrice);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTotal.setBounds(29, 650, 106, 24);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setForeground(new Color(0, 0, 128));
		txtTotal.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTotal.setColumns(10);
		txtTotal.setBounds(173, 650, 125, 25);
		contentPane.add(txtTotal);
		
		JLabel lblRs = new JLabel("Rs");
		lblRs.setForeground(Color.WHITE);
		lblRs.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs.setBounds(300, 410, 106, 24);
		contentPane.add(lblRs);
		
		JLabel lblRs_1 = new JLabel("%");
		lblRs_1.setForeground(Color.WHITE);
		lblRs_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_1.setBounds(300, 590, 106, 24);
		contentPane.add(lblRs_1);
		
		JLabel lblUnits = new JLabel("Units");
		lblUnits.setForeground(Color.WHITE);
		lblUnits.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblUnits.setBounds(300, 560, 106, 24);
		contentPane.add(lblUnits);
		
		JLabel lblRs_2 = new JLabel("Rs");
		lblRs_2.setForeground(Color.WHITE);
		lblRs_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_2.setBounds(300, 620, 106, 24);
		contentPane.add(lblRs_2);
		
		JLabel lblRs_3 = new JLabel("Rs");
		lblRs_3.setForeground(Color.WHITE);
		lblRs_3.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3.setBounds(300, 650, 106, 24);
		contentPane.add(lblRs_3);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					
					String sql="select Quantity from StockData where Product_Name=?";
					ps=con.prepareStatement(sql);
					
					String Prod_Name=(String) cbName.getSelectedItem();
		//			JOptionPane.showMessageDialog(null,Prod_Name);
		//			System.out.println(Prod_Name);
					ps.setString(1,Prod_Name);
					
					rs=ps.executeQuery();
					
					while(rs.next())
					{
						String qq=txtQuantity.getText();
						int qq1=Integer.parseInt(qq);
						String q2=rs.getString("Quantity");
						
						int q3=Integer.parseInt(q2);	
						int q4=q3-qq1;
						String Final_Quantity=String.valueOf(q4);
						
						try 
						{
						
						String sql3="UPDATE StockData SET Quantity=? WHERE Product_Name=?";	
						con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
						ps=con.prepareStatement(sql3);
						
						String Prod=(String) cbName.getSelectedItem();
						ps.setString(2,Prod);
						
						
						ps.setString(1,Final_Quantity);
						ps.executeUpdate();
			//			JOptionPane.showMessageDialog(null,"Stock updated");
						}
						catch(Exception e1)
						{
			//				JOptionPane.showMessageDialog(null,"Stock updated failed");
						}
					}
					
				} 
				catch(Exception e5) {}
				
				
				try {
					itemName.add((String) cbName.getSelectedItem());
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
				
				
				
				try 
				{
				String sql1="insert into ProductsData values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql1);
				ps.setString(1,txtProductNo.getText());
				ps.setString(2,txtSrNo.getText());
				String Category=(String) cbCategory.getSelectedItem();
				ps.setString(3,Category);
				String pname=(String) cbName.getSelectedItem();
				ps.setString(4,pname);
				ps.setString(5,txtSerialNo.getText());
				ps.setString(6,txtModuleNo.getText());
				ps.setString(7,txtRateRs.getText());
				ps.setString(8,txtDiscount.getText());
				ps.setString(9,txtQuantity.getText());
				ps.setString(10,txtDiscountPrice.getText());
				ps.setString(11,txtTotal.getText());
				ps.setString(12,txtInvoiceNo.getText());
				ps.setString(13,txtDate.getText());
				ps.setString(14,txtTime.getText());
				ps.setString(15,txtDis.getText());
				ps.setString(16,txtCGST.getText());
				ps.setString(17,txtCGSTPrice.getText());
				ps.setString(18,txtSGST.getText());
				ps.setString(19,txtSGSTPrice.getText());
				ps.setString(20,txtGST.getText());
				ps.setString(21,txtGSTPrice.getText());
				ps.setString(22,txtActualPrice.getText());
				
				ps.executeUpdate();
	//			JOptionPane.showMessageDialog(null,"inserted into ProductsData");
				
				cbCategory.setSelectedItem("Electronics");
				txtSrNo.setText("");
				txtSerialNo.setText("");
				txtModuleNo.setText("");
				txtRateRs.setText("");
				txtDiscount.setText("");
				txtDiscountPrice.setText("");
				txtQuantity.setText("");
				txtTotal.setText("");
				txtDis.setText("");
				txtCGSTPrice.setText("");
				txtCGST.setText("");
				txtSGSTPrice.setText("");
				txtSGST.setText("");
				txtGST.setText("");
				txtGSTPrice.setText("");
				txtActualPrice.setText("");
			    }
				catch(Exception e1) 
				{
					JOptionPane.showMessageDialog(null,e1);	
				}
				
				try 
				{
					String sql11="insert into BillsData values(?,?)";
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					ps=con.prepareStatement(sql11);
					ps.setString(1,txtInvoiceNo.getText());
					ps.setString(2,txtProductNo.getText());
					ps.executeUpdate();
		//			JOptionPane.showMessageDialog(null,"inserted into BillsData");
				} 
				catch(Exception e2)
				{
					JOptionPane.showMessageDialog(null,e2);
				}
				
		
				ShowDataInvoiceNo();
				total();
				totalDiscount();
				ProductNo();
				totalCGST();
				totalSGST();
				totalCGSTPrice();
				totalSGSTPrice();
				totalGST();
				totalGSTPrice();
			}
		});
		btnAdd.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnAdd.setBounds(173, 685, 134, 28);
		contentPane.add(btnAdd);
		
		JButton btnNewInvoice = new JButton("New Invoice");
		btnNewInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new CreateBillShop().setVisible(true);
			}
		});
		btnNewInvoice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnNewInvoice.setBounds(605, 685, 177, 28);
		contentPane.add(btnNewInvoice);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new HomeShop().setVisible(true);
				CreateBillShop.this.dispose();
			}
		});
		btnCancel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnCancel.setBounds(936, 685, 134, 28);
		contentPane.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(540, 10, 802, 537);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int i=table.getSelectedRow();
				txtProductNo.setText(model.getValueAt(i,0).toString());
				txtSrNo.setText(model.getValueAt(i,1).toString());
				cbName.setSelectedItem(model.getValueAt(i,2).toString());
				txtSerialNo.setText(model.getValueAt(i,3).toString());
				txtModuleNo.setText(model.getValueAt(i,4).toString());
				txtRateRs.setText(model.getValueAt(i,5).toString());
				txtDiscount.setText(model.getValueAt(i,6).toString());
				txtQuantity.setText(model.getValueAt(i,7).toString());
				txtDiscountPrice.setText(model.getValueAt(i,8).toString());
				txtTotal.setText(model.getValueAt(i,9).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblTotalAmmount = new JLabel("Total Amount:");
		lblTotalAmmount.setForeground(Color.WHITE);
		lblTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTotalAmmount.setBounds(1042, 590, 241, 24);
		contentPane.add(lblTotalAmmount);
		
		txtTotalAmmount = new JTextField();
		txtTotalAmmount.setForeground(new Color(0, 0, 128));
		txtTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTotalAmmount.setColumns(10);
		txtTotalAmmount.setBounds(1208, 590, 104, 25);
		contentPane.add(txtTotalAmmount);
		
		JLabel lblRs_4 = new JLabel("Rs");
		lblRs_4.setForeground(Color.WHITE);
		lblRs_4.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_4.setBounds(1316, 590, 26, 24);
		contentPane.add(lblRs_4);
		
		JLabel lblPaidAmmount = new JLabel("Paid Amount:");
		lblPaidAmmount.setForeground(Color.WHITE);
		lblPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPaidAmmount.setBounds(1042, 620, 199, 24);
		contentPane.add(lblPaidAmmount);
		
		txtPaidAmmount = new JTextField();
		txtPaidAmmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				String p1=txtPaidAmmount.getText();
				float paid=Float.parseFloat(p1);
				String t5=txtTotalAmmount.getText();
				float total2=Float.parseFloat(t5);
				float pending=total2-paid;
				txtPendingAmmount.setText(String.valueOf(pending));
			}
		});
		txtPaidAmmount.setForeground(new Color(0, 0, 128));
		txtPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPaidAmmount.setColumns(10);
		txtPaidAmmount.setBounds(1208, 620, 104, 25);
		contentPane.add(txtPaidAmmount);
		
		JLabel lblRs_2_1 = new JLabel("Rs");
		lblRs_2_1.setForeground(Color.WHITE);
		lblRs_2_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_2_1.setBounds(1316, 620, 26, 24);
		contentPane.add(lblRs_2_1);
		
		JLabel lblPendingAmmount = new JLabel("Pending Amount:");
		lblPendingAmmount.setForeground(Color.WHITE);
		lblPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblPendingAmmount.setBounds(1042, 650, 171, 24);
		contentPane.add(lblPendingAmmount);
		
		txtPendingAmmount = new JTextField();
		txtPendingAmmount.setForeground(new Color(0, 0, 128));
		txtPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtPendingAmmount.setColumns(10);
		txtPendingAmmount.setBounds(1208, 650, 104, 25);
		contentPane.add(txtPendingAmmount);
		
		JLabel lblRs_3_1 = new JLabel("Rs");
		lblRs_3_1.setForeground(Color.WHITE);
		lblRs_3_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3_1.setBounds(1316, 650, 26, 24);
		contentPane.add(lblRs_3_1);
		
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
				String sql1="insert into CustomerData values(?,?,?,?,?,?,?,?,?)";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
				ps=con.prepareStatement(sql1);
				ps.setString(1,txtInvoiceNo.getText());
				ps.setString(2,txtDate.getText());
				ps.setString(3,txtTime.getText());
				ps.setString(4,txtCustomerName.getText());
				ps.setString(5,txtAddress.getText());
				ps.setString(6,Contact.getText());
				ps.setString(7,txtTotalAmmount.getText());
				ps.setString(8,txtPaidAmmount.getText());
				ps.setString(9,txtPendingAmmount.getText());
				
				
				ps.executeUpdate();
	//			JOptionPane.showMessageDialog(null,"inserted into CustomerData");
				txtCustomerName.setText("");
				Contact.setText("");
				txtTotalAmmount.setText("");
				txtPaidAmmount.setText("");
				txtPendingAmmount.setText("");
				txtAddress.setText("");
				textTotalDiscount.setText("");
				
				}
				
				catch(Exception e1) 
				{
	//				JOptionPane.showMessageDialog(null,e1);	
				}
				InvoiceNo();
				total();
				ShowDataInvoiceNo();
				
				
				
			}
		});
		btnPrint.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnPrint.setBounds(792, 685, 134, 28);
		contentPane.add(btnPrint);
		
		JButton btnDiscard = new JButton("Discard");
		btnDiscard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int a=JOptionPane.showConfirmDialog(null,"Are you sure you want to discard this Product?","Delete this Record?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			    if(a==JOptionPane.YES_OPTION)
			    {
			    	try 
					{
					String sql2="DELETE FROM ProductsData WHERE Product_No=?";	
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					ps=con.prepareStatement(sql2);
					ps.setString(1,txtProductNo.getText());
					ps.executeUpdate();
			//		JOptionPane.showMessageDialog(null,"deleted from ProductsData");

					
					ProductNo();
					txtSrNo.setText(null);
				    cbCategory.setSelectedItem("Electronics");
					txtSerialNo.setText(null);
					txtModuleNo.setText(null);
					txtRateRs.setText(null);
					txtDiscount.setText(null);
					txtQuantity.setText(null);
				    txtDiscountPrice.setText(null);
					txtTotal.setText(null);
					txtDis.setText("");
					}
					catch(Exception e1) {}
			    	
			    	try 
					{
					String sql2="DELETE FROM BillsData WHERE Product_No=?";	
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics1","root","vishakha");
					ps=con.prepareStatement(sql2);
					ps.setString(1,txtProductNo.getText());
					ps.executeUpdate();
	//				JOptionPane.showMessageDialog(null,"deleted from BillsData");
					
					ProductNo();
					txtSrNo.setText(null);
				    cbCategory.setSelectedItem("Electronics");
					txtSerialNo.setText(null);
					txtModuleNo.setText(null);
					txtRateRs.setText(null);
					txtDiscount.setText(null);
					txtQuantity.setText(null);
				    txtDiscountPrice.setText(null);
					txtTotal.setText(null);
					txtDis.setText("");
					}
					catch(Exception e1) {}
			    	
			    }
				total();
				ShowDataInvoiceNo();
			
			}
		});
		btnDiscard.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnDiscard.setBounds(317, 685, 134, 28);
		contentPane.add(btnDiscard);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ProductNo();
				txtSrNo.setText(null);
			    cbCategory.setSelectedItem("Electronics");
				txtSerialNo.setText(null);
				txtModuleNo.setText(null);
				txtRateRs.setText(null);
				txtDiscount.setText(null);
				txtQuantity.setText(null);
				txtDiscountPrice.setText(null);
				txtTotal.setText(null);	
				txtDis.setText("");
			}
		});
		btnReset.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnReset.setBounds(461, 685, 134, 28);
		contentPane.add(btnReset);
		
		txtDis = new JTextField();
		txtDis.setForeground(new Color(0, 0, 128));
		txtDis.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtDis.setColumns(10);
		txtDis.setBounds(325, 590, 104, 25);
		contentPane.add(txtDis);
		
		JLabel lblRs_2_2 = new JLabel("Rs");
		lblRs_2_2.setForeground(Color.WHITE);
		lblRs_2_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_2_2.setBounds(433, 590, 106, 24);
		contentPane.add(lblRs_2_2);
		
		JLabel lblTotalAmmount_1 = new JLabel("Discount Amount:");
		lblTotalAmmount_1.setForeground(Color.WHITE);
		lblTotalAmmount_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblTotalAmmount_1.setBounds(1042, 560, 241, 24);
		contentPane.add(lblTotalAmmount_1);
		
		textTotalDiscount = new JTextField();
		textTotalDiscount.setForeground(new Color(0, 0, 128));
		textTotalDiscount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		textTotalDiscount.setColumns(10);
		textTotalDiscount.setBounds(1208, 560, 104, 25);
		contentPane.add(textTotalDiscount);
		
		JLabel lblRs_4_1 = new JLabel("Rs");
		lblRs_4_1.setForeground(Color.WHITE);
		lblRs_4_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_4_1.setBounds(1316, 560, 26, 24);
		contentPane.add(lblRs_4_1);
		

		clock();
		date();
		InvoiceNo();
		CustomerData();
		ProductNo();
		ProductsData();
		BillsData();
		
		JLabel lblCgst = new JLabel("CGST:");
		lblCgst.setForeground(Color.WHITE);
		lblCgst.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblCgst.setBounds(29, 440, 132, 22);
		contentPane.add(lblCgst);
		
		txtCGST = new JTextField();
		txtCGST.setForeground(new Color(0, 0, 128));
		txtCGST.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtCGST.setColumns(10);
		txtCGST.setBounds(173, 440, 125, 25);
		contentPane.add(txtCGST);
		
		JLabel lblRs_1_1 = new JLabel("%");
		lblRs_1_1.setForeground(Color.WHITE);
		lblRs_1_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_1_1.setBounds(300, 440, 33, 26);
		contentPane.add(lblRs_1_1);
		
		txtCGSTPrice = new JTextField();
		txtCGSTPrice.setForeground(new Color(0, 0, 128));
		txtCGSTPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtCGSTPrice.setColumns(10);
		txtCGSTPrice.setBounds(325, 441, 125, 25);
		contentPane.add(txtCGSTPrice);
		
		JLabel lblRs_3_1_2 = new JLabel("Rs");
		lblRs_3_1_2.setForeground(Color.WHITE);
		lblRs_3_1_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3_1_2.setBounds(451, 441, 33, 24);
		contentPane.add(lblRs_3_1_2);
		
		JLabel lblSgst = new JLabel("SGST");
		lblSgst.setForeground(Color.WHITE);
		lblSgst.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblSgst.setBounds(29, 470, 132, 22);
		contentPane.add(lblSgst);
		
		txtSGST = new JTextField();
		txtSGST.setForeground(new Color(0, 0, 128));
		txtSGST.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSGST.setColumns(10);
		txtSGST.setBounds(173, 470, 125, 25);
		contentPane.add(txtSGST);
		
		JLabel lblRs_2_3 = new JLabel("%");
		lblRs_2_3.setForeground(Color.WHITE);
		lblRs_2_3.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_2_3.setBounds(300, 470, 33, 26);
		contentPane.add(lblRs_2_3);
		
		txtSGSTPrice = new JTextField();
		txtSGSTPrice.setForeground(new Color(0, 0, 128));
		txtSGSTPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtSGSTPrice.setColumns(10);
		txtSGSTPrice.setBounds(325, 471, 125, 25);
		contentPane.add(txtSGSTPrice);
		
		JLabel lblRs_3_1_1 = new JLabel("Rs");
		lblRs_3_1_1.setForeground(Color.WHITE);
		lblRs_3_1_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3_1_1.setBounds(451, 471, 33, 24);
		contentPane.add(lblRs_3_1_1);
		
		JLabel lblRs_3_1_3 = new JLabel("Rs");
		lblRs_3_1_3.setForeground(Color.WHITE);
		lblRs_3_1_3.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3_1_3.setBounds(451, 501, 33, 24);
		contentPane.add(lblRs_3_1_3);
		
		txtGSTPrice = new JTextField();
		txtGSTPrice.setForeground(new Color(0, 0, 128));
		txtGSTPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtGSTPrice.setColumns(10);
		txtGSTPrice.setBounds(325, 501, 125, 25);
		contentPane.add(txtGSTPrice);
		
		JLabel lblRs_3_2 = new JLabel("%");
		lblRs_3_2.setForeground(Color.WHITE);
		lblRs_3_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3_2.setBounds(300, 500, 33, 26);
		contentPane.add(lblRs_3_2);
		
		txtGST = new JTextField();
		txtGST.setForeground(new Color(0, 0, 128));
		txtGST.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtGST.setColumns(10);
		txtGST.setBounds(173, 500, 125, 25);
		contentPane.add(txtGST);
		
		JLabel lblGst = new JLabel("GST");
		lblGst.setForeground(Color.WHITE);
		lblGst.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblGst.setBounds(29, 500, 132, 22);
		contentPane.add(lblGst);
		
		JLabel lblActualPrice = new JLabel("Actual Price:");
		lblActualPrice.setForeground(Color.WHITE);
		lblActualPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblActualPrice.setBounds(31, 530, 170, 26);
		contentPane.add(lblActualPrice);
		
		txtActualPrice = new JTextField();
		txtActualPrice.setForeground(new Color(0, 0, 128));
		txtActualPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtActualPrice.setColumns(10);
		txtActualPrice.setBounds(173, 530, 125, 25);
		contentPane.add(txtActualPrice);
		
		JLabel lblRs_4_2 = new JLabel("Rs");
		lblRs_4_2.setForeground(Color.WHITE);
		lblRs_4_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_4_2.setBounds(300, 530, 33, 26);
		contentPane.add(lblRs_4_2);
		
		JLabel lblCgst_1 = new JLabel("CGST:");
		lblCgst_1.setForeground(Color.WHITE);
		lblCgst_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblCgst_1.setBounds(540, 560, 132, 22);
		contentPane.add(lblCgst_1);
		
		JLabel lblSgst_1 = new JLabel("SGST");
		lblSgst_1.setForeground(Color.WHITE);
		lblSgst_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblSgst_1.setBounds(540, 590, 132, 22);
		contentPane.add(lblSgst_1);
		
		JLabel lblGst_1 = new JLabel("GST");
		lblGst_1.setForeground(Color.WHITE);
		lblGst_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblGst_1.setBounds(540, 620, 132, 22);
		contentPane.add(lblGst_1);
		
		txtTotalCGST = new JTextField();
		txtTotalCGST.setForeground(new Color(0, 0, 128));
		txtTotalCGST.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTotalCGST.setColumns(10);
		txtTotalCGST.setBounds(625, 560, 125, 25);
		contentPane.add(txtTotalCGST);
		
		txtTotalSGST = new JTextField();
		txtTotalSGST.setForeground(new Color(0, 0, 128));
		txtTotalSGST.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTotalSGST.setColumns(10);
		txtTotalSGST.setBounds(625, 590, 125, 25);
		contentPane.add(txtTotalSGST);
		
		txtTotalGST = new JTextField();
		txtTotalGST.setForeground(new Color(0, 0, 128));
		txtTotalGST.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTotalGST.setColumns(10);
		txtTotalGST.setBounds(625, 620, 125, 25);
		contentPane.add(txtTotalGST);
		
		JLabel lblRs_1_1_1 = new JLabel("%");
		lblRs_1_1_1.setForeground(Color.WHITE);
		lblRs_1_1_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_1_1_1.setBounds(752, 560, 33, 26);
		contentPane.add(lblRs_1_1_1);
		
		JLabel lblRs_2_3_1 = new JLabel("%");
		lblRs_2_3_1.setForeground(Color.WHITE);
		lblRs_2_3_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_2_3_1.setBounds(752, 590, 33, 26);
		contentPane.add(lblRs_2_3_1);
		
		JLabel lblRs_3_2_1 = new JLabel("%");
		lblRs_3_2_1.setForeground(Color.WHITE);
		lblRs_3_2_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3_2_1.setBounds(752, 620, 33, 26);
		contentPane.add(lblRs_3_2_1);
		
		txtTotalCGSTPrice = new JTextField();
		txtTotalCGSTPrice.setForeground(new Color(0, 0, 128));
		txtTotalCGSTPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTotalCGSTPrice.setColumns(10);
		txtTotalCGSTPrice.setBounds(777, 561, 125, 25);
		contentPane.add(txtTotalCGSTPrice);
		
		txtTotalSGSTPrice = new JTextField();
		txtTotalSGSTPrice.setForeground(new Color(0, 0, 128));
		txtTotalSGSTPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTotalSGSTPrice.setColumns(10);
		txtTotalSGSTPrice.setBounds(777, 591, 125, 25);
		contentPane.add(txtTotalSGSTPrice);
		
		txtTotalGSTPrice = new JTextField();
		txtTotalGSTPrice.setForeground(new Color(0, 0, 128));
		txtTotalGSTPrice.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTotalGSTPrice.setColumns(10);
		txtTotalGSTPrice.setBounds(777, 621, 125, 25);
		contentPane.add(txtTotalGSTPrice);
		
		JLabel lblRs_3_1_2_1 = new JLabel("Rs");
		lblRs_3_1_2_1.setForeground(Color.WHITE);
		lblRs_3_1_2_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3_1_2_1.setBounds(903, 561, 33, 24);
		contentPane.add(lblRs_3_1_2_1);
		
		JLabel lblRs_3_1_1_1 = new JLabel("Rs");
		lblRs_3_1_1_1.setForeground(Color.WHITE);
		lblRs_3_1_1_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3_1_1_1.setBounds(903, 591, 33, 24);
		contentPane.add(lblRs_3_1_1_1);
		
		JLabel lblRs_3_1_3_1 = new JLabel("Rs");
		lblRs_3_1_3_1.setForeground(Color.WHITE);
		lblRs_3_1_3_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblRs_3_1_3_1.setBounds(903, 621, 33, 24);
		contentPane.add(lblRs_3_1_3_1);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginShop.class.getResource("/images/wallpaper2test.jpg")));
		lblNewLabel_3.setBounds(0,0,1366,768);
		contentPane.add(lblNewLabel_3);
		
		
	}
}
