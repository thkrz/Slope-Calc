package com.erdrutsch.slopecalc.controls;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.erdrutsch.slopecalc.command.Command;
import com.erdrutsch.slopecalc.command.Result;

public class Terminal extends JPanel {
  private final JTextArea stdout = new JTextArea();
  private final JTextField stdin = new JTextField();
  private final JLabel prompt = new JLabel();
  private boolean echo = true;
  private Command cmd = null;
  private int ln = 0;

  public Terminal() {
    createComponents();
    setPrompt();
    stdin.addActionListener(this::submit);
  }

  public void disableEcho(boolean b) {
    echo = !b;
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

  public void setPrompt(String s) {
    prompt.setText(s + ": ");
  }

  public void submit(ActionEvent e) {
    if (echo) print(String.format("[%d]: %s", ++ln, read()));
    if (cmd == null) // TODO: find cmd
      ;
    if (cmd == null) {
      print("command not found");
    } else {
      var r = cmd.run(read());
      print(r.getMessage());
      if (r.getExitCode() != Result.ONGOING) cmd = null;
    }
    stdin.setText(null);
  }

  private void createComponents() {
    stdout.setBackground(stdin.getBackground());
    stdout.setEditable(false);
    stdout.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
    stdout.setLineWrap(true);
    stdout.setRows(3);
    setLayout(new BorderLayout());
    add(
        new JScrollPane(
            stdout, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
        BorderLayout.CENTER);
    var panel = new JPanel();
    panel.setBorder(stdin.getBorder());
    panel.setLayout(new BorderLayout());
    prompt.setBackground(stdin.getBackground());
    prompt.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
    prompt.setOpaque(true);
    panel.add(prompt, BorderLayout.LINE_START);
    stdin.setBorder(null);
    stdin.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
    panel.add(stdin, BorderLayout.CENTER);
    add(panel, BorderLayout.PAGE_END);
  }
}
