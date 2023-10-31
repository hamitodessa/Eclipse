package OBS_2025;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.*;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SOLA;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;
 

@SuppressWarnings({"serial","static-access","unused"})
public class E_FATURA extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private JTable table;
	private JTextField textField;
	private JTextField textField1;
	private JTable table1;
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
		
		MaterialTabbed tabbedPane = new MaterialTabbed();
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane.setForeground(new Color(0, 0, 128));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("E Fatura", null, panel_1, null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitPane, BorderLayout.CENTER);
		
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
				getContentPane().setCursor(oac.WAIT_CURSOR);
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
			    getContentPane().setCursor(oac.DEFAULT_CURSOR);
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
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}

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
	    table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		th.repaint();
		
		table.setRowSelectionInterval(0, 0);
		table.setRowHeight(21);
		//table.setSelectionBackground(Color.PINK);
		//table.setSelectionForeground(Color.BLUE);
		
		scrollPane.setViewportView(table);
	     GRID_TEMIZLE.grid_temizle(table);
	     
	     JPanel panel_2 = new JPanel();
	 	panel_2.setLayout(new BorderLayout(0, 0));
	     tabbedPane.addTab("Fatura Oku", null, panel_2, null);
	     
	     JSplitPane splitPane_1 = new JSplitPane();
	     splitPane_1.setDividerSize(0);
		 splitPane_1.setResizeWeight(0.0);
		 splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
	     panel_2.add(splitPane_1, BorderLayout.CENTER);
	     
	     JPanel panel2 = new JPanel();
			panel2.setBorder(new LineBorder(new Color(0, 191, 255)));
			panel2.setMinimumSize(new Dimension(0, 40));
			panel2.setMaximumSize(new Dimension(0, 40));
			splitPane_1.setLeftComponent(panel2);
			panel2.setLayout(null);
			
			JLabel lblNewLabel1 = new JLabel("Kodu / Adi");
			lblNewLabel1.setBounds(10, 14, 64, 14);
			panel2.add(lblNewLabel1);
			
			textField1 = new JTextField();
			textField1.getDocument().addDocumentListener(new DocumentListener() {
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
			textField1.setFont(new Font("Tahoma", Font.BOLD, 11));
			textField1.setBounds(74, 11, 363, 20);
			panel2.add(textField1);
			textField1.setColumns(10);
			
			JButton btnNewButton1 = new JButton("");
			btnNewButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getContentPane().setCursor(oac.WAIT_CURSOR);
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
				    getContentPane().setCursor(oac.DEFAULT_CURSOR);
				    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
				    		 try {
				    			File file = chooser.getSelectedFile();
				    			doldur3(file);
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage());
							}
	  			       }
				      else {
				    		return;
				        }
				}
			});
			btnNewButton1.setToolTipText("Dosya Sec");
			btnNewButton1.setIcon(new ImageIcon(E_FATURA.class.getResource("/ICONLAR/icons8-add-folder-16.png")));
			btnNewButton1.setBounds(447, 11, 56, 23);
			panel2.add(btnNewButton1);
			
			JScrollPane scrollPane2 = new JScrollPane();
			splitPane_1.setRightComponent(scrollPane2);
			
			table1 = new JTable() ;
			if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
			{
				table1.setGridColor(oac.gridcolor);
			}

	
		    table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table1.setRowSelectionAllowed(true);
			table1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			table1.setBorder(null);
			scrollPane2.add(table1);
		
			//
			
			
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
	private void doldur2(File dosya) 
	{
		 DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	        try
	        {
	            //DocumentBuilder builder = domFactory.newDocumentBuilder();
	           //Document doc = builder.parse(dosya);
	        	InputStream inputStream= new FileInputStream(dosya);
	            Reader reader = new InputStreamReader(inputStream,"UTF-8");
	            InputSource is = new InputSource(reader);
	            is.setEncoding("UTF-8");

	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(is);
	            
	           
	            Element root = doc.getDocumentElement();
	            NodeList nodelist1 = root.getChildNodes();
	            String[] st = null;
	            DefaultTableModel model1 = (DefaultTableModel) table1.getModel();

	            List<String> texts = new ArrayList<String>();
	            for (int i = 0; i < nodelist1.getLength(); i++)
	            {
	                Node node = nodelist1.item(i);
	                if (node.getNodeType() == Node.ELEMENT_NODE ) 
	                {
	                	//cac:AccountingCustomerParty
	                	if (node.getNodeName().toString() == "cac:AccountingCustomerParty")
	                	{
	                	if ( ! node.getTextContent().toString().equals("")) {
	                
	                    texts.add(  node.getTextContent().trim() );
	                    model1.addRow(st);
	                	}
	                	}
	                }
	            }

	            st = texts.toArray( new String[]{} );
	            System.out.println( Arrays.toString( st ) );
	      
	             table1.setModel(model1);
	             
	           

	        }
	        catch(Exception ex)
	        {
	            ex.printStackTrace();
	            System.out.print("error");
	        }
	}
	private void doldur3(File dosya) throws ParserConfigurationException, SAXException, IOException
	{
		InputStream inputStream= new FileInputStream(dosya);
        Reader reader = new InputStreamReader(inputStream,"UTF-8");
        InputSource is = new InputSource(reader);
        is.setEncoding("UTF-8");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(is);
        
         
		 Node companyNode = doc.getElementsByTagName("cac:InvoiceLine").item(0);
		    NodeList companyChildNodes = companyNode.getChildNodes();
		    for (int i = 0; i < companyChildNodes.getLength(); i++) {
		        Node node = companyChildNodes.item(i);
		      //  if (node.getNodeType() == Node.ELEMENT_NODE && Objects.equals("cbc:Name", node.getNodeName())) {
		            System.out.println(node.getNodeName() + "="+ node.getTextContent());
		         //   break;
			    }
		    
		    for(int i=0;i<companyChildNodes.getLength();i++){
		        Node n=companyChildNodes.item(i);
		        if(n.getNodeType()==Node.ELEMENT_NODE){
		           System.out.println(n.getNodeName()+"\t"+n.getTextContent());
		        }
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

