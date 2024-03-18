package OBS_C_2025;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Font_Sec extends JComponent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static final Integer[] SIZES = {8, 9, 10, 11, 12, 13, 14, 16, 18, 20, 24, 26, 28, 32, 36, 40, 48, 56, 64, 72};
    private static final String[] FONTS = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    private FontSelectionModel selectionModel;
    private JList<String> fontList;
    private JList<Integer> sizeList;
    private JCheckBox boldCheckBox;
    private JCheckBox italicCheckBox;
    private JLabel previewLabel;
    private String previewText;
    private SelectionUpdater selectionUpdater = new SelectionUpdater();
    private LabelUpdater labelUpdater = new LabelUpdater();
    private boolean updatingComponents = false;
   
    private class LabelUpdater implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            updateComponents();
        }
    }
    private class SelectionUpdater implements ChangeListener, ListSelectionListener {
        public void stateChanged(ChangeEvent e) {
            if (!updatingComponents)
            	setFont(buildFont());
        }
        public void valueChanged(ListSelectionEvent e) {
            if (!updatingComponents)
            	setFont(buildFont());
        }
    }
    public Font showDialog(Component component, String title) {
        FontTracker ok = new FontTracker(this);
        JDialog dialog = createDialog(component, "Font Secimi", true, ok, null);
        dialog.setSize(300, 500);
        dialog.addWindowListener(new FontChooserDialog.Closer());
        dialog.addComponentListener(new FontChooserDialog.DisposeOnClose());
        dialog.setVisible(true); // blocks until user brings dialog down...
        return ok.getFont();
    }
    public JDialog createDialog(Component c, String title, boolean modal,
            ActionListener okListener, ActionListener cancelListener) {
            return new FontChooserDialog(c, title, modal, this,
                    okListener, cancelListener);
        }
    public Font_Sec() {
        this(new DefaultFontSelectionModel());
    }
    public Font_Sec(Font initialFont) {
        this(new DefaultFontSelectionModel(initialFont));
    }
    public Font_Sec(FontSelectionModel model) {
        selectionModel = model;
        init(model.getSelectedFont());
        selectionModel.addChangeListener(labelUpdater);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void init(Font font) {
        setLayout(new GridBagLayout());
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        Insets ins = new Insets(2, 2, 2, 2);
        fontList = new JList(FONTS);

        fontList.setVisibleRowCount(10);
        fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPanefontList = new JScrollPane();
        scrollPanefontList.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPanefontList.setViewportView(fontList);
		
        add(scrollPanefontList, new GridBagConstraints(0, 0, 1, 1, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH,new Insets(2, 2, 5, 5), 0, 0));

        sizeList = new JList(SIZES);
        sizeList.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        ((JLabel)sizeList.getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
        sizeList.setVisibleRowCount(10);
       
        sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPanesizeList = new JScrollPane();
        scrollPanesizeList.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPanesizeList.setViewportView(sizeList);

        add(scrollPanesizeList, new GridBagConstraints(1, 0, 1, 1, 1, 2,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 5, 2), 0, 0));

        boldCheckBox = new JCheckBox("Bold");
        add(boldCheckBox, new GridBagConstraints(0, 1, 2, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(2, 2, 5, 2), 0, 0));

        italicCheckBox = new JCheckBox("Italic");
        add(italicCheckBox, new GridBagConstraints(0, 2, 2, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(2, 2, 5, 2), 0, 0));
        previewLabel = new JLabel("");
        previewLabel.setHorizontalAlignment(JLabel.CENTER);
        previewLabel.setVerticalAlignment(JLabel.CENTER);
        JScrollPane scrollPaneLabel = new JScrollPane();
		scrollPaneLabel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPaneLabel.setViewportView(previewLabel);
        add(scrollPaneLabel, new GridBagConstraints(0, 4, 2, 1, 1, 1,GridBagConstraints.CENTER, GridBagConstraints.BOTH,ins, 0, 0));
        setFont(font == null ? previewLabel.getFont() : font);

        fontList.addListSelectionListener(selectionUpdater);
        sizeList.addListSelectionListener(selectionUpdater);
        boldCheckBox.addChangeListener(selectionUpdater);
        italicCheckBox.addChangeListener(selectionUpdater);
      
    }
    private Font buildFont() {
      String fontName = (String)fontList.getSelectedValue();
      if (fontName == null)
    	  return null;
      Integer sizeInt = (Integer)sizeList.getSelectedValue();
      if (sizeInt == null)
    	  return null;
      return new Font(fontName,
              (italicCheckBox.isSelected() ? Font.ITALIC : Font.PLAIN)
              | (boldCheckBox.isSelected() ? Font.BOLD : Font.PLAIN), sizeInt);
  }
    private void updateComponents() {
        updatingComponents = true;
        Font font = getFont();
        fontList.setSelectedValue(font.getName(), true);
        sizeList.setSelectedValue(font.getSize(), true);
        boldCheckBox.setSelected(font.isBold());
        italicCheckBox.setSelected(font.isItalic());
        
        if (previewText == null)
        	previewLabel.setText(font.getName());
        Font oldValue = previewLabel.getFont();
        previewLabel.setFont(font);
        firePropertyChange("font", oldValue, font);
        updatingComponents = false;
    }
    public FontSelectionModel getSelectionModel() {
        return selectionModel;
    }
    public void setSelectionModel(FontSelectionModel newModel ) {
        FontSelectionModel oldModel = selectionModel;
        selectionModel = newModel;
        oldModel.removeChangeListener(labelUpdater);
        newModel.addChangeListener(labelUpdater);
        firePropertyChange("selectionModel", oldModel, newModel);
    }
    public Font getFont() {
        return selectionModel.getSelectedFont();
    }
    public void setFont(Font font) {
        selectionModel.setSelectedFont(font);
    }
    public String getPreviewText() {
        return previewText;
    }
    public void setPreviewText(String previewText) {
        this.previewText = previewText;
        previewLabel.setText("");
        updateComponents();
    }
}
    class FontChooserDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private Font initialFont;
    private Font_Sec chooserPane;
    public FontChooserDialog(Component c, String title, boolean modal,
            Font_Sec chooserPane,
            ActionListener okListener, ActionListener cancelListener) {
      super(JOptionPane.getFrameForComponent(c), title, modal);
      String okString = UIManager.getString("ColorChooser.okText");
      String cancelString = UIManager.getString("ColorChooser.cancelText");
      String resetString = UIManager.getString("ColorChooser.resetText");
      JPanel buttonPane = new JPanel();
      buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
      JButton okButton = new JButton(okString);
      getRootPane().setDefaultButton(okButton);
      okButton.setActionCommand("OK");
      if (okListener != null)
    	  okButton.addActionListener(okListener);
      okButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              setVisible(false);
          }
      });
      buttonPane.add(okButton);
      JButton cancelButton = new JButton(cancelString);
      Action cancelKeyAction = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
              // todo make it in 1.3
//              ActionListener[] listeners
//                      = ((AbstractButton) e.getSource()).getActionListeners();
//              for (int i = 0; i < listeners.length; i++) {
//                  listeners[i].actionPerformed(e);
//              }
          }
      };
      KeyStroke cancelKeyStroke = KeyStroke.getKeyStroke((char) KeyEvent.VK_ESCAPE);
      InputMap inputMap = cancelButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
      ActionMap actionMap = cancelButton.getActionMap();
      if (inputMap != null && actionMap != null) {
          inputMap.put(cancelKeyStroke, "cancel");
          actionMap.put("cancel", cancelKeyAction);
      }
      // end esc handling
      cancelButton.setActionCommand("cancel");
      if (cancelListener != null)
    	  cancelButton.addActionListener(cancelListener);
      cancelButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              setVisible(false);
          }
      });
      buttonPane.add(cancelButton);
      
      JButton resetButton = new JButton(resetString);
      resetButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              reset();
          }
      });
      int mnemonic = UIManager.getInt("ColorChooser.resetMnemonic");
      if (mnemonic != -1)
    	  resetButton.setMnemonic(mnemonic);
      buttonPane.add(resetButton);
      this.chooserPane = chooserPane;
      Container contentPane = getContentPane();
      contentPane.setLayout(new BorderLayout());
      contentPane.add(chooserPane, BorderLayout.CENTER);
      contentPane.add(buttonPane, BorderLayout.SOUTH);
      pack();
      setLocationRelativeTo(c);
  }
    public void setVisible(boolean visible) {
        if (visible)
            initialFont = chooserPane.getFont();
        super.setVisible(visible);
    }

    public void reset() {
        chooserPane.setFont(initialFont);
    }
    static class Closer extends WindowAdapter implements Serializable {
		private static final long serialVersionUID = 1L;
		public void windowClosing(WindowEvent e) {
            Window w = e.getWindow();
            w.setVisible(false);
        }
    }
    static class DisposeOnClose extends ComponentAdapter implements Serializable {
		private static final long serialVersionUID = 1L;
		public void componentHidden(ComponentEvent e) {
            Window w = (Window) e.getComponent();
            w.dispose();
        }
    }
}
     class FontTracker implements ActionListener, Serializable {
		private static final long serialVersionUID = 1L;
			Font_Sec chooser;
    	    Font color;
    	    public FontTracker(Font_Sec c) {
    	        chooser = c;
    	    }
    	    public void actionPerformed(ActionEvent e) {
    	        color = chooser.getFont();
    	    }
    	    public Font getFont() {
    	        return color;
    	    }
    	}
     class DefaultFontSelectionModel implements FontSelectionModel {
    	    private static final Font DEFAULT_INITIAL_FONT = new Font("Dialog", Font.PLAIN, 12);
    	    private Font selectedFont;
    	    private EventListenerList listeners = new EventListenerList();
    	    public DefaultFontSelectionModel() {
    	        this(DEFAULT_INITIAL_FONT);
    	    }
    	    public DefaultFontSelectionModel(Font selectedFont) {
    	        if (selectedFont == null)
    	        	selectedFont = DEFAULT_INITIAL_FONT;
    	        this.selectedFont = selectedFont;
    	    }
    	    public Font getSelectedFont() {
    	        return selectedFont;
    	    }
    	    public void setSelectedFont(Font selectedFont) {
    	        if (selectedFont != null) {
    	            this.selectedFont = selectedFont;
    	            fireChangeListeners();
    	        }
    	    }
    	    public void addChangeListener(ChangeListener listener) {
    	        listeners.add(ChangeListener.class, listener);
    	    }
    	    public void removeChangeListener(ChangeListener listener) {
    	        listeners.remove(ChangeListener.class, listener);
    	    }
    	    protected void fireChangeListeners() {
    	        ChangeEvent ev = new ChangeEvent(this);
    	        Object[] l = listeners.getListeners(ChangeListener.class);
    	        for (Object listener : l)
    	        	((ChangeListener) listener).stateChanged(ev);
    	    }
    	}
     interface FontSelectionModel {
    	    Font getSelectedFont();
    	    void setSelectedFont(Font font);

    	    /**
    	     * Adds <code>listener</code> as a listener to changes in the model.
    	     * @param listener the <code>ChangeListener</code> to be added
    	     */
    	    void addChangeListener(ChangeListener listener);

    	    /**
    	     * Removes <code>listener</code> as a listener to changes in the model.
    	     * @param listener the <code>ChangeListener</code> to be removed
    	     */
    	    void removeChangeListener(ChangeListener listener);
    	}
