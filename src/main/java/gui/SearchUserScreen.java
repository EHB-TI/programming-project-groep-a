package gui;

import dao.UserDAO;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchUserScreen extends JFrame {
    private JFrame jframe;
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton searchButton;

    public SearchUserScreen() {
        jframe = new JFrame("MyBrews");
        jframe.setSize(500, 800);

        nameLabel = new JLabel("Search by name");
        nameField = new JTextField(30);
        searchButton = new JButton("Search!");

        jframe.setLayout(new FlowLayout()); // Tijdelijke layout voor tests
        jframe.add(nameLabel);
        jframe.add(nameField);
        jframe.add(searchButton);





        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                UserDAO udao = new UserDAO();
                User u = new User(name, surname, sqlDOB, gender, beer, profession, residence, email);
                udao.saveUser(u);
                System.out.println("added");
            }

        });
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
