package VT_YEDEK;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class EMIR extends JFrame {
	VT_ANA_CLASS oac = new VT_ANA_CLASS();
	private JPanel contentPane;
	private JList<CheckListItem> list ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BASLA bsl = new BASLA();
					bsl.setVisible(false);
					bsl.dispose();
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				try {
					BASLA frame = new BASLA();
					frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void windowOpened(WindowEvent e) {
				
					contentPane.setCursor(oac.WAIT_CURSOR);
					//activ_sayfa =0;
					//grid_doldur();
					//doldur_kutu(tblCari,0);
					contentPane.setCursor(oac.DEFAULT_CURSOR);
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
		
		JComboBox<String> cmbSQL = new JComboBox<String>();
		
		cmbSQL.setForeground(new Color(0, 0, 139));
		cmbSQL.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbSQL.setModel(new DefaultComboBoxModel<String>(new String[] {"MS SQL", "MY SQL"}));
		cmbSQL.setBounds(10, 11, 89, 22);
		panel.add(cmbSQL);
		
	
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
		 
		 JButton btnNewButton = new JButton("New button");
		 btnNewButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		SQL_BILGI hsp ;
				hsp = new SQL_BILGI();
				hsp.show();
				dispose();
		 	}
		 });
		 btnNewButton.setBounds(114, 11, 26, 23);
		 panel.add(btnNewButton);
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
