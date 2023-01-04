package com.erdrutsch.slopecalc.command;

import com.erdrutsch.slopecalc.Controller;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import org.kordamp.ikonli.swing.FontIcon;

public abstract class AbstractCommand extends AbstractAction implements Command {
  protected final Controller c;
  protected String name;

  public AbstractCommand(
      Controller c,
      String text,
      FontIcon icon,
      int mnemonic,
      KeyStroke accelerator,
      String tooltip) {
    putValue(ACCELERATOR_KEY, accelerator);
    putValue(MNEMONIC_KEY, mnemonic);
    putValue(NAME, text);
    putValue(SMALL_ICON, icon);
    putValue(SHORT_DESCRIPTION, tooltip);
    setName(text);
    this.c = c;
  }

  public void setName(String text) {
    name = text.toLowerCase();
  }

  public String getName() {
    return name;
  }

  public boolean hasIcon() {
    return getValue(SMALL_ICON) != null || getValue(LARGE_ICON_KEY) != null;
  }

  public void kill() {}

  @Override
  public void actionPerformed(ActionEvent e) {
    c.getTerminal().execute(name);
  }
}
