package com.quran.tajweed.util;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class CharacterUtilTest {

  @Test
  public void testGetPreviousCharacter() {
    CharacterInfo info = CharacterUtil.getPreviousCharacter("hello", 1);
    assertThat(info.character).isEqualTo('h');
    assertThat(info.index).isEqualTo(0);
  }

  @Test
  public void testGetPreviousCharacterSpaces() {
    CharacterInfo info = CharacterUtil.getPreviousCharacter("a   hello", 3);
    assertThat(info.character).isEqualTo('a');
    assertThat(info.index).isEqualTo(0);
  }

  @Test
  public void testGetPreviousCharacterInvalid() {
    CharacterInfo info = CharacterUtil.getPreviousCharacter("hello", 0);
    assertThat(info.character).isEqualTo(-1);
    assertThat(info.index).isEqualTo(-1);

    info = CharacterUtil.getPreviousCharacter("   hello", 3);
    assertThat(info.character).isEqualTo(-1);
    assertThat(info.index).isEqualTo(-1);
  }
}