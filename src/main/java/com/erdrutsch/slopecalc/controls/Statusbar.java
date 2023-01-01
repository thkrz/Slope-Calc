package com.erdrutsch.slopecalc.controls;

import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class Statusbar extends JPanel {
  private final JLabel info = new JLabel();

  public Statusbar() {
    createComponents();
  }

  private void createComponents() {
    // info.setBorder(new BevelBorder(BevelBorder.LOWERED));
    info.setText("INFO");
    setLayout(new FlowLayout(FlowLayout.LEADING));
    add(info);
  }
}
