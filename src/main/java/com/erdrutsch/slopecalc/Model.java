package com.erdrutsch.slopecalc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// import org.locationtech.jts.geom.Polygon;

public class Model implements Serializable {
  private String name;

  public static Model load(String name)
      throws ClassNotFoundException, FileNotFoundException, IOException {
    return Model.load(new File(name));
  }

  public static Model load(File file)
      throws ClassNotFoundException, FileNotFoundException, IOException {
    var in = new ObjectInputStream(new FileInputStream(file));
    var model = (Model) in.readObject();
    in.close();
    model.setName(file.getPath());
    return model;
  }

  public Model(int id) {
    if (id == 0) name = "Unknow.sdc";
    else name = String.format("Unknown (%d).sdc", id);
  }

  public void save() throws FileNotFoundException, IOException {
    this.save(name);
  }

  public void save(String name) throws FileNotFoundException, IOException {
    var out = new ObjectOutputStream(new FileOutputStream(name));
    out.writeObject(this);
    out.flush();
    out.close();
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
