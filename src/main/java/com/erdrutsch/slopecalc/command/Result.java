package com.erdrutsch.slopecalc.command;

public class Result {
  public static final int SUCCESS = 0;
  public static final int ONGOING = 1;
  public static final int FAILURE = -1;

  private int code;
  private String message;

  public Result(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getExitCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
