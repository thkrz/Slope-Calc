package com.erdrutsch.slopecalc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// import org.locationtech.jts.geom.Polygon;

public class Model implements Serializable {
  public static final String DEFAULT_NAME = "Unknown.sdc";
  private String name = DEFAULT_NAME;

  public static Model load(String name)
      throws ClassNotFoundException, FileNotFoundException, IOException {
    var in = new ObjectInputStream(new FileInputStream(name));
    var model = (Model) in.readObject();
    in.close();
    model.setName(name);
    return model;
  }

  public void save() throws FileNotFoundException, IOException {
    this.save(name);
  }

  public void save(String name) throws FileNotFoundException, IOException {
    var out = new ObjectOutputStream(new FileOutputStream(name));
    out.writeObject(this);
    out.flush();
    out.close();
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
