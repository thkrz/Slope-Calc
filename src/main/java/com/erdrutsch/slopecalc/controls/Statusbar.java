package com.erdrutsch.slopecalc.controls;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Statusbar extends JPanel {
  private final JLabel info = new JLabel();

  public Statusbar() {
    createComponents();
  }

  public void setStatus(String s) {
    info.setText(s);
  }

  private void createComponents() {
    // info.setBorder(new BevelBorder(BevelBorder.LOWERED));
    info.setText("INFO");
    setLayout(new FlowLayout(FlowLayout.LEADING));
    add(info);
  }
}
