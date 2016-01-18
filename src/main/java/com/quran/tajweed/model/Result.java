package com.quran.tajweed.model;

public class Result {
  public int start;
  public int ending;
  public final ResultType resultType;

  public Result(ResultType resultType, int start, int ending) {
    this.start = start;
    this.ending = ending;
    this.resultType = resultType;
  }

  public void setMinimumStartingPosition(int newStart) {
    start = newStart;
  }

  public void setMaximumEndingPosition(int newEnd) {
    ending = newEnd;
  }

  public int getMinimumStartingPosition() {
    return start;
  }

  public int getMaximumEndingPosition() {
    return ending;
  }

}
