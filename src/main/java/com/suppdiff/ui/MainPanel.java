package com.suppdiff.ui;

import javax.swing.*;
import java.awt.*;

import com.suppdiff.application.services.UserSession;
import com.suppdiff.ui.components.SideMenu;

public class MainPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel sideMenuPanel;
    private UserSession userSession = UserSession.getInstance();

    public MainPanel() {
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        sideMenuPanel = new JPanel(new BorderLayout());

        // Inicialmente não adiciona o SideMenu
        add(contentPanel, BorderLayout.CENTER);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void showSideMenu() {
        if (sideMenuPanel.getComponentCount() == 0) {
            SideMenu sideMenu = new SideMenu(cardLayout, contentPanel, userSession.getUserType());
            sideMenuPanel.add(sideMenu, BorderLayout.WEST);
            add(sideMenuPanel, BorderLayout.WEST);
        }
    }
}
