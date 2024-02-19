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




public class AdminRegistration extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField name;
    private JTextField username;
    private JPasswordField JPasswordField;
    


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminRegistration frame = new AdminRegistration(); 
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }




    public AdminRegistration() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\STDM.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(180, 50, 1014, 597);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(00, 00, 82));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("REGISTER NEW ADMIN");
        lblNewUserRegister.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 43));
        lblNewUserRegister.setForeground(new Color(255, 255, 255)); 
        lblNewUserRegister.setBounds(362, 52, 350, 80);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblName.setBounds(58, 175, 99, 43);
        contentPane.add(lblName);


        JLabel lblUsernameAddress = new JLabel("Username");
        lblUsernameAddress.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblUsernameAddress.setBounds(542, 175, 124, 36);
        contentPane.add(lblUsernameAddress);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblPassword.setBounds(58, 339, 99, 24);
        contentPane.add(lblPassword);


        name = new JTextField();
        name.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
        name.setBounds(214, 175, 228, 50);
        contentPane.add(name);
        name.setColumns(10);



        username = new JTextField();
        username.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
        username.setBounds(707, 175, 228, 50);
        contentPane.add(username);
        username.setColumns(10);

        

        
        
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
        passwordField.setBounds(214, 335, 228, 50);
        contentPane.add(passwordField);
        
        
        
        name.setBackground(Color.LIGHT_GRAY);
        username.setBackground(Color.LIGHT_GRAY);
        passwordField.setBackground(Color.LIGHT_GRAY);
        
        JButton loginButton = new JButton("GO TO LOGIN");
        loginButton.setFont(new Font("Algerian", Font.PLAIN, 22));
        loginButton.setBounds(660, 450, 180, 62);
        loginButton.setBackground(new Color(37, 0, 0));
        loginButton.setForeground(Color.WHITE);
        contentPane.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminLogin loginForm = new AdminLogin();
                loginForm.setTitle("Admin Login");
                loginForm.setVisible(true);
            }
        });
    




        JButton btnNewButton = new JButton("REGISTER");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Name = name.getText();
                String usernameId = username.getText();
                String password = passwordField.getText();

                String msg = "" + Name;
                msg += " \n";
              

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moise_hagenayezu_ovs", "root", "");

                    String query = "INSERT INTO admins (Name,  Username, Password) " +
                            "VALUES ('" + Name + "','" + usernameId + "','" + password + "')";


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
        btnNewButton.setBounds(260, 450, 180, 62);
        btnNewButton.setBackground(new Color(37, 0, 0));
        btnNewButton.setForeground(Color.WHITE); 
        contentPane.add(btnNewButton);
        
        JLabel copyrightLabel = new JLabel("\u00a9 2024 Moise HAGENAYEZU - OVS(Online Voting System) . All rights reserved.");
        copyrightLabel.setForeground(Color.GRAY);
        copyrightLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        copyrightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        copyrightLabel.setBounds(10, 530, 974, 20); 
        contentPane.add(copyrightLabel);
    }




	public JPasswordField getJPasswordField() {
		return JPasswordField;
	}


	public void setJPasswordField(JPasswordField jPasswordField) {
		JPasswordField = jPasswordField;
	}
	
}

