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
            ResultType.IQLAB_NOT_PRONOUNCED, previousCharacter.index, previousCharacter.index + 1));
      }
    }
    return results;
  }
}
