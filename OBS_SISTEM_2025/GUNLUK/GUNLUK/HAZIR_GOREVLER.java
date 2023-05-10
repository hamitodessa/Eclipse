package GUNLUK;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_2025.FILTRE;
import OBS_2025.OBS_MAIN;
import OBS_2025.OBS_SIS_2025_ANA_CLASS;

import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.GUNLUK_ACCESS;
import OBS_C_2025.Gunluk_Bilgi;
import OBS_C_2025.SOLA;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import java.awt.event.KeyAdapter;

@SuppressWarnings("serial")
public class HAZIR_GOREVLER extends JInternalFrame {
	private static JTable table;
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	@SuppressWarnings({ "static-access" })
	private static GUNLUK_ACCESS  g_Access = new GUNLUK_ACCESS(oac._IGunluk , oac._IGunluk_Loger);
	public static JScrollPane scrollPane;
	private JPanel panel;
	private static JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HAZIR_GOREVLER frame = new HAZIR_GOREVLER();
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

		scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		///***************************
		JPopupMenu popup = new JPopupMenu();
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
				} catch (NumberFormatException | ClassNotFoundException | SQLException e1) 
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
				} catch (ClassNotFoundException | PropertyVetoException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		popup.add(menuItem);
		///***********************************************************************************************************************
		table = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {  
				return false;
			}
		};
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if( e.getKeyCode() ==127)
				{
					try {
						grv_tek_sil();
					} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
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
				if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		scrollPane.setViewportView(table);
		panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		panel.setLayout(null);
		splitPane.setRightComponent(panel);
		lblNewLabel = new JLabel("Kayit Sayisi :");
		lblNewLabel.setBounds(10, 5, 150, 14);
		panel.add(lblNewLabel);

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
				gbilgi.isim = " LikE '%' ";
			}
			else
			{
				gbilgi.isim = " ='" + FILTRE.cmbGrv_Isim.getSelectedItem().toString() + "'";
			}

			gbilgi.saat1 = FILTRE.comboBox_75.getSelectedItem().toString();
			gbilgi.saat2 = FILTRE.comboBox_76.getSelectedItem().toString();
			gbilgi.tarih1 = TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_33);
			gbilgi.tarih2 = TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_34);
			rs = g_Access.hazir_gorevler(gbilgi);
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lblNewLabel.setText( String.format("Satir Sayisi : %,d %n" ,  0));
				return;
			}

			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new TARIH());
			tc.setMinWidth(80);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(80);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);

			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);

			tc = tcm.getColumn(7);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(300);
			
			tc = tcm.getColumn(8);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(80);
			
			th.repaint();
			table.removeColumn(table.getColumnModel().getColumn(0));
			table.removeColumn(table.getColumnModel().getColumn(0));

			th.repaint();
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(22);

			Dimension dd = table.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();

			table.setSelectionBackground(Color.PINK);
			table.setSelectionForeground(Color.BLUE);
			lblNewLabel.setText( String.format("Satir Sayisi : %,d %n" ,  table.getRowCount()));


			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
		} 
		catch (Exception ex) 
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Hazir Gorevler", JOptionPane.ERROR_MESSAGE); 
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
	}
	private void grv_tek_sil() throws NumberFormatException, ClassNotFoundException, SQLException
	{
		try
		{
			int g =  JOptionPane.showOptionDialog( null,  "Gorev  Dosyadan Silinecek ..?", "Gunluk Dosyasindan Gorev Silme",   JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,	   			 	null,   	oac.options,   	oac.options[1]); 
			if(g != 0 ) { return;	}

			g_Access.gorev_tek_sil(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
			hisset();
		} 
		catch (Exception ex) 
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Gorev Silme", JOptionPane.ERROR_MESSAGE); 
		}
	}
}