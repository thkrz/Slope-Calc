package com.erdrutsch.slopecalc;

import com.erdrutsch.slopecalc.command.AbstractCommand;
import com.erdrutsch.slopecalc.command.CommandNotFoundException;
import com.erdrutsch.slopecalc.command.NewCommand;
import com.erdrutsch.slopecalc.command.OpenCommand;
import com.erdrutsch.slopecalc.command.QuitCommand;
import com.erdrutsch.slopecalc.command.RidgeCommand;
import com.erdrutsch.slopecalc.command.SaveAsCommand;
import com.erdrutsch.slopecalc.command.SaveCommand;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Path {
  public static SortedMap<String, List<AbstractCommand>> commands = new TreeMap<>();

  public static void init(Controller c) {
    commands.put(
        "File",
        Stream.of(
                new NewCommand(c),
                new OpenCommand(c),
                null,
                new SaveCommand(c),
                new SaveAsCommand(c),
                null,
                new QuitCommand(c))
            .toList());
    commands.put("Tools", Stream.of((AbstractCommand) new RidgeCommand(c)).toList());
  }

  public static AbstractCommand search(String name) throws CommandNotFoundException {
    for (var list : commands.values()) {
      var cmd =
          list.stream()
              .filter(c -> c != null && c.getName().equals(name.toLowerCase()))
              .findFirst()
              .orElse(null);
      if (cmd != null) return cmd;
    }
    throw new CommandNotFoundException(name + ": command not found");
  }
}
