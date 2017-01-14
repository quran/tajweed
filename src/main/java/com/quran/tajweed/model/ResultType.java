package com.quran.tajweed.model;

public enum ResultType {
  GHUNNA("Ghunna", "#43A047"),
  IDGHAM_NOT_PRONOUNCED("Idgham, not pronounced", "#EEEEEE"),     // gray
  IDGHAM_WITH_GHUNNA("Idgham with Ghunna", "#43A047"),            // light green
  IDGHAM_WITHOUT_GHUNNA("Idgham without Ghunna", "#EEEEEE"),      // gray
  IQLAB_NOT_PRONOUNCED("Iqlab, not pronounced", "#EEEEEE"),       // gray
  IQLAB("Iqlab", "#43A047"),                                      // light green
  QALQALAH("Qalqalah", "#0091EA"),                                // light blue
  MEEM_IDGHAM("Meem Idgham", "#43A047"),                          // light green
  MEEM_IKHFA("Meem Ikhfa", "#EACE00"),                            // yellow
  IKHFA("Ikhfa", "#EACE00");                                      // yellow

  public final String color;
  public final String debugName;

  ResultType(String debugName, String color) {
    this.debugName = debugName;
    this.color = color;
  }
}
