package com.suppdiff.ui.components;
import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.suppdiff.ui.ChamadoListScreen;

public class ChamadoButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean clicked;
    private final ChamadoListScreen chamadoListScreen; // Referência para a tela principal

    public ChamadoButtonEditor(JCheckBox checkBox, ChamadoListScreen chamadoListScreen) {
        super(checkBox);
        this.chamadoListScreen = chamadoListScreen;

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
                chamadoListScreen.handleEdit(); // Chama o método handleEdit da tela principal
            } else if ("Excluir".equals(label)) {
                chamadoListScreen.handleDelete(); // Chama o método handleDelete da tela principal
            }
        }
        clicked = false;
        return label;
    }
}
