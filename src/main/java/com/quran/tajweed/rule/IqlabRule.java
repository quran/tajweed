package com.quran.tajweed.rule;

import com.quran.tajweed.CharacterUtil;

/**
 * Iqlab Rule
 * Iqlab occurs when a ن (noon sakina) is followed by a ب.
 */
public class IqlabRule implements Rule {

  @Override
  public void checkAyah(String ayah) {
    System.out.println("checking iqlab...");
    int ayahLength = ayah.length();

    int index = -1;
    while ((index = (ayah.indexOf(CharacterUtil.NOON, index + 1))) > -1) {
      int nextIndex = index + Character.charCount(ayah.codePointAt(index));
      if (nextIndex < ayahLength) {
        int next = ayah.codePointAt(nextIndex);
        if (Character.isSpaceChar(next)) {
          if (++nextIndex < ayahLength) {
            next = ayah.codePointAt(nextIndex);
          } else {
            break;
          }
        }

        if (next == CharacterUtil.BA) {
          System.out.println("match at " + index);
        }
      }
    }
  }
}
