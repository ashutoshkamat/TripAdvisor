/*
 * To change this license sheader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;

import com.mysql.jdbc.PreparedStatement;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends JFrame {

    /**
	 * 
	 */
	public static String name, contact;
	private static final long serialVersionUID = 1L;
	private JLabel nameJLabel, welcomeJLabel;
    private JTextField nameJTextField;
    private JLabel contactJLabel;
    private JTextField contactJTextfield;
    private JButton loginButton;
    private HomePage home;

    public Login()  {

        setTitle("Login Window");
        setLayout(null);
        setSize(400, 400);

        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(
                   "/home/ashutosh/A3T3.jpg")))));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        pack();
        	
        
        welcomeJLabel = new JLabel("Login ");
        add(welcomeJLabel);
        welcomeJLabel.setBounds(430, 290, 100, 30);
        welcomeJLabel.setFont(new Font("Courier", Font.BOLD, 25));
        welcomeJLabel.setForeground(new Color(255, 255, 255));
        
        nameJLabel = new JLabel("Name :");
        add(nameJLabel);
        nameJLabel.setBounds(430, 400, 100, 30);
        nameJLabel.setFont(new Font("Courier", Font.BOLD, 25));
        nameJLabel.setForeground(new Color(255, 255, 255));

        nameJTextField = new JTextField(20);
        add(nameJTextField);
        nameJTextField.setBounds(530, 400, 300, 30);
        contactJLabel = new JLabel("Contact :");
        add(contactJLabel);
        
        contactJLabel.setBounds(390, 460, 150, 30);
        contactJLabel.setFont(new Font("Courier", Font.BOLD, 25));
        contactJLabel.setForeground(new Color(255, 255, 255));
        
        contactJTextfield = new JTextField(20);
        add(contactJTextfield);
        contactJTextfield.setBounds(530, 460, 300, 30);

        loginButton = new JButton();
        add(loginButton);
        loginButton.setToolTipText("Confirm And Continue");
        loginButton.setBounds(530, 550, 90, 60);
        
        try {
          loginButton.setIcon(new ImageIcon(ImageIO.read(new File(
                   "/home/ashutosh/h.png"))));
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					loginButtonActionListener(e);
				} catch (Exception e1){}

            }

        });

        home = new HomePage();
    	

    }
    
    
	
    private void loginButtonActionListener(ActionEvent e)  {

    	    	
    	name = nameJTextField.getText();
    	contact=contactJTextfield.getText();
    	
    	
    	
    	if(name.equalsIgnoreCase("") || contact.equalsIgnoreCase("")){
    		JOptionPane.showMessageDialog(null, "Fields cannot be empty");
    	}
    	else if(contact.length()<10)
    	{
    		JOptionPane.showMessageDialog(null, "Enter valid mobile number");
        		
    	}
    	else{
    	
    	try{
    		
    		
			Class.forName("com.mysql.jdbc.Driver");
		String constr = "jdbc:mysql://localhost/tourmanagement";
    	Connection con = (Connection)DriverManager.getConnection(constr,"root","root");
    	Statement sat=con.createStatement();
    	String query = "select *  from reviews where contact='"+contact+"'";
    	ResultSet rs=sat.executeQuery(query);
    	while(rs.next())
    	{
    		if(rs.getString("review").equals("0"))
    		{
    			new NewRatings().setVisible(true);
    	        this.setVisible(false);
    	    }
    		
    	}
    
//    	home = new HomePage();    	
    	home.setVisible(true);
        this.setVisible(false);
    	
    	}
    	catch(Exception ex){
    		System.out.println(ex);
    	}
    	}
    	}
    

    public static void main(String[] args)  {

    	
        new Login().setVisible(true);
        
    }

}
