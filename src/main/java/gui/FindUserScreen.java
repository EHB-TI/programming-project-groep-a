package gui;

import dao.UserDAO;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Also for deleting users
public class FindUserScreen extends JFrame {
    private JFrame jframe;
    private JLabel findTitle;
    private JLabel inputLabel;
    private JTextField inputField;
    private JButton searchButton;
    private JScrollPane tablePane;
    private JTable displayUserTable;
    private JButton deleteButton;

    public FindUserScreen() {
        jframe = new JFrame("MyBrews");
        jframe.setSize(500,650);
        // Form title
        findTitle = new JLabel("Find someone in the MyBrews database");
        findTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        // Search input label and field
        inputLabel = new JLabel("Search by (sur)name");
        inputField = new JTextField(30);
        // Search button
        searchButton = new JButton("Search user!");
        // Prepare table to show search results
        String[] header = {"Name", "Surname", "DOB", "Gender", "Favorite beer", "Profession", "Residence", "e-mail", "Date joined"};
        DefaultTableModel model = new DefaultTableModel(header, 0) {
            // Make jtable cells uneditable
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        displayUserTable = new JTable(model);
        displayUserTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        // Table will go in scroll pane, if you click search again, another row is added to the table
        tablePane = new JScrollPane(displayUserTable);
        tablePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        // Search button
        deleteButton = new JButton("Delete user in last row!");


        SpringLayout layout = new SpringLayout();
        jframe.setLayout(layout);
        jframe.add(findTitle);
        jframe.add(inputLabel);
        jframe.add(inputField);
        jframe.add(searchButton);
        jframe.add(tablePane);
        jframe.add(deleteButton);

        // Layout title
        layout.putConstraint(SpringLayout.WEST, findTitle, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, findTitle, 10, SpringLayout.NORTH, jframe);
        // Layout input
        layout.putConstraint(SpringLayout.WEST, inputLabel, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, inputLabel, 50, SpringLayout.NORTH, jframe);
        layout.putConstraint(SpringLayout.WEST, inputField, 5, SpringLayout.EAST, inputLabel);
        layout.putConstraint(SpringLayout.NORTH, inputField, 50, SpringLayout.NORTH, jframe);
        // Layout search button
        layout.putConstraint(SpringLayout.WEST, searchButton, 10, SpringLayout.WEST, tablePane);
        layout.putConstraint(SpringLayout.NORTH, searchButton, 50, SpringLayout.NORTH, inputLabel);
        // Layout delete button next to search button
        layout.putConstraint(SpringLayout.EAST, deleteButton, -10, SpringLayout.EAST, tablePane);
        layout.putConstraint(SpringLayout.NORTH, deleteButton, 50, SpringLayout.NORTH, inputLabel);
        // Layout table
        layout.putConstraint(SpringLayout.WEST, tablePane, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, tablePane, 50, SpringLayout.NORTH, searchButton);



        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserDAO udao = new UserDAO();
                ArrayList<User> usersFound = new ArrayList<>();
                usersFound = udao.findUser(inputField.getText()); // usersFound.get(0) is null if no users found
                // User found
                if (usersFound.size() != 0) {
                    for (User u : usersFound) {
                        String[] tableData = {u.getName(), u.getSurname(), u.getDOB().toString(), u.getGender(), u.getFavoriteBeer(), u.getProfession(), u.getResidence(), u.getEmail(), u.getJoiningDate().toString()};
                        model.addRow(tableData);
                        System.out.println("searched");
                    }
                }
                // No user found
                else {
                    JOptionPane.showMessageDialog(jframe, "There is no user by that name! You can close this dialog and search again.", "User not found", JOptionPane.ERROR_MESSAGE);
                };
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserDAO udao = new UserDAO();
                udao.deleteUser(displayUserTable, jframe);
            }
        });




        jframe.setLocationRelativeTo(null); // Center of screen
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }





    public static void main(String[] args) {
        FindUserScreen s = new FindUserScreen();
    }
}
