package miniproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;

public class Confirmation extends JFrame{

	private JLabel noofpeoplelabel;
	private JTextField noofpeoplefield;
	private JButton confirmbutton;
	private String city,hotel;
	int cost;

	public Confirmation(String city, String hotel, int cost)
	{
		this.hotel = hotel;
		this.city = city;
		this.cost = cost;
        setTitle("Confirm Booking");
        setLayout(null);
        setSize(400, 400);

        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(
                    "/home/ashutosh/A3T3.jpg")))));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        pack();
        
		noofpeoplelabel = new JLabel("How many tickets you want to book ?");
		noofpeoplelabel.setBounds(400, 350, 800, 30);
		noofpeoplelabel.setFont(new Font("Courier", Font.BOLD, 20));
		noofpeoplelabel.setForeground(new Color(255, 255, 255));

		noofpeoplefield = new JTextField(3);
		noofpeoplefield.setBounds(400, 400, 30, 20);
		
		
		confirmbutton=new JButton("CONFIRM BOOKING");
        confirmbutton.setBounds(590, 400, 250, 20);
        
	        add(noofpeoplefield);
	        add(noofpeoplelabel);
	        add(confirmbutton);
	   
	        
	     confirmbutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					confirmbuttonActionPerformed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}); 
	}
	
	
	public void confirmbuttonActionPerformed(ActionEvent e) throws Exception
	{
		if(noofpeoplefield.getText().equals("") || noofpeoplefield.getText().equals("0") )
    	{
    	    JOptionPane.showMessageDialog(null, "Enter valid credentials");
        }
    	else
    	{
    	
			Class.forName("com.mysql.jdbc.Driver");
			String constr = "jdbc:mysql://localhost/tourmanagement";
			Connection con = (Connection)DriverManager.getConnection(constr,"root","root");
			PreparedStatement sat=(PreparedStatement) con.prepareStatement("insert into customer values (?,?,?,?,?,?,?)");
			sat.setString(1, Login.name);
			sat.setString(2, Login.contact);
			sat.setString(3,city);
			sat.setInt(4, (Integer.parseInt(noofpeoplefield.getText())*cost+1000));
			sat.setString(5, hotel);
			sat.setString(6, noofpeoplefield.getText());
			sat.setString(7, "0");
    	
			sat.executeUpdate();
        
			sat=(PreparedStatement) con.prepareStatement("insert into reviews values (?,?,?,?)");
			sat.setString(1, Login.name);
			sat.setString(2,city);
			sat.setString(3,"0");
			sat.setString(4, (Login.contact));
			sat.executeUpdate();
			con.close();
        
 
			JOptionPane.showMessageDialog(null, "Booking Confirmed !");
			new Billing().setVisible(true);
        
			this.setVisible(false);
			}
		
		
	}
	
	
}
