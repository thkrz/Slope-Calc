package com.erdrutsch.slopecalc.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Canvas extends JPanel {

  public Canvas() {
    setLayout(null);
    setBackground(Color.BLACK);
    setPreferredSize(new Dimension(1000, 1000));
  }

  @Override
  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(getBackground());
    g2.fillRect(0, 0, getWidth(), getHeight());
  }
}
