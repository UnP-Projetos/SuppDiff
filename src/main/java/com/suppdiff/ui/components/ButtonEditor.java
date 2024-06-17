package com.suppdiff.ui.components;
import javax.swing.*;

import com.suppdiff.ui.UserListScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean clicked;
    private UserListScreen userListScreen; // Referência para a tela principal

    public ButtonEditor(JCheckBox checkBox, UserListScreen userListScreen) {
        super(checkBox);
        this.userListScreen = userListScreen;

        button = new JButton();
        button.setOpaque(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            if ("Editar".equals(label)) {
                userListScreen.handleEdit(); // Chama o método handleEdit da tela principal
            } else if ("Excluir".equals(label)) {
                userListScreen.handleDelete(); // Chama o método handleDelete da tela principal
            }
        }
        clicked = false;
        return label;
    }
}
