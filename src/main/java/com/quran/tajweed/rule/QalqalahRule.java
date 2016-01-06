package com.quran.tajweed.rule;

import com.quran.tajweed.util.CharacterUtil;

/**
 * Qalqalah Rule
 * The letters of qalqalah are ق ط ب ج د. Qalqalah is done on these letters when
 * there is either a sukoon on the letter or when one is stopping on these letters
 */

public class QalqalahRule implements Rule {

  private static final Character BA = CharacterUtil.BA;
  private static final Character JEEM = 0x062c;
  private static final Character DAAL = 0x062f;
  private static final Character QAAF = 0x0642;
  private static final Character TAA = 0x0637;

  @Override
  public void checkAyah(String ayah) {
    System.out.println("checking qalqalah...");
    int length = ayah.length();
    int startPos = 0, endPos = 0;
    for (int i = 0; i < length; i++) {
      int[] next = {0, 0, 0, 0, 0};
      int currentChar = ayah.codePointAt(i);
      for (int j = 0; j < next.length; j++) {
        int nIndex = i + j + 1;
        if (nIndex < length) {
          next[j] = ayah.codePointAt(nIndex);
        }
      }

      if ((currentChar == DAAL ||
          currentChar == BA ||
          currentChar == JEEM ||
          currentChar == QAAF ||
          currentChar == TAA) &&
          (next[0] == CharacterUtil.SUKUN || weStopping(next)))    {
        startPos = i;
        endPos = i + remaingMarks(next);
        System.out.print("match from: " + startPos + " till " + endPos + ", letter: ");
        System.out.println(Character.toChars(currentChar));
      }
    }
  }

  private boolean weStopping(int[] next){
    for (int i = 0; i < next.length; i++){
      if ((CharacterUtil.isEndMark(next[i]) && next[i] != CharacterUtil.SMALL_LAAM_ALEF) || next[i] == 0) {
        return true;
      }
      if(CharacterUtil.isLetter(next[i])){
        break;
      }
    }
    return false;
  }

  private int remaingMarks(int[] next){
    int i = 0;
    for (; i < next.length; i++){
      if (!CharacterUtil.isDiaMark(next[i])) {
        break;
      }
    }
    return i;
  }
}
