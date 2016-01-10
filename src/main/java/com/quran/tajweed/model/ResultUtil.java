package com.quran.tajweed.model;

import java.util.List;

public class ResultUtil {

  public static final ResultUtil INSTANCE = new ResultUtil();

  private ResultUtil() {
  }

  /**
   * Sort the list according to the position in the ayah each result occurs.
   * @param results the sorted results
   */
  public void sort(List<Result> results) {
    // note that this Comparator imposes an order inconsistent with equals
    results.sort((o1, o2) -> o1.getMinimumStartingPosition() - o2.getMinimumStartingPosition());
  }
}
