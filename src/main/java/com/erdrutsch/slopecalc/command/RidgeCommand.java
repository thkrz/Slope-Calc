package com.erdrutsch.slopecalc.command;

import com.erdrutsch.slopecalc.Controller;

public class RidgeCommand extends AbstractCommand {
  private final String[] prompts = {"Enter coordinates"};
  private int pos = 0;

  public RidgeCommand(Controller c) {
    super(c, "Define ridge...", null, 0, null, "Define a slope ridge");
    setName("ridge");
  }

  @Override
  public Result run(String line) {
    if (line.isEmpty()) {
      // finish collected data
      c.getWindow().getCanvas().closeTerminal();
      return new Result(Result.SUCCESS, "ridge added");
    }
    c.getTerminal().setPrompt(prompts[pos++] + "[Empty to quit]");
    if (pos == prompts.length) pos = 0;
    if (line.toLowerCase().equals(getName()))
      c.getWindow().getCanvas().sendToTerminal(c.getTerminal());
    else c.getTerminal().print("I parse now:" + line);
    return new Result(Result.ONGOING, null);
  }

  @Override
  public void kill() {
    c.getTerminal().print("killed");
    // clean up
  }
}
