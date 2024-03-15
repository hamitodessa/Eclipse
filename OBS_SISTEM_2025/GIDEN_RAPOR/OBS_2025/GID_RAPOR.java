package OBS_2025;

import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SOLA;
import OBS_C_2025.SOLA_ORTA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TARIH_SAATLI;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import javax.swing.JLabel;

import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings({"serial","static-access"})
public class GID_RAPOR extends JInternalFrame {
	private static JTable table;
	public static JSplitPane splitPane ;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static Obs_TextFIeld textField;

	public GID_RAPOR() {
		setTitle("GIDEN RAPORLAR");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 1000, 400);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(GID_RAPOR.class.getResource("/ICONLAR/icons8-mailbox-opened-flag-up-30.png")), 16, 16));//
		splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 30));
		panel.setMaximumSize(new Dimension(0, 30));
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Arama");
		lblNewLabel.setBounds(10, 5, 46, 14);
		panel.add(lblNewLabel);

		textField = new Obs_TextFIeld();
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(69, 5, 312, 20);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				GuiUtil.setWaitCursor(textField,true);
				arama();
				GuiUtil.setWaitCursor(textField,false);
			}
			public void removeUpdate(DocumentEvent e) {
				GuiUtil.setWaitCursor(textField,true);
				arama();
				GuiUtil.setWaitCursor(textField,false);
			}
			public void insertUpdate(DocumentEvent e) {
				GuiUtil.setWaitCursor(textField,true);
				arama();
				GuiUtil.setWaitCursor(textField,false);
			}
		});
		textField.addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorRemoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorMoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorAdded(AncestorEvent pEvent) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						textField.requestFocusInWindow();
					}
				});
			}
		});

		panel.add(textField);
		textField.setColumns(10);

		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		splitPane.setRightComponent(scrollPane);

		table = new JTable() {
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == 127)
				{
					if (table.getSelectedRow() < 0 ) return ;
					sil();
				}
			}
		});
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
			table.setGridColor(oac.gridcolor);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
		hisset ();
		textField.requestFocus();
	}
	public static void hisset()
	{
		try
		{
			long startTime = System.currentTimeMillis(); 
			ResultSet	rs = null;
			GRID_TEMIZLE.grid_temizle(table);
			textField.setText("");
			rs = oac.uSER_ISL.giden_rapor(GLOBAL.KULL_ADI);				
			if (!rs.isBeforeFirst() )
				return;
			GRID_TEMIZLE.grid_temizle(table);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			table.removeColumn(table.getColumnModel().getColumn(0));

			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new TARIH_SAATLI());
			tc.setMinWidth(130);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(200);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(200);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(200);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(200);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(400);

			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(50);

			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(20);
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

			String deger;
			String[] parts;
			Font bigFont;
			deger = GLOBAL.setting_oku("STK_RAPORLAMA").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			table.setFont(bigFont);

		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
	public void arama()  
	{
		if (textField.getText().equals(""))
			table.setRowSorter(null);
		else
		{
			GuiUtil.setWaitCursor(splitPane,true);
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
			sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textField.getText()));
			table.setRowSorter(sorter);
			GuiUtil.setWaitCursor(splitPane,false);
		}
	}
	public static  void sil()
	{			
		int g =  JOptionPane.showOptionDialog( null,  "Secilen Rapor  Silinecek ..?"  ,"Gonderilen Raporlardan Evrak Silme",   JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, 	null,  oac.options, oac.options[1]); //default button
		if(g != 0 ) { return;	}
		if (table.getSelectedRow() < 0 ) return ;
		try
		{
			GuiUtil.setWaitCursor(splitPane,true);
			if( table.getRowSorter() == null)
			{
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				oac.uSER_ISL.giden_rapor_sil(Integer.parseInt(mdl.getValueAt(table.getSelectedRow(),0).toString()));
				hisset();
			}
			else {
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				oac.uSER_ISL.giden_rapor_sil(Integer.parseInt(mdl.getValueAt(table.getRowSorter().convertRowIndexToModel(table.getSelectedRow()),0).toString()));
				hisset();
			}
			GuiUtil.setWaitCursor(splitPane,false);
		}
		catch (Exception ex)
		{
			GuiUtil.setWaitCursor(splitPane,false);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
}
