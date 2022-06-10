package gui;

import dao.UserDAO;
import entity.Beer;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static gui.MainScreen.beerglass;

// Also for deleting users
public class FindUserScreen {

    private final JFrame jframe;
    private final JTextField nameInputField;
    private final JTextField surnameInputField;
    private final JTextField emailInputField;
    private final JTable displayUserTable;

    public FindUserScreen() {
        jframe = new JFrame("MyBrews");
        jframe.setIconImage(beerglass.getImage());
        jframe.setSize(710,600);
        // Form title
        JLabel findTitle = new JLabel("Find someone in the MyBrews database");
        findTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        // Search input label and field
        JLabel nameInputLabel = new JLabel("Search by name");
        nameInputField = new JTextField(30);
        JLabel surnameInputLabel = new JLabel("Search by surname");
        surnameInputField = new JTextField(30);
        JLabel emailInputLabel = new JLabel("Search by e-mail");
        emailInputField = new JTextField(30);
        // Search button
        JButton searchButton = new JButton("Search user!");
        // Prepare table to show search results
        String[] header = {"Name", "Surname", "DOB", "Gender", "Favorite beer", "Profession", "Residence", "e-mail", "Date joined", "id"};
        DefaultTableModel usermodel = new DefaultTableModel(header, 0) {

            // Make jtable cells uneditable
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        displayUserTable = new JTable(usermodel);
        displayUserTable.removeColumn(displayUserTable.getColumn("id")); // Hide id column
        displayUserTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        displayUserTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Select only one at a time
        // Table will go in scroll pane, if you click search again, another row is added to the table
        JScrollPane tablePane = new JScrollPane(displayUserTable);
        tablePane.setPreferredSize(new Dimension(675, 300));
        tablePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        // Beer button
        JButton checkUserButton = new JButton("View selected user's beers");
        // Delete button
        JButton deleteButton = new JButton("Delete selected user");


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
        jframe.add(checkUserButton);

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
        layout.putConstraint(SpringLayout.EAST, checkUserButton, -10, SpringLayout.HORIZONTAL_CENTER, tablePane);
        layout.putConstraint(SpringLayout.NORTH, checkUserButton, 20, SpringLayout.SOUTH, tablePane);
        // Layout delete button
        layout.putConstraint(SpringLayout.WEST, deleteButton, 10, SpringLayout.HORIZONTAL_CENTER, tablePane);
        layout.putConstraint(SpringLayout.NORTH, deleteButton, 20, SpringLayout.SOUTH, tablePane);

        searchButton.addActionListener(e -> {
            UserDAO udao = new UserDAO();
            ArrayList<User> usersFound = udao.findUser(nameInputField.getText(), surnameInputField.getText(), emailInputField.getText());
            // User found
            if (usersFound.size() != 0) {
                for (User u : usersFound) {
                    String[] tableData = {u.getName(), u.getSurname(), u.getDOB().toString(), u.getGender(), u.getFavoriteBeer(), u.getProfession(), u.getResidence(), u.getEmail(), u.getJoiningDate().toString(), Integer.toString(u.getUserID())};
                    usermodel.addRow(tableData);
                }
            }
            // No user found
            else {
                JOptionPane.showMessageDialog(jframe, "There is no user by that combination! You can close this dialog and search again.", "User not found", JOptionPane.ERROR_MESSAGE);
            }
        });

        checkUserButton.addActionListener(e -> {
            int userRow = displayUserTable.getSelectedRow(); // Value -1 if none selected
            if (userRow == -1) {
                JOptionPane.showMessageDialog(jframe, "You must first search or select a user.", "No user selected", JOptionPane.WARNING_MESSAGE);
            }
            else {
                // Get User's info in user object for next screen
                String name = (String)displayUserTable.getModel().getValueAt(userRow, 0); // Returns object, cast to String
                String surname = (String)displayUserTable.getModel().getValueAt(userRow, 1);
                LocalDate DOB = LocalDate.parse((String)displayUserTable.getModel().getValueAt(userRow, 2));
                String gender = (String)displayUserTable.getModel().getValueAt(userRow, 3);
                String favoBeer = (String)displayUserTable.getModel().getValueAt(userRow, 4);
                String profession = (String)displayUserTable.getModel().getValueAt(userRow, 5);
                String residence = (String)displayUserTable.getModel().getValueAt(userRow, 6);
                String email = (String)displayUserTable.getModel().getValueAt(userRow, 7);
                LocalDate dateJoined = LocalDate.parse((String)displayUserTable.getModel().getValueAt(userRow, 8));
                String userIDString = (String)displayUserTable.getModel().getValueAt(userRow,9);
                int userID = Integer.parseInt(userIDString); // Difficulties with direct casting from object to int
                User u = new User(userID, name, surname, DOB, gender, favoBeer, profession, residence, email, dateJoined);

                // New screen to display user's beers
                CheckUserScreen cus = new CheckUserScreen(u);
                UserDAO udao = new UserDAO();
                ArrayList<Beer> beersFound = udao.selectUser(userID);
                for (Beer b : beersFound) {
                    String[] tableData = {b.getName(), b.getVariant(), Double.toString(b.getAlcoholPercentage()), b.getColor(), b.getBrewery(), Integer.toString(b.getBeerID())};
                    cus.getBeermodel().addRow(tableData);
                }
                cus.getTableInfo3().setText(u.getName() + " has drank " + cus.getBeermodel().getRowCount() + " different beers");
            }
        });

        deleteButton.addActionListener(e -> {
            int userRow = displayUserTable.getSelectedRow(); // Value -1 if none selected
            if (userRow == -1) {
                JOptionPane.showMessageDialog(jframe, "You must first search or select a user.", "No user selected", JOptionPane.WARNING_MESSAGE);
            }
            else {
                String userIDString = (String)displayUserTable.getModel().getValueAt(userRow, 9);
                int userID = Integer.parseInt(userIDString); // Difficulties with direct casting from object to int
                UserDAO udao = new UserDAO();
                int[] checkIfDeleted = udao.deleteUser(userID);
                // Check if any user was deleted and delete from table
                if (checkIfDeleted[1] != 0)  {
                    usermodel.removeRow(userRow);
                    JOptionPane.showMessageDialog(jframe, "The user and the user's " + checkIfDeleted[0] + " beers have been deleted!", "User deleted", JOptionPane.PLAIN_MESSAGE);
                }
                // Delete error
                else {
                    JOptionPane.showMessageDialog(jframe, "The user has already been deleted or doesn't exist. Try selecting another user first.", "User delete failed", JOptionPane.WARNING_MESSAGE);
                }
            }
        });



        jframe.setLocationRelativeTo(null); // Center of screen
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }





    public static void main(String[] args) {
        new FindUserScreen();
    }
}
