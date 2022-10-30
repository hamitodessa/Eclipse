import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;


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
 *  Editor window for an event. All parameters of an event can be
 *  changed here. Will be created<br>
 *  - if a new event has been created by mouse dragging in the main
 *  window,<br>
 *  - if an existing event is clicked by left mouse,<br>
 *  - if an existing event has been dragged to another time or row,<br>
 *  - if an existing event has been copied by dragging + ctrl-key.
 */
public class EditorFrame extends JFrame {

  private JPanel contentPane;
  private EditorPanel editor_panel;


  /**
   *  Construct the frame
   *
   * @param  t                 event to be changed or has been changed by dragging
   * @param  ot                orignal event (clone, made before dragging)
   * @param  new_event   true - event is newly created by dragging<br>
   *                     false - event is not newly created.
   * @param  show_goto_button  true - show the Goto-button<br>
   *                           false - don't show Goto-button
   */
  public EditorFrame(Event t, Event ot, boolean new_event, boolean show_goto_button) {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      editor_panel = new EditorPanel(t, ot, this, new_event, show_goto_button);
      contentPane = (JPanel) this.getContentPane();
      setIconImage(Toolkit.getDefaultToolkit().createImage(EditorFrame.class.getResource("dl.png")));
      this.setSize(14 * DateLookPanel.slot_height + DateLookPanel.frame_decor_width,
          (9 * DateLookPanel.slot_height) + DateLookPanel.frame_decor_height);
      this.setTitle("Event Editor");
      contentPane.add(editor_panel);
      this.addKeyListener(editor_panel);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   *  Process window event
   *
   * @param  e  window event
   */
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      editor_panel.close_event();
    }
  }


  /**
   *  Delete descriptor editor frame
   */
  public void delete_description_editor_frame() {
    editor_panel.delete_description_editor_frame();
  }


  /**
   *  Delete colour frame
   *
   * @param  c  colour chosen be colour frame
   */
  public void delete_colour_frame(Color c) {
    editor_panel.delete_colour_frame(c);
    editor_panel.repaint();
  }


  /**
   *  Dispose
   */
  public void dispose() {
    if (editor_panel.my_description_editor_frame != null) {
      editor_panel.my_description_editor_frame.dispose();
    }
    if (editor_panel.my_colour_frame != null) {
      editor_panel.my_colour_frame.dispose();
    }
    super.dispose();
  }


  /**
   *  Panel containing all buttons and RComponents to modify the event
   */
  public static class EditorPanel extends RPanel {

    private Event event;    // the origianl event
    private Event event_before_modifed;    // a event-object that contains the data of the original
    private boolean new_event;
    private boolean show_goto_button;

    // All RComponents to show and changes entries of the event
    private RComponent bhour_rc; //Begin
    private RComponent bmin_rc;
    private RComponent bday_rc;
    private RComponent bmonth_rc;
    private RComponent byear_rc;
    
    private RComponent ehour_rc; //End
    private RComponent emin_rc;
    private RComponent eday_rc;
    private RComponent emonth_rc;
    private RComponent eyear_rc;
    
    private RComponent ahour_rc; //Alarm
    private RComponent a_on_off_rc;
    private RComponent amin_rc;
    private RComponent aday_rc;
    private RComponent amonth_rc;
    private RComponent ayear_rc;
    
    private RComponent period_multiplier_rc;
    private RComponent ctype_rc; //Cycle
    private RComponent ccount_rc;
    private RComponent til_date_rc;
    private RComponent c_pub_priv_rc; //Class

    private RTextField summary_text_field;

    private RButton descr_button;
    private RButton color_button;
    private RButton save_button;
    private RButton delete_button;
    private RButton cancel_button;
    private RButton goto_button;
    private RButton print_button;
    
    private ArrayList<RComponent> mouse_receiver_list = new ArrayList<RComponent>();    // all RComponents with mouse response
    private ArrayList<Renderer> renderer_list = new ArrayList<Renderer>();    // all other RComponents to be drawn
    private MouseListener click_beep;
    private KeyListener key_beep;
    private DescriptionEditorFrame my_description_editor_frame = null;
    private ColourFrame my_colour_frame = null;
    private StringBuffer description;
    private final static int rel_font_size = 48;


    /**
     *  Constructor for the EditorPanel object
     *
     * @param  t   event to be changed or has been changed by dragging
     * @param  ot  orignal event (clone, made before dragging)
     * @param  pw  parent window
     * @param  b   true - event is newly created by dragging<br>
     *      false - event is not newly created.
     * @param  s   true - show the Goto-button<br>
     *      false - don't show Goto-button
     */
    public EditorPanel(Event t, Event ot, Window pw, boolean b, boolean s) {
      super(pw, true);
      event = t;
      event_before_modifed = ot;
      new_event = b;
      show_goto_button = s;

      description = new StringBuffer(event.get_description());    // read description

      RComponent[] rc_array = new RComponent[5];
      rc_array = render_event_entry("Begin", 18, 40, t.get_begin_UTC_ms());
      bhour_rc = rc_array[0];
      bmin_rc = rc_array[1];
      bday_rc = rc_array[2];
      bmonth_rc = rc_array[3];
      byear_rc = rc_array[4];
      rc_array = render_event_entry("End", 18, 160, t.get_end_UTC_ms());
      ehour_rc = rc_array[0];
      emin_rc = rc_array[1];
      eday_rc = rc_array[2];
      emonth_rc = rc_array[3];
      eyear_rc = rc_array[4];
      rc_array = render_event_entry("", 18, 280, t.get_alarm_UTC_ms());
      ahour_rc = rc_array[0];
      amin_rc = rc_array[1];
      aday_rc = rc_array[2];
      amonth_rc = rc_array[3];
      ayear_rc = rc_array[4];

      //alarm active
      String[] sa = {"Alarm Off", "Alarm"};
      int alarm_active = 0;
      if (t.get_alarm_active()) {
        alarm_active = 1;
      }
      a_on_off_rc = new RComponent(this, bg_color, Color.orange, alarm_active, 1, 0, 1, sa, "", 18, 280, 230, rel_font_size);
      mouse_receiver_list.add(a_on_off_rc);

      //period_multiplier
      period_multiplier_rc = new RComponent(this, bg_color, Color.orange, t.get_period_multiplier(),
          9, 1, 1, null, "", 18, 400, 55, rel_font_size);
      mouse_receiver_list.add(period_multiplier_rc);

      //period
      String[] sc = {"once", "daily", "weekly", "monthly", "yearly"};
      ctype_rc = new RComponent(this, bg_color, Color.orange, t.get_period(),
          4, 0, 1, sc, "", 80, 400, 220, rel_font_size);
      mouse_receiver_list.add(ctype_rc);

      //number of periods
      ccount_rc = new RComponent(this, bg_color, Color.orange, t.get_number_of_periods(),
          999, 2, 1, null, " x", 306, 400, 155, rel_font_size);
      mouse_receiver_list.add(ccount_rc);

      //till .. field
      String[] sd = {"till "};
      til_date_rc = new RComponent(this, bg_color, bg_color, 0, 0, 0, 1, sd, "", 490, 400, 500, rel_font_size);

      //class-label
      String[] se = {""};
      renderer_list.add(new RComponent(this, bg_color, bg_color, 0, 0, 0, 0, se, "Class:", 18, 720, 180, rel_font_size));
      renderer_list.add(a_on_off_rc);

      //vcal_class-field
      String[] sf = {"Public", "Private"};
      c_pub_priv_rc = new RComponent(this, bg_color, Color.orange, t.get_vcal_class(),
          1, 0, 1, sf, "", 195, 720, 180, rel_font_size);
      mouse_receiver_list.add(c_pub_priv_rc);

      //text field
      summary_text_field = new RTextField(18, 540, 964, rel_font_size);
      summary_text_field.setBackground(Color.white);
      summary_text_field.setEditable(true);
      summary_text_field.setText(event.get_summary());
      summary_text_field.setToolTipText("Enter the event's summary.");
      this.add(summary_text_field);
      summary_text_field.addKeyListener(this);
      
      //buttons
      descr_button = new RButton(this, Color.gray, Color.orange, Color.red, "description", 410, 720, 375, rel_font_size);
      color_button = new RButton(this, event.get_renderer_color(), Color.orange, Color.red, "colour", 802, 720, 180, rel_font_size);
      save_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "save", 18, 860, 180, rel_font_size);
      cancel_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "cancel", 214, 860, 180, rel_font_size);
      delete_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "delete", 410, 860, 180, rel_font_size);
      goto_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "go to", 606, 860, 180, rel_font_size);
      print_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "print", 802, 860, 180, rel_font_size);

      click_beep = new ClickBeep();
      key_beep = new KeyBeep();
      calc_til_date();
    }


    /**
     *  Paint component
     *
     * @param  g  Graphics object
     */
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      bhour_rc.draw(g2);   // Begin
      bmin_rc.draw(g2);
      bday_rc.draw(g2);
      bmonth_rc.draw(g2);
      byear_rc.draw(g2);
      
      ehour_rc.draw(g2);   // End
      emin_rc.draw(g2);
      eday_rc.draw(g2);
      emonth_rc.draw(g2);
      eyear_rc.draw(g2);
      
      // Alarm On/Off
      if (a_on_off_rc.get_value() != 0) {  // alarm is active
        ahour_rc.draw(g2);    // Alarm hours
        amin_rc.draw(g2);     //       min
        aday_rc.draw(g2);     //       day
        amonth_rc.draw(g2);   //       month
        ayear_rc.draw(g2);    //       year
      }
      
      ctype_rc.draw(g2);   // Cycle
      
      if (ctype_rc.get_value() != Event.None) {
        // non-periodic event
        period_multiplier_rc.draw(g2);
        ccount_rc.draw(g2);
        til_date_rc.draw(g2);
        ((RComponent) renderer_list.get(0)).set_additional_string("1st Begin");
        ((RComponent) renderer_list.get(1)).set_additional_string("1st End");
        String[] sa = {"Alarm Off", "1st Alarm"};
        a_on_off_rc.set_string_aray(sa);
      }
      else {
        ((RComponent) renderer_list.get(0)).set_additional_string("Begin");
        ((RComponent) renderer_list.get(1)).set_additional_string("End");
        String[] sa = {"Alarm Off", "Alarm"};
        a_on_off_rc.set_string_aray(sa);
      }
      a_on_off_rc.draw(g2);       // Alarm on/off
      c_pub_priv_rc.draw(g2);     // Class

      descr_button.draw(g2);
      color_button.draw(g2);
      save_button.draw(g2);
      if (!new_event) {
        delete_button.draw(g2);
      }
      cancel_button.draw(g2);
      if (show_goto_button) {
        goto_button.draw(g2);
      }
      print_button.draw(g2);
      for (int i = 0; i < renderer_list.size(); i++) {
        // draw all labels
        ((Renderer) renderer_list.get(i)).draw(g2);
      }
    }


    /**
     *  Check for pressed key and handles it.<br>
     *  F1 - opens HelpFrame<br>
     *  ctrl-Q - saves database to file closes the frame<br>
     *  ctrl-S - saves database to file closes the frame<br>
     *  ctrl-C - cancels all changes of the event and closes the frame<br>
     *  ctrl-P - opens the PrinterDialog<br>
     *  ctrl-D - opens the DescriptionEditorFrame<br>
     *  ctrl-O - opens the ColourEditor<br>
     *
     * @param  e  key event
     */
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_F1) {
        HelpFrame.get_instance().help("Create_a_new_event");
      }
      else {
        if (e.getModifiers() == InputEvent.CTRL_MASK) {
          if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_S) {
            if (!this.write_event()) {
              this.repaint();
              EventMemory.get_instance(null).save(false);
              return;
            }
            this.close();
            return;
          }
          if (e.getKeyCode() == KeyEvent.VK_C) {
            if (event_before_modifed != null) {
              // restore original
              event_before_modifed.set_UID(event.get_UID());
              // write orig UID to original event
              EventMemory.get_instance(null).purge_event(event);
              EventMemory.get_instance(null).add_event(event_before_modifed);
              if (event_before_modifed.get_event_renderer() != null) {
                event_before_modifed.get_event_renderer().set_visible(true);
              }
            }
            if (new_event) {
              EventMemory.get_instance(null).purge_event(event);
            }
            this.close();
            return;
          }
          if (e.getKeyCode() == KeyEvent.VK_P) {
            if (this.write_event()) {
              new EventPrinter(event);
            }
            calc_til_date();
            this.repaint();
            return;
          }
          if (e.getKeyCode() == KeyEvent.VK_D) {
            open_description();
            return;
          }
          if (e.getKeyCode() == KeyEvent.VK_O) {
            open_colour_frame();
            return;
          }
        }
      }
    }


    /**
     *  Handle mouse click.<br>
     *  Check whether buttons/entries are hit and if true handle the action.<br>
     *  save button - save changes and close editor frame<br>
     *  delete button - close editor frame and delete event<br>
     *  cancel entry - discard changes and close editor frame.
     *
     * @param  e  mouse event
     */
    public void mouseClicked(MouseEvent e) {
      for (int i = 0; i < mouse_receiver_list.size(); i++) {
        ((RComponent) mouse_receiver_list.get(i)).mouse_clicked(e);
      }
      if (save_button.mouse_clicked(e)) {
        if (!this.write_event()) {
          this.repaint();
          return;
        }
        this.close();
        EventMemory.get_instance(null).save(false);
      }
      else if (delete_button.mouse_clicked(e)) {
        EventMemory.get_instance(null).delete_event(event);
        this.close();
        EventMemory.get_instance(null).save(false);
      }
      else if (cancel_button.mouse_clicked(e)) {
        if (event_before_modifed != null) {
          // restore original
          event_before_modifed.set_UID(event.get_UID());  // write orig UID to original event
          EventMemory.get_instance(null).purge_event(event);
          EventMemory.get_instance(null).add_event(event_before_modifed);
          if (event_before_modifed.get_event_renderer() != null) {
            event_before_modifed.get_event_renderer().set_visible(true);
          }
        }
        if (new_event && (event != null)) {
          EventMemory.get_instance(null).purge_event(event);
        }
        DateLookPanel.get_instance(null).changed();
        this.close();
      }
      else if (goto_button.mouse_clicked(e)) {
        if (this.write_event()) {
          GregorianCalendar gc = Converter.ms2gc(event.get_begin_UTC_ms());
          gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
          gc.set(GregorianCalendar.MINUTE, 0);
          DateLookPanel.get_instance(null).set_first_rendered_hour_UTC_ms(gc.getTime().getTime());
        }
        else {
          this.repaint();
        }
      }
      else if (color_button.mouse_clicked(e)) {
        open_colour_frame();
      }
      else if (descr_button.mouse_clicked(e)) {
        open_description();
      }
      else if (print_button.mouse_clicked(e)) {
        if (this.write_event()) {
          new EventPrinter(event);
        }
      }
      calc_til_date();
      this.repaint();
    }


    /**
     *  Handle mouse press.
     *
     * @param  e  mouse event
     */
    public void mousePressed(MouseEvent e) {
      for (int i = 0; i < mouse_receiver_list.size(); i++) {
        ((RComponent) mouse_receiver_list.get(i)).mouse_pressed(e);
      }
      descr_button.mouse_pressed(e);
      color_button.mouse_pressed(e);
      save_button.mouse_pressed(e);
      if (!new_event) {
        delete_button.mouse_pressed(e);
      }
      cancel_button.mouse_pressed(e);
      if (show_goto_button) {
        goto_button.mouse_pressed(e);
      }
      print_button.mouse_pressed(e);    // switch off selection of wrong fields
      ehour_rc.set_selected(false);     // End hours
      emin_rc.set_selected(false);      //     min
      eday_rc.set_selected(false);      //     day
      emonth_rc.set_selected(false);    //     month
      eyear_rc.set_selected(false);     //     year
      ahour_rc.set_selected(false);     // Alarm hours
      amin_rc.set_selected(false);      //       min
      aday_rc.set_selected(false);      //       day
      amonth_rc.set_selected(false);    //       month
      ayear_rc.set_selected(false);     //       year
      this.repaint();
    }


    /**
     *  Handle mouse release.
     *
     * @param  e  mouse event
     */
    public void mouseReleased(MouseEvent e) {
      descr_button.mouse_released(e);
      color_button.mouse_released(e);
      save_button.mouse_released(e);
      if (!new_event) {
        delete_button.mouse_released(e);
      }
      cancel_button.mouse_released(e);
      if (show_goto_button) {
        goto_button.mouse_released(e);
      }
      print_button.mouse_released(e);
      for (int i = 0; i < mouse_receiver_list.size(); i++) {
        ((RComponent) mouse_receiver_list.get(i)).mouse_released(e);
      }
      calc_til_date();
      this.repaint();
    }


    /**
     *  Handle mouse move.
     *
     * @param  e  mouse event
     */
    public void mouseMoved(MouseEvent e) {
      for (int i = 0; i < mouse_receiver_list.size(); i++) {
        ((RComponent) mouse_receiver_list.get(i)).mouse_over(e);
      }
      descr_button.mouse_over(e);
      color_button.mouse_over(e);
      save_button.mouse_over(e);
      if (!new_event) {
        delete_button.mouse_over(e);
      }
      cancel_button.mouse_over(e);
      if (show_goto_button) {
        goto_button.mouse_over(e);
      }
      print_button.mouse_over(e);
      this.repaint();
    }


    /**
     *  Handle mouse wheel move
     *
     * @param  e  mouse wheel event
     */
    public void mouseWheelMoved(MouseWheelEvent e) {
      // switch off selection of wrong fields
      ehour_rc.set_selected(false);     // End hours
      emin_rc.set_selected(false);      //     min
      eday_rc.set_selected(false);      //     day
      emonth_rc.set_selected(false);    //     month
      eyear_rc.set_selected(false);     //     year
      ahour_rc.set_selected(false);     // Alarm hours
      amin_rc.set_selected(false);      //       min
      aday_rc.set_selected(false);      //       day
      amonth_rc.set_selected(false);    //       month
      ayear_rc.set_selected(false);     //       year
      for (int i = 0; i < mouse_receiver_list.size(); i++) {
        ((RComponent) mouse_receiver_list.get(i)).mouse_wheel_rotate(e);
      }
      calc_til_date();
      this.repaint();
    }


    /**
     *  Delete description editor frame (and unlock key and mouse for own panel)
     */
    public void delete_description_editor_frame() {
      if (my_description_editor_frame != null) {
        my_description_editor_frame = null;
        summary_text_field.setEditable(true);
        summary_text_field.addKeyListener(this);
        this.removeMouseListener(click_beep);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.removeKeyListener(key_beep);
        this.addKeyListener(this);
      }
    }


    /**
     *  Delete colour editor frame (and unlock key and mouse for own panel)
     *
     * @param  c  colour
     */
    public void delete_colour_frame(Color c) {
      if (my_colour_frame != null) {
        my_colour_frame = null;
        if (c != null) {
          color_button.set_bg_colour(c);
          this.repaint();
        }
        summary_text_field.setEditable(true);
        summary_text_field.addKeyListener(this);
        this.removeMouseListener(click_beep);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.removeKeyListener(key_beep);
        this.addKeyListener(this);
      }
    }


    /**
     *  Handle closing of the editor frame,<br>
     *  close dependent frames,<br>
     *  restore original event's data or if new delete event
     */
    public void close_event() {
      event.set_my_editor_frame(null);
      if (my_description_editor_frame != null) {
        my_description_editor_frame.dispose();
      }
      if (my_colour_frame != null) {
        my_colour_frame.dispose();
      }
      if (event_before_modifed != null) {
        // restore original
        event_before_modifed.set_UID(event.get_UID());
        // write orig UID to original event
        EventMemory.get_instance(null).purge_event(event);
        EventMemory.get_instance(null).add_event(event_before_modifed);
        if (event_before_modifed.get_event_renderer() != null) {
          event_before_modifed.get_event_renderer().set_visible(true);
        }
      }
      if (new_event) {
        EventMemory.get_instance(null).purge_event(event);
      }
    }


    /**
     *  Handle closing of the editor frame,<br>
     *  close dependent frames and parent frame
     */
    public void close() {
      event.set_my_editor_frame(null);
      if (my_description_editor_frame != null) {
        my_description_editor_frame.dispose();
      }
      if (my_colour_frame != null) {
        my_colour_frame.dispose();
      }
      parent_window.dispose();
    }


    /**
     *  Create a set of RComponents for hour/minute/day/month/year...
     *
     * @param  title  title sting of that set (e.g. "Begin")
     * @param  x      x position on panel (range 0 - 1000)
     * @param  y      y position on panel (range 0 - 1000)
     * @param  ms     start value for that set in UTC
     * @return        an array of RComponents of that set
     */
    private RComponent[] render_event_entry(String title, int x, int y, long ms) {

      RComponent[] rc_array = new RComponent[5];

      //Titel
      String[] st = {""};
      renderer_list.add(new RComponent(this, bg_color, bg_color, 0,
          0, 0, 1, st, title, x, y, 250, rel_font_size));

      //hours
      rc_array[0] = new RComponent(this, bg_color, Color.orange, Converter.ms2hour(ms),
          23, 0, 1, null, ":", x + 288, y, 86, rel_font_size);
      mouse_receiver_list.add(rc_array[0]);

      //minute
      rc_array[1] = new RComponent(this, bg_color, Color.orange, Converter.ms2min(ms),
          55, 0, 5, null, "", x + 374, y, 86, rel_font_size);
      mouse_receiver_list.add(rc_array[1]);

      //day of month
      rc_array[2] = new RComponent(this, bg_color, Color.orange, Converter.ms2day(ms),
          31, 1, 1, null, ".", x + 468, y, 86, rel_font_size);
      mouse_receiver_list.add(rc_array[2]);

      //month
      rc_array[3] = new RComponent(this, bg_color, Color.orange, Converter.ms2month(ms),
          11, 0, 1, Converter.longMonthNames, "", x + 554, y, 270, rel_font_size);
      mouse_receiver_list.add(rc_array[3]);

      //year
      rc_array[4] = new RComponent(this, bg_color, Color.orange, Converter.ms2year(ms),
          2500, 1, 1, null, "", x + 824, y, 140, rel_font_size);
      mouse_receiver_list.add(rc_array[4]);

      return rc_array;
    }


    /**
     *  Write all modified values back to the event object
     *
     * @return    true - if values are okay,<br>
     *            false - if values violate rules, then values are not written.
     */
    private boolean write_event() {
      // writes values back to event-object
      if ((event_before_modifed == null) && !new_event) { // store old values first
        event_before_modifed = event.clone2();
      }

      event.set_description(description.toString());
      // write description
      long begin = Converter.hmdmy2ms(
          bhour_rc.get_value(),       // Begin hour
          bmin_rc.get_value(),        //       min
          bday_rc.get_value(),        //       day
          bmonth_rc.get_value(),      //       month
          byear_rc.get_value());      //       year
      long end = Converter.hmdmy2ms(
          ehour_rc.get_value(),       // End hour
          emin_rc.get_value(),        //     min
          eday_rc.get_value(),        //     day
          emonth_rc.get_value(),      //     month
          eyear_rc.get_value());      //     year
      long alarm = Converter.hmdmy2ms(
          ahour_rc.get_value(),       // Alarm hour
          amin_rc.get_value(),        //       min
          aday_rc.get_value(),        //       day
          amonth_rc.get_value(),      //       month
          ayear_rc.get_value());      //       year
      boolean ret = true;
      if (end < begin) {
        ehour_rc.set_selected(true);
        emin_rc.set_selected(true);
        eday_rc.set_selected(true);
        emonth_rc.set_selected(true);
        eyear_rc.set_selected(true);
        ret = false;
      }
      if (alarm > begin && a_on_off_rc.get_value() == 1) {
        ahour_rc.set_selected(true);
        amin_rc.set_selected(true);
        aday_rc.set_selected(true);
        amonth_rc.set_selected(true);
        ayear_rc.set_selected(true);
        ret = false;
      }
      if (ctype_rc.get_value() != Event.None) {
        // is cyclic
        if (end > Converter.UTCplusPeriod2UTC(begin, ctype_rc.get_value(), 1, period_multiplier_rc.get_value())) {
          ehour_rc.set_selected(true);
          emin_rc.set_selected(true);
          eday_rc.set_selected(true);
          emonth_rc.set_selected(true);
          eyear_rc.set_selected(true);
          ret = false;
        }
      }
      if (!ret) {
        return false;
      }
      event.set_begin_UTC_ms(begin);
      event.set_period_multiplier(period_multiplier_rc.get_value());
      event.set_period(ctype_rc.get_value());
      // Period
      event.set_number_of_periods(ccount_rc.get_value());
      event.set_end_UTC_ms(end);
      event.set_alarm_UTC_ms(alarm);
      event.set_alarm_counter_to_next_after_now();

      boolean b = true;
      if (a_on_off_rc.get_value() == 0) {
        b = false;
      }
      event.set_alarm_active(b);
      event.set_vcal_class(c_pub_priv_rc.get_value());
      event.set_summary(summary_text_field.getText());
      event.set_renderer_color(color_button.get_bg_colour());
      event.changed();    // to inform all renderer
      return true;
    }


    /**
     *  Calculate the last day of the occurence of a cyclic event
     */
    private void calc_til_date() {
      int hour = bhour_rc.get_value();
      int minute = bmin_rc.get_value();
      int day = bday_rc.get_value();
      int month = bmonth_rc.get_value();
      int year = byear_rc.get_value();
      int period = ctype_rc.get_value();
      int period_multiplier = period_multiplier_rc.get_value();
      int number_of_periods = ccount_rc.get_value() - 1;
      if (period == Event.Yearly) {
        year = year + period_multiplier * number_of_periods;
      }
      else if (period == Event.Monthly) {
        month = month + period_multiplier * number_of_periods;
      }
      else if (period == Event.Weekly) {
        day = day + period_multiplier * 7 * number_of_periods;
      }
      else if (period == Event.Daily) {
        day = day + period_multiplier * number_of_periods;
      }
      til_date_rc.set_additional_string(Converter.ms2dmy(Converter.hmdmy2ms(hour, minute, day, month, year)));
    }


    /**
     *  Open descriptor editor
     */
    private void open_description() {
      if (my_description_editor_frame == null) {
        this.removeMouseListener(this);
        this.addMouseListener(click_beep);
        this.removeMouseMotionListener(this);
        this.removeMouseWheelListener(this);
        parent_window.removeKeyListener(this);
        parent_window.addKeyListener(key_beep);
        summary_text_field.removeKeyListener(this);
        summary_text_field.addKeyListener(key_beep);
        summary_text_field.setEditable(false);
        my_description_editor_frame = new DescriptionEditorFrame((EditorFrame) parent_window, description,
            summary_text_field.getText(), color_button.get_bg_colour());
        my_description_editor_frame.setLocation((int) this.getLocationOnScreen().getX() + 20,
            (int) this.getLocationOnScreen().getY() + 20);
        my_description_editor_frame.setVisible(true);
      }
    }


    /**
     *  Open colour frame
     */
    private void open_colour_frame() {
      if (my_colour_frame == null) {
        this.removeMouseListener(this);
        this.addMouseListener(click_beep);
        this.removeMouseMotionListener(this);
        this.removeMouseWheelListener(this);
        parent_window.removeKeyListener(this);
        parent_window.addKeyListener(key_beep);
        summary_text_field.removeKeyListener(this);
        summary_text_field.addKeyListener(key_beep);
        summary_text_field.setEditable(false);
        my_colour_frame = new ColourFrame((EditorFrame) parent_window, color_button.get_bg_colour());
        my_colour_frame.setLocation((int) this.getLocationOnScreen().getX() + 20,
            (int) this.getLocationOnScreen().getY() + 20);
        my_colour_frame.setVisible(true);
      }
    }
  }


  /**
   *  Beep on all mouse pressings
   */
  private static class ClickBeep extends MouseAdapter {
    /**
     *  Beep on all mouse pressings
     *
     * @param  e  mouse event
     */
    public void mousePressed(MouseEvent e) {
      Toolkit.getDefaultToolkit().beep();
    }
  }


  /**
   *  Beep on all key strokes
   */
  private static class KeyBeep implements KeyListener {
    /**
     *  Beep on all key events
     *
     * @param  e  key event
     */
    public void keyPressed(KeyEvent e) {
      Toolkit.getDefaultToolkit().beep();
    }


    /**
     *  Dummy
     *
     * @param  e  key event
     */
    public void keyReleased(KeyEvent e) {
    }


    /**
     *  Dummy
     *
     * @param  e  key event
     */
    public void keyTyped(KeyEvent e) {
    }
  }
}

