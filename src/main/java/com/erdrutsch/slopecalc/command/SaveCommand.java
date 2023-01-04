package com.erdrutsch.slopecalc.command;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.KeyStroke;

import org.kordamp.ikonli.fluentui.FluentUiRegularMZ;
import org.kordamp.ikonli.swing.FontIcon;

import com.erdrutsch.slopecalc.Controller;

public class SaveCommand extends AbstractCommand {

  public SaveCommand(Controller c) {
    super(
        c,
        "Save",
        FontIcon.of(FluentUiRegularMZ.SAVE_20, 20),
        KeyEvent.VK_S,
        KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK),
        "Save the current model");
  }

  @Override
  public Result run(String line) {
    var model = c.getWindow().getModel();
    try {
      model.save();
    } catch (IOException e) {
      return new Result(Result.FAILURE, e.toString());
    }
    return new Result(Result.SUCCESS, String.format("'%s' saved", model.getName()));
  }
}
