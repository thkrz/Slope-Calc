package com.erdrutsch.slopecalc.controls;

import com.erdrutsch.slopecalc.Model;
import java.io.File;
import java.io.IOException;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class Window extends JInternalFrame {
  private static int id = 0;
  private final Canvas canvas = new Canvas();
  private final Model model;

  public Window() {
    super(null, true, true, true, true);
    model = new Model(id++);
    setTitle(model.getName());
    createComponents();
  }

  public Window(File file) throws ClassNotFoundException, IOException {
    super(file.getName(), true, true, true, true);
    model = Model.load(file);
    createComponents();
  }

  public Canvas getCanvas() {
    return canvas;
  }

  public Model getModel() {
    return model;
  }

  private void createComponents() {
    var pane =
        new JScrollPane(
            canvas, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    var v = pane.getVerticalScrollBar();
    v.setValue(v.getMaximum());
    var tab = new JTabbedPane();
    tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    tab.setTabPlacement(JTabbedPane.BOTTOM);
    tab.addTab("Model", pane);
    tab.addTab("Properties", new JPanel());
    getContentPane().add(tab);
    pack();
    setVisible(true);
  }
}
