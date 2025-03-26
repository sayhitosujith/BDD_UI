package StepDefs.services.Practice;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridUpdateExample {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Grid Update Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Sample data for the grid (2D array)
        String[][] data = {
                {"sayhitosujith@gmail.com", "Qw@12345678"},
        };

        // Column names
        String[] columnNames = {"Email ID", "Password"};

        // Create DefaultTableModel and JTable
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the table to the panel
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel to hold input fields for new data
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        // Create input fields for ID, Name, and Age
        JTextField idField = new JTextField(5);
        JTextField nameField = new JTextField(10);
        JTextField ageField = new JTextField(5);

        // Add labels and input fields to the inputPanel
        inputPanel.add(new JLabel("EmailID:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(ageField);

        // Add the inputPanel to the main panel (above the table)
        panel.add(inputPanel, BorderLayout.NORTH);

        // Create a button to trigger the update
        JButton updateButton = new JButton("Update Grid");

        // Action listener for button click
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the values entered by the user
                String name = nameField.getText();
                String age = ageField.getText();

                // Check if all fields are filled
                if (name.isEmpty() || age.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Add new row to the table
                String[] newRow = {name, age};
                tableModel.addRow(newRow);

                // Clear input fields after adding
                nameField.setText("");
                ageField.setText("");
            }
        });

        // Add the button to the panel
        panel.add(updateButton, BorderLayout.SOUTH);

        // Add the panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }
}
