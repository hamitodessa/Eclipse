package OBS_2025;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.lOG_BILGI;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import javax.swing.JTable;
import javax.swing.JTextArea;
@SuppressWarnings({ "static-access", "serial" })
public class SQL_SORGULAMA extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static CARI_ACCESS c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	static KERESTE_ACCESS ker_Access = new KERESTE_ACCESS(OBS_SIS_2025_ANA_CLASS._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);
	private static JTable table;
	private static String modul = "" ;
	private static JTextArea textArea ;

	public SQL_SORGULAMA(String nerden) {
		setTitle("SQL SORGULAMA");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0,0, 900, 600);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		splitPane.setRightComponent(scrollPane);

		table = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 191, 255)));
		textArea.setFont(new Font("Monospaced", Font.BOLD, 16));
		textArea.setMinimumSize(new Dimension(0, 100));
		textArea.setMaximumSize(new Dimension(0, 100));
		textArea.setLineWrap(true);
		textArea.setDocument(new JTextFieldLimit(100));
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
				{	
					hisset ();
				}
			}
		});
		Border borderr = BorderFactory.createLineBorder(Color.GRAY);

		textArea.setBorder(BorderFactory.createCompoundBorder(borderr,BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		splitPane.setLeftComponent(textArea);
		modul = nerden ;
	}
	public static void hisset ()
	{
		try
		{
			if (textArea.getText().equals("")) return ;
			if (  textArea.getText().toUpperCase().contains("DROP") ) return;
			if (  textArea.getText().toUpperCase().contains("ALTER") ) return;
			if (  textArea.getText().toUpperCase(). contains("DELETE") ) return;  
			if (  textArea.getText().toUpperCase(). contains("INSERT") ) return;  
			if (  textArea.getText().toUpperCase(). contains("UPDATE") ) return;  

			ResultSet rs =null;
			if (modul.equals("cari"))
			{
				String mesaj = "Aranan:" ;
				String mesaj1 = textArea.getText();
				mesaj1 = mesaj1.replace("\n"," ");
				if( mesaj1.length() <= 93)
				{
					mesaj = mesaj +  mesaj1 ;
				}
				else
				{
					mesaj = mesaj +  mesaj1.substring(0, 93  -(mesaj.length())) ;
				}
				lOG_BILGI lBILGI = new lOG_BILGI();
				lBILGI.setmESAJ(mesaj);
				lBILGI.seteVRAK("");
				rs = c_Access.sql_sorgu(textArea.getText(),lBILGI,BAGLAN_LOG.cariLogDizin);

				if (!rs.isBeforeFirst() ) {  
					GRID_TEMIZLE.grid_temizle(table);
					return;
				} 
			}
			else if (modul.equals("stok"))
			{
				String mesaj = "Aranan:" ;
				String mesaj1 = textArea.getText();
				if( mesaj1.length() <= 93)
				{
					mesaj = mesaj +  mesaj1 ;
				}
				else
				{
					mesaj = mesaj +  mesaj1.substring(0, 93  -(mesaj.length())) ;
				}
				lOG_BILGI lBILGI = new lOG_BILGI();
				lBILGI.setmESAJ(mesaj);
				lBILGI.seteVRAK("");
				rs = f_Access.sql_sorgu(textArea.getText(),lBILGI,BAGLAN_LOG.fatLogDizin);

				if (!rs.isBeforeFirst() ) {  
					GRID_TEMIZLE.grid_temizle(table);
					return;
				} 

			}
			else if (modul.equals("kereste"))
			{
				String mesaj = "Aranan:" ;
				String mesaj1 = textArea.getText();
				if( mesaj1.length() <= 93)
				{
					mesaj = mesaj +  mesaj1 ;
				}
				else
				{
					mesaj = mesaj +  mesaj1.substring(0, 93  -(mesaj.length())) ;
				}
				lOG_BILGI lBILGI = new lOG_BILGI();
				lBILGI.setmESAJ(mesaj);
				lBILGI.seteVRAK("");
				rs = ker_Access.sql_sorgu(textArea.getText(),lBILGI,BAGLAN_LOG.kerLogDizin);

				if (!rs.isBeforeFirst() ) {  
					GRID_TEMIZLE.grid_temizle(table);
					return;
				} 

			}
			GRID_TEMIZLE.grid_temizle(table);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();

			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;

			for (int i = 0; i < table.getColumnCount()  ; i ++)
			 {
				tc = tcm.getColumn(i);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(75);
			 }
			//table.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);

		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
			//JOptionPane.showMessageDialog(null, ex.getMessage(),"Sql Sorgulama", JOptionPane.ERROR_MESSAGE);
		}
	}
}
//textArea.setText("SELECT TARIH,SATIRLAR.EVRAK ,IZAHAT,KOD,KUR,BORC,ALACAK,  (BORC / KUR) as USD_BORC ,  (ALACAK / KUR) as USD_ALACAK,\r\n"
//+ "CAST(SUM(ALACAK-BORC) OVER(ORDER BY TARIH  ROWS BETWEEN UNBOUNDED PRECEDING And CURRENT ROW)  AS DECIMAL(30,2))  AS BAKIYE   ,\r\n"
//+ "CAST(SUM((ALACAK/kur)-(BORC/KUR)) OVER(ORDER BY TARIH  ROWS BETWEEN UNBOUNDED PRECEDING And CURRENT ROW)  AS DECIMAL(30,2))  AS USD_BAKIYE   \r\n"
//+ "FROM SATIRLAR WITH (INDEX (IX_SATIRLAR)) INNER JOIN IZAHAT WITH (INDEX (IX_EVRAK))   ON SATIRLAR.EVRAK = IZAHAT.EVRAK\r\n"
//+ "WHERE  HESAP =N'120.01.0001' \r\n"
//+ "AND TARIH BETWEEN  '1900.01.01' AND '2100.12.31 23:59:59.998'  ORDER BY TARIH  ");


