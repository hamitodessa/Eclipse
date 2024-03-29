package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
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
public class KONS_ACIKLAMA extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static KERESTE_ACCESS  ker_Access = new KERESTE_ACCESS(OBS_SIS_2025_ANA_CLASS._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);
	private static JTable table;
	private static Obs_TextFIeld txtKons;
	private static Obs_TextFIeld txtAciklama;

	public KONS_ACIKLAMA() {
		setIconifiable(true);
		setTitle("KONSIMENTO ACIKLAMA");
		setResizable(true);
		setClosable(true);
		setBounds(0, 0,600,400);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(KONS_ACIKLAMA.class.getResource("/ICONLAR/icons8-prototype-30.png")), 16, 16));//
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panel.setMinimumSize(new Dimension(0, 50));
		panel.setMaximumSize(new Dimension(0, 50));
		panel.setLayout(null);
		splitPane.setLeftComponent(panel);
		
		JLabel lblNewLabel = new JLabel("Konsimento");
		lblNewLabel.setBounds(10, 18, 70, 14);
		panel.add(lblNewLabel);
		
		txtKons = new Obs_TextFIeld(15);
		txtKons.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtKons.setBounds(78, 15, 112, 20);
		panel.add(txtKons);
		
		JLabel lblNewLabel_1 = new JLabel("Aciklama");
		lblNewLabel_1.setBounds(200, 18, 60, 14);
		panel.add(lblNewLabel_1);
		
		txtAciklama = new Obs_TextFIeld(50);
		txtAciklama.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAciklama.setBounds(260, 15, 320, 20);
		panel.add(txtAciklama);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		splitPane.setRightComponent(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
			table.setGridColor(oac.gridcolor);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		rs = ker_Access.kons_pln();
		if (!rs.isBeforeFirst() )
			return;
		table.setModel(DbUtils.resultSetToTableModel(rs));
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc;
		tc = tcm.getColumn(0);
		tc.setHeaderRenderer(new SOLA());
		tc.setCellRenderer(new SOLA_ORTA());
		tc.setMinWidth(100);
		tc.setMaxWidth(100);
		
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
		if (txtKons.getText().toString().equals("") )
			return;
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ(txtKons.getText() + " Acik=" + txtAciklama.getText());
		lBILGI.seteVRAK("");
		 try {
			lBILGI.setmESAJ(txtKons.getText() + " Silme");
			lBILGI.seteVRAK("");
			int pak_noString = ker_Access.kons_sil(txtKons.getText(), lBILGI ,BAGLAN_LOG.kerLogDizin);
			lBILGI.setmESAJ(txtKons.getText() + " Acik=" + txtAciklama.getText());
			lBILGI.seteVRAK("");
			ker_Access.kons_kayit(txtKons.getText(), txtAciklama.getText(),pak_noString ,lBILGI ,BAGLAN_LOG.kerLogDizin);
			temizle();
			hisset();
		} catch (Exception ex) {
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	public static void sil() 
	{
		if (table.getRowCount() == 0 || txtKons.getText().toString().equals("") )
			return;
		int g =  JOptionPane.showOptionDialog( null, txtAciklama.getText() + "   Nolu  Konsimento Dosyadan Silinecek ..?", "Kereste Dosyasindan Konsimento Silme",   JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,	   			 	null,   	oac.options,   	oac.options[1]); 
		if(g != 0 ) { return;	}
		
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ(txtKons.getText() + " Acik=" + txtAciklama.getText());
		lBILGI.seteVRAK("");
		 try {
			lBILGI.setmESAJ(txtKons.getText() + " Silme");
			lBILGI.seteVRAK("");
			ker_Access.kons_sil(txtKons.getText(), lBILGI ,BAGLAN_LOG.kerLogDizin);
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
		txtKons.setText(table.getModel().getValueAt(satir, 0).toString());
		txtAciklama.setText(table.getModel().getValueAt(satir, 1).toString());
	}
	private static void temizle()
	{
		txtKons.setText("");
		txtAciklama.setText("");
	}
}
