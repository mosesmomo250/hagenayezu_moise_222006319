package moise_hagenayezu_ovs;



import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



public class AdminHome extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminHome frame = new AdminHome();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AdminHome(String adminSes) {
    	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 setBounds(180, 50, 1014, 597);
         setResizable(true);
         contentPane = new JPanel();
         contentPane.setBackground(Color.DARK_GRAY);
         contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
         setContentPane(contentPane);
         contentPane.setLayout(null);
         
         JLabel lblNewUserRegister = new JLabel(" WELCOME TO DASHBORD");
         lblNewUserRegister.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 43));
         lblNewUserRegister.setForeground(new Color(139, 0, 0)); 
         lblNewUserRegister.setBounds(362, 18, 350, 80);
         contentPane.add(lblNewUserRegister);
         
         JButton btnViewDetails = new JButton("View Details");
         btnViewDetails.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 try {
                     
                     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moise_hagenayezu_ovs", "root", "");

                     
                     String query = "SELECT * FROM admins WHERE username = ?";
                     PreparedStatement statement = connection.prepareStatement(query);
                     statement.setString(1, adminSes);

                     
                     ResultSet resultSet = statement.executeQuery();

                     
                     StringBuilder adminData = new StringBuilder();
                     while (resultSet.next()) {
                         adminData.append("AdminID: ").append(resultSet.getInt("admin_id")).append("\n");
                         adminData.append("Name: ").append(resultSet.getString("Name")).append("\n");
                         adminData.append("User Name: ").append(resultSet.getString("username")).append("\n");
                         adminData.append("Password: ").append(resultSet.getString("Password")).append("\n");

                         
                     }

                     
                     JOptionPane.showMessageDialog(AdminHome.this, adminData.toString(), "Admin Details", JOptionPane.INFORMATION_MESSAGE);

                    
                     resultSet.close();
                     statement.close();
                     connection.close();
                 } catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(AdminHome.this, "Error retrieving admin data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                 }
             }
         });
         btnViewDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
         btnViewDetails.setBounds(214, 410, 228, 50);
         btnViewDetails.setBackground(new Color(139, 0, 0));
         btnViewDetails.setForeground(Color.WHITE ); 
         contentPane.add(btnViewDetails);
         
         
         
         JButton btnViewTable = new JButton("Analyze Database");
         btnViewTable.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 try {
                     
                     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moise_hagenayezu_ovs", "root", "");

                     
                     DatabaseMetaData metaData = (DatabaseMetaData) connection.getMetaData();
                     ResultSet tablesResultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});
                     List<String> tableNames = new ArrayList<>();
                     while (tablesResultSet.next()) {
                         String tableName = tablesResultSet.getString("TABLE_NAME");
                         tableNames.add(tableName);
                     }
                     tablesResultSet.close();

                     
                     String selectedTable = (String) JOptionPane.showInputDialog(AdminHome.this, "Select a table to view data:", "Select Table",
                             JOptionPane.QUESTION_MESSAGE, null, tableNames.toArray(), tableNames.get(0));

                     
                     String query = "SELECT * FROM " + selectedTable;
                     PreparedStatement statement = connection.prepareStatement(query);

                     ResultSet resultSet = statement.executeQuery();

                    
                     StringBuilder tableData = new StringBuilder();
                     ResultSetMetaData metaData1 = resultSet.getMetaData();
                     int columnCount = metaData1.getColumnCount();
                     while (resultSet.next()) {
                         for (int i = 1; i <= columnCount; i++) {
                             String columnName = metaData1.getColumnName(i);
                             String columnValue = resultSet.getString(i);
                             tableData.append(columnName).append(": ").append(columnValue).append("\n");
                         }
                         tableData.append("\n");
                     }

                    
                     JTextArea textArea = new JTextArea(20, 40);
                     textArea.setText(tableData.toString());
                     textArea.setEditable(false);
                     JScrollPane scrollPane = new JScrollPane(textArea);
                     scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                     JOptionPane.showMessageDialog(AdminHome.this, scrollPane, "Table Data: " + selectedTable, JOptionPane.INFORMATION_MESSAGE);

                     
                     resultSet.close();
                     statement.close();
                     connection.close();
                 } catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(AdminHome.this, "Error retrieving table data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                 }
             }
         });
         btnViewTable.setFont(new Font("Tahoma", Font.PLAIN, 20));
         btnViewTable.setBounds(454, 410, 228, 50);
         btnViewTable.setBackground(new Color(139, 0, 0));
         btnViewTable.setForeground(Color.WHITE);
         contentPane.add(btnViewTable);



         JButton btnNewButton = new JButton("Sign Out");
         btnNewButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 int a = JOptionPane.showConfirmDialog(btnNewButton, "Are you sure?");
                 if (a == JOptionPane.YES_OPTION) {
                     dispose();
                     AdminLogin obj = new AdminLogin();
                     obj.setTitle("Admin-Login");
                     obj.setVisible(true);
                 }
             }
         });
         btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
         btnNewButton.setBounds(454, 480, 228, 50);
         btnNewButton.setBackground(new Color(139, 0, 0));
         btnNewButton.setForeground(Color.WHITE ); 
         contentPane.add(btnNewButton);

         JButton button = new JButton("Register a Voter");
         button.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 VoterRegistration bo = new VoterRegistration();
                 bo.setTitle("Voter Registration");
                 bo.setVisible(true);
             }
         });
         button.setFont(new Font("Tahoma", Font.PLAIN, 20));
         button.setBackground(new Color(139, 0, 0));
         button.setForeground(Color.WHITE); 
         button.setBounds(214, 310, 228, 50);
         contentPane.add(button);
         
         JButton button12 = new JButton("Set Election Admin");
         button12.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 Election_Admins_Form bo = new Election_Admins_Form();
                 bo.setTitle("Election_Admins_Form");
                 bo.setVisible(true);
             }
         });
         button12.setFont(new Font("Tahoma", Font.PLAIN, 20));
         button12.setBackground(new Color(139, 0, 0));
         button12.setForeground(Color.WHITE); 
         button12.setBounds(214, 240, 228, 50);
         contentPane.add(button12);
         
         JButton button1 = new JButton("Prepare Election");
         button1.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 ElectionsRegistration bo = new ElectionsRegistration();
                 bo.setTitle("Elections Registration");
                 bo.setVisible(true);
             }
         });
         button1.setFont(new Font("Tahoma", Font.PLAIN, 20));
         button1.setBackground(new Color(139, 0, 0));
         button1.setForeground(Color.WHITE); 
         button1.setBounds(454, 310, 228, 50);
         contentPane.add(button1);
         
         
         
         JButton button11 = new JButton("Register  a Candidate");
         button11.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 CandidatesRegistrstion bo = new CandidatesRegistrstion();
                 bo.setTitle("Candidates Reagistration");
                 bo.setVisible(true);
             }
         });
         button11.setFont(new Font("Tahoma", Font.PLAIN, 20));
         button11.setBackground(new Color(139, 0, 0));
         button11.setForeground(Color.WHITE); 
         button11.setBounds(707, 310, 228, 50);
         contentPane.add(button11);
         
         JButton button111 = new JButton("Register a Party");
         button111.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 PartyRegistration bo = new PartyRegistration();
                 bo.setTitle("Party Registration");
                 bo.setVisible(true);
             }
         });
         button111.setFont(new Font("Tahoma", Font.PLAIN, 20));
         button111.setBackground(new Color(139, 0, 0));
         button111.setForeground(Color.WHITE); 
         button111.setBounds(707, 240, 228, 50);
         contentPane.add(button111);
         
         


         
         JButton btnUpdateData = new JButton("Update Your Data");
         btnUpdateData.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 AdminUpdateDataForm updateForm = new AdminUpdateDataForm(adminSes);
                 updateForm.setVisible(true);
             }
         });
         btnUpdateData.setFont(new Font("Tahoma", Font.PLAIN, 16));
         btnUpdateData.setBounds(707, 410, 228, 50);
         btnUpdateData.setBackground(new Color(139, 0, 0));
         btnUpdateData.setForeground(Color.WHITE ); 
         contentPane.add(btnUpdateData);
         

         
         
         
      
         JLabel copyrightLabel = new JLabel("\u00a9 2024 Moise HAGENAYEZU - OVS(Online Voting System) . All rights reserved.");
         copyrightLabel.setForeground(Color.GRAY);
         copyrightLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
         copyrightLabel.setHorizontalAlignment(SwingConstants.CENTER);
         copyrightLabel.setBounds(10, 540, 974, 20); 
         contentPane.add(copyrightLabel);
    }

    public AdminHome() {
		// TODO Auto-generated constructor stub
	}
}
