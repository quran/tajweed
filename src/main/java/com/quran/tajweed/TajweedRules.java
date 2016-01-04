package com.quran.tajweed;

import java.io.*;
import java.util.*;

public class TajweedRules{
  public static void main (String args[]){
    String arabicText = "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ";//sample verse
    int length = arabicText.length();
    
    //general set up
    for(int i = 0; i < length; i++){
      // all these if statements stores previous and next characters to help in applying tajweed rules
      // c1,2... refer to next chars while cp1,2 etc are previous chars. c is simply current char we are reading
      char c = arabicText.charAt(i);
      char c1 = '!', c2 = '!', c3 = '!', cp1 = '!', cp2 = '!', cp3 = '!';
      boolean shaddaAtEnd = false;
      if (i + 1 < length)
        c1 = arabicText.charAt(i+1);
      if (i + 2 < length)
        c2 = arabicText.charAt(i+2);
      if (i + 3 < length)
        c3 = arabicText.charAt(i+3);
      if (i - 1 >= 0)
        cp1 = arabicText.charAt(i-1);
      if (i - 2 >= 0)
        cp2 = arabicText.charAt(i-2);
      if (i - 3 >= 0)
        cp3 = arabicText.charAt(i-3);
      
      
      //qalqalah
      if (c1 == 'ّ'&& (i+3 >= length || isEndMark(c3)))
        shaddaAtEnd = true;
      //checks wheter ending letter has shadda since this will cause an extra character to be taken into account
      int tillQalqalah = i + 2; //normally Qalqalah has the letter itself and a sukoon/case ending during a stop
      if (shaddaAtEnd)
        tillQalqalah++; //in this case (like surah Masad verse 1) there is additional one char length
      if (c2 == '!')
        tillQalqalah = length; //if the ayah is ending, it should go till end of ayah then
      if ((c == 'د') || (c == 'ب') || (c == 'ج') || (c == 'ق')|| (c == 'ط')) { //is this even qalqalah? lol
          if(c2 == '!' || c1 == 'ْ'|| isEndMark(c2) || shaddaAtEnd) {//c1==sukoon. btw if statement separated for simplicity
            /*
            arabicText.setSpan(new ForegroundColorSpan(Color.RED),
                i, tillQalqalah, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            the above was used in Android app to go ahead and set the color
            but now we will use it to set position. At this point in the code,
            we know that there is qalqalah at the position i in the verse. We also
            know by using tillQalqlah, how many chars to include in the font coloring.
            The position i and end position tillQalqalah can be stored in a db file for
            this specific verse.
            */
          }
      } //end qalqalah check
            
    }
    
  }
  
  public static boolean isDiaMark (char c){
        return c == 'ً' || c == 'ٌ' || c == 'ٍ' || c == 'َ' || c == 'ُ' || c == 'ِ' || c == 'ّ' || c == 'ْ' || c == 'ٰ';
  }
  
  public static boolean isEndMark (char c){
      return c == 'ؕ' || c == 'ۘ' || c == 'ۚ'|| c == 'ۢ'|| c == 'ۙ'|| c == 'ۖ'|| c == 'ۗ'|| c == '؞'|| c == 'ؗ';
  }
}
