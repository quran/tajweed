package com.quran.tajweed.rule;

import com.quran.tajweed.CharacterUtil;

/**
 * There are two rules with meem:
 * After a meem sakin, if there is a ba then we have Ikhfa meem rule
 * After a meem sakin, if there is another meem (with a shadda, in full scripts)
 * then we have Idgaam meem rule
 */
public class MeemRule implements Rule {

    @Override
    public void checkAyah(String ayah){
        System.out.println("checking meem rules...");
        int length = ayah.length();
        for (int i = 0; i < length; i++) {
            int[] nextWithCurrent = {0, 0, 0, 0, 0, 0, 0, 0};
            for (int j = 0; j < nextWithCurrent.length; j++) {
                int nIndex = i + j;
                if (nIndex < length) {
                    nextWithCurrent[j] = ayah.codePointAt(nIndex);
                }
            }
            if(nextWithCurrent[0] == CharacterUtil.MEEM && nextWithCurrent[1] == CharacterUtil.SUKUN){
                checkMeemIdgham(nextWithCurrent, i);
                checkMeemIkhfa(nextWithCurrent, i);
            }

        }
    }

    private void checkMeemIdgham(int[] nextWithCurrent, int i){
        int startPos = 0, endPos = 0;
        for (int j = 2; j < nextWithCurrent.length && nextWithCurrent[j] != 0; j++){
            if(CharacterUtil.isLetter(nextWithCurrent[j])){
                if(nextWithCurrent[j] == CharacterUtil.MEEM){
                    startPos = i;
                    endPos = i + remainingMarks(nextWithCurrent, j);
                    System.out.print("match from meem idgham : " + startPos + " till " + endPos + ", letter: ");
                    System.out.println(Character.toChars(nextWithCurrent[0]));
                }
                else {
                    break;
                }
            }
        }

    }

    private void checkMeemIkhfa(int[] nextWithCurrent, int i){
        int startPos = 0, endPos = 0;
        for (int j = 2; j < nextWithCurrent.length && nextWithCurrent[j] != 0; j++){
            if(CharacterUtil.isLetter(nextWithCurrent[j])){
                if(nextWithCurrent[j] == CharacterUtil.BA){
                    startPos = i;
                    endPos = i + remainingMarks(nextWithCurrent, j);
                    System.out.print("match from meem ikhfa: " + startPos + " till " + endPos + ", letter: ");
                    System.out.println(Character.toChars(nextWithCurrent[0]));
                }
                else {
                    break;
                }
            }
        }
    }

    private int remainingMarks(int[] nextWithCurrent, int j){
        for (;j < nextWithCurrent.length && nextWithCurrent[j] != 0; j++){
            if(CharacterUtil.isLetter(nextWithCurrent[j])){
                return j;
            }
        }
        return 0;
    }
}
