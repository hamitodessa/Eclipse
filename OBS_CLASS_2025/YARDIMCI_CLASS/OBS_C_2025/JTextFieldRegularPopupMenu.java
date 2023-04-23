package OBS_C_2025;


import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.undo.UndoManager;

public class JTextFieldRegularPopupMenu {
    @SuppressWarnings("serial")
	public static void addTo(JTextField txtField ) 
    {
        JPopupMenu popup = new JPopupMenu();
        UndoManager undoManager = new UndoManager();
        txtField.getDocument().addUndoableEditListener(undoManager);
       
        Action undoAction = (Action) new AbstractAction("Geri Al") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
                else {
                  // System.out.println("No Undo Buffer.");
                }
            }
        };
        Action redoAction = (Action) new AbstractAction("Ileri Al") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (undoManager.canRedo()) {
                    undoManager.redo();
                }
                else {
                  // System.out.println("No Undo Buffer.");
                }
            }
        };

       Action copyAction = new AbstractAction("Kopyala") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                txtField.copy();
            }
        };

        Action cutAction = new AbstractAction("Kes") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                txtField.cut();
            }
        };

        Action pasteAction = new AbstractAction("Yapistir") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                txtField.paste();
            }
        };

        Action selectAllAction = new AbstractAction("Hepsini Sec") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                txtField.selectAll();
                txtField.requestFocusInWindow();
            }
        };
          
        undoAction.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("control Z"));
        redoAction.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("control Y"));
        cutAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X"));
        copyAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control C"));
        pasteAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
        selectAllAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control A"));
       
        ImageIcon icon = new ImageIcon(JTextFieldRegularPopupMenu.class.getResource("/ICONLAR/undo-20.png"));
        popup.add(undoAction).setIcon(icon);
        icon = new ImageIcon(JTextFieldRegularPopupMenu.class.getResource("/ICONLAR/redo-20.png"));
        popup.add(redoAction).setIcon(icon);
        popup.addSeparator();
        icon = new ImageIcon(JTextFieldRegularPopupMenu.class.getResource("/ICONLAR/cut-20.png"));
        popup.add (cutAction).setIcon(icon);
        icon = new ImageIcon(JTextFieldRegularPopupMenu.class.getResource("/ICONLAR/copy-20.png"));
        popup.add (copyAction).setIcon(icon);
        icon = new ImageIcon(JTextFieldRegularPopupMenu.class.getResource("/ICONLAR/paste-20.png"));
        popup.add (pasteAction).setIcon(icon);
        popup.addSeparator();
        icon = new ImageIcon(JTextFieldRegularPopupMenu.class.getResource("/ICONLAR/select-all-20.png"));
        popup.add (selectAllAction).setIcon(icon);

       txtField.setComponentPopupMenu(popup);
    }
    @SuppressWarnings("serial")
	public static void addTo(JTextArea txtField ,int limit) 
    {
    	  JPopupMenu popup = new JPopupMenu();
          UndoManager undoManager = new UndoManager();
          txtField.getDocument().addUndoableEditListener(undoManager);
         
          Action undoAction = (Action) new AbstractAction("Geri Al") {
              @Override
              public void actionPerformed(ActionEvent ae) {
                  if (undoManager.canUndo()) {
                      undoManager.undo();
                  }
                  else {
                    // System.out.println("No Undo Buffer.");
                  }
              }
          };
          Action redoAction = (Action) new AbstractAction("Ileri Al") {
              @Override
              public void actionPerformed(ActionEvent ae) {
                  if (undoManager.canRedo()) {
                      undoManager.redo();
                  }
                  else {
                    // System.out.println("No Undo Buffer.");
                  }
              }
          };

         Action copyAction = new AbstractAction("Kopyala") {
              @Override
              public void actionPerformed(ActionEvent ae) {
                  txtField.copy();
              }
          };

          Action cutAction = new AbstractAction("Kes") {
              @Override
              public void actionPerformed(ActionEvent ae) {
                  txtField.cut();
              }
          };

          Action pasteAction = new AbstractAction("Yapistir") {
              @Override
              public void actionPerformed(ActionEvent ae) {
            	 
					String data = "";
					try {
						data = (String) Toolkit.getDefaultToolkit()
						          .getSystemClipboard().getData(DataFlavor.stringFlavor);
				
					} catch (HeadlessException | UnsupportedFlavorException | IOException e) {
					
					}
				//	System.out.println(txtField.getColumns());
				if (data.length() > limit)
				{
					String myString = data;
					StringSelection stringSelection = new StringSelection(myString.substring(0,100));
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
				}
                  txtField.paste();
              }
          };

          Action selectAllAction = new AbstractAction("Hepsini Sec") {
              @Override
              public void actionPerformed(ActionEvent ae) {
                  txtField.selectAll();
                  txtField.requestFocusInWindow();
              }
          };
            
          undoAction.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("control Z"));
          redoAction.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("control Y"));
          cutAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X"));
          copyAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control C"));
          pasteAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
          selectAllAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control A"));
         
          ImageIcon icon = new ImageIcon(JTextFieldRegularPopupMenu.class.getResource("/ICONLAR/undo-20.png"));
          popup.add(undoAction).setIcon(icon);
          icon = new ImageIcon(JTextFieldRegularPopupMenu.class.getResource("/ICONLAR/redo-20.png"));
          popup.add(redoAction).setIcon(icon);
          popup.addSeparator();
          icon = new ImageIcon(JTextFieldRegularPopupMenu.class.getResource("/ICONLAR/cut-20.png"));
          popup.add (cutAction).setIcon(icon);
          icon = new ImageIcon(JTextFieldRegularPopupMenu.class.getResource("/ICONLAR/copy-20.png"));
          popup.add (copyAction).setIcon(icon);
          icon = new ImageIcon(JTextFieldRegularPopupMenu.class.getResource("/ICONLAR/paste-20.png"));
          popup.add (pasteAction).setIcon(icon);
          popup.addSeparator();
          icon = new ImageIcon(JTextFieldRegularPopupMenu.class.getResource("/ICONLAR/select-all-20.png"));
          popup.add (selectAllAction).setIcon(icon);

         txtField.setComponentPopupMenu(popup);
    }
}