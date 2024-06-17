package com.suppdiff.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenu extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    public SideMenu(CardLayout _cardLayout, JPanel _mainPanel) {
        this.cardLayout = _cardLayout;
        this.mainPanel = _mainPanel;

        setLayout(new GridBagLayout());
        setBackground(new Color(32, 58, 84));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(32, 58, 84));
        logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("../images/logo.png"));
        imageLabel.setIcon(imageIcon);
        logoPanel.add(imageLabel);

        JLabel textLabel = new JLabel("SuppDiff");
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 18));
        logoPanel.add(textLabel);

        gbc.gridy = 0;
        add(logoPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.1;
        add(Box.createVerticalStrut(20), gbc);

        JButton usersButton = new JButton("Usuários");
        usersButton.setForeground(Color.WHITE);
        usersButton.setBackground(new Color(48, 77, 110));
        usersButton.setFocusPainted(false);
        usersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "userListScreen");
            }
        });
        gbc.gridy = 2;
        gbc.weighty = 0;
        add(usersButton, gbc);

        // Criação e configuração do botão de Chamados
        JButton ticketsButton = new JButton("Chamados");
        ticketsButton.setForeground(Color.WHITE);
        ticketsButton.setBackground(new Color(48, 77, 110));
        ticketsButton.setFocusPainted(false);
        gbc.gridy = 3;
        add(ticketsButton, gbc);

        // Preencher o restante do espaço para manter os botões no final
        gbc.gridy = 4;
        gbc.weighty = 1; // Peso máximo para empurrar tudo para o topo
        add(Box.createVerticalGlue(), gbc);
    }
}
