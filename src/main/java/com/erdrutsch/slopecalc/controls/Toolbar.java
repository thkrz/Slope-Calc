package com.erdrutsch.slopecalc.controls;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.kordamp.ikonli.fluentui.FluentUiRegularAL;
import org.kordamp.ikonli.fluentui.FluentUiRegularMZ;
import org.kordamp.ikonli.swing.FontIcon;

public class Toolbar extends JPanel {
  public Toolbar() {
    setLayout(new FlowLayout(FlowLayout.LEADING));
    createComponents();
  }

  private void createComponents() {
    addSubToolbar();
  }

  private void addSubToolbar() {
    var tb = new JToolBar("File");
    tb.setFloatable(true);
    var b = new JButton(FontIcon.of(FluentUiRegularAL.DOCUMENT_20, 20));
    tb.add(b);
    b = new JButton(FontIcon.of(FluentUiRegularAL.FOLDER_OPEN_20, 20));
    tb.add(b);
    b = new JButton(FontIcon.of(FluentUiRegularMZ.SAVE_20, 20));
    tb.add(b);
    tb.addSeparator();
    b = new JButton(FontIcon.of(FluentUiRegularAL.ARROW_UNDO_20, 20));
    tb.add(b);
    b = new JButton(FontIcon.of(FluentUiRegularAL.ARROW_REDO_20, 20));
    tb.add(b);
    add(tb);
  }
}
