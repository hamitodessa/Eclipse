package OBS_2025;

import java.awt.Font;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({"serial" , "static-access" , "deprecation"})
public class DVZ_CEVIRME extends JInternalFrame {
	
	
	public static JTable table;
	public static JLabel lblkod ;
	public static JLabel lblunvan ;
	public static JLabel lblcins ;
	private static JLabel lblboskur ;
	private static JLabel label_1 ;
	private JLabel lblNewLabel;
	public static JLabel lblcevrilen;
	public static JSplitPane splitPane ;
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	public DVZ_CEVIRME() {
		
		setTitle("DOVIZE CEVIRME");
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(0, 0, 1250, 600);
		
		splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 30));
		panel.setMaximumSize(new Dimension(0, 30));
		panel.setLayout(null);
		
		lblkod = new JLabel("...");
		lblkod.setForeground(new Color(139, 0, 0));
		lblkod.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblkod.setBounds(10, 8, 116, 14);
		panel.add(lblkod);
		
		lblunvan = new JLabel("...");
		lblunvan.setForeground(new Color(139, 0, 0));
		lblunvan.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblunvan.setBounds(149, 8, 312, 14);
		panel.add(lblunvan);
		
		lblcins = new JLabel("...");
		lblcins.setForeground(new Color(139, 0, 0));
		lblcins.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblcins.setBounds(491, 8, 60, 14);
		panel.add(lblcins);
		
		lblNewLabel = new JLabel("Cevrilen:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(666, 8, 60, 14);
		panel.add(lblNewLabel);
		
		lblcevrilen = new JLabel("...");
		lblcevrilen.setForeground(new Color(0, 0, 128));
		lblcevrilen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblcevrilen.setBounds(736, 8, 66, 14);
		panel.add(lblcevrilen);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (lblkod.getText().equals("...")) return ;
					if (DecimalFormat.getNumberInstance().parse(lblboskur.getText()).doubleValue() == 0) return ;
				} catch (Exception ex) {
				
				}
				boolean varmi = OBS_MAIN.pencere_bak("BOS KUR");
                if (varmi  ) 
                	{
                	try {
                		OBS_MAIN.pencere_aktiv_yap("BOS KUR");
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
                	return;
                 	}
                else
                {
                getContentPane().setCursor(oac.WAIT_CURSOR);
				 JInternalFrame internalFrame;
				 internalFrame = new BOS_KUR();
				 OBS_MAIN.desktopPane.add(internalFrame);
				 internalFrame.setVisible(true);
				 BOS_KUR.hisset();
				 getContentPane().setCursor(oac.DEFAULT_CURSOR);
                }
			}
		});
		btnNewButton.setIcon(new ImageIcon(DVZ_CEVIRME.class.getResource("/ICONLAR/icons8-view-16.png")));
		btnNewButton.setBounds(970, 4, 30, 23);
		panel.add(btnNewButton);
		
		JLabel lblBosKur = new JLabel("Bos Kur :");
		lblBosKur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBosKur.setBounds(848, 8, 60, 14);
		panel.add(lblBosKur);
		
		lblboskur = new JLabel("...");
		lblboskur.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblboskur.setForeground(Color.RED);
		lblboskur.setBounds(918, 8, 53, 14);
		panel.add(lblboskur);
		
		JLabel lblKayitSayisi = new JLabel("Satir Sayisi :");
		lblKayitSayisi.setHorizontalAlignment(SwingConstants.LEFT);
		lblKayitSayisi.setBounds(1022, 8, 85, 14);
		panel.add(lblKayitSayisi);
		
		label_1 = new JLabel("0");
		label_1.setForeground(new Color(0, 0, 128));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(1112, 8, 60, 14);
		panel.add(label_1);
		
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		splitPane.setRightComponent(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                if (col == 5)
                {
                	if (getValueAt(row,5) != null)
                	{
                	double tut = (double)getValueAt(row,5);
                		if (tut < 0)
                		{
                		c.setForeground(new Color(128,0,0));
                		 Font fnt = new Font(table.getFont().getFontName(),1 ,table.getFont().getSize());
      		            c.setFont(fnt);
                		}
                		else
                		{
                          c.setForeground(super.getForeground());
                		}
                	}
                }
                if (col == 6)
                {
                	if (getValueAt(row,6) != null)
                	{
                	double tut = Double.parseDouble(getValueAt(row,6).toString());
                		if (tut < 0)
                		{
                		c.setForeground(new Color(128,0,0));
                		 Font fnt = new Font(table.getFont().getFontName(),1 ,table.getFont().getSize());
      		            c.setFont(fnt);
                		}
                		else
                		{
                          c.setForeground(super.getForeground());
                		}
                	}
                }
                return c;
            }
		};
		table.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						table.requestFocusInWindow();
					}
				});
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});

		table.getTableHeader().setReorderingAllowed(false);
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					String[] parts;
					String deger ;
					deger = GLOBAL.setting_oku("PRG_FILTRE").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
							getContentPane().setCursor(oac.WAIT_CURSOR);
							OBS_MAIN.btnFiltre.doClick();
							getContentPane().setCursor(oac.DEFAULT_CURSOR);
		                }
					}
					}
					catch (Exception ex)
					{
						
					}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					 boolean varmi = OBS_MAIN.pencere_bak("DEKONT");
					 if (varmi  ) 
	             		{
	             	try {
	             		OBS_MAIN.pencere_aktiv_yap("DEKONT");
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	              	}
					 else
	                {
						 JInternalFrame internalFrame;
						 internalFrame  = new DEKONT();
						 OBS_MAIN.desktopPane.add(internalFrame);
						 internalFrame.setVisible(true);
		            }
					try 
					{
						DEKONT.txtevrak.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
						DEKONT.fiskont();
					} 
					catch (NumberFormatException e1) 
					{
						e1.printStackTrace();
					}
					
				}
			}
		});
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		scrollPane.setViewportView(table);

		table.requestFocus();


	}
	public static void hisset ()
	{
		if ( FILTRE.txtdvz.getText().equals("000000000000")) return ;
		if ( FILTRE.txtdvz.getText().equals("")) return ;
		if ( FILTRE.lblNewLabel_2_1.getText().equals("...")) return ;
		long startTime = System.currentTimeMillis(); 
		try
		{
			String islem  = "" ;
			ResultSet	rs = null;
			String hKURString = "";
			if (FILTRE.chckbxNewCheckBox_4.isSelected())
			{
				hKURString = "Kayitli";
				islem = "/" ;
			}
			else 
			{
				if (  GLOBAL.setting_oku("PRG_PARA").toString().equals(FILTRE.lblNewLabel_2_1.getText()))
				{
					islem = "/" ;
				}
				else
				{
					islem = "*" ;
				}
			}

			rs = c_Access.dvz_cevirme(FILTRE.comboBox_2.getItemAt(FILTRE.comboBox_2.getSelectedIndex()),
					FILTRE.txtdvz.getText(),
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_3),
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_4),
					FILTRE.comboBox_1.getItemAt(FILTRE.comboBox_1.getSelectedIndex()), islem,hKURString);
			GRID_TEMIZLE.grid_temizle(table);
			if (rs == null)
			{
				return;
			}
			if (!rs.isBeforeFirst() ) {  
				label_1.setText("0");
				lblboskur.setText("...");
				return;
			} 
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;

			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new TARIH());
			tc.setMinWidth(70);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(60);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(430);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(4,false));
			tc.setMinWidth(60);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(100);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(100);

			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);

			tc = tcm.getColumn(7);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(4,false));
			tc.setMinWidth(60);

			tc = tcm.getColumn(8);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(100);

			tc = tcm.getColumn(9);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);

			tc = tcm.getColumn(10);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);

			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);

			int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
			table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
			table.setRowSelectionInterval(lastRow, lastRow);

			/*
			 * BOS Kur
			 */
			int boskur = 0 ;
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			for (int i = 0; i <= model.getRowCount() - 1 ; i ++)
			{
				double kur = (double) model.getValueAt(i , 3);
				if ( kur == 1 )
				{
					boskur += 1 ;
				}
			}
			lblboskur.setText(FORMATLAMA.doub_0(boskur));
			label_1.setText(FORMATLAMA.doub_0(model.getRowCount()));
			//table.setSelectionBackground(Color.PINK);
			//table.setSelectionForeground(Color.BLUE);
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

			String deger;
			String[] parts;
			Font bigFont;
			deger = GLOBAL.setting_oku("CARI_MIZAN").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			table.setFont(bigFont);
		}
		catch (Exception ex )
		{
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Dovize Cevirme", JOptionPane.ERROR_MESSAGE);   
		}
	}
}
