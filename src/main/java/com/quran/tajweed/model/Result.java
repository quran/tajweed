package com.quran.tajweed.model;

public class Result {
  public final int start;
  public final int ending;
  public final ResultType resultType;

  public Result(ResultType resultType, int start, int ending) {
    this.start = start;
    this.ending = ending;
    this.resultType = resultType;
  }
}
