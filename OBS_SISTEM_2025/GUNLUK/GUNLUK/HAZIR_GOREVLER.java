package GUNLUK;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_2025.FILTRE;
import OBS_2025.OBS_MAIN;
import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.ButtonColumn;

import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.GUNLUK_ACCESS;
import OBS_C_2025.Gunluk_Bilgi;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.lOG_BILGI;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import java.awt.event.KeyAdapter;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class HAZIR_GOREVLER extends JInternalFrame {
	private static JTable table;
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static GUNLUK_ACCESS  g_Access = new GUNLUK_ACCESS(OBS_SIS_2025_ANA_CLASS._IGunluk , OBS_SIS_2025_ANA_CLASS._IGunluk_Loger);
	private JPanel panel;
	private static JLabel lbladet ;
	static JPopupMenu popup ;
	public static ScrollPaneWin11 scrollPane ;

	public HAZIR_GOREVLER() {
		setMaximizable(true);
		setResizable(true);
		setBounds(100, 100, 900, 400);
		setIconifiable(true);
		setClosable(true);
		setTitle("HAZIR GOREVLER");
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(1.0);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		scrollPane = new ScrollPaneWin11();
		splitPane.setLeftComponent(scrollPane);
		///***************************
		popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Tek Gorev Sil..", new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-reduce-16.png")));
		menuItem.setMnemonic(KeyEvent.VK_T);
		menuItem.getAccessibleContext().setAccessibleDescription( "Tek Gorev Sil");
		menuItem.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					grv_tek_sil();
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		popup.add(menuItem);  //
		menuItem = new JMenuItem("Gorev Degistir / Sil",new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/sil.png")));
		menuItem.setMnemonic(KeyEvent.VK_S);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					grv_degis_sil();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		popup.add(menuItem);
		///***********************************************************************************************************************
		table = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {  
				switch (column) {
				case 0:
					return true;

				default:
					return false;
				}
			}
		};
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if( e.getKeyCode() ==127)
				{
					try {
						Rectangle qaz =	table.getCellRect(table.getSelectedRow(), table.getSelectedColumn(),false) ;
						popup.show(e.getComponent(), qaz.x, qaz.y);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSurrendersFocusOnKeystroke(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

				}
			}
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				int r = table.rowAtPoint(e.getPoint());
				if (r >= 0 && r < table.getRowCount()) {
					table.setRowSelectionInterval(r, r);
				} else {
					table.clearSelection();
				}
				int rowindex = table.getSelectedRow();
				if (rowindex < 0)
					return;
				if (table.getSelectedColumn() < 1)
					popup.show(e.getComponent(), 50, e.getY());
				if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
		panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		panel.setBorder(new LineBorder(null));
		panel.setLayout(null);
		splitPane.setRightComponent(panel);

		JLabel lblNewLabel = new JLabel("Kayit Sayisi :");
		lblNewLabel.setBounds(10, 5, 85, 14);
		panel.add(lblNewLabel);

		lbladet = new JLabel("0");
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		//lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setBounds(100, 5, 66, 14);
		panel.add(lbladet);

	}
	public static void hisset()
	{
		try 
		{
			long startTime = System.currentTimeMillis();
			ResultSet	rs = null;
			Gunluk_Bilgi gbilgi = new Gunluk_Bilgi();
			if (FILTRE.cmbGrv_Isim.getSelectedItem().toString().equals(""))
			{
				gbilgi.isim = " Like N'%' ";
			}
			else
			{
				gbilgi.isim = " = N'" + FILTRE.cmbGrv_Isim.getSelectedItem().toString() + "'";
			}
			gbilgi.saat1 = FILTRE.comboBox_75.getSelectedItem().toString();
			gbilgi.saat2 = FILTRE.comboBox_76.getSelectedItem().toString();
			gbilgi.tarih1 = TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_33);
			gbilgi.tarih2 = TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_34);
			rs = g_Access.hazir_gorevler(gbilgi);
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText("0");
				return;
			}
			table.setModel(DbUtils.resultSetToTableModel(rs));
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addColumn("");
			table.moveColumn(table.getColumnCount()-1, 0);

			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;

			tc = tcm.getColumn(0);
			Action delete = new AbstractAction()
			{
				public void actionPerformed(ActionEvent e)
				{
					popup.show(table, 50, table.getSelectedRow() * 22 );
				}
			};   
			ButtonColumn buttonColumn = new ButtonColumn(table, delete, 0 ,new ImageIcon(HAZIR_GOREVLER.class.getResource("/ICONLAR/gunluk_sil.png")) );
			buttonColumn.setMnemonic(KeyEvent.VK_D);
			tc.setMinWidth(50);
			tc.setMaxWidth(50);
			tc.setHeaderRenderer(new SOLA());

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new TARIH());
			tc.setMinWidth(80);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SOLA());
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
			tc.setMinWidth(300);

			tc = tcm.getColumn(9);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(80);

			th.repaint();
			table.removeColumn(table.getColumnModel().getColumn(1));
			table.removeColumn(table.getColumnModel().getColumn(1));

			th.repaint();
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(22);

			Dimension dd = table.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();

			//table.setSelectionBackground(Color.PINK);
			//table.setSelectionForeground(Color.BLUE);
			lbladet.setText( FORMATLAMA.doub_0(table.getRowCount()) );


			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
		} 
		catch (Exception ex) 
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
	private void grv_degis_sil() throws PropertyVetoException, ClassNotFoundException, SQLException
	{
		boolean varmi = OBS_MAIN.pencere_bak("GOREV GIRIS");
		if (varmi  ) 
		{
			OBS_MAIN.pencere_aktiv_yap("GOREV GIRIS");
		}
		else
		{
			JInternalFrame internalFrame;
			internalFrame  = new GOREV_GIRIS();
			OBS_MAIN.desktopPane.add(internalFrame);
			internalFrame.setVisible(true);
		}
		GOREV_GIRIS.txtGID.setText(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 1).toString()));
		GOREV_GIRIS.gOKU();
		hisset();
	}
	private void grv_tek_sil() throws NumberFormatException, ClassNotFoundException, SQLException
	{
		try
		{
			int g =  JOptionPane.showOptionDialog( null,  "Gorev  Dosyadan Silinecek ..?", "Gunluk Dosyasindan Gorev Silme",   JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,	   			 	null,   	oac.options,   	oac.options[1]); 
			if(g != 0 ) { return;	}

			String mesaj = "" ;
			mesaj = "Isim="+ table.getModel().getValueAt(table.getSelectedRow(), 4).toString() + " Gorev="+ table.getModel().getValueAt(table.getSelectedRow(), 5).toString() + 
					" Mesaj="  ;
			if( mesaj.length() +  (table.getModel().getValueAt(table.getSelectedRow(), 7).toString() + " Silme ").length() <= 95)
			{
				mesaj = mesaj + " Msj:" + table.getModel().getValueAt(table.getSelectedRow(), 7).toString() + " Silme " ;
			}
			else
			{
				mesaj = mesaj + " Msj:" + table.getModel().getValueAt(table.getSelectedRow(), 7).toString().substring(0, 89  -(mesaj.length()) ) + "Silme" ;
			}
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ(mesaj);
			lBILGI.seteVRAK("");

			g_Access.gorev_tek_sil(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()),
					lBILGI, BAGLAN_LOG.gunLogDizin );
			hisset();
		} 
		catch (Exception ex) 
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
}

