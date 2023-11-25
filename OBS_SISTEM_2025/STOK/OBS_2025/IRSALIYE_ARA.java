package OBS_2025;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;


import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import java.awt.Font;

@SuppressWarnings({"serial","static-access","deprecation","unused"})
public class IRSALIYE_ARA extends JDialog {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	static CARI_ACCESS c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);	

	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private static JLabel lblNewLabel_1;
	private static JComboBox<String> cmbanagrup ;
	private static JComboBox<String> cmbaltgrup ;
	/**
	 * Create the frame.
	 */
	public IRSALIYE_ARA() {
		setResizable(true);
		setModal(true);
	

		setTitle("IRSALIYE ARAMA");
		setBounds(100, 100, 900, 320);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		
		
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0);
		splitPane.setDividerSize(1);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		splitPane.setRightComponent(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					int row = table.getSelectedRow();
					if (table.getRowSorter()!=null) {
					    row = table.getRowSorter().convertRowIndexToModel(row);
					    oac.irs_no = 	table.getModel().getValueAt(row, 0).toString() ;
						dispose();
					}
					else
					{
						oac.irs_no = 	table.getModel().getValueAt(table.getSelectedRow(), 0).toString() ;
						dispose();
					}
				}
			}
		});
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 81));
		panel.setMaximumSize(new Dimension(0, 81));
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Irsaliye No");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 15, 68, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(88, 10, 113, 20);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				 getContentPane().setCursor(oac.WAIT_CURSOR);
			    hisset();
			    getContentPane().setCursor(oac.DEFAULT_CURSOR);
			  }
			  public void removeUpdate(DocumentEvent e) {
				  getContentPane().setCursor(oac.WAIT_CURSOR);
			    hisset();
			    getContentPane().setCursor(oac.DEFAULT_CURSOR);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  getContentPane().setCursor(oac.WAIT_CURSOR);
			    hisset();
			    getContentPane().setCursor(oac.DEFAULT_CURSOR);
			  }
			});
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblHesapKodu = new JLabel("Hesap Kodu");
		lblHesapKodu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHesapKodu.setBounds(10, 42, 68, 14);
		panel.add(lblHesapKodu);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_1.setColumns(10);
		textField_1.setBounds(88, 37, 113, 20);
		textField_1.setDocument(new JTextFieldLimit(12));
		textField_1.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    isimoku();
			    hisset();
			  }
			  public void removeUpdate(DocumentEvent e) {
				    isimoku();
				    hisset();
			  }
			  public void insertUpdate(DocumentEvent e) {
				    isimoku();
				    hisset();
			  }
			
			});
		textField_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					try {
						HESAP_PLN hsp ;
						getContentPane().setCursor(oac.WAIT_CURSOR);
						hsp = new HESAP_PLN();
						hsp.show();
						textField_1.setText( oac.hsp_hsp_kodu);
						isimoku();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				}
			}
		});
		panel.add(textField_1);
		
		JLabel lblAnaGrup = new JLabel("Ana Grup");
		lblAnaGrup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAnaGrup.setBounds(329, 15, 68, 14);
		panel.add(lblAnaGrup);
		
		JLabel lblAltGrup = new JLabel("Alt Grup");
		lblAltGrup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAltGrup.setBounds(329, 42, 68, 14);
		panel.add(lblAltGrup);
		
		cmbanagrup = new JComboBox<String>();
		cmbanagrup.setForeground(new Color(0, 0, 128));
		cmbanagrup.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbanagrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hisset();
				alt_grup_doldur();
			}
		});
		cmbanagrup.setBounds(395, 10, 147, 22);
		panel.add(cmbanagrup);
		
		cmbaltgrup = new JComboBox<String>();
		cmbaltgrup.setForeground(new Color(0, 0, 128));
		cmbaltgrup.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbaltgrup.setEnabled(false);
		cmbaltgrup.setBounds(395, 39, 147, 22);
		panel.add(cmbaltgrup);
		
		lblNewLabel_1 = new JLabel("...");
		lblNewLabel_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_1.setBounds(88, 63, 232, 14);
		panel.add(lblNewLabel_1);

		ana_grup_doldur();
		hisset();
	}
	private void hisset()
	{
		try
		{
			ResultSet rs=null;
			//
			 int anagrp = 0 ;
			 String angrp = "" ;
			 int altagrp = 0;
		     String altgrp = "" ;
		     
		     if (  cmbanagrup.getSelectedItem()  != null)  
		     {
		     
		        if ( ! cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals("") ) {
		        				rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString());
		        		
		        		if (!rs.isBeforeFirst() ) {      		
		    	    	}
		    	    	else
		    	    	{
		    	    		rs.next();
			        		anagrp  = rs.getInt("AGID_Y");
			        		angrp = String.valueOf(anagrp);
		    	    	}
		        }
		     }
		     if ( cmbaltgrup.getSelectedItem() != null)  
		    {
		        if ( ! cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString().equals("") )
		        {
		     	      
		     	    		rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN",  cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString());
		     			
		     	      	if (!rs.isBeforeFirst() ) {      		
		    	    	}
		    	    	else
		    	    	{
		    	    		rs.next();
			     	      	altagrp  = rs.getInt("ALID_Y");
			     	   	altgrp = String.valueOf(altagrp);
		    	    	}
		        }
		     	}
			//
			
			String arama = " AND  Irsaliye_No Like '%"+ textField.getText() + "%'  AND Firma Like  '%" + textField_1.getText() + "%'  AND Ana_Grup Like '%"+ angrp + "%' AND Alt_Grup Like '%"+ altgrp + "%' ";
			
				rs = f_Access.irsaliye_faturasiz(FATURA.cmbcins.getItemAt(FATURA.cmbcins.getSelectedIndex()).toString().equals("SATIS") ? "C":"G",arama);
			
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
			    return;
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
					JTableHeader th = table.getTableHeader();
					TableColumnModel tcm = th.getColumnModel();
					TableColumn tc;
						
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(90);
					
					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new TARIH());
					tc.setMinWidth(80);
					
					tc = tcm.getColumn(2);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(90);
					
					tc = tcm.getColumn(3);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(90);
					
					tc = tcm.getColumn(4);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(3,false));
					tc.setMinWidth(80);
					
					tc = tcm.getColumn(5);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(150);
					
					tc = tcm.getColumn(6);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(150);
					
					tc = tcm.getColumn(7);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(150);
					
					tc = tcm.getColumn(8);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(150);
					
					tc = tcm.getColumn(9);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(90);
					
						Dimension dd = th.getPreferredSize();
					    dd.height = 30;
					    th.setPreferredSize(dd); 
						th.repaint();
						table.setRowSelectionInterval(0, 0);
						table.setRowHeight(21);
						
						String deger;
						String[] parts;
						Font bigFont;
							try {
								deger = GLOBAL.setting_oku("STK_RAPORLAMA").toString();
								deger = deger.substring(1, deger.length()-1);
								parts = deger.split(",");
								bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
								table.setFont(bigFont);
							} catch (IOException e) {
								e.printStackTrace();
							}
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Okuma", JOptionPane.ERROR_MESSAGE);   
		}
	}
		private void ana_grup_doldur()
		{
			try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			cmbanagrup .removeAllItems();
			ResultSet rs=null;
			
				rs = f_Access.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
			
			if (!rs.isBeforeFirst() ) {  
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				cmbaltgrup.setEnabled(false);
				cmbanagrup .addItem("");
				cmbanagrup.setSelectedItem("");
			    return;
			} 
			cmbanagrup .addItem("");
		    while (rs.next())
		    {
		    	cmbanagrup .addItem(rs.getString("ANA_GRUP"));
		    }
		    getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			catch (Exception ex)
			{
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				JOptionPane.showMessageDialog(null, ex.getMessage(),  "Ana Grup", JOptionPane.ERROR_MESSAGE);   
			}
		}
		private void alt_grup_doldur()
		{
			try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			cmbaltgrup.removeAllItems();
			cmbaltgrup .addItem("");
			ResultSet rs=null;
			
				rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					int in1 = rs.getInt("AGID_Y");
					rs =null;
					rs = f_Access.stk_kod_alt_grup_degisken_oku(in1);
				}
			
			
			if (!rs.isBeforeFirst() ) {  
				cmbaltgrup.setSelectedItem("");
				cmbaltgrup.setEnabled(false);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			} 
			else
			{
		    while (rs.next())
		    {
		    	cmbaltgrup .addItem(rs.getString("ALT_GRUP"));
		    }
		    cmbaltgrup.setSelectedItem(0);
		    cmbaltgrup.setEnabled(true);
			}
			hisset();
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			catch (Exception ex)
			{
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				JOptionPane.showMessageDialog(null, ex.getMessage(),  "Alt Grup", JOptionPane.ERROR_MESSAGE);    	
			}
		}
		public void isimoku()  {
		    ResultSet	rs = null;
		    try
		    {
		    
		    	rs = c_Access.hesap_adi_oku(textField_1.getText());
		   
			if (!rs.isBeforeFirst() ) {  
				lblNewLabel_1.setText("");
			} 
			else
			{
			 while (rs.next()) 
			 {
				 lblNewLabel_1.setText(rs.getString("UNVAN"));
			  }
			}
		    }
		    catch (Exception ex)
		    {
		    	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Unvan Okuma", JOptionPane.ERROR_MESSAGE);   
		    }
	  }
}
