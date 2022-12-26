package VT_YEDEK;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class EMIR extends JFrame {

	private JPanel contentPane;
	private JList<CheckListItem> list ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EMIR frame = new EMIR();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public EMIR() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(150,0));
		panel.setLayout(null);
		splitPane.setLeftComponent(panel);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 11, 130, 22);
		panel.add(comboBox);
		
	
		list = new JList<CheckListItem>();
		
		    list.setCellRenderer(new CheckListRenderer());
		    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    list.addListSelectionListener(new ListSelectionListener() {
		    	 @SuppressWarnings("deprecation")
				public void valueChanged(ListSelectionEvent e) {
				      if (!e.getValueIsAdjusting()) {
				        System.out.println(Arrays.toString(list.getSelectedValues()));
				      }
				    }
		    	
		    });
		    list.addMouseListener(new MouseAdapter() {
		      @SuppressWarnings("rawtypes")
			@Override
		      public void mouseClicked(MouseEvent event) {
		        JList list = (JList) event.getSource();
		        int index = list.locationToIndex(event.getPoint());// Get index of item
		                                                           // clicked
		        CheckListItem item = (CheckListItem) list.getModel()
		            .getElementAt(index);
		        item.setSelected(!item.isSelected()); // Toggle selected state
		        list.repaint(list.getCellBounds(index, index));// Repaint cell
		        System.out.println(list.getSelectedValue() + " = " + item.isSelected());
		      }
		    });
		list.setBounds(10, 44, 130, 337);
		panel.add(list);
		
		///
		DefaultListModel<CheckListItem> demoList = new DefaultListModel<CheckListItem>();
		 demoList.addElement( new CheckListItem("mango"));
		 demoList.addElement( new CheckListItem("elma"));
		 list.setModel(demoList);
		//
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
	}
	
}

@SuppressWarnings({ "serial", "rawtypes" })
class CheckListRenderer extends JCheckBox implements ListCellRenderer {
	  public Component getListCellRendererComponent(JList list, Object value,
	      int index, boolean isSelected, boolean hasFocus) {
	    setEnabled(list.isEnabled());
	    setSelected(((CheckListItem) value).isSelected());
	    setFont(list.getFont());
	    setBackground(list.getBackground());
	    setForeground(list.getForeground());
	    setText(value.toString());
	    return this;
	  }
	}
class CheckListItem {

	  private String label;
	  private boolean isSelected = false;

	  public CheckListItem(String label) {
	    this.label = label;
	  }

	  public boolean isSelected() {
	    return isSelected;
	  }

	  public void setSelected(boolean isSelected) {
	    this.isSelected = isSelected;
	  }

	  @Override
	  public String toString() {
	    return label;
	  }
	}
