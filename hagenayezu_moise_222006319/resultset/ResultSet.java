package resultset;



import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AdminLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnLogin;
    private JButton btnDelete;
    private JLabel label;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminLogin frame = new AdminLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AdminLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("... LOGIN PAGE FOR ADMIN (ONLINE VOTING SYSTEM) ...");
        setBounds(214, 80, 800, 597);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(37, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("LOGIN HERE");
        lblNewLabel.setForeground(new Color(139, 0, 0));
        lblNewLabel.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 50));
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

        JLabel lblUsername = new JLabel("username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Georgia", Font.PLAIN, 24));
        lblUsername.setBounds(80, 170, 193, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Georgia", Font.PLAIN, 24));
        lblPassword.setBounds(80, 286, 193, 52);
        contentPane.add(lblPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(139, 0, 0));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Algerian", Font.PLAIN, 26));
        btnLogin.setBounds(210, 450, 162, 73);
        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        contentPane.add(btnLogin);

        btnDelete = new JButton("Delete");
        btnDelete.setBackground(Color.RED);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Algerian", Font.PLAIN, 26));
        btnDelete.setBounds(420, 450, 162, 73);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
        contentPane.add(btnDelete);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);

        JLabel copyrightLabel = new JLabel("\u00a9 2024 MOISE HAGENAYEZU - OVS(Online Voting System) . All rights reserved.");
        copyrightLabel.setForeground(Color.GRAY);
        copyrightLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        copyrightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        copyrightLabel.setBounds(1, 530, 1012, 20);
        contentPane.add(copyrightLabel);
    }

    private void login() {
        String username = textField.getText();
        String password = new String(passwordField.getPassword());
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_voting_system",
                    "root", "");

            PreparedStatement st = connection
                    .prepareStatement("SELECT name, password FROM admins WHERE username=? AND password=?");

            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                dispose();
                AdminHome ah = new AdminHome(username);
                ah.setTitle("... HOME PAGE FOR ADMIN (ONLINE VOTING SYSTEM ) ...");
                ah.setVisible(true);
                JOptionPane.showMessageDialog(btnLogin, "You have successfully logged in");
            } else {
                JOptionPane.showMessageDialog(btnLogin, "Wrong Username & Password");
            }

            rs.close();
            st.close();
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private void delete() {
        String username = textField.getText();
        String password = new String(passwordField.getPassword());
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_voting_system",
                    "root", "");

            PreparedStatement st = connection
                    .prepareStatement("DELETE FROM admins WHERE username=? AND password=?");

            st.setString(1, username);
            st.setString(2, password);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(btnDelete, "Record deleted successfully");
            } else {
                JOptionPane.showMessageDialog(btnDelete, "Record not found or could not be deleted");
            }

            st.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}




