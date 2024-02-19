package moise_hagenayezu_ovs;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VoterLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;

    /**
     * @author Laurier HABIYAREMYE - Greens Laurier Master With certified Copyright.
     */

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VoterLogin frame = new VoterLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
	 * @author Moise HAGENAYEZU BIT GROUP 1
	 */
    public VoterLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("...                               LOGIN  PAGE FOR VOTERS            (ONLINE VOTING SYSTEM )                 ...");
        setBounds(214, 80, 700, 597);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(37, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("LOGIN HERE");
        lblNewLabel.setForeground(new Color(0, 0, 128));
        lblNewLabel.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 48));
        lblNewLabel.setBounds(223, 13, 273, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));
        textField.setBounds(300, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));
        passwordField.setBounds(300, 286, 281, 68);
        contentPane.add(passwordField);
        
        textField.setBackground(Color.LIGHT_GRAY);
        passwordField.setBackground(Color.LIGHT_GRAY);

        JLabel lblVotername = new JLabel("Email Addres");
        lblVotername.setBackground(Color.BLACK);
        lblVotername.setForeground(Color.BLACK);
        lblVotername.setFont(new Font("Georgia", Font.PLAIN, 31));
        lblVotername.setBounds(80, 166, 193, 52);
        contentPane.add(lblVotername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Georgia", Font.PLAIN, 31));
        lblPassword.setBounds(80, 286, 193, 52);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setBackground(new Color(139, 0, 0));
        btnNewButton.setForeground(Color.WHITE); 
        btnNewButton.setFont(new Font("Algerian", Font.PLAIN, 26));
        btnNewButton.setBounds(310, 450, 162, 73);
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String Email = textField.getText();
                String password = passwordField.getText();
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/moise_hagenayezu_ovs",
                        "root", "");

                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select name, password from voters where email=? and password=?");

                    st.setString(1, Email);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();
                        VoterHome ah = new VoterHome(Email);
                        ah.setTitle("...                                                                           HOME PAGE FOR VOTERS                         OVS(ONLINE VOTING SYSTEM )                 ...");
                        ah.setVisible(true);
                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Wrong Votername or Password" + ", Please Verify Credentials");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        contentPane.add(btnNewButton);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }
}