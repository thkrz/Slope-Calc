package com.erdrutsch.slopecalc.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;

public class Canvas extends JPanel
    implements MouseListener, MouseMotionListener, MouseWheelListener {
  private Terminal term;

  public Canvas() {
    setLayout(null);
    setBackground(Color.BLACK);
    setPreferredSize(new Dimension(1000, 1000));
    setCursor();
    addMouseListener(this);
    addMouseMotionListener(this);
    addMouseWheelListener(this);
    closeTerminal();
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
  public void mouseWheelMoved(MouseWheelEvent e) {}

  private void setCursor() {
    var tk = Toolkit.getDefaultToolkit();
    var im = tk.getImage(getClass().getResource("cross_hair.png"));
    super.setCursor(tk.createCustomCursor(im, new Point(64, 64), "cross_hair"));
  }

  private void drawGrid(Graphics2D g) {
    g.setColor(Color.GRAY);
    int w = getWidth();
    int h = getHeight();
  }
}
