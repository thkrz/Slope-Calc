package com.erdrutsch.slopecalc.controls;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class Window extends JInternalFrame {
  private final Canvas canvas = new Canvas();

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
    tab.addTab("Model", new JScrollPane(canvas));
    tab.addTab("Properties", new JPanel());
    getContentPane().add(tab);
  }
}
