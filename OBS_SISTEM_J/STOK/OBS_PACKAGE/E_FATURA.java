package OBS_PACKAGE;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class E_FATURA extends JInternalFrame {
	static Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	static Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E_FATURA frame = new E_FATURA();
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
	public E_FATURA() {
		setTitle("E FATURA ARAMA");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 1000, 600);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 40));
		panel.setMaximumSize(new Dimension(0, 40));
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kodu / Adi");
		lblNewLabel.setBounds(10, 14, 64, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    arama();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    arama();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    arama();
			  }
			});
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(74, 11, 363, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(WAIT_CURSOR);
				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle("Surucu Seciniz");
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);
			    chooser.setApproveButtonText("Dosya Sec");
			    chooser.setApproveButtonToolTipText("Dosya Sec");
			    chooser.addChoosableFileFilter(new FileNameExtensionFilter("XML Dosya", "xml"));
			    chooser.setApproveButtonMnemonic('s');
			    getContentPane().setCursor(DEFAULT_CURSOR);
			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
			    		 try {
			    			File file = chooser.getSelectedFile();
			    			doldur(file);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage());
						}
  			       }
			      else {
			    		return;
			        }
			}
		});
		btnNewButton.setToolTipText("Dosya Sec");
		btnNewButton.setIcon(new ImageIcon(E_FATURA.class.getResource("/ICONLAR/icons8-add-folder-16.png")));
		btnNewButton.setBounds(447, 11, 56, 23);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel() ; 
		table = new JTable(model) {
				public boolean isCellEditable(int row, int column) {     return false;          };
		};
		table.setGridColor(oac.gridcolor);
		model.addColumn("Id", new String []{""});
	    model.addColumn("Firma Adi", new String []{""});
	    model.addColumn("Mail", new String []{""});
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setBorder(null);
		
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc;
		
		tc = tcm.getColumn(0);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(100);
		
		tc = tcm.getColumn(1);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(450);

		tc = tcm.getColumn(2);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(350);
		
		Dimension dd = th.getPreferredSize();
	    dd.height = 30;
	    th.setPreferredSize(dd); 
		th.repaint();
		
		table.setRowSelectionInterval(0, 0);
		table.setRowHeight(21);
		table.setSelectionBackground(Color.PINK);
		table.setSelectionForeground(Color.BLUE);
		
		scrollPane.setViewportView(table);
	     GRID_TEMIZLE.grid_temizle(table);
	}
	private void doldur(File dosya) 
	{
		try
		{
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse(dosya);
        Element kokElement = doc.getDocumentElement();
        NodeList kisiListesi = kokElement.getElementsByTagName("User");
        GRID_TEMIZLE.grid_temizle(table);
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < kisiListesi.getLength(); i++) {
            Node kisi = kisiListesi.item(i);
            Element kisiElement = (Element) kisi;
            String kisiId = kisiElement.getElementsByTagName("Identifier").item(0).getTextContent();
            String kisiAdi = kisiElement.getElementsByTagName("Title").item(0).getTextContent();
            String email = kisiElement.getElementsByTagName("Alias").item(0).getTextContent();
            email = email.toString().substring(4, email.length());
            model.addRow(new Object[]{kisiId,kisiAdi ,email});
        }
        //
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        //
		table.setRowSelectionInterval(0, 0);
		textField.requestFocus();
		}
		catch (Exception ex)
		{
			 JOptionPane.showMessageDialog(null, ex.getMessage()); 
		}
	}
	public void arama()  
	{
		if (textField.getText().equals(""))
		{
			table.setRowSorter(null);
		}
		else
		{
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
		sorter.setStringConverter(new TableStringConverter() {
	        @Override
	        public String toString(TableModel model, int row, int column) {
	            return model.getValueAt(row, column).toString().toLowerCase();
	        }
	    });
	    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textField.getText().toLowerCase()));
	    table.setRowSorter(sorter);
		}
	}
}
