package com.quran.tajweed;

public class CharacterUtil {

  public static final Character FATHA_TANWEEN = 0x064b;
  public static final Character DAMMA_TANWEEN = 0x064c;
  public static final Character KASRA_TANWEEN = 0x064d;

  public static boolean isDiaMark (char c){
        return c == 'ً' || c == 'ٌ' || c == 'ٍ' || c == 'َ' || c == 'ُ' || c == 'ِ' || c == 'ّ' || c == 'ْ' || c == 'ٰ';
  }

  public static boolean isEndMark (char c){
      return c == 'ؕ' || c == 'ۘ' || c == 'ۚ'|| c == 'ۢ'|| c == 'ۙ'|| c == 'ۖ'|| c == 'ۗ'|| c == '؞'|| c == 'ؗ';
  }
}
