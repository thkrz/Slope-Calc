package com.erdrutsch.slopecalc;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.KeyboardFocusManager;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class App extends JFrame {
  private final Controller c = new Controller();

  App() {
    super("Slope-Calc");
    Path.init(c);
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    createComponents();
    createNav();
    KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .addKeyEventDispatcher(
            e -> {
              if (e.isControlDown()) {
                switch (e.getKeyChar()) {
                  case 27: // [
                  case 28: // \
                    c.getTerminal().execute("Ctrl+C");
                    break;
                  case '9':
                    c.getTerminal().focusInput();
                    break;
                }
              }
              return false;
            });
  }

  @Override
  public void setVisible(boolean b) {
    super.setVisible(b);
    c.getTerminal().focusInput();
  }

  private void createComponents() {
    var pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, c.getDesktopPane(), c.getTerminal());
    pane.setResizeWeight(1.0);
    add(pane, BorderLayout.CENTER);
    add(c.getStatusbar(), BorderLayout.PAGE_END);
  }

  private void createNav() {
    var mbar = new JMenuBar();
    var tbar = new JPanel();
    tbar.setLayout(new FlowLayout(FlowLayout.LEADING));
    Path.commands.forEach(
        (name, list) -> {
          var m = new JMenu(name);
          m.setMnemonic(name.getBytes()[0]);
          var tb = new JToolBar(name);
          tb.setFloatable(true);
          list.forEach(
              a -> {
                if (a == null) {
                  m.addSeparator();
                  tb.addSeparator();
                } else {
                  m.add(a);
                  if (a.hasIcon()) tb.add(a);
                }
              });
          mbar.add(m);
          // clean toolbar
          int twice = 0;
          var components = tb.getComponents();
          for (int i = 0; i < components.length; i++) {
            var child = components[i];
            if (child instanceof JToolBar.Separator) twice++;
            if (twice == 2 || (twice == 1 && i == components.length - 1)) {
              tb.remove(child);
              twice = 0;
            }
          }
          tbar.add(tb);
        });
    setJMenuBar(mbar);
    add(tbar, BorderLayout.PAGE_START);
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
