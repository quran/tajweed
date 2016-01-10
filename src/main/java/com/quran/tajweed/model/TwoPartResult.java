package com.quran.tajweed.model;

public class TwoPartResult extends Result {
  public final int secondStart;
  public final int secondEnding;
  public final ResultType secondResultType;

  public TwoPartResult(ResultType resultType, int start, int ending,
      ResultType secondResultType, int secondStart, int secondEnding) {
    super(resultType, start, ending);
    this.secondStart = secondStart;
    this.secondEnding = secondEnding;
    this.secondResultType = secondResultType;
  }
}
