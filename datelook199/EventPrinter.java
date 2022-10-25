import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.print.*;
import java.awt.geom.*;
import java.awt.*;


/*
 *  Title:        DateLook
 *  Copyright:    Copyright (c) 2005
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
 *  Print an event.
 */
public class EventPrinter implements Printable {

  private Event event;
  private ArrayList items_to_print;  // list of items to be printed
  private Font font;
  private Font big_font;
  private Font small_font;
  private int font_height;
  private int big_font_height;
  private int small_font_height;


  /**
   *  Constructor for the EventPrinter object
   *
   * @param  t  event
   */
  EventPrinter(Event t) {
    event = t;
    PrinterJob print_job = PrinterJob.getPrinterJob();
    PageFormat page_format = print_job.defaultPage();
    page_format.setOrientation(PageFormat.PORTRAIT);
    print_job.setPrintable(this, page_format);
    if (print_job.printDialog()) {
      try {
        print_job.print();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }


  /**
   *  Print
   *
   * @param  g                     graphics object
   * @param  pf                    page format
   * @param  p                     page offset
   * @return                       Printable.PAGE_EXISTS or Printable.NO_SUCH_PAGE
   * @exception  PrinterException  -
   */
  public int print(Graphics g, PageFormat pf, int p) throws PrinterException {
    Graphics2D g2 = (Graphics2D) g;
    int width = 1050;    // page width
    int height = 1480;    // page height
    int page_offset = p * height;

    // set coordinates and scale: x_range = 0 to panel-width, y_range = 0 to 30 * slot_height
    g2.translate(pf.getImageableX(), pf.getImageableY());
    g2.scale(pf.getImageableWidth() / width, pf.getImageableHeight() / height);

    if (p == 0) {
      // if first page put all to be printed into an arraylist
      items_to_print = new ArrayList();

      // determine fonts and geometry
      font = new Font("SansSerif", Font.PLAIN, 24);
      big_font = new Font("SansSerif", Font.PLAIN, 40);
      small_font = new Font("SansSerif", Font.PLAIN, 22);
      Rectangle2D bounds = font.getStringBounds("0", g2.getFontRenderContext());
      font_height = (int) bounds.getHeight();
      bounds = big_font.getStringBounds("0", g2.getFontRenderContext());
      big_font_height = (int) bounds.getHeight();
      bounds = small_font.getStringBounds("0", g2.getFontRenderContext());
      small_font_height = (int) bounds.getHeight();

      // generate print items for all pages and put them to the "items_to_print"-list
      // summary
      int y = 0;
      ArrayList summary_lines = line_cutter(event.get_summary(), big_font, width, g2);
      for (int i = 0; i < summary_lines.size(); i++) {
        y = y + big_font_height;
        items_to_print.add(new PrintItem((String) summary_lines.get(i), big_font, 0, y, big_font_height));
      }
      y = y + big_font_height / 3;
      g2.drawLine(0, y, width, y);
      y = y + font_height;
      // begin, end, alarm ...
      if (event.get_period() == Event.None) {
        items_to_print.add(new PrintItem("Begin:", font, 0, y, font_height));
      }
      else {
        items_to_print.add(new PrintItem("1st Begin:", font, 0, y, font_height));
      }
      items_to_print.add(new PrintItem(Converter.ms2hm(event.get_begin_UTC_ms()), font, 180, y, font_height));
      items_to_print.add(new PrintItem(Converter.ms2dmyl(event.get_begin_UTC_ms()), font, 360, y, font_height));
      y = y + font_height;
      if (event.get_period() == Event.None) {
        items_to_print.add(new PrintItem("End:", font, 0, y, font_height));
      }
      else {
        items_to_print.add(new PrintItem("1st End:", font, 0, y, font_height));
      }
      items_to_print.add(new PrintItem(Converter.ms2hm(event.get_end_UTC_ms()), font, 180, y, font_height));
      items_to_print.add(new PrintItem(Converter.ms2dmyl(event.get_end_UTC_ms()), font, 360, y, font_height));
      y = y + font_height;
      
      String alarm_label;
      if (event.get_period() == Event.None) {
        alarm_label = "Alarm:"; 
      }
      else {
        alarm_label = "1st Alarm:"; 
      }
      if (event.get_alarm_active()) {
        items_to_print.add(new PrintItem(alarm_label, font, 0, y, font_height));
        items_to_print.add(new PrintItem(Converter.ms2hm(event.get_alarm_UTC_ms()), font, 180, y, font_height));
        items_to_print.add(new PrintItem(Converter.ms2dmyl(event.get_alarm_UTC_ms()), font, 360, y, font_height));
      }
      else {
        items_to_print.add(new PrintItem("Alarm Off", font, 0, y, font_height));
      }
      y = y + font_height;
      items_to_print.add(new PrintItem(event.get_period_as_string(), font, 0, y, font_height));
      if (event.get_period() != Event.None) {
        items_to_print.add(new PrintItem(new Integer(
            event.get_number_of_periods()).toString() + " x till ", font, 180, y, font_height));
        items_to_print.add(new PrintItem(Converter.ms2dmyl(Converter.UTCplusPeriod2UTC(event.get_begin_UTC_ms(),
            event.get_period(), event.get_number_of_periods() - 1, event.get_period_multiplier())), font, 360, y, font_height));
      }
      y = y + font_height / 3;
      g2.drawLine(0, (int) y, width, (int) y);
      y = y + 2 * small_font_height;

      // print description
      ArrayList lines = line_cutter(event.get_description(), small_font, width, g2);
      for (int i = 0; i < lines.size(); i++) {
        items_to_print.add(new PrintItem((String) lines.get(i), small_font, 0, y, small_font_height));
        y = y + small_font_height;
      }
    }

    // here printing really starts
    g2.setColor(Color.black);
    int return_value = Printable.NO_SUCH_PAGE;
    for (int k = 0; k < items_to_print.size(); k++) {
      PrintItem pi = (PrintItem) items_to_print.get(k);
      if (pi.get_y() > page_offset & pi.get_y() - pi.get_height() < page_offset) {
        // item pi is on page border therefore shift pi and all following items down
        int shifter = page_offset - pi.get_y() + pi.get_height();
        for (int l = k; l < items_to_print.size(); l++) {
          ((PrintItem) items_to_print.get(l)).add_y(shifter);
        }
      }
      if (pi.get_y() > page_offset & pi.get_y() <= page_offset + height) {
        pi.print_(g2, page_offset);
        return_value = Printable.PAGE_EXISTS;
      }
    }
    return return_value;
  }


  /**
   *  Cut the String s into lines that fit into page width and puts the lines as string into the ArrayList
   *
   * @param  s           string containing unformatted text
   * @param  font        used font
   * @param  page_width  page width
   * @param  g2          graphics object
   * @return             list of cutted lines
   */
  private ArrayList line_cutter(String s, Font font, int page_width, Graphics2D g2) {
    StringBuffer sb = new StringBuffer(s);
    ArrayList line_list = new ArrayList();   // stores all lines as String
    int i;
    StringBuffer line;
    int rest_length = sb.length();
    while (rest_length > 0) {
      line = new StringBuffer("");   // start with empty line
      int last_blank_pos = -1;
      for (i = 0; i < rest_length; i++) {
        char c = sb.charAt(i);
        if (c != '\n' & c != '\r') {
          line.append(sb.charAt(i));
        }
        if (c == '\n') {
          i++;
          break;
        }
        if (c == ' ') {
          last_blank_pos = i;
        }
        if (page_width < (int) font.getStringBounds(line + "0", g2.getFontRenderContext()).getWidth()) {
          if (last_blank_pos != -1) {
            line = new StringBuffer(line.substring(0, last_blank_pos + 1));
            i = last_blank_pos;
          }
          i++;
          break;
        }
      }
      sb = sb.delete(0, i);  // remove line from here
      rest_length = sb.length();
      line_list.add(line.toString());
    }
    return line_list;
  }


  /**
   *  This is a String to be printed at a certain position with a certain font
   */
  public static class PrintItem {
    private String print_string;
    private Font font;
    private int x_pos;
    private int y_pos;    // y-position on an endless paper
    private int height;   // height of a line in that font

    /**
     *  Constructor for the PrintItem object
     *
     * @param  s  string
     * @param  f  font
     * @param  x  x-coordinate
     * @param  y  y-coordinate
     * @param  h  height of a line in that font
     */
    PrintItem(String s, Font f, int x, int y, int h) {
      print_string = s;
      font = f;
      x_pos = x;
      y_pos = y;
      height = h;
    }


    /**
     *  Gets the y attribute of the PrintItem object.
     *
     * @return    The y value
     */
    public int get_y() {
      return y_pos;
    }


    /**
     *  Gets the height attribute of the PrintItem object.
     *
     * @return    The height value
     */
    public int get_height() {
      return height;
    }


    /**
     *  Add value to the y-coordinate.
     *
     * @param  a  value to be added
     */
    public void add_y(int a) {
      y_pos = y_pos + a;
    }


    /**
     *  Print
     *
     * @param  g2        graphics object
     * @param  y_offset  y offset
     */
    private void print_(Graphics2D g2, int y_offset) {
      g2.setFont(font);
      g2.drawString(print_string, x_pos, y_pos - y_offset);
    }
  }
}

