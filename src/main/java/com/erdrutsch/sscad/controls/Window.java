package com.erdrutsch.sscad.controls;

import javax.swing.*;

public class Window extends JInternalFrame {
  public Window(String title) {
    this(title, true);
  }

  public Window(String title, boolean visible) {
    super(title, true, true, true, true);
    getContentPane().add(new JLabel("Frame 1  contents..."));
    pack();
    setVisible(visible);
  }
}
