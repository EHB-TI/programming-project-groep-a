package gui;

import dao.UserDAO;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Screen extends JFrame {

    public static void main(String[] args) {
        JFrame jframe = new JFrame("MyBrews");
        jframe.setSize(500,800);
        // Name
        JLabel nameLabel = new JLabel("Name");
        JTextField nameField = new JTextField(30);
        // Surname
        JLabel surnameLabel = new JLabel("Surname");
        JTextField surnameField = new JTextField(30);
        // Age
        JLabel ageLabel = new JLabel("Age");
        JTextField ageField = new JTextField(3);
        ageField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });
        // Gender
        JLabel genderLabel = new JLabel("Gender");
        JRadioButton optionM = new JRadioButton("M");
        JRadioButton optionV = new JRadioButton("V");
        JRadioButton optionX = new JRadioButton("X");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(optionM);
        genderGroup.add(optionV);
        genderGroup.add(optionX);
        // Favorite beer
        JLabel beerLabel = new JLabel("Favorite beer");
        JTextField beerField = new JTextField(30);
        // Profession
        JLabel professionLabel = new JLabel("Profession");
        JTextField professionField = new JTextField(30);
        // Residence
        JLabel residenceLabel = new JLabel("Residence");
        JTextField residenceField = new JTextField(30);
        // Email
        JLabel emailLabel = new JLabel("e-mail address");
        JTextField emailField = new JTextField(30);
        // Save user button
        JButton saveButton = new JButton("Add user");
        jframe.setLayout(new FlowLayout()); // Tijdelijke layout voor tests
        jframe.add(nameLabel);
        jframe.add(nameField);
        jframe.add(surnameLabel);
        jframe.add(surnameField);
        jframe.add(ageLabel);
        jframe.add(ageField);
        jframe.add(genderLabel);
        jframe.add(optionM);
        jframe.add(optionV);
        jframe.add(optionX);
        jframe.add(beerLabel);
        jframe.add(beerField);
        jframe.add(professionLabel);
        jframe.add(professionField);
        jframe.add(residenceLabel);
        jframe.add(residenceField);
        jframe.add(emailLabel);
        jframe.add(emailField);
        jframe.add(saveButton);

        saveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                final String name = nameField.getText();
                final String surname = surnameField.getText();
                final int age = Integer.parseInt(ageField.getText());
                final String gender;
                if (optionM.isSelected()) {gender = "M";}
                else if (optionV.isSelected()) {gender = "V";}
                else if (optionX.isSelected()) {gender = "X";}
                else {gender = null;} // Otherwise gender not initialised error
                final String beer = beerField.getText();
                final String profession = professionField.getText();
                final String residence = residenceField.getText();
                final String email = emailField.getText();

                UserDAO udao = new UserDAO();
                User u = new User(name, surname, age, gender, beer, profession, residence, email);
                udao.saveUser(u);
                System.out.println("added");
            }
        });

        // later:
        // jb.addActionListener(new AddUserListener());
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
