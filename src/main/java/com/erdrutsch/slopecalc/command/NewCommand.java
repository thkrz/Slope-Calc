package com.erdrutsch.slopecalc.command;

import com.erdrutsch.slopecalc.Controller;
import com.erdrutsch.slopecalc.controls.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import org.kordamp.ikonli.fluentui.FluentUiRegularAL;
import org.kordamp.ikonli.swing.FontIcon;

public class NewCommand extends AbstractCommand {

  public NewCommand(Controller c) {
    super(
        c,
        "New",
        FontIcon.of(FluentUiRegularAL.DOCUMENT_20, 20),
        KeyEvent.VK_N,
        KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK),
        "Create new model");
  }

  @Override
  public Result run(String line) {
    c.addWindow(new Window());
    return new Result(Result.SUCCESS, String.format("model created", name));
  }
}
