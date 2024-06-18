package com.suppdiff.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.suppdiff.application.services.PersonService;
import com.suppdiff.domain.entities.Person;

public class UpdateScreen extends JPanel {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField cpfField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JTextField birthDateField;
    private JButton saveButton;
    private JButton cancelButton;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private Person currentPerson;
    private PersonService personService;

    public UpdateScreen(CardLayout _cardLayout, JPanel _mainPanel) {
        this.cardLayout = _cardLayout;
        this.mainPanel = _mainPanel;
        this.personService = new PersonService();

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(nameLabel, gbc);

        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(nameField, gbc);

        JLabel emailLabel = new JLabel("E-mail:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(emailField, gbc);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(cpfLabel, gbc);

        cpfField = new JTextField(20);
        cpfField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(cpfField, gbc);

        JLabel phoneLabel = new JLabel("Telefone:");
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(phoneLabel, gbc);

        phoneField = new JTextField(20);
        phoneField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(phoneField, gbc);

        JLabel birthDateLabel = new JLabel("Data de Nascimento:");
        birthDateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(birthDateLabel, gbc);

        birthDateField = new JTextField(20);
        birthDateField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 4;
        inputPanel.add(birthDateField, gbc);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 5;
        inputPanel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

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

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "userListScreen");
            }
        });
    }

    public void setPersonData(Person person) {
        this.currentPerson = person;
        nameField.setText(person.getName());
        emailField.setText(person.getEmail());
        cpfField.setText(person.getCpf());
        phoneField.setText(person.getPhone());
        birthDateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(person.getBirthDate()));
        // Populate other fields as needed
    }

    private void saveUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        String cpf = cpfField.getText();
        String phone = phoneField.getText();
        String birthDateStr = birthDateField.getText();
        char[] password = passwordField.getPassword();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = null;
        try {
            birthDate = dateFormat.parse(birthDateStr);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use yyyy-MM-dd.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        currentPerson.setName(name);
        currentPerson.setEmail(email);
        currentPerson.setCpf(cpf);
        currentPerson.setPhone(phone);
        currentPerson.setBirthDate(birthDate);
        currentPerson.setPassword(String.valueOf(password));

        personService.update(currentPerson);
        cardLayout.show(mainPanel, "userListScreen");
    }
}
