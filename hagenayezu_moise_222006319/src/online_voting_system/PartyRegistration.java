package moise_hagenayezu_ovs;





import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;




public class PartyRegistration extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField name;
    private JTextField abbreviation;
    

    


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PartyRegistration frame = new PartyRegistration(); 
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }




    public PartyRegistration() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\STDM.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(180, 50, 1014, 497);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(37, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("ADD A NEW PARTY");
        lblNewUserRegister.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 43));
        lblNewUserRegister.setForeground(new Color(0, 0, 128)); 
        lblNewUserRegister.setBounds(362, 52, 350, 80);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblName.setBounds(58, 175, 99, 43);
        contentPane.add(lblName);


        JLabel lblAbbreviationAddress = new JLabel("Abbreviation");
        lblAbbreviationAddress.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblAbbreviationAddress.setBounds(542, 175, 124, 36);
        contentPane.add(lblAbbreviationAddress);
        


        name = new JTextField();
        name.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
        name.setBounds(214, 175, 228, 50);
        contentPane.add(name);
        name.setColumns(10);



        abbreviation = new JTextField();
        abbreviation.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
        abbreviation.setBounds(707, 175, 228, 50);
        contentPane.add(abbreviation);
        abbreviation.setColumns(10);

        

        

        
        
        name.setBackground(Color.LIGHT_GRAY);
        abbreviation.setBackground(Color.LIGHT_GRAY);

        
       

       
    




        JButton btnNewButton = new JButton("SUBMIT");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Name = name.getText();
                String abbreviationId = abbreviation.getText();


                String msg = "" + Name;
                msg += " \n";
              

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moise_hagenayezu_ovs", "root", "");

                    String query = "INSERT INTO parties (Name,  Abbreviation) " +
                            "VALUES ('" + Name + "','" + abbreviationId + "')";


                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(btnNewButton, "This is alredy exist");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton,
                            "Welcome, " + msg + "Your Party is sucessfully Registered");
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("Algerian", Font.PLAIN, 22));
        btnNewButton.setBounds(390, 300, 180, 62);
        btnNewButton.setBackground(new Color(139, 0, 0));
        btnNewButton.setForeground(Color.WHITE); 
        contentPane.add(btnNewButton);
        
        JLabel copyrightLabel = new JLabel("\u00a9 2024 Moise HAGENAYEZU - OVS(Online Voting System) . All rights reserved.");
        copyrightLabel.setForeground(Color.GRAY);
        copyrightLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        copyrightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        copyrightLabel.setBounds(10, 430, 974, 20); 
        contentPane.add(copyrightLabel);
    }





	}
	


