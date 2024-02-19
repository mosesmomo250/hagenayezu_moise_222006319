package online_voting_system;







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




public class ElectionsRegistration extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField name;
    private JTextField start_date;
    private JTextField end_date;

    


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ElectionsRegistration frame = new ElectionsRegistration(); 
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }




    public ElectionsRegistration() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\STDM.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(180, 50, 1014, 497);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(37, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("ADD A NEW ELECTION");
        lblNewUserRegister.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 43));
        lblNewUserRegister.setForeground(new Color( 0, 0, 128)); 
        lblNewUserRegister.setBounds(362, 52, 350, 80);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblName.setBounds(58, 175, 99, 43);
        contentPane.add(lblName);


        JLabel lblEnd_date = new JLabel("End_date");
        lblEnd_date.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblEnd_date.setBounds(58, 250, 99, 43);
        contentPane.add(lblEnd_date);
        
        
        JLabel lblStart_dateAddress = new JLabel("Start_date");
        lblStart_dateAddress.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblStart_dateAddress.setBounds(542, 175, 124, 36);
        contentPane.add(lblStart_dateAddress);
        

        name = new JTextField();
        name.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
        name.setBounds(214, 175, 228, 50);
        contentPane.add(name);
        name.setColumns(10);
        
        end_date = new JTextField();
        end_date.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
        end_date.setBounds(214, 250, 228, 50);
        contentPane.add(end_date);
        end_date.setColumns(10);



        start_date = new JTextField();
        start_date.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
        start_date.setBounds(707, 175, 228, 50);
        contentPane.add(start_date);
        start_date.setColumns(10);

        

        

        
        
        name.setBackground(Color.LIGHT_GRAY);
        start_date.setBackground(Color.LIGHT_GRAY);
        end_date.setBackground(Color.LIGHT_GRAY);

        
       

       
    




        JButton btnNewButton = new JButton("SUBMIT");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Name = name.getText();
                String start_dateId = start_date.getText();
                String End_date = end_date.getText();


                String msg = "" + Name;
                msg += " \n";
              

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_voting_system", "root", "");

                    String query = "INSERT INTO elections (name,  start_date, end_date) " +
                            "VALUES ('" + Name + "','" + start_dateId + "','" + End_date + "')";
                    
                    
                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(btnNewButton, "This is alredy exist");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton,
                            "Congs, " + msg + " eletion was created");
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("Algerian", Font.PLAIN, 22));
        btnNewButton.setBounds(390, 350, 180, 62);
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
	



