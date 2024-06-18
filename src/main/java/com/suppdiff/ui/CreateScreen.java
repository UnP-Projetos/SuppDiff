package com.suppdiff.ui;

import javax.swing.*;
import java.awt.*;

import com.suppdiff.application.DTO.UserDto;
import com.suppdiff.application.enums.TypeUser;
import com.suppdiff.application.services.UserService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CreateScreen extends BasePanel {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField cpfField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JTextField birdDataField;
    private JButton saveButton;
    private JButton cancelButton;
    private JComboBox<String> typeUserField;

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
        
        // Birth Date
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel birthDateLabel = new JLabel("Data nascimento:");
        birthDateLabel.setForeground(Color.WHITE);
        birthDateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(birthDateLabel, gbc);

        gbc.gridx = 1;
        birdDataField = new JTextField(20);
        birdDataField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(birdDataField, gbc);
        
        // Password
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(passwordField, gbc);

        // Admin, Client, Empoyee
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel typeUserLabel = new JLabel("Tipo User:");
        typeUserLabel.setForeground(Color.WHITE);
        typeUserLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(typeUserLabel, gbc);

        gbc.gridx = 1;
        String[] options = {"Admin", "Cliente", "Suporte"};
        typeUserField = new JComboBox<>(options);
        
        inputPanel.add(typeUserField, gbc);

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

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String cpf = cpfField.getText();
                String phone = phoneField.getText();
                String birdData = birdDataField.getText();
                char[] password = passwordField.getPassword();
                TypeUser typeUser = getSelectedUserType((String) typeUserField.getSelectedItem());
                Date date = null;

                System.out.println(typeUser);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false); 

                try {
                    date = dateFormat.parse(birdData);
                    System.out.println("Data convertida: " + date);

                    String formattedDate = dateFormat.format(date);
                    System.out.println("Data formatada de volta para string: " + formattedDate);

                } catch (Exception ex) {}

                if (name.isEmpty() || email.isEmpty() || cpf.isEmpty() || phone.isEmpty() || birdData.isEmpty() || String.valueOf(password).isEmpty() || typeUser == TypeUser.UNKNOWN) {
                    JOptionPane.showMessageDialog(CreateScreen.this, "Por favor, preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    UserService userService = new UserService();
                    UserDto userDto = new UserDto(name, email, cpf, phone, (Date) date, String.valueOf(password), typeUser);
                    userService.save(userDto);

                    cardLayout.show(mainPanel, "userListScreen");
                    ((UserListScreen) mainPanel.getComponent(2)).updateTable();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "userListScreen");
            }
        });
    }

    public TypeUser getSelectedUserType(String selectedString) {
        switch (selectedString) {
            case "Admin":
                return TypeUser.ADMIN;
            case "Cliente":
                return TypeUser.CLIENT;
            case "Suporte":
                return TypeUser.EMPLOYEE;
            default:
                return TypeUser.UNKNOWN;
        }
    }

}
