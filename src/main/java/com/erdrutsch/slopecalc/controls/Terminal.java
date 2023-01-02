package com.erdrutsch.slopecalc.controls;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Terminal extends JPanel {
  private final JTextArea stdout = new JTextArea();
  private final JTextField stdin = new JTextField();
  private final JLabel prompt = new JLabel();

  public Terminal() {
    createComponents();
    setPrompt();
  }

  public void print(String s) {
    this.print(s, "\n");
  }

  public void print(String s, String end) {
    stdout.append(s + end);
  }

  public String read() {
    return stdin.getText();
  }

  public void setPrompt() {
    this.setPrompt("Command");
  }

  public void setPrompt(String ps) {
    prompt.setText(ps + ": ");
  }

  private void createComponents() {
    stdout.setLineWrap(true);
    stdout.setEditable(false);
    stdout.setBackground(stdin.getBackground());
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
