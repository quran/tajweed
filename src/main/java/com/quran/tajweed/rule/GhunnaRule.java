package com.quran.tajweed.rule;

import com.quran.tajweed.model.Result;
import com.quran.tajweed.model.ResultType;
import com.quran.tajweed.util.CharacterUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Ghunna Rule
 * Occurs when a noon shadda or meem shadda appears
 */
public class GhunnaRule implements Rule {

  @Override
  public List<Result> checkAyah(String ayah){
    List<Result> results = new ArrayList<>();
    int length = ayah.length();
    int startPos = 0, endPos = 0;
    for (int i = 0; i < length; i++){
      int[] previous = CharacterUtil.getPreviousChars(ayah, i);
      int[] next = CharacterUtil.getNextChars(ayah, i);
      int currentChar = next[0];
      if ((currentChar == CharacterUtil.NOON ||
         currentChar == CharacterUtil.MEEM) &&
         (next[1] == CharacterUtil.SHADDA ||
         next[2] == CharacterUtil.SHADDA)){
        startPos = i;
        endPos = i + CharacterUtil.findRemainingMarks(next);
        int indexOfPreviousPronounced = CharacterUtil.findPreviousLetterPronounced(previous);
        if(CharacterUtil.NASKHSTYLE) {
          startPos = i - indexOfPreviousPronounced;
        }
        // In Naskh, meem idgham is differentiated with a different color than ghunna
        results.add(new Result(ResultType.GHUNNA, startPos, endPos));
      }
    }
    return results;
  }
}
