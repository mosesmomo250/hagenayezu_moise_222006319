package moise_hagenayezu_ovs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.EventQueue;


public class voting extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField voterIdField;
    private JTextField candidateIdField;
    private JTextField partyIdField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    voting frame = new voting();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public voting() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\STDM.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(180, 50, 1014, 497);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(37, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("SELECTION OF A CANDIDATE");
        lblNewUserRegister.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 38));
        lblNewUserRegister.setForeground(new Color(0, 0, 128));
        lblNewUserRegister.setBounds(362, 52, 350, 80);
        contentPane.add(lblNewUserRegister);

        JLabel lblvoter_idAddress = new JLabel("voter_id");
        lblvoter_idAddress.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblvoter_idAddress.setBounds(58, 175, 124, 36);
        contentPane.add(lblvoter_idAddress);

        JLabel lblcandidate_idAddress = new JLabel("candidate_id");
        lblcandidate_idAddress.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblcandidate_idAddress.setBounds(542, 250, 124, 36);
        contentPane.add(lblcandidate_idAddress);

        JLabel lblparty_idAddress = new JLabel("party_id");
        lblparty_idAddress.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblparty_idAddress.setBounds(542, 175, 124, 36);
        contentPane.add(lblparty_idAddress);

        voterIdField = new JTextField();
        voterIdField.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
        voterIdField.setBounds(214, 175, 228, 50);
        contentPane.add(voterIdField);
        voterIdField.setColumns(10);

        candidateIdField = new JTextField();
        candidateIdField.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
        candidateIdField.setBounds(710, 235, 225, 50);
        contentPane.add(candidateIdField);
        candidateIdField.setColumns(10);

        partyIdField = new JTextField();
        partyIdField.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
        partyIdField.setBounds(707, 175, 228, 50);
        contentPane.add(partyIdField);
        partyIdField.setColumns(10);

        partyIdField.setBackground(Color.LIGHT_GRAY);
        voterIdField.setBackground(Color.LIGHT_GRAY);

        JButton btnNewButton = new JButton("SUBMIT");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String voterId = voterIdField.getText();
                String candidateId = candidateIdField.getText();
                String partyId = partyIdField.getText();
                String msg = "Vote taken";

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moise_hagenayezu_ovs",
                            "root", "");

                    // Check if voter_id exists in the database
                    PreparedStatement voterCheckStatement = connection.prepareStatement("SELECT * FROM voters WHERE voter_id = ?");
                    voterCheckStatement.setString(1, voterId);
                    ResultSet voterResult = voterCheckStatement.executeQuery();
                    boolean voterExists = voterResult.next();

                    // Check if candidate_id exists in the database
                    PreparedStatement candidateCheckStatement = connection.prepareStatement("SELECT * FROM candidates WHERE candidate_id = ?");
                    candidateCheckStatement.setString(1, candidateId);
                    ResultSet candidateResult = candidateCheckStatement.executeQuery();
                    boolean candidateExists = candidateResult.next();

                    // Check if party_id exists in the database
                    PreparedStatement partyCheckStatement = connection.prepareStatement("SELECT * FROM parties WHERE party_id = ?");
                    partyCheckStatement.setString(1, partyId);
                    ResultSet partyResult = partyCheckStatement.executeQuery();
                    boolean partyExists = partyResult.next();

                    if (!voterExists || !candidateExists || !partyExists) {
                        JOptionPane.showMessageDialog(btnNewButton, "Wrong ID entered");
                        return; // Exit the method without further execution
                    }

                    // Insert the vote into the database
                    String query = "INSERT INTO voting (voter_id, party_id, candidate_id) VALUES (?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, voterId);
                    statement.setString(2, partyId);
                    statement.setString(3, candidateId);

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(btnNewButton, msg);
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

        JLabel copyrightLabel = new JLabel(
                "\u00a9 2024 Moise HAGENAYEZU - OVS(Online Voting System) . All rights reserved.");
        copyrightLabel.setForeground(Color.GRAY);
        copyrightLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        copyrightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        copyrightLabel.setBounds(10, 430, 974, 20);
        contentPane.add(copyrightLabel);
    }
}
