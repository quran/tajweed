package com.quran.tajweed;

import com.quran.tajweed.exporter.Exporter;
import com.quran.tajweed.exporter.HtmlExporter;
import com.quran.tajweed.model.Result;
import com.quran.tajweed.model.ResultUtil;
import com.quran.tajweed.model.TajweedRule;

import java.util.ArrayList;
import java.util.List;

public class TajweedRules {

  public static void main(String args[]) {
    String[] text = new String[]{
        "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ",
        "ذَٰلِكَ الْكِتَابُ لَا رَيْبَ ۛ فِيهِ ۛ هُدًى لِّلْمُتَّقِينَ",
        "تَبَّتْ يَدَا أَبِي لَهَبٍ وَتَبَّ",
        "ثُمَّ بَعَثْنَاكُم مِّن بَعْدِ مَوْتِكُمْ لَعَلَّكُمْ تَشْكُرُونَ",
        "تَرْمِيهِم بِحِجَارَةٍ مِّن سِجِّيلٍ",
    };

    Exporter exporter = new HtmlExporter();
    exporter.onOutputStarted();

    List<TajweedRule> rules = TajweedRule.RULES;
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
