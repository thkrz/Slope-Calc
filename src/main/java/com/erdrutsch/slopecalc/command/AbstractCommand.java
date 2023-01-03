package com.erdrutsch.slopecalc.command;

import com.erdrutsch.slopecalc.Controller;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import org.kordamp.ikonli.swing.FontIcon;

public abstract class AbstractCommand extends AbstractAction implements Command {
  protected Controller c;
  protected final String name;

  public AbstractCommand(
      Controller c, String text, FontIcon icon, int mnemonic, KeyStroke accelerator, String tooltip) {
    name = text.toLowerCase();
    putValue(ACCELERATOR_KEY, accelerator);
    putValue(MNEMONIC_KEY, mnemonic);
    putValue(NAME, text);
    putValue(SMALL_ICON, icon);
    putValue(SHORT_DESCRIPTION, tooltip);
    this.c = c;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    c.getTerminal().execute(name);
  }
}
