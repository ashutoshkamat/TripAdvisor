package miniproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NewRatings extends JFrame
{
		private JLabel ratingslabel;
		private JTextField ratingsfield;
		private JButton confirmbutton;

		public NewRatings()  
		{
		    setTitle("Provide Reviews");
	        setLayout(null);
	        setSize(400, 400);

	        try {
	            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(
	                    "/home/ashutosh/A3T3.jpg")))));
	        } catch (Exception ex) {
	            System.out.println(ex);
	        }
			ratingslabel = new JLabel("How was your trip?");
			ratingslabel.setBounds(500, 350, 500, 30);
			ratingslabel.setFont(new Font("Courier", Font.BOLD, 28));
			ratingslabel.setForeground(new Color(255, 255, 255));
			ratingsfield = new JTextField();
				       
			ratingsfield.setBounds(500, 400, 200, 50);
			
			
			confirmbutton=new JButton("SUBMIT");
	        confirmbutton.setBounds(500, 600, 250, 20);
	        
		        add(ratingsfield);
		        add(ratingslabel);
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
			Class.forName("com.mysql.jdbc.Driver");
	    	String constr = "jdbc:mysql://localhost/tourmanagement";
	    	Connection con = (Connection)DriverManager.getConnection(constr,"root","root");
	    	PreparedStatement sat=(PreparedStatement) con.prepareStatement("update reviews set review = ? where contact = '"+Login.contact+"' and review ='0'");
	    	sat.setString(1, ratingsfield.getText());
	    	sat.executeUpdate();
	        con.close();
	        JOptionPane.showMessageDialog(null, "Thanx for your valuable response");
	        this.setVisible(false);
	        
		}
		
		
}


