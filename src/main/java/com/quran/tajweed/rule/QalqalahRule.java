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
      int[] next = CharacterUtil.getNextChars(ayah, i);
      int currentChar = next[0];

      if ((currentChar == DAAL ||
          currentChar == BA ||
          currentChar == JEEM ||
          currentChar == QAAF ||
          currentChar == TAA) &&
          (next[1] == CharacterUtil.SUKUN ||
           next[1] == ' ' ||
           CharacterUtil.isLetter(next[1]) ||
           weStopping(next)))    {
        startPos = i;
        // In the madani pattern, only the qalqalh letter and sukun are highlighted (if present)
        endPos = i + 1;
        if(next[1] == CharacterUtil.SUKUN){
          endPos++;
        }
        if(CharacterUtil.NASKHSTYLE) {
          endPos = i + CharacterUtil.findRemainingMarks(next);
        }
        // A special case where no qalqalah is done see surah kafiroon ayah 4 for example
        if(next[1] == CharacterUtil.SUKUN || next[1] == ' '){
          //will do later
        }
        results.add(new Result(ResultType.QALQALAH, startPos, endPos));
      }
    }
    return results;
  }

  private boolean weStopping(int[] next){
    for (int i = 1; i < next.length; i++){
      if ((CharacterUtil.isEndMark(next[i]) && next[i] != CharacterUtil.SMALL_LAAM_ALEF && (CharacterUtil.NASKHSTYLE ||
          (next[i] != CharacterUtil.SMALL_THREE_DOTS))) || next[i] == 0) {
        return true;
      }
      if(CharacterUtil.isLetter(next[i])){
        break;
      }
    }
    return false;
  }
}
