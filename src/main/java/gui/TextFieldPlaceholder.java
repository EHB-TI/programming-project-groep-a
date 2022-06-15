package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

// This class creates a custom placeholder and adds it to a JTextField

public class TextFieldPlaceholder extends JLabel {

    public TextFieldPlaceholder(String placeholderText, JTextField field) {
        // Set 'label' text and color
        this.setText(placeholderText);
        this.setForeground(Color.LIGHT_GRAY);
        // Add the label to the text field
        field.setLayout(new BorderLayout());
        field.add(this);

        // set label invisible when text field is focused and visible when unfocused with blank text
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Blank, not empty, spaces don't count
                if (field.getText().isBlank()) {
                    setVisible(true);
                }
            }
        });

    }

    public TextFieldPlaceholder(String placeholderText, JFormattedTextField field) {
        // Set 'label' text and color
        this.setText(placeholderText);
        this.setForeground(Color.LIGHT_GRAY);
        // Add the label to the text field
        field.setLayout(new BorderLayout());
        field.add(this);

        // set label invisible when text field is focused and visible when unfocused with blank text
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Blank, not empty, spaces don't count
                if (field.getText().isBlank()) {
                    setVisible(true);
                }
            }
        });

    }


}
