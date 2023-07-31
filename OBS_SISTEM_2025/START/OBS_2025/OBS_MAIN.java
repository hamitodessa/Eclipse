package OBS_2025;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import GUNLUK.GOREV_GIRIS;
import GUNLUK.Gunluk;
import GUNLUK.HAZIR_GOREVLER;

import javax.swing.JDesktopPane;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

import OBS_2025_RAPORLAR.ENVANTER;
import OBS_2025_RAPORLAR.FATURA_RAPOR;
import OBS_2025_RAPORLAR.GRUP_RAPOR;
import OBS_2025_RAPORLAR.IMALAT_GRUP_RAPOR;
import OBS_2025_RAPORLAR.IMALAT_RAPORLAMA;
import OBS_2025_RAPORLAR.IRSALIYE_RAPOR;
import OBS_2025_RAPORLAR.ORTALAMA_FIAT;
import OBS_2025_RAPORLAR.RECETE_RAPOR;
import OBS_2025_RAPORLAR.STOK_DETAY;
import OBS_2025_RAPORLAR.STOK_RAPOR;
import OBS_2025_RAPORLAR.URUN_LISTE;
import OBS_2025_RAPORLAR.ZAYI_RAPOR;
import OBS_C_2025.DesktopScrollPane;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.TARIH_CEVIR;

import javax.swing.JMenuBar;
import javax.swing.JSeparator;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings({"serial","static-access"})
public class OBS_MAIN extends JFrame {

	/**
	 * 
	 */
	
	//Cari
	private JButton btnNewButton_5 ; //Hesap Plani
	private JButton btnNewButton_4 ; // Dekont 
	private JButton btnNewButton_6 ;// Coklu Giris
	public static JButton btnNewButton_7  ; // Ekstre
	private JButton btnNewButton_8 ; //Mizan
	private JButton btnNewButton_9 ;// Dovize Cevirme
	private JButton button ; // Ozel Mizan
	private JButton btnNewButton_10; //Hesap Bak
	private JButton btnNewButton_11 ;// Cari Arama
	private JButton btnNewButton_12 ; // Gunluk Dokum
	private JButton btnNewButton_13 ;//Hesap Plani detay
	private JButton btnNewButton_15 ;//Gunluk Takip
	private JButton btnNewButton_16; //SQL
	private JButton btnNewButton_17;//Ornek Hesap Plani
	private JButton btnNewButton_18;//Kod Degisme
	private JButton btnNewButton_19;//Yil Sonu
	private JButton btnNewButton_22;//Firma Ismi
	//KUR
	private JButton btnNewButton_32;//Kur Giris
	private JButton btnNewButton_33;//Kur Rapor
	private JButton btnNewButton_34 ;//Kur Grafik
	private JButton btnNewButton_92 ;//Eksik Kur
	//ADRES
	private JButton btnNewButton_36;//Adres Giris
	private JButton btnNewButton_37;//Adres Detay
	private JButton btnNewButton_39;//Adres Etiket
	private JButton btnNewButton_40;//Adres Etiket Ayar
	private JButton btnNewButton_41;//Adres Firma
	//FATURA
	private JButton btnNewButton_42;//Urun Karti
	private JButton btnNewButton_43;//Imalat
	private JButton btnNewButton_20;//Coklu Uretim
	private JButton btnNewButton_44;//Recete
	private JButton btnNewButton_45 ;//Imalat Fisno Duzenleme
	private JButton btnNewButton_46;//Fatura
	private JButton btnNewButton_47;//Irsaliye
	private JButton btnNewButton_48;//Depolar Arasi Transfer
	private JButton btnzayi;//Zayi
	private JButton btnNewButton_49;//Fatura Rapor
	private JButton btnNewButton_50;//Envanter
	private JButton btnNewButton_53;//Irsaliye
	private JButton btnNewButton_51;//Urun Liste
	private JButton btnNewButton_52;//Urun Detay
	private JButton btnNewButton_54;//Imalat Fisleri
	private JButton btnNewButton_55;//Recete Rapor
	private JButton btnNewButton_57;//Imalat Grup Rapor
	private JButton btnNewButton_56;//Grup Rapor
	private JButton btnNewButton_58;//Stok Rapor
	private JButton btnNewButton_59 ;//Ortalama Fiat
	private JButton btnNewButton_14 ;//Zayi Rapor
	private JButton btnNewButton_60 ;//SQL Sorgu
	private JButton btnNewButton_61 ;//Bos Kur
	private JButton btnNewButton_62 ;//E Fatura
	private JButton btnNewButton_70 ;//Evrak Format
	private JButton btnNewButton_31 ;//Degisken Yenileme
	private JButton btnNewButton_73 ;//Firma Ismi
	private JButton btnNewButton_74 ;//Yil Sonu
	//SMS
	private JButton btnNewButton_83 ;//SMS
	private JButton btnNewButton_84 ;//MAIL
	//AYARLA
	private JButton btnNewButton_87;//Kullanici Ekleme
	private JButton btnNewButton_88;//Kull Detay Ekleme
	private JButton btnNewButton_89;//Kull Kopyala
	private JButton btnNewButton_90;//Ayarlar
	private JButton btnNewButton_91;//Email Bilgileri
	//GENEL
	private JButton btnNewButton_85;//Gonderilmis Raporlar
	private JButton btnNewButton_86;//Calisma Dizinleri
	private JButton btnNewButton_86_1;//Log Raporlama
	private JButton btnNewButton_86_1_1; // Veri Aktarimi
	//KAMBIYO
	private JButton btnNewButton_75;//Cek Giris
	private JButton btnNewButton_76;//Cek Cikis
	private JButton btnNewButton_77;//Raporlama
	private JButton btnNewButton_78;//Cek Kontrol
	private JButton btnNewButton_80;//Firma Ismi
	//GUNLUK
	private JButton btnNewButton_81;//Gunluk
	private JButton btnNewButton_82;//Gorev Kayit
	private JButton btnNewButton_21;//Kayitli Gorevler
	private JButton btnNewButton_38;//Firma Ismi
	//
	private JButton btnYenile ; // Yenile Tusu
	private JButton btnExcell ; // Excell Aktarma
	private JButton btnGrafik ; // Grafik
	private JButton btnEmail ;  // E Mail
	public static JButton btnKaydet ;
	public static JButton btnFiltre ;
	public static JButton btnYazici ;
	
	
	//
	public static JLabel lblNewLabel_1 ;
	public static JLabel lblNewLabel_2 ;
	public static JLabel lblNewLabel_3 ;
	public static JLabel lblNewLabel_21 ;
	public static JLabel lblNewLabel_22 ;
	public static JLabel lblNewLabel_23 ;
	public static JLabel lblNewLabel_24 ;
	public static JTabbedPane tabbedPane ;
	public static JToolBar toolBar ;
	public static JToolBar toolBar_1;
	public JToolBar toolBar_2 ;
	
	
	
	public static JDesktopPane desktopPane;
	public static JProgressBar progressBar  ;
	
	public static JSplitPane splitPane ;
	public static JLabel lblNewLabel_9;
	private Rectangle maxBounds;

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OBS_MAIN frame = new OBS_MAIN();
					frame.setVisible(true);
					frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
					frame.setTitle("OBS SISTEM" + "               " + GLOBAL.KULL_ADI );
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OBS_MAIN() {
//2663 satir
		setFont(new Font("Dialog", Font.BOLD, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(OBS_MAIN.class.getResource("/ICONLAR/icon-obs-32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1374, 655);


		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setJMenuBar(menuBar);
		JMenu mnCari = new JMenu("Cari Hesap");
		mnCari.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		mnCari.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnCari);
		JMenuItem mntmHpln = new JMenuItem("Hesap Plani");
		mntmHpln.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/user-16.png")));
		mntmHpln.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_5.doClick();
			}
		});
		mnCari.add(mntmHpln);
		JMenuItem mntmDekont = new JMenuItem("Dekont");
		mntmDekont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_4.doClick();
			}
		});
		mnCari.add(mntmDekont );
		JMenuItem mntmCgiris = new JMenuItem("Coklu Giris");
		mntmCgiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_6.doClick();
			}
		});
		mnCari.add(mntmCgiris);
		mnCari.addSeparator();
		JMenuItem mntmEkstre = new JMenuItem("Cari Ekstre");
		mntmEkstre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_7.doClick();
			}
		});
		mnCari.add(mntmEkstre);
		JMenuItem mntmMizan = new JMenuItem("Cari Mizan");
		mntmMizan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_8.doClick();
			}
		});
		mnCari.add(mntmMizan);
		JMenuItem mntmOmizan = new JMenuItem("Cari Ozel Mizan");
		mntmOmizan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.doClick();
			}
		});
		mnCari.add(mntmOmizan);
		JMenuItem mntmDcevir = new JMenuItem("Cari Dovize Cevirme");
		mntmDcevir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_9.doClick();
			}
		});
		mnCari.add(mntmDcevir);
		JMenuItem mntmHdokum = new JMenuItem("Hesap Dokum");
		mntmHdokum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_10.doClick();
			}
		});
		mnCari.add(mntmHdokum);

		JMenuItem mntmArama = new JMenuItem("Cari Arama");
		mntmArama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_11.doClick();
			}
		});
		mnCari.add(mntmArama);

		JMenuItem mntmGdokum = new JMenuItem("Gunluk Dokum");
		mntmGdokum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_12.doClick();
			}
		});
		mnCari.add(mntmGdokum);

		JMenuItem mntmHspln = new JMenuItem("Hesap Plani Dokum");
		mntmHspln.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_13.doClick();
			}
		});
		mnCari.add(mntmHspln);

		JMenuItem mntmGtakip = new JMenuItem("Gunluk Takip");
		mntmGtakip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_15.doClick();
			}
		});
		mnCari.add(mntmGtakip);

		mnCari.addSeparator();

		JMenuItem mntmSql = new JMenuItem("SQL Sorgulama");
		mntmSql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_16.doClick();
			}
		});
		mnCari.add(mntmSql);

		mnCari.addSeparator();

		JMenuItem mntmOrnek = new JMenuItem("Ornek Hesap Plani");
		mntmOrnek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_17.doClick();
			}
		});
		mnCari.add(mntmOrnek);

		JMenuItem mntmKdegis = new JMenuItem("Kod Degistirme");
		mntmKdegis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_18.doClick();
			}
		});
		mnCari.add(mntmKdegis);

		JMenuItem mntmYsonu = new JMenuItem("Yil Sonu");
		mntmYsonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_19.doClick();
			}
		});
		mnCari.add(mntmYsonu);

		mnCari.addSeparator();

		JMenuItem mntmFismi = new JMenuItem("Firma Ismi");
		mntmFismi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_22.doClick();
			}
		});
		mnCari.add(mntmFismi);
		JMenu mnKur = new JMenu("Kur Islemleri");
		mnKur.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		mnKur.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnKur);

		JMenuItem mntmKur = new JMenuItem("Kur Giris");
		mntmKur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_32.doClick();
			}
		});
		mnKur.add(mntmKur);

		JMenuItem mntmKrapor = new JMenuItem("Raporlama");
		mntmKrapor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_33.doClick();
			}
		});
		mnKur.add(mntmKrapor);

		JMenuItem mntmKgrafik = new JMenuItem("Grafik");
		mntmKgrafik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_34.doClick();
			}
		});
		mnKur.add(mntmKgrafik);

		JMenuItem mntmKeksik = new JMenuItem("Eksik_Kur");
		mntmKeksik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_92.doClick();
			}
		});
//************************************************************ADRES ******************************
		mnKur.add(mntmKeksik);
		JMenu mnAdres = new JMenu("Adres");
		mnAdres.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		mnAdres.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnAdres);
		JMenuItem mntmAgiris = new JMenuItem("Adres Giris");
		mntmAgiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_36.doClick();
			}
		});
		mnAdres.add(mntmAgiris);
		JMenuItem mntmAdetay = new JMenuItem("Adres Detay");
		mntmAdetay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_37.doClick();
			}
		});
		mnAdres.add(mntmAdetay);

		JSeparator separator = new JSeparator();
		mnAdres.add(separator);
		
		JMenuItem mntmEtiket = new JMenuItem("Etiket Yazdirma");
		mntmEtiket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_39.doClick();
			}
		});
		mnAdres.add(mntmEtiket);
		
		JMenuItem mntmEtiketAyar = new JMenuItem("Etiket Ayar");
		mntmEtiketAyar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btnNewButton_40.doClick();
			}
		});
		mnAdres.add(mntmEtiketAyar);
		
		JSeparator separator1 = new JSeparator();
		mnAdres.add(separator1);

		JMenuItem mntmAfismi = new JMenuItem("Firma Ismi");
		mntmAfismi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_41.doClick();
			}
		});
		mnAdres.add(mntmAfismi);
//****************************************STOK ****************************************************************
		JMenu mnFatura = new JMenu("Fatura / Stok Islemleri");
		mnFatura.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		mnFatura.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnFatura);

		JMenuItem mntmUkart = new JMenuItem("Urun Karti");
		mntmUkart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_42.doClick();
			}
		});
		mnFatura.add(mntmUkart);

		JSeparator separator_1 = new JSeparator();
		mnFatura.add(separator_1);

		JMenuItem mntmImalat = new JMenuItem("Imalat");
		mntmImalat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_43.doClick();
			}
		});
		mnFatura.add(mntmImalat);

		JMenuItem mntmTimalat = new JMenuItem("Toplu Imalat");
		mntmTimalat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_20.doClick();
			}
		});
		mnFatura.add(mntmTimalat);

		JMenuItem mntmRecete = new JMenuItem("Recete");
		mntmRecete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_44.doClick();
			}
		});
		mnFatura.add(mntmRecete);

		JMenuItem mntmIfno = new JMenuItem("Imalat Fis No Duzenleme");
		mntmIfno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_45.doClick();
			}
		});
		mnFatura.add(mntmIfno);

		JSeparator separator_2 = new JSeparator();
		mnFatura.add(separator_2);

		JMenuItem mntmFatura = new JMenuItem("Fatura ");
		mntmFatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_46.doClick();
			}
		});
		mnFatura.add(mntmFatura);

		JMenuItem mntmIrsaliye = new JMenuItem("Irsaliye");
		mntmIrsaliye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_47.doClick();
			}
		});
		mnFatura.add(mntmIrsaliye);

		JMenuItem mntmDtransfer = new JMenuItem("Depolar Arasi Transfer");
		mntmDtransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_48.doClick();
			}
		});
		mnFatura.add(mntmDtransfer);

		JMenuItem mntmZayi = new JMenuItem("Zayi Formu");
		mntmZayi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnzayi.doClick();
			}
		});
		mnFatura.add(mntmZayi);

		JSeparator separator_3 = new JSeparator();
		mnFatura.add(separator_3);

		JMenuItem mntmFrapor = new JMenuItem("Fatura Raporlama");
		mntmFrapor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_49.doClick();
			}
		});
		mnFatura.add(mntmFrapor);

		JMenuItem mntmIrapor = new JMenuItem("Irsaliye Raporlama");
		mntmIrapor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_53.doClick();
			}
		});
		mnFatura.add(mntmIrapor);

		JMenuItem mntmEnvanter = new JMenuItem("Envanter");
		mntmEnvanter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_50.doClick();
			}
		});
		mnFatura.add(mntmEnvanter);

		JMenuItem mntmUliste = new JMenuItem("Urun Liste");
		mntmUliste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_51.doClick();
			}
		});
		mnFatura.add(mntmUliste);

		JMenuItem mntmUdetayli = new JMenuItem("Urun Detayli Dokum");
		mntmUdetayli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_52.doClick();
			}
		});
		mnFatura.add(mntmUdetayli);

		JSeparator separator_4 = new JSeparator();
		mnFatura.add(separator_4);

		JMenuItem mntmImrapor = new JMenuItem("Imalat Rapor");
		mntmImrapor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_54.doClick();
			}
		});
		mnFatura.add(mntmImrapor);

		JMenuItem mntmImgrapor = new JMenuItem("Imalat Grup Rapor");
		mntmImgrapor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_57.doClick();
			}
		});
		mnFatura.add(mntmImgrapor);

		JMenuItem mntmRrapor = new JMenuItem("Recete Rapor");
		mntmRrapor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_55.doClick();
			}
		});
		mnFatura.add(mntmRrapor);

		JSeparator separator_5 = new JSeparator();
		mnFatura.add(separator_5);

		JMenuItem mntmGrapor = new JMenuItem("Grup Raporlama");
		mntmGrapor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_56.doClick();
			}
		});
		mnFatura.add(mntmGrapor);

		JMenuItem mntmSrrapor = new JMenuItem("Stok Rapor");
		mntmSrrapor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_58.doClick();
			}
		});
		mnFatura.add(mntmSrrapor);

		JMenuItem mntmOsatis = new JMenuItem("Ortalama Satis");
		mntmOsatis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_59.doClick();
			}
		});
		mnFatura.add(mntmOsatis);

		JMenuItem mntmZrapor = new JMenuItem("Zayi Raporlama");
		mntmZrapor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_14.doClick();
			}
		});
		mnFatura.add(mntmZrapor);

		JSeparator separator_6 = new JSeparator();
		mnFatura.add(separator_6);

		JMenuItem mntmSqlf = new JMenuItem("SQL Sorgulama");
		mntmSqlf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_60.doClick();
			}
		});
		mnFatura.add(mntmSqlf);

		JMenuItem mntmEf = new JMenuItem("E Fatura");
		mntmEf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_62.doClick();
			}
		});
		mnFatura.add(mntmEf);

		JMenuItem mntmBkur = new JMenuItem("Bos Kur Sorgulama");
		mntmBkur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_61.doClick();
			}
		});
		mnFatura.add(mntmBkur);

		JSeparator separator_7 = new JSeparator();
		mnFatura.add(separator_7);

		JMenuItem mntmEvf = new JMenuItem("Evrak Format");
		mntmEvf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_70.doClick();
			}
		});
		mnFatura.add(mntmEvf);

		JMenuItem mntmDyen = new JMenuItem("Degisken Yenileme");
		mntmDyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_31.doClick();
			}
		});
		mnFatura.add(mntmDyen);

		JMenuItem mntmFismif = new JMenuItem("Firma Ismi");
		mntmFismif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_73.doClick();
			}
		});
		mnFatura.add(mntmFismif);

		JMenuItem mntmDevir = new JMenuItem("Yil Sonu Devir");
		mntmDevir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_74.doClick();
			}
		});
		mnFatura.add(mntmDevir);

		JSeparator separator_8 = new JSeparator();
		mnFatura.add(separator_8);
		JMenu mnDegiskenler = new JMenu("Degiskenler");
		mnFatura .add(mnDegiskenler);
		JMenuItem mntmAgrp = new JMenuItem("Ana Grup");
		mntmAgrp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("DEGISKENLER","anagrup");
			}
		});
		mnDegiskenler .add(mntmAgrp);

		JMenuItem mntmAltgrp = new JMenuItem("Alt Grup");
		mntmAltgrp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("DEGISKENLER","altgrup");
			}
		});
		mnDegiskenler .add(mntmAltgrp );

		JSeparator separator_9 = new JSeparator();
		mnDegiskenler.add(separator_9);

		JMenuItem mntmDepo = new JMenuItem("Depo");
		mntmDepo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("DEGISKENLER","depo");
			}
		});
		mnDegiskenler .add(mntmDepo );

		JMenuItem mntmMensei = new JMenuItem("Mensei");
		mntmMensei.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("DEGISKENLER","mensei");
			}
		});
		mnDegiskenler .add(mntmMensei );

		JSeparator separator_10 = new JSeparator();
		mnDegiskenler.add(separator_10);

		JMenuItem mntmO1 = new JMenuItem("Ozel Kod 1");
		mntmO1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("DEGISKENLER","oz1");
			}
		});
		mnDegiskenler .add(mntmO1 );

		JMenuItem mntmO2 = new JMenuItem("Ozel Kod 2");
		mntmO2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("DEGISKENLER","oz2");
			}
		});
		mnDegiskenler .add(mntmO2 );
//*************************************************************KAMBIYO  *************************************************
		JMenu mnKambiyo = new JMenu("Kambiyo");
		mnKambiyo.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		mnKambiyo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnKambiyo);

		JMenuItem mntmCekgiris = new JMenuItem("Cek Giris");
		mntmCekgiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_75.doClick();
			}
		});
		mnKambiyo.add(mntmCekgiris);

		JMenuItem mntmCcikis = new JMenuItem("Cek Cikis");
		mntmCcikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_76.doClick();
			}
		});
		mnKambiyo.add(mntmCcikis);

		JSeparator separator_16 = new JSeparator();
		mnKambiyo.add(separator_16);

		JMenuItem mntmCraporlama = new JMenuItem("Raporlama");
		mntmCraporlama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_77.doClick();
			}
		});
		mnKambiyo.add(mntmCraporlama);

		JSeparator separator_14 = new JSeparator();
		mnKambiyo.add(separator_14);

		JMenuItem mntmCkontrol = new JMenuItem("Cek Kontrol");
		mntmCkontrol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_78.doClick();
			}
		});
		mnKambiyo.add(mntmCkontrol);

		JSeparator separator_15 = new JSeparator();
		mnKambiyo.add(separator_15);

		JMenuItem mntmCfirma = new JMenuItem("Firma Ismi");
		mntmCfirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_80.doClick();
			}
		});
		mnKambiyo.add(mntmCfirma);
		//************************************************************* GUNLUK **************************************************
		JMenu mnGunluk = new JMenu("Gunluk");
		mnGunluk.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				tabbedPane.setSelectedIndex(5);
			}
		});
		mnGunluk.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnGunluk);
		JMenuItem mntmGunluk = new JMenuItem("Gunluk");
		mntmGunluk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_81.doClick();
			}
		});
		mnGunluk.add(mntmGunluk);
		JMenuItem mntmGrvgiris = new JMenuItem("Gorev Giris");
		mntmGrvgiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_82.doClick();
			}
		});
		mnGunluk.add(mntmGrvgiris);
		JMenuItem mntmKayitli = new JMenuItem("Kayitli Gorevler");
		mntmKayitli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_21.doClick();
			}
		});
		mnGunluk.add(mntmKayitli);
		
		JSeparator separator_13 = new JSeparator();
		mnGunluk.add(separator_13);
		
		JMenuItem mntmFirma = new JMenuItem("Firma Ismi");
		mntmFirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_38.doClick();
			}
		});
		mnGunluk.add(mntmFirma);
//************************************************************************************************
		JMenu mnSms = new JMenu("Sms / Mail");
		mnSms.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				tabbedPane.setSelectedIndex(6);
			}
		});
		mnSms.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnSms);

		JMenuItem mntmSms = new JMenuItem("Sms");
		mntmSms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_83.doClick();
			}
		});
		mnSms.add(mntmSms);

		JMenuItem mntmMail = new JMenuItem("Mail");
		mntmMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_84.doClick();
			}
		});
		mnSms.add(mntmMail);
//***************************************************GENEL *************************************************
		JMenu mnGenel = new JMenu("Genel");
		mnGenel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				tabbedPane.setSelectedIndex(7);
			}
		});
		mnGenel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnGenel);

		JMenuItem mntmGrap = new JMenuItem("Gonderilmis Raporlar");
		mntmGrap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_85.doClick();
			}
		});
		mnGenel.add(mntmGrap);

		JMenuItem mntmCdizin = new JMenuItem("Calisma Dizinleri");
		mntmCdizin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_86.doClick();
			}
		});
		mnGenel.add(mntmCdizin);
		
		JMenuItem mntmLog = new JMenuItem("Log Raporlama");
		mntmLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_86_1.doClick();
			}
		});
		
		JMenuItem mntmVeri = new JMenuItem("Veri Aktarimi");
		mntmVeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_86_1_1.doClick();
			}
		});
		
		mnGenel.add(mntmVeri);

		JMenu mnAyarlar = new JMenu("Ayarlar");
		mnAyarlar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				tabbedPane.setSelectedIndex(8);
			}
		});
		mnAyarlar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnAyarlar);

		JMenuItem mntmKekleme = new JMenuItem("Kullanici Ekleme");
		mntmKekleme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_87.doClick();
			}
		});
		mnAyarlar.add(mntmKekleme);

		JMenuItem mntmKdetay = new JMenuItem("Kullanici Detay");
		mntmKdetay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_88.doClick();
			}
		});
		mnAyarlar.add(mntmKdetay);

		JMenuItem mntmKkopya = new JMenuItem("Kullanici Kopyalama");
		mntmKkopya.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_89.doClick();
			}
		});
		mnAyarlar.add(mntmKkopya);

		JSeparator separator_12 = new JSeparator();
		mnAyarlar.add(separator_12);

		JSeparator separator_11 = new JSeparator();
		mnAyarlar.add(separator_11);

		JMenuItem mntmAyar = new JMenuItem("Ayarlar");
		mntmAyar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_90.doClick();
			}
		});
		mnAyarlar.add(mntmAyar);

		JMenuItem mntmEmailayar = new JMenuItem("E Mail Ayarlari");
		mntmEmailayar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_91.doClick();
			}
		});
		mnAyarlar.add(mntmEmailayar);

		JMenu mnHakkinda = new JMenu("Hakkinda");
		mnHakkinda.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				tabbedPane.setSelectedIndex(9);
			}
		});
		mnHakkinda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnHakkinda);

		JMenuItem mntmHakkinda = new JMenuItem("Hakkinda");
		mnHakkinda.add(mntmHakkinda);

		//**********************************************************************************************************************************************
		//**********************************************************************************************************************************************

		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
        
		contentPane.add(splitPane, BorderLayout.NORTH);
//***************************************GORUNTU ICIN ASAGIDAKI BLOGU KALDIR********************************
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addComponentListener(new ComponentAdapter() 
		{
		    @Override
		    public void componentResized(ComponentEvent e) {
		        JTabbedPane tabbedPane = (JTabbedPane) e.getComponent();
		        int tabCount = tabbedPane.getTabCount();
		        for (int i = 0; i < tabCount; i++) {
		            Component c = tabbedPane.getComponentAt(i);
		            c.setPreferredSize(new Dimension(c.getSize().width, c.getPreferredSize().height));
		        }
		    }
		});

		///*******
		//tabbedPane.addComponentListener(new ComponentAdapter() 
		//{
		//    @Override
		//    public void componentResized(ComponentEvent e) {
		//        JTabbedPane tabbedPane = (JTabbedPane) e.getComponent();
		//        int tabCount = tabbedPane.getTabCount();
		//        for (int i = 0; i < tabCount; i++) {
		//            Component c = tabbedPane.getComponentAt(i);
		//            c.setPreferredSize(new Dimension(c.getSize().width, c.getPreferredSize().height));
		//        }
		//    }
		//});

		///*******
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane.setForeground(new Color(25, 25, 112));
		tabbedPane.setFont(new Font("Calibri", Font.BOLD, 20));
		
		
		splitPane.setLeftComponent(tabbedPane);
//******************************************************* CARI HESAP *******************************************************
		toolBar_2 = new JToolBar();
		toolBar_2.setFloatable(false);
		//toolBar_2.setBorder(new LineBorder(new Color(0, 191, 255)));
		toolBar_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		toolBar_2.setName("");
		toolBar_2.setToolTipText("");

		tabbedPane.addTab("Cari Hesap", null, toolBar_2, null);

		btnNewButton_5 = new JButton("");
		btnNewButton_5.setToolTipText("Hesap Plani Giris");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("HESAP GIRISI","");
			}
		});

		btnNewButton_5.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/hsp-30.png")));
		toolBar_2.add(btnNewButton_5);

		btnNewButton_4 = new JButton("");
		btnNewButton_4.setToolTipText("Dekont");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("DEKONT","");
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/dek-30.png")));
		toolBar_2.add(btnNewButton_4);

		btnNewButton_6 = new JButton("");
		btnNewButton_6.setToolTipText("Coklu Giris (Excell)");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("TOPLU GIRIS","");
			}
		});
		btnNewButton_6.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/coklud-30.png")));
		toolBar_2.add(btnNewButton_6);

		JLabel lblNewLabel = new JLabel("         ");
		toolBar_2.add(lblNewLabel);

		btnNewButton_7 = new JButton("");
		btnNewButton_7.setToolTipText("Cari Ekstre");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("EKSTRE","");
			}
		});
		btnNewButton_7.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-search-property-30.png")));
		toolBar_2.add(btnNewButton_7);

		btnNewButton_8 = new JButton("");
		btnNewButton_8.setToolTipText("Mizan");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("CARI MIZAN","");
			}
		});
		btnNewButton_8.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-check-30.png")));
		toolBar_2.add(btnNewButton_8);

		btnNewButton_9 = new JButton("");
		btnNewButton_9.setToolTipText("Dovize Cevirme");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("DOVIZE CEVIRME","");
			}
		});

		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("CARI OZEL MIZAN","");
			}
		});
		button.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-open-view-30.png")));
		button.setToolTipText("Ozel Mizan");
		toolBar_2.add(button);
		btnNewButton_9.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-data-transfer-30.png")));
		toolBar_2.add(btnNewButton_9);

		btnNewButton_10 = new JButton("");
		btnNewButton_10.setToolTipText("Hesap Dokum");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("HESAP BAK","");
			}
		});
		btnNewButton_10.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-order-history-30.png")));
		toolBar_2.add(btnNewButton_10);

		btnNewButton_11 = new JButton("");
		btnNewButton_11.setToolTipText("Arama");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("CARI ARAMA","");
			}
		});
		btnNewButton_11.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-view-30 (1).png")));
		toolBar_2.add(btnNewButton_11);

		btnNewButton_12 = new JButton("");
		btnNewButton_12.setToolTipText("Gunluk Dokum");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("GUNLUK TAKIP","");
			}
		});
		btnNewButton_12.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-calendar-30.png")));
		toolBar_2.add(btnNewButton_12);

		btnNewButton_13 = new JButton("");
		btnNewButton_13.setToolTipText("Hesap Plani");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("HESAP PLANI DETAY","");
			}
		});
		btnNewButton_13.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/hsp-30.png")));
		toolBar_2.add(btnNewButton_13);

		//JButton btnNewButton_14 = new JButton("");
		//btnNewButton_14.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-related-companies-30.png")));
		//toolBar_2.add(btnNewButton_14);

		btnNewButton_15 = new JButton("");
		btnNewButton_15.setToolTipText("Gunluk Takip");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("GUNLUK ISLEM","");
			}
		});
		btnNewButton_15.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-check-book-30.png")));
		toolBar_2.add(btnNewButton_15);

		btnNewButton_16 = new JButton("");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("SQL SORGULAMA","cari");
			}
		});
		btnNewButton_16.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-sql-30.png")));
		toolBar_2.add(btnNewButton_16);

		JLabel label = new JLabel("         ");
		toolBar_2.add(label);

		btnNewButton_17 = new JButton("");
		btnNewButton_17.setToolTipText("Ornek Hesap Plani");
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("ORNEK HESAP PLANI","");
			}
		});
		btnNewButton_17.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-user-groups-30.png")));
		toolBar_2.add(btnNewButton_17);

		btnNewButton_18 = new JButton("");
		btnNewButton_18.setToolTipText("Kod Degistirme");
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("KOD DEGISTIRME","");
			}
		});
		btnNewButton_18.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-exchange-30.png")));
		toolBar_2.add(btnNewButton_18);

		btnNewButton_19 = new JButton("");
		btnNewButton_19.setToolTipText("Yil Sonu");
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("YIL SONU AKTARMA","");
			}
		});
		btnNewButton_19.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-related-companies-30.png")));
		toolBar_2.add(btnNewButton_19);

		JLabel label_1 = new JLabel("         ");
		toolBar_2.add(label_1);

		btnNewButton_22 = new JButton("");
		btnNewButton_22.setToolTipText("Firma Ismi");
		btnNewButton_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("FIRMA ISMI DEGISTIRME","");
				FIRMA_ISMI_DEGIS.modul("cari");
			}
		});
		btnNewButton_22.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-organization-30.png")));
		toolBar_2.add(btnNewButton_22);
//**********************************************************************KUR ********************************************************************
		JToolBar toolBar_3 = new JToolBar();
		//toolBar_3.setBorder(new LineBorder(new Color(0, 191, 255)));
		toolBar_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		toolBar_3.setFloatable(false);
		tabbedPane.addTab("Kur", null, toolBar_3, null);

		btnNewButton_32 = new JButton("");
		btnNewButton_32.setToolTipText("Kur Giris");
		btnNewButton_32.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("KUR GIRIS","");
			}
		});

		btnNewButton_32.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-currency-exchange-30.png")));
		toolBar_3.add(btnNewButton_32);

		btnNewButton_33 = new JButton("");
		btnNewButton_33.setToolTipText("Kur Raporlama");
		btnNewButton_33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("KUR RAPORLAMA","");
			}
		});
		btnNewButton_33.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-pincode-keyboard-30.png")));
		toolBar_3.add(btnNewButton_33);

		btnNewButton_34 = new JButton("");
		btnNewButton_34.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("KUR GRAFIK","");
			}
		});
		btnNewButton_34.setToolTipText("Grafik");
		btnNewButton_34.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/grafik.png")));
		toolBar_3.add(btnNewButton_34);

		btnNewButton_92 = new JButton("");
		btnNewButton_92.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("EKSIK_KUR","");
			}
		});
		btnNewButton_92.setToolTipText("Eksik Kur");
		btnNewButton_92.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-data-transfer-30.png")));
		toolBar_3.add(btnNewButton_92);
//************************************************************ADRES *********************************************************************************
		JToolBar toolBar_4 = new JToolBar();
		toolBar_4.setFloatable(false);
		//toolBar_4.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane.addTab("Adres", null, toolBar_4, null);

		btnNewButton_36 = new JButton("");
		btnNewButton_36.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("ADRES GIRISI","");
			}
		});
		btnNewButton_36.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-post-office-30.png")));
		toolBar_4.add(btnNewButton_36);

		btnNewButton_37 = new JButton("");
		btnNewButton_37.setToolTipText("Adres Detay");
		btnNewButton_37.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("ADRES DETAY","");
			}
		});
		btnNewButton_37.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-contact-us-30.png")));
		toolBar_4.add(btnNewButton_37);

		JLabel lblNewLabel_10 = new JLabel("          ");
		toolBar_4.add(lblNewLabel_10);
		
		btnNewButton_39 = new JButton("");
		btnNewButton_39.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-print-address-label-30.png")));
		btnNewButton_39.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("ETIKET","");
			
			}
		});
		toolBar_4.add(btnNewButton_39);

		
		
		btnNewButton_40 = new JButton("");
		btnNewButton_40.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-pincode-keyboard-30.png")));
		btnNewButton_40.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("ETIKET AYAR","");
				
			}
		});
		toolBar_4.add(btnNewButton_40);
		
		JLabel lblNewLabel_11 = new JLabel("          ");
		toolBar_4.add(lblNewLabel_11);

		btnNewButton_41 = new JButton("");
		btnNewButton_41.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-organization-30.png")));
		btnNewButton_41.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("FIRMA ISMI DEGISTIRME","");
				FIRMA_ISMI_DEGIS.modul("adres");
			}
		});
		toolBar_4.add(btnNewButton_41);
		JScrollPane stkscrol = new JScrollPane();
		//stkscrol.setViewportBorder(UIManager.getBorder("ToolBar.border"));
		//stkscrol.setBorder(new LineBorder(new Color(0, 191, 255)));
		stkscrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		stkscrol.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));
		//***********************************************************************************************************************
		JPanel qwer = new JPanel();
		qwer.setPreferredSize(new Dimension(0, 65));
		qwer.setLayout(new BorderLayout(0, 0));
		qwer.add(stkscrol);
		//*************************************************************************************		
		JToolBar toolBar_5 = new JToolBar();
		toolBar_5.setMinimumSize(new Dimension(0, 55));
		toolBar_5.setMaximumSize(new Dimension(0, 55));
		toolBar_5.setFloatable(false);
		stkscrol.add(toolBar_5);
		stkscrol.setViewportView(toolBar_5);
		tabbedPane.addTab("Fatura", null, qwer, null);
		
		btnNewButton_42 = new JButton("");
		btnNewButton_42.setToolTipText("Urun Karti");
		btnNewButton_42.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("URUN KARTI","");
			}
		});
		btnNewButton_42.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-product-30.png")));
		toolBar_5.add(btnNewButton_42);

		JLabel lblNewLabel_12 = new JLabel("     ");
		toolBar_5.add(lblNewLabel_12);

		btnNewButton_43 = new JButton("");
		btnNewButton_43.setToolTipText("Imalat");
		btnNewButton_43.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("IMALAT","");
			}
		});
		btnNewButton_43.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-video-editing-30.png")));
		toolBar_5.add(btnNewButton_43);

		btnNewButton_44 = new JButton("");
		btnNewButton_44.setToolTipText("Recete");
		btnNewButton_44.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("RECETE","");
			}
		});

		btnNewButton_20 = new JButton("");
		btnNewButton_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("COKLU IMALAT","");
			}
		});
		btnNewButton_20.setToolTipText("Coklu Uretim");
		btnNewButton_20.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/coklud-30.png")));
		toolBar_5.add(btnNewButton_20);
		btnNewButton_44.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-prototype-30.png")));
		toolBar_5.add(btnNewButton_44);

		btnNewButton_45 = new JButton("");
		btnNewButton_45.setToolTipText("Imalat Fisno Duzenleme");
		btnNewButton_45.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("URETIM FIS NO YENILEME","");
			}
		});
		btnNewButton_45.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-data-transfer-30.png")));
		toolBar_5.add(btnNewButton_45);

		JLabel lblNewLabel_13 = new JLabel("     ");
		toolBar_5.add(lblNewLabel_13);

		btnNewButton_46 = new JButton("");
		btnNewButton_46.setToolTipText("Fatura");
		btnNewButton_46.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("FATURA		- SATIS","");
			}
		});
		btnNewButton_46.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-invoice-30.png")));
		toolBar_5.add(btnNewButton_46);

		btnNewButton_47 = new JButton("");
		btnNewButton_47.setToolTipText("Irsaliye");
		btnNewButton_47.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("IRSALIYE		- SATIS","");
			}
		});
		btnNewButton_47.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-questionnaire-30.png")));
		toolBar_5.add(btnNewButton_47);

		btnNewButton_48 = new JButton("");
		btnNewButton_48.setToolTipText("Depolar Arasi Transfer");
		btnNewButton_48.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-shipped-30.png")));
		toolBar_5.add(btnNewButton_48);

		btnzayi = new JButton("");
		btnzayi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("ZAYI","");
			}
		});
		btnzayi.setToolTipText("Zayi Formu");
		btnzayi.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-equity-security-30.png")));
		toolBar_5.add(btnzayi);

		JLabel lblNewLabel_14 = new JLabel("     ");
		toolBar_5.add(lblNewLabel_14);

		btnNewButton_49 = new JButton("");
		btnNewButton_49.setToolTipText("Fatura Raporlama");
		btnNewButton_49.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("FATURA RAPORLAMA","");
			}
		});
		btnNewButton_49.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-news-30.png")));
		toolBar_5.add(btnNewButton_49);

		btnNewButton_50 = new JButton("");
		btnNewButton_50.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("ENVANTER DOKUM","");
			}
		});

		btnNewButton_53 = new JButton("");
		btnNewButton_53.setToolTipText("Irsaliye Rapor");
		btnNewButton_53.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("IRSALIYE RAPOR","");
			}
		});
		btnNewButton_53.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-invoice-30.png")));
		toolBar_5.add(btnNewButton_53);
		btnNewButton_50.setToolTipText("Envanter");
		btnNewButton_50.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-hashtag-activity-feed-30.png")));
		toolBar_5.add(btnNewButton_50);

		btnNewButton_51 = new JButton("");
		btnNewButton_51.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("URUN LISTE","");
			}
		});
		btnNewButton_51.setToolTipText("Urun Liste");
		btnNewButton_51.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-variable-30.png")));
		toolBar_5.add(btnNewButton_51);

		btnNewButton_52 = new JButton("");
		btnNewButton_52.setToolTipText("Urun Detay");
		btnNewButton_52.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("STOK DETAY","");
			}
		});
		btnNewButton_52.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-module-30.png")));
		toolBar_5.add(btnNewButton_52);

		btnNewButton_54 = new JButton("");
		btnNewButton_54.setToolTipText("Imalat Fisleri");
		btnNewButton_54.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("IMALAT RAPORLAMA","");
			}
		});

		JLabel lblNewLabel_34 = new JLabel("     ");
		toolBar_5.add(lblNewLabel_34);
		btnNewButton_54.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-robot-30.png")));
		toolBar_5.add(btnNewButton_54);

		btnNewButton_55 = new JButton("");
		btnNewButton_55.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("RECETE RAPORLAMA","");
			}
		});

		btnNewButton_57 = new JButton("");
		btnNewButton_57.setToolTipText("Imalat Grup Raporlama");
		btnNewButton_57.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("IMALAT GRUP RAPOR","");
			}
		});
		btnNewButton_57.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-data-sheet-filled-30.png")));
		toolBar_5.add(btnNewButton_57);

		JLabel lblNewLabel_35 = new JLabel("  ");
		toolBar_5.add(lblNewLabel_35);
		btnNewButton_55.setToolTipText("Recete Raporlama");
		btnNewButton_55.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/mfis.png")));
		toolBar_5.add(btnNewButton_55);

		btnNewButton_56 = new JButton("");
		btnNewButton_56.setToolTipText("Grup Raporlama");
		btnNewButton_56.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("GRUP RAPOR","");
			}
		});
		btnNewButton_56.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-group-objects-30.png")));
		toolBar_5.add(btnNewButton_56);

		btnNewButton_58 = new JButton("");
		btnNewButton_58.setToolTipText("Stok Rapor");
		btnNewButton_58.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("STOK RAPOR","");
			}
		});
		btnNewButton_58.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-stocks-30.png")));
		toolBar_5.add(btnNewButton_58);

		btnNewButton_59 = new JButton("");
		btnNewButton_59.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("ORTALAMA SATIS","");
			}
		});
		btnNewButton_59.setToolTipText("Ortalama Satis Fiati");
		btnNewButton_59.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-approximately-not-equal-30.png")));
		toolBar_5.add(btnNewButton_59);

		btnNewButton_60 = new JButton("");
		btnNewButton_60.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("SQL SORGULAMA","stok");
			}
		});

		btnNewButton_14 = new JButton("");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("ZAYI RAPORLAMA","");
			}
		});
		btnNewButton_14.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-order-history-30.png")));
		btnNewButton_14.setToolTipText("Zayi Raporlama");
		toolBar_5.add(btnNewButton_14);
		btnNewButton_60.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-sql-30.png")));
		toolBar_5.add(btnNewButton_60);


		btnNewButton_61 = new JButton("");
		btnNewButton_61.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("STOK BOS KUR","");
			}
		});

		btnNewButton_62 = new JButton("");
		btnNewButton_62.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("E FATURA ARAMA","");
			}
		});
		btnNewButton_62.setToolTipText("E Fatura Arama");
		btnNewButton_62.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-check-book-30.png")));
		toolBar_5.add(btnNewButton_62);
		btnNewButton_61.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-currency-exchange-30.png")));
		toolBar_5.add(btnNewButton_61);

		btnNewButton_70 = new JButton("");
		btnNewButton_70.setToolTipText("Evrak Formatlama");
		btnNewButton_70.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("EVRAK FORMATLAMA","");
			}
		});
		btnNewButton_70.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-descending-sorting-30.png")));
		toolBar_5.add(btnNewButton_70);

		btnNewButton_31 = new JButton("");
		btnNewButton_31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("DEGISKEN YENILEME","");
			}
		});
		btnNewButton_31.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-not-equal-30.png")));
		btnNewButton_31.setToolTipText("Degisken Yenileme");
		toolBar_5.add(btnNewButton_31);

		JLabel lblNewLabel_16 = new JLabel("  ");
		toolBar_5.add(lblNewLabel_16);

		btnNewButton_73 = new JButton("");
		btnNewButton_73.setToolTipText("Firma Ismi");
		btnNewButton_73.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("FIRMA ISMI DEGISTIRME","");
				FIRMA_ISMI_DEGIS.modul("stok");
			}
		});
		btnNewButton_73.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-organization-30.png")));
		toolBar_5.add(btnNewButton_73);

		btnNewButton_74 = new JButton("");
		btnNewButton_74.setToolTipText("Yil Sonu Devir");
		btnNewButton_74.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean varmi = OBS_MAIN.pencere_bak("ENVANTER DOKUM");
				if (! varmi  ) 
				{
					JOptionPane.showMessageDialog(null, "Oncelikli Olarak Envanter Dokumu Aliniz......", "Yil Sonu Aktarma",JOptionPane.ERROR_MESSAGE);
					return;
				}
				form_ac("STOK YIL SONU","");
			}
		});
		btnNewButton_74.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-static-view-level2-30.png")));
		toolBar_5.add(btnNewButton_74);
		JLabel lblNewLabel_15 = new JLabel("      ");
		toolBar_5.add(lblNewLabel_15);
		//****************************************************************************
		JComboBox<String> comboDegisken = new JComboBox<String>();
		comboDegisken.setBorder(new LineBorder(new Color(0, 191, 255)));
		comboDegisken.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboDegisken.setMaximumSize(new Dimension(200, 35));
		comboDegisken.addItemListener(new ItemListener() 
		{
			public void itemStateChanged(ItemEvent e) {
				String hangi = (String) comboDegisken.getItemAt(comboDegisken.getSelectedIndex())  ;
				if (hangi == "Ana Grup")
				{
					form_ac("DEGISKENLER","anagrup");
				}
				else if (hangi == "Alt Grup")
				{
					form_ac("DEGISKENLER","altgrup");
				}
				else if (hangi == "Mensei")
				{
					form_ac("DEGISKENLER","mensei");
				}
				else if (hangi == "Depo")
				{
					form_ac("DEGISKENLER","depo");
				}
				else if (hangi == "Ozel Kod 1")
				{
					form_ac("DEGISKENLER","oz1");
				}
				else if (hangi == "Ozel Kod 2")
				{
					form_ac("DEGISKENLER","oz2");
				}
			}
		});
		comboDegisken.setModel(new DefaultComboBoxModel<String>(new String[] {"Degiskenler             ","Ana Grup","Alt Grup","Mensei","Depo","Ozel Kod 1","Ozel Kod 2"}));
		toolBar_5.add(comboDegisken);
		//************************************************************KAMBIYO *********************************************************************************
		JToolBar toolBar_6 = new JToolBar();
		//toolBar_6.setBorder(new LineBorder(new Color(0, 191, 255)));
		toolBar_6.setFloatable(false);
		tabbedPane.addTab("Kambiyo", null, toolBar_6, null);

		btnNewButton_75 = new JButton("");
		btnNewButton_75.setToolTipText("Cek Giris");
		btnNewButton_75.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("CEK GIRIS","");
			}
		});
		btnNewButton_75.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-check-book-30.png")));
		toolBar_6.add(btnNewButton_75);
		btnNewButton_76 = new JButton("");
		btnNewButton_76.setToolTipText("Cek Cikis");
		btnNewButton_76.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("CEK CIKIS","");
			}
		});
		btnNewButton_76.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-equity-security-30.png")));
		toolBar_6.add(btnNewButton_76);

		JLabel lblNewLabel_17 = new JLabel("          ");
		toolBar_6.add(lblNewLabel_17);

		btnNewButton_77 = new JButton("");
		btnNewButton_77.setToolTipText("Raporlama");
		btnNewButton_77.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("CEK RAPOR","");
			}
		});
		btnNewButton_77.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-questionnaire-30.png")));
		toolBar_6.add(btnNewButton_77);

		JLabel lblNewLabel_18 = new JLabel("          ");
		toolBar_6.add(lblNewLabel_18);

		btnNewButton_78 = new JButton("");
		btnNewButton_78.setToolTipText("Cek Takip");
		btnNewButton_78.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("CEK TAKIP","");
			}
		});
		btnNewButton_78.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-services-30.png")));
		toolBar_6.add(btnNewButton_78);

		JLabel lblNewLabel_19 = new JLabel("          ");
		toolBar_6.add(lblNewLabel_19);

		btnNewButton_80 = new JButton("");
		btnNewButton_80.setToolTipText("Firma Ismi");
		btnNewButton_80.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("FIRMA ISMI DEGISTIRME","");
				FIRMA_ISMI_DEGIS.modul("kambiyo");
			}
		});
		btnNewButton_80.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-organization-30.png")));
		toolBar_6.add(btnNewButton_80);
		//************************************************************GUNLUK *********************************************************************************

		JToolBar toolBar_7 = new JToolBar();
		toolBar_7.setBorder(new LineBorder(new Color(0, 191, 255)));
		toolBar_7.setFloatable(false);
		tabbedPane.addTab("Gunluk", null, toolBar_7, null);

		btnNewButton_81 = new JButton("");
		btnNewButton_81.setToolTipText("Gunluk");
		btnNewButton_81.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				form_ac("GUNLUK","");
			}
		});
		btnNewButton_81.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-schedule-30.png")));
		toolBar_7.add(btnNewButton_81);

		btnNewButton_82 = new JButton("");
		btnNewButton_82.setToolTipText("Gorev Girisi");
		
		btnNewButton_82.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("GOREV GIRIS","");
			}
		});
		
		JLabel lblNewLabel_37 = new JLabel("     ");
		toolBar_7.add(lblNewLabel_37);
		btnNewButton_82.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-active-directory-30.png")));
		toolBar_7.add(btnNewButton_82);
		
		btnNewButton_21 = new JButton("");
		btnNewButton_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("HAZIR GOREVLER","");
			}
		});
		
		JLabel lblNewLabel_36 = new JLabel("     ");
		toolBar_7.add(lblNewLabel_36);
		btnNewButton_21.setToolTipText("Hazir Gorevler");
		btnNewButton_21.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-news-30.png")));
		toolBar_7.add(btnNewButton_21);
		
		JLabel lblNewLabel_38 = new JLabel("          ");
		toolBar_7.add(lblNewLabel_38);
		
		btnNewButton_38 = new JButton("");
		btnNewButton_38.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("FIRMA ISMI DEGISTIRME","");
				FIRMA_ISMI_DEGIS.modul("gunluk");
			}
		});
		btnNewButton_38.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-organization-30.png")));
		toolBar_7.add(btnNewButton_38);
		
		
		//************************************************************SMS*********************************************************************************

		JToolBar toolBar_8 = new JToolBar();
		//toolBar_8.setBorder(new LineBorder(new Color(0, 191, 255)));
		toolBar_8.setFloatable(false);
		tabbedPane.addTab("Sms/Mail", null, toolBar_8, null);

		btnNewButton_83 = new JButton("");
		btnNewButton_83.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("SMS","");
			}
		});
		btnNewButton_83.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-sms-30.png")));
		toolBar_8.add(btnNewButton_83);

		btnNewButton_84 = new JButton("");
		btnNewButton_84.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("MAIL","");
			}
		});
		btnNewButton_84.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-post-30.png")));
		toolBar_8.add(btnNewButton_84);
		
		//*********************************************************** GIDEN RAPOR *********************************************************************************

		JToolBar toolBar_9 = new JToolBar();
		//toolBar_9.setBorder(new LineBorder(new Color(0, 191, 255)));
		toolBar_9.setFloatable(false);
		tabbedPane.addTab("Genel", null, toolBar_9, null);

		btnNewButton_85 = new JButton("");
		btnNewButton_85.setToolTipText("Gonderilmis Raporlar");
		btnNewButton_85.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("GIDEN RAPORLAR","");
			}
		});
		btnNewButton_85.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-mailbox-opened-flag-up-30.png")));
		toolBar_9.add(btnNewButton_85);

		btnNewButton_86 = new JButton("");
		btnNewButton_86.setToolTipText("Calisma Dizinleri");
		btnNewButton_86.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("CALISMA DIZINLERI","");
			}
		});
		btnNewButton_86.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-database-administrator-30.png")));
		toolBar_9.add(btnNewButton_86);

		btnNewButton_86_1 = new JButton("");
		btnNewButton_86_1.setToolTipText("Log Raporlama");
		btnNewButton_86_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(! GLOBAL.KULL_ADI.equals("Admin") && ! GLOBAL.KULL_ADI.equals("hamit"))
				{
					JOptionPane.showMessageDialog(null, "Sadece Admin Gorebilir","Log Sorgulama", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				form_ac("LOG RAPORLAMA","");
			}
		});
		btnNewButton_86_1.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-data-sheet-filled-30.png")));
		toolBar_9.add(btnNewButton_86_1);
		
		btnNewButton_86_1_1 = new JButton("");
		btnNewButton_86_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUtil.setWaitCursor(toolBar,true);
				JInternalFrame internalFrame;
				internalFrame = new MSSQL_TO_MYSQL();
				desktopPane.add(internalFrame);
				internalFrame.setVisible(true);
				GuiUtil.setWaitCursor(toolBar,false);
			}
		});
		
		JLabel lblNewLabel_39 = new JLabel("     ");
		toolBar_9.add(lblNewLabel_39);
		btnNewButton_86_1_1.setToolTipText("Veri Aktarma");
		btnNewButton_86_1_1.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-data-transfer-30.png")));
		toolBar_9.add(btnNewButton_86_1_1);
		//************************************************************AYARLAR*********************************************************************************

		JToolBar toolBar_10 = new JToolBar();
		//toolBar_10.setBorder(new LineBorder(new Color(0, 191, 255)));
		toolBar_10.setFloatable(false);
		tabbedPane.addTab("Ayarlar", null, toolBar_10, null);

		btnNewButton_87 = new JButton(""); //USER EKELEME
		btnNewButton_87.setToolTipText("Kullanici Ekleme");
		btnNewButton_87.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("KULLANICI EKLEME","");
			}
		});
		btnNewButton_87.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/hsp-30.png")));
		toolBar_10.add(btnNewButton_87);

		btnNewButton_88 = new JButton("");
		btnNewButton_88.setToolTipText("Kullanici Detay Ekleme");
		btnNewButton_88.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("KULLANICI DETAY EKLEME","");
			}
		});

		btnNewButton_88.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/add-user30.png")));
		toolBar_10.add(btnNewButton_88);

		btnNewButton_89 = new JButton("");
		btnNewButton_89.setToolTipText("Kullanici Kopyala");
		btnNewButton_89.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("KULLANICI KOPYALA","");
			}
		});
		btnNewButton_89.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-identification-documents-30.png")));
		toolBar_10.add(btnNewButton_89);

		JLabel lblNewLabel_20 = new JLabel("          ");
		toolBar_10.add(lblNewLabel_20);

		btnNewButton_90 = new JButton("");
		btnNewButton_90.setToolTipText("Ayarlar");
		btnNewButton_90.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("PARAMETRELER","");
			}
		});

		btnNewButton_90.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-settings-40.png")));
		toolBar_10.add(btnNewButton_90);

		btnNewButton_91 = new JButton("");
		btnNewButton_91.setToolTipText("E Mail Bilgileri");
		btnNewButton_91.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("E MAIL BILGILERI","");
			}
		});

		btnNewButton_91.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-mailbox-opened-flag-up-30.png")));
		toolBar_10.add(btnNewButton_91);
		//************************************************************HAKKINDA *********************************************************************************

		JToolBar toolBar_11 = new JToolBar();
		toolBar_11.setBorder(new LineBorder(new Color(0, 191, 255)));
		toolBar_11.setFloatable(false);
		tabbedPane.addTab("Hakkinda", null, toolBar_11, null);

		JButton btnNewButton_92 = new JButton("");
		btnNewButton_92.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("HAKKINDA","");
			}
		});
		btnNewButton_92.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-about-30.png")));
		toolBar_11.add(btnNewButton_92);

		toolBar = new JToolBar();
		toolBar.setBorder(new LineBorder(new Color(0, 191, 255)));
		toolBar.setFloatable(false);
		splitPane.setRightComponent(toolBar);

		btnKaydet = new JButton(""); // Kaydet //
		btnKaydet.setToolTipText("Kayit");
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUtil.setWaitCursor(toolBar,true);
				String sonuc = pencere_activmi();
				if (sonuc == "KULLANICI EKLEME") USER_EKLEME.kayit();
				else if (sonuc == "KULLANICI DETAY EKLEME") USER_DETAY_EKLEME.kayit();
				else if (sonuc == "KULLANICI KOPYALA")	USER_KOPYALA.kaydet();
				else if (sonuc == "E MAIL BILGILERI")	E_MAIL_BILGILERI.kayit();
				else if (sonuc == "PARAMETRELER")	PARAMETRELER.kayit();
				else if (sonuc == "DEKONT")	DEKONT.kaydet();
				else if (sonuc == "HESAP GIRISI")	H_PLANI.kayit();
				else if (sonuc == "KUR GIRIS")	KUR_GIRIS.kayit();
				else if (sonuc == "EKSIK KUR")	EKSIK_KUR.kaydet();
				else if (sonuc == "TOPLU GIRIS")	DISTAN_AKTAR.kaydet_carii();
				else if (sonuc == "YIL SONU AKTARMA")	YIL_SONU.kaydet();
				else if (sonuc == "FIRMA ISMI DEGISTIRME")	FIRMA_ISMI_DEGIS.kaydet();
				else if (sonuc == "CEK GIRIS") 	CEK_GIRIS.kaydet();
				else if (sonuc == "CEK CIKIS") 	CEK_CIKIS.kaydet();
				else if (sonuc == "CEK TAKIP")	CEK_TAKIP.kaydet();
				else if (sonuc == "ADRES GIRISI")	ADRES_GIRIS.kayit();
				else if (sonuc == "ETIKET AYAR") ETIKET_AYAR.kaydet();
				else if (sonuc == "MAIL")	MAIL.giris_kayit();
				else if (sonuc == "SMS") 	SMS.giris_kayit();
				else if (sonuc == "DEGISKENLER")	DEGISKEN_GIRIS.kayit();
				else if (sonuc == "URUN KARTI")	URUN_KART.kayit();
				else if (sonuc == "FATURA		- SATIS")	FATURA.kaydet();
				else if (sonuc == "FATURA		- ALIS") 	FATURA.kaydet();
				else if (sonuc == "RECETE") 	RECETE.kaydet();
				else if (sonuc == "IMALAT") 	IMALAT.kaydet();
				else if (sonuc == "COKLU IMALAT")	COKLU_IMALAT.kaydet();
				else if (sonuc == "IRSALIYE		- SATIS")	IRSALIYE.kaydet();
				else if (sonuc == "IRSALIYE		- ALIS")	IRSALIYE.kaydet();
				else if (sonuc == "GIDECEGI YER")	GIDECEGI_YER.kaydet();
				else if (sonuc == "EVRAK FORMATLAMA") EVRAK_FORMAT.kaydet();
				else if (sonuc == "ZAYI")	ZAYI.kaydet();
				else if (sonuc == "GRAFIK")	GRAFIK.kaydet();
				else if (sonuc == "DEGISKEN YENILEME")	DEGISKEN_DEGIS.kaydet();
				else if (sonuc == "GOREV GIRIS")	GOREV_GIRIS.kaydet();
				GuiUtil.setWaitCursor(toolBar,false);
			}
		});

		btnKaydet.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/save.png")));
		toolBar.add(btnKaydet);

		JButton btnNewButton_2 = new JButton("");  // SIL
		btnNewButton_2.setToolTipText("Sil");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUtil.setWaitCursor(toolBar,true);
				String sonuc = pencere_activmi();
				if (sonuc == "KULLANICI EKLEME") USER_EKLEME.sil();
				else if (sonuc == "KULLANICI DETAY EKLEME") USER_DETAY_EKLEME.sil();
				else if (sonuc == "DEKONT")	DEKONT.yoket();
				else if (sonuc == "HESAP GIRISI")	H_PLANI.sil();
				else if (sonuc == "KUR GIRIS")	KUR_GIRIS.sil();
				else if (sonuc == "CEK GIRIS") 	CEK_GIRIS.sil();
				else if (sonuc == "CEK CIKIS") 	CEK_CIKIS.sil();
				else if (sonuc == "ADRES GIRISI")	ADRES_GIRIS.sil();
				else if (sonuc == "MAIL")	MAIL.sil();
				else if (sonuc == "SMS")	SMS.sil();
				else if (sonuc == "URUN KARTI")	URUN_KART.sil();
				else if (sonuc == "FATURA		- SATIS")	FATURA.fatura_sil();
				else if (sonuc == "FATURA		- ALIS")	FATURA.fatura_sil();
				else if (sonuc == "RECETE")	RECETE.recete_sil();
				else if (sonuc == "IMALAT")	IMALAT.imalat_sil();
				else if (sonuc == "IRSALIYE		- SATIS")	IRSALIYE.irs_sil();
				else if (sonuc == "IRSALIYE		- ALIS")	IRSALIYE.irs_sil();
				else if (sonuc == "GIDECEGI YER")	GIDECEGI_YER.sil();
				else if (sonuc == "ZAYI")	ZAYI.zai_sil();
				else if (sonuc == "GIDEN RAPORLAR")	GID_RAPOR.sil();
				else if (sonuc == "DEGISKENLER")	DEGISKEN_GIRIS.sil();
				else if (sonuc == "GOREV GIRIS")	GOREV_GIRIS.sil();
				GuiUtil.setWaitCursor(toolBar,false);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/sil.png")));
		toolBar.add(btnNewButton_2);

		btnYenile = new JButton("");   //*******************YENILE**************************
		btnYenile.setToolTipText("Yenile");
		btnYenile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUtil.setWaitCursor(toolBar,true);
				String sonuc = pencere_activmi();
				if (sonuc == "BOS KUR")	BOS_KUR.hisset();
				else if (sonuc == "STOK BOS KUR")	FAT_BOS_KUR.hisset();
				else if (sonuc == "SQL SORGULAMA")	SQL_SORGULAMA.hisset();
				else if (sonuc == "LOG RAPORLAMA")	LOGLAMA_RAPOR.hisset();
				else if (sonuc == "EKSTRE")	EKSTRE.hisset();
				else if (sonuc == "CARI MIZAN")	MIZAN.hisset();
				else if (sonuc == "CARI OZEL MIZAN")	OZEL_MIZAN.hisset();
				GuiUtil.setWaitCursor(toolBar,false);
			}
		});
		btnYenile.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		toolBar.add(btnYenile);

		btnFiltre = new JButton("");
		btnFiltre.setToolTipText("Filtre");
		btnFiltre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FILTRE ft = null;
				GuiUtil.setWaitCursor(toolBar,true);
				String sonuc = pencere_activmi();
				if (sonuc == "EKSTRE")
				{
					GLOBAL.filtre_sayfa = 0 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "CARI MIZAN")
				{
					GLOBAL.filtre_sayfa = 1 ;
					GLOBAL.hangi_mizan = "normal" ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "CARI OZEL MIZAN")
				{
					GLOBAL.filtre_sayfa = 1 ;
					GLOBAL.hangi_mizan = "ozel" ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "DOVIZE CEVIRME")
				{
					GLOBAL.filtre_sayfa = 2 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "CARI ARAMA")
				{
					GLOBAL.filtre_sayfa = 3 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "GUNLUK ISLEM")
				{
					GLOBAL.filtre_sayfa = 4 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "CEK RAPOR")
				{
					GLOBAL.filtre_sayfa = 5 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "KUR RAPORLAMA")
				{
					GLOBAL.filtre_sayfa = 6 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "FATURA RAPORLAMA")
				{
					GLOBAL.filtre_sayfa = 7 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "IMALAT RAPORLAMA")
				{
					GLOBAL.filtre_sayfa = 8 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "STOK_RAPOR")
				{
					GLOBAL.filtre_sayfa = 9 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "STOK DETAY")
				{
					GLOBAL.filtre_sayfa = 10 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "GRUP RAPOR")
				{
					GLOBAL.filtre_sayfa = 11 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "IMALAT GRUP RAPOR")
				{
					GLOBAL.filtre_sayfa = 12 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "IRSALIYE RAPOR")
				{
					GLOBAL.filtre_sayfa = 13 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "RECETE RAPORLAMA")
				{
					GLOBAL.filtre_sayfa = 14 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "ORTALAMA SATIS")
				{
					GLOBAL.filtre_sayfa = 15 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "URUN LISTE")
				{
					GLOBAL.filtre_sayfa = 16 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "ENVANTER DOKUM")
				{
					GLOBAL.filtre_sayfa = 17 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "ZAYI RAPORLAMA")
				{
					GLOBAL.filtre_sayfa = 18 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "KUR GRAFIK")
				{
					GLOBAL.filtre_sayfa = 19 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}
				else if (sonuc == "HAZIR GOREVLER")
				{
					GLOBAL.filtre_sayfa = 20 ;
					ft = new FILTRE();
					ft.setVisible(true);
				}

				GuiUtil.setWaitCursor(toolBar,false);
			}
		});
		btnFiltre.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-filter-16.png")));
		toolBar.add(btnFiltre);
		JLabel lblNewLabel_4 = new JLabel("          ");
		toolBar.add(lblNewLabel_4);
		btnYazici = new JButton("");
		btnYazici.setToolTipText("Yazici");
		btnYazici.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				GuiUtil.setWaitCursor(toolBar,true);
				GuiUtil.setWaitCursor(tabbedPane,true); 
				//
				boolean varmi = pencere_bak("RAPOR YAZDIRMA");
				if (varmi) 
				{
					try {
						pencere_aktiv_yap("RAPOR YAZDIRMA");
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
				}
				//
				JInternalFrame internalFrame;
				try {
					String sonuc = pencere_activmi();
					if (sonuc == "HESAP PLANI DETAY")
					{
						InputStream is = this.getClass().getClassLoader().getResourceAsStream("RPT/HSP_PLN.rpt");
						Files.copy(is, Paths.get("C:\\OBS_SISTEM\\HSP_PLN.rpt"),StandardCopyOption.REPLACE_EXISTING);
						internalFrame = new PRINT_YAPMA();
						desktopPane.add(internalFrame);
						internalFrame.setVisible(true);
						PRINT_YAPMA.hisset("hsppln","");
					}
					else if (sonuc == "EKSTRE")
					{
						if ( EKSTRE.table.getRowCount() == 0 ) 
						{
							GuiUtil.setWaitCursor(toolBar,false);
							GuiUtil.setWaitCursor(tabbedPane,false); 
							return ;
						}

						InputStream is = this.getClass().getClassLoader().getResourceAsStream("RPT/C_EKSTRE.rpt");
						Files.copy(is, Paths.get("C:\\OBS_SISTEM\\EKSTRE.rpt"),StandardCopyOption.REPLACE_EXISTING);
						internalFrame = new PRINT_YAPMA();
						if (! TARIH_CEVIR.tarih_geri(FILTRE.dateChooser).equals("1900.01.01"))
						{
							//*****
							Runnable runner = new Runnable()
							{ public void run() {
								try {
									//*****
									GuiUtil.setWaitCursor(toolBar,true);
									GuiUtil.setWaitCursor(tabbedPane,true); 
									GuiUtil.setWaitCursor(EKSTRE.pane,true);
									EKSTRE.sqlite_yaz();
									desktopPane.add(internalFrame);
									internalFrame.setVisible(true);

									PRINT_YAPMA.hisset("ekstre","aratarih");
									GuiUtil.setWaitCursor(toolBar,false);
									GuiUtil.setWaitCursor(EKSTRE.pane,false);
									GuiUtil.setWaitCursor(tabbedPane,false); 
									Thread.currentThread().isInterrupted();
								}
								catch (Exception ex) 
								{	}
								//***
							}
							};
							//// Progress Bar

							Thread t = new Thread(runner, "Code Executer");
							t.start();
							//***
						}
						else
						{
							desktopPane.add(internalFrame);
							internalFrame.setVisible(true);

							PRINT_YAPMA.hisset("ekstre","normal");
						}
					}
					else if (sonuc == "CARI MIZAN")
					{
						if ( MIZAN.table.getRowCount() == 0 ) 
						{
							GuiUtil.setWaitCursor(toolBar,false);
							GuiUtil.setWaitCursor(tabbedPane,false); 
							return ;
						}
						InputStream is = this.getClass().getClassLoader().getResourceAsStream("RPT/MIZAN.rpt");
						Files.copy(is, Paths.get("C:\\OBS_SISTEM\\MIZAN.rpt"),StandardCopyOption.REPLACE_EXISTING);
						internalFrame = new PRINT_YAPMA();
						desktopPane.add(internalFrame);
						internalFrame.setVisible(true);

						PRINT_YAPMA.hisset("mizan","");
					}
					else if (sonuc == "CARI OZEL MIZAN")
					{
						if ( OZEL_MIZAN.table.getRowCount() == 0 ) 
						{
							GuiUtil.setWaitCursor(toolBar,false);
							GuiUtil.setWaitCursor(tabbedPane,false); 
							return ;
						}
						InputStream is = this.getClass().getClassLoader().getResourceAsStream("RPT/OZEL_MIZAN.rpt");
						Files.copy(is, Paths.get("C:\\OBS_SISTEM\\OZEL_MIZAN.rpt"),StandardCopyOption.REPLACE_EXISTING);
						internalFrame = new PRINT_YAPMA();
						desktopPane.add(internalFrame);
						internalFrame.setVisible(true);

						PRINT_YAPMA.hisset("ozel_mizan","");
					}
					else if (sonuc == "DOVIZE CEVIRME")
					{
						if ( DVZ_CEVIRME.table.getRowCount() == 0 ) 
						{
							GuiUtil.setWaitCursor(toolBar,false);
							GuiUtil.setWaitCursor(tabbedPane,false); 
							return ;
						}
						InputStream is = this.getClass().getClassLoader().getResourceAsStream("RPT/DVZ_CEVIRME.rpt");
						Files.copy(is, Paths.get("C:\\OBS_SISTEM\\DVZ_CEVIRME.rpt"),StandardCopyOption.REPLACE_EXISTING);
						internalFrame = new PRINT_YAPMA();
						desktopPane.add(internalFrame);
						internalFrame.setVisible(true);

						PRINT_YAPMA.hisset("dvzcev","");
					}
					else if (sonuc == "CEK GIRIS")
					{
						if ( CEK_GIRIS.textField.getText().equals("")) 
						{
							GuiUtil.setWaitCursor(toolBar,false);
							GuiUtil.setWaitCursor(tabbedPane,false); 
							return ;
						}
						InputStream is = this.getClass().getClassLoader().getResourceAsStream("RPT/BORDRO_CEK.rpt");
						Files.copy(is, Paths.get("C:\\OBS_SISTEM\\BORDRO.rpt"),StandardCopyOption.REPLACE_EXISTING);
						internalFrame = new PRINT_YAPMA();
						desktopPane.add(internalFrame);
						internalFrame.setVisible(true);

						PRINT_YAPMA.hisset("cekg",CEK_GIRIS.textField.getText());
					}
					else if (sonuc == "CEK CIKIS")
					{
						if ( CEK_CIKIS.textField.getText().equals("") ) 
						{
							GuiUtil.setWaitCursor(toolBar,false);
							GuiUtil.setWaitCursor(tabbedPane,false); 
							return ;
						}
						InputStream is = this.getClass().getClassLoader().getResourceAsStream("RPT/BORDRO_CEK.rpt");
						Files.copy(is, Paths.get("C:\\OBS_SISTEM\\BORDRO.rpt"),StandardCopyOption.REPLACE_EXISTING);
						internalFrame = new PRINT_YAPMA();
						desktopPane.add(internalFrame);
						internalFrame.setVisible(true);

						PRINT_YAPMA.hisset("cekc",CEK_CIKIS.textField.getText());
					}
					else if (sonuc == "ETIKET")
					{
						//InputStream is = this.getClass().getClassLoader().getResourceAsStream("RPT/ADRES_RPT/Etiket.rpt");
						//Files.copy(is, Paths.get("C:\\OBS_SISTEM\\ETIKET.rpt"),StandardCopyOption.REPLACE_EXISTING);
						//internalFrame = new PRINT_YAPMA();
						//desktopPane.add(internalFrame);
						//internalFrame.setVisible(true);
						//PRINT_YAPMA.hisset("etiket","");
						
						//**************************
						
						InputStream is = this.getClass().getClassLoader().getResourceAsStream("RPT/ADRES_RPT/Etiket.jrxml");
						Files.copy(is, Paths.get("C:\\OBS_SISTEM\\ETIKET.jrxml"),StandardCopyOption.REPLACE_EXISTING);
						internalFrame = new PRINT_JASPER();
						desktopPane.add(internalFrame);
						internalFrame.setVisible(true);
						oac.nerden = "etiket";
						PRINT_JASPER.hisset("etiket","");
					}
					else if (sonuc == "EKSTRE1")
					{
						if (! TARIH_CEVIR.tarih_geri(FILTRE.dateChooser).equals("1900.01.01"))
						{
							InputStream is = this.getClass().getClassLoader().getResourceAsStream("RPT/CARI_RPT/Ekstre_Kisa.jrxml");
							Files.copy(is, Paths.get("C:\\OBS_SISTEM\\Ekstre_Kisa.jrxml"),StandardCopyOption.REPLACE_EXISTING);
							internalFrame = new PRINT_JASPER();
							desktopPane.add(internalFrame);
							internalFrame.setVisible(true);
							oac.nerden = "ekstre";
							PRINT_JASPER.hisset("ekstre_kisa","aratarih");
						}
						else
						{
							InputStream is = this.getClass().getClassLoader().getResourceAsStream("RPT/CARI_RPT/Ekstre.jrxml");
							Files.copy(is, Paths.get("C:\\OBS_SISTEM\\Ekstre.jrxml"),StandardCopyOption.REPLACE_EXISTING);
							internalFrame = new PRINT_JASPER();
							desktopPane.add(internalFrame);
							internalFrame.setVisible(true);
							oac.nerden = "ekstre";
							PRINT_JASPER.hisset("ekstre","normal");
						}
						
					}
					else if (sonuc == "STOK_RAPOR")
					{
						if ( STOK_RAPOR.table.getRowCount() == 0 ) 
						{
							GuiUtil.setWaitCursor(toolBar,false);
							GuiUtil.setWaitCursor(tabbedPane,false); 
							return ;
						}
						InputStream is = this.getClass().getClassLoader().getResourceAsStream("RPT/STOK_RPT/STOK.rpt");
						Files.copy(is, Paths.get("C:\\OBS_SISTEM\\STOK.rpt"),StandardCopyOption.REPLACE_EXISTING);
						internalFrame = new PRINT_YAPMA();
						desktopPane.add(internalFrame);
						internalFrame.setVisible(true);

						PRINT_YAPMA.hisset("stok","");
					}
					else if (sonuc == "FATURA		- SATIS" || sonuc == "FATURA		- ALIS")
					{
						if (FATURA.textField.getText().equals("")) 
						{
							GuiUtil.setWaitCursor(toolBar,false);
							GuiUtil.setWaitCursor(tabbedPane,false); 
							return;
						}
						InputStream is = this.getClass().getClassLoader().getResourceAsStream("RPT/STOK_RPT/FATURA.rpt");
						Files.copy(is, Paths.get("C:\\OBS_SISTEM\\FATURA.rpt"),StandardCopyOption.REPLACE_EXISTING);
						internalFrame = new PRINT_YAPMA();
						desktopPane.add(internalFrame);
						internalFrame.setVisible(true);

						PRINT_YAPMA.hisset("fatura","");
					}
					GuiUtil.setWaitCursor(toolBar,false);
					GuiUtil.setWaitCursor(tabbedPane,false); 
				} 
				catch (Exception ex) 
				{
					GuiUtil.setWaitCursor(toolBar,false);
					JOptionPane.showMessageDialog(null, ex.getMessage(),  "Yazici", JOptionPane.ERROR_MESSAGE);   
				}
			}
		});
		btnYazici.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-send-to-printer-16.png")));
		toolBar.add(btnYazici);

		btnEmail = new JButton("");
		btnEmail.setToolTipText("Email");
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form_ac("E MAIL GONDERME","");
			}
		});
		btnEmail.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/mail-16.png")));
		toolBar.add(btnEmail);

		btnExcell = new JButton("");
		btnExcell.setToolTipText("Excell Aktarma");
		btnExcell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUtil.setWaitCursor(toolBar,true);
				String sonuc = pencere_activmi();
				if (sonuc == "IMALAT GRUP RAPOR")
				{
					IMALAT_GRUP_RAPOR.excell_aktar() ;
				}
				else if (sonuc == "GRUP RAPOR")
				{
					GRUP_RAPOR.excell_aktar() ;
				}
				else if (sonuc == "STOK DETAY")
				{
					STOK_DETAY.excell_aktar() ;
				}
				else if (sonuc == "FATURA RAPORLAMA")
				{
					FATURA_RAPOR.excell_aktar() ;
				}
				else if (sonuc == "ENVANTER DOKUM")
				{
					ENVANTER.excell_aktar() ;
				}
				else if (sonuc == "IMALAT RAPORLAMA")
				{
					IMALAT_RAPORLAMA.excell_aktar() ;
				}
				else if (sonuc == "STOK_RAPOR")
				{
					STOK_RAPOR.excell_aktar() ;
				}
				else if (sonuc == "CARI OZEL MIZAN")
				{
					OZEL_MIZAN.excell_aktar() ;
				}
				else if (sonuc == "ORTALAMA SATIS")
				{
					ORTALAMA_FIAT.excell_aktar() ;
				}
				GuiUtil.setWaitCursor(toolBar,false);
			}
		});
		btnExcell.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/excel-icon_16.png")));
		toolBar.add(btnExcell);

		btnGrafik = new JButton("");
		btnGrafik.setToolTipText("Grafik");
		btnGrafik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean varmi = pencere_bak("GRAFIK");
					if (varmi ) 
					{
						pencere_aktiv_yap("GRAFIK");
					}
					else
					{
						GuiUtil.setWaitCursor(toolBar,true);
						String sonuc = pencere_activmi();
						if (sonuc == "GRUP RAPOR")
						{
							GRUP_RAPOR.grafik();
						}
						else if (sonuc == "IMALAT GRUP RAPOR")
						{
							IMALAT_GRUP_RAPOR.grafik();
						}
						else if (sonuc == "KUR GRAFIK")
						{
							KUR_GRAFIK.grafik_kur();
						}
						else 
						{
							GuiUtil.setWaitCursor(toolBar,false);
							return;
						}
						if (	GLOBAL.g_baslik == "" ) 
						{
							GuiUtil.setWaitCursor(toolBar,false);
							return ;
						}


						JInternalFrame internalFrame;
						internalFrame = new GRAFIK();
						desktopPane.add(internalFrame);
						internalFrame.setVisible(true);
						GuiUtil.setWaitCursor(toolBar,false);
					}
				}
				catch (Exception ex) 
				{
					GuiUtil.setWaitCursor(toolBar,false);		
					JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grafik", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnGrafik.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-edit-graph-report-16.png")));
		toolBar.add(btnGrafik);

		JLabel lblNewLabel_5 = new JLabel("          ");
		toolBar.add(lblNewLabel_5);

		JButton btnNewButton_27 = new JButton("");
		btnNewButton_27.setToolTipText("Yeni / Satir Ilave");
		btnNewButton_27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUtil.setWaitCursor(toolBar,true);
				String sonuc = pencere_activmi();
				if (sonuc == "KULLANICI EKLEME")	USER_EKLEME.yeni();
				else if (sonuc == "KULLANICI DETAY EKLEME")	USER_DETAY_EKLEME.yeni();
				else if (sonuc == "HESAP GIRISI")	H_PLANI.yeni();
				else if (sonuc == "CEK GIRIS")	CEK_GIRIS.satir_ilave();
				else if (sonuc == "CEK CIKIS")	CEK_CIKIS.satir_ilave();
				else if (sonuc == "ADRES GIRISI")	ADRES_GIRIS.yeni();
				else if (sonuc == "MAIL")	MAIL.yeni();
				else if (sonuc == "SMS")	SMS.yeni();
				else if (sonuc == "DEGISKENLER")	DEGISKEN_GIRIS.yeni();
				else if (sonuc == "URUN KARTI")	URUN_KART.yeni();
				else if (sonuc == "GIDECEGI YER")	GIDECEGI_YER.yeni();
				else if (sonuc == "COKLU IMALAT")	COKLU_IMALAT.satir_ilave();
				GuiUtil.setWaitCursor(toolBar,false);
			}
		});
		btnNewButton_27.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/yeni.png")));
		toolBar.add(btnNewButton_27);

		JButton btnNewButton_28 = new JButton("");
		btnNewButton_28.setToolTipText("Satir Sil");
		btnNewButton_28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUtil.setWaitCursor(toolBar,true);
				String sonuc = pencere_activmi();
				if (sonuc == "TOPLU GIRIS")	DISTAN_AKTAR.satir_sil();
				else if (sonuc == "CEK GIRIS")	CEK_GIRIS.satir_sil();
				else if (sonuc == "CEK CIKIS")	CEK_CIKIS.satir_sil();
				else if (sonuc == "MAIL")	MAIL.satir_sil();
				else if (sonuc == "SMS")	SMS.satir_sil();
				else if (sonuc == "COKLU IMALAT")	COKLU_IMALAT.satir_sil();
				GuiUtil.setWaitCursor(toolBar,false);
			}
		});
		btnNewButton_28.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-reduce-16.png")));
		toolBar.add(btnNewButton_28);

		JLabel lblNewLabel_6 = new JLabel("          ");
		toolBar.add(lblNewLabel_6);

		JButton btnNewButton_29 = new JButton("");
		btnNewButton_29.setToolTipText("Cari Hesap Isleme");
		btnNewButton_29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUtil.setWaitCursor(toolBar,true);
				String sonuc = pencere_activmi();
				if (sonuc == "CEK GIRIS")	CEK_GIRIS.cari_kaydet();
				else if (sonuc == "CEK CIKIS")	CEK_CIKIS.cari_kaydet();
				else if (sonuc =="FATURA		- SATIS" || sonuc =="FATURA		- ALIS" )	FATURA.cari_kaydet();
				GuiUtil.setWaitCursor(toolBar,false);
			}
		});
		btnNewButton_29.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/dek-16.png")));
		toolBar.add(btnNewButton_29);

		JLabel lblNewLabel_7 = new JLabel("          ");
		toolBar.add(lblNewLabel_7);

		JButton btnNewButton_30 = new JButton("");
		btnNewButton_30.setToolTipText("Hesap Makinasi");
		btnNewButton_30.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					GuiUtil.setWaitCursor(toolBar,true);
					Runtime.getRuntime().exec("calc");
					GuiUtil.setWaitCursor(toolBar,false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_30.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/icons8-calculator-16.png")));
		toolBar.add(btnNewButton_30);
		
		final Dimension size = label.getPreferredSize();

		JLabel lblNewLabel_32 = new JLabel("     ");
		toolBar.add(lblNewLabel_32);

		JButton btnNewButton_93 = new JButton("");
		btnNewButton_93.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUtil.setWaitCursor(toolBar,true);
				CAL_DIZIN frame = new CAL_DIZIN();
				frame.setVisible(true);
				dispose();
				GuiUtil.setWaitCursor(toolBar,false);
			}
		});
		btnNewButton_93.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/db.png")));
		btnNewButton_93.setToolTipText("Calisma Dizini");
		toolBar.add(btnNewButton_93);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUtil.setWaitCursor(toolBar,true);
				try {
					LOGIN frame = new LOGIN();
					frame.setVisible(true);
					dispose();
					GuiUtil.setWaitCursor(toolBar,false);
				} catch (IOException e1) {
					GuiUtil.setWaitCursor(toolBar,false);
					e1.printStackTrace();
				}
				GuiUtil.setWaitCursor(toolBar,false);
			}
		});
		button_1.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/user-16.png")));
		button_1.setToolTipText("Giris");
		toolBar.add(button_1);


		JLabel lblNewLabel_8 = new JLabel("          ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		toolBar.add(lblNewLabel_8);

		lblNewLabel_9 = new JLabel("...");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_9.setForeground(new Color(128, 0, 0));
		lblNewLabel_9 .setMinimumSize(size);
		lblNewLabel_9 .setPreferredSize(new Dimension(250, 14));
		toolBar.add(lblNewLabel_9);

		toolBar_1 = new JToolBar();
		toolBar_1.setBorder(new LineBorder(new Color(0, 191, 255)));
		toolBar_1.setFloatable(false);
		toolBar_1.setMinimumSize(new Dimension(0, 30));
		toolBar_1.setMaximumSize(new Dimension(0, 30));

		JLabel lblNewLabel_33 = new JLabel("  ");
		toolBar_1.add(lblNewLabel_33);

		progressBar = new JProgressBar();
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 11));
		//progressBar.setBorder(new LineBorder(new Color(0, 191, 255)));
		progressBar.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 1));   
		progressBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		//progressBar.setForeground(new Color(166, 55, 55));
		progressBar.setMaximumSize(new Dimension(350, 30));
		progressBar.setMinimumSize(new Dimension(350, 30));


		toolBar_1.add(progressBar);

		JLabel lblNewLabel_25 = new JLabel("     ");
		toolBar_1.add(lblNewLabel_25);

		lblNewLabel_1 = new JLabel(".....");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setForeground(new Color(32, 178, 170));
		toolBar_1.add(lblNewLabel_1);

		JLabel lblNewLabel_26 = new JLabel("     ");
		toolBar_1.add(lblNewLabel_26);

		lblNewLabel_2 = new JLabel(".....");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setForeground(new Color(220, 20, 60));
		toolBar_1.add(lblNewLabel_2);

		JLabel lblNewLabel_27 = new JLabel("     ");
		toolBar_1.add(lblNewLabel_27);

		lblNewLabel_3 = new JLabel(".....");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setForeground(new Color(0, 0, 255));
		toolBar_1.add(lblNewLabel_3);

		JLabel lblNewLabel_28 = new JLabel("     ");
		toolBar_1.add(lblNewLabel_28);

		lblNewLabel_21 = new JLabel(".....");
		lblNewLabel_21.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_21.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_21.setForeground(new Color(60, 179, 113));
		toolBar_1.add(lblNewLabel_21);

		JLabel lblNewLabel_29 = new JLabel("     ");
		toolBar_1.add(lblNewLabel_29);

		lblNewLabel_22 = new JLabel(".....");
		lblNewLabel_22.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_22.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_22.setForeground(new Color(199, 21, 133));
		toolBar_1.add(lblNewLabel_22);

		JLabel lblNewLabel_30 = new JLabel("     ");
		toolBar_1.add(lblNewLabel_30);

		lblNewLabel_23 = new JLabel(".....");
		lblNewLabel_23.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_23.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_23.setForeground(new Color(0, 0, 205));
		toolBar_1.add(lblNewLabel_23);

		JLabel lblNewLabel_31 = new JLabel("     ");
		toolBar_1.add(lblNewLabel_31);

		lblNewLabel_24 = new JLabel(".....");
		lblNewLabel_24.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_24.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_24.setForeground(new Color(255, 0, 255));
		toolBar_1.add(lblNewLabel_24);

		desktopPane = new JDesktopPane();
		//desktopPane.setBackground(new Color(39, 45, 61));
		DesktopScrollPane scrpane = new DesktopScrollPane(desktopPane );


		JSplitPane ortapane = new  JSplitPane();
		ortapane.setDividerSize(0);
		ortapane.setResizeWeight(1.0);
		ortapane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		ortapane.setLeftComponent(scrpane );

		JScrollPane qaz = new JScrollPane();
		qaz.setMinimumSize(new Dimension(0, 30));
		qaz.setMaximumSize(new Dimension(0, 30));
		qaz.setViewportView (toolBar_1);

		ortapane.setRightComponent(qaz);
		contentPane.add( ortapane, BorderLayout.CENTER);

		
		progressBar.setStringPainted(false);
		try {
			if (GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("Metal"))
			{
				setExtendedStatee(JFrame.MAXIMIZED_BOTH);
			}
			else
			{
				setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// GRID RENK
		String deger;
		try {
			deger = GLOBAL.setting_oku("PRG_GRID_RENK").toString();
			String[] parts;
			parts = deger.split(",");
			OBS_SIS_2025_ANA_CLASS.gridcolor =  new Color( Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		form_ac("CALISMA DIZINLERI","");
		//
	}
	private void form_ac(String pencere,String hangi) 
	{
		boolean varmi = pencere_bak(pencere);

		if (varmi  ) 
		{
			try {
				pencere_aktiv_yap(pencere);
			} catch (PropertyVetoException e1) {	e1.printStackTrace();	}
			return;
		}
		else
		{
			GuiUtil.setWaitCursor(tabbedPane,true);
			JInternalFrame internalFrame = null;
			if (pencere.equals("DEKONT")) internalFrame  = new DEKONT();
			else if (pencere.equals("EKSTRE")) internalFrame  = new EKSTRE();
			else if (pencere.equals("HESAP GIRISI")) internalFrame  = new H_PLANI();
			else if (pencere.equals("TOPLU GIRIS")) internalFrame  = new DISTAN_AKTAR();
			else if (pencere.equals("CARI MIZAN")) internalFrame  = new MIZAN();
			else if (pencere.equals("DOVIZE CEVIRME")) internalFrame  = new DVZ_CEVIRME();
			else if (pencere.equals("CARI OZEL MIZAN")) internalFrame  = new OZEL_MIZAN();
			else if (pencere.equals("HESAP BAK")) internalFrame  = new HESAP_BAK();
			else if (pencere.equals("CARI ARAMA")) internalFrame  = new ARAMA();
			else if (pencere.equals("GUNLUK TAKIP")) internalFrame  = new KASA();
			else if (pencere.equals("HESAP PLANI DETAY")) internalFrame  = new HESAP_PLANI_DETAY();
			else if (pencere.equals("GUNLUK ISLEM")) internalFrame  = new GUNLUK_ISLEM();
			else if (pencere.equals("ORNEK HESAP PLANI")) internalFrame  = new ORN_HSP_PLN();
			else if (pencere.equals("KOD DEGISTIRME")) internalFrame  = new KOD_DEGISTIRME();
			else if (pencere.equals("YIL SONU AKTARMA")) internalFrame  = new YIL_SONU();
			//KUR
			else if (pencere.equals("KUR GIRIS")) internalFrame  = new KUR_GIRIS();
			else if (pencere.equals("KUR RAPORLAMA")) internalFrame  = new KUR_RAPORLAMA();
			else if (pencere.equals("KUR GRAFIK")) internalFrame  = new KUR_GRAFIK();
			else if (pencere.equals("EKSIK_KUR")) internalFrame  = new EKSIK_KUR();
			//ADRES
			else if (pencere.equals("ADRES GIRISI")) internalFrame  = new ADRES_GIRIS();
			else if (pencere.equals("ADRES DETAY")) internalFrame  = new ADRESLER();
			else if (pencere.equals("ETIKET")) internalFrame  = new ETIKET();
			//else if (pencere.equals("ETIKET PRINT")) internalFrame  = new ETIKET_PRINT();
			else if (pencere.equals("ETIKET AYAR")) internalFrame  = new ETIKET_AYAR();
			//GUNLUK
			else if (pencere.equals("GUNLUK")) internalFrame  = new Gunluk();
			else if (pencere.equals("GOREV GIRIS")) internalFrame  = new GOREV_GIRIS();
			else if (pencere.equals("HAZIR GOREVLER")) internalFrame  = new HAZIR_GOREVLER();
			//STOK
			else if (pencere.equals("URUN KARTI")) internalFrame  = new URUN_KART();
			else if (pencere.equals("IMALAT")) internalFrame  = new IMALAT();
			else if (pencere.equals("RECETE")) internalFrame  = new RECETE();
			else if (pencere.equals("COKLU IMALAT")) internalFrame  = new COKLU_IMALAT();
			else if (pencere.equals("URETIM FIS NO YENILEME")) internalFrame  = new URETIM_FIS_NO();
			else if (pencere.equals("FATURA		- SATIS")) internalFrame  = new FATURA();
			else if (pencere.equals("IRSALIYE		- SATIS")) internalFrame  = new IRSALIYE();
			else if (pencere.equals("ZAYI")) internalFrame  = new ZAYI();
			else if (pencere.equals("FATURA RAPORLAMA")) internalFrame  = new FATURA_RAPOR();
			else if (pencere.equals("ENVANTER DOKUM")) internalFrame  = new ENVANTER();
			else if (pencere.equals("URUN LISTE")) internalFrame  = new URUN_LISTE();
			else if (pencere.equals("STOK DETAY")) internalFrame  = new STOK_DETAY();
			else if (pencere.equals("IRSALIYE RAPOR")) internalFrame  = new IRSALIYE_RAPOR();
			else if (pencere.equals("IMALAT RAPORLAMA")) internalFrame  = new IMALAT_RAPORLAMA();
			else if (pencere.equals("RECETE RAPORLAMA")) internalFrame  = new RECETE_RAPOR();
			else if (pencere.equals("GRUP RAPOR")) internalFrame  = new GRUP_RAPOR();
			else if (pencere.equals("IMALAT GRUP RAPOR")) internalFrame  = new IMALAT_GRUP_RAPOR();
			else if (pencere.equals("STOK RAPOR")) internalFrame  = new STOK_RAPOR();
			else if (pencere.equals("ORTALAMA SATIS")) internalFrame  = new ORTALAMA_FIAT();
			else if (pencere.equals("STOK BOS KUR")) internalFrame  = new FAT_BOS_KUR();
			else if (pencere.equals("E FATURA ARAMA")) internalFrame  = new E_FATURA();
			else if (pencere.equals("ZAYI RAPORLAMA")) internalFrame  = new ZAYI_RAPOR();
			else if (pencere.equals("EVRAK FORMATLAMA")) internalFrame  = new EVRAK_FORMAT();
			else if (pencere.equals( "DEGISKEN YENILEME")) internalFrame  = new DEGISKEN_DEGIS();
			else if (pencere.equals( "STOK YIL SONU")) internalFrame  = new STK_YIL_SONU();
			//KAMBIYO
			else if (pencere.equals("CEK GIRIS")) internalFrame  = new CEK_GIRIS();
			else if (pencere.equals("CEK CIKIS")) internalFrame  = new CEK_CIKIS();
			else if (pencere.equals("CEK RAPOR")) internalFrame  = new CEK_RAPOR();
			else if (pencere.equals("CEK TAKIP")) internalFrame  = new CEK_TAKIP();
			//		 //SMS-MAIL
			else if (pencere.equals("SMS")) internalFrame  = new SMS();
			else if (pencere.equals("MAIL")) internalFrame  = new MAIL();
			//else if (pencere.equals("SMS INET")) internalFrame  = new SMS_INET();
			//		 //GENEL
			else if (pencere.equals("GIDEN RAPORLAR")) internalFrame  = new GID_RAPOR();
			else if (pencere.equals("CALISMA DIZINLERI")) internalFrame  = new MODUL_PARAMETRE();
			//AYARLAR
			else if (pencere.equals("KULLANICI EKLEME")) internalFrame  = new  USER_EKLEME();
			else if (pencere.equals("KULLANICI DETAY EKLEME")) internalFrame  = new  USER_DETAY_EKLEME();
			else if (pencere.equals("KULLANICI KOPYALA")) internalFrame  = new  USER_KOPYALA();
			else if (pencere.equals("PARAMETRELER")) internalFrame  = new  PARAMETRELER();
			else if (pencere.equals("E MAIL BILGILERI")) internalFrame  = new  E_MAIL_BILGILERI();
			//
			else if (pencere.equals("FIRMA ISMI DEGISTIRME")) internalFrame  = new FIRMA_ISMI_DEGIS();
			else if (pencere.equals("E MAIL GONDERME")) internalFrame  = new E_MAIL_GONDERME();
			else if (pencere.equals("DEGISKENLER")) internalFrame  = new DEGISKEN_GIRIS(hangi);
			else if (pencere.equals("SQL SORGULAMA")) internalFrame  = new SQL_SORGULAMA(hangi);
			else if (pencere.equals("LOG RAPORLAMA")) internalFrame  = new LOGLAMA_RAPOR();
			else if (pencere.equals("HAKKINDA")) internalFrame  = new HAKKINDA();
			//3536
			desktopPane.add(internalFrame);
			internalFrame.setVisible(true);
			GuiUtil.setWaitCursor(tabbedPane,false);
		}
	}
	private String  pencere_activmi()
	{
		String result = "";
		for(int i=0;i<desktopPane.getAllFrames().length;i++)
		{   
			JInternalFrame frame =(JInternalFrame) desktopPane.getComponent(i);
			if (frame.isSelected() )
			{
				result = frame.getTitle();
				break; 
			}
		}
		return result;
	}
	public static boolean pencere_bak(String pencere)
	{
		boolean result = false;
		for(int i=0;i<desktopPane.getAllFrames().length;i++)
		{   
			JInternalFrame frame=(JInternalFrame) desktopPane.getComponent(i);
			String tit=frame.getTitle();
			if (tit.equals(pencere) )
			{
				result = true;
				break; 
			}
		}
		return result;
	}
	public static void pencere_aktiv_yap(String pencere) throws PropertyVetoException
	{
		for(int i=0;i<desktopPane.getAllFrames().length;i++)
		{   
			JInternalFrame frame=(JInternalFrame) desktopPane.getComponent(i);
			String tit=frame.getTitle();
			if (tit.equals(pencere) )
			{
				frame.setSelected(true);
				break; 
			}
		}
	}
	public synchronized  void setExtendedStatee(int state)
	{       
		if (maxBounds == null &&
				(state & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH)
		{
			Insets screenInsets = getToolkit().getScreenInsets(getGraphicsConfiguration());         
			Rectangle screenSize = getGraphicsConfiguration().getBounds();
			Rectangle maxBounds = new Rectangle(screenInsets.left + screenSize.x, 
					screenInsets.top + screenSize.y, 
					screenSize.x + screenSize.width - screenInsets.right - screenInsets.left,
					screenSize.y + screenSize.height - screenInsets.bottom - screenInsets.top);
			super.setMaximizedBounds(maxBounds);
		}
		super.setExtendedState(state);
	}
}
