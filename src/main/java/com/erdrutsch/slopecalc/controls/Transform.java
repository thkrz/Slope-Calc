package com.erdrutsch.slopecalc.controls;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

public class Transform extends AffineTransform {
  private final float[] p = new float[2];
  private final float[] q = new float[2];
  private AffineTransform inv;

  public void setTransform(double s, double x, double y, double z) {
    setTransform(s, 0, 0, -s * z, x, y);
    try {
      inv = createInverse();
    } catch (NoninvertibleTransformException e) {
    }
  }

  public float[] inverseTransform(int x, int y) {
    p[0] = (float) x;
    p[1] = (float) y;
    inv.transform(p, 0, q, 0, 1);
    return q;
  }
}
