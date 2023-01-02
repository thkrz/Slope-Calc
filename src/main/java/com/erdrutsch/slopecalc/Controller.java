package com.erdrutsch.slopecalc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import org.kordamp.ikonli.fluentui.FluentUiRegularAL;
import org.kordamp.ikonli.swing.FontIcon;

public class Controller {
  public class Action extends AbstractAction {
    private final ActionListener l;

    public Action(
        String text,
        FontIcon icon,
        boolean small,
        ActionListener l,
        int mnemonic,
        String accelerator,
        String description) {
      putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
      putValue(MNEMONIC_KEY, mnemonic);
      putValue(NAME, text);
      putValue(small ? SMALL_ICON : LARGE_ICON_KEY, icon);
      putValue(SHORT_DESCRIPTION, description);
      this.l = l;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      l.actionPerformed(e);
    }
  }

  public Controller() {
    var file = new ArrayList<Action>();
    file.add(
        new Action(
            "New",
            FontIcon.of(FluentUiRegularAL.DOCUMENT_20, 20),
            true,
            this::newFile,
            KeyEvent.VK_N,
            "Ctrl+N",
            "Create new model"));
  }

  public void newFile(ActionEvent e) {}
}
