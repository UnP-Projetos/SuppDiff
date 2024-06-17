package com.suppdiff.ui;

import javax.swing.*;
import java.awt.*;

import com.suppdiff.domain.entities.Person;
import com.suppdiff.domain.services.UserService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateScreen extends BasePanel {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField cpfField;
    private JTextField phoneField;
    private JButton saveButton;
    private JButton cancelButton;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public CreateScreen(CardLayout _cardLayout, JPanel _mainPanel) {
        super("Cadastro de Usuário", _cardLayout, _mainPanel);
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

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(nameField, gbc);

        // E-mail
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel emailLabel = new JLabel("E-mail:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(emailField, gbc);

        // CPF
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setForeground(Color.WHITE);
        cpfLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(cpfLabel, gbc);

        gbc.gridx = 1;
        cpfField = new JTextField(20);
        cpfField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(cpfField, gbc);

        // Telefone
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel phoneLabel = new JLabel("Telefone:");
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        phoneField = new JTextField(20);
        phoneField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(phoneField, gbc);

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
                String name = nameField.getText();
                String email = emailField.getText();
                String cpf = cpfField.getText();
                String phone = phoneField.getText();

                if (name.isEmpty() || email.isEmpty() || cpf.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(CreateScreen.this, "Por favor, preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    UserService userService = new UserService();
                    Person person = new Person();
                    person.setName(name);
                    person.setEmail(email);
                    person.setCpf(cpf);
                    person.setPhone(phone);
                    userService.save(person);

                    // Retornar para a tela de lista de usuários
                    cardLayout.show(mainPanel, "userListScreen");
                    ((UserListScreen) mainPanel.getComponent(1)).updateTable(); // Atualiza a tabela de usuários
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retornar para a tela de lista de usuários sem salvar
                cardLayout.show(mainPanel, "userListScreen");
            }
        });
    }

}
