package com.suppdiff.ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

import com.suppdiff.domain.services.Authentication;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JPanel {
    private JLabel backgroundLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Authentication authentication = new Authentication();

    public LoginScreen(CardLayout _cardLayout, JPanel _mainPanel) {
        this.cardLayout = _cardLayout;
        this.mainPanel = _mainPanel;

        // Configuração do JPanel
        setLayout(new GridBagLayout());
        backgroundLabel = new JLabel();
        backgroundLabel.setLayout(new GridBagLayout());
        add(backgroundLabel);

        // Painel de conteúdo
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(new Color(0, 0, 0, 120));
        contentPane.setLayout(new GridBagLayout());

        // GridBagConstraints para layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // Título
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel, gbc);

        // Campo de e-mail
        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel emailLabel = new JLabel("E-mail");
        emailLabel.setForeground(Color.WHITE);
        contentPane.add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPane.add(emailField, gbc);

        // Campo de senha
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Senha");
        passwordLabel.setForeground(Color.WHITE);
        contentPane.add(passwordLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPane.add(passwordField, gbc);

        // Botão de login
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(66, 135, 245));
        loginButton.setForeground(Color.WHITE);
        contentPane.add(loginButton, gbc);

        // Ação do botão de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificação simples de login
                final String email = emailField.getText();
                char[] password = passwordField.getPassword();
                
                if (authentication.Login(email, String.valueOf(password))) {
                    cardLayout.show(mainPanel, "homeScreen");
                } else {
                    JOptionPane.showMessageDialog(LoginScreen.this, "E-mail ou senha incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adicionando o painel de conteúdo ao painel de fundo
        backgroundLabel.add(contentPane);

        // Listener para redimensionar a imagem ao redimensionar a janela
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeBackgroundImage();
            }

            @Override
            public void componentShown(ComponentEvent e) {
                resizeBackgroundImage();
            }
        });
    }

    private void resizeBackgroundImage() {
        int width = getWidth();
        int height = getHeight();
        if (width > 0 && height > 0) {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("./images/background.png"));
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            backgroundLabel.setIcon(new ImageIcon(resizedImage));
        }
    }
}
