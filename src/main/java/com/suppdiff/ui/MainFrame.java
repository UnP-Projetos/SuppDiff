package com.suppdiff.ui;

import java.awt.CardLayout;
import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("SuppDiff");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Adicionando as telas ao CardLayout
        LoginScreen loginScreen = new LoginScreen(cardLayout, mainPanel);
        UserListScreen userListScreen = new UserListScreen(cardLayout, mainPanel);
        CreateScreen userRegistrationScreen = new CreateScreen(cardLayout, mainPanel);
        HomeScreen homeScreen = new HomeScreen(cardLayout, mainPanel);
        // UpdateScreen updateScreen = new UpdateScreen(cardLayout, mainPanel);

        mainPanel.add(loginScreen, "loginScreen");
        mainPanel.add(userListScreen, "userListScreen");
        mainPanel.add(userRegistrationScreen, "createEmployeeScreen");
        mainPanel.add(homeScreen, "homeScreen");

        add(mainPanel);

        // Exibindo a tela de login inicialmente
        cardLayout.show(mainPanel, "loginScreen");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
