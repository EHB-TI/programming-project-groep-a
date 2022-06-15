package gui;

import dao.EventDAO;
import entity.*;
import entity.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.TreeSet;

public class AddEventScreen extends JFrame{

    private JPanel panel;
    private JLabel lbl_title;
    private JTextField txt_title;
    private JButton btn_add;

    public AddEventScreen(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 150);
        this.setTitle("Add event");

        this.add(getJPanel());
        panel.add(getLbl_title());
        panel.add(getTxt_title());
        panel.add(getBtn_add());
    }

    private JPanel getJPanel() {
        if (panel == null)
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        return panel;
    }

    public JLabel getLbl_title() {
        if (lbl_title == null)
            lbl_title = new JLabel("Name of event: ");
        return lbl_title;
    }

    public JTextField getTxt_title() {
        if (txt_title == null)
            txt_title = new JTextField(15);
        return txt_title;
    }

    public JButton getBtn_add() {
        if (btn_add == null)
            btn_add = new JButton("Add");
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Event event = new Event(txt_title.getText());
                EventDAO dao = new EventDAO();
                dao.saveEvent(event);
            }
        });
        return btn_add;
    }


    public static void main(String[] args) {
        AddEventScreen addEventScreen = new AddEventScreen();
    }

}
