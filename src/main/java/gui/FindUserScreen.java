package gui;

import dao.UserDAO;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindUserScreen extends JFrame {
    private JFrame jframe;
    private JLabel inputLabel;
    private JTextField inputField;
    private JButton searchButton;
    private JTextArea displayUser;

    public FindUserScreen() {
        jframe = new JFrame("MyBrews");
        jframe.setSize(500, 800);

        inputLabel = new JLabel("Search by name");
        inputField = new JTextField(30);
        searchButton = new JButton("Search!");
        displayUser = new JTextArea();
        displayUser.setEditable(false);
        displayUser.setLineWrap(true);

        jframe.setLayout(new FlowLayout()); // Tijdelijke layout voor tests
        jframe.add(inputLabel);
        jframe.add(inputField);
        jframe.add(searchButton);
        jframe.add(displayUser);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserDAO udao = new UserDAO();
                User u = udao.findUser(inputField.getText());
                displayUser.setText(u.toString());
            }

        });
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        FindUserScreen s = new FindUserScreen();
    }
}
