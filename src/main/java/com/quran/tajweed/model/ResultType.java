package com.quran.tajweed.model;

public enum ResultType {
  GHUNNA("Ghunna", 0x43A047),
  IDGHAM_NOT_PRONOUNCED("Idgham, not pronounced", 0xEEEEEE),
  IDGHAM_WITH_GHUNNA("Idgham with Ghunna", 0x43A047),
  IDGHAM_WITHOUT_GHUNNA("Idgham without Ghunna", 0xEEEEEE),
  IQLAB_NOT_PRONOUNCED("Iqlab, not pronounced", 0xEEEEEE),
  IQLAB("Iqlab", 0x43A047),
  QALQALAH("Qalqalah", 0x0091EA);

  public int color;
  public String debugName;

  ResultType(String debugName, int color) {
    this.debugName = debugName;
    this.color = color;
  }
}
