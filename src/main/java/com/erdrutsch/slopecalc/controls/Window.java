package com.erdrutsch.slopecalc.controls;

import javax.swing.*;

public class Window extends JInternalFrame {
  public Window(String title) {
    this(title, true);
  }

  public Window(String title, boolean visible) {
    super(title, true, true, true, true);
    createComponents();
    pack();
    setVisible(visible);
  }

  private void createComponents() {
    var tab = new JTabbedPane();
    tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    tab.setTabPlacement(JTabbedPane.BOTTOM);
    var sc = new JScrollPane(new Canvas());
    tab.addTab("Model", sc);
    tab.addTab("Properties", new JPanel());
    getContentPane().add(tab);
  }
}
