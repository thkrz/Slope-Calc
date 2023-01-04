package com.erdrutsch.slopecalc.command;

import com.erdrutsch.slopecalc.Controller;
import com.erdrutsch.slopecalc.controls.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import org.kordamp.ikonli.fluentui.FluentUiRegularAL;
import org.kordamp.ikonli.swing.FontIcon;

public class OpenCommand extends AbstractCommand {

  public OpenCommand(Controller c) {
    super(
        c,
        "Open...",
        FontIcon.of(FluentUiRegularAL.FOLDER_OPEN_20, 20),
        KeyEvent.VK_O,
        KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK),
        "Open a new model");
    setName("open");
  }

  @Override
  public Result run(String line) {
    var chooser = new JFileChooser();
    if (chooser.showOpenDialog(SwingUtilities.getWindowAncestor(c.getDesktopPane()))
        != JFileChooser.APPROVE_OPTION) return new Result(Result.FAILURE, null);
    var file = chooser.getSelectedFile();
    try {
      c.addWindow(new Window(file));
    } catch (ClassNotFoundException | IOException e) {
      return new Result(Result.FAILURE, String.format("'%s' failed to open", file.getPath()));
    }
    return new Result(Result.SUCCESS, String.format("'%s' opened", file.getPath()));
  }
}
