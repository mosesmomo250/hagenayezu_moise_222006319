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




public class Election_Admins_Form extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField name;
    private JTextField admin_id;
    

    


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Election_Admins_Form frame = new Election_Admins_Form(); 
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }




    public Election_Admins_Form() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\STDM.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(180, 50, 1014, 497);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(37, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("ADD AN ADMIN TO ELECTION");
        lblNewUserRegister.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 38));
        lblNewUserRegister.setForeground(new Color(0, 0, 128)); 
        lblNewUserRegister.setBounds(362, 52, 350, 80);
        contentPane.add(lblNewUserRegister);

        JLabel lblElection_id  = new JLabel("Election_id ");
        lblElection_id .setFont(new Font("Georgia", Font.PLAIN, 17));
        lblElection_id .setBounds(58, 175, 99, 43);
        contentPane.add(lblElection_id );


        JLabel lblAdmin_idAddress = new JLabel("Admin_id");
        lblAdmin_idAddress.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblAdmin_idAddress.setBounds(542, 175, 124, 36);
        contentPane.add(lblAdmin_idAddress);
        


        name = new JTextField();
        name.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
        name.setBounds(214, 175, 228, 50);
        contentPane.add(name);
        name.setColumns(10);



        admin_id = new JTextField();
        admin_id.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
        admin_id.setBounds(707, 175, 228, 50);
        contentPane.add(admin_id);
        admin_id.setColumns(10);

        

        

        
        
        name.setBackground(Color.LIGHT_GRAY);
        admin_id.setBackground(Color.LIGHT_GRAY);

        
       

       
    




        JButton btnNewButton = new JButton("SUBMIT");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Election_id  = name.getText();
                String admin_idId = admin_id.getText();


                String msg = "" + Election_id ;
                msg += " \n";
              

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moise_hagenayezu_ovs", "root", "");

                    String query = "INSERT INTO election_admin (Election_id ,  Admin_id) " +
                            "VALUES ('" + Election_id  + "','" + admin_idId + "')";


                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(btnNewButton, "This is alredy exist");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton,
                            "CONGS " + msg + "Election process is ongoing");
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
	


