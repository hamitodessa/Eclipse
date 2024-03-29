package OBS_2025;

import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SOLA;
import OBS_C_2025.SOLA_ORTA;
import OBS_C_2025.lOG_BILGI;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

@SuppressWarnings({"serial","static-access"})
public class KOD_ACIKLAMA extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static KERESTE_ACCESS  ker_Access = new KERESTE_ACCESS(OBS_SIS_2025_ANA_CLASS._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);
	private static JTable table;
	private static Obs_TextFIeld txtKod;
	private static Obs_TextFIeld txtAciklama;

	public KOD_ACIKLAMA() {
		setIconifiable(true);
		setTitle("KOD ACIKLAMA");
		setResizable(true);
		setClosable(true);
		setBounds(0, 0,600,400);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(KOD_ACIKLAMA.class.getResource("/ICONLAR/icons8-news-30.png")), 16, 16));//
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerSize(0);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panel.setMinimumSize(new Dimension(0, 50));
		panel.setMaximumSize(new Dimension(0, 50));
		
		panel.setLayout(null);
		splitPane.setLeftComponent(panel);
		
		JLabel lblNewLabel = new JLabel("Kod");
		lblNewLabel.setBounds(10, 18, 35, 14);
		panel.add(lblNewLabel);
		
		txtKod = new Obs_TextFIeld(2);
		txtKod.setFont(new Font("Tahoma", Font.BOLD, 11));
	
		txtKod.setBounds(50, 15, 60, 20);
		panel.add(txtKod);
		
		JLabel lblNewLabel_1 = new JLabel("Aciklama");
		lblNewLabel_1.setBounds(129, 18, 65, 14);
		panel.add(lblNewLabel_1);
		
		txtAciklama = new Obs_TextFIeld(50);
		txtAciklama.setFont(new Font("Tahoma", Font.BOLD, 11));

		txtAciklama.setBounds(193, 15, 377, 20);
		panel.add(txtAciklama);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		splitPane.setRightComponent(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
			table.setGridColor(oac.gridcolor);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent lse) {
			        if (!lse.getValueIsAdjusting()) {
			        	if (table.getSelectedRow() == -1) return;
			        	getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
						temizle();
						doldur(table.getSelectedRow());
						getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
			        }
			    }
			});
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
		hisset();
	}
	public static void hisset() 
	{
		try {
		ResultSet	rs = null;
		rs = ker_Access.kod_pln();
		if (!rs.isBeforeFirst() )
			return;
		table.setModel(DbUtils.resultSetToTableModel(rs));
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc;
		tc = tcm.getColumn(0);
		tc.setHeaderRenderer(new SOLA());
		tc.setCellRenderer(new SOLA_ORTA());
		tc.setMinWidth(60);
		tc.setMaxWidth(60);
		
		tc = tcm.getColumn(1);
		tc.setHeaderRenderer(new SOLA());
		tc.setCellRenderer(new SOLA_ORTA());

		th.repaint();
		table.setRowSelectionInterval(0, 0);
		table.setRowHeight(22);
		
	    Dimension dd = table.getPreferredSize();
	    dd.height = 30;
	    th.setPreferredSize(dd); 
	    th.repaint();
	    
		//table.setSelectionBackground(Color.PINK);
		//table.setSelectionForeground(Color.BLUE);
		
		doldur(0);
		String deger;
		String[] parts;
		Font bigFont;
		deger = GLOBAL.setting_oku("KER_RAPORLAMA").toString();
		deger = deger.substring(1, deger.length()-1);
		parts = deger.split(",");
		bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
		table.setFont(bigFont);
		} catch (Exception ex) {
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	public static void kaydet() 
	{
		if ( txtKod.getText().toString().equals("") ) {  
			return;
		}
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ(txtKod.getText() + " Acik=" + txtAciklama.getText());
		lBILGI.seteVRAK("");
		 try {
			lBILGI.setmESAJ(txtKod.getText() + " Silme");
			lBILGI.seteVRAK("");
			ker_Access.kod_sil(txtKod.getText(), lBILGI ,BAGLAN_LOG.kerLogDizin);
			lBILGI.setmESAJ(txtKod.getText() + " Acik=" + txtAciklama.getText());
			lBILGI.seteVRAK("");
			ker_Access.kod_kayit(txtKod.getText(), txtAciklama.getText(), lBILGI ,BAGLAN_LOG.kerLogDizin);
			temizle();
			hisset();
		} catch (Exception ex) {
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	public static void sil() 
	{
		if (table.getRowCount() == 0 || txtKod.getText().toString().equals("") )
			return;
		int g =  JOptionPane.showOptionDialog( null,  txtAciklama.getText() + "     Kod Dosyadan Silinecek ..?", "Kereste Dosyasindan d Silme",   JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,	   			 	null,   	oac.options,   	oac.options[1]); 
		if(g != 0 ) { return;	}
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ(txtKod.getText() + " Acik=" + txtAciklama.getText());
		lBILGI.seteVRAK("");
		 try {
			lBILGI.setmESAJ(txtKod.getText() + " Silme");
			lBILGI.seteVRAK("");
			ker_Access.kod_sil(txtKod.getText(), lBILGI ,BAGLAN_LOG.kerLogDizin);
			temizle();
			hisset();
		} catch (Exception ex) {
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private static void doldur(int satir)
	{
		if (table.getRowCount() == 0 ) {  
			temizle();
			return;
		} 
		txtKod.setText(table.getModel().getValueAt(satir, 0).toString());
		txtAciklama.setText(table.getModel().getValueAt(satir, 1).toString());
	}
	private static void temizle()
	{
		txtKod.setText("");
		txtAciklama.setText("");
	}
}
