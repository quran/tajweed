package com.quran.tajweed.util;

public class CharacterUtil {
  public static boolean NASKHSTYLE = false;

  // diacritic marks
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
   * Given an array of characters this checks for the cases there is a noon saakin.
   * Namely, noon saakin can either exist if there is a noon either explicitly followed
   * by a sukun or in certain unmarked texts it will have no marks. Note: Only array
   * size 2 is necessary to determine this.
   */
  public static boolean isNoonSaakin(int[] nextChars){
    return nextChars[0] == NOON &&
            (nextChars[1] == SUKUN ||
             nextChars[1] == ' ' ||
             isLetter(nextChars[1]));
  }

  public static boolean isMeemSaakin(int[] nextChars){
    return (nextChars[0] == MEEM &&
            (nextChars[1] == SUKUN ||
            nextChars[1] == ' ' ||
            isLetter(nextChars[1])));
  }

  public static boolean isTanween(int thisChar){
    return thisChar == FATHA_TANWEEN ||
            thisChar == DAMMA_TANWEEN ||
            thisChar == KASRA_TANWEEN;
  }
  
  /**
   * Given a string and an index, return an arrays with next characters from the index.
   * The first position is always the character at current position.
   * Zeros are used to represent non-characters and thus end of the string.
   */
  public static int[] getNextChars(String ayah, int index){
    int[] next = {0, 0, 0, 0, 0, 0};
    for (int j = 0; j < next.length; j++){
      int nIndex = index + j;
      if(nIndex < ayah.length()){
        next[j] = ayah.codePointAt(nIndex);
      }
    }
    return next;
  }

  public static int[] getPreviousChars(String ayah, int index){
    int[] previous = {0, 0, 0, 0, 0, 0};
    for (int j = 0; j < previous.length; j++){
      int pIndex = index - j;
      if(pIndex >= 0){
        previous[j] = ayah.codePointAt(pIndex);
      }
    }
    return previous;
  }
  
  /**
   * Given a string and an index, return the previous character and its position, ignoring spaces.
   * @param string the string to look at
   * @param index the index of the character to look before
   * @return a {{@link CharacterInfo}} instance containing the index and character. In case of
   * errors or inability to move backwards, a character of -1 is returned.
   */
  public static CharacterInfo getPreviousCharacter(String string, int index) {
    int previous = -1;
    int lastIndex = index - 1;
    if (index > 0) {
      previous = string.codePointBefore(index);
      while (Character.isSpaceChar(previous) && lastIndex > 0) {
        previous = string.codePointBefore(lastIndex--);
      }
    }

    if (Character.isSpaceChar(previous)) {
      previous = -1;
      lastIndex = -1;
    }
    return new CharacterInfo(lastIndex, previous);
  }

  public static int findPreviousLetterPronounced (int[] previous){
    for (int i = 2; i < previous.length; i++){
      if(isLetter(previous[i]) && isDiaMark(previous[i-1])){
        return i;
      }
    }
    return 0;
  }

  public static int findRemainingMarks(int[] next) {
    for (int i = 1; i < next.length; i++){
      if(!isDiaMark(next[i])){
        return i;
      }
    }
    return 0;
  }
}
