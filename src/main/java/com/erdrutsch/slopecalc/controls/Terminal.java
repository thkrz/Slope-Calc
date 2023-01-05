package com.erdrutsch.slopecalc.controls;

import com.erdrutsch.slopecalc.Path;
import com.erdrutsch.slopecalc.command.Command;
import com.erdrutsch.slopecalc.command.CommandNotFoundException;
import com.erdrutsch.slopecalc.command.Result;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Terminal extends JPanel {
  private final JTextArea stdout = new JTextArea();
  private final JTextField stdin = new JTextField();
  private final JLabel prompt = new JLabel();
  private Command cmd = null;
  private boolean echo = true;
  private int ln = 0;

  public Terminal() {
    createComponents();
    setPrompt();
    stdin.addActionListener(this::execute);
  }

  public void disableEcho(boolean b) {
    echo = !b;
  }

  public void print(String s) {
    this.print(s, "\n");
  }

  public void print(String s, String end) {
    if (s == null) return;
    stdout.append(s + end);
  }

  public String read() {
    return stdin.getText();
  }

  public void setPrompt() {
    cmd = null;
    setPrompt("Command");
  }

  public void setPrompt(String s) {
    prompt.setText(s + ": ");
  }

  public void execute() {
    execute("");
  }

  public void execute(String s) {
    s = s.trim();
    if (s.equals("Ctrl+C")) {
      if (cmd != null) cmd.kill();
      setPrompt();
      return;
    }
    if (echo) print(String.format("[%d]: %s", ++ln, s));
    if (cmd == null)
      try {
        cmd = Path.search(s);
      } catch (CommandNotFoundException e) {
        print(e.getMessage());
        return;
      }
    var r = cmd.run(s);
    print(r.getMessage());
    if (r.getExitCode() != Result.ONGOING) setPrompt();
  }

  public void execute(ActionEvent e) {
    this.execute(read());
    stdin.setText(null);
  }

  public void focusInput() {
    stdin.requestFocusInWindow();
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
