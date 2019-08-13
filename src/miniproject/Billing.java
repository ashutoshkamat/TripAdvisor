package miniproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Billing extends JFrame {
	
	private JLabel nameJlabel, hotelfeeJLabel;
	private JLabel contactJlabel;
	private JLabel packageJLabel;
	private JLabel hotelJLabel;
	private JLabel noofPeopleJLabel;
	private JLabel rentOfHotelJLabel;
	private JLabel transportJLabel;
	private JLabel totalfeeJLabel;

	
	private JLabel nameJL, hotelfeeJL;
	private JLabel contactJL;
	private JLabel packageJL;
	private JLabel hotelJL;
	private JLabel noofPeopleJL;
	private JLabel rentOfHotelJL;
	private JLabel transportJL;
	private JLabel totalfeeJL;	
	
	private JButton confirm;
	
	
	public Billing() throws ClassNotFoundException, SQLException {

	
		setTitle("Bill");
		setSize(2600,1600);
		setLayout(null);

		nameJlabel = new JLabel("TAX INVOICE");
		nameJlabel.setBounds(550, 50, 150,30); 
		nameJlabel.setForeground(Color.BLACK);
		add(nameJlabel);
	
		Class.forName("com.mysql.jdbc.Driver");
    	String constr = "jdbc:mysql://localhost/tourmanagement";
    	Connection con = (Connection)DriverManager.getConnection(constr,"root","root");
    	Statement sat=con.createStatement();
    	
    	String query = "select *  from customer where contact='"+Login.contact+"'  and bookingstatus = '0'";
    	ResultSet rs=sat.executeQuery(query);
    	while(rs.next())
    	{
    	
		nameJlabel = new JLabel("Name : ");
		nameJlabel.setBounds(450, 100, 80,30); 
		nameJlabel.setForeground(Color.BLACK);
		add(nameJlabel);

		
		nameJL = new JLabel(rs.getString("name"));
		nameJL.setBounds(535, 100, 200,30); 
		nameJL.setForeground(Color.BLACK);
		add(nameJL);
		
		contactJlabel = new JLabel("Contact : ");
		contactJlabel.setBounds(435, 150, 80,30); 
		contactJlabel.setForeground(Color.BLACK);
		add(contactJlabel);

		
		contactJL = new JLabel(Login.contact);
		contactJL.setBounds(535, 150, 200,30); 
		contactJL.setForeground(Color.BLACK);
		add(contactJL);
		
		
		packageJLabel = new JLabel("Package : ");
		packageJLabel.setBounds(435, 200, 80,30); 
		packageJLabel.setForeground(Color.BLACK);
		add(packageJLabel);

		
		packageJL = new JLabel(rs.getString("package"));
		packageJL.setBounds(535, 200, 200,30); 
		packageJL.setForeground(Color.BLACK);
		add(packageJL);
		
		
		
		noofPeopleJLabel = new JLabel("No. of People : ");
		noofPeopleJLabel.setBounds(400, 250, 150,30); 
		noofPeopleJLabel.setForeground(Color.BLACK);
		add(noofPeopleJLabel);

		
		noofPeopleJL = new JLabel(rs.getString("noofpeople"));
		noofPeopleJL.setBounds(535, 250, 200,30); 
		noofPeopleJL.setForeground(Color.BLACK);
		add(noofPeopleJL);
		
		
		
		hotelJLabel = new JLabel("Hotel booked : ");
		hotelJLabel.setBounds(400, 300, 150,30); 
		hotelJLabel.setForeground(Color.BLACK);
		add(hotelJLabel);

		
		hotelJL = new JLabel(rs.getString("hotel"));
		hotelJL.setBounds(535, 300, 200,30); 
		hotelJL.setForeground(Color.BLACK);
		add(hotelJL);
		
		hotelfeeJLabel = new JLabel("Hotel Fee : ");
		hotelfeeJLabel.setBounds(440, 350, 150,30); 
		hotelfeeJLabel.setForeground(Color.BLACK);
		add(hotelfeeJLabel);

		Integer a=(rs.getInt("cost")-1000);
				
		hotelfeeJL = new JLabel(a.toString());
		hotelfeeJL.setBounds(535, 350, 200,30); 
		hotelfeeJL.setForeground(Color.BLACK);
		add(hotelfeeJL);
		
		
		transportJLabel = new JLabel("Transport fees : ");
		transportJLabel.setBounds(390, 400, 150,30); 
		transportJLabel.setForeground(Color.BLACK);
		add(transportJLabel);

		
		transportJL = new JLabel("1000");
		transportJL.setBounds(535, 400, 200,30); 
		transportJL.setForeground(Color.BLACK);
		add(transportJL);
		
		totalfeeJLabel = new JLabel("Total fees : ");
		totalfeeJLabel.setBounds(420, 450, 150,30); 
		totalfeeJLabel.setForeground(Color.BLACK);
		add(totalfeeJLabel);

		a=rs.getInt("cost");
			
		totalfeeJL = new JLabel(a.toString());
		totalfeeJL.setBounds(535, 450, 200,30); 
		totalfeeJL.setForeground(Color.BLACK);
		add(totalfeeJL);

		
		confirm = new JButton("Ok");
		confirm.setBounds(550, 500, 150,30); 
		confirm.setForeground(Color.BLACK);
		add(confirm);

		confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					confirmbuttonlistner(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

            }

        });


		
	
    	}    	
       
    	con.close();
	
	}
	
	void confirmbuttonlistner(ActionEvent e) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
    	String constr = "jdbc:mysql://localhost/tourmanagement";
    	Connection con = (Connection)DriverManager.getConnection(constr,"root","root");
    	
		PreparedStatement psat=(PreparedStatement) con.prepareStatement("update customer set bookingstatus = ? where contact = '"+Login.contact+"' and bookingstatus ='0'");
    	psat.setString(1, "1");
    	psat.executeUpdate();
    	JOptionPane.showMessageDialog(null, "Happy Journey !!");
        this.setVisible(false);
        
    	
    	con.close();
    	
	}

}