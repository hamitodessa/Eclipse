package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;

import javax.swing.JPanel;
import raven.toast.Notifications;

@SuppressWarnings({ "serial", "static-access", "deprecation" })
public class KASA extends JInternalFrame {

	private JTable table_1;
	private static JTextField textField;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_6;
	private JDateChooser dateChooser;
	private static JLabel lblNewLabel_4;
	private static JLabel lblNewLabel_5;
	private static JLabel lblNewLabel_4_1;
	private static JLabel lblNewLabel_5_1;
	private static JLabel lblNewLabel_4_2;
	private static JLabel lblNewLabel_5_2;
	private static JLabel lblNewLabel_4_1_1;
	private static JLabel lblNewLabel_4_1_2;
	private static JLabel lblNewLabel_5_2_1;

	private static JLabel lblNewLabel_3;
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar, OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	double double_1 = 0;
	double double_2 = 0;
	double double_3 = 0;
	double double_4 = 0;
	String myDate;
	String myDate1;
	ResultSet rs = null;

	public KASA() {
		setTitle("GUNLUK TAKIP");
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 748, 600);

		getContentPane().setLayout(new BorderLayout());
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		scrollPane.setMinimumSize(new Dimension(0, 75));
		scrollPane.setMaximumSize(new Dimension(0, 75));
		splitPane.setLeftComponent(scrollPane);
		
		JPanel leftPanel = new JPanel();
		
		leftPanel.setPreferredSize(new Dimension(718,65));
		leftPanel.setLayout(null);
		scrollPane.setViewportView(leftPanel);
		
		JLabel lblNewLabel = new JLabel("Hesap");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 14, 46, 14);
		leftPanel.add(lblNewLabel);

		textField = new JTextField();
		textField.setDocument(new JTextFieldLimit(12));
		InputMap txtkoduMap = textField.getInputMap();
		txtkoduMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK), "foo");
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				try {
					getContentPane().setCursor(oac.WAIT_CURSOR);
					isimoku_ekstre();
					yenile();
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			public void removeUpdate(DocumentEvent e) {
				try {
					getContentPane().setCursor(oac.WAIT_CURSOR);
					isimoku_ekstre();
					yenile();
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			public void insertUpdate(DocumentEvent e) {
				try {
					getContentPane().setCursor(oac.WAIT_CURSOR);
					isimoku_ekstre();
					yenile();
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					String[] parts;
					String deger;
					deger = GLOBAL.setting_oku("CARI_HSPPLN_CAG").toString();
					parts = deger.split(",");
					if (!parts[2].equals(" ")) {
						char c = parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers()
								& (parts[0].equals("E") ? KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK)) != 0)) {
							HESAP_PLN hsp;
							getContentPane().setCursor(oac.WAIT_CURSOR);
							hsp = new HESAP_PLN();
							hsp.show();
							textField.setText(oac.hsp_hsp_kodu);
							getContentPane().setCursor(oac.DEFAULT_CURSOR);
						}
					}
				} catch (Exception ex) {

				}
			}
		});
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					HESAP_PLN hsp;
					try {
						hsp = new HESAP_PLN();
						hsp.show();
						textField.setText(oac.hsp_hsp_kodu);
						getContentPane().setCursor(oac.WAIT_CURSOR);
						isimoku_ekstre();
						yenile();
						getContentPane().setCursor(oac.DEFAULT_CURSOR);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(87, 11, 125, 20);

		leftPanel.add(textField);

		lblNewLabel_1 = new JLabel("...");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_1.setBounds(87, 40, 360, 14);
		leftPanel.add(lblNewLabel_1);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(320, 11, 150, 30);
		dateChooser.setDateFormatString("dd.MM.yyyy");
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 14));
		dateChooser.setDate(new Date());
		dateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if ("date".equals(e.getPropertyName())) {
					getContentPane().setCursor(oac.WAIT_CURSOR);
					yenile();
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				}
			}
		});
		dateChooser.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, -1);
						dateChooser.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days

						dateChooser.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		leftPanel.add(dateChooser);

		JButton btnNewButton_1_2 = new JButton("<<");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
				Date date;
				try {
					date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser));
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					cal.add(Calendar.DAY_OF_MONTH, -1);
					dateChooser.setDate(new Date(cal.getTimeInMillis()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_2.setToolTipText("Geri");
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1_2.setBounds(484, 11, 50, 30);
		leftPanel.add(btnNewButton_1_2);

		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooser.setDate(new Date());
			}
		});
		btnNewButton_1_1.setToolTipText("Kayitli Son Dekont");
		btnNewButton_1_1.setBounds(538, 11, 55, 30);
		btnNewButton_1_1.setIcon(new ImageIcon(DEKONT.class.getResource("/ICONLAR/icons8-open-view-24.png")));
		leftPanel.add(btnNewButton_1_1);

		JButton btnNewButton_1 = new JButton(">>");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
				Date date;
				try {
					date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser));
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
					dateChooser.setDate(new Date(cal.getTimeInMillis()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setToolTipText("Ileri");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(597, 11, 50, 30);
		leftPanel.add(btnNewButton_1);

		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(222, 15, 46, 14);
		leftPanel.add(lblNewLabel_6);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setDividerSize(0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);
		
		ScrollPaneWin11 scrollPane_1 = new ScrollPaneWin11();
		 scrollPane_1.setMinimumSize(new Dimension(0, 100));
		 scrollPane_1.setMaximumSize(new Dimension(0, 100));
		splitPane_1.setRightComponent(scrollPane_1);
		
		JPanel rightPanel = new JPanel();
		
		rightPanel.setPreferredSize(new Dimension(718,90));
		rightPanel.setLayout(null);
		scrollPane_1.setViewportView(rightPanel);
		
		lblNewLabel_4 = new JLabel("0.00");
		lblNewLabel_4.setForeground(new Color(0, 0, 139));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(503, 11, 96, 14);
		rightPanel.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("0.00");
		lblNewLabel_5.setForeground(new Color(0, 0, 139));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(400, 11, 100, 14);
		rightPanel.add(lblNewLabel_5);

		lblNewLabel_4_1 = new JLabel("0.00");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1.setBounds(503, 31, 96, 14);
		rightPanel.add(lblNewLabel_4_1);

		lblNewLabel_4_2 = new JLabel("0.00");
		lblNewLabel_4_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2.setBounds(503, 56, 96, 14);
		rightPanel.add(lblNewLabel_4_2);

		lblNewLabel_5_1 = new JLabel("0.00");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5_1.setBounds(400, 31, 100, 14);
		rightPanel.add(lblNewLabel_5_1);

		lblNewLabel_5_2 = new JLabel("0.00");
		lblNewLabel_5_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5_2.setBounds(400, 56, 100, 14);
		rightPanel.add(lblNewLabel_5_2);

		lblNewLabel_5_2_1 = new JLabel("0.00");
		lblNewLabel_5_2_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_5_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5_2_1.setBounds(609, 56, 111, 14);
		rightPanel.add(lblNewLabel_5_2_1);

		lblNewLabel_4_1_1 = new JLabel("0.00");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1_1.setBounds(624, 31, 96, 14);
		rightPanel.add(lblNewLabel_4_1_1);

		lblNewLabel_4_1_2 = new JLabel("0.00");
		lblNewLabel_4_1_2.setForeground(new Color(0, 0, 139));
		lblNewLabel_4_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1_2.setBounds(624, 11, 96, 14);
		rightPanel.add(lblNewLabel_4_1_2);

		JLabel lblNewLabel_2 = new JLabel("Satir Sayisi :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 65, 85, 14);
		rightPanel.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("0");
		lblNewLabel_3.setForeground(new Color(0, 0, 128));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(100, 65, 43, 14);
		rightPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_5_3 = new JLabel("Gunluk Toplam");
		lblNewLabel_5_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5_3.setBounds(290, 11, 100, 14);
		rightPanel.add(lblNewLabel_5_3);

		JLabel lblNewLabel_5_1_1 = new JLabel("Eski Donem");
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5_1_1.setBounds(290, 31, 100, 14);
		rightPanel.add(lblNewLabel_5_1_1);

		JLabel lblNewLabel_5_2_2 = new JLabel("+ / - Bakiye");
		lblNewLabel_5_2_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5_2_2.setBounds(290, 56, 100, 14);
		rightPanel.add(lblNewLabel_5_2_2);
		
		//*********************************************ORTA************************************************
		table_1 = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table_1.setGridColor(oac.gridcolor);
		}

		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					boolean varmi = OBS_MAIN.pencere_bak("DEKONT");
					if (varmi) {
						try {
							OBS_MAIN.pencere_aktiv_yap("DEKONT");
						} catch (PropertyVetoException e1) {
							e1.printStackTrace();
						}
					} else {
						JInternalFrame internalFrame;
						internalFrame = new DEKONT();
						OBS_MAIN.desktopPane.add(internalFrame);
						internalFrame.setVisible(true);
					}
					try {
						DEKONT.txtevrak.setText(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
						DEKONT.fiskont();
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		table_1.setShowHorizontalLines(true);
		table_1.setShowVerticalLines(true);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		ScrollPaneWin11 scrollPane_2 = new ScrollPaneWin11();
		splitPane_1.setLeftComponent(scrollPane_2);
		scrollPane_2.setViewportView(table_1);
	}
	public void isimoku_ekstre() throws ClassNotFoundException, SQLException {
		ResultSet rs = null;
		rs = c_Access.hesap_adi_oku(textField.getText());
		if (!rs.isBeforeFirst()) {
			lblNewLabel_1.setText("");
			lblNewLabel_6.setText("");
			return;
		}
		rs.next();
		lblNewLabel_1.setText(rs.getString("UNVAN"));
		lblNewLabel_6.setText(rs.getString("HESAP_CINSI"));
	}

	private void yenile() {
		hisset();
	}

	private void hisset() {
		try {
			long startTime = System.currentTimeMillis();
			rs = c_Access.kasa_kontrol(textField.getText(), TARIH_CEVIR.tarih_geri_kasa(dateChooser));
			if (!rs.isBeforeFirst()) {
				GRID_TEMIZLE.grid_temizle(table_1);
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + 0 + " saniye");
			} else {
				GRID_TEMIZLE.grid_temizle(table_1);
				table_1.setModel(DbUtils.resultSetToTableModel(rs));
				JTableHeader th = table_1.getTableHeader();

				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;

				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				// tc.setHeaderRenderer(new ORTA());
				// tc.setCellRenderer(new COLUMN_ORTALA());
				tc.setMinWidth(50);
				tc.setMaxWidth(50);

				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(412);

				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				tc.setMaxWidth(50);

				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2, false));
				tc.setMinWidth(100);

				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2, false));
				tc.setMinWidth(100);

				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				tc.setMaxWidth(50);

				table_1.setRowHeight(21);
				Dimension dd = th.getPreferredSize();
				dd.height = 30;
				th.setPreferredSize(dd);
				th.repaint();
				// table_1.setRowSelectionInterval(0, 0);
				table_1.setRowHeight(20);
				table_1.repaint();
			}
			// ********** Guncel Bakiye
			guncel_bakiye();
			// ********** ONCEKI Bakiye
			onceki_bakiye();
			// ****** TOPLAMLARI YAZ
			lblNewLabel_5_2.setText(FORMATLAMA.doub_2(double_1 + double_3));
			lblNewLabel_4_2.setText(FORMATLAMA.doub_2(double_2 + double_4));
			lblNewLabel_5_2_1.setText(FORMATLAMA.doub_2((double_2 + double_4) - (double_1 + double_3)));
			lblNewLabel_3.setText(FORMATLAMA.doub_0(table_1.getRowCount()));
			// ******
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime;
			double seconds = (double) estimatedTime / 1000;
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) + " saniye");

			String deger;
			String[] parts;
			Font bigFont;
			deger = GLOBAL.setting_oku("CARI_GUNLUK").toString();
			deger = deger.substring(1, deger.length() - 1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			table_1.setFont(bigFont);
		} catch (Exception ex) {
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
			//JOptionPane.showMessageDialog(null, ex.getMessage(), "Gunluk Islem", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void guncel_bakiye() throws SQLException, ClassNotFoundException {
		myDate = TARIH_CEVIR.tarih_geri_SQL(dateChooser);
		myDate1 = TARIH_CEVIR.tarih_geri_SQL(dateChooser);
		rs = null;
		rs = c_Access.kasa_mizan(textField.getText(), myDate, myDate1);
		if (!rs.isBeforeFirst()) {
			double_1 = 0;
			double_2 = 0;
		} else {
			rs.next();
			double_1 = rs.getDouble("islem");
			double_2 = rs.getDouble("islem2");
		}
		lblNewLabel_5.setText(FORMATLAMA.doub_2(double_1));
		lblNewLabel_4.setText(FORMATLAMA.doub_2(double_2));
		lblNewLabel_4_1_2.setText(FORMATLAMA.doub_2(double_2 - double_1));
	}

	private void onceki_bakiye() throws ClassNotFoundException, SQLException {
		myDate = TARIH_CEVIR.tarih_geri_SQL(dateChooser);
		myDate1 = TARIH_CEVIR.chooser_string_eksi1(dateChooser);
		rs = null;
		rs = c_Access.kasa_mizan(textField.getText(), "1900.01.01", myDate1);
		if (!rs.isBeforeFirst()) {
			double_3 = 0;
			double_4 = 0;
		} else {
			rs.next();
			double_3 = rs.getDouble("islem");
			double_4 = rs.getDouble("islem2");
		}
		lblNewLabel_5_1.setText(FORMATLAMA.doub_2(double_3));
		lblNewLabel_4_1.setText(FORMATLAMA.doub_2(double_4));
		lblNewLabel_4_1_1.setText(FORMATLAMA.doub_2(double_4 - double_3));
	}
}
