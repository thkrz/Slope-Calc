package com.erdrutsch.slopecalc.controls;

import com.erdrutsch.slopecalc.Model;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class Window extends JInternalFrame {
  private final Canvas canvas = new Canvas();
  private final Model model = new Model();

  public Window() {
    super(Model.DEFAULT_NAME, true, true, true, true);
    createComponents();
    pack();
    setVisible(true);
  }

  public Model getModel() {
    return model;
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
