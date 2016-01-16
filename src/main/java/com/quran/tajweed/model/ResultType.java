package com.quran.tajweed.model;

public enum ResultType {
  GHUNNA("Ghunna", 0x43A047),
  IDGHAM_NOT_PRONOUNCED("Idgham, not pronounced", 0xEEEEEE),
  IDGHAM_WITH_GHUNNA("Idgham with Ghunna", 0x43A047),
  IDGHAM_WITHOUT_GHUNNA("Idgham without Ghunna", 0xEEEEEE),
  IQLAB_NOT_PRONOUNCED("Iqlab, not pronounced", 0xEEEEEE),
  IQLAB("Iqlab", 0x43A047),
  QALQALAH("Qalqalah", 0x0091EA),
  MEEM_IDGHAM("Meem Idgham", 0x43A047),
  MEEM_IKHFA("Meem Ikhfa", 0xEACE00),
  IKHFA("Ikhfa", 0xEACE00);

  public int color;
  public String debugName;

  ResultType(String debugName, int color) {
    this.debugName = debugName;
    this.color = color;
  }
}
