package OBS_2025;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.nio.*;
import java.net.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.geom.*;
import javax.swing.table.*;


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
 *  Frame with a table of all events and some buttons for searching, import, export, ...
 */
public class EventTableFrame extends JFrame {

  private JPanel contentPane;
  private JScrollPane scrollPane;
  private JSplitPane split_pane;
  private SearchPanel search_panel;

  private EventTableModel event_table_model;
  private JTable event_table;
  private EventTableFrame event_table_frame;

  private int last_selected_row = -1;

  private boolean close_button_locked = false;


  /**
   *  Construct the frame
   *
   */
  public EventTableFrame() {
    event_table_frame = this;
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      event_table_model = new EventTableModel();
      event_table = new JTable(event_table_model);
      contentPane = (JPanel) this.getContentPane();
      setIconImage(Toolkit.getDefaultToolkit().createImage(EventTableFrame.class.getResource("dl.png")));
      this.setSize(new Dimension(32 * DateLookPanel.slot_height + DateLookPanel.frame_decor_width,
          15 * DateLookPanel.slot_height + DateLookPanel.frame_decor_height));
      this.setTitle("Event Manager");
      int h = DateLookPanel.slot_height;
      event_table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
      event_table.getColumnModel().getColumn(0).setPreferredWidth(h * 5);
      event_table.getColumnModel().getColumn(1).setPreferredWidth(h * 3);
      event_table.getColumnModel().getColumn(2).setPreferredWidth(h * 3);
      event_table.getColumnModel().getColumn(3).setPreferredWidth(h * 7 / 2);
      event_table.getColumnModel().getColumn(4).setPreferredWidth(h * 2);
      event_table.getColumnModel().getColumn(0).setMaxWidth(h * 8);
      event_table.getColumnModel().getColumn(1).setMaxWidth(h * 8);
      event_table.getColumnModel().getColumn(2).setMaxWidth(h * 8);
      event_table.getColumnModel().getColumn(3).setMaxWidth(h * 8);
      event_table.getColumnModel().getColumn(4).setMaxWidth(h * 8);
      event_table.setRowHeight(h);
      event_table.setIntercellSpacing(new Dimension(h / 4, h / 4));
      event_table.setFont(new Font("SansSerif", Font.PLAIN, h * 2 / 3));
      event_table.setGridColor(Color.gray);
      event_table.setColumnSelectionAllowed(false);
      event_table.setRowSelectionAllowed(true);
      event_table.getSelectionModel().setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      event_table.getSelectionModel().addListSelectionListener(
        new ListSelectionListener() {
          public void valueChanged(ListSelectionEvent e) {
            search_panel.reset();
          }
        });
      MouseListener listener[] = event_table.getMouseListeners();
      for (int i = 0; i < listener.length; i++) {
        event_table.removeMouseListener(listener[i]);
      }

      event_table.addMouseListener(
        new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
            event_table_frame.table_clicked(e);
          }
        });
      search_panel = new SearchPanel(this, event_table, event_table_model);

      event_table.addKeyListener(search_panel);

      scrollPane = new JScrollPane(event_table);
      split_pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, search_panel, scrollPane);
      split_pane.setResizeWeight(0);
      split_pane.setDividerSize(4);
      split_pane.setDividerLocation((int) DateLookPanel.slot_height * 3);
      contentPane.add(split_pane);
      this.addKeyListener(search_panel);
      //srcoll to bottom
      event_table.scrollRectToVisible(event_table.getCellRect(event_table.getRowCount() - 1, 3, true));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   *  Process window event.<br>
   *  In case of a window closing-event and if the close button is not locked the window will be closed.
   *
   * @param  e  window event
   */
  protected void processWindowEvent(WindowEvent e) {
    if (!close_button_locked) {
      super.processWindowEvent(e);
      if (e.getID() == WindowEvent.WINDOW_CLOSING) {
        EventMemory.get_instance(null).set_event_table_frame(null);
      }
    }
  }


  /**
   *  Set "close button locked"-flag
   *
   * @param  b  true - "close button" is locked<br>
   *            false - "close button" is unlocked
   */
  public void set_close_button_locked(boolean b) {
    close_button_locked = b;
  }


  /**
   *  Changed, indicates the at least one event has been changed.
   */
  public void changed() {
    event_table.tableChanged(new TableModelEvent(event_table_model));
    search_panel.reset();
    super.repaint();
  }


  /**
   *  Indicates that the table has been clicked.<br>
   *  Handles the selection/deselection of table rows.
   *
   * @param  e  mouse event
   */
  public void table_clicked(MouseEvent e) {
    int row = event_table.rowAtPoint(new Point(e.getX(), e.getY()));
    if (row > -1) {
      // existing row matched
      if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
        // right mouse button
        int[] sel_rows;
        if (!event_table.isRowSelected(row)) {
          sel_rows = new int[]{row};
        }
        else {
          sel_rows = event_table.getSelectedRows();
        }
        for (int i = 0; i < sel_rows.length; i++) {
          Event t = EventMemory.get_instance(null).get_event(sel_rows[i]);
          if (t.get_my_editor_frame() == null) {
            EditorFrame ed = new EditorFrame(t, null, false, true);
            t.set_my_editor_frame(ed);
            ed.setLocation((int) event_table.getLocationOnScreen().getX() + e.getX() + 10 * i,
                (int) event_table.getLocationOnScreen().getY() + e.getY() + 10 * i);
            ed.setVisible(true);
          }
        }
      }
      else if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
        // left mouse button , this handling is needed only for MAC OS X
        if ((e.getModifiers() & InputEvent.CTRL_MASK) != 0) {
          // control button pressed simultaniously
          if (event_table.isRowSelected(row)) {
            event_table.removeRowSelectionInterval(row, row);
            if (row == last_selected_row) {
              last_selected_row = -1;
            }
          }
          else {
            event_table.addRowSelectionInterval(row, row);
            last_selected_row = row;
          }
        }
        else if ((e.getModifiers() & InputEvent.SHIFT_MASK) != 0) {
          // shift button pressed simultaniously
          if (last_selected_row != -1) {
            event_table.addRowSelectionInterval(row, last_selected_row);
          }
          else {
            event_table.addRowSelectionInterval(row, row);
            last_selected_row = row;
          }
        }
        else {
          // no additional modifier button pressed
          event_table.clearSelection();
          event_table.addRowSelectionInterval(row, row);
          last_selected_row = row;
        }
      }
    }
  }


  /**
   *  Sets the last_selected_row attribute of the EventTableFrame object
   *
   * @param  i  The new last_selected_row value
   */
  public void set_last_selected_row(int i) {
    last_selected_row = i;
  }


  /**
   *  The panel contains some fields and buttons to control searching, import/export and sync.
   */
  public static class SearchPanel extends RPanel {

    private JTable event_table;
    private EventTableModel event_table_model;
    private SearchPanel me;

    private RButton search_button;
    private RButton export_button;
    private RButton import_button;
    private RButton sync_button;
    private RButton delete_button;
    private RButton delete_sure_button;
    private RButton undelete_button;
    private RButton undelete_sure_button;
    private RButton purgedb_button;
    private RButton purgedb_sure_button;
    private RButton close_button;
    private RTextField search_text_field;
    private String result_text = "";
    private javax.swing.Timer button_timer;

    private boolean show_delete_sure_button = false;
    private boolean show_undelete_sure_button = false;
    private boolean show_purgedb_sure_button = false;


    /**
     *  Constructor for the SearchPanel object
     *
     * @param  pw   parent window
     * @param  tt   table of all events
     * @param  ttm  table model of tt
     */
    public SearchPanel(Window pw, JTable tt, EventTableModel ttm) {
      super(pw, false);
      me = this;
      event_table = tt;
      event_table_model = ttm;
      search_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "search", 460, 150, 80, 21);
      export_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "export", 10, 600, 80, 21);
      import_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "import", 100, 600, 80, 21);
      sync_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "sync", 190, 600, 80, 21);
      delete_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "delete", 280, 600, 80, 21);
      delete_sure_button = new RButton(this, Color.blue, Color.red, Color.red, "sure?", 280, 600, 80, 21);
      undelete_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "undelete", 370, 600, 80, 21);
      undelete_sure_button = new RButton(this, Color.blue, Color.red, Color.red, "sure?", 370, 600, 80, 21);
      purgedb_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "purge", 460, 600, 80, 21);
      purgedb_sure_button = new RButton(this, Color.blue, Color.red, Color.red, "sure?", 460, 600, 80, 21);
      close_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "close", 910, 150, 80, 21);
      search_text_field = new RTextField(10, 130, 440, 21);
      search_text_field.setBackground(Color.white);
      search_text_field.setEditable(true);
      search_text_field.setToolTipText("Enter one or more search strings separated by blanks or encapsulated by quotation.");
      search_text_field.addKeyListener(this);
      search_text_field.addKeyListener(
        new KeyAdapter() {
          public void KeyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              search();
            }
          }
        });
      this.add(search_text_field);
      button_timer = new javax.swing.Timer(3000,
        new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            show_delete_sure_button = false;
            show_undelete_sure_button = false;
            show_purgedb_sure_button = false;
            result_text = "";
            me.repaint();
            button_timer.stop();
          }
        });
    }


    /**
     *  Paint component
     *
     * @param  g  Graphics object
     */
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Font font = new Font("SansSerif", Font.PLAIN, 21 * this.getWidth() / 1000);
      Rectangle2D bounds = font.getStringBounds("0", g2.getFontRenderContext());
      g2.setColor(Color.red);
      g2.setFont(font);
      g2.drawString(result_text, this.getWidth() * 56 / 100, this.getHeight() * 6 / 10 - 2 + (int) bounds.getHeight());
      search_button.draw(g2);
      export_button.draw(g2);
      import_button.draw(g2);
      sync_button.draw(g2);
      if (show_delete_sure_button) {
        delete_sure_button.draw(g2);
      }
      else {
        delete_button.draw(g2);
      }
      if (show_undelete_sure_button) {
        undelete_sure_button.draw(g2);
      }
      else {
        undelete_button.draw(g2);
      }
      if (show_purgedb_sure_button) {
        purgedb_sure_button.draw(g2);
      }
      else {
        purgedb_button.draw(g2);
      }
      close_button.draw(g2);
    }


    /**
     *  Check for pressed key and handles it.<br>
     *  F1 - opens HelpFrame<br>
     *  ctrl-Q - closes the frame<br>
     *  ctrl-C - closes the frame<br>
     *  ctrl-S - opens SyncDialog<br>
     *  ctrl-E - opens FileChooser for export<br>
     *  ctrl-I - opens FileChooser for import
     *
     * @param  e  key event
     */
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_F1) {
        HelpFrame.get_instance().help("Search_for_events");
      }
      else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        if (search_text_field.hasFocus()) {
          search();
          return;
        }
      }
      else {
        if ((e.getModifiers() & InputEvent.CTRL_MASK) != 0) {
          if (e.getKeyCode() == KeyEvent.VK_Q) {
            EventMemory.get_instance(null).set_event_table_frame(null);
            parent_window.dispose();
            return;
          }
          if (e.getKeyCode() == KeyEvent.VK_C) {
            EventMemory.get_instance(null).set_event_table_frame(null);
            parent_window.dispose();
            return;
          }
          if (e.getKeyCode() == KeyEvent.VK_S) {
            sync();
            return;
          }
          if (e.getKeyCode() == KeyEvent.VK_E) {
            export();
            return;
          }
          if (e.getKeyCode() == KeyEvent.VK_I) {
            import_();
            return;
          }
        }
      }
      show_delete_sure_button = false;
      show_purgedb_sure_button = false;
      button_timer.stop();
      result_text = "";
      me.repaint();
    }


    /**
     *  Handle mouse click.<br>
     *  Check whether buttons are hit and if true handle the action.<br>
     *  close button - close EventTableFrame<br>
     *  search button - start searching<br>
     *  export button - open FileChooserDialog<br>
     *  import button - open FileChooserDialog<br>
     *  sync button - open SyncDialog<br>
     *  delete/sure button - delete highlighted events
     *
     * @param  e  mouse event
     */
    public void mouseClicked(MouseEvent e) {
      if (close_button.mouse_clicked(e)) {
        EventMemory.get_instance(null).set_event_table_frame(null);
        parent_window.dispose();
      }
      else if (search_button.mouse_clicked(e)) {
        search();
        return;
      }
      else if (export_button.mouse_clicked(e)) {
        export();
        return;
      }
      else if (import_button.mouse_clicked(e)) {
        import_();
        return;
      }
      else if (sync_button.mouse_clicked(e)) {
        sync();
        return;
      }
      else if (!show_purgedb_sure_button && purgedb_button.mouse_clicked(e)) {
        this.reset();
        event_table.clearSelection();
        button_timer.start();
        show_purgedb_sure_button = true;
        result_text = "Do you want to purge " + Integer.toString(EventMemory.get_instance(null).get_deleted_size()) + " deleted events?";
        return;
      }
      else if (show_purgedb_sure_button && purgedb_sure_button.mouse_clicked(e)) {
        button_timer.stop();
        show_purgedb_sure_button = false;
        purgedb();
        return;
      }
      else if (!show_undelete_sure_button && undelete_button.mouse_clicked(e)) {
        this.reset();
        event_table.clearSelection();
        button_timer.start();
        show_undelete_sure_button = true;
        result_text = "Do you want to undeleted " + Integer.toString(EventMemory.get_instance(null).get_deleted_size()) + " deleted events?";
        return;
      }
      else if (show_undelete_sure_button && undelete_sure_button.mouse_clicked(e)) {
        button_timer.stop();
        show_undelete_sure_button = false;
        undelete();
        return;
      }
      else if (!show_delete_sure_button && delete_button.mouse_clicked(e)) {
        show_purgedb_sure_button = false;
        show_undelete_sure_button = false;
        if (event_table.getSelectedRows().length > 0) {
          button_timer.start();
          show_delete_sure_button = true;
          result_text = "Do you want to delete " + Integer.toString(event_table.getSelectedRows().length) + " event(s)?";
        }
        else {
          result_text = "first select event(s) for deletetion!";
        }
        this.repaint();
        return;
      }
      else if (show_delete_sure_button && delete_sure_button.mouse_clicked(e)) {
        button_timer.stop();
        show_delete_sure_button = false;
        int[] sel_rows = event_table.getSelectedRows();
        // array of events to delete is nessesary because of
        // the row indexes are wrong after first delete because table is rebuild.
        Event[] events_to_delete = new Event[sel_rows.length];
        for (int i = 0; i < sel_rows.length; i++) {
          events_to_delete[i] = EventMemory.get_instance(null).get_event(sel_rows[i]);
        }
        for (int i = 0; i < events_to_delete.length; i++) {
          EventMemory.get_instance(null).delete_event(events_to_delete[i]);
        }
        result_text = Integer.toString(sel_rows.length) + " event(s) deleted!";
        EventMemory.get_instance(null).save(false);
      }
    }


    /**
     *  Handle "mouse pressed" event.
     *
     * @param  e  mouse event
     */
    public void mousePressed(MouseEvent e) {
      search_button.mouse_pressed(e);
      import_button.mouse_pressed(e);
      export_button.mouse_pressed(e);
      sync_button.mouse_pressed(e);
      delete_button.mouse_pressed(e);
      delete_sure_button.mouse_pressed(e);
      undelete_button.mouse_pressed(e);
      undelete_sure_button.mouse_pressed(e);
      purgedb_button.mouse_pressed(e);
      purgedb_sure_button.mouse_pressed(e);
      close_button.mouse_pressed(e);
      this.repaint();
    }


    /**
     *  Handle "mouse release" event.
     *
     * @param  e  mouse event
     */
    public void mouseReleased(MouseEvent e) {
      search_button.mouse_released(e);
      import_button.mouse_released(e);
      export_button.mouse_released(e);
      sync_button.mouse_released(e);
      delete_button.mouse_released(e);
      delete_sure_button.mouse_released(e);
      undelete_button.mouse_released(e);
      undelete_sure_button.mouse_released(e);
      purgedb_button.mouse_released(e);
      purgedb_sure_button.mouse_released(e);
      close_button.mouse_released(e);
      this.repaint();
    }


    /**
     *  Handle "mouse moved" event.
     *
     * @param  e  mouse event
     */
    public void mouseMoved(MouseEvent e) {
      search_button.mouse_over(e);
      import_button.mouse_over(e);
      export_button.mouse_over(e);
      sync_button.mouse_over(e);
      delete_button.mouse_over(e);
      delete_sure_button.mouse_over(e);
      undelete_button.mouse_over(e);
      undelete_sure_button.mouse_over(e);
      purgedb_button.mouse_over(e);
      purgedb_sure_button.mouse_over(e);
      close_button.mouse_over(e);
      this.repaint();
    }


    /**
     *  Reset result text, stop pending delete/purge action (sure button set to invisible), decollapse all rows.
     */
    public void reset() {
      // but don't reset the selection
      show_delete_sure_button = false;
      show_undelete_sure_button = false;
      show_purgedb_sure_button = false;
      button_timer.stop();
      result_text = "";
      event_table.setRowHeight(DateLookPanel.slot_height);
      this.repaint();
    }


    /**
     *  Export selected events
     */
    private void export() {
      show_delete_sure_button = false;
      show_undelete_sure_button = false;
      show_purgedb_sure_button = false;
      button_timer.stop();
      result_text = "";
      if (event_table.getSelectedRows().length == 0) {
        result_text = "first select event(s) for export.";
        this.repaint();
        return;
      }
      JFileChooser file_chooser = new JFileChooser();
      file_chooser.setFileFilter(
        new javax.swing.filechooser.FileFilter() {
          public boolean accept(File f) {
            if (f.isDirectory()) {
              return true;
            }
            if (f.toString().endsWith(".vcs")) {
              return true;
            }
            return false;
          }

          public String getDescription() {
            return "vCalendar *.vcs";
          }
        });
      file_chooser.setFileHidingEnabled(true);
      int retVal = file_chooser.showDialog(this, "Export");
      if (retVal == JFileChooser.APPROVE_OPTION) {
        try {
          result_text = Integer.toString(EventMemory.get_instance(null).export_vCalendar(
              file_chooser.getSelectedFile(), event_table.getSelectedRows(), false, null, false)) +
              " event(s) exported.";
        }
        catch (Exception b) {
          result_text = "Error: " + b.getMessage();
        }
      }
      this.repaint();
    }


    /**
     *  Import events from chosen file
     */
    private void import_() {
      this.reset();
      event_table.clearSelection();
      ((EventTableFrame) parent_window).set_last_selected_row(-1);
      JFileChooser file_chooser = new JFileChooser();
      file_chooser.setFileFilter(
        new javax.swing.filechooser.FileFilter() {
          public boolean accept(File f) {
            if (f.isDirectory()) {
              return true;
            }
            if (f.toString().endsWith(".vcs")) {
              return true;
            }
            return false;
          }


          public String getDescription() {
            return "vCalendar *.vcs";
          }
        });
      file_chooser.setFileHidingEnabled(true);
      int retVal = file_chooser.showDialog(this, "Import");
      if (retVal == JFileChooser.APPROVE_OPTION) {
        try {
          EventMemory.get_instance(null).import_vCalendar(file_chooser.getSelectedFile(), false, null);
        }
        catch (Exception x) {
          result_text = "Error: " + x.getMessage();
        }
      }
      event_table.clearSelection();
      ((EventTableFrame) parent_window).set_last_selected_row(-1);
      int k = 0;   //counter of imported event
      for (int i = 0; i < EventMemory.get_instance(null).get_size(); i++) {
        if (EventMemory.get_instance(null).get_event(i).get_now_imported()) {
          EventMemory.get_instance(null).get_event(i).set_now_imported(false);
          event_table.addRowSelectionInterval(i, i);
          k++;
        }
      }
      if (k != 0) {
        // row height of unselected rows must set in a separate loop because addRowSelection causes call of reset()
        for (int i = 0; i < EventMemory.get_instance(null).get_size(); i++) {
          if (event_table.isRowSelected(i)) {
            event_table.scrollRectToVisible(event_table.getCellRect(i, 3, true));
          }
          else {
            event_table.setRowHeight(i, 2);
          }
        }
      }
      if (result_text.length() == 0) {
        result_text = Integer.toString(k) + " event(s) imported/updated!";
      }
      this.repaint();
      EventMemory.get_instance(null).save(false);
    }


    /**
     *  Open sync dialog
     */
    private void sync() {
      this.reset();
      event_table.clearSelection();
      ((EventTableFrame) parent_window).set_last_selected_row(-1);
      SyncDialog sync_dialog = new SyncDialog((JFrame) parent_window);
      sync_dialog.setLocation((int) this.getLocationOnScreen().getX() + 20,
          (int) this.getLocationOnScreen().getY() + 20);
      sync_dialog.setVisible(true);
      event_table.clearSelection();
      int k = 0;  // counter of imported events
      int j = 0;  // counter of deleted events while import
      for (int i = 0; i < EventMemory.get_instance(null).get_size(); i++) {
        if (EventMemory.get_instance(null).get_event(i).get_now_imported()) {
          EventMemory.get_instance(null).get_event(i).set_now_imported(false);
          event_table.addRowSelectionInterval(i, i);
          k++;
        }
      }
      if (k != 0) {
        // row height of unselected rows must set in a separate loop because addRowSelection causes call of reset()
        for (int i = 0; i < EventMemory.get_instance(null).get_size(); i++) {
          if (event_table.isRowSelected(i)) {
            event_table.scrollRectToVisible(event_table.getCellRect(i, 3, true));
          }
          else {
            event_table.setRowHeight(i, 2);
          }
        }
      }
      for (int i = 0; i < EventMemory.get_instance(null).get_deleted_size(); i++) {
        if (EventMemory.get_instance(null).get_deleted_event(i).get_deleted_while_import()) {
          EventMemory.get_instance(null).get_deleted_event(i).set_deleted_while_import(false);
          j++;
        }
      }
      result_text = Integer.toString(k) + " event(s) upd./imported, " + Integer.toString(j) + " event(s) deleted!"; 
      this.repaint();
      EventMemory.get_instance(null).save(false);
    }


    /**
     *  Undelete all deleted events
     */
    private void undelete() {
      this.reset();
      event_table.clearSelection();
      ((EventTableFrame) parent_window).set_last_selected_row(-1);
      int k = 0;  // counter of undeleted events
      EventMemory.get_instance(null).undelete_all();
      for (int i = 0; i < EventMemory.get_instance(null).get_size(); i++) {
        if (EventMemory.get_instance(null).get_event(i).get_now_imported()) {
          EventMemory.get_instance(null).get_event(i).set_now_imported(false);
          event_table.addRowSelectionInterval(i, i);
          k++;
        }
      }
      if (k != 0) {
        // row height of unselected rows must set in a separate loop because addRowSelection causes call of reset()
        for (int i = 0; i < EventMemory.get_instance(null).get_size(); i++) {
          if (event_table.isRowSelected(i)) {
            event_table.scrollRectToVisible(event_table.getCellRect(i, 3, true));
          }
          else {
            event_table.setRowHeight(i, 2);
          }
        }
      }
      result_text = Integer.toString(k) + " event(s) undeleted!"; 
      this.repaint();
      EventMemory.get_instance(null).save(false);
    }


    /**
     *  purge event memory from already deleted events
     */
    private void purgedb() {
      result_text = Integer.toString(EventMemory.get_instance(null).purge_all()) + " deleted event(s) purged!";
    }
    
    
    /**
     *  Start searching.
     */
    private void search() {
      show_delete_sure_button = false;
      show_undelete_sure_button = false;
      show_purgedb_sure_button = false;
      button_timer.stop();
      result_text = "searching...";
      this.repaint();
      event_table.clearSelection();
      ((EventTableFrame) parent_window).set_last_selected_row(-1);
      int found_rows = 0;
      // parse for search strings
      ArrayList<String> search_strings = new ArrayList<String>();  // to store all search strings
      char[] chars = search_text_field.getText().toCharArray();
      for (int i = 0; i < chars.length; i++) {
        String tmp = "";
        char separator = ' ';
        while (i < chars.length && chars[i] != separator) {
          if (chars[i] == '"') {
            separator = '"';
          }
          else {
            tmp = tmp + chars[i];
          }
          i++;
        }
        if (tmp.length() > 0) {
          search_strings.add(tmp);
        }
      }
      String tmp = "";
      for (int i = 0; i < search_strings.size(); i++) {
        tmp = tmp + "\"" + (String) search_strings.get(i) + "\" ";
      }
      search_text_field.setText(tmp.trim());
      // start seaching in table here
      for (int i = event_table_model.getRowCount() - 1; i >= 0; i--) {
        int match_counter = 0;
        for (int k = 0; k < search_strings.size(); k++) {
          if (((String) event_table_model.getValueAt(i, 0)).toUpperCase().indexOf(((String) search_strings.get(k)).toUpperCase()) != -1 ||
              ((String) event_table_model.getValueAt(i, 1)).toUpperCase().indexOf(((String) search_strings.get(k)).toUpperCase()) != -1 ||
              ((String) event_table_model.getValueAt(i, 2)).toUpperCase().indexOf(((String) search_strings.get(k)).toUpperCase()) != -1 ||
              ((String) event_table_model.getValueAt(i, 3)).toUpperCase().indexOf(((String) search_strings.get(k)).toUpperCase()) != -1 ||
              ((String) event_table_model.getValueAt(i, 5)).toUpperCase().indexOf(((String) search_strings.get(k)).toUpperCase()) != -1) {
            match_counter++;
          }
        }
        if (match_counter == search_strings.size()) {
          // all search strings found in date
          event_table.addRowSelectionInterval(i, i);
          found_rows++;
        }
      }
      if (found_rows > 0) {
        for (int i = 0; i < event_table_model.getRowCount(); i++) {
          if (!event_table.isRowSelected(i)) {
            event_table.setRowHeight(i, 2);
          }
          else {
            event_table.scrollRectToVisible(event_table.getCellRect(i, 3, true));
          }
        }
      }
      result_text = Integer.toString(found_rows) + " event(s) found.";
      this.repaint();
    }
  }


  /**
   *  Event table model
   */
  public static class EventTableModel extends AbstractTableModel {

    /**
     *  Gets the columnCount attribute of the EventTableModel object
     *
     * @return    The columnCount value
     */
    public int getColumnCount() {
      return 6;
    }


    /**
     *  Gets the rowCount attribute of the EventTableModel object
     *
     * @return    The rowCount value
     */
    public int getRowCount() {
      return EventMemory.get_instance(null).get_size();
    }


    /**
     *  Gets the valueAt attribute of the EventTableModel object
     *
     * @param  i    index to row
     * @param  col  index to column
     * @return      The valueAt value
     */
    public Object getValueAt(int i, int col) {
      Event t = EventMemory.get_instance(null).get_event(i);
      switch (col) {
          case 0:
          {
            // Begin Date
            return Converter.ms2dmy(t.get_begin_UTC_ms());
          }
          case 1:
          {
            // Begin Time
            return Converter.ms2hm(t.get_begin_UTC_ms());
          }
          case 2:
          {
            // Class
            if (t.get_vcal_class() == Event.Private) {
              return "private";
            }
            return "public";
          }
          case 3:
          {
            // Period
            String pm = Integer.toString(t.get_period_multiplier()) + "-";
            if (t.get_period_multiplier() == 1) {
              pm = "";
            }
            switch (t.get_period()) {
                case (Event.None):
                  return "none";
                case (Event.Daily):
                  return pm + "daily";
                case (Event.Weekly):
                  return pm + "weekly";
                case (Event.Monthly):
                  return pm + "monthly";
                case (Event.Yearly):
                  return pm + "yearly";
            }
          }
          case 4:
          {
            // Number of periods
            if (t.get_period() != Event.None) {
              return Integer.toString(t.get_number_of_periods());
            }
            return "---";
          }
          case 5:
          {
            // Text
            return t.get_summary();
          }
          default:
            return "";
      }
    }


    /**
     *  Sets the valueAt attribute of the EventTableModel object
     *
     * @param  content  The new valueAt value
     * @param  row      The new valueAt value
     * @param  col      The new valueAt value
     */
    public void setValueAt(Object content, int row, int col) {
    }


    /**
     *  Gets the cellEditable attribute of the EventTableModel object
     *
     * @param  row  index to row
     * @param  col  index to column
     * @return      The cellEditable value
     */
    public boolean isCellEditable(int row, int col) {
      return false;
    }


    /**
     *  Gets the columnName attribute of the EventTableModel object
     *
     * @param  col  index to column
     * @return      The columnName value
     */
    public String getColumnName(int col) {
      switch (col) {
          case 0:
            return "(first) Date";
          case 1:
            return "Time";
          case 2:
            return "Class";
          case 3:
            return "Cycle";
          case 4:
            return "times";
          case 5:
            return "Summary";
          default:
            return "";
      }
    }
  }
}

