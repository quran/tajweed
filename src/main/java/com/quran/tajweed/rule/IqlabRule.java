package com.quran.tajweed.rule;

import com.quran.tajweed.model.Result;
import com.quran.tajweed.model.ResultType;
import com.quran.tajweed.model.TwoPartResult;
import com.quran.tajweed.util.CharacterInfo;
import com.quran.tajweed.util.CharacterUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Iqlab Rule
 * Iqlab occurs when a ن (noon sakina) or tanween is followed by a ب.
 */
public class IqlabRule implements Rule {

  @Override
  public List<Result> checkAyah(String ayah) {
    List<Result> results = new ArrayList<>();
    int index = -1;
    while ((index = (ayah.indexOf(CharacterUtil.BA, index + 1))) > -1) {
      CharacterInfo previousCharacter = CharacterUtil.getPreviousCharacter(ayah, index);

      int previous = previousCharacter.character;
      if (previous == CharacterUtil.FATHA_TANWEEN ||
          previous == CharacterUtil.DAMMA_TANWEEN ||
          previous == CharacterUtil.KASRA_TANWEEN ||
          previous == CharacterUtil.NOON) {
        results.add(new TwoPartResult(ResultType.IQLAB, index, index + 1,
            ResultType.IQLAB_NOT_PRONOUNCED, previousCharacter.index,
            previousCharacter.index + 1));
      } else if (previous == CharacterUtil.SMALL_HIGH_MEEM_ISOLATED) {
        // the letter should be pronounced as a meem - let's double check it's above a noon
        CharacterInfo actual = CharacterUtil.getPreviousCharacter(ayah, previousCharacter.index);
        if (actual.character == CharacterUtil.NOON) {
          results.add(new TwoPartResult(ResultType.IQLAB, previousCharacter.index, index + 1,
              ResultType.IQLAB_NOT_PRONOUNCED, actual.index, previousCharacter.index));
        }
      }
    }
    return results;
  }
}
