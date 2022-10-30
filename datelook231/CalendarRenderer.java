import java.awt.*;
import java.util.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;


/*
 *  Title:        DateLook
 *  Copyright:    Copyright (c) 2001 - 2011
 *  Author:       Rene Ewald
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License as
 *  published by the Free Software Foundation; either version 2 of
 *  the License, or (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details. You should have
 *  received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
/**
 *  Renders the calendar on the DateLookPanel.
 */
public class CalendarRenderer {

  // main colors
  private float red_begin = 80;
  private float green_begin = 150;
  private float blue_begin = 190;
  private Color month_color = new Color((int) (red_begin + 0.85 * (255 - red_begin)),
      (int) (green_begin + 0.85 * (255 - green_begin)),
      (int) (blue_begin + 0.85 * (255 - blue_begin)));
  private Color hour_color = new Color((int) (red_begin + 0.5 * (255 - red_begin)),
      (int) (green_begin + 0.5 * (255 - green_begin)),
      (int) (blue_begin + 0.5 * (255 - blue_begin)));
  private Color week_end_color = new Color(255, 180, 180);
  private Color week_color = Color.white;
  private int day_width;
  private int space;
  private Font f;
  private Font small_f;
  private int digit_font_width;
  private int font_height;
  private int font_ascent;
  private int small_digit_font_width;
  private int small_font_ascent;


  /**
   *  Gets the font attribute of the CalendarRenderer object
   *
   * @return    The font value
   */
  public Font get_font() {
    return f;
  }


  /**
   *  Draw the calendar on the DateLookPanel
   *
   * @param  g2  graphics object
   */
  public void draw(Graphics2D g2) {
    int csh = DateLookPanel.slot_height;
    int csn = DateLookPanel.number_of_slots;
    
    // day_width and small_f will be changed by zooming therefore both must be determined on each draw
    day_width = DateLookPanel.get_instance(null).getWidth() * 24 / (int) DateLookPanel.get_instance(null).get_number_of_rendered_hours(); // not exact for DST-switches!
    small_f = new Font("SansSerif", Font.PLAIN, Math.min(DateLookPanel.slot_height * 2 / 3, day_width / 8));
    FontRenderContext context = g2.getFontRenderContext();
    Rectangle2D bounds = small_f.getStringBounds("0", context);
    small_digit_font_width = (int) bounds.getWidth();
    small_font_ascent = (int) -bounds.getY();

    g2.setFont(f);
    g2.setColor(Color.black);
    GregorianCalendar gc = Converter.ms2gc(DateLookPanel.get_instance(null).get_first_rendered_hour_UTC_ms());
    gc.set(GregorianCalendar.HOUR_OF_DAY, 0);

    // render each day
    int i = 0;
    // counter of renderer days
    while (true) {
      int x_pos_real = UTC2x_pos(gc.getTime().getTime());
      int x_pos;
      // x where day rendering begins (for first day not equal to x_pos_real!)
      if (i != 0) {
        x_pos = x_pos_real;
      }
      else {
        x_pos = 0;
      }
      if (x_pos > DateLookPanel.get_instance(null).getWidth()) {
        break;
      }

      // draw grid lines, rectangles and write text
      // year
      if (i == 0 || gc.get(GregorianCalendar.DAY_OF_YEAR) == 1) {
        g2.setColor(Color.white);
        g2.fillRect(x_pos, 0, DateLookPanel.get_instance(null).getWidth(), csh);
        g2.setColor(Color.black);
        if (i != 0) {
          g2.drawLine(x_pos, 0, x_pos, csh);
        }
        g2.drawString(new Integer(gc.get(GregorianCalendar.YEAR)).toString(),
            x_pos + space, csh / 2 + font_ascent / 2);
      }

      // month
      g2.setColor(Color.black);
      if (i == 0 || gc.get(GregorianCalendar.DAY_OF_MONTH) == 1) {
        g2.setColor(month_color);
        g2.fillRect(x_pos, csh, DateLookPanel.get_instance(null).getWidth(), csh);
        g2.setColor(Color.black);
        if (i != 0) {
          g2.drawLine(x_pos, csh, x_pos, 2 * csh);
        }
        g2.drawString(Converter.gc2monthl(gc), x_pos + space,
            (3 * csh) / 2 + font_ascent - font_height / 2);
      }

      int day_width_ext = day_width * 25 / 24 + 2;  // to render DST-switch-day exact too!

      // day
      g2.setColor(this.getDayColour(gc));
      g2.fillRect(x_pos, 2 * csh, day_width_ext, csh);
      if (day_width > 16) {
        g2.setColor(Color.black);
        g2.drawString(gc.get(GregorianCalendar.DAY_OF_MONTH) + "",
            x_pos + space, (5 * csh) / 2 + font_ascent / 2);
        if (gc.get(GregorianCalendar.DAY_OF_WEEK) == Calendar.MONDAY && (day_width > 2 * (digit_font_width + small_digit_font_width + space))) {
          g2.setFont(small_f);
          g2.setColor(Color.red);
          g2.drawString(gc.get(GregorianCalendar.WEEK_OF_YEAR) + "",
              x_pos + day_width - space - small_digit_font_width * 2, (5 * csh) / 2 + font_ascent / 2);
          g2.setFont(f);
          g2.setColor(Color.black);
        }
      }
      else if ((gc.get(GregorianCalendar.DAY_OF_WEEK) == Calendar.SUNDAY) && (7 * day_width > space + 2 * digit_font_width)) {
        g2.setColor(Color.red);
        g2.drawString(gc.get(GregorianCalendar.WEEK_OF_YEAR) + "",
            x_pos - 6 * day_width + space / 2, (7 * csh) / 2 + font_ascent / 2);
        g2.setColor(Color.black);
      }

      // day of week
      g2.setColor(this.getDayOfWeekColour(gc));
      g2.fillRect(x_pos, 3 * csh, day_width_ext, csh);
      if (day_width > 16) {
        g2.setColor(Color.black);
        g2.drawString(Converter.getDayOfWeekWString(gc),
            x_pos + space, (7 * csh) / 2 + font_ascent - font_height / 2);
      }

      // hours
      g2.setColor(hour_color);
      g2.fillRect(x_pos_real, 4 * csh, day_width_ext, csh);

      // determine number of hours of that day (23/24/25)
      int day_hours = 24;
      day_hours = day_hours + gc.get(Calendar.DST_OFFSET) / (1000 * 60 * 60);

      // align digits to right edge of a day (necessary for DST-switch!)
      gc.add(GregorianCalendar.DAY_OF_YEAR, 1);  // increase day
      day_hours = day_hours - gc.get(Calendar.DST_OFFSET) / (1000 * 60 * 60);
      x_pos_real = UTC2x_pos(gc.getTime().getTime()) - day_width;

      if (day_width > 30) {
        g2.setColor(Color.black);
        g2.setFont(small_f);
        g2.drawString("6", x_pos_real + day_width / 4 - small_digit_font_width / 2, (9 * csh) / 2 + small_font_ascent / 2);
        g2.drawString("12", x_pos_real + day_width / 2 - small_digit_font_width, (9 * csh) / 2 + small_font_ascent / 2);
        g2.drawString("18", x_pos_real + day_width * 3 / 4 - small_digit_font_width, (9 * csh) / 2 + small_font_ascent / 2);
        if (day_width / 16 > small_digit_font_width) {
          g2.drawString("3", x_pos_real + day_width / 8 - small_digit_font_width / 2, (9 * csh) / 2 + small_font_ascent / 2);
          g2.drawString("9", x_pos_real + day_width * 3 / 8 - small_digit_font_width / 2, (9 * csh) / 2 + small_font_ascent / 2);
          g2.drawString("15", x_pos_real + day_width * 5 / 8 - small_digit_font_width, (9 * csh) / 2 + small_font_ascent / 2);
          g2.drawString("21", x_pos_real + day_width * 7 / 8 - small_digit_font_width, (9 * csh) / 2 + small_font_ascent / 2);
          if (day_width / 48 > small_digit_font_width) {
            // draw hour-lines too
            g2.drawLine(x_pos_real + day_width * 6 / 24, 5 * csh, x_pos_real + day_width * 6 / 24, csn * csh);
            g2.drawLine(x_pos_real + day_width * 12 / 24, 5 * csh, x_pos_real + day_width * 12 / 24, csn * csh);
            g2.drawLine(x_pos_real + day_width * 18 / 24, 5 * csh, x_pos_real + day_width * 18 / 24, csn * csh);
            g2.drawLine(x_pos_real + day_width * 3 / 24, 5 * csh, x_pos_real + day_width * 3 / 24, csn * csh);
            g2.drawLine(x_pos_real + day_width * 9 / 24, 5 * csh, x_pos_real + day_width * 9 / 24, csn * csh);
            g2.drawLine(x_pos_real + day_width * 15 / 24, 5 * csh, x_pos_real + day_width * 15 / 24, csn * csh);
            g2.drawLine(x_pos_real + day_width * 21 / 24, 5 * csh, x_pos_real + day_width * 21 / 24, csn * csh);
            g2.drawString("4", x_pos_real + day_width * 4 / 24 - small_digit_font_width / 2, (9 * csh) / 2 + small_font_ascent / 2);
            g2.drawLine(x_pos_real + day_width * 4 / 24, 5 * csh, x_pos_real + day_width * 4 / 24, csn * csh);
            g2.drawString("5", x_pos_real + day_width * 5 / 24 - small_digit_font_width / 2, (9 * csh) / 2 + small_font_ascent / 2);
            g2.drawLine(x_pos_real + day_width * 5 / 24, 5 * csh, x_pos_real + day_width * 5 / 24, csn * csh);
            g2.drawString("7", x_pos_real + day_width * 7 / 24 - small_digit_font_width / 2, (9 * csh) / 2 + small_font_ascent / 2);
            g2.drawLine(x_pos_real + day_width * 7 / 24, 5 * csh, x_pos_real + day_width * 7 / 24, csn * csh);
            g2.drawString("8", x_pos_real + day_width * 8 / 24 - small_digit_font_width / 2, (9 * csh) / 2 + small_font_ascent / 2);
            g2.drawLine(x_pos_real + day_width * 8 / 24, 5 * csh, x_pos_real + day_width * 8 / 24, csn * csh);
            g2.drawString("10", x_pos_real + day_width * 10 / 24 - small_digit_font_width, (9 * csh) / 2 + small_font_ascent / 2);
            g2.drawLine(x_pos_real + day_width * 10 / 24, 5 * csh, x_pos_real + day_width * 10 / 24, csn * csh);
            g2.drawString("11", x_pos_real + day_width * 11 / 24 - small_digit_font_width, (9 * csh) / 2 + small_font_ascent / 2);
            g2.drawLine(x_pos_real + day_width * 11 / 24, 5 * csh, x_pos_real + day_width * 11 / 24, csn * csh);
            g2.drawString("13", x_pos_real + day_width * 13 / 24 - small_digit_font_width, (9 * csh) / 2 + small_font_ascent / 2);
            g2.drawLine(x_pos_real + day_width * 13 / 24, 5 * csh, x_pos_real + day_width * 13 / 24, csn * csh);
            g2.drawString("14", x_pos_real + day_width * 14 / 24 - small_digit_font_width, (9 * csh) / 2 + small_font_ascent / 2);
            g2.drawLine(x_pos_real + day_width * 14 / 24, 5 * csh, x_pos_real + day_width * 14 / 24, csn * csh);
            g2.drawString("16", x_pos_real + day_width * 16 / 24 - small_digit_font_width, (9 * csh) / 2 + small_font_ascent / 2);
            g2.drawLine(x_pos_real + day_width * 16 / 24, 5 * csh, x_pos_real + day_width * 16 / 24, csn * csh);
            g2.drawString("17", x_pos_real + day_width * 17 / 24 - small_digit_font_width, (9 * csh) / 2 + small_font_ascent / 2);
            g2.drawLine(x_pos_real + day_width * 17 / 24, 5 * csh, x_pos_real + day_width * 17 / 24, csn * csh);
            g2.drawString("19", x_pos_real + day_width * 19 / 24 - small_digit_font_width, (9 * csh) / 2 + small_font_ascent / 2);
            g2.drawLine(x_pos_real + day_width * 19 / 24, 5 * csh, x_pos_real + day_width * 19 / 24, csn * csh);
            g2.drawString("20", x_pos_real + day_width * 20 / 24 - small_digit_font_width, (9 * csh) / 2 + small_font_ascent / 2);
            g2.drawLine(x_pos_real + day_width * 20 / 24, 5 * csh, x_pos_real + day_width * 20 / 24, csn * csh);
            g2.drawString("22", x_pos_real + day_width * 22 / 24 - small_digit_font_width, (9 * csh) / 2 + small_font_ascent / 2);
            g2.drawLine(x_pos_real + day_width * 22 / 24, 5 * csh, x_pos_real + day_width * 22 / 24, csn * csh);
            g2.drawString("23", x_pos_real + day_width * 23 / 24 - small_digit_font_width, (9 * csh) / 2 + small_font_ascent / 2);
            g2.drawLine(x_pos_real + day_width * 23 / 24, 5 * csh, x_pos_real + day_width * 23 / 24, csn * csh);
            // check for DST-switch, if true "1" and "2" must be shifted
            if (day_hours == 24) {
              // 24 h day
              g2.drawString("1", x_pos_real + day_width / 24 - small_digit_font_width / 2, (9 * csh) / 2 + small_font_ascent / 2);
              g2.drawLine(x_pos_real + day_width / 24, 5 * csh, x_pos_real + day_width / 24, csn * csh);
              g2.drawString("2", x_pos_real + day_width * 2 / 24 - small_digit_font_width / 2, (9 * csh) / 2 + small_font_ascent / 2);
              g2.drawLine(x_pos_real + day_width * 2 / 24, 5 * csh, x_pos_real + day_width * 2 / 24, csn * csh);
            }
            else if (day_hours == 25) {
              // 25 h day
              g2.drawString("1", x_pos_real - small_digit_font_width / 2, (9 * csh) / 2 + small_font_ascent / 2);
              g2.drawLine(x_pos_real, 5 * csh, x_pos_real, csn * csh);
              g2.drawString("2", x_pos_real + day_width / 24 - small_digit_font_width / 2, (9 * csh) / 2 + small_font_ascent / 2);
              g2.drawLine(x_pos_real + day_width / 24, 5 * csh, x_pos_real + day_width / 24, csn * csh);
              g2.drawString("2", x_pos_real + day_width * 2 / 24 - small_digit_font_width / 2, (9 * csh) / 2 + small_font_ascent / 2);
              g2.drawLine(x_pos_real + day_width * 2 / 24, 5 * csh, x_pos_real + day_width * 2 / 24, csn * csh);
            }
            else {
              // 23 h day
              g2.drawString("1", x_pos_real + day_width * 2 / 24 - small_digit_font_width / 2, (9 * csh) / 2 + small_font_ascent / 2);
              g2.drawLine(x_pos_real + day_width * 2 / 24, 5 * csh, x_pos_real + day_width * 2 / 24, csn * csh);
            }
          }
        }
        g2.setFont(f);
      }

      // lines between days
      g2.setColor(Color.black);
      if (i != 0 && day_width > 16) {
        g2.drawLine(x_pos, 2 * csh, x_pos, DateLookPanel.number_of_slots * csh);
      }
      i++;
    }

    // render horizontal lines
    g2.setColor(Color.black);
    for (int k = 1; k < DateLookPanel.number_of_slots + 1; k++) {
      g2.drawLine(0, csh * k, DateLookPanel.get_instance(null).getWidth(), csh * k);
    }
  }


  /**
   *  Determines default font (called after resizing of the DateLookPanel by this)
   */
  public void resized() {
    Graphics2D g2 = (Graphics2D) DateLookPanel.get_instance(null).getGraphics();
    day_width = DateLookPanel.get_instance(null).getWidth() * 24 / (int) DateLookPanel.get_instance(null).get_number_of_rendered_hours(); // not exact for DST-switches!
    space = DateLookPanel.slot_height / 4;

    f = new Font("SansSerif", Font.PLAIN, DateLookPanel.slot_height * 2 / 3);
    FontRenderContext context = g2.getFontRenderContext();
    Rectangle2D bounds = f.getStringBounds("0", context);
    digit_font_width = (int) bounds.getWidth();
    font_height = (int) bounds.getHeight();
    font_ascent = (int) -bounds.getY();
  }


  /**
   *  Gets the dayColour attribute of the CalendarRenderer object
   *
   * @param  g  gregorian calendar object
   * @return    The dayColor value
   */
  private Color getDayColour(GregorianCalendar g) {
    float c = (float) g.get(GregorianCalendar.DAY_OF_MONTH) / 31F;
    return new Color((int) (red_begin + c * (255 - red_begin)),
        (int) (green_begin + c * (255 - green_begin)),
        (int) (blue_begin + c * (255 - blue_begin)));
  }


  /**
   *  Gets the dayOfWeekColour attribute of the CalendarRenderer object
   *
   * @param  g  gregorian calendar object
   * @return    The dayOfWeekColor value
   */
  private Color getDayOfWeekColour(GregorianCalendar g) {
    if (g.get(GregorianCalendar.DAY_OF_WEEK) == Calendar.SATURDAY
         || g.get(GregorianCalendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
      return week_end_color;
    }
    return week_color;
  }


  /**
   *  Convert UTC to x position on DateLookPanel
   *
   * @param  l  UTC
   * @return    x position
   */
  private int UTC2x_pos(long l) {
    return (int) ((l - DateLookPanel.get_instance(null).get_first_rendered_hour_UTC_ms()) * DateLookPanel.get_instance(null).getWidth() /
        (DateLookPanel.get_instance(null).get_number_of_rendered_hours() * 60 * 60 * 1000));
  }
}

