package com.erdrutsch.slopecalc.command;

import com.erdrutsch.slopecalc.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public class QuitCommand extends AbstractCommand {

  public QuitCommand(Controller c) {
    super(
        c,
        "Quit",
        null,
        KeyEvent.VK_Q,
        KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK),
        "Quit");
  }

  @Override
  public Result run(String line) {
    System.exit(0);
    return new Result(Result.SUCCESS, "not reached");
  }
}
