package com.suppdiff.ui;

import javax.swing.*;
import java.awt.*;

import com.suppdiff.domain.entities.Person;
import com.suppdiff.domain.services.UserService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;

public class UpdateScreen extends BasePanel {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField cpfField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JTextField bithdDataField;
    private JButton saveButton;
    private JButton cancelButton;
    private JComboBox<String> typeUserField;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public UpdateScreen(Person person, CardLayout _cardLayout, JPanel _mainPanel) {
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
        nameField.setText(person.getName());
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
        emailField.setText(person.getEmail());
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
        cpfField.setText(person.getCpf());
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
        phoneField.setText(person.getPhone());
        inputPanel.add(phoneField, gbc);
        
        // Bird Date
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel birdDateLabel = new JLabel("Tipo User:");
        birdDateLabel.setForeground(Color.WHITE);
        birdDateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(birdDateLabel, gbc);

        gbc.gridx = 1;
        bithdDataField = new JTextField(20);
        bithdDataField.setFont(new Font("Arial", Font.PLAIN, 16));
        bithdDataField.setText(person.getBirthDate().toString());
        inputPanel.add(bithdDataField, gbc);
        
        // Password
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel passwordLabel = new JLabel("Tipo User:");
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

        // Ações dos botões
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para salvar o usuário
                String name = nameField.getText();
                String email = emailField.getText();
                String cpf = cpfField.getText();
                String phone = phoneField.getText();
                String birdData = bithdDataField.getText();
                char[] password = passwordField.getPassword();
                Date date = null;

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false); 

                try {
                    date = dateFormat.parse(birdData);
                    System.out.println("Data convertida: " + date);

                    String formattedDate = dateFormat.format(date);
                    System.out.println("Data formatada de volta para string: " + formattedDate);

                } catch (Exception ex) {}

                if (name.isEmpty() || email.isEmpty() || cpf.isEmpty() || phone.isEmpty() || birdData.isEmpty() || String.valueOf(password).isEmpty()) {
                    JOptionPane.showMessageDialog(UpdateScreen.this, "Por favor, preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    UserService userService = new UserService();
                    Person person = new Person();
                    person.setName(name);
                    person.setEmail(email);
                    person.setCpf(cpf);
                    person.setPhone(phone);
                    person.setBirthDate((Date) date);
                    person.setPassword(String.valueOf(password));
                    userService.save(person);

                    // Retornar para a tela de lista de usuários
                    cardLayout.show(mainPanel, "homeScreen");
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
