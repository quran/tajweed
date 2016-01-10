package com.quran.tajweed.model;

public enum ResultType {
  GHUNNA("Ghunna"),
  IDGHAM_NOT_PRONOUNCED("Idgham, not pronounced"),
  IDGHAM_WITH_GHUNNA("Idgham with Ghunna"),
  IDGHAM_WITHOUT_GHUNNA("Idgham without Ghunna"),
  IQLAB_NOT_PRONOUNCED("Iqlab, not pronounced"),
  IQLAB("Iqlab"),
  QALQALAH("Qalqalah");

  public String debugName;

  ResultType(String debugName) {
    this.debugName = debugName;
  }
}
