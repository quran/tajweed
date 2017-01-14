package com.quran.tajweed.rule;

import com.quran.tajweed.model.Result;
import com.quran.tajweed.model.ResultType;
import com.quran.tajweed.util.CharacterUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Ikhfa Rule
 * Ikhafa occurs when a ن (noon sakina) or tanween are followed by any letter, except for:
 * - the letters of idgham (يرملون)
 * - the letter of iqlab (ب)
 * - the letters of izhar ء ه ع ح غ خ
 */
public class IkhfaRule implements Rule {

  private static final Character TA = 0x062a;
  private static final Character THAA = 0x062b;
  private static final Character JEEM = CharacterUtil.JEEM;
  private static final Character DAAL = CharacterUtil.DAAL;
  private static final Character ZAAL = 0x0630;
  private static final Character ZA = 0x0632;
  private static final Character SEEN = 0x0633;
  private static final Character SHEEN = 0x0634;
  private static final Character SAAD = 0x0635;
  private static final Character DAAD = 0x0636;
  private static final Character TAA = CharacterUtil.TAA;
  private static final Character ZAA = 0x0638;
  private static final Character FAA = 0x0641;
  private static final Character QAAF = CharacterUtil.QAAF;
  private static final Character KAAF = 0x0643;
  private static final Character[] LETTERS_OF_IKHFA = { TA, THAA, JEEM, DAAL, ZAAL,
      ZA, SEEN, SHEEN, SAAD, DAAD, TAA, ZAA, FAA, QAAF, KAAF };


  @Override
  public List<Result> checkAyah(String ayah) {
    List<Result> results = new ArrayList<>();
    int length = ayah.length();
    int startPos = 0, endPos = 0;
    for (int i = 0; i < length; i++) {
      int[] next = CharacterUtil.getNextChars(ayah, i);
      int[] previous = CharacterUtil.getPreviousChars(ayah, i);
      if (CharacterUtil.isTanween(next[0]) || CharacterUtil.isNoonSaakin(next)) {
        for (int j = 1; j < next.length && next[j] != 0; j++) {
          // Basically the first instance there is a real letter
          if (CharacterUtil.isLetter(next[j]) &&
              (next[0] != CharacterUtil.FATHA_TANWEEN ||
                  (next[j] != CharacterUtil.ALEF || next[j] != CharacterUtil.ALEF_LAYINA))) {
            if (Arrays.asList(LETTERS_OF_IKHFA).contains(ayah.charAt(i + j))) {
              startPos = i;
              endPos = i + CharacterUtil.findRemainingMarks(next);
              if (CharacterUtil.NASKHSTYLE) {
                startPos = i - CharacterUtil.findPreviousLetterPronounced(previous);
                endPos = i + j + CharacterUtil
                    .findRemainingMarks(Arrays.copyOfRange(next, j, next.length));
              }
              results.add(new Result(ResultType.IKHFA, startPos, endPos));
            } else { //If the first real letter was not of Ikhfa
              break;
            }
          }
        }
      }
    }
    return results;
  }
}
