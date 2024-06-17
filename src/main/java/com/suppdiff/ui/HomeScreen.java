package com.suppdiff.ui;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeScreen extends BasePanel {
    private JLabel imageLabel;
    public HomeScreen(CardLayout _cardLayout, JPanel _mainPanel) {
        super("Home", _cardLayout, _mainPanel);

        imageLabel = new JLabel();
        imageLabel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel nameLabel = new JLabel("SuppDiif");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 48));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(nameLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(imageLabel, gbc);

        centerPanel.add(inputPanel, BorderLayout.CENTER);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeImage();
            }

            @Override
            public void componentShown(ComponentEvent e) {
                resizeImage();
            }
        });
    }
    
    private void resizeImage() {
        int width = getWidth();
        int height = getHeight();
        if (width > 0 && height > 0 && imageLabel != null) {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("./images/logo.png"));
            imageLabel.setIcon(originalIcon);
        }
    }
}
