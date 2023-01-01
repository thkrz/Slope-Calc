package com.erdrutsch.slopecalc.controls;

import java.awt.BorderLayout;
import javax.swing.*;

public class Terminal extends JPanel {
  private final JTextArea stdout;
  private final JTextField stdin;
  private final JLabel prompt;

  public Terminal() {
    stdout = new JTextArea();
    stdin = new JTextField();
    prompt = new JLabel("Command: ");
    createComponents();
  }

  private void createComponents() {
    setLayout(new BorderLayout());
    add(
        new JScrollPane(
            stdout, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
        BorderLayout.CENTER);
    var panel = new JPanel();
    panel.setBorder(stdin.getBorder());
    panel.setLayout(new BorderLayout());
    prompt.setBackground(stdin.getBackground());
    prompt.setOpaque(true);
    panel.add(prompt, BorderLayout.LINE_START);
    stdin.setBorder(null);
    panel.add(stdin, BorderLayout.CENTER);
    add(panel, BorderLayout.PAGE_END);
  }
}
