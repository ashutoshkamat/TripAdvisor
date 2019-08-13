/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Tanay Kumar
 */
public class HomePage extends JFrame {

    
	private JLabel whereJLabel;
    public static JComboBox placesComboBox;
 //   private Pune pune;
  //  private Mumbai mumbai;
   // private Nagpur nagpur;
  //  private String city;
   // private Bengaluru bengaluru;
    private Frame frame;

    
    @SuppressWarnings("unchecked")
	public HomePage() throws HeadlessException {

    	setTitle("HomePage");
        setLayout(null);
        setSize(400, 400);

        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(
                    "/home/ashutosh/A3T3.jpg")))));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        pack();

        whereJLabel = new JLabel("WHERE YOU WANT TO GO !!");
        whereJLabel.setBounds(430, 290, 1000, 30);
        whereJLabel.setForeground(Color.WHITE);
        whereJLabel.setFont(new Font("Courier", Font.BOLD, 28));
        add(whereJLabel);

        String cities[] = {"Select City", "Pune", "Mumbai", "Mahabaleshwar", "Bengaluru","Goa"};
        // placesComboBox.setPreferredSize(new Dimension(1,25));
        placesComboBox = new JComboBox(cities);
        add(placesComboBox);
        placesComboBox.setBounds(450, 400, 200, 50);
        placesComboBox.setSize(200, 60);

       /* placesComboBox.addItemListener(new ItemListener() {
            
            public void itemStateChanged(ItemEvent e) {

               try {
		//			placesComboBoxItemStateChanged(e);
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

        });*/

        
   //     mumbai = new Mumbai();
    //    nagpur = new Nagpur();
     //   bengaluru = new Bengaluru();

    }
//
//    public void placesComboBoxItemStateChanged(ItemEvent e) throws HeadlessException, ClassNotFoundException, SQLException {
//
//     //   city = (String) placesComboBox.getSelectedItem();
//        if (e.getItem().equals("Pune")) {
//
//            frame = new Frame("Pune");
//            frame.setVisible(true);
//            this.setVisible(false);
//
//        }
//        if (e.getItem().equals("Mumbai")) {
//            frame = new Frame("Mumbai");
//            frame.setVisible(true);
//            this.setVisible(false);
//
//        }
//        if (e.getItem().equals("Mahabaleshwar")) {
//
//            frame = new Frame("Mahabaleshwar");
//            frame.setVisible(true);
//            this.setVisible(false);
//
//        }
//
//        if (e.getItem().equals("Bengaluru")) {
//            frame = new Frame("Banglore");
//            frame.setVisible(true);
//            this.setVisible(false);
//
//        }
//
//        if (e.getItem().equals("Goa")) {
//            frame = new Frame("Panaji");
//            frame.setVisible(true);
//            this.setVisible(false);
//
//        }
        
    }


