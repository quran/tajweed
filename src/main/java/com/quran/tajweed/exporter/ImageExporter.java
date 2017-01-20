package com.quran.tajweed.exporter;

import com.quran.tajweed.model.Result;
import com.quran.tajweed.model.ResultType;
import com.quran.tajweed.model.TwoPartResult;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageExporter implements Exporter {
  private static final int MAXIMUM_WIDTH = 1000;

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
    BufferedImage bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

    Graphics2D gfx = prepareGraphics(bufferedImage.getGraphics());
    if (gfx != null) {
      FontRenderContext fontRenderContext = gfx.getFontRenderContext();
      AttributedCharacterIterator charIterator = attributedString.getIterator();
      LineBreakMeasurer lineBreakMeasurer = new LineBreakMeasurer(charIterator, fontRenderContext);
      Point size = getImageSize(charIterator, lineBreakMeasurer, MAXIMUM_WIDTH);
      bufferedImage = new BufferedImage(size.x, size.y, BufferedImage.TYPE_INT_ARGB);
      gfx = prepareGraphics(bufferedImage.getGraphics());

      if (gfx != null) {
        gfx.setColor(Color.BLACK);

        float drawPositionX = 0;
        float drawPositionY = 0;
        final int endIndex = charIterator.getEndIndex();
        lineBreakMeasurer.setPosition(charIterator.getBeginIndex());
        while (lineBreakMeasurer.getPosition() < endIndex) {
          TextLayout layout = lineBreakMeasurer.nextLayout(MAXIMUM_WIDTH);
          drawPositionY += layout.getAscent();
          layout.draw(gfx, drawPositionX, drawPositionY);
          drawPositionY += layout.getDescent() + layout.getLeading();
        }

        try {
          ImageIO.write(bufferedImage, "png",
              new File("ayah-" + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private Graphics2D prepareGraphics(Graphics graphics) {
    if (graphics instanceof Graphics2D) {
      Graphics2D gfx = (Graphics2D) graphics;
      gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      gfx.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      return gfx;
    }
    return null;
  }

  private Point getImageSize(AttributedCharacterIterator charIterator,
                             LineBreakMeasurer lineBreakMeasurer,
                             int maximumWidth) {
    final int endIndex = charIterator.getEndIndex();
    float drawPositionX = 0;
    float drawPositionY = 0;
    while (lineBreakMeasurer.getPosition() < endIndex) {
      TextLayout layout = lineBreakMeasurer.nextLayout(maximumWidth);
      float width = (float) layout.getBounds().getWidth();
      drawPositionX = Math.max(drawPositionX, width + Math.abs(width - layout.getAdvance()));
      drawPositionY += layout.getAscent() + layout.getDescent() + layout.getLeading();
    }
    return new Point((int) drawPositionX, (int) drawPositionY);
  }

  private Color getColorForRule(ResultType type) {
    return new Color(Integer.decode("0x" + type.color));
  }
}
