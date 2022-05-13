package gui;

import dao.UserDAO;
import entity.User;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screen extends JFrame {
    private JFrame jframe;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel surnameLabel;
    private JTextField surnameField;
    private JLabel DOBLabel;
    private JFormattedTextField DOBField;
    private JLabel genderLabel;
    private JRadioButton optionM;
    private JRadioButton optionV;
    private JRadioButton optionX;
    private ButtonGroup genderGroup;
    private JLabel beerLabel;
    private JTextField beerField;
    private JLabel professionLabel;
    private JTextField professionField;
    private JLabel residenceLabel;
    private JTextField residenceField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JButton saveButton;

    public Screen() {
        jframe = new JFrame("MyBrews");
        jframe.setSize(500,800);
        // Name
        nameLabel = new JLabel("Name");
        nameField = new JTextField(30);
        // Surname
        surnameLabel = new JLabel("Surname");
        surnameField = new JTextField(30);
        // DOB
        DOBLabel = new JLabel("DOB (YYYY-MM-DD)");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        DateFormatter formatter = new DateFormatter(format);
        DOBField = new JFormattedTextField(formatter);
        DOBField.setColumns(11);
        // Gender
        genderLabel = new JLabel("Gender");
        optionM = new JRadioButton("M");
        optionV = new JRadioButton("V");
        optionX = new JRadioButton("X");
        genderGroup = new ButtonGroup();
        genderGroup.add(optionM);
        genderGroup.add(optionV);
        genderGroup.add(optionX);
        // Favorite beer
        beerLabel = new JLabel("Favorite beer");
        beerField = new JTextField(30);
        // Profession
        professionLabel = new JLabel("Profession");
        professionField = new JTextField(30);
        // Residence
        residenceLabel = new JLabel("Residence");
        residenceField = new JTextField(30);
        // Email
        emailLabel = new JLabel("e-mail address");
        emailField = new JTextField(30);
        // Save user button
        saveButton = new JButton("Add user to database!");

        jframe.setLayout(new FlowLayout()); // Tijdelijke layout voor tests
        jframe.add(nameLabel);
        jframe.add(nameField);
        jframe.add(surnameLabel);
        jframe.add(surnameField);
        jframe.add(DOBLabel);
        jframe.add(DOBField);
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
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                final String DOBString = DOBField.getText();
                Date DOB = null;
                try {
                    DOB = formatter.parse(DOBString);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                final java.sql.Date sqlDOB = new java.sql.Date(DOB.getTime());
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
                User u = new User(name, surname, sqlDOB, gender, beer, profession, residence, email);
                udao.saveUser(u);
                System.out.println("added");
            }
        });
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Screen s = new Screen();
    }
}
