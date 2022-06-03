package gui;

import dao.UserDAO;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// Also for deleting users
public class FindUserScreen extends JFrame {
    private final JFrame jframe;
    private final JLabel findTitle;
    private final JLabel inputLabel;
    private final JTextField inputField;
    private final JButton searchButton;
    private final JScrollPane tablePane;
    private final JTable displayUserTable;
    private final JButton deleteButton;

    public FindUserScreen() {
        jframe = new JFrame("MyBrews");
        jframe.setSize(710,500);
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
        tablePane.setPreferredSize(new Dimension(675, 300));
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
        layout.putConstraint(SpringLayout.NORTH, inputLabel, 40, SpringLayout.NORTH, findTitle);
        layout.putConstraint(SpringLayout.WEST, inputField, 5, SpringLayout.EAST, inputLabel);
        layout.putConstraint(SpringLayout.NORTH, inputField, 40, SpringLayout.NORTH, findTitle);
        // Layout search button
        layout.putConstraint(SpringLayout.WEST, searchButton, 20, SpringLayout.EAST, inputField);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, searchButton, 0, SpringLayout.VERTICAL_CENTER, inputField);
        // Layout table
        layout.putConstraint(SpringLayout.WEST, tablePane, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, tablePane, 50, SpringLayout.NORTH, searchButton);
        // Layout delete button
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, deleteButton, 0, SpringLayout.HORIZONTAL_CENTER, tablePane);
        layout.putConstraint(SpringLayout.NORTH, deleteButton, 20, SpringLayout.SOUTH, tablePane);



        searchButton.addActionListener(e -> {
            UserDAO udao = new UserDAO();
            ArrayList<User> usersFound = udao.findUser(inputField.getText()); // usersFound.get(0) is null if no users found
            // User found
            if (usersFound.size() != 0) {
                for (User u : usersFound) {
                    String[] tableData = {u.getName(), u.getSurname(), u.getDOB().toString(), u.getGender(), u.getFavoriteBeer(), u.getProfession(), u.getResidence(), u.getEmail(), u.getJoiningDate().toString()};
                    model.addRow(tableData);
                }
            }
            // No user found
            else {
                JOptionPane.showMessageDialog(jframe, "There is no user by that name! You can close this dialog and search again.", "User not found", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            UserDAO udao = new UserDAO();
            udao.deleteUser(displayUserTable, jframe);
        });




        jframe.setLocationRelativeTo(null); // Center of screen
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }





    public static void main(String[] args) {
        FindUserScreen s = new FindUserScreen();
    }
}
