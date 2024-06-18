package com.suppdiff.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.suppdiff.domain.entities.Client;
import com.suppdiff.domain.entities.Person;
import com.suppdiff.domain.entities.Ticket;
import com.suppdiff.application.services.TicketService;
import com.suppdiff.application.services.UserSession;

public class CreateChamadoScreen extends BasePanel {
    private JTextField titleField;
    private JTextArea descriptionField;
    private JButton saveButton;
    private JButton cancelButton;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public CreateChamadoScreen(CardLayout _cardLayout, JPanel _mainPanel) {
        super("Novo Chamado", _cardLayout, _mainPanel);
        this.cardLayout = _cardLayout;
        this.mainPanel = _mainPanel;

        // Painel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Assunto
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel emailLabel = new JLabel("Assunto:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        titleField = new JTextField(20);
        titleField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(titleField, gbc);

        // description
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Expandir a label por duas colunas
        JLabel descriptionLabel = new JLabel("Descrição:");
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(descriptionLabel, gbc);

         // JTextArea dentro de um JScrollPane
         gbc.gridx = 0;
         gbc.gridy = 2;
         gbc.gridwidth = 2; // Expandir o JTextArea por duas colunas
         gbc.fill = GridBagConstraints.BOTH; // Preencher horizontalmente e verticalmente
         gbc.weightx = 1; // Expandir na horizontal
         gbc.weighty = 1; // Expandir na vertical
         descriptionField = new JTextArea();
         descriptionField.setFont(new Font("Arial", Font.PLAIN, 16));
         descriptionField.setLineWrap(true);
         descriptionField.setWrapStyleWord(true);
         JScrollPane scrollPane = new JScrollPane(descriptionField);
         scrollPane.setPreferredSize(new Dimension(250, 250));
         inputPanel.add(scrollPane, gbc);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        saveButton = new JButton("Salvar");
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));
        saveButton.setBackground(new Color(66, 135, 245));
        saveButton.setForeground(Color.WHITE);
        buttonPanel.add(saveButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelButton.setBackground(new Color(66, 135, 245));
        cancelButton.setForeground(Color.WHITE);
        buttonPanel.add(cancelButton);

        centerPanel.add(inputPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Ações dos botões
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para salvar o usuário
                String title = titleField.getText();
                String description = descriptionField.getText();

                if (title.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(CreateChamadoScreen.this, "Por favor, preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    TicketService ticketService = new TicketService();
                    Ticket ticket = new Ticket();
                    ticket.setTitle(title);
                    ticket.setDescription(description);
                    ticket.setStatus("Criado");
                    Person person = UserSession.getInstance().getLoggedInUser();
                    Client client = new Client();
                    client.setId(person.getId());
                    ticket.setClient(client);
                    ticketService.save(ticket);

                    // Retornar para a tela de lista de usuários
                    cardLayout.show(mainPanel, "chamadoListScreen");
                    ((ChamadoListScreen) mainPanel.getComponent(5)).updateTable(); // Atualiza a tabela de usuários
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retornar para a tela de lista de usuários sem salvar
                cardLayout.show(mainPanel, "chamadoListScreen");
            }
        });
    }

}
