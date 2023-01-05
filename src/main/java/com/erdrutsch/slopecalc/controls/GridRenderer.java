package com.erdrutsch.slopecalc.controls;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class GridRenderer {
  private final int d = 8;
  private final Stroke dotted =
      new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {1, 2}, 0);
  private final int x, y, w, h;

  public GridRenderer(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  public void render(Graphics2D g, int width, int height) {
    g.setColor(Color.GRAY);
    var stroke = g.getStroke();
    g.setStroke(dotted);
    for (int i = x; i < width - d; i += w)
      for (int j = y; j < height - d; j += h) {
        g.drawLine(i - d, j, i + d, j);
        g.drawLine(i, j - d, i, j + d);
      }
    g.setStroke(stroke);
  }
}
