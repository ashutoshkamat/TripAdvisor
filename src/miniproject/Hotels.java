package miniproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Hotels extends JFrame{
	int i=0;
	
	private String hotelname, city;
	private int cost;
	private JButton backButton, hotelsconfirmbutton[], hotelbutton;
	private JLabel hotelnamelabel[], hotelpricelabel[], choice;
	private JTextField  hoteltextfield;
    public Hotels(String city) throws HeadlessException, ClassNotFoundException, SQLException {
    
    	
    	this.city=city;
        setTitle("Wellcome to "+city);
        setLayout(null);
        setSize(400, 400);

        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(
                    "/home/ashutosh/A3T3.jpg")))));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        pack();
        backButton = new JButton("BACK");
        backButton.setBounds(10, 10, 80, 20);
        add(backButton);
        //Fetching city details from database
        Class.forName("com.mysql.jdbc.Driver");
    	String constr = "jdbc:mysql://localhost/tourmanagement";
    	Connection con = (Connection)DriverManager.getConnection(constr,"root","root");
    	Statement sat=con.createStatement();
    	String query = "select *  from hotels where city='"+city+"'";
    	ResultSet rs=sat.executeQuery(query);
    	hotelpricelabel = new JLabel[10];
    	hotelnamelabel = new JLabel[10];
    	hotelnamelabel[0] = new JLabel("Presenting highly rated hotels of "+city);
		hotelnamelabel[0].setFont(new Font("Courier", Font.BOLD, 18));
		hotelnamelabel[0].setForeground(new Color(255, 255, 255));
		hotelnamelabel[0].setBounds(200, 20, 1000,200);
    	add(hotelnamelabel[0]);
    	
    	hotelsconfirmbutton =new JButton[10];
    	
    	i = 0;
    	while(rs.next())
    	{
    		hotelpricelabel[i] = new JLabel(rs.getString("rate"));
    		hotelpricelabel[i].setFont(new Font("Courier", Font.BOLD, 18));
    		hotelpricelabel[i].setForeground(new Color(255, 255, 255));
    		hotelpricelabel[i].setBounds(200, 20, 1000,600+60*i);
        	add(hotelpricelabel[i]);
        	
    		
    		hotelnamelabel[i] = new JLabel(rs.getString("name"));
    		hotelnamelabel[i].setFont(new Font("Courier", Font.BOLD, 18));
    		hotelnamelabel[i].setForeground(new Color(255, 255, 255));
    		hotelnamelabel[i].setBounds(20, 20, 1000,600+60*i);
        	add(hotelnamelabel[i]);
        /*
        	hotelsconfirmbutton[i]=new JButton("Book "+hotelnamelabel[i].getText());
        	hotelsconfirmbutton[i].setBounds(320, 310+30*i, 1000, 20);
        	add(hotelsconfirmbutton[i]);
        	
        	hotelsconfirmbutton[i].addActionListener(new ActionListener() {
        	
        		
            public void actionPerformed(ActionEvent e) {
            	
            	System.out.println();
            	hotelsbuttonActionPerformed(e,hotelsconfirmbutton[i].getText());
            	
            }
        	});
        	*/
        	
        	++i;
        	
    	
    	}
    		
    	con.close();
    	choice = new JLabel("Enter hotel of your choice");
    	choice.setFont(new Font("Courier", Font.BOLD, 18));
    	choice.setForeground(new Color(255, 255, 255));
    	choice.setBounds(20, 600, 1000, 20);
    	add(choice);
    	
    	hoteltextfield=new JTextField();
    	hoteltextfield.setBounds(600, 600, 300, 20);
    	add(hoteltextfield);
    	
    	
    	hotelbutton=new JButton("Book");
    	hotelbutton.setBounds(550, 650, 100, 20);
    	add(hotelbutton);
    	hotelbutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				if(hoteltextfield.getText().equals("0"))
				{
					JOptionPane.showMessageDialog(null, "Fields could not be empty");
			        
				}
				else
					hotelsbuttonActionPerformed(ae, hoteltextfield.getText(), i);
			}
		});
    	
        backButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {

                backButtonActionPerformed(e);

            }

        });
        

    }
	private void backButtonActionPerformed(ActionEvent e) {

        new HomePage().setVisible(true);
        this.setVisible(false);

    }
	private void hotelsbuttonActionPerformed(ActionEvent e,String i, int count) {
		int n=0, fl = 0;
		hotelname = i;
		System.out.println(hotelnamelabel.length);
		for( n=0;n<count;n++)
		{
			if(hotelnamelabel[n].getText().equalsIgnoreCase(i))
			{
					fl = 1;
				break;
			}
		}
		if(fl == 0)
		{
			JOptionPane.showMessageDialog(null, "Hotel not found");
	        
		}
		else{
		cost = Integer.parseInt(hotelpricelabel[n].getText());
        new Confirmation(city, hotelname, cost).setVisible(true);
        this.setVisible(false);
		}
		
    }

}
