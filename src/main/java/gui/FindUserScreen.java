package gui;

import dao.UserDAO;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindUserScreen extends JFrame {
    private JFrame jframe;
    private JLabel findTitle;
    private JLabel inputLabel;
    private JTextField inputField;
    private JButton searchButton;
    private JScrollPane tablePane;
    private JTable displayUserTable;

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
        DefaultTableModel model = new DefaultTableModel(header, 0);
        displayUserTable = new JTable(model);
        displayUserTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        // Table will go in scroll pane, if you click search again, another row is added to the table
        tablePane = new JScrollPane(displayUserTable);

        // FIX WIDTH
        tablePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);



        SpringLayout layout = new SpringLayout();
        jframe.setLayout(layout);
        jframe.add(findTitle);
        jframe.add(inputLabel);
        jframe.add(inputField);
        jframe.add(searchButton);
        jframe.add(tablePane);

        // Layout title
        layout.putConstraint(SpringLayout.WEST, findTitle, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, findTitle, 10, SpringLayout.NORTH, jframe);
        // Layout input
        layout.putConstraint(SpringLayout.WEST, inputLabel, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, inputLabel, 50, SpringLayout.NORTH, jframe);
        layout.putConstraint(SpringLayout.WEST, inputField, 5, SpringLayout.EAST, inputLabel);
        layout.putConstraint(SpringLayout.NORTH, inputField, 50, SpringLayout.NORTH, jframe);
        // Layout savebutton
        layout.putConstraint(SpringLayout.WEST, searchButton, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, searchButton, 50, SpringLayout.NORTH, inputLabel);
        // Layout table
        layout.putConstraint(SpringLayout.WEST, tablePane, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, tablePane, 50, SpringLayout.NORTH, searchButton);


        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserDAO udao = new UserDAO();
                User u = udao.findUser(inputField.getText());
                String[] tableData = {u.getName(), u.getSurname(), u.getDOB().toString(), u.getGender(), u.getFavoriteBeer(), u.getProfession(), u.getResidence(), u.getEmail(), u.getJoiningDate().toString()};
                model.addRow(tableData);
                System.out.println("searched");
            }
        });
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        FindUserScreen s = new FindUserScreen();
    }
}
