package gui;

import dao.UserDAO;
import entity.Beer;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

import static gui.MainScreen.beerglass;

public class CheckUserScreen {

    private final JFrame beersFrame;
    private final JLabel tableInfo3;
    private final JTable displayBeersTable;
    private final JTextField beerAdd;
    private final JTextField variantAdd;
    private JFormattedTextField percentageAdd;
    private final JTextField colorAdd;
    private final JTextField breweryAdd;
    private final DefaultTableModel beermodel;

    public DefaultTableModel getBeermodel() {
        return beermodel;
    }
    public JLabel getTableInfo3() {
        return tableInfo3;
    }

    public CheckUserScreen (User u) {

        beersFrame = new JFrame("MyBrews");
        beersFrame.setIconImage(beerglass.getImage());
        beersFrame.setSize(450, 580);
        // User name title
        JLabel userTitle = new JLabel(u.getName() + " " + u.getSurname());
        userTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        // User email
        JLabel emailInfo = new JLabel(u.getEmail());
        emailInfo.setFont(new Font("Book antiqua", Font.ITALIC, 12));
        // Table info
        JLabel tableInfo1 = new JLabel("This table shows the beers consumed by " + u.getName() + " " + u.getSurname() + ".");
        tableInfo1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JLabel tableInfo2 = new JLabel("Beers can also be added or removed here.");
        tableInfo2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tableInfo3 = new JLabel(""); // Will be replaced
        tableInfo3.setFont(new Font("Tahoma", Font.ITALIC, 14));
        // Prepare table to show search results
        String[] header = {"Beer name", "Variant name", "percentage", "Color", "Brewery", "beerid"};
        beermodel = new DefaultTableModel(header, 0) {
            // Make jtable cells uneditable
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        displayBeersTable = new JTable(beermodel);
        //displayBeersTable.removeColumn(displayBeersTable.getColumn("beerid")); // Hide id column
        displayBeersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Select only one at a time
        displayBeersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Table will go in scroll pane
        JScrollPane tablePane = new JScrollPane(displayBeersTable);
        tablePane.setPreferredSize(new Dimension(380, 200));
        // Add beer title
        JLabel addBeerTitle = new JLabel("Add a beer to " + u.getName() + "'s collection");
        addBeerTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
        // Fields to add beer
        beerAdd = new JTextField(" Beer name ");
        variantAdd = new JTextField(" Beer variant ");

        try {
            MaskFormatter formatter = new MaskFormatter("##.#"); // A possible '0' in front (e.g. 09.0) is parsed away
            formatter.setPlaceholderCharacter('0');
            percentageAdd = new JFormattedTextField(formatter);
            percentageAdd.setColumns(3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JLabel percentageLabel = new JLabel("%");

        colorAdd = new JTextField(" Beer color ");
        breweryAdd = new JTextField(" Brewery ");
        // Add beer button
        JButton addBeerButton = new JButton("Add beer!");
        // Remove beer title
        JLabel removeBeerTitle = new JLabel("Remove the selected beer from " + u.getName() + "'s collection");
        removeBeerTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
        // Remove beer button
        JButton removeBeerButton = new JButton("Remove selected beer!");

        SpringLayout layout = new SpringLayout();
        beersFrame.setLayout(layout);
        beersFrame.add(userTitle);
        beersFrame.add(emailInfo);
        beersFrame.add(tableInfo1);
        beersFrame.add(tableInfo2);
        beersFrame.add(tableInfo3);
        beersFrame.add(tablePane);
        beersFrame.add(addBeerTitle);
        beersFrame.add(beerAdd);
        beersFrame.add(variantAdd);
        beersFrame.add(percentageAdd);
        beersFrame.add(percentageLabel);
        beersFrame.add(colorAdd);
        beersFrame.add(breweryAdd);
        beersFrame.add(addBeerButton);
        beersFrame.add(removeBeerTitle);
        beersFrame.add(removeBeerButton);

        // Layout title
        layout.putConstraint(SpringLayout.WEST, userTitle, 10, SpringLayout.WEST, beersFrame);
        layout.putConstraint(SpringLayout.NORTH, userTitle, 10, SpringLayout.NORTH, beersFrame);
        // Layout email
        layout.putConstraint(SpringLayout.WEST, emailInfo, 10, SpringLayout.WEST, beersFrame);
        layout.putConstraint(SpringLayout.NORTH, emailInfo, 25, SpringLayout.NORTH, userTitle);
        // Layout table info
        layout.putConstraint(SpringLayout.WEST, tableInfo1, 10, SpringLayout.WEST, beersFrame);
        layout.putConstraint(SpringLayout.NORTH, tableInfo1, 40, SpringLayout.NORTH, emailInfo);
        layout.putConstraint(SpringLayout.WEST, tableInfo2, 10, SpringLayout.WEST, beersFrame);
        layout.putConstraint(SpringLayout.NORTH, tableInfo2, 5, SpringLayout.SOUTH, tableInfo1);
        layout.putConstraint(SpringLayout.WEST, tableInfo3, 10, SpringLayout.WEST, beersFrame);
        layout.putConstraint(SpringLayout.NORTH, tableInfo3, 10, SpringLayout.SOUTH, tableInfo2);
        // Layout table scroll pane
        layout.putConstraint(SpringLayout.WEST, tablePane, 10, SpringLayout.WEST, beersFrame);
        layout.putConstraint(SpringLayout.NORTH, tablePane, 5, SpringLayout.SOUTH, tableInfo3);
        // Layout add beer title
        layout.putConstraint(SpringLayout.WEST, addBeerTitle, 10, SpringLayout.WEST, beersFrame);
        layout.putConstraint(SpringLayout.NORTH, addBeerTitle, 10, SpringLayout.SOUTH, tablePane);
        // Layout add beer fields and button
        layout.putConstraint(SpringLayout.WEST, beerAdd, 10, SpringLayout.WEST, beersFrame);
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

        layout.putConstraint(SpringLayout.WEST, addBeerButton, 10, SpringLayout.WEST, beersFrame);
        layout.putConstraint(SpringLayout.NORTH, addBeerButton, 15, SpringLayout.SOUTH, beerAdd);
        // Layout remove beer title
        layout.putConstraint(SpringLayout.WEST, removeBeerTitle, 10, SpringLayout.WEST, beersFrame);
        layout.putConstraint(SpringLayout.NORTH, removeBeerTitle, 20, SpringLayout.SOUTH, addBeerButton);
        // Layout remove beer button
        layout.putConstraint(SpringLayout.WEST, removeBeerButton, 10, SpringLayout.WEST, beersFrame);
        layout.putConstraint(SpringLayout.NORTH, removeBeerButton, 15, SpringLayout.SOUTH, removeBeerTitle);


        addBeerButton.addActionListener(e -> {
            UserDAO udao = new UserDAO();
            // Check for empty fields
            if (!beerAdd.getText().isBlank() && !variantAdd.getText().isBlank() && !percentageAdd.getText().isBlank() && !colorAdd.getText().isBlank() && !breweryAdd.getText().isBlank() ) {
                Beer b = new Beer(beerAdd.getText().trim(), variantAdd.getText().trim(), Double.parseDouble(percentageAdd.getText().trim()), colorAdd.getText().trim(), breweryAdd.getText().trim());
                // Check for duplicates based on name and variant
                // Check if beer already in user table
                boolean duplicate = false;
                for(int i = 0; i < displayBeersTable.getRowCount(); i++){ // For each row, check first two columns
                    // Duplicate beer
                    if(displayBeersTable.getModel().getValueAt(i, 0).toString().equalsIgnoreCase(b.getName()) && displayBeersTable.getModel().getValueAt(i, 1).toString().equalsIgnoreCase(b.getVariant())) {
                        JOptionPane.showMessageDialog(beersFrame, "The user already drank this beer!", "Duplicate beer", JOptionPane.WARNING_MESSAGE);
                        duplicate = true;
                    }
                }
                // No empty fields and no duplicate --> add beer
                if (!duplicate) {
                    b = udao.addUserBeer(b, u.getUserID());
                    // Add to jtable
                    addNewBeerToTable(b, displayBeersTable, beermodel);
                    getTableInfo3().setText(u.getName() + " has drank " + getBeermodel().getRowCount() + " different beers");
                    JOptionPane.showMessageDialog(beersFrame, "Beer added!");
                }
            }
            // Empty fields
            else {
                JOptionPane.showMessageDialog(beersFrame, "Please fill out all fields!");
            }
        });




        removeBeerButton.addActionListener(e -> {
            int beerRowView = displayBeersTable.getSelectedRow(); // Value -1 if none selected
            int beerRow = displayBeersTable.convertRowIndexToModel(beerRowView);
            if (beerRow == -1) {
                JOptionPane.showMessageDialog(beersFrame, "You must select a beer.", "No beer selected", JOptionPane.WARNING_MESSAGE);
            }
            else {
                String beerIDString = (String)displayBeersTable.getModel().getValueAt(beerRow, 5);
                int beerID = Integer.parseInt(beerIDString); // Difficulties with direct casting from object to int
                UserDAO udao = new UserDAO();
                int checkIfDeleted = udao.removeBeer(beerID, u.getUserID());
                // Check if any beer was deleted and delete from table
                if (checkIfDeleted != 0)  {
                    beermodel.removeRow(beerRow);
                    JOptionPane.showMessageDialog(beersFrame, "The beer has been removed!", "Beer removed", JOptionPane.PLAIN_MESSAGE);
                    getTableInfo3().setText(u.getName() + " has drank " + getBeermodel().getRowCount() + " different beers");
                }
                // Delete error
                else {
                    JOptionPane.showMessageDialog(beersFrame, "This beer has already been removed. Try selecting another.", "Beer remove failed", JOptionPane.WARNING_MESSAGE);
                }
            }

        });


        beersFrame.setLocationRelativeTo(null); // Center of screen
        beersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        beersFrame.setVisible(true);

    }


    private void addNewBeerToTable(Beer b, JTable table, DefaultTableModel beermodel) {
        String[] tableData = {b.getName(), b.getVariant(), String.valueOf(b.getAlcoholPercentage()), b.getColor(), b.getBrewery(), Integer.toString(b.getBeerID())};
        beermodel.addRow(tableData);

        // Re-sort table
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(beermodel);
        table.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);

        /*
        VIEW AND MODEL ARE SPLIT:
        The table (which is the view part) will be sorted, but in the model, a newly added beer will still be the last row of the data model.
        So while adding a beer which gets sorted to the second row (of the view) and then selecting it for removal, you select the second row of the model
        while the added beer is at the last row in the model and a wrong beer is removed (visually the beer below the inserted one).
        The following line of code will thus be used in the removeBeerButton actionlistener to pass the correct index to the removeBeer function:

        int beerRow = displayBeersTable.convertRowIndexToModel(beerRowView);
        */


    }


}
