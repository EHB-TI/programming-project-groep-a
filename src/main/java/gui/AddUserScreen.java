package gui;

import dao.UserDAO;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static gui.MainScreen.beerGlass;


public class AddUserScreen {

    private final JFrame jframe;
    private final JTextField nameField;
    private final JTextField surnameField;
    private final JFormattedTextField DOBField;
    private final JRadioButton optionM;
    private final JRadioButton optionV;
    private final JRadioButton optionX;
    private final JTextField beerField;
    private final JTextField professionField;
    private final JTextField residenceField;
    private final JTextField emailField;

    public AddUserScreen() {
        jframe = new JFrame("MyBrews");
        jframe.setIconImage(beerGlass.getImage());
        jframe.setSize(450,550);
        // Form title
        JLabel formTitle = new JLabel("Add someone to the MyBrews database");
        formTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        // Name
        JLabel nameLabel = new JLabel("Name");
        nameField = new JTextField(30);
        // Surname
        JLabel surnameLabel = new JLabel("Surname");
        surnameField = new JTextField(30);
        // DOB
        JLabel DOBLabel = new JLabel("DOB (YYYY-MM-DD)");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DOBField = new JFormattedTextField(format);
        DOBField.setColumns(11);
        // Gender
        JLabel genderLabel = new JLabel("Gender");
        optionM = new JRadioButton("M");
        optionV = new JRadioButton("V");
        optionX = new JRadioButton("X");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(optionM);
        genderGroup.add(optionV);
        genderGroup.add(optionX);
        // Favorite beer
        JLabel beerLabel = new JLabel("Favorite beer");
        beerField = new JTextField(30);
        // Profession
        JLabel professionLabel = new JLabel("Profession");
        professionField = new JTextField(30);
        // Residence
        JLabel residenceLabel = new JLabel("Residence");
        residenceField = new JTextField(30);
        // Email
        JLabel emailLabel = new JLabel("e-mail address");
        emailField = new JTextField(30);
        // Save user button
        JButton saveButton = new JButton("Add user to database!");

        SpringLayout layout = new SpringLayout();
        jframe.setLayout(layout);
        jframe.add(formTitle);
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

        // Layout form title
        layout.putConstraint(SpringLayout.WEST, formTitle, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, formTitle, 10, SpringLayout.NORTH, jframe);
        // Layout name
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 50, SpringLayout.NORTH, jframe);
        layout.putConstraint(SpringLayout.WEST, nameField, 5, SpringLayout.EAST, nameLabel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 50, SpringLayout.NORTH, jframe);
        // Layout surname
        layout.putConstraint(SpringLayout.WEST, surnameLabel, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, surnameLabel, 50, SpringLayout.NORTH, nameLabel);
        layout.putConstraint(SpringLayout.WEST, surnameField, 5, SpringLayout.EAST, surnameLabel);
        layout.putConstraint(SpringLayout.NORTH, surnameField, 50, SpringLayout.NORTH, nameLabel);
        // Layout DOB
        layout.putConstraint(SpringLayout.WEST, DOBLabel, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, DOBLabel, 50, SpringLayout.NORTH, surnameLabel);
        layout.putConstraint(SpringLayout.WEST, DOBField, 5, SpringLayout.EAST, DOBLabel);
        layout.putConstraint(SpringLayout.NORTH, DOBField, 50, SpringLayout.NORTH, surnameLabel);
        // Layout gender
        layout.putConstraint(SpringLayout.WEST, genderLabel, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, genderLabel, 50, SpringLayout.NORTH, DOBLabel);
        layout.putConstraint(SpringLayout.WEST, optionM, 5, SpringLayout.EAST, genderLabel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, optionM, 0, SpringLayout.VERTICAL_CENTER, genderLabel);
        layout.putConstraint(SpringLayout.WEST, optionV, 5, SpringLayout.EAST, optionM);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, optionV, 0, SpringLayout.VERTICAL_CENTER, genderLabel);
        layout.putConstraint(SpringLayout.WEST, optionX, 5, SpringLayout.EAST, optionV);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, optionX, 0, SpringLayout.VERTICAL_CENTER, genderLabel);
        // Layout beer
        layout.putConstraint(SpringLayout.WEST, beerLabel, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, beerLabel, 50, SpringLayout.NORTH, genderLabel);
        layout.putConstraint(SpringLayout.WEST, beerField, 5, SpringLayout.EAST, beerLabel);
        layout.putConstraint(SpringLayout.NORTH, beerField, 50, SpringLayout.NORTH, genderLabel);
        // Layout profession
        layout.putConstraint(SpringLayout.WEST, professionLabel, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, professionLabel, 50, SpringLayout.NORTH, beerLabel);
        layout.putConstraint(SpringLayout.WEST, professionField, 5, SpringLayout.EAST, professionLabel);
        layout.putConstraint(SpringLayout.NORTH, professionField, 50, SpringLayout.NORTH, beerField);
        // Layout residence
        layout.putConstraint(SpringLayout.WEST, residenceLabel, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, residenceLabel, 50, SpringLayout.NORTH, professionLabel);
        layout.putConstraint(SpringLayout.WEST, residenceField, 5, SpringLayout.EAST, residenceLabel);
        layout.putConstraint(SpringLayout.NORTH, residenceField, 50, SpringLayout.NORTH, professionLabel);
        // Layout email
        layout.putConstraint(SpringLayout.WEST, emailLabel, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, emailLabel, 50, SpringLayout.NORTH, residenceLabel);
        layout.putConstraint(SpringLayout.WEST, emailField, 5, SpringLayout.EAST, emailLabel);
        layout.putConstraint(SpringLayout.NORTH, emailField, 50, SpringLayout.NORTH, residenceLabel);
        // Layout savebutton
        layout.putConstraint(SpringLayout.WEST, saveButton, 10, SpringLayout.WEST, jframe);
        layout.putConstraint(SpringLayout.NORTH, saveButton, 50, SpringLayout.NORTH, emailLabel);


        saveButton.addActionListener(e -> {
            final String name = nameField.getText().trim();
            final String surname = surnameField.getText().trim();
            final String DOBString = DOBField.getText();
            LocalDate DOB;
            try {
                DOB = LocalDate.parse(DOBString);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(jframe, "Please fill out a correct date!", "Incorrect date format", JOptionPane.WARNING_MESSAGE);
                return; // Stop method, otherwise the 'fill out all fields' dialog will also unnecessarily be shown
            }
            final String gender;
            if (optionM.isSelected()) {gender = "M";}
            else if (optionV.isSelected()) {gender = "V";}
            else if (optionX.isSelected()) {gender = "X";}
            else {gender = null;} // Otherwise gender not initialised error
            final String beer = beerField.getText().trim();
            final String profession = professionField.getText().trim();
            final String residence = residenceField.getText().trim();
            final String email = emailField.getText().trim();
            if (!name.isBlank() && !surname.isBlank() && DOB != null && gender != null && !beer.isBlank() && !profession.isBlank() && !residence.isBlank() && !email.isBlank()) {
                // Put fields in user to pass to saveUser function
                User u = new User(name, surname, DOB, gender, beer, profession, residence, email);
                UserDAO udao = new UserDAO();
                // Parameter jframe to show dialogs from saveUser function
                udao.saveUser(u, jframe);
            }
            else {
                JOptionPane.showMessageDialog(jframe, "Please fill out all fields!", "Empty form", JOptionPane.WARNING_MESSAGE);
            }
        });
        jframe.setLocationRelativeTo(null); // Center of screen
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframe.setVisible(true);
    }

    public static void main(String[] args) {
        new AddUserScreen();
    }
}
