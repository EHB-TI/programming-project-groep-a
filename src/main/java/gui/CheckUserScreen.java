package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CheckUserScreen {
    private JFrame newFrame;
    private JLabel userTitle;
    private JLabel frameDescription;
    private JScrollPane tablePane;
    private JTable displayBeersTable;
    private JButton addBeer;
    private static DefaultTableModel model = null;

    public CheckUserScreen (String username) {

        newFrame = new JFrame("MyBrews");
        newFrame.setSize(500, 350);
        // User name title
        userTitle = new JLabel(username);
        userTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        // Frame description
        frameDescription = new JLabel("Below is a table showing the user's consumed beers.");
        frameDescription.setFont(new Font("Booq antiqua", Font.PLAIN, 12));
        // Prepare table to show search results
        String[] header = {"Beer name", "Variant name", "percentage", "Color"};
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
        tablePane.setPreferredSize(new Dimension(300, 100));
        // Add beer button
        addBeer = new JButton("Add a beer for this user!");

        SpringLayout layout = new SpringLayout();
        newFrame.setLayout(layout);
        newFrame.add(userTitle);
        newFrame.add(frameDescription);
        newFrame.add(tablePane);
        newFrame.add(addBeer);

        // Layout title
        layout.putConstraint(SpringLayout.WEST, userTitle, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, userTitle, 10, SpringLayout.NORTH, newFrame);
        // Layout small description
        layout.putConstraint(SpringLayout.WEST, frameDescription, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, frameDescription, 25, SpringLayout.NORTH, userTitle);
        // Layout table scroll pane
        layout.putConstraint(SpringLayout.WEST, tablePane, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, tablePane, 40, SpringLayout.NORTH, frameDescription);
        // Layout add beer button
        layout.putConstraint(SpringLayout.WEST, addBeer, 10, SpringLayout.WEST, newFrame);
        layout.putConstraint(SpringLayout.NORTH, addBeer, 40, SpringLayout.NORTH, tablePane);

        newFrame.setLocationRelativeTo(null); // Center of screen
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setVisible(true);

    }

    public void addToTable (String[] tableData) {
        model.addRow(tableData);
    }


}
