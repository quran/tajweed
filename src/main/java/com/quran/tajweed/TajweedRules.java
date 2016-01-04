package com.quran.tajweed;

import com.quran.tajweed.rule.QalqalahRule;
import com.quran.tajweed.rule.Rule;

public class TajweedRules {
  private static final Rule[] TAJWEED_RULES = new Rule[] { new QalqalahRule() };

  public static void main (String args[]){
    String arabicText = "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ"; //sample verse
    for (Rule rule : TAJWEED_RULES) {
      rule.checkAyah(arabicText);
    }
  }
}
