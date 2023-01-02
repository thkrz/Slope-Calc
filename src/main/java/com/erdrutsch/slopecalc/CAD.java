package com.erdrutsch.slopecalc;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

public class CAD {
  private Polygon boundary;

  public void setBoundary(double[] x, double[] y) {
    var coords = new Coordinate[x.length];
    for (int i = 0; i < coords.length; i++) {
      coords[i] = new Coordinate(x[i], y[i]);
    }
    boundary = new Polygon(new LinearRing(new CoordinateArraySequence(coords), new GeometryFactory()), null, new GeometryFactory());
  }
}
