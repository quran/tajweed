package com.quran.tajweed.exporter;

import com.quran.tajweed.model.Result;
import com.quran.tajweed.model.ResultType;
import com.quran.tajweed.model.TwoPartResult;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageExporter implements Exporter {
  private Font font;

  @Override
  public void export(String ayah, List<Result> results) {
    AttributedString attributedString = new AttributedString(ayah);
    attributedString.addAttribute(TextAttribute.FONT, font);

    for (Result result : results) {
      int start = result.getMinimumStartingPosition();
      if (result instanceof TwoPartResult) {
        TwoPartResult twoPartResult = (TwoPartResult) result;
        if (start == twoPartResult.start) {
          // first, then second
          attributedString.addAttribute(TextAttribute.FOREGROUND,
              getColorForRule(twoPartResult.resultType),
              twoPartResult.start, twoPartResult.ending);
          attributedString.addAttribute(TextAttribute.FOREGROUND,
              getColorForRule(twoPartResult.secondResultType),
              twoPartResult.secondStart, twoPartResult.secondEnding);
        } else {
          // second, then first
          attributedString.addAttribute(TextAttribute.FOREGROUND,
              getColorForRule(twoPartResult.secondResultType),
              twoPartResult.secondStart, twoPartResult.secondEnding);
          attributedString.addAttribute(TextAttribute.FOREGROUND,
              getColorForRule(twoPartResult.resultType),
              twoPartResult.start, twoPartResult.ending);
        }
      } else {
        attributedString.addAttribute(TextAttribute.FOREGROUND,
            getColorForRule(result.resultType), result.start, result.ending);
      }
    }

    writeImage(attributedString);
  }

  @Override
  public void onOutputStarted() {
    try {
      font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Scheherazade-Bold.ttf"));
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }
    font = font.deriveFont(75f);
  }

  private void writeImage(AttributedString attributedString) {
    BufferedImage bufferedImage = new BufferedImage(1000, 125, BufferedImage.TYPE_INT_ARGB);
    Graphics graphics = bufferedImage.getGraphics();
    if (graphics instanceof Graphics2D) {
      Graphics2D gfx = (Graphics2D) graphics;
      gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      gfx.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }
    graphics.setColor(Color.BLACK);
    graphics.drawString(attributedString.getIterator(), 50, 75);
    try {
      ImageIO.write(bufferedImage, "png",
          new File("ayah-" + System.currentTimeMillis() + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Color getColorForRule(ResultType type) {
    return new Color(Integer.decode("0x" + type.color));
  }
}
