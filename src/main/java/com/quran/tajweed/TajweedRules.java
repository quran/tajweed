package com.quran.tajweed;

import com.quran.tajweed.rule.IdghamRule;
import com.quran.tajweed.rule.IkhfaRule;
import com.quran.tajweed.rule.IqlabRule;
import com.quran.tajweed.rule.QalqalahRule;
import com.quran.tajweed.rule.Rule;

public class TajweedRules {
  private static final Rule[] TAJWEED_RULES = new Rule[] {
      new QalqalahRule(), new IdghamRule(), new IqlabRule(), new IkhfaRule()};

  public static void main (String args[]) {
    String[] text = new String[] {
      "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ",
      "ذَٰلِكَ الْكِتَابُ لَا رَيْبَ ۛ فِيهِ ۛ هُدًى لِّلْمُتَّقِينَ",
      "تَبَّتْ يَدَا أَبِي لَهَبٍ وَتَبَّ",
      "ثُمَّ بَعَثْنَاكُم مِّن بَعْدِ مَوْتِكُمْ لَعَلَّكُمْ تَشْكُرُونَ",
    };

    for (String ayahText : text) {
      System.out.println("Considering: " + ayahText);
      for (Rule rule : TAJWEED_RULES) {
        rule.checkAyah(ayahText);
      }
    }
  }
}
