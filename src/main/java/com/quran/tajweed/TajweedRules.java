package com.quran.tajweed;

import com.quran.tajweed.exporter.Exporter;
import com.quran.tajweed.exporter.TextExporter;
import com.quran.tajweed.model.Result;
import com.quran.tajweed.model.ResultUtil;
import com.quran.tajweed.model.TajweedRule;

import java.util.ArrayList;
import java.util.List;

public class TajweedRules {

  public static void main(String args[]) {

    // text uses uthmani text from tanzil
    String[] text = new String[]{
        "ٱلْحَمْدُ لِلَّـهِ رَبِّ ٱلْعَـٰلَمِينَ",  // 1:2
        "ذَٰلِكَ ٱلْكِتَـٰبُ لَا رَيْبَ ۛ فِيهِ ۛ هُدًى لِّلْمُتَّقِينَ",  // 2:2
        "ثُمَّ بَعَثْنَـٰكُم مِّنۢ بَعْدِ مَوْتِكُمْ لَعَلَّكُمْ تَشْكُرُونَ",  // 2:56
        "تَرْمِيهِم بِحِجَارَةٍ مِّن سِجِّيلٍ",  // 105:4
        "تَبَّتْ يَدَآ أَبِى لَهَبٍ وَتَبَّ",  // 111:1
    };

    Exporter exporter = new TextExporter();
    exporter.onOutputStarted();

    List<TajweedRule> rules = TajweedRule.MADANI_RULES;
    for (String ayahText : text) {
      List<Result> results = new ArrayList<>();
      for (TajweedRule tajweedRule : rules) {
        results.addAll(tajweedRule.rule.checkAyah(ayahText));
      }
      ResultUtil.INSTANCE.sort(results);
      exporter.export(ayahText, results);
    }
    exporter.onOutputCompleted();
  }
}
