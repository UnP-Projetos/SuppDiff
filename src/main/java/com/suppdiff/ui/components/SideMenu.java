package com.suppdiff.ui.components;

import javax.swing.*;

import com.suppdiff.application.enums.TypeUser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenu extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    public SideMenu(CardLayout _cardLayout, JPanel _mainPanel, TypeUser typeUser) {
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
        
        JButton homeButton = new JButton("Home");
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(new Color(48, 77, 110));
        homeButton.setFocusPainted(false);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "homeScreen");
            }
        });
        gbc.gridy = 2;
        gbc.weighty = 0;
        add(homeButton, gbc);
        System.out.println(typeUser);
        if (typeUser == TypeUser.ADMIN) {
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
            gbc.gridy = 3;
            gbc.weighty = 0;
            add(usersButton, gbc);
        }
        
        if (typeUser == TypeUser.CLIENT || typeUser == TypeUser.EMPLOYEE && typeUser != null) {
            JButton ticketsButton = new JButton("Chamados");
            ticketsButton.setForeground(Color.WHITE);
            ticketsButton.setBackground(new Color(48, 77, 110));
            ticketsButton.setFocusPainted(false);
            ticketsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(mainPanel, "chamadoListScreen");
                }
            });
            gbc.gridy = 4;
            add(ticketsButton, gbc);
        }

        gbc.gridy = 4;
        gbc.weighty = 1;
        add(Box.createVerticalGlue(), gbc);
    }
}
