package com.erdrutsch.slopecalc.controls;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Statusbar extends JPanel {
  private final JLabel c = new JLabel();
  private final JLabel s = new JLabel();
  private final JLabel z = new JLabel();

  public Statusbar() {
    createComponents();
  }

  public void showCoordinates(float x, float y) {
    c.setText(String.format("Coordinates: %7.1f, %7.1f", x, y));
  }

  public void showScale(double d) {
    s.setText(String.format("Zoom x %.1f", d));
  }

  public void showZFactor(double d) {
    z.setText(String.format("Z-factor ^ %.1f", d));
  }

  private void createComponents() {
    c.setPreferredSize(new Dimension(200, 24));
    showCoordinates(0.0f, 0.0f);
    s.setPreferredSize(new Dimension(200, 24));
    showScale(1.0);
    z.setPreferredSize(new Dimension(200, 24));
    showZFactor(1.0);
    setLayout(new FlowLayout(FlowLayout.TRAILING, 48, 8));
    add(c);
    add(s);
    add(z);
  }
}
