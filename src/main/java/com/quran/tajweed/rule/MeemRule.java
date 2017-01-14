package com.quran.tajweed.rule;

import com.quran.tajweed.model.Result;
import com.quran.tajweed.model.ResultType;
import com.quran.tajweed.util.CharacterUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * There are two rules with meem:
 * After a meem sakin, if there is a ba then we have Ikhfa meem rule
 * After a meem sakin, if there is another meem (with a shadda, in full scripts)
 * then we have Idgaam meem rule
 */
public class MeemRule implements Rule {
  @Override
  public List<Result> checkAyah(String ayah) {
    List<Result> results = new ArrayList<>();
    int length = ayah.length();
    int startPos, endPos;
    for (int i = 0; i < length; i++) {
      int[] next = CharacterUtil.getNextChars(ayah, i);
      if (CharacterUtil.isMeemSaakin(next)) {
        for (int j = 1; j < next.length && next[j] != 0; j++) {
          if (CharacterUtil.isLetter(next[j])) {
            if (next[j] == CharacterUtil.MEEM || next[j] == CharacterUtil.BA) {
              startPos = i;
              endPos = i + CharacterUtil.findRemainingMarks(next);
              if (CharacterUtil.NASKHSTYLE) {
                endPos = i + j + CharacterUtil
                    .findRemainingMarks(Arrays.copyOfRange(next, j, next.length));
              }
              if (next[j] == CharacterUtil.MEEM) {
                results.add(new Result(ResultType.MEEM_IDGHAM, startPos, endPos));
              } else {
                results.add(new Result(ResultType.MEEM_IKHFA, startPos, endPos));
              }
            } else {
              break;
            }
          }
        }
      }
    }
    return results;
  }
}
