package com.erdrutsch.slopecalc;

import com.erdrutsch.slopecalc.controls.Statusbar;
import com.erdrutsch.slopecalc.controls.Terminal;
import com.erdrutsch.slopecalc.controls.Window;
import javax.swing.JDesktopPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class Controller implements InternalFrameListener {
  private final JDesktopPane mdi = new JDesktopPane();
  private final Terminal term = new Terminal();
  private final Statusbar sbar = new Statusbar();
  private Window wnd;

  public Controller() {
    wnd = new Window();
    addWindow(wnd);
  }

  public void addWindow(Window w) {
    w.addInternalFrameListener(this);
    mdi.add(w);
  }

  public JDesktopPane getDesktopPane() {
    return mdi;
  }

  public Statusbar getStatusbar() {
    return sbar;
  }

  public Terminal getTerminal() {
    return term;
  }

  public Window getWindow() {
    return wnd;
  }

  public void internalFrameClosing(InternalFrameEvent e) {}

  public void internalFrameClosed(InternalFrameEvent e) {}

  public void internalFrameOpened(InternalFrameEvent e) {}

  public void internalFrameIconified(InternalFrameEvent e) {}

  public void internalFrameDeiconified(InternalFrameEvent e) {}

  public void internalFrameActivated(InternalFrameEvent e) {
    wnd = (Window) e.getSource();
  }

  public void internalFrameDeactivated(InternalFrameEvent e) {}
}
