package com.quran.tajweed.rule;

import com.quran.tajweed.model.Result;
import com.quran.tajweed.model.ResultType;
import com.quran.tajweed.model.TwoPartResult;
import com.quran.tajweed.util.CharacterInfo;
import com.quran.tajweed.util.CharacterUtil;

import java.util.ArrayList;
import java.util.List;

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
  public List<Result> checkAyah(String ayah) {
    List<Result> results = new ArrayList<>();
    int[] characters = ayah.codePoints().toArray();
    for (int i = 0; i < characters.length; i++) {
      int current = characters[i];

      if (current == RA || current == LAM ||
          current == YA || current == NOON || current == MEEM || current == WAW) {
        int previousMatchPosition = isValidIdgham(ayah, i);
        if (previousMatchPosition >= 0) {
          boolean withGhunna = (current != LAM && current != RA);
          if (withGhunna) {
            results.add(new TwoPartResult(ResultType.IDGHAM_WITH_GHUNNA, i, i + 1,
                ResultType.IDGHAM_NOT_PRONOUNCED, previousMatchPosition,
                previousMatchPosition + 1));
          } else {
            results.add(new Result(ResultType.IDGHAM_WITHOUT_GHUNNA,
                previousMatchPosition, previousMatchPosition + 1));
          }
        }
      }
    }
    return results;
  }

  private int isValidIdgham(String ayah, int index) {
    CharacterInfo previousCharacter = CharacterUtil.getPreviousCharacter(ayah, index);

    boolean result = false;
    int previousIndex = previousCharacter.index;
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
      previousIndex = previousCharacter.index - 1;
    }
    return result ? previousIndex : -1;
  }
}
