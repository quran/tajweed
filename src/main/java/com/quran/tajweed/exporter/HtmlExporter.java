package com.quran.tajweed.exporter;

import com.quran.tajweed.model.Result;
import com.quran.tajweed.model.ResultType;
import com.quran.tajweed.model.TwoPartResult;
import com.quran.tajweed.util.CharacterUtil;

import java.util.List;

/**
 * HtmlExporter
 * Exports the highlighted tajweed results for the given ayahs as html.
 * Note that this only works in Firefox due to a bug in Webkit - see:
 * https://bugs.webkit.org/show_bug.cgi?id=6148 and
 * http://stackoverflow.com/questions/11155849/partially-colored-arabic-word-in-html
 * for more details.
 */
public class HtmlExporter implements Exporter {
  private static final char ZERO_WIDTH_JOINER = 0x200d;

  @Override
  public void onOutputStarted() {
    String builder = "<html>" + "<head>" +
        "<meta charset=\"UTF-8\">" +
        "<style>" +
        "body { font-size: 350%; }" +
        "</style>" +
        "<title>Tajweed Test</title>" +
        "</head>";
    write(builder);
  }

  @Override
  public void onOutputCompleted() {
    write("</html>");
  }

  @Override
  public void export(String ayah, List<Result> results) {
    StringBuilder buffer = new StringBuilder("<p>");
    int currentIndex = 0;
    for (Result result : results) {
      int start = result.getMinimumStartingPosition();
      if (start > currentIndex) {
        buffer.append(ayah.substring(currentIndex, start));
      }

      start = result.getMinimumStartingPosition();
      if (result instanceof TwoPartResult) {
        TwoPartResult twoPartResult = (TwoPartResult) result;
        if (start == twoPartResult.start) {
          // first, then second
          appendRule(buffer, ayah, twoPartResult.resultType,
              twoPartResult.start, twoPartResult.ending);
          if (twoPartResult.secondStart - twoPartResult.ending > 0) {
            buffer.append(ayah.substring(twoPartResult.ending, twoPartResult.secondStart));
          }
          appendRule(buffer, ayah, twoPartResult.secondResultType,
              twoPartResult.secondStart, twoPartResult.secondEnding);
        } else {
          // second, then first
          appendRule(buffer, ayah, twoPartResult.secondResultType,
              twoPartResult.secondStart, twoPartResult.secondEnding);
          if (twoPartResult.start - twoPartResult.secondEnding > 0) {
            buffer.append(ayah.substring(twoPartResult.secondEnding, twoPartResult.start));
          }
          appendRule(buffer, ayah,
              twoPartResult.resultType, twoPartResult.start, twoPartResult.ending);
        }
      } else {
        appendRule(buffer, ayah, result.resultType, result.start, result.ending);
      }
      currentIndex = result.getMaximumEndingPosition();
    }

    buffer.append(ayah.substring(currentIndex));
    buffer.append("</p>");
    write(buffer.toString());
  }

  private void appendRule(StringBuilder buffer, String ayah, ResultType type, int start, int end) {
    buffer.append("<font color=");
    buffer.append(getColorForRule(type));
    buffer.append(">");
    buffer.append(ayah.substring(start, end));
    if (!isEndOfWord(ayah, end)) {
      buffer.append(ZERO_WIDTH_JOINER);
    }
    buffer.append("</font>");
  }

  private boolean isEndOfWord(String ayah, int index) {
    int totalSize = ayah.length();
    for (int i = index; i < totalSize; i++) {
      int codePoint = ayah.codePointAt(i);
      if (CharacterUtil.isLetter(codePoint)) {
        return false;
      } else if (Character.isSpaceChar(codePoint)) {
        break;
      }
    }
    return true;
  }

  private String getColorForRule(ResultType type) {
    return Integer.toHexString(type.color);
  }

  private void write(String string) {
    System.out.println(string);
  }
}
