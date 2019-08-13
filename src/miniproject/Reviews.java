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

public class Reviews extends JFrame{

	private JLabel reviewlabel, namelabel;
	private JButton backButton;
	private String city;
	
	public Reviews(String city) throws Exception
	{
		this.city = city;
        setTitle("Reviews");
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
        
        Class.forName("com.mysql.jdbc.Driver");
    	String constr = "jdbc:mysql://localhost/tourmanagement";
    	Connection con = (Connection)DriverManager.getConnection(constr,"root","root");
    	Statement sat=con.createStatement();
    	String query = "select *  from reviews where city='"+city+"'";
    	ResultSet rs=sat.executeQuery(query);
    	int i=0;
    	while(rs.next())
    	{
    			
    		if(rs.getString("review").equals("0"))
    		{
    			
    		}
    		else
    		{
    		reviewlabel = new JLabel(rs.getString("review"));
    		reviewlabel.setFont(new Font("Courier", Font.BOLD, 18));
    		reviewlabel.setForeground(new Color(255, 255, 255));
    		reviewlabel.setBounds(200, 20, 1000,600+60*i);
        	add(reviewlabel);
        	
    		
    		
    		namelabel = new JLabel(rs.getString("name"));
    		namelabel.setFont(new Font("Courier", Font.BOLD, 18));
    		namelabel.setForeground(new Color(255, 255, 255));
    		namelabel.setBounds(20, 20, 1000,600+60*i);
        	add(namelabel);
    		++i;
    		}
    		
    	
    	}
    		
    	con.close();
    	
	
	
	backButton.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent e) {

            try {
				backButtonActionPerformed(e);
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
	private void backButtonActionPerformed(ActionEvent e) throws HeadlessException, ClassNotFoundException, SQLException {

        new Frame(city).setVisible(true);
        this.setVisible(false);

    }

}
