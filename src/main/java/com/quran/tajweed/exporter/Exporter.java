package com.quran.tajweed.exporter;

import com.quran.tajweed.model.Result;

import java.util.List;

public interface Exporter {
  void export(String ayah, List<Result> results);
}
