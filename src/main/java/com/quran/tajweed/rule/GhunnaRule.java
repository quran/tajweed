package com.quran.tajweed.rule;

import com.quran.tajweed.CharacterUtil;

/**
 * Ghunna Rule
 * Occurs when a noon shadda or meem shadda appears
 */
public class GhunnaRule implements Rule {
  @Override
  public void checkAyah(String ayah){
    System.out.println("Checking ghunna...");
    int length = ayah.length();
    int startPos = 0, endPos = 0;
    for (int i = 0; i < length; i++){
      char[] previous = {'!', '!', '!', '!', '!'};
      char[] next = {'!', '!', '!', '!', '!'};
      for (int j = 0; j < previous.length; j++){
        int nIndex = i + j + 1;
        int pIndex = i - j - 1;
        if(nIndex < length){
          next[j] = ayah.charAt(nIndex);
        }
        if(pIndex >= 0){
          previous[j] = ayah.charAt(pIndex);
        }
      }
      char currentChar = ayah.charAt(i);
      if ((currentChar == CharacterUtil.NOON ||
        currentChar == CharacterUtil.MEEM) &&
        next[0] == CharacterUtil.SHADDA){

          startPos = i - findPreviousLetterPronounced(previous);
          endPos = i + findNextLetter(next);
          System.out.println("match from: " + startPos + " till " + endPos + ", letter: " + currentChar + " next[]="+next[0]);
      }
    }
  }

  private int findPreviousLetterPronounced (char[] previous){
    for (int i = 1; i < previous.length; i++){
      if(CharacterUtil.isLetter(previous[i]) && CharacterUtil.isDiaMark(previous[i-1])){
        return i;
      }
    }
    return 0;
  }

  private int findNextLetter (char[] next) {
    for (int i = 0; i < next.length; i++){
      if(!CharacterUtil.isDiaMark(next[i])){
        return i;
      }
    }
    return 0;
  }
}
