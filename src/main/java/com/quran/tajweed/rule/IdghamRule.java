package com.quran.tajweed.rule;

import com.quran.tajweed.CharacterUtil;

/**
 * Idgham Rule
 * The letters of idgham are يرملون. Idgham occurs when a ن (noon sakina) or tanween are followed by
 * one of the aforementioned 6 letters. There are two of idgham.
 * Idgham with ghunna: when ن or tanween are followed by one of ينمو.
 * Igham without ghunna: when ن or tanween are followed by ل or ر
 */
public class IdghamRule implements Rule {
  private static final Character YA = 0x0649; // should this be 0x064a?
  private static final Character RA = 0x0631;
  private static final Character MEEM = 0x0645;
  private static final Character LAM = 0x0644;
  private static final Character WAW = 0x0648;
  private static final Character NOON = 0x0646;

  private static final Character[] IDGHAM_WITH_GUNNAH = new Character[] { YA, NOON, MEEM, WAW };
  private static final Character[] IDGHAM_WITHOUT_GUNNAH = new Character[] { LAM, RA };

  @Override
  public void checkAyah(String ayah) {
    checkIdghamWithGhunna(ayah);
    checkIdghamWithoutGhunna(ayah);
  }

  private void checkIdghamWithGhunna(String ayah) {
    System.out.println("checking idgham with ghunnah...");
    checkIdghamHelper(ayah, IDGHAM_WITH_GUNNAH);
  }

  private void checkIdghamWithoutGhunna(String ayah) {
    System.out.println("checking idgham without ghunnah...");
    checkIdghamHelper(ayah, IDGHAM_WITHOUT_GUNNAH);
  }

  private void checkIdghamHelper(String ayah, Character[] prefixCharacters) {
    for (Character potentialPostfix : prefixCharacters) {
      int index = -1;
      while ((index = (ayah.indexOf(potentialPostfix, index + 1))) > -1) {
        int previous = ayah.codePointBefore(index);

        int lastIndex = index;
        while (Character.isSpaceChar(previous) && previous > 0) {
          previous = ayah.codePointBefore(--lastIndex);
        }

        if (previous == CharacterUtil.FATHA_TANWEEN ||
            previous == CharacterUtil.DAMMA_TANWEEN ||
            previous == CharacterUtil.KASRA_TANWEEN ||
            previous == NOON) {
          System.out.println("match at: " + index + ", letter: " + potentialPostfix);
        }
      }
    }
  }
}
