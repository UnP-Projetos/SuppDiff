package com.suppdiff.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.suppdiff.domain.entities.Person;
import com.suppdiff.domain.services.UserService;
import com.suppdiff.ui.components.ButtonEditor;
import com.suppdiff.ui.components.ButtonRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class UserListScreen extends BasePanel {
    private JTable userTable;
    private JButton newUserButton;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private UserService userService;

    public UserListScreen(CardLayout _cardLayout, JPanel _mainPanel) {
        super("Lista de Usuários", _cardLayout, _mainPanel);
        this.cardLayout = _cardLayout;
        this.mainPanel = _mainPanel;
        this.userService = new UserService();

        Vector<String> columnNames = new Vector<>();
        columnNames.add("#");
        columnNames.add("Nome");
        columnNames.add("E-mail");
        columnNames.add("CPF");
        columnNames.add("Telefone");
        columnNames.add("Editar");
        columnNames.add("Excluir");

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5 || column == 6; // Permite edição apenas nas colunas de editar e excluir
            }
        };

        userTable = new JTable(model);
        userTable.setRowHeight(30);
        userTable.setFont(new Font("Arial", Font.PLAIN, 16));
        userTable.setShowGrid(false);
        userTable.setFillsViewportHeight(true);
        userTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        TableColumn editColumn = userTable.getColumnModel().getColumn(5);
        editColumn.setCellRenderer(new ButtonRenderer());
        editColumn.setCellEditor(new ButtonEditor(new JCheckBox(), this));

        TableColumn deleteColumn = userTable.getColumnModel().getColumn(6);
        deleteColumn.setCellRenderer(new ButtonRenderer());
        deleteColumn.setCellEditor(new ButtonEditor(new JCheckBox(), this));

        JScrollPane scrollPane = new JScrollPane(userTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(52, 74, 100));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JButton updateList = new JButton("Atualizar");
        updateList.setFont(new Font("Arial", Font.BOLD, 16));
        updateList.setBackground(new Color(66, 135, 245));
        updateList.setForeground(Color.WHITE);
        updateList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });

        newUserButton = new JButton("Novo Usuário");
        newUserButton.setFont(new Font("Arial", Font.BOLD, 16));
        newUserButton.setBackground(new Color(66, 135, 245));
        newUserButton.setForeground(Color.WHITE);
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "createScreen");
            }
        });

        buttonPanel.add(updateList);
        buttonPanel.add(newUserButton);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        updateTable();
    }

    public void updateTable() {
        List<Person> persons = userService.getAll();

        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0);

        for (Person person : persons) {
            Vector<Object> row = new Vector<>();
            row.add(person.getId());
            row.add(person.getName());
            row.add(person.getEmail());
            row.add(person.getCpf());
            row.add(person.getPhone());
            row.add("Editar");
            row.add("Excluir");
            model.addRow(row);
        }
    }

    // Método chamado ao clicar no botão "Editar"
    public void handleEdit() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            Object id = userTable.getValueAt(selectedRow, 0);
            Person person = userService.getById((int) id);
            if (person != null) {
                // Aqui você pode implementar a lógica de edição, por exemplo, mostrar uma tela de edição
                // Exemplo: new EditUserDialog(person, userRepository, this).setVisible(true);
                System.out.println("Editar pessoa com ID: " + id);
            }
        }
    }

    // Método chamado ao clicar no botão "Excluir"
    public void handleDelete() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            Object id = userTable.getValueAt(selectedRow, 0);
            int response = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este usuário?", "Confirmação", JOptionPane.YES_NO_OPTION);
            
            if (response == JOptionPane.YES_OPTION) {
                try {
                    userService.delete((int) id);
                    // updateTable();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao excluir usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}
