package com.quran.tajweed.rule;

import com.quran.tajweed.model.Result;
import com.quran.tajweed.model.ResultType;
import com.quran.tajweed.util.CharacterUtil;

import java.util.ArrayList;
import java.util.List;

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
  public List<Result> checkAyah(String ayah) {
    List<Result> results = new ArrayList<>();
    int length = ayah.length();
    int startPos, endPos;
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
        results.add(new Result(ResultType.QALQALAH, startPos, endPos));
      }
    }
    return results;
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
