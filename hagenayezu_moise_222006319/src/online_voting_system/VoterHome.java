package moise_hagenayezu_ovs;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class VoterHome extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VoterHome frame = new VoterHome();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    

    public VoterHome(String userSes) {
    	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 setTitle("...                                                                           HOME PAGE FOR USER                          (AMBULANCE  BOOKING  SYSTEM )                 ...");
    	 setBounds(180, 50, 1014, 597);
         setResizable(true);
         contentPane = new JPanel();
         contentPane.setBackground(new Color(37, 0, 0));
         contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
         setContentPane(contentPane);
         contentPane.setLayout(null);
         
         JLabel copyrightLabel = new JLabel("\u00a9 2024 Moise HAGENAYEZU BIT GROUP 1 - OVS(Online Voting System) . All rights reserved.");
         copyrightLabel.setForeground(Color.GRAY);
         copyrightLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
         copyrightLabel.setHorizontalAlignment(SwingConstants.CENTER);
         copyrightLabel.setBounds(10, 540, 974, 20); 
         contentPane.add(copyrightLabel);
         
         JLabel lblNewUserRegister = new JLabel(" VOTER DASHBOARD");
         lblNewUserRegister.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 50));
         lblNewUserRegister.setForeground(new Color(0, 0, 128)); 
         lblNewUserRegister.setBounds(362, 18, 350, 80);
         contentPane.add(lblNewUserRegister);
         
         
         JButton btnViewDetails = new JButton("View Details");
         btnViewDetails.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 try {
                     
                     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moise_hagenayezu_ovs", "root", "");

                     
                     String query = "SELECT * FROM voters WHERE email = ?";
                     PreparedStatement statement = connection.prepareStatement(query);
                     statement.setString(1, userSes);

                   
                     ResultSet resultSet = statement.executeQuery();

                     
                     StringBuilder userData = new StringBuilder();
                     while (resultSet.next()) {
                         userData.append("Name: ").append(resultSet.getString("Name")).append("\n");
                         userData.append("Email: ").append(resultSet.getString("Email")).append("\n");
                         userData.append("Address: ").append(resultSet.getString("Address")).append("\n");
                         userData.append("DOB: ").append(resultSet.getString("DateOfBirth")).append("\n");
                         userData.append("Password: ").append(resultSet.getString("Password")).append("\n");
                         
                     }

                     
                     JOptionPane.showMessageDialog(VoterHome.this, userData.toString(), "Voters Details", JOptionPane.INFORMATION_MESSAGE);

                    
                     resultSet.close();
                     statement.close();
                     connection.close();
                 } catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(VoterHome.this, "Error retrieving user data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                 }
             }
         });
         btnViewDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
         btnViewDetails.setBounds(214, 300, 228, 50);
         btnViewDetails.setBackground(new Color(0, 0, 128));
         btnViewDetails.setForeground(Color.WHITE ); 
         contentPane.add(btnViewDetails);
         


         JButton btnNewButton = new JButton("Logout");
         btnNewButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 int a = JOptionPane.showConfirmDialog(btnNewButton, "Are you sure?");
                 if (a == JOptionPane.YES_OPTION) {
                     dispose();
                     VoterLogin obj = new VoterLogin();
                     obj.setTitle("Voters - Login");
                     obj.setVisible(true);
                 }
             }
         });
         btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
         btnNewButton.setBounds(454, 400, 228, 50);
         btnNewButton.setBackground(new Color(0, 0, 128));
         btnNewButton.setForeground(Color.WHITE ); 
         contentPane.add(btnNewButton);


         /*
         JButton btnBookingRequest = new JButton("Booking Request");
         btnBookingRequest.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 BookingRequestForm bookingRequestForm = new BookingRequestForm();
                 bookingRequestForm.setTitle("Booking Request Form");
                 bookingRequestForm.setVisible(true);
             }
         });
         btnBookingRequest.setFont(new Font("Tahoma", Font.PLAIN, 20));
         btnBookingRequest.setBackground(new Color(0, 0, 128));
         btnBookingRequest.setForeground(Color.WHITE); 
         btnBookingRequest.setBounds(214, 151, 228, 50);
         contentPane.add(btnBookingRequest);
         

         JButton btnUpdateData = new JButton("Update Data");
         btnUpdateData.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 UserUpdateDataForm updateForm = new UserUpdateDataForm(userSes);
                 updateForm.setVisible(true);
             }
         });
         btnUpdateData.setFont(new Font("Tahoma", Font.PLAIN, 16));
         btnUpdateData.setBounds(707, 300, 228, 50);
         btnUpdateData.setBackground(new Color(0, 0, 128));
         btnUpdateData.setForeground(Color.WHITE ); 
         contentPane.add(btnUpdateData);
   
         
         JButton btnEmergencyContacts = new JButton("Add Emergency contact");
         btnEmergencyContacts.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 EmergencyContactsForm EmergencyContactsForm = new EmergencyContactsForm();
            	 EmergencyContactsForm.setTitle("Emergency Contacts Form");
            	 EmergencyContactsForm.setVisible(true);
             }
         });
         btnEmergencyContacts.setFont(new Font("Tahoma", Font.PLAIN, 17));
         btnEmergencyContacts.setBackground(new Color(0, 0, 128));
         btnEmergencyContacts.setForeground(Color.WHITE); 
         btnEmergencyContacts.setBounds(707, 224, 228, 50);
         contentPane.add(btnEmergencyContacts);
         */
    }

    public VoterHome() {
		
	}

	private JButton createButton(String text, int fontSize) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        button.setBackground(new Color(0, 0, 128));
        button.setForeground(Color.WHITE);
        return button;
    }
}
