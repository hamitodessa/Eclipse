import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.*;
import java.awt.datatransfer.*;


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
 *  Event's description editor frame 
 */
public class DescriptionEditorFrame extends JFrame {

  private JPanel contentPane;
  private JScrollPane scrollPane;
  private JSplitPane split_pane;
  private JEditorPane editor_pane;
  private ButtonPanel button_panel;

  private StringBuffer description;
  private EditorFrame my_editor_frame;


  /**
   *  Construct the frame
   *
   * @param  ef  EditorFrame from where DescriptionEditor has been opened
   * @param  sb  description of the event
   * @param  s   summary of the event
   * @param  c   colour of the event
   */
  public DescriptionEditorFrame(EditorFrame ef, StringBuffer sb, String s, Color c) {
    super();

    my_editor_frame = ef;
    description = sb;
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      contentPane = (JPanel) this.getContentPane();
      setIconImage(Toolkit.getDefaultToolkit().createImage(DescriptionEditorFrame.class.getResource("dl.png")));
      this.setSize(new Dimension(28 * DateLookPanel.slot_height + DateLookPanel.frame_decor_width,
          15 * DateLookPanel.slot_height + DateLookPanel.frame_decor_height));
      this.setTitle("Description Editor");

      editor_pane = new JEditorPane("text/plain", description.toString());
      editor_pane.setFont(new Font("SansSerif", Font.PLAIN, DateLookPanel.slot_height * 2 / 3));
      button_panel = new ButtonPanel(this, ef, editor_pane, s, c);
      editor_pane.addKeyListener(button_panel);

      scrollPane = new JScrollPane(editor_pane);
      split_pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, button_panel, scrollPane);
      split_pane.setResizeWeight(0);
      split_pane.setDividerSize(4);
      split_pane.setDividerLocation(DateLookPanel.slot_height * 3 / 2);
      contentPane.add(split_pane);
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
      description.delete(0, description.length());
      description.append(editor_pane.getText());
      my_editor_frame.delete_description_editor_frame();
    }
  }


  /**
   *  Handle dispose of this frame
   */
  public void dispose() {
    description.delete(0, description.length());
    description.append(editor_pane.getText());
    super.dispose();
  }


  /**
   *  Button panel with button for copy, cut, paste and close
   */
  public static class ButtonPanel extends RPanel {

    private EditorFrame my_editor_frame;
    private JEditorPane editor_pane;

    private RButton copy_button;
    private RButton cut_button;
    private RButton paste_button;
    private RButton close_button;
    private RComponent summary_text;
    private Clipboard clipboard;


    /**
     *  Constructor for the ButtonPanel object
     *
     * @param  pw  parent window
     * @param  ef  EditorFrame from where DescriptionEditor has been opened
     * @param  ep  the editor pane to edit the event's description
     * @param  s   summary of the event
     * @param  c   colour of the event
     */
    public ButtonPanel(Window pw, EditorFrame ef, JEditorPane ep, String s, Color c) {
      super(pw, false);
      my_editor_frame = ef;
      editor_pane = ep;
      clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      String[] st = {s};
      summary_text = new RComponent(this, c, Color.orange, 0, 0, 0, 1, st, "", 15, 240, 510, 24);
      copy_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "copy", 570, 240, 91, 24);
      cut_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "cut", 670, 240, 91, 24);
      paste_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "paste", 770, 240, 91, 24);
      close_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "close", 900, 240, 91, 24);
    }


    /**
     *  Paint component
     *
     * @param  g  Graphics object
     */
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Font font = new Font("SansSerif", Font.PLAIN, 24 * this.getWidth() / 1000);
      g2.setColor(Color.black);
      g2.setFont(font);
      g2.setFont(font);
      copy_button.draw(g2);
      cut_button.draw(g2);
      paste_button.draw(g2);
      close_button.draw(g2);
      summary_text.draw(g2);
    }


    /**
     *  Check for pressed key and handles it.<br>
     *  F1 - opens HelpFrame<br>
     *  ctrl-Q - closes the frame
     *
     * @param  e  key event
     */
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_F1) {
        HelpFrame.get_instance().help("Edit_an_event");
      }
      else {
        if (e.getModifiers() == InputEvent.CTRL_MASK) {
          if (e.getKeyCode() == KeyEvent.VK_Q) {
            parent_window.dispose();
            my_editor_frame.delete_description_editor_frame();
            return;
          }
        }
      }
    }


    /**
     *  Handle "mouse clicked" event
     *
     * @param  e  mouse event
     */
    public void mouseClicked(MouseEvent e) {
      if (copy_button.mouse_clicked(e)) {
        clipboard.setContents(new StringSelection(editor_pane.getSelectedText()), null);
      }
      if (cut_button.mouse_clicked(e)) {
        clipboard.setContents(new StringSelection(editor_pane.getSelectedText()), null);
        editor_pane.replaceSelection("");
      }
      if (paste_button.mouse_clicked(e)) {
        Transferable contents = clipboard.getContents(this);
        if (contents != null) {
          try {
            editor_pane.replaceSelection((String) (contents.getTransferData(DataFlavor.stringFlavor)));
          }
          catch (Exception ex) {
          }
        }
      }
      if (close_button.mouse_clicked(e)) {
        parent_window.dispose();
        my_editor_frame.delete_description_editor_frame();
      }
    }


    /**
     *  Handle "mouse pressed" event
     *
     * @param  e  mouse event
     */
    public void mousePressed(MouseEvent e) {
      copy_button.mouse_pressed(e);
      cut_button.mouse_pressed(e);
      paste_button.mouse_pressed(e);
      close_button.mouse_pressed(e);
      this.repaint();
    }


    /**
     *  Handle "mouse released" event
     *
     * @param  e  mouse event
     */
    public void mouseReleased(MouseEvent e) {
      copy_button.mouse_released(e);
      cut_button.mouse_released(e);
      paste_button.mouse_released(e);
      close_button.mouse_released(e);
      this.repaint();
    }


    /**
     *  Handle "mouse moved" event
     *
     * @param  e  mouse event
     */
    public void mouseMoved(MouseEvent e) {
      copy_button.mouse_over(e);
      cut_button.mouse_over(e);
      paste_button.mouse_over(e);
      close_button.mouse_over(e);
      this.repaint();
    }
  }
}

