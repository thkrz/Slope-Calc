package com.erdrutsch.slopecalc.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;


public class Canvas extends JPanel
    implements MouseListener, MouseMotionListener, MouseWheelListener {

  public Canvas() {
    setLayout(null);
    setBackground(Color.BLACK);
    setPreferredSize(new Dimension(1000, 1000));
    addMouseListener(this);
    addMouseMotionListener(this);
    addMouseWheelListener(this);
  }

  @Override
  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(getBackground());
    g2.fillRect(0, 0, getWidth(), getHeight());
  }

  @Override
  public void mouseClicked(MouseEvent e) {}

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
}
