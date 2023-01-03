package com.erdrutsch.slopecalc;

import com.erdrutsch.slopecalc.controls.Statusbar;
import com.erdrutsch.slopecalc.controls.Terminal;
import com.erdrutsch.slopecalc.controls.Toolbar;
import com.erdrutsch.slopecalc.controls.Window;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class App extends JFrame {
  private final JDesktopPane mdi = new JDesktopPane();
  private final Statusbar sbar = new Statusbar();
  private final Toolbar tbar = new Toolbar();
  private final Terminal term = new Terminal();

  App() {
    super("Slope-Calc");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    createComponents();
    createMenu();
  }

  private void createComponents() {
    mdi.add(new Window());

    var pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mdi, term);
    pane.setResizeWeight(1.0);
    add(tbar, BorderLayout.PAGE_START);
    add(pane, BorderLayout.CENTER);
    add(sbar, BorderLayout.PAGE_END);
  }

  private void createMenu() {
    var mbar = new JMenuBar();

    var menu = new JMenu("File");
    menu.setMnemonic(KeyEvent.VK_A);
    var item = new JMenuItem("Quit");
    item.setMnemonic(KeyEvent.VK_Q);
    item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
    item.addActionListener((ev) -> this.dispose());
    menu.add(item);
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
