package gui;

import dao.EventDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FindEventScreen extends JFrame{

        private JPanel panel1;
        private JPanel panel2;
        private JLabel lbl_title;
        private JTextField txt_title;
        private JButton btn_find;
        private JTextArea txt_found;

        public FindEventScreen(){
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setVisible(true);
            this.setSize(500, 150);
            this.setTitle("Find event");

            this.add(getJPanel1(), BorderLayout.NORTH);
            this.add(getJPanel2(), BorderLayout.CENTER);
            panel1.add(getLbl_title());
            panel1.add(getTxt_title());
            panel1.add(getBtn_find());
            panel2.add(getTxt_found());
        }

    public JTextArea getTxt_found() {
        if (txt_found == null)
            txt_found = new JTextArea();
        txt_found.setColumns(20);
        return txt_found;
    }

        private JPanel getJPanel1() {
            if (panel1 == null)
                panel1 = new JPanel();
            return panel1;
        }

    public JPanel getJPanel2() {
        if (panel2 == null)
            panel2 = new JPanel();
        return panel2;
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

        public JButton getBtn_find() {
            if (btn_find == null)
                btn_find = new JButton("Find");
            btn_find.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EventDAO dao = new EventDAO();
                    ArrayList<String> eventsFound = dao.findEventByTitle(txt_title.getText());
                    for (String s : eventsFound) {
                        txt_found.append(s + '\n');
                        System.out.println("I'm in the loop");                    }
                }});
            return btn_find;
        }




        public static void main(String[] args) {
            FindEventScreen findEventScreen = new FindEventScreen();
        }

    }
