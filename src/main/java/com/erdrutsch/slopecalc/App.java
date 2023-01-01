package com.erdrutsch.slopecalc;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import javax.swing.*;

import com.erdrutsch.slopecalc.controls.*;

public class App extends JFrame {
  private final JDesktopPane mdi = new JDesktopPane();
  private final Statusbar sbar = new Statusbar();
  // private final Toolbar tbar = new Toolbar();
  private final Terminal term = new Terminal();

  App() {
    super("Slope-Calc");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    createComponents();
    createMenu();
  }

  private void createComponents() {
    mdi.add(new Window("Unknown"));

    var panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(mdi, BorderLayout.CENTER);
    panel.add(term, BorderLayout.PAGE_END);
    // add(tbar, BorderLayout.PAGE_NORTH);
    add(panel, BorderLayout.CENTER);
    add(sbar, BorderLayout.PAGE_END);
  }

  private void createMenu() {
    var mbar = new JMenuBar();

    var menu = new JMenu("File");
    mbar.add(menu);

    setJMenuBar(mbar);
  }

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(new FlatLightLaf());
    } catch (Exception e) {
      System.err.println("Failed to initialize Look & Feel");
    }

    SwingUtilities.invokeLater(
        () -> {
          var app = new App();
          app.setVisible(true);
        });
  }
}
