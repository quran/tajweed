package com.quran.tajweed.rule;

import com.quran.tajweed.util.CharacterUtil;
import com.quran.tajweed.util.CharacterInfo;

/**
 * Idgham Rule
 * The letters of idgham are يرملون. Idgham occurs when a ن (noon sakina) or tanween are followed by
 * one of the aforementioned 6 letters. There are two of idgham.
 * Idgham with ghunna: when ن or tanween are followed by one of ينمو.
 * Igham without ghunna: when ن or tanween are followed by ل or ر
 */
public class IdghamRule implements Rule {
  // with ghunna
  private static final Character YA = 0x064a;
  private static final Character MEEM = CharacterUtil.MEEM;
  private static final Character WAW = 0x0648;
  private static final Character NOON = CharacterUtil.NOON;

  // without ghunna
  private static final Character RA = 0x0631;
  private static final Character LAM = 0x0644;

  @Override
  public void checkAyah(String ayah) {
    System.out.println("checking idgham...");

    int[] characters = ayah.codePoints().toArray();
    for (int i = 0; i < characters.length; i++) {
      int current = characters[i];

      if (current == RA || current == LAM ||
          current == YA || current == NOON || current == MEEM || current == WAW) {
        if (isValidIdgham(ayah, i)) {
          String type = (current != LAM && current != RA) ? "with ghunna" : "without ghunna";
          System.out.println("match idgham " + type + " at: " + i + ", letter: " + (char) current);
        }
      }
    }
  }

  private boolean isValidIdgham(String ayah, int index) {
    CharacterInfo previousCharacter = CharacterUtil.getPreviousCharacter(ayah, index);

    boolean result = false;
    int previous = previousCharacter.character;
    if (previous == CharacterUtil.FATHA_TANWEEN ||
        previous == CharacterUtil.DAMMA_TANWEEN ||
        previous == CharacterUtil.KASRA_TANWEEN ||
        previous == NOON) {
      result = true;
    } else if (previousCharacter.index > 0 &&
        (previous == CharacterUtil.ALEF_LAYINA || previous == CharacterUtil.ALEF)) {
      // if the previous character is either alif layina or alif, check to see if we have
      // tanween with fatha on the previous letter.
      previous = ayah.codePointBefore(previousCharacter.index);
      result = previous == CharacterUtil.FATHA_TANWEEN;
    }
    return result;
  }
}
