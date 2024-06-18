package com.suppdiff.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.suppdiff.application.services.TicketService;
import com.suppdiff.domain.entities.Ticket;
import com.suppdiff.ui.components.ChamadoButtonEditor;
import com.suppdiff.ui.components.ButtonRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class ChamadoListScreen extends BasePanel {
    private JTable chamadoTable;
    private JButton newChamadoButton;
    private JPanel mainChamadoPanel;
    private CardLayout cardLayout;
    private TicketService ticketService;

    public ChamadoListScreen(CardLayout _cardLayout, JPanel _mainPanel) {
        super("Lista de Chamados", _cardLayout, _mainPanel);
        this.cardLayout = _cardLayout;
        this.mainChamadoPanel = _mainPanel;
        this.ticketService = new TicketService();

        Vector<String> columnNames = new Vector<>();
        columnNames.add("#");
        columnNames.add("Assunto");
        columnNames.add("Atendente");
        columnNames.add("Editar");
        columnNames.add("Excluir");

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // Permite edição apenas nas colunas de editar e excluir
            }
        };

        chamadoTable = new JTable(model);
        chamadoTable.setRowHeight(30);
        chamadoTable.setFont(new Font("Arial", Font.PLAIN, 16));
        chamadoTable.setShowGrid(false);
        chamadoTable.setFillsViewportHeight(true);
        chamadoTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        TableColumn editColumn = chamadoTable.getColumnModel().getColumn(3);
        editColumn.setCellRenderer(new ButtonRenderer());
        editColumn.setCellEditor(new ChamadoButtonEditor(new JCheckBox(), this));

        TableColumn deleteColumn = chamadoTable.getColumnModel().getColumn(4);
        deleteColumn.setCellRenderer(new ButtonRenderer());
        deleteColumn.setCellEditor(new ChamadoButtonEditor(new JCheckBox(), this));

        JScrollPane scrollPane = new JScrollPane(chamadoTable);
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

        newChamadoButton = new JButton("Novo Chamado");
        newChamadoButton.setFont(new Font("Arial", Font.BOLD, 16));
        newChamadoButton.setBackground(new Color(66, 135, 245));
        newChamadoButton.setForeground(Color.WHITE);
        newChamadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainChamadoPanel, "createEmployeeScreen");
            }
        });

        buttonPanel.add(updateList);
        buttonPanel.add(newChamadoButton);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        updateTable();
    }

    public void updateTable() {
        List<Ticket> tickets = ticketService.getAll();

        DefaultTableModel model = (DefaultTableModel) chamadoTable.getModel();
        model.setRowCount(0);

        for (Ticket ticket : tickets) {
            Vector<Object> row = new Vector<>();
            row.add(ticket.getId());
            row.add(ticket.getDescription());
            row.add(ticket.getStatus());
            row.add(ticket.getClient().getName());
            row.add("Editar");
            row.add("Excluir");
            model.addRow(row);
        }
    }

    // Método chamado ao clicar no botão "Editar"
    public void handleEdit() {
        int selectedRow = chamadoTable.getSelectedRow();
        if (selectedRow != -1) {
            Object id = chamadoTable.getValueAt(selectedRow, 0);
            Ticket person = ticketService.getById((int) id);
            if (person != null) {
                // Aqui você pode implementar a lógica de edição, por exemplo, mostrar uma tela de edição
                // Exemplo: new EditUserDialog(person, userRepository, this).setVisible(true);
                System.out.println("Editar pessoa com ID: " + id);
            }
        }
    }

    // Método chamado ao clicar no botão "Excluir"
    public void handleDelete() {
        int selectedRow = chamadoTable.getSelectedRow();
        if (selectedRow != -1) {
            Object id = chamadoTable.getValueAt(selectedRow, 0);
            int response = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este usuário?", "Confirmação", JOptionPane.YES_NO_OPTION);
            
            if (response == JOptionPane.YES_OPTION) {
                try {
                    ticketService.delete((int) id);
                    // updateTable();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao excluir usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}
