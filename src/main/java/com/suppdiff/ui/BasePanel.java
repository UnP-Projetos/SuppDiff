package com.suppdiff.ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class BasePanel extends JPanel {
    protected JLabel backgroundLabel;
    protected JLabel titleLabel;
    protected JPanel contentPanel;
    protected JPanel centerPanel;

    public BasePanel(String title, CardLayout _cardLayout, JPanel _mainPanel) {
        setLayout(new BorderLayout());

        backgroundLabel = new JLabel();
        backgroundLabel.setLayout(new BorderLayout());
        add(backgroundLabel, BorderLayout.CENTER);

        // SideMenu sideMenu = new SideMenu(cardLayout, mainPanel, UserSession.getInstance().getUserType());
        // add(sideMenu, BorderLayout.WEST);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(0, 0, 0, 100));
        titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        contentPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));
        outerPanel.setOpaque(false);

        centerPanel = new JPanel();
        centerPanel.setBackground(Color.decode("#1A6497"));
        centerPanel.setLayout(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        outerPanel.add(centerPanel, gbc);

        contentPanel.add(outerPanel, BorderLayout.CENTER);

        backgroundLabel.add(contentPanel, BorderLayout.CENTER);

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
        if (width > 0 && height > 0 && backgroundLabel != null) {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("./images/background.png"));
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            backgroundLabel.setIcon(new ImageIcon(resizedImage));
        }
    }
}
