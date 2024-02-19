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


public class CandidatesRegistrstion extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField name;
    private JTextField party_id ;
    private JTextField election_id;
    private JTextField platform;


    private JButton btnNewButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CandidatesRegistrstion frame = new CandidatesRegistrstion();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public CandidatesRegistrstion() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Voter\\Desktop\\STDM.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(180, 50, 1014, 470);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(37, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("CREATE A CANDIDATE");
        lblNewUserRegister.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 32));
        lblNewUserRegister.setForeground(new Color(0, 0, 128)); 
        lblNewUserRegister.setBounds(362, 40, 325, 50);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblName.setBounds(58, 140, 99, 43);
        contentPane.add(lblName);

        JLabel lblNewLabel = new JLabel("Party ID");
        lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblNewLabel.setBounds(58, 250, 110, 29);
        contentPane.add(lblNewLabel);

        JLabel lblElection_idPlatform = new JLabel("Election_id");
        lblElection_idPlatform.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblElection_idPlatform.setBounds(542, 140, 124, 36);
        contentPane.add(lblElection_idPlatform);
        


        name = new JTextField();
        name.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));
        name.setBounds(214, 140, 228, 50);
        contentPane.add(name);
        name.setColumns(10);

        party_id  = new JTextField();
        party_id .setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
        party_id .setBounds(214, 250, 228, 50);
        contentPane.add(party_id );
        party_id .setColumns(10);

        election_id = new JTextField();
        election_id.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
        election_id.setBounds(707, 140, 228, 50);
        contentPane.add(election_id);
        election_id.setColumns(10);



        JLabel lblplatform = new JLabel("Platform");
        lblplatform.setFont(new Font("Georgia", Font.PLAIN, 17));
        lblplatform.setBounds(542, 250, 99, 29);
        contentPane.add(lblplatform);
        



        platform = new JTextField();
        platform.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
        platform.setBounds(707, 250, 228, 50);
        contentPane.add(platform);
        platform.setColumns(10);




        
        name.setBackground(Color.LIGHT_GRAY);
        party_id .setBackground(Color.LIGHT_GRAY);
        election_id.setBackground(Color.LIGHT_GRAY);
        platform.setBackground(Color.LIGHT_GRAY);




        btnNewButton = new JButton("SUBMIT");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Name = name.getText();
                String Party_id  = party_id .getText();
                String election_idId = election_id.getText();
                String Platform = platform.getText();

                

                String msg = "" + Name;
                msg += " \n";
              

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moise_hagenayezu_ovs", "root", "");

                    String query = "INSERT INTO candidates (Name, Party_id, Election_id,Platform ) " +
                            "VALUES ('" + Name + "','" + Party_id  + "','" + election_idId + "','" + Platform  + "')";


                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(btnNewButton, "This is alredy exist");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton,
                            "Welcome, " + msg + "Kandidatire yawe yatanzwe neza");
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("Algerian", Font.PLAIN, 22));
        btnNewButton.setBounds(430, 320, 180, 62);
        btnNewButton.setBackground(new Color(139, 0, 0));
        btnNewButton.setForeground(Color.WHITE); 
        contentPane.add(btnNewButton);
        

        JLabel copyrightLabel = new JLabel("\u00a9 2024 MOISE HAGENAYEZU - OVS(ONLINE VOTING SYSTEM) . All rights reserved.");
        copyrightLabel.setForeground(Color.GRAY);
        copyrightLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        copyrightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        copyrightLabel.setBounds(10, 400, 974, 20); 
        contentPane.add(copyrightLabel);
    }
}

