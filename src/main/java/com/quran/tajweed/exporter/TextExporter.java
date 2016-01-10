package com.quran.tajweed.exporter;

import com.quran.tajweed.model.Result;

import java.util.List;

public class TextExporter implements Exporter {

  @Override
  public void export(String ayahText, List<Result> results) {
    System.out.println("Considering: " + ayahText);
    for (Result result : results) {
      System.out.println("matched " + result.resultType.debugName +
          " at " + result.start + " to " + result.ending);
    }
  }
}
