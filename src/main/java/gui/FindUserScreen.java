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
    private final JLabel nameInputLabel;
    private final JTextField nameInputField;
    private final JLabel surnameInputLabel;
    private final JTextField surnameInputField;
    private final JLabel emailInputLabel;
    private final JTextField emailInputField;
    private final JButton searchButton;
    private final JScrollPane tablePane;
    private final JTable displayUserTable;
    private final JButton deleteButton;
    private final JButton beerButton;

    public FindUserScreen() {
        jframe = new JFrame("MyBrews");
        jframe.setSize(710,600);
        // Form title
        findTitle = new JLabel("Find someone in the MyBrews database");
        findTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        // Search input label and field
        nameInputLabel = new JLabel("Search by name");
        nameInputField = new JTextField(30);
        surnameInputLabel = new JLabel("Search by surname");
        surnameInputField = new JTextField(30);
        emailInputLabel = new JLabel("Search by e-mail");
        emailInputField = new JTextField(30);
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
        // Beer button
        beerButton = new JButton("View or add to last user's beers");
        // Delete button
        deleteButton = new JButton("Delete user in last row!");


        SpringLayout layout = new SpringLayout();
        jframe.setLayout(layout);
        jframe.add(findTitle);
        jframe.add(nameInputLabel);
        jframe.add(nameInputField);
        jframe.add(surnameInputLabel);
        jframe.add(surnameInputField);
        jframe.add(emailInputLabel);
        jframe.add(emailInputField);
        jframe.add(searchButton);
        jframe.add(tablePane);
        jframe.add(deleteButton);
        jframe.add(beerButton);

        // Layout title
        layout.putConstraint(SpringLayout.WEST, findTitle, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, findTitle, 10, SpringLayout.NORTH, jframe);
        // Layout input
        layout.putConstraint(SpringLayout.WEST, nameInputLabel, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, nameInputLabel, 40, SpringLayout.NORTH, findTitle);
        layout.putConstraint(SpringLayout.WEST, nameInputField, 5, SpringLayout.EAST, nameInputLabel);
        layout.putConstraint(SpringLayout.NORTH, nameInputField, 40, SpringLayout.NORTH, findTitle);

        layout.putConstraint(SpringLayout.WEST, surnameInputLabel, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, surnameInputLabel, 40, SpringLayout.NORTH, nameInputLabel);
        layout.putConstraint(SpringLayout.WEST, surnameInputField, 5, SpringLayout.EAST, surnameInputLabel);
        layout.putConstraint(SpringLayout.NORTH, surnameInputField, 40, SpringLayout.NORTH, nameInputLabel);

        layout.putConstraint(SpringLayout.WEST, emailInputLabel, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, emailInputLabel, 40, SpringLayout.NORTH, surnameInputLabel);
        layout.putConstraint(SpringLayout.WEST, emailInputField, 5, SpringLayout.EAST, emailInputLabel);
        layout.putConstraint(SpringLayout.NORTH, emailInputField, 40, SpringLayout.NORTH, surnameInputLabel);
        // Layout search button
        layout.putConstraint(SpringLayout.WEST, searchButton, 20, SpringLayout.EAST, emailInputField);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, searchButton, 0, SpringLayout.VERTICAL_CENTER, emailInputField);
        // Layout table
        layout.putConstraint(SpringLayout.WEST, tablePane, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, tablePane, 50, SpringLayout.NORTH, emailInputLabel);
        // Layout beer button
        layout.putConstraint(SpringLayout.EAST, beerButton, -10, SpringLayout.HORIZONTAL_CENTER, tablePane);
        layout.putConstraint(SpringLayout.NORTH, beerButton, 20, SpringLayout.SOUTH, tablePane);
        // Layout delete button
        layout.putConstraint(SpringLayout.WEST, deleteButton, 10, SpringLayout.HORIZONTAL_CENTER, tablePane);
        layout.putConstraint(SpringLayout.NORTH, deleteButton, 20, SpringLayout.SOUTH, tablePane);

        searchButton.addActionListener(e -> {
            UserDAO udao = new UserDAO();
            ArrayList<User> usersFound = udao.findUser(nameInputField.getText(), surnameInputField.getText(), emailInputField.getText()); // usersFound.get(0) is null if no users found
            // User found
            if (usersFound.size() != 0) {
                for (User u : usersFound) {
                    String[] tableData = {u.getName(), u.getSurname(), u.getDOB().toString(), u.getGender(), u.getFavoriteBeer(), u.getProfession(), u.getResidence(), u.getEmail(), u.getJoiningDate().toString()};
                    model.addRow(tableData);
                }
            }
            // No user found
            else {
                JOptionPane.showMessageDialog(jframe, "There is no user by that combination! You can close this dialog and search again.", "User not found", JOptionPane.ERROR_MESSAGE);
            }
        });

        beerButton.addActionListener(e -> {
            UserDAO udao = new UserDAO();
            udao.selectUser(displayUserTable, jframe);
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
