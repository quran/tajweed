package com.quran.tajweed.rule;

import com.quran.tajweed.CharacterUtil;

/**
 * Iqlab Rule
 * Iqlab occurs when a ن (noon sakina) or tanween is followed by a ب.
 */
public class IqlabRule implements Rule {

  @Override
  public void checkAyah(String ayah) {
    System.out.println("checking iqlab...");

    int index = -1;
    while ((index = (ayah.indexOf(CharacterUtil.BA, index + 1))) > -1) {
      int previous = ayah.codePointBefore(index);

      int lastIndex = index;
      while (Character.isSpaceChar(previous) && lastIndex > 0) {
        previous = ayah.codePointBefore(lastIndex--);
      }

      if (previous == CharacterUtil.FATHA_TANWEEN ||
          previous == CharacterUtil.DAMMA_TANWEEN ||
          previous == CharacterUtil.KASRA_TANWEEN ||
          previous == CharacterUtil.NOON) {
        System.out.println("match at " + index);
      }
    }
  }
}
