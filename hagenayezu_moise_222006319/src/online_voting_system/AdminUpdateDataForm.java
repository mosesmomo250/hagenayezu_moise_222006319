package moise_hagenayezu_ovs;




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminUpdateDataForm extends JFrame {

   
	private static final long serialVersionUID = 1L;
	private JComboBox<String> columnComboBox;
    private JTextField valueTextField;
    private JButton updateButton;
	public AdminUpdateDataForm(String username) {
        setTitle("Update Admin Data");
        setSize(400, 200);
        setBackground(new Color (240, 128, 128));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Column selection
        JLabel columnLabel = new JLabel("Select Column:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(columnLabel, gbc);

        String[] columns = {"Name", "username", "Password"};
        columnComboBox = new JComboBox<>(columns);
        columnComboBox.setFont(new Font("Goudy Old Style", Font.PLAIN, 18));
        columnComboBox.setBounds(214, 235, 228, 50);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(columnComboBox, gbc);

        // Value input
        JLabel valueLabel = new JLabel("Enter New Value:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(valueLabel, gbc);

        valueTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(valueTextField, gbc);

        // Update button
        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String column = (String) columnComboBox.getSelectedItem();
                String value = valueTextField.getText();
                updateAdminData(username, column, value);
                dispose();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(updateButton, gbc);

        add(panel);
    }

    private void updateAdminData(String username, String column, String value) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moise_hagenayezu_ovs",
                    "root", "");

            String query = "UPDATE admins SET " + column + " = ? WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, value);
            statement.setString(2, username);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Admin data updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update admin data.");
            }

            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating admin data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            

        }

    }
}

