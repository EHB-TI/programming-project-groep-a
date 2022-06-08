package gui;

import dao.UserDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CheckUserScreen {
    private JFrame newFrame;
    private JLabel userTitle;
    private JLabel emailInfo;
    private JLabel tableInfo1;
    private JLabel tableInfo2;
    private JLabel tableInfo3;
    private JScrollPane tablePane;
    private JTable displayBeersTable;
    private JLabel addBeerTitle;
    private JTextField beerAdd;
    private JTextField variantAdd;
    private JFormattedTextField percentageAdd;
    private JLabel percentageLabel;
    private JTextField colorAdd;
    private JTextField breweryAdd;
    private JButton addBeerButton;
    private JLabel removeBeerTitle;
    private JTextField beerRemove;
    private JTextField variantRemove;
    private JButton removeBeerButton;
    private static DefaultTableModel model = null;

    public CheckUserScreen (String username, String surname, String email, int userID) {

        newFrame = new JFrame("MyBrews");
        newFrame.setSize(450, 600);
        // User name title
        userTitle = new JLabel(username + " " + surname);
        userTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        // User email
        emailInfo = new JLabel(email);
        emailInfo.setFont(new Font("Book antiqua", Font.ITALIC, 12));
        // Table info
        tableInfo1 = new JLabel("This table shows the beers consumed by " + username + " " + surname + ".");
        tableInfo1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tableInfo2 = new JLabel("Beers can also be added or removed here.");
        tableInfo2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tableInfo3 = new JLabel("Beers can also be added or removed here.");
        tableInfo3.setFont(new Font("Tahoma", Font.ITALIC, 14));
        // Prepare table to show search results
        String[] header = {"Beer name", "Variant name", "percentage", "Color", "Brewery"};
        model = new DefaultTableModel(header, 0) {
            // Make jtable cells uneditable
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        displayBeersTable = new JTable(model);
        displayBeersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // Table will go in scroll pane
        tablePane = new JScrollPane(displayBeersTable);
        tablePane.setPreferredSize(new Dimension(380, 200));
        // Add beer title
        addBeerTitle = new JLabel("Add a beer to " + username + "'s collection");
        addBeerTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
        // Fields to add beer
        beerAdd = new JTextField(" Beer name ");
        variantAdd = new JTextField(" Beer variant ");


        NumberFormat numFormat = new DecimalFormat("##.#");
        NumberFormatter numFormatter = new NumberFormatter(numFormat);
        percentageAdd = new JFormattedTextField(numFormatter);

        percentageAdd.setValue(0.0);
        percentageLabel = new JLabel("%");

        colorAdd = new JTextField(" Beer color ");
        breweryAdd = new JTextField(" Brewery ");
        // Add beer button
        addBeerButton = new JButton("Add beer!");
        // Remove beer title
        removeBeerTitle = new JLabel("Remove a beer from " + username + "'s collection");
        removeBeerTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
        // Fields to remove beer
        beerRemove = new JTextField(" Beer name ");
        variantRemove = new JTextField(" Variant name ");
        // Remove beer button
        removeBeerButton = new JButton("Remove beer!");

        SpringLayout layout = new SpringLayout();
        newFrame.setLayout(layout);
        newFrame.add(userTitle);
        newFrame.add(emailInfo);
        newFrame.add(tableInfo1);
        newFrame.add(tableInfo2);
        newFrame.add(tableInfo3);
        newFrame.add(tablePane);
        newFrame.add(addBeerTitle);
        newFrame.add(beerAdd);
        newFrame.add(variantAdd);
        newFrame.add(percentageAdd);
        newFrame.add(percentageLabel);
        newFrame.add(colorAdd);
        newFrame.add(breweryAdd);
        newFrame.add(addBeerButton);
        newFrame.add(removeBeerTitle);
        newFrame.add(beerRemove);
        newFrame.add(variantRemove);
        newFrame.add(removeBeerButton);

        // Layout title
        layout.putConstraint(SpringLayout.WEST, userTitle, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, userTitle, 10, SpringLayout.NORTH, newFrame);
        // Layout email
        layout.putConstraint(SpringLayout.WEST, emailInfo, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, emailInfo, 25, SpringLayout.NORTH, userTitle);
        // Layout table info
        layout.putConstraint(SpringLayout.WEST, tableInfo1, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, tableInfo1, 40, SpringLayout.NORTH, emailInfo);
        layout.putConstraint(SpringLayout.WEST, tableInfo2, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, tableInfo2, 5, SpringLayout.SOUTH, tableInfo1);
        layout.putConstraint(SpringLayout.WEST, tableInfo3, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, tableInfo3, 10, SpringLayout.SOUTH, tableInfo2);
        // Layout table scroll pane
        layout.putConstraint(SpringLayout.WEST, tablePane, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, tablePane, 5, SpringLayout.SOUTH, tableInfo3);
        // Layout add beer title
        layout.putConstraint(SpringLayout.WEST, addBeerTitle, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, addBeerTitle, 10, SpringLayout.SOUTH, tablePane);
        // Layout add beer fields and button
        layout.putConstraint(SpringLayout.WEST, beerAdd, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, beerAdd, 10, SpringLayout.SOUTH, addBeerTitle);

        layout.putConstraint(SpringLayout.WEST, variantAdd, 5, SpringLayout.EAST, beerAdd);
        layout.putConstraint(SpringLayout.NORTH, variantAdd, 10, SpringLayout.SOUTH, addBeerTitle);

        layout.putConstraint(SpringLayout.WEST, percentageAdd, 5, SpringLayout.EAST, variantAdd);
        layout.putConstraint(SpringLayout.NORTH, percentageAdd, 10, SpringLayout.SOUTH, addBeerTitle);
        layout.putConstraint(SpringLayout.WEST, percentageLabel, 1, SpringLayout.EAST, percentageAdd);
        layout.putConstraint(SpringLayout.NORTH, percentageLabel, 10, SpringLayout.SOUTH, addBeerTitle);

        layout.putConstraint(SpringLayout.WEST, colorAdd, 8, SpringLayout.EAST, percentageLabel);
        layout.putConstraint(SpringLayout.NORTH, colorAdd, 10, SpringLayout.SOUTH, addBeerTitle);

        layout.putConstraint(SpringLayout.WEST, breweryAdd, 5, SpringLayout.EAST, colorAdd);
        layout.putConstraint(SpringLayout.NORTH, breweryAdd, 10, SpringLayout.SOUTH, addBeerTitle);

        layout.putConstraint(SpringLayout.WEST, addBeerButton, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, addBeerButton, 15, SpringLayout.SOUTH, beerAdd);
        // Layout remove beer title
        layout.putConstraint(SpringLayout.WEST, removeBeerTitle, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, removeBeerTitle, 20, SpringLayout.SOUTH, addBeerButton);
        // Layout remove beer fields and button
        layout.putConstraint(SpringLayout.WEST, beerRemove, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, beerRemove, 10, SpringLayout.SOUTH, removeBeerTitle);

        layout.putConstraint(SpringLayout.WEST, variantRemove, 5, SpringLayout.EAST, beerRemove);
        layout.putConstraint(SpringLayout.NORTH, variantRemove, 10, SpringLayout.SOUTH, removeBeerTitle);

        layout.putConstraint(SpringLayout.WEST, removeBeerButton, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, removeBeerButton, 15, SpringLayout.SOUTH, variantRemove);



        addBeerButton.addActionListener(e -> {
            UserDAO udao = new UserDAO();
            if (!beerAdd.getText().isBlank() && !variantAdd.getText().isBlank() && !percentageAdd.getText().isBlank() && !colorAdd.getText().isBlank() && !breweryAdd.getText().isBlank() ) {
                udao.addUserBeer(beerAdd.getText().trim(), variantAdd.getText().trim(), Double.parseDouble(percentageAdd.getText().trim()), colorAdd.getText().trim(), breweryAdd.getText().trim(), newFrame, displayBeersTable, model, userID);
            }
            else {
                JOptionPane.showMessageDialog(newFrame, "Please fill out all fields!");
            }
        });



        newFrame.setLocationRelativeTo(null); // Center of screen
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setVisible(true);

    }

    public void addToTable (String[] tableData, String username) {
        model.addRow(tableData);
        tableInfo3.setText(username + " has drank " + model.getRowCount() + " different beers!");
    }


}
