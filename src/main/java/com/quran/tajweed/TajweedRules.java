package com.quran.tajweed;

import com.quran.tajweed.rule.IdghamRule;
import com.quran.tajweed.rule.QalqalahRule;
import com.quran.tajweed.rule.Rule;

public class TajweedRules {
  private static final Rule[] TAJWEED_RULES = new Rule[] { new QalqalahRule(), new IdghamRule() };

  public static void main (String args[]) {
    String[] text = new String[] {
      "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ",
      "ذَٰلِكَ الْكِتَابُ لَا رَيْبَ ۛ فِيهِ ۛ هُدًى لِّلْمُتَّقِينَ",
    };

    for (String ayahText : text) {
      System.out.println("Considering: " + ayahText);
      for (Rule rule : TAJWEED_RULES) {
        rule.checkAyah(ayahText);
      }
    }
  }
}
