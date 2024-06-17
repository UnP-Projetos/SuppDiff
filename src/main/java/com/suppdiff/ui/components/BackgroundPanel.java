package com.suppdiff.ui.components;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    private Image imagemFundo;

    public BackgroundPanel(String caminhoImagem) {
        try {
            imagemFundo = new ImageIcon(caminhoImagem).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
}
