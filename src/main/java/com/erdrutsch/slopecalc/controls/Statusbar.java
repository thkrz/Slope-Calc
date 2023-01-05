package com.erdrutsch.slopecalc.controls;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Statusbar extends JPanel implements StatusListener {
  private final JLabel c = new JLabel();

  public Statusbar() {
    createComponents();
  }

  @Override
  public void setStatus(float x, float y) {
    c.setText(String.format("%7.2f, %7.2f", x, y));
  }

  private void createComponents() {
    c.setBorder(BorderFactory.createEtchedBorder());
    c.setPreferredSize(new Dimension(200, 24));
    setStatus(0.0f, 0.0f);
    setLayout(new FlowLayout(FlowLayout.LEADING));
    add(c);
  }
}
