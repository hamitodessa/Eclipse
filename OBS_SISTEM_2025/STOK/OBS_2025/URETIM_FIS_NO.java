package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.commons.lang.StringUtils;

import com.toedter.calendar.JDateChooser;

import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.TARIH_SAATLI;
import net.proteanit.sql.DbUtils;

import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class URETIM_FIS_NO extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	@SuppressWarnings("static-access")
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);


	private JPanel panel_1;
	private JTable table;
	private JLabel lbladet;
	private JCheckBox chckbxNewCheckBox ;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1;
	private JFormattedTextField formattedTextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					URETIM_FIS_NO frame = new URETIM_FIS_NO();
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
	@SuppressWarnings("static-access")
	public URETIM_FIS_NO() {
		setTitle("URETIM FIS NO YENILEME");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 1100, 600);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 60));
		panel.setMaximumSize(new Dimension(0, 60));
		panel.setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(10, 11, 59, 30);
		panel.add(toolBar);

		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Yenile");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected())
				{
					getContentPane().setCursor(oac.WAIT_CURSOR);
					hisset(TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1));
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				}
				else
				{
					getContentPane().setCursor(oac.WAIT_CURSOR);
					hisset("1900.01.01", "2100.12.31");
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(URETIM_FIS_NO.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		toolBar.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setToolTipText("Kaydet");
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected())
				{
					if (formattedTextField.getText().equals(""))
					{
						return ;
					}
				}
				int g =  JOptionPane.showOptionDialog( null, "Yeniden Numaralama Yapilacak  ..?" ,
						"Imalat Yeniden Numaralama",   JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,     //no custom icon
						oac.options,  //button titles
						oac.options[1]); //default button
				if(g != 0 ) { return;	}
				getContentPane().setCursor(oac.WAIT_CURSOR);
				kaydet();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(URETIM_FIS_NO.class.getResource("/ICONLAR/save.png")));
		toolBar.add(btnNewButton_1);

		chckbxNewCheckBox = new JCheckBox("Belli Tarih Araligi");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected())
				{
					panel_1.setVisible(true);
				}
				else
				{
					panel_1.setVisible(false);
				}
			}
		});
		chckbxNewCheckBox.setBounds(187, 8, 143, 23);
		panel.add(chckbxNewCheckBox);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Degiskenler", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		panel_1.setBounds(336, 5, 517, 50);
		panel_1.setVisible(false);
		panel.add(panel_1);
		panel_1.setLayout(null);

		dateChooser = new JDateChooser();
		dateChooser.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dateChooser.setDate(new Date());
				}
			}
		});
		dateChooser.setBounds(20, 18, 110, 20);
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser.setDateFormatString("dd.MM.yyyy");
		dateChooser.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_1.add(dateChooser);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dateChooser_1.setDate(new Date());
				}
			}
		});
		dateChooser_1.setBounds(140, 18, 110, 20);
		dateChooser_1.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		dateChooser_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(dateChooser_1);

		JLabel lblNewLabel = new JLabel("Baslangic No");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(275, 20, 99, 14);
		panel_1.add(lblNewLabel);

		formattedTextField = new JFormattedTextField();
		formattedTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField.setBounds(384, 18, 70, 20);
		panel_1.add(formattedTextField);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(0);
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);

		splitPane.setRightComponent(splitPane_1);

		JPanel panel_3 = new JPanel();
		splitPane_1.setRightComponent(panel_3);
		panel_3.setMinimumSize(new Dimension(0, 25));
		panel_3.setMaximumSize(new Dimension(0, 25));
		panel_3.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_3.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Kayit Sayisi  :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 5, 78, 14);
		panel_3.add(lblNewLabel_1);

		lbladet = new JLabel("0");
		lbladet.setForeground(Color.BLUE);
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setBounds(98, 5, 46, 14);
		panel_3.add(lbladet);

		JScrollPane scrollPane = new JScrollPane();
		splitPane_1.setLeftComponent(scrollPane);

		table = new JTable();
		table.setGridColor(oac.gridcolor);
		scrollPane.setViewportView(table);
		getContentPane().setCursor(oac.WAIT_CURSOR);
		hisset("1900.01.01", "2100.12.31");
		getContentPane().setCursor(oac.DEFAULT_CURSOR);
	}
	public void hisset(String t1,String t2)
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			rs = f_Access.uret_doldur(t1,t2);
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				table.removeColumn(table.getColumnModel().getColumn(9));
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;

				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setCellRenderer(new TARIH_SAATLI());
				tc.setMinWidth(100);

				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(80);

				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(80);

				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(80);

				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);

				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(200);

				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(60);

				Dimension dd = th.getPreferredSize();
				dd.height = 30;
				th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);

				int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
				table.setRowSelectionInterval(lastRow, lastRow);

				table.setSelectionBackground(Color.PINK);
				table.setSelectionForeground(Color.BLUE);

				//***
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				lbladet.setText(FORMATLAMA.doub_0(mdl.getRowCount()));
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
		} 
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Imalat Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void kaydet() 
	{
		Runnable runner = new Runnable()
		{ @SuppressWarnings("static-access")
		public void run() {
			try
			{
				getContentPane().setCursor(oac.WAIT_CURSOR);
				String fisno  = "" , yeni_no = "" ;
				int kj = 0,y_no = 0 ;
				//  '************* B1 e yaz **************************
				if (chckbxNewCheckBox.isSelected())
				{
					y_no =  DecimalFormat.getNumberInstance().parse(formattedTextField.getText()).intValue(); 
				}
				else
				{
					y_no = 1 ;
				}
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				Progres_Bar_Temizle();
				OBS_MAIN.progressBar.setStringPainted(true);
				OBS_MAIN.progressBar.setMaximum(mdl.getRowCount()-1); 
				for (int  i = 0; i<=  mdl.getRowCount() - 1;i++)
				{
					Progres_Bar( mdl.getRowCount()  - 1, i);
					fisno =  mdl.getValueAt(i,0).toString() ;
					kj = 10 - Integer.toString(y_no).length() ;
					yeni_no =StringUtils.repeat("0", kj)   + Integer.toString(y_no);

					f_Access.uret_no_degis(fisno, yeni_no);

					y_no += 1;
				}
				//  '************* B1 den evrak noya yaz **************************
				if (chckbxNewCheckBox.isSelected())
				{
					y_no =  DecimalFormat.getNumberInstance().parse(formattedTextField.getText()).intValue(); 
				}
				else
				{
					y_no = 1 ;
				}
				Progres_Bar_Temizle();
				OBS_MAIN.progressBar.setStringPainted(true);
				OBS_MAIN.progressBar.setMaximum(mdl.getRowCount()-1); 
				for (int  i = 0; i<=  mdl.getRowCount() - 1;i++)
				{
					Progres_Bar( mdl.getRowCount()  - 1, i);
					kj = 10 - Integer.toString(y_no).length() ;
					yeni_no =StringUtils.repeat("0", kj)   + Integer.toString(y_no);

					f_Access.uret_b1_degis( yeni_no);

					y_no += 1;
				}
				//  '*************  B1 sifirla **************************
				if (chckbxNewCheckBox.isSelected())
				{
					y_no =  DecimalFormat.getNumberInstance().parse(formattedTextField.getText()).intValue(); 
				}
				else
				{
					y_no = 1 ;
				}
				Progres_Bar_Temizle();
				OBS_MAIN.progressBar.setStringPainted(true);
				OBS_MAIN.progressBar.setMaximum(mdl.getRowCount()-1); 
				for (int  i = 0; i<=  mdl.getRowCount() - 1;i++)
				{
					Progres_Bar( mdl.getRowCount()  - 1, i);
					kj = 10 - Integer.toString(y_no).length() ;
					yeni_no =StringUtils.repeat("0", kj)   + Integer.toString(y_no);

					f_Access.uret_b1_sifir( yeni_no);

					y_no += 1;
				}
				//  '*************  IZAHAT DUZELT **************************
				if (chckbxNewCheckBox.isSelected())
				{
					y_no =  DecimalFormat.getNumberInstance().parse(formattedTextField.getText()).intValue(); 
				}
				else
				{
					y_no = 1 ;
				}
				Progres_Bar_Temizle();
				OBS_MAIN.progressBar.setStringPainted(true);
				OBS_MAIN.progressBar.setMaximum(mdl.getRowCount()-1); 
				for (int  i = 0; i<=  mdl.getRowCount() - 1;i++)
				{
					Progres_Bar( mdl.getRowCount()  - 1, i);
					kj = 10 - Integer.toString(y_no).length() ;
					yeni_no =StringUtils.repeat("0", kj)   + Integer.toString(y_no);

					f_Access.uret_izahat_duzelt( yeni_no);

					y_no += 1;
				}
				//  '*************  IZAHAT CIKIS DUZELT **************************
				if (chckbxNewCheckBox.isSelected())
				{
					y_no =  DecimalFormat.getNumberInstance().parse(formattedTextField.getText()).intValue(); 
				}
				else
				{
					y_no = 1 ;
				}
				Progres_Bar_Temizle();
				OBS_MAIN.progressBar.setStringPainted(true);
				OBS_MAIN.progressBar.setMaximum(mdl.getRowCount()-1); 
				for (int  i = 0; i<=  mdl.getRowCount() - 1;i++)
				{
					Progres_Bar( mdl.getRowCount()  - 1, i);
					kj = 10 - Integer.toString(y_no).length() ;
					yeni_no =StringUtils.repeat("0", kj)   + Integer.toString(y_no);

					f_Access.uret_izahat_cikis_duzelt( yeni_no, mdl.getValueAt(i,0).toString());

					y_no += 1;
				}	
				//  '*************  ACIKLAMA  DUZELT **************************
				if (chckbxNewCheckBox.isSelected())
				{
					y_no =  DecimalFormat.getNumberInstance().parse(formattedTextField.getText()).intValue(); 
				}
				else
				{
					y_no = 1 ;
				}
				Progres_Bar_Temizle();
				OBS_MAIN.progressBar.setStringPainted(true);
				OBS_MAIN.progressBar.setMaximum(mdl.getRowCount()-1); 
				for (int  i = 0; i<=  mdl.getRowCount() - 1;i++)
				{
					Progres_Bar( mdl.getRowCount()  - 1, i);
					kj = 10 - Integer.toString(y_no).length() ;
					yeni_no =StringUtils.repeat("0", kj)   + Integer.toString(y_no);

					f_Access.uret_aciklama_duzelt( yeni_no,  Integer.parseInt( mdl.getValueAt(i,9).toString()));

					y_no += 1;
				}	
				Progres_Bar_Temizle();
				OBS_MAIN.progressBar.setStringPainted(true);
				OBS_MAIN.progressBar.setMaximum(mdl.getRowCount()-1); 
				//**
				f_Access.uretim_fisno_yapilanma_kayit(y_no - 1) ;
				//**
				if (chckbxNewCheckBox.isSelected())
				{
					hisset(TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1));
				}
				else
				{
					hisset("1900.01.01", "2100.12.31");
				}
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				JOptionPane.showMessageDialog(null,  "Yeniden Numaralandirma Islemi Basari ile Bitirilmistir",  "Imalat Yeniden Numaralama", JOptionPane.PLAIN_MESSAGE);
			}
			catch (Exception ex)
			{
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				JOptionPane.showMessageDialog(null,  ex.getMessage(), "Imalat Fis No Degisme", JOptionPane.ERROR_MESSAGE);
			}
		}
		};
		//// Progress Bar
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}	
	static void Progres_Bar(int max, int deger) throws InterruptedException
	{

		OBS_MAIN.progressBar.setValue(deger);
	}
	static void Progres_Bar_Temizle()
	{
		OBS_MAIN.progressBar.setMaximum(0);
		OBS_MAIN.progressBar.setValue(0);
		OBS_MAIN.progressBar.setStringPainted(false);
	}
}
