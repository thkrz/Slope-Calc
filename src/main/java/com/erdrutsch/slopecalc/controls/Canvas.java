package com.erdrutsch.slopecalc.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;

public class Canvas extends JPanel
    implements ComponentListener, MouseListener, MouseMotionListener, MouseWheelListener {
  public enum Mode {
    BOTH,
    X,
    NONE
  }

  private final GridRenderer grid = new GridRenderer(50, 50, 100, 100);
  private final int margin = 75;
  private final Dimension size = new Dimension(1000, 1000);
  private final Transform xform = new Transform();
  private StatusListener l;
  private double scale = 1.0;
  private double z = 1.0;
  private Terminal pipe;
  private Mode mode;

  public Canvas() {
    setLayout(null);
    setBackground(Color.BLACK);
    setDoubleBuffered(true);
    setPreferredSize(size);
    setCursor();
    addComponentListener(this);
    addMouseListener(this);
    addMouseMotionListener(this);
    addMouseWheelListener(this);
    closePipe();
  }

  public void addStatusListener(StatusListener l) {
    this.l = l;
  }

  public void openPipe(Terminal pipe, Mode mode) {
    this.pipe = pipe;
    this.mode = mode;
  }

  public void closePipe() {
    pipe = null;
    mode = Mode.NONE;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    var g2 = (Graphics2D) g.create();
    g2.setColor(getBackground());
    g2.fillRect(0, 0, getWidth(), getHeight());
    grid.render(g2, getWidth(), getHeight());
    g2.setTransform(xform);
    // draw
    g2.setColor(Color.RED);
    g2.drawLine(0, 0, 200, 200);

    g2.dispose();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (mode == Mode.NONE) return;
    switch (e.getButton()) {
      case MouseEvent.BUTTON1:
        var p = xform.inverseTransform(e.getX(), e.getY());
        if (mode == Mode.BOTH) pipe.execute(String.format("%.0f, %.0f", p[0], p[1]));
        else if (mode == Mode.X) pipe.execute(String.format("%.0f", p[0]));
        break;
      case MouseEvent.BUTTON3:
        pipe.execute();
        break;
    }
  }

  @Override
  public void mouseDragged(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mouseMoved(MouseEvent e) {
    var p = xform.inverseTransform(e.getX(), e.getY());
    l.setStatus(p[0], p[1]);
  }

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    scale = Math.min(4.0, Math.max(0.1, e.getWheelRotation() > 0 ? scale * 1.1 : scale / 1.1));
    setPreferredSize(
        new Dimension((int) (size.width * scale), (int) (size.height * scale)));
    revalidate();
  }

  @Override
  public void componentHidden(ComponentEvent e) {}

  @Override
  public void componentMoved(ComponentEvent e) {}

  @Override
  public void componentResized(ComponentEvent e) {
    xform.setTransform(scale, margin, getHeight() - margin, z);
    repaint();
  }

  @Override
  public void componentShown(ComponentEvent e) {}

  private void setCursor() {
    var tk = Toolkit.getDefaultToolkit();
    var im = tk.getImage(getClass().getResource("cross_hair.png"));
    super.setCursor(tk.createCustomCursor(im, new Point(64, 64), "cross_hair"));
  }
}
