package com.quran.tajweed;

public class CharacterUtil {
  public static boolean isDiaMark (char c){
        return c == 'ً' || c == 'ٌ' || c == 'ٍ' || c == 'َ' || c == 'ُ' || c == 'ِ' || c == 'ّ' || c == 'ْ' || c == 'ٰ';
  }

  public static boolean isEndMark (char c){
      return c == 'ؕ' || c == 'ۘ' || c == 'ۚ'|| c == 'ۢ'|| c == 'ۙ'|| c == 'ۖ'|| c == 'ۗ'|| c == '؞'|| c == 'ؗ';
  }
}
