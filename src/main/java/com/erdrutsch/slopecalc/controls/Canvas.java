package com.erdrutsch.slopecalc.controls;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

public class Canvas extends JPanel
    implements MouseListener, MouseMotionListener, MouseWheelListener {
  // TODO: clipping ?
  private final int margin = 75;
  private final Stroke dotted =
      new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {1, 2}, 0);
  private final Dimension size = new Dimension(3000, 3000);
  private final AffineTransform xform = new AffineTransform();
  private Terminal term;
  private double scale;

  public Canvas() {
    setLayout(null);
    setBackground(Color.BLACK);
    setPreferredSize(size);
    setCursor();
    addMouseListener(this);
    addMouseMotionListener(this);
    addMouseWheelListener(this);
    closeTerminal();
    scale = 1.0;
  }

  public void sendToTerminal(Terminal term) {
    this.term = term;
  }

  public void closeTerminal() {
    term = null;
  }

  @Override
  public void paint(Graphics g) {
    var g2 = (Graphics2D) g.create();
    g2.setColor(getBackground());
    g2.fillRect(0, 0, getWidth(), getHeight());
    drawGrid(g2);
    xform.setTransform(scale, 0, 0, -scale, margin, getHeight() - margin);
    g2.setTransform(xform);
    // draw
    g2.setColor(Color.RED);
    g2.drawLine(0, 0, 128, 0);
    g2.drawLine(0, 0, 0, 128);

    g2.dispose();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (term != null)
      switch (e.getButton()) {
        case MouseEvent.BUTTON1:
          term.execute(String.format("%d, %d", e.getX(), e.getY()));
          break;
        case MouseEvent.BUTTON3:
          term.execute("");
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
  public void mouseMoved(MouseEvent e) {}

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    scale = Math.min(4.0, Math.max(0.1, e.getWheelRotation() > 0 ? scale * 1.2 : scale / 1.2));
    setPreferredSize(
        new Dimension((int) Math.round(size.width * scale), (int) Math.round(size.height * scale)));
    revalidate();
  }

  private void setCursor() {
    var tk = Toolkit.getDefaultToolkit();
    var im = tk.getImage(getClass().getResource("cross_hair.png"));
    super.setCursor(tk.createCustomCursor(im, new Point(64, 64), "cross_hair"));
  }

  private void drawGrid(Graphics2D g) {
    g.setColor(Color.GRAY);
    var stroke = g.getStroke();
    g.setStroke(dotted);
    for (int i = 50; i < getWidth(); i += 100)
      for (int j = 50; j < getHeight(); j += 100) {
        g.drawLine(i - 8, j, i + 8, j);
        g.drawLine(i, j - 8, i, j + 8);
      }
    g.setStroke(stroke);
  }
}
