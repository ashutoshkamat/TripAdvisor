/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

/**
 *
 * @author Tanay Kumar
 */
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
import javax.swing.*;

public class Frame  extends JFrame{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton backButton, hotelsbutton, reviewsbutton;
	private JLabel descriptionlabel, info;
    private String city;
	public Frame(String cityn) throws HeadlessException, ClassNotFoundException, SQLException {
    
		city = cityn;
		setTitle("Welcome to "+city);
        setLayout(null);
        setSize(400, 400);

        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(
                    "/home/ashutosh/A3T3.jpg")))));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        pack();
        reviewsbutton = new JButton("REVIEWS");
        reviewsbutton.setBounds(500, 600, 250, 20);
        
        backButton = new JButton("BACK");
        backButton.setBounds(10, 10, 80, 20);
        
        hotelsbutton=new JButton("SEARCH HOTELS");
        hotelsbutton.setBounds(100, 600, 250, 20);
        add(backButton);
        add(hotelsbutton);
        add(reviewsbutton);
        //Fetching city details from database
        Class.forName("com.mysql.jdbc.Driver");
    	String constr = "jdbc:mysql://localhost/tourmanagement";
    	Connection con = (Connection)DriverManager.getConnection(constr,"root","root");
    	Statement sat=con.createStatement();
    	String query = "select *  from description where city='"+city+"'";
    	ResultSet rs=sat.executeQuery(query);
    	
    	while(rs.next())
    	{
    		descriptionlabel = new JLabel(rs.getString("details"));
            
    	}
    	
    	info = new JLabel("Places you are going to visit :");
    	info.setFont(new Font("Courier", Font.BOLD, 18));
        info.setForeground(new Color(255, 255, 255));
        info.setBounds(20, 20, 1000, 400);
    	add(info);
    	descriptionlabel.setFont(new Font("Courier", Font.BOLD, 18));
        descriptionlabel.setForeground(new Color(255, 255, 255));
    
    	descriptionlabel.setBounds(20, 20, 1000, 200);
    	add(descriptionlabel);
    	
    	query = "select *  from places where city='"+city+"'";
    	rs=sat.executeQuery(query);
    	
    	int i=0;
    	while(rs.next())
    	{
    		
    		descriptionlabel = new JLabel(rs.getString("name"));
    		descriptionlabel.setFont(new Font("Courier", Font.BOLD, 18));
            descriptionlabel.setForeground(new Color(255, 255, 255));
            
    		descriptionlabel.setBounds(20, 20, 1000,600+50*i);
        	add(descriptionlabel);
        	++i;
        	
    	
    	}

    	con.close();
        
    	
    	
    	hotelsbutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                try {
					hotelButtonActionPerformed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
    	reviewsbutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					reviewsActionPerformed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
    
    private void hotelButtonActionPerformed(ActionEvent e) throws HeadlessException, ClassNotFoundException, SQLException {

        new Hotels(city).setVisible(true);
        this.setVisible(false);

    }
    private void reviewsActionPerformed(ActionEvent e) throws Exception
    {
        new Reviews(city).setVisible(true);
        this.setVisible(false);

    }

    
}
