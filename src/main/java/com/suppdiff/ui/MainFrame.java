package com.suppdiff.ui;

import java.awt.CardLayout;
import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("SuppDiff");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        MainPanel mainPanel = new MainPanel();
        setContentPane(mainPanel);
        
        CardLayout cardLayout = mainPanel.getCardLayout();
        JPanel contentPanel = mainPanel.getContentPanel();

        // Instanciando as telas
        HomeScreen homeScreen = new HomeScreen(cardLayout, contentPanel);
        LoginScreen loginScreen = new LoginScreen(cardLayout, contentPanel);
        UserListScreen userListScreen = new UserListScreen(cardLayout, contentPanel);
        CreateScreen createScreen = new CreateScreen(cardLayout, contentPanel);
        // UpdateScreen updateScreen = new UpdateScreen(cardLayout, mainPanel);
        ChamadoListScreen chamadoListScreen = new ChamadoListScreen(cardLayout, contentPanel);

        // Adicionando as telas ao CardLayout
        contentPanel.add(loginScreen, "loginScreen");
        contentPanel.add(homeScreen, "homeScreen");
        contentPanel.add(userListScreen, "userListScreen");
        contentPanel.add(createScreen, "createScreen");
        contentPanel.add(chamadoListScreen, "chamadoListScreen");

        cardLayout.show(contentPanel, "loginScreen");
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
