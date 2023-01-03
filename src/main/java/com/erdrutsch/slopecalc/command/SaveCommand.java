package com.erdrutsch.slopecalc.command;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.kordamp.ikonli.fluentui.FluentUiRegularMZ;
import org.kordamp.ikonli.swing.FontIcon;

import com.erdrutsch.slopecalc.Controller;

public class SaveCommand extends AbstractAction implements Command {
  private Controller c;

  public SaveCommand(Controller c) {
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    putValue(MNEMONIC_KEY, KeyEvent.VK_S);
    putValue(NAME, "Save");
    putValue(SMALL_ICON, FontIcon.of(FluentUiRegularMZ.SAVE_20, 20));
    putValue(SHORT_DESCRIPTION, "Saves the current model");
    this.c = c;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    run(null);
  }

  @Override
  public Result run(String line) {
    try {
      c.getWindow().getModel().save();
    } catch (IOException e) {
      return new Result(Result.FAILURE, e.toString());
    }
    return new Result(Result.SUCCESS, String.format("'%s' saved", "Name"));
  }
}
