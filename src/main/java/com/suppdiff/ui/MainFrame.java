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
        HomeScreen homeScreen = new HomeScreen(cardLayout, mainPanel);
        LoginScreen loginScreen = new LoginScreen(cardLayout, mainPanel);
        UserListScreen userListScreen = new UserListScreen(cardLayout, mainPanel);
        CreateScreen userRegistrationScreen = new CreateScreen(cardLayout, mainPanel);
        CreateChamadoScreen createChamadoScreen = new CreateChamadoScreen(cardLayout, mainPanel);
        // UpdateScreen updateScreen = new UpdateScreen(cardLayout, mainPanel);
        ChamadoListScreen chamadoListScreen = new ChamadoListScreen(cardLayout, mainPanel);

        mainPanel.add(homeScreen, "homeScreen");
        mainPanel.add(loginScreen, "loginScreen");
        mainPanel.add(userListScreen, "userListScreen");
        mainPanel.add(userRegistrationScreen, "createScreen");
        mainPanel.add(chamadoListScreen, "chamadoListScreen");
        mainPanel.add(createChamadoScreen, "createChamadoScreen");

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
