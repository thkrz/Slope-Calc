package com.erdrutsch.slopecalc.command;

import com.erdrutsch.slopecalc.Controller;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class SaveAsCommand extends AbstractCommand {

  public SaveAsCommand(Controller c) {
    super(c, "Save As...", null, KeyEvent.VK_A, null, "Save the current model");
  }

  @Override
  public Result run(String line) {
    var chooser = new JFileChooser();
    if (chooser.showSaveDialog(SwingUtilities.getWindowAncestor(c.getDesktopPane()))
        != JFileChooser.APPROVE_OPTION) return new Result(Result.FAILURE, null);
    var file = chooser.getSelectedFile();
    var model = c.getWindow().getModel();
    try {
      model.save(file.getPath());
    } catch (IOException e) {
      return new Result(Result.FAILURE, e.toString());
    }
    return new Result(Result.SUCCESS, String.format("'%s' saved", model.getName()));
  }
}
