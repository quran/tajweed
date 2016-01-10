package com.quran.tajweed.rule;

import com.quran.tajweed.model.Result;

import java.util.List;

public interface Rule {
  List<Result> checkAyah(String ayah);
}
