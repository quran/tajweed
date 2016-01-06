package com.quran.tajweed;

import com.quran.tajweed.util.CharacterInfo;

public class CharacterUtil {
  //diacritic marks
  public static final Character FATHA_TANWEEN = 0x064b;
  public static final Character DAMMA_TANWEEN = 0x064c;
  public static final Character KASRA_TANWEEN = 0x064d;
  public static final Character FATHA = 0x064e;
  public static final Character DAMMA = 0x064f;
  public static final Character KASRA = 0x0650;
  public static final Character SUKUN = 0x0652;
  public static final Character SHADDA = 0x0651;
  public static final Character SMALL_ALEF = 0x0670; //the superscript alef

  public static final Character ALEF = 0x0627; // normal alef
  public static final Character ALEF_LAYINA = 0x0649; // looks like ya but pronounced like alef

  public static final Character NOON = 0x0646;
  public static final Character BA = 0x0628;
  public static final Character MEEM = 0x0645;

  //pausemarks
  public static final Character SMALL_SAAD_LAAM_ALEF = 0x06D6;
  public static final Character SMALL_QAAF_LAAM_ALEF = 0x06D7;
  public static final Character SMALL_HIGH_MEEM = 0x06D8;
  public static final Character SMALL_LAAM_ALEF = 0x06D9;
  public static final Character SMALL_HIGH_JEEM = 0x06DA;
  public static final Character SMALL_THREE_DOTS = 0x06DB;

  public static boolean isDiaMark (int c){
      return c == FATHA_TANWEEN ||
              c == DAMMA_TANWEEN ||
              c == KASRA_TANWEEN ||
              c == FATHA ||
              c == DAMMA ||
              c == KASRA ||
              c == SUKUN ||
              c == SHADDA ||
              c == SMALL_ALEF;
  }

  public static boolean isEndMark (int c){
      return c == SMALL_SAAD_LAAM_ALEF ||
              c == SMALL_QAAF_LAAM_ALEF ||
              c == SMALL_HIGH_MEEM ||
              c == SMALL_LAAM_ALEF ||
              c == SMALL_HIGH_JEEM ||
              c == SMALL_THREE_DOTS;
  }
  
  public static boolean isLetter (int c){
    return !isEndMark(c) && !isDiaMark(c) && c != ' ';
  }

  /**
   * Given a string and an index, return the previous character and its position, ignoring spaces.
   * @param string the string to look at
   * @param index the index of the character to look before
   * @return a {{@link CharacterInfo}} instance containing the index and character. In case of
   * errors or inability to move backwards, a character of -1 maybe returned.
   */
  public static CharacterInfo getPreviousCharacter(String string, int index) {
    int previous = -1;
    int lastIndex = index;
    if (index > 0) {
      previous = string.codePointBefore(index);
      while (Character.isSpaceChar(previous) && lastIndex > 0) {
        previous = string.codePointBefore(lastIndex--);
      }
    }
    return new CharacterInfo(lastIndex, previous);
  }
}
