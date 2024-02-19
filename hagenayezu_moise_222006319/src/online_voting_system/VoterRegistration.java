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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class VoterRegistration extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField name;
    private JTextField phonenumber;
    private JTextField email;
    private JTextField address;
    private JTextField dateofbirth;
    private JPasswordField passwordField;
    private JButton btnNewButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VoterRegistration frame = new VoterRegistration();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public VoterRegistration() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Voter\\Desktop\\STDM.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(180, 50, 1014, 597);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(37, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("REGISTER AS NEW VOTER");
        lblNewUserRegister.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 32));
        lblNewUserRegister.setForeground(new Color(0, 0, 128)); 
        lblNewUserRegister.setBounds(362, 40, 325, 50);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblName.setBounds(58, 140, 99, 43);
        contentPane.add(lblName);

        JLabel lblNewLabel = new JLabel("Phone Number");
        lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblNewLabel.setBounds(58, 230, 110, 29);
        contentPane.add(lblNewLabel);

        JLabel lblEmailAddress = new JLabel("Email\r\n address");
        lblEmailAddress.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblEmailAddress.setBounds(58, 320, 124, 36);
        contentPane.add(lblEmailAddress);
        
        JLabel lblNewLabeldob = new JLabel("Date of Birth");
        lblNewLabeldob.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblNewLabeldob.setBounds(542, 310, 110, 29);
        contentPane.add(lblNewLabeldob);

        name = new JTextField();
        name.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));
        name.setBounds(214, 140, 228, 50);
        contentPane.add(name);
        name.setColumns(10);

        phonenumber = new JTextField();
        phonenumber.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
        phonenumber.setBounds(214, 220, 228, 50);
        contentPane.add(phonenumber);
        phonenumber.setColumns(10);

        email = new JTextField();
        email.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
        email.setBounds(214, 320, 228, 50);
        contentPane.add(email);
        email.setColumns(10);
        
        dateofbirth = new JTextField();
        dateofbirth.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
        dateofbirth.setBounds(707, 310, 228, 50);
        contentPane.add(dateofbirth);
        dateofbirth.setColumns(10);


        JLabel lbladdress = new JLabel("Addres");
        lbladdress.setFont(new Font("Georgia", Font.PLAIN, 17));
        lbladdress.setBounds(542, 140, 99, 29);
        contentPane.add(lbladdress);
        

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblPassword.setBounds(542, 230, 99, 24);
        contentPane.add(lblPassword);


        address = new JTextField();
        address.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
        address.setBounds(707, 140, 228, 50);
        contentPane.add(address);
        address.setColumns(10);



        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
        passwordField.setBounds(707, 230, 228, 50);
        contentPane.add(passwordField);
        
        name.setBackground(Color.LIGHT_GRAY);
        phonenumber.setBackground(Color.LIGHT_GRAY);
        email.setBackground(Color.LIGHT_GRAY);
        address.setBackground(Color.LIGHT_GRAY);
        dateofbirth.setBackground(Color.LIGHT_GRAY);
        passwordField.setBackground(Color.LIGHT_GRAY);



        btnNewButton = new JButton("Register");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Name = name.getText();
                String phoneNumber = phonenumber.getText();
                String emailId = email.getText();
                String Address = address.getText();
                String dateOfBirth = dateofbirth.getText();
                String password = passwordField.getText();

                

                String msg = "" + Name;
                msg += " \n";
              

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moise_hagenayezu_ovs", "root", "");

                    String query = "INSERT INTO voters (Name, DateOfBirth,  Address, Email, PhoneNumber, Password) " +
                            "VALUES ('" + Name + "','" + dateOfBirth + "','" + Address + "','" + emailId + "','" + phoneNumber + "','" + password + "')";


                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(btnNewButton, "This is alredy exist");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton,
                            "Welcome, " + msg + "Your account is sucessfully created");
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("Algerian", Font.PLAIN, 22));
        btnNewButton.setBounds(430, 460, 180, 62);
        btnNewButton.setBackground(new Color(139, 0, 0));
        btnNewButton.setForeground(Color.WHITE); 
        contentPane.add(btnNewButton);
        

        JLabel copyrightLabel = new JLabel("\u00a9 2024 MOISE HAGENAYEZU - OVS(ONLINE VOTING SYSTEM) . All rights reserved.");
        copyrightLabel.setForeground(Color.GRAY);
        copyrightLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        copyrightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        copyrightLabel.setBounds(10, 530, 974, 20); 
        contentPane.add(copyrightLabel);
    }
}

