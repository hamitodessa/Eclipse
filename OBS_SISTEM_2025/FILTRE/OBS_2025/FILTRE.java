package OBS_2025;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import javax.swing.text.MaskFormatter;

import javax.swing.JLabel;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

import GUNLUK.HAZIR_GOREVLER;
import KER_RAPOR.KER_DETAY;
import KER_RAPOR.KER_ENVANTER;
import KER_RAPOR.KER_FAT_RAPOR;
import KER_RAPOR.KER_GRUP_RAPOR;
import KER_RAPOR.KER_ORT_FIAT;
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
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GUNLUK_ACCESS;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.TARIH_CEVIR;
import raven.toast.Notifications;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.InputMap;

import java.awt.event.KeyAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JFormattedTextField;

@SuppressWarnings({ "static-access", "serial" })
public class FILTRE extends JDialog {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS() ;
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	private static GUNLUK_ACCESS  g_Access = new GUNLUK_ACCESS(OBS_SIS_2025_ANA_CLASS._IGunluk , OBS_SIS_2025_ANA_CLASS._IGunluk_Loger);
	private static STOK_ACCESS  f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	private static KERESTE_ACCESS  ker_Access = new KERESTE_ACCESS(OBS_SIS_2025_ANA_CLASS._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);
	private final JPanel contentPanel = new JPanel();
	public static JButton okButton ;
	public static JTabbedPane tabbedPane ;
	private static String [] hpl = {"","",""};
	//EKSTRE
	public static Obs_TextFIeld txtkodu;
	public static JDateChooser dateChooser ;
	public static JDateChooser dateChooser_1 ; 
	public static JLabel lblNewLabel_1 ;
	public static JLabel lblNewLabel_2 ; 
	//**** MIZAN
	public static JComboBox<String> comboBox ;
	public static Obs_TextFIeld txtilk;
	public static Obs_TextFIeld txtson;
	public static Obs_TextFIeld txticins;
	public static Obs_TextFIeld txtscins;
	public static Obs_TextFIeld txtikarton;
	public static Obs_TextFIeld txtskarton;
	public static JDateChooser dateChooser_2 ;
	public static JDateChooser dateChooser_2_1 ;
	//**** DVZ CEVIR
	public static Obs_TextFIeld txtdvz;
	private static JLabel lblNewLabel_1_1  ;
	public static JLabel lblNewLabel_2_1  ;
	public static JComboBox<String> comboBox_1 ;
	public static JComboBox<String> comboBox_2 ;
	public static JDateChooser dateChooser_3 ;
	public static JDateChooser dateChooser_4 ;
	public static JCheckBox chckbxNewCheckBox_4 ;
	//**** ARAMA
	public static Obs_TextFIeld textField;
	public static Obs_TextFIeld textField_1;
	public static Obs_TextFIeld textField_2;
	public static Obs_TextFIeld textField_3;
	public static Obs_TextFIeld textField_4;
	public static Obs_TextFIeld textField_5;
	public static Obs_TextFIeld textField_79;
	public static JCheckBox chcbaslayan ;
	public static JCheckBox chcile ;
	private JPanel panel_3;
	//***** TAHSILAT
	public static JComboBox<String> comboBox_85 ;
	public static JComboBox<String> comboBox_86 ;
	public static JComboBox<String> comboBox_87;
	public static JDateChooser dateChooser_35;
	public static JDateChooser dateChooser_36;
	public static Obs_TextFIeld textTaheno1;
	public static Obs_TextFIeld textTaheno2;
	public static Obs_TextFIeld textTahck1;
	public static Obs_TextFIeld textTahck2;
	//**** Gunluk ISLEM
	public static JDateChooser dateChooser_5 ;
	public static JDateChooser dateChooser_5_1 ;
	//**** CEK RAPOR
	public static Obs_TextFIeld txtcn1;
	public static Obs_TextFIeld txtcn2;
	public static Obs_TextFIeld txtgb1;
	public static Obs_TextFIeld txtgb2;
	public static Obs_TextFIeld txtcb1;
	public static Obs_TextFIeld txtcb2;
	public static Obs_TextFIeld txtgh1;
	public static Obs_TextFIeld txtgh2;
	public static Obs_TextFIeld txtd1;
	public static Obs_TextFIeld txtd2;
	public static Obs_TextFIeld txtc1;
	public static Obs_TextFIeld txtc2;
	public static Obs_TextFIeld txtch1;
	public static Obs_TextFIeld txtch2;
	public static JDateChooser dateChooser_6 ;
	public static JDateChooser dateChooser_6_1 ;
	public static JDateChooser dateChooser_7 ;
	public static JDateChooser dateChooser_8 ;
	public static JDateChooser dateChooser_9 ;
	public static JDateChooser dateChooser_10 ;
	public static JDateChooser dateChooser_6_2 ;
	public static JDateChooser dateChooser_6_2_1 ;
	public static JComboBox<String> cmbg ;
	public static JComboBox<String> cmbc ;
	//*** KUR 
	public static JDateChooser dateChooser_11 ;
	public static JDateChooser dateChooser_11_1 ;
	public static  Obs_TextFIeld txtkc1;
	public static  Obs_TextFIeld txtkc2;
	//KUR RAPORLAMA
	public static JDateChooser dateChooser_32;
	public static JDateChooser dateChooser_32_1;
	public static JComboBox<String> comboBox_72;
	public static JComboBox<String> comboBox_73;
	public static JComboBox<String> comboBox_74;
	//*** FATURA
	public static JPanel panel_6;
	public static Obs_TextFIeld textField_6;
	public static Obs_TextFIeld textField_7;
	public static Obs_TextFIeld textField_8;
	public static Obs_TextFIeld textField_9;
	public static Obs_TextFIeld textField_10;
	public static Obs_TextFIeld textField_11;
	public static Obs_TextFIeld txtZzzzzzzzzz;
	public static Obs_TextFIeld txtZzzzzzzzzzzz;
	public static Obs_TextFIeld txtZzzzzzzzzzzz_1;
	public static Obs_TextFIeld textField_15;
	public static Obs_TextFIeld txtZzzzzzzzzz_1;
	public static Obs_TextFIeld txtZzz;
	public static JComboBox<String> comboBox_3;
	public static JComboBox<String> comboBox_4;
	public static JComboBox<String> comboBox_5;
	public static JComboBox<String> comboBox_6;
	public static JComboBox<String> comboBox_7;
	public static JComboBox<String> comboBox_8;
	public static JLabel lblUrunKodu;
	public static Obs_TextFIeld textField_12;
	public static Obs_TextFIeld textField_13;
	public static JDateChooser dateChooser_12 ;
	public static JDateChooser dateChooser_13 ;
	//*** IMALAT RAPORLAMA
	public static JComboBox<String> comboBox_9 ;
	public static JComboBox<String> comboBox_10 ;
	public static JComboBox<String> comboBox_11 ;
	public static JComboBox<String> comboBox_12 ;
	public static JComboBox<String> comboBox_13 ;
	public static Obs_TextFIeld textField_14;
	public static Obs_TextFIeld textField_16;
	public static Obs_TextFIeld textField_17;
	public static Obs_TextFIeld textField_21;
	public static Obs_TextFIeld textField_22;
	public static Obs_TextFIeld textField_23;
	public static Obs_TextFIeld textField_27;
	public static Obs_TextFIeld textField_28;
	public static JDateChooser dateChooser_14 ;
	public static JDateChooser dateChooser_15 ;
	//** STOK_RAPOR
	//private JPanel panel_9;
	public static Obs_TextFIeld textField_18;
	public static Obs_TextFIeld textField_19;
	public static Obs_TextFIeld textField_20;
	public static Obs_TextFIeld textField_24;
	public static JDateChooser dateChooser_16 ;
	public static JDateChooser dateChooser_17 ;
	public static JComboBox<String> comboBox_14 ;
	public static JComboBox<String> comboBox_15 ;
	public static JComboBox<String> comboBox_16 ;
	public static JComboBox<String> comboBox_17 ;
	public static JComboBox<String> comboBox_18 ;
	public static JComboBox<String> comboBox_19 ;
	public static JCheckBox chckbxNewCheckBox ;
	public static JCheckBox chckbxNewCheckBox_1 ;
	public static JCheckBox chckbxNewCheckBox_2;
	//** STOK_DETAY
	public static  Obs_TextFIeld textField_25;
	public static  Obs_TextFIeld textField_26;
	public static  Obs_TextFIeld textField_29;
	public static  Obs_TextFIeld textField_30;
	public static JComboBox<String> comboBox_20;
	public static JComboBox<String> comboBox_21;
	public static JComboBox<String> comboBox_22;
	public static JComboBox<String> comboBox_23;
	public static JComboBox<String> comboBox_24;
	public static JComboBox<String> comboBox_25;
	public static JDateChooser dateChooser_18 ;
	public static JDateChooser dateChooser_19 ;
	public static JCheckBox checkBox ;
	public static JCheckBox checkBox_1 ;
	public static Obs_TextFIeld textField_80;
	public static Obs_TextFIeld textField_81;
	//** GRUP RAPOR
	public static  Obs_TextFIeld textField_31;
	public static  Obs_TextFIeld textField_32;
	public static  Obs_TextFIeld textField_33;
	public static  Obs_TextFIeld textField_34;
	public static  Obs_TextFIeld textField_35;
	public static  Obs_TextFIeld textField_36;
	public static  Obs_TextFIeld textField_37;
	public static JDateChooser dateChooser_20 ;
	public static JDateChooser dateChooser_21 ;
	public static JComboBox<String> comboBox_26;
	public static JComboBox<String> comboBox_27;
	public static JComboBox<String> comboBox_28;
	public static JComboBox<String> comboBox_29;
	public static JComboBox<String> comboBox_30;
	public static JComboBox<String> comboBox_31;
	public static JComboBox<String> comboBox_32;
	public static JComboBox<String> comboBox_33;
	public static JCheckBox chckbxIstenenAy ;
	public static JCheckBox chckbxDovizeCevirme ;
	public static  Obs_TextFIeld textField_39;
	public static  Obs_TextFIeld textField_40;
	public static  Obs_TextFIeld textField_42;
	public static  Obs_TextFIeld textField_43;
	public static JDateChooser dateChooser_22 ;
	public static JDateChooser dateChooser_23 ;
	public static JComboBox<String> comboBox_34;
	public static JComboBox<String> comboBox_35;
	public static JComboBox<String> comboBox_36;
	public static JComboBox<String> comboBox_37;
	public static JComboBox<String> comboBox_38;
	public static JComboBox<String> comboBox_39;
	public static JComboBox<String> comboBox_40;
	public static JComboBox<String> comboBox_41;
	//** IRSALIYE RAPOR
	public static  Obs_TextFIeld textField_38;
	public static  Obs_TextFIeld textField_41;
	public static  Obs_TextFIeld textField_44;
	public static  Obs_TextFIeld textField_46;
	public static  Obs_TextFIeld textField_47;
	public static  Obs_TextFIeld textField_48;
	public static  Obs_TextFIeld textField_49;
	public static  Obs_TextFIeld textField_50;
	public static  Obs_TextFIeld  textField_53;
	public static  Obs_TextFIeld textField_54;
	public static  Obs_TextFIeld textField_55;
	public static JComboBox<String> comboBox_42;
	public static JComboBox<String> comboBox_43;
	public static JComboBox<String> comboBox_45;
	public static JDateChooser dateChooser_24 ;
	public static JDateChooser dateChooser_25 ;

	//** RECETE RAPOR
	public static Obs_TextFIeld textField_45;
	public static Obs_TextFIeld textField_58;
	public static Obs_TextFIeld textField_62;
	public static Obs_TextFIeld textField_63;
	public static JComboBox<String> comboBox_44;
	public static JComboBox<String> comboBox_46;
	public static JComboBox<String> comboBox_47;
	public static JComboBox<String> comboBox_48;
	public static JComboBox<String> comboBox_49;
	//** ORTALAMA FIAT
	public static Obs_TextFIeld textField_51;
	public static Obs_TextFIeld textField_52;
	public static Obs_TextFIeld textField_56;
	public static Obs_TextFIeld textField_57;
	public static Obs_TextFIeld textField_59;
	public static Obs_TextFIeld textField_60;
	public static JComboBox<String> comboBox_56;
	public static JComboBox<String> comboBox_57;
	public static JComboBox<String> comboBox_51;
	public static JComboBox<String> comboBox_50;
	public static JDateChooser dateChooser_26 ;
	public static JDateChooser dateChooser_27 ;
	private JLabel lblDoviz;
	//*** URUN LISTE
	public static Obs_TextFIeld textField_61;
	public static Obs_TextFIeld textField_64;
	public static Obs_TextFIeld textField_65;
	public static Obs_TextFIeld textField_66;
	public static Obs_TextFIeld textField_67;
	public static Obs_TextFIeld textField_68;
	public static Obs_TextFIeld textField_69;
	public static Obs_TextFIeld textField_70;
	public static JComboBox<String> comboBox_52;
	public static JComboBox<String> comboBox_53;
	public static JComboBox<String> comboBox_54;
	public static JComboBox<String> comboBox_55;
	public static JComboBox<String> comboBox_58;
	public static JComboBox<String> comboBox_59;
	//*** ENVANTER
	public static Obs_TextFIeld textField_71;
	public static Obs_TextFIeld textField_72;
	public static Obs_TextFIeld textField_73;
	public static Obs_TextFIeld textField_74;
	public static JComboBox<String> comboBox_60;
	public static JComboBox<String> comboBox_61;
	public static JComboBox<String> comboBox_62;
	public static JComboBox<String> comboBox_63;
	public static JComboBox<String> comboBox_64;
	public static JComboBox<String> comboBox_65;
	public static JComboBox<String> comboBox_66;
	public static JDateChooser dateChooser_28 ;
	public static JDateChooser dateChooser_29 ;
	public static JCheckBox checkBox_2 ;
	public static JCheckBox checkBox_3 ;
	//*** ZAYI
	public static JDateChooser dateChooser_30;
	public static JDateChooser dateChooser_31;
	public static Obs_TextFIeld textField_75;
	public static Obs_TextFIeld textField_76;
	public static Obs_TextFIeld textField_77;
	public static Obs_TextFIeld textField_78;
	public static JComboBox<String> comboBox_67;
	public static JComboBox<String> comboBox_68;
	public static JComboBox<String> comboBox_69;
	public static JComboBox<String> comboBox_70;
	public static JComboBox<String> comboBox_71;
	//GUNLUK
	public static JComboBox<String> cmbGrv_Isim;
	public static JDateChooser dateChooser_33;
	public static JDateChooser dateChooser_34;
	public static JComboBox<String> comboBox_75;
	public static JComboBox<String> comboBox_76;
	
	//KERESTE GRUP
	public static JDateChooser dateChooser_20_1;
	public static JDateChooser dateChooser_21_1;
	public static JComboBox<String> comboBox_26_1 ;
	public static JComboBox<String> comboBox_27_1 ;
	public static JComboBox<String> comboBox_28_1 ;
	public static JFormattedTextField formattedTextField;
	public static JFormattedTextField formattedTextField_1;
	public static JComboBox<String> comboBox_77;
	public static JComboBox<String> comboBox_78;
	public static JComboBox<String> comboBox_79;
	public static JComboBox<String> comboBox_80;
	public static JComboBox<String> comboBox_77_1;
	public static JComboBox<String> comboBox_77_2;
	public static JComboBox<String> comboBox_80_6 ;
	
	public static Obs_TextFIeld textField_82;
	public static Obs_TextFIeld textField_83;
	public static Obs_TextFIeld textField_99;
	public static Obs_TextFIeld textField_100;
	public static JCheckBox chckbxDovizeCevirme_1 ;
	public static JCheckBox chckbxNewCheckBox_3 ;
	public static JLabel lblNewLabel_41;
	//KERESTE DETY
	public static JDateChooser dateChooser_20_1_1;
	public static JDateChooser dateChooser_21_1_1;
	public static JDateChooser dateChooser_20_1_1_1;
	public static JDateChooser dateChooser_21_1_1_1;
	
	public static JFormattedTextField formattedTextField_2;
	public static JFormattedTextField formattedTextField_1_1;
	public static JComboBox<String> comboBox_78_1;
	public static JComboBox<String> comboBox_79_1;
	public static JComboBox<String> comboBox_80_1;
	public static JComboBox<String> comboBox_80_2;
	public static JComboBox<String> comboBox_79_2;
	public static JComboBox<String> comboBox_78_2;
	public static JComboBox<String> comboBox_80_3;
	public static JComboBox<String> comboBox_80_4;
	public static JComboBox<String> comboBox_7_1 ;
	public static JComboBox<String> comboBox_8_1 ;
	public static JComboBox<String> comboBox_81;
	public static Obs_TextFIeld textField_84;
	public static Obs_TextFIeld textField_85;
	public static Obs_TextFIeld textField_86;
	public static Obs_TextFIeld textField_87;
	public static Obs_TextFIeld textField_88;
	public static Obs_TextFIeld textField_88_1;
	public static Obs_TextFIeld textField_90;
	public static Obs_TextFIeld textField_91;
	public static Obs_TextFIeld textField_92;
	public static Obs_TextFIeld textField_93;
	public static Obs_TextFIeld textField_89;
	public static Obs_TextFIeld textField_94;
	public static JComboBox<String> comboBox_84;
	public JLabel lblGrup;
	//KERESTE ORT FIAT
	public static JComboBox<String> comboBox_82;
	public static JComboBox<String> comboBox_80_5;
	public static JComboBox<String> comboBox_78_3;
	public static JComboBox<String> comboBox_79_3;
	public static JComboBox<String> comboBox_27_1_1;
	public static Obs_TextFIeld textField_95;
	public static Obs_TextFIeld textField_96;
	public static Obs_TextFIeld textField_97;
	public static Obs_TextFIeld textField_98;
	public static JFormattedTextField formattedTextField_3 ;
	public static JFormattedTextField formattedTextField_1_2;
	public static JDateChooser dateChooser_20_1_2 ;
	public static JDateChooser dateChooser_21_1_2 ;
	public static JComboBox<String> comboBox_83;
	public static JCheckBox chckbxNewCheckBox_5 ;

	
	
	public FILTRE() {
		setTitle("FILTRE");
		setModal(true);
		setBounds(100, 100, 800, 338);
		setResizable(false);	
		setLocationRelativeTo(null);

		JSplitPane splitPane= new JSplitPane() ;
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(1.0);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		tabbedPane = new JTabbedPane();
		//*****************************************************************************************************************
		// Goruntu icin asagidaki blogu kaldir 

		final boolean showTabsHeader = false;
		tabbedPane.setUI(new MetalTabbedPaneUI()
		{
		    @Override
		    protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
		        if (showTabsHeader) {return super.calculateTabAreaHeight(tabPlacement, horizRunCount, maxTabHeight);
		        } else {return 0;}	        }
		  protected void paintTabArea(Graphics g,int tabPlacement,int selectedIndex){}
		}); 

		tabbedPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		//final boolean showTabsHeader = false;
		//tabbedPane.setUI(new MetalTabbedPaneUI()
		//{
		//    @Override
		//    protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
		//        if (showTabsHeader) {return super.calculateTabAreaHeight(tabPlacement, horizRunCount, maxTabHeight);
		//        } else {return 0;}	        }
		//  protected void paintTabArea(Graphics g,int tabPlacement,int selectedIndex){}
		//}); 

//*****************************************************************************************************************		    
		splitPane.setLeftComponent(tabbedPane);
		//*******************************EKSTRE ****************************************************
		JPanel paneleks = new JPanel();
		tabbedPane.addTab("Ekstre", null, paneleks, null);
		
		paneleks.setLayout(null);
		JLabel lblNewLabelh = new JLabel("Hesap Kodu");
		lblNewLabelh.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabelh.setBounds(26, 30, 103, 14);
		paneleks.add(lblNewLabelh);

		lblNewLabel_1 = new JLabel(".....");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(139, 58, 315, 14);
		paneleks.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel(".....");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(139, 83, 120, 14);
		paneleks.add(lblNewLabel_2);

		JLabel lblNewLabel_3e = new JLabel("Ilk Tarih");
		lblNewLabel_3e.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3e.setBounds(26, 114, 87, 14);
		paneleks.add(lblNewLabel_3e);

		JLabel lblNewLabel_4 = new JLabel("Son Tarih");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(26, 139, 87, 14);
		paneleks.add(lblNewLabel_4);

		dateChooser =  new JDateChooser();
		dateChooser.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser.setDate(new Date());
			}
		});
		dateChooser.setBounds(139, 108, 120, 20);
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser.setDateFormatString("dd.MM.yyyy");
		dateChooser.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		dateChooser.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					okButton.doClick();
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
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
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
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
		paneleks.add(dateChooser);
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		dateChooser_1.setBounds(139, 133, 120, 20);
		dateChooser_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_1.setDate(new Date());
			}
		});
		dateChooser_1.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					okButton.doClick();
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_1));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, -1); 
						dateChooser_1.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_1));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
						dateChooser_1.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		paneleks.add(dateChooser_1);
		txtkodu = new Obs_TextFIeld(12);
		InputMap txtkoduMap = txtkodu.getInputMap();
		txtkoduMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_H,KeyEvent.CTRL_DOWN_MASK ), "foo");
		txtkodu.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				isimoku_ekstre();
			}
			public void removeUpdate(DocumentEvent e) {
				isimoku_ekstre();
			}
			public void insertUpdate(DocumentEvent e) {
				isimoku_ekstre();
			}
		});
		txtkodu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						txtkodu.setText( oac.hsp_hsp_kodu);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		txtkodu.addAncestorListener(new AncestorListener() {
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
						txtkodu.requestFocusInWindow();
					}
				});
			}
		});
		txtkodu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if(e.getKeyCode()==KeyEvent.VK_ENTER)
						okButton.doClick();
					String[] parts;
					String deger ;
					deger = GLOBAL.setting_oku("CARI_HSPPLN_CAG").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiersEx() & (parts[0].equals("E") ?  KeyEvent.CTRL_DOWN_MASK : KeyEvent.ALT_DOWN_MASK) ) != 0))
						{
							HESAP_PLN hsp ;
							getContentPane().setCursor(oac.WAIT_CURSOR);
							hsp = new HESAP_PLN();
							hsp.setVisible(true);
							txtkodu.setText(oac.hsp_hsp_kodu);
							getContentPane().setCursor(oac.DEFAULT_CURSOR);
						}
					}
				}
				catch (Exception ex)
				{

				}
			}
		});
		txtkodu.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtkodu.setBounds(139, 27, 145, 20);
		paneleks.add(txtkodu);
		//*******************************************MIZAN ********************************************
		JPanel panel_1m = new JPanel();

		tabbedPane.addTab("Mizan", null, panel_1m, null);
		panel_1m.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Ilk Hesap");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(27, 26, 59, 14);
		panel_1m.add(lblNewLabel_5);

		txtilk = new Obs_TextFIeld(12);
		txtilk.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtilk.selectAll();
			}
		});
		InputMap txtilkMap =txtilk.getInputMap();
		txtilkMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_H,KeyEvent.CTRL_DOWN_MASK ), "foo");
		txtilk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						txtilk.setText( oac.hsp_hsp_kodu);
					} catch (Exception ex) {
					}
				}
			}
		});
		txtilk.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if(e.getKeyCode()==KeyEvent.VK_ENTER)
						okButton.doClick();
					String[] parts;
					String deger ;
					deger = GLOBAL.setting_oku("CARI_HSPPLN_CAG").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiersEx() & (parts[0].equals("E") ?  KeyEvent.CTRL_DOWN_MASK : KeyEvent.ALT_DOWN_MASK) ) != 0))
						{
							HESAP_PLN hsp ;
							getContentPane().setCursor(oac.WAIT_CURSOR);
							hsp = new HESAP_PLN();
							hsp.setVisible(true);
							txtilk.setText(oac.hsp_hsp_kodu);
							getContentPane().setCursor(oac.DEFAULT_CURSOR);
						}
					}
				}
				catch (Exception ex)
				{

					}
				}
		});
		txtilk.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtilk.setBounds(96, 23, 141, 20);
		txtilk.addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorRemoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorMoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorAdded(AncestorEvent pEvent) {
				// TextField is added to its parent => request focus in Event Dispatch Thread
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						txtilk.requestFocusInWindow();
					}
				});
			}
		});
		panel_1m.add(txtilk);

		JLabel lblNewLabel_6 = new JLabel("Son Hesap");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_6.setBounds(283, 26, 78, 14);
		panel_1m.add(lblNewLabel_6);

		txtson = new Obs_TextFIeld(12);
		txtson.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtson.selectAll();
			}
		});
		InputMap txtsonMap =txtilk.getInputMap();
		txtsonMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_H,KeyEvent.CTRL_DOWN_MASK ), "foo");
		txtson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						txtson.setText( oac.hsp_hsp_kodu);
					} catch (Exception ex) {}
				}
			}
		});
		txtson.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
						okButton.doClick();
					String[] parts;
					String deger ;
					deger = GLOBAL.setting_oku("CARI_HSPPLN_CAG").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiersEx() & (parts[0].equals("E") ?  KeyEvent.CTRL_DOWN_MASK : KeyEvent.ALT_DOWN_MASK) ) != 0))
						{
							HESAP_PLN hsp ;
							getContentPane().setCursor(oac.WAIT_CURSOR);
							hsp = new HESAP_PLN();
							hsp.setVisible(true);
							txtson.setText(oac.hsp_hsp_kodu);
							getContentPane().setCursor(oac.DEFAULT_CURSOR);
						}
					}
				}
				catch (Exception ex)
				{

				}
			}
		});
		txtson.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtson.setText("ZZZZZZZZZZZZ");
		txtson.setBounds(371, 23, 141, 20);
		panel_1m.add(txtson);

		JLabel lblNewLabel_5_1 = new JLabel("Ilk Cins");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_1.setBounds(27, 54, 59, 14);
		panel_1m.add(lblNewLabel_5_1);

		txticins = new Obs_TextFIeld(3);
		txticins.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txticins.selectAll();
			}
		});
		txticins.setFont(new Font("Tahoma", Font.BOLD, 11));
		txticins.setColumns(10);
		txticins.setBounds(96, 51, 69, 20);
		panel_1m.add(txticins);

		JLabel lblNewLabel_6_1 = new JLabel("Son Cins");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_6_1.setBounds(283, 54, 61, 14);
		panel_1m.add(lblNewLabel_6_1);

		txtscins = new Obs_TextFIeld(3);
		txtscins.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtscins.selectAll();
			}
		});
		txtscins.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtscins.setText("ZZZ");
		txtscins.setColumns(10);
		txtscins.setBounds(371, 51, 69, 20);
		panel_1m.add(txtscins);

		JLabel lblNewLabel_5_2 = new JLabel("Ilk Karton");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_2.setBounds(27, 82, 69, 14);
		panel_1m.add(lblNewLabel_5_2);

		txtikarton = new Obs_TextFIeld(5);
		txtikarton.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtikarton.selectAll();
			}
		});
		txtikarton.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtikarton.setColumns(10);
		txtikarton.setBounds(96, 79, 69, 20);
		panel_1m.add(txtikarton);

		JLabel lblNewLabel_6_2 = new JLabel("Son Karton");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_6_2.setBounds(283, 82, 78, 14);
		panel_1m.add(lblNewLabel_6_2);

		txtskarton = new Obs_TextFIeld(5);
		txtskarton.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtskarton.selectAll();
			}
		});
		txtskarton.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtskarton.setText("ZZZZZ");
		txtskarton.setColumns(10);
		txtskarton.setBounds(371, 79, 69, 20);
		panel_1m.add(txtskarton);

		JLabel lblNewLabel_7 = new JLabel("Ilk Tarih");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7.setBounds(27, 113, 59, 14);
		panel_1m.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Son Tarih");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8.setBounds(283, 113, 78, 14);
		panel_1m.add(lblNewLabel_8);

		dateChooser_2 = new JDateChooser();
		dateChooser_2.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_2.setDate(new Date());
			}
		});
		dateChooser_2.setBounds(96, 107, 141, 20);
		dateChooser_2.setDateFormatString("dd.MM.yyyy");
		dateChooser_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_2.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		dateChooser_2.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					okButton.doClick();
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_2));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, -1); 
						dateChooser_2.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_2));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
						dateChooser_2.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel_1m.add(dateChooser_2);

		dateChooser_2_1 = new JDateChooser();
		dateChooser_2_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_2_1.setDate(new Date());
			}
		});
		dateChooser_2_1.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		dateChooser_2_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_2_1.setBounds(371, 107, 141, 20);
		dateChooser_2_1.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					okButton.doClick();
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_2_1));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, -1); 
						dateChooser_2_1.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_2_1));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
						dateChooser_2_1.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel_1m.add(dateChooser_2_1);

		JLabel lblNewLabel_9 = new JLabel("Tur");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_9.setBounds(27, 155, 46, 14);
		panel_1m.add(lblNewLabel_9);

		comboBox = new JComboBox<String>();
		//comboBox.setForeground(new Color(0, 0, 128));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Butun Hesaplar", "Borclu Hesaplar", "Alacakli Hesaplar", "Bakiyesi 0 Olanlar", "Bakiyesi 0 Olmayanlar"}));
		comboBox.setBounds(96, 151, 167, 22);
		panel_1m.add(comboBox);

		/// DOVIZE CEVIRME **********************************************************************
		JPanel panel = new JPanel();

		tabbedPane.addTab("Dvz_Cevirme", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hesap Kodu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 28, 103, 14);
		panel.add(lblNewLabel);

		txtdvz = new Obs_TextFIeld(12);
		InputMap txtdvzMap = txtdvz.getInputMap();
		txtdvzMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_H,KeyEvent.CTRL_DOWN_MASK ), "foo");
		txtdvz.addAncestorListener(new AncestorListener() {
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
						txtdvz.requestFocusInWindow();
					}
				});
			}
		});
		txtdvz.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				isimoku_dvz();
			}
			public void removeUpdate(DocumentEvent e) {
				isimoku_dvz();
			}
			public void insertUpdate(DocumentEvent e) {
				isimoku_dvz();
			}
		});
		txtdvz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						txtdvz.setText( oac.hsp_hsp_kodu);
						//isimoku_dvz();
					} catch (Exception ex) {}
				}
			}
		});
		txtdvz.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
						okButton.doClick();
					String[] parts;
					String deger ;
					deger = GLOBAL.setting_oku("CARI_HSPPLN_CAG").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiersEx() & (parts[0].equals("E") ?  KeyEvent.CTRL_DOWN_MASK : KeyEvent.ALT_DOWN_MASK) ) != 0))
						{
							HESAP_PLN hsp ;
							getContentPane().setCursor(oac.WAIT_CURSOR);
							hsp = new HESAP_PLN();
							hsp.setVisible(true);
							txtdvz.setText(oac.hsp_hsp_kodu);
							getContentPane().setCursor(oac.DEFAULT_CURSOR);
						}
					}
				}
				catch (Exception ex)
				{

				}
			}
		});
		txtdvz.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtdvz.setBounds(123, 25, 145, 20);
		panel.add(txtdvz);

		lblNewLabel_1_1 = new JLabel(".....");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		//lblNewLabel_1_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_1_1.setBounds(123, 56, 315, 14);
		panel.add(lblNewLabel_1_1);

		lblNewLabel_2_1 = new JLabel(".....");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		//lblNewLabel_2_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_2_1.setBounds(123, 81, 120, 14);
		panel.add(lblNewLabel_2_1);

		dateChooser_3 = new JDateChooser();
		dateChooser_3.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_3.setDate(new Date());
			}
		});
		dateChooser_3.setBounds(123, 113, 120, 20);
		dateChooser_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_3.setDateFormatString("dd.MM.yyyy");
		dateChooser_3.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		dateChooser_3.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					okButton.doClick();
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_3));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, -1); 
						dateChooser_3.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_3));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
						dateChooser_3.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel.add(dateChooser_3);

		dateChooser_4 = new JDateChooser();
		dateChooser_4.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_4.setDate(new Date());
			}
		});
		dateChooser_4.setBounds(301, 113, 120, 20);
		dateChooser_4.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		dateChooser_4.setDateFormatString("dd.MM.yyyy");
		dateChooser_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_4.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					okButton.doClick();
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_4));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, -1); 
						dateChooser_2.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_4));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
						dateChooser_4.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel.add(dateChooser_4);

		comboBox_1 = new JComboBox<String>();
		//comboBox_1.setForeground(new Color(0, 0, 128));
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				DVZ_CEVIRME.lblcevrilen.setText(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()) + " / " + comboBox_2.getItemAt(comboBox_2.getSelectedIndex()));
			}
		});
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"USD", "EUR"}));
		comboBox_1.setBounds(123, 144, 70, 22);
		panel.add(comboBox_1);

		comboBox_2 = new JComboBox<String>();
		//comboBox_2.setForeground(new Color(0, 0, 128));
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DVZ_CEVIRME.lblcevrilen.setText(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()) + " / " + comboBox_2.getItemAt(comboBox_2.getSelectedIndex()));
			}
		});
		comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"MA", "MS", "SA", "SS", "BA", "BS"}));
		comboBox_2.setBounds(123, 177, 55, 22);
		panel.add(comboBox_2);

		JLabel lblTarihAraligi = new JLabel("Tarih Araligi");
		lblTarihAraligi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTarihAraligi.setBounds(10, 119, 103, 14);
		panel.add(lblTarihAraligi);

		JLabel lblCevrilecek = new JLabel("Cevrilecek");
		lblCevrilecek.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCevrilecek.setBounds(10, 148, 103, 14);
		panel.add(lblCevrilecek);

		JLabel lblTur = new JLabel("Tur");
		lblTur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTur.setBounds(10, 181, 103, 14);
		panel.add(lblTur);

		chckbxNewCheckBox_4 = new JCheckBox("Kayitli Kurdan Cevir");
		chckbxNewCheckBox_4.setVisible(false);
		chckbxNewCheckBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox_4.isSelected())
				{
					lblCevrilecek.setVisible(false);
					lblTur.setVisible(false);
					comboBox_1.setVisible(false);
					comboBox_2.setVisible(false);
					DVZ_CEVIRME.lblcevrilen.setText("");
				}
				else 
				{
					lblCevrilecek.setVisible(true);
					lblTur.setVisible(true);
					comboBox_1.setVisible(true);
					comboBox_2.setVisible(true);
					DVZ_CEVIRME.lblcevrilen.setText(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()) + " / " + comboBox_2.getItemAt(comboBox_2.getSelectedIndex()));
				}
			}
		});
		chckbxNewCheckBox_4.setBounds(301, 140, 178, 23);
		panel.add(chckbxNewCheckBox_4);
		/// CARI ARAMA ****************************************************************************************************
		JPanel panel_1 = new JPanel();
		//panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Arama", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Hesap Kodu");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(10, 29, 103, 14);
		panel_1.add(lblNewLabel_3);

		textField = new Obs_TextFIeld(12);
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						textField.setText( oac.hsp_hsp_kodu);
					} catch (Exception ex) {}
				}
			}
		});
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(123, 26, 145, 20);

		panel_1.add(textField);

		JLabel lblIzahat = new JLabel("Izahat");
		lblIzahat.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblIzahat.setBounds(10, 57, 103, 14);
		panel_1.add(lblIzahat);

		textField_1 = new Obs_TextFIeld();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_1.setBounds(123, 54, 290, 20);
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
				{	
					contentPanel.setCursor(oac.WAIT_CURSOR);
					mWAIT();
					ARAMA.hisset();
					mDEFAULT();
					contentPanel.setCursor(oac.DEFAULT_CURSOR);
					//okButton.doClick();
				}
			}
		});
		textField_1.addAncestorListener(new AncestorListener() {
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
						textField_1.requestFocusInWindow();
					}
				});
			}
		});
		panel_1.add(textField_1);

		JLabel lblGun = new JLabel("Gun");
		lblGun.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGun.setBounds(10, 85, 103, 14);
		panel_1.add(lblGun);

		textField_2 = new Obs_TextFIeld();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_2.setBounds(123, 82, 49, 20);
		panel_1.add(textField_2);

		JLabel lblAy = new JLabel("Ay");
		lblAy.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAy.setBounds(10, 113, 103, 14);
		panel_1.add(lblAy);

		textField_3 = new Obs_TextFIeld();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_3.setBounds(123, 110, 49, 20);
		panel_1.add(textField_3);

		JLabel lblYil = new JLabel("Yil");
		lblYil.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblYil.setBounds(10, 141, 103, 14);
		panel_1.add(lblYil);

		textField_4 = new Obs_TextFIeld();
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_4.setBounds(123, 138, 71, 20);
		panel_1.add(textField_4);

		JLabel lblKod = new JLabel("Kod");
		lblKod.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblKod.setBounds(10, 169, 103, 14);
		panel_1.add(lblKod);

		textField_5 = new Obs_TextFIeld();
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_5.setBounds(123, 166, 71, 20);
		panel_1.add(textField_5);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Arama Kriteri", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
		panel_2.setBounds(327, 95, 290, 88);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		chcbaslayan = new JCheckBox("Ile Baslayan");
		chcbaslayan.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chcbaslayan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chcbaslayan.isSelected())
					chcile.setSelected(false);
				else
					chcile.setSelected(true);
			}
		});
		chcbaslayan.setSelected(true);
		chcbaslayan.setBounds(51, 27, 97, 23);
		panel_2.add(chcbaslayan);

		chcile = new JCheckBox("Icinde");
		chcile.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chcile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chcile.isSelected())
					chcbaslayan.setSelected(false);
				else
					chcbaslayan.setSelected(true);
			}
		});
		chcile.setBounds(51, 53, 97, 23);
		panel_2.add(chcile);

		textField_79 = new Obs_TextFIeld();
		textField_79.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_79.setBounds(123, 194, 145, 20);
		panel_1.add(textField_79);

		JLabel lblKullanici = new JLabel("Kullanici");
		lblKullanici.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblKullanici.setBounds(10, 196, 103, 14);
		panel_1.add(lblKullanici);

		//**************** GUNLUK ISLEM *************************************************************************
		panel_3 = new JPanel();
		//panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Gunluk_Islem", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblNewLabel_10 = new JLabel("Ilk Tarih");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10.setBounds(10, 32, 78, 14);
		panel_3.add(lblNewLabel_10);

		dateChooser_5 = new JDateChooser();
		dateChooser_5.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_5.setDate(new Date());
			}
		});
		dateChooser_5.setBounds(98, 26, 127, 20);
		dateChooser_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_5.setDateFormatString("dd.MM.yyyy");
		dateChooser_5.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		dateChooser_5.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					okButton.doClick();
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_5));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, -1); 
						dateChooser_5.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_5));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
						dateChooser_5.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel_3.add(dateChooser_5);

		JLabel lblNewLabel_10_1 = new JLabel("Son Tarih");
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10_1.setBounds(10, 71, 78, 14);
		panel_3.add(lblNewLabel_10_1);

		dateChooser_5_1 = new JDateChooser();
		dateChooser_5_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_5_1.setDate(new Date());
			}
		});
		dateChooser_5_1.setBounds(98, 65, 127, 20);

		dateChooser_5_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_5_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_5_1.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		dateChooser_5_1.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					okButton.doClick();
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_5_1));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, -1); 
						dateChooser_5_1.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_5_1));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
						dateChooser_5_1.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel_3.add(dateChooser_5_1);

		//******************************************CEK RAPOR **********************************************************
		JPanel panel_4 = new JPanel();
		//panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Cek_Rapor", null, panel_4, null);
		panel_4.setLayout(null);

		JLabel lblNewLabel_11 = new JLabel("Cek No");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_11.setBounds(10, 11, 46, 14);
		panel_4.add(lblNewLabel_11);

		txtcn1 = new Obs_TextFIeld(10);
		txtcn1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtcn1.setBounds(85, 8, 114, 20);
		panel_4.add(txtcn1);
		txtcn1.setColumns(10);

		txtcn2 = new Obs_TextFIeld(10);
		txtcn2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtcn2.setText("ZZZZZZZZZZ");
		txtcn2.setColumns(10);
		txtcn2.setBounds(219, 8, 114, 20);
		panel_4.add(txtcn2);

		JLabel lblNewLabel_12 = new JLabel("Vade");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_12.setBounds(10, 36, 46, 14);
		panel_4.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("Giris Bordro");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_13.setBounds(10, 62, 76, 14);
		panel_4.add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("Giris Tarih");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_14.setBounds(10, 86, 65, 14);
		panel_4.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("Cikis Bordro");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_15.setBounds(10, 111, 76, 14);
		panel_4.add(lblNewLabel_15);

		JLabel lblNewLabel_16 = new JLabel("Cikis Tarih");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_16.setBounds(10, 136, 76, 14);
		panel_4.add(lblNewLabel_16);

		JLabel lblNewLabel_17 = new JLabel("Giris Hesap");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_17.setBounds(10, 161, 76, 14);
		panel_4.add(lblNewLabel_17);

		dateChooser_6 = new JDateChooser();
		dateChooser_6.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_6.setDate(new Date());
			}
		});
		dateChooser_6.setBounds(85, 34, 114, 20);
		dateChooser_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_6.setDateFormatString("dd.MM.yyyy");
		dateChooser_6.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_4.add(dateChooser_6);

		dateChooser_6_1 = new JDateChooser();
		dateChooser_6_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_6_1.setDate(new Date());
			}
		});
		dateChooser_6_1.setBounds(219, 34, 114, 20);
		dateChooser_6_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_6_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_6_1.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel_4.add(dateChooser_6_1);

		txtgb1 = new Obs_TextFIeld(10);
		txtgb1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtgb1.setBounds(85, 58, 114, 20);
		panel_4.add(txtgb1);
		txtgb1.setColumns(10);

		txtgb2 = new Obs_TextFIeld(10);
		txtgb2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtgb2.setText("ZZZZZZZZZZ");
		txtgb2.setBounds(219, 58, 114, 20);
		panel_4.add(txtgb2);
		txtgb2.setColumns(10);

		dateChooser_7 = new JDateChooser();
		dateChooser_7.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_7.setDate(new Date());
			}
		});
		dateChooser_7.setBounds(85, 82, 114, 20);
		dateChooser_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_7.setDateFormatString("dd.MM.yyyy");
		dateChooser_7.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_4.add(dateChooser_7);

		dateChooser_8 = new JDateChooser();
		dateChooser_8.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_8.setDate(new Date());
			}
		});
		dateChooser_8.setBounds(219, 82, 114, 20);
		dateChooser_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_8.setDateFormatString("dd.MM.yyyy");
		dateChooser_8.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel_4.add(dateChooser_8);

		txtcb1 = new Obs_TextFIeld(10);
		txtcb1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtcb1.setBounds(85, 108, 114, 20);
		panel_4.add(txtcb1);
		txtcb1.setColumns(10);

		txtcb2 = new Obs_TextFIeld(10);
		txtcb2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtcb2.setText("ZZZZZZZZZZ");
		txtcb2.setBounds(219, 108, 114, 20);
		panel_4.add(txtcb2);
		txtcb2.setColumns(10);

		dateChooser_9 = new JDateChooser();
		dateChooser_9.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_9.setDate(new Date());
			}
		});
		dateChooser_9.setBounds(85, 132, 114, 20);
		dateChooser_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_9.setDateFormatString("dd.MM.yyyy");
		dateChooser_9.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_4.add(dateChooser_9);

		dateChooser_10 = new JDateChooser();
		dateChooser_10.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_10.setDate(new Date());
			}
		});
		dateChooser_10.setBounds(219, 132, 114, 20);
		dateChooser_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_10.setDateFormatString("dd.MM.yyyy");
		dateChooser_10.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel_4.add(dateChooser_10);

		txtgh1 = new Obs_TextFIeld(12);
		txtgh1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtgh1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						txtgh1.setText( oac.hsp_hsp_kodu);
					} catch (Exception ex) {}
				}
			}
		});
		txtgh1.setText("");
		txtgh1.setBounds(85, 158, 114, 20);
		panel_4.add(txtgh1);
		txtgh1.setColumns(10);

		txtgh2 = new Obs_TextFIeld(12);
		txtgh2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtgh2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						txtgh2.setText( oac.hsp_hsp_kodu);
					} catch (Exception ex) {}
				}
			}
		});
		txtgh2.setText("ZZZZZZZZZZZZ");
		txtgh2.setBounds(219, 158, 114, 20);
		panel_4.add(txtgh2);
		txtgh2.setColumns(10);

		JLabel lblNewLabel_18 = new JLabel("Durum");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_18.setBounds(375, 11, 46, 14);
		panel_4.add(lblNewLabel_18);

		JLabel lblNewLabel_19 = new JLabel("T.Tarih");
		lblNewLabel_19.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_19.setBounds(375, 36, 46, 14);
		panel_4.add(lblNewLabel_19);

		txtd1 = new Obs_TextFIeld();
		txtd1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtd1.setBounds(444, 8, 114, 20);
		panel_4.add(txtd1);
		txtd1.setColumns(10);

		dateChooser_6_2 = new JDateChooser();
		dateChooser_6_2.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_6_2.setDate(new Date());
			}
		});
		dateChooser_6_2.setBounds(444, 34, 114, 20);
		dateChooser_6_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_6_2.setDateFormatString("dd.MM.yyyy");
		dateChooser_6_2.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_4.add(dateChooser_6_2);

		txtd2 = new Obs_TextFIeld();
		txtd2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtd2.setText("Z");
		txtd2.setColumns(10);
		txtd2.setBounds(585, 8, 114, 20);
		panel_4.add(txtd2);

		dateChooser_6_2_1 = new JDateChooser();
		dateChooser_6_2_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_6_2_1.setDate(new Date());
			}
		});
		dateChooser_6_2_1.setBounds(585, 34, 114, 20);
		dateChooser_6_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_6_2_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_6_2_1.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel_4.add(dateChooser_6_2_1);

		JLabel lblNewLabel_20 = new JLabel("Giris Ozel");
		lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_20.setBounds(375, 64, 65, 14);
		panel_4.add(lblNewLabel_20);

		cmbg = new JComboBox<String>();
		//cmbg.setForeground(new Color(0, 0, 128));
		cmbg.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbg.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Bos"}));
		cmbg.setEditable(true);
		cmbg.setBounds(443, 58, 256, 22);
		panel_4.add(cmbg);

		JLabel lblNewLabel_20_1 = new JLabel("Cikis Ozel");
		lblNewLabel_20_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_20_1.setBounds(375, 92, 65, 14);
		panel_4.add(lblNewLabel_20_1);

		cmbc = new JComboBox<String>();
		//cmbc.setForeground(new Color(0, 0, 128));
		cmbc.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbc.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Bos"}));
		cmbc.setEditable(true);
		cmbc.setBounds(443, 86, 256, 22);
		panel_4.add(cmbc);

		JLabel lblNewLabel_17_1 = new JLabel("Cins");
		lblNewLabel_17_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_17_1.setBounds(376, 136, 76, 14);
		panel_4.add(lblNewLabel_17_1);

		txtc1 = new Obs_TextFIeld();
		txtc1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtc1.setText("");
		txtc1.setColumns(10);
		txtc1.setBounds(444, 133, 114, 20);
		panel_4.add(txtc1);

		txtc2 = new Obs_TextFIeld();
		txtc2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtc2.setText("ZZZ");
		txtc2.setColumns(10);
		txtc2.setBounds(585, 133, 114, 20);
		panel_4.add(txtc2);

		JLabel lblNewLabel_17_2 = new JLabel("Cikis Hesap");
		lblNewLabel_17_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_17_2.setBounds(375, 161, 76, 14);
		panel_4.add(lblNewLabel_17_2);

		txtch1 = new Obs_TextFIeld(12);
		txtch1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtch1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						txtch1.setText( oac.hsp_hsp_kodu);
					} catch (Exception ex) {}
				}
			}
		});
		txtch1.setText("");
		txtch1.setColumns(10);
		txtch1.setBounds(444, 158, 114, 20);
		panel_4.add(txtch1);

		txtch2 = new Obs_TextFIeld(12);
		txtch2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtch2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						txtch2.setText( oac.hsp_hsp_kodu);
					} catch (Exception ex) {}
				}
			}
		});
		txtch2.setText("ZZZZZZZZZZZZ");
		txtch2.setColumns(10);
		txtch2.setBounds(584, 158, 114, 20);
		panel_4.add(txtch2);

		JPanel panel_5 = new JPanel();
		//panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Kur_Rapor", null, panel_5, null);
		panel_5.setLayout(null);

		JLabel lblNewLabel_21 = new JLabel("Tarih");
		lblNewLabel_21.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_21.setBounds(10, 26, 46, 14);
		panel_5.add(lblNewLabel_21);

		dateChooser_11 = new JDateChooser();
		dateChooser_11.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_11.setDate(new Date());
			}
		});
		dateChooser_11.setBounds(76, 20, 120, 20);
		dateChooser_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_11.setDateFormatString("dd.MM.yyyy");
		dateChooser_11.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_5.add(dateChooser_11);

		dateChooser_11_1 = new JDateChooser();
		dateChooser_11_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_11_1.setDate(new Date());
			}
		});
		dateChooser_11_1.setBounds(235, 20, 120, 20);
		dateChooser_11_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_11_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_11_1.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel_5.add(dateChooser_11_1);

		JLabel lblNewLabel_22 = new JLabel("Cins");
		lblNewLabel_22.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_22.setBounds(10, 62, 46, 14);
		panel_5.add(lblNewLabel_22);

		txtkc1 = new Obs_TextFIeld();
		txtkc1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtkc1.setBounds(76, 59, 63, 20);
		txtkc1.addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorRemoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorMoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorAdded(AncestorEvent pEvent) {
				// TextField is added to its parent => request focus in Event Dispatch Thread
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						txtkc1.requestFocusInWindow();
					}
				});
			}
		});
		panel_5.add(txtkc1);
		txtkc1.setColumns(10);

		txtkc2 = new Obs_TextFIeld();
		txtkc2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtkc2.setText("ZZZ");
		txtkc2.setColumns(10);
		txtkc2.setBounds(235, 59, 63, 20);
		panel_5.add(txtkc2);

		panel_6 = new JPanel();
		//panel_6.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Fatura", null, panel_6, null);
		panel_6.setLayout(null);

		JLabel lblNewLabel_23 = new JLabel("Fatura No");
		lblNewLabel_23.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_23.setBounds(10, 11, 69, 14);
		panel_6.add(lblNewLabel_23);

		textField_6 = new Obs_TextFIeld();
		textField_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_6.setBounds(79, 8, 104, 20);
		panel_6.add(textField_6);
		textField_6.setColumns(10);

		JLabel lblNewLabel_24 = new JLabel("Tarih");
		lblNewLabel_24.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_24.setBounds(10, 39, 58, 14);
		panel_6.add(lblNewLabel_24);

		dateChooser_12 = new JDateChooser();
		dateChooser_12.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_12.setDate(new Date());
			}
		});
		dateChooser_12.setBounds(79, 33, 125, 20);
		dateChooser_12.setDateFormatString("dd.MM.yyyy");
		dateChooser_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_12.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_6.add(dateChooser_12);

		JLabel lblNewLabel_25 = new JLabel("Cari Kodu");
		lblNewLabel_25.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_25.setBounds(10, 64, 58, 14);
		panel_6.add(lblNewLabel_25);

		textField_7 = new Obs_TextFIeld();
		textField_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_7.setBounds(79, 58, 125, 20);
		panel_6.add(textField_7);
		textField_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						textField_7.setText( oac.hsp_hsp_kodu);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		textField_7.setColumns(10);

		JLabel lblNewLabel_26 = new JLabel("Adres Kodu");
		lblNewLabel_26.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_26.setBounds(10, 89, 69, 14);
		panel_6.add(lblNewLabel_26);

		textField_8 = new Obs_TextFIeld();
		textField_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_8.setBounds(79, 83, 125, 20);
		textField_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					ADRES_LISTE asp ;
					asp = new ADRES_LISTE();
					asp.setVisible(true);
					textField_8.setText( oac.hsp_hsp_kodu);
				}
			}
		});
		panel_6.add(textField_8);
		textField_8.setColumns(10);

		JLabel lblNewLabel_27 = new JLabel("Tevkifat");
		lblNewLabel_27.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_27.setBounds(10, 139, 69, 14);
		panel_6.add(lblNewLabel_27);

		textField_9 = new Obs_TextFIeld();
		textField_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_9.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_9.setText("0");
		textField_9.setBounds(79, 133, 46, 20);
		panel_6.add(textField_9);
		textField_9.setColumns(10);

		JLabel lblNewLabel_28 = new JLabel("Ozel Kod");
		lblNewLabel_28.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_28.setBounds(10, 164, 69, 14);
		panel_6.add(lblNewLabel_28);

		textField_10 = new Obs_TextFIeld();
		textField_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_10.setBounds(79, 158, 86, 20);
		panel_6.add(textField_10);
		textField_10.setColumns(10);

		JLabel lblNewLabel_29 = new JLabel("Doviz");
		lblNewLabel_29.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_29.setBounds(10, 189, 46, 14);
		panel_6.add(lblNewLabel_29);

		textField_11 = new Obs_TextFIeld();
		textField_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_11.setBounds(79, 183, 86, 20);
		panel_6.add(textField_11);
		textField_11.setColumns(10);

		txtZzzzzzzzzz = new Obs_TextFIeld();
		txtZzzzzzzzzz.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtZzzzzzzzzz.setText("ZZZZZZZZZZ");
		txtZzzzzzzzzz.setColumns(10);
		txtZzzzzzzzzz.setBounds(248, 8, 104, 20);
		panel_6.add(txtZzzzzzzzzz);

		dateChooser_13 = new JDateChooser();
		dateChooser_13.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_13.setDate(new Date());
			}
		});
		dateChooser_13.setBounds(248, 33, 125, 20);
		dateChooser_13.setDateFormatString("dd.MM.yyyy");
		dateChooser_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_13.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel_6.add(dateChooser_13);

		txtZzzzzzzzzzzz = new Obs_TextFIeld();
		txtZzzzzzzzzzzz.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtZzzzzzzzzzzz.setText("ZZZZZZZZZZZZ");
		txtZzzzzzzzzzzz.setColumns(10);
		txtZzzzzzzzzzzz.setBounds(248, 58, 125, 20);
		txtZzzzzzzzzzzz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						txtZzzzzzzzzzzz.setText( oac.hsp_hsp_kodu);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel_6.add(txtZzzzzzzzzzzz);

		txtZzzzzzzzzzzz_1 = new Obs_TextFIeld();
		txtZzzzzzzzzzzz_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					ADRES_LISTE asp ;
					asp = new ADRES_LISTE();
					asp.setVisible(true);
					txtZzzzzzzzzzzz_1.setText( oac.hsp_hsp_kodu);
				}
			}
		});
		txtZzzzzzzzzzzz_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtZzzzzzzzzzzz_1.setText("ZZZZZZZZZZZZ");
		txtZzzzzzzzzzzz_1.setColumns(10);
		txtZzzzzzzzzzzz_1.setBounds(248, 83, 125, 20);
		panel_6.add(txtZzzzzzzzzzzz_1);

		textField_15 = new Obs_TextFIeld();
		textField_15.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_15.setText("99");
		textField_15.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_15.setColumns(10);
		textField_15.setBounds(248, 133, 46, 20);
		panel_6.add(textField_15);

		txtZzzzzzzzzz_1 = new Obs_TextFIeld();
		txtZzzzzzzzzz_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtZzzzzzzzzz_1.setText("ZZZZZZZZZZ");
		txtZzzzzzzzzz_1.setColumns(10);
		txtZzzzzzzzzz_1.setBounds(248, 158, 86, 20);
		panel_6.add(txtZzzzzzzzzz_1);

		txtZzz = new Obs_TextFIeld();
		txtZzz.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtZzz.setText("ZZZ");
		txtZzz.setColumns(10);
		txtZzz.setBounds(248, 183, 86, 20);
		panel_6.add(txtZzz);

		JLabel lblNewLabel_30 = new JLabel("Ana Grup");
		lblNewLabel_30.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_30.setBounds(459, 11, 69, 14);
		panel_6.add(lblNewLabel_30);

		JLabel lblNewLabel_31 = new JLabel("Alt Grup");
		lblNewLabel_31.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_31.setBounds(459, 39, 69, 14);
		panel_6.add(lblNewLabel_31);

		comboBox_3 = new JComboBox<String>();
		//comboBox_3.setForeground(new Color(0, 0, 128));
		comboBox_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_4,comboBox_3 .getItemAt(comboBox_3 .getSelectedIndex()));
			}
		});
		comboBox_3.setBounds(566, 7, 165, 22);
		panel_6.add(comboBox_3);

		comboBox_4 = new JComboBox<String>();
		//comboBox_4.setForeground(new Color(0, 0, 128));
		comboBox_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_4.setEnabled(false);

		comboBox_4.setBounds(566, 35, 165, 22);
		panel_6.add(comboBox_4);

		comboBox_5 = new JComboBox<String>();
		//comboBox_5.setForeground(new Color(0, 0, 128));
		comboBox_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_5.setBounds(566, 63, 165, 22);
		panel_6.add(comboBox_5);

		comboBox_6 = new JComboBox<String>();
		//comboBox_6.setForeground(new Color(0, 0, 128));
		comboBox_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_6.setModel(new DefaultComboBoxModel<String>(new String[] {"", "GIREN", "CIKAN"}));
		comboBox_6.setBounds(566, 91, 165, 22);
		panel_6.add(comboBox_6);

		JLabel lblNewLabel_32 = new JLabel("Depo");
		lblNewLabel_32.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_32.setBounds(459, 67, 46, 14);
		panel_6.add(lblNewLabel_32);

		JLabel lblNewLabel_33 = new JLabel("Turu");
		lblNewLabel_33.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_33.setBounds(459, 95, 46, 14);
		panel_6.add(lblNewLabel_33);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Gruplama", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
		panel_7.setBounds(459, 133, 272, 70);
		panel_6.add(panel_7);
		panel_7.setLayout(null);

		comboBox_7 = new JComboBox<String>();
		//comboBox_7.setForeground(new Color(0, 0, 128));
		comboBox_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_7.setModel(new DefaultComboBoxModel<String>(new String[] {"Fatura No", "Firma Kodu", "Fatura_No_Tarih"}));
		comboBox_7.setBounds(107, 11, 149, 22);
		panel_7.add(comboBox_7);

		comboBox_8 = new JComboBox<String>();
		//comboBox_8.setForeground(new Color(0, 0, 128));
		comboBox_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_8.setModel(new DefaultComboBoxModel<String>(new String[] {"Cari_Firma", "Adres_Firma"}));
		comboBox_8.setBounds(107, 37, 149, 22);
		panel_7.add(comboBox_8);

		lblUrunKodu = new JLabel("Urun Kodu");
		lblUrunKodu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUrunKodu.setBounds(10, 114, 69, 14);
		panel_6.add(lblUrunKodu);

		textField_12 = new Obs_TextFIeld();
		textField_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					URUN_ARAMA arm ;
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
					arm = new URUN_ARAMA();
					arm.setVisible(true);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
					textField_12.setText(oac.stk_kodu);	
				}
			}
		});

		textField_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_12.setColumns(10);
		textField_12.setBounds(79, 108, 125, 20);
		panel_6.add(textField_12);

		textField_13 = new Obs_TextFIeld();
		textField_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_13.setText("ZZZZZZZZZZZZ");
		textField_13.setColumns(10);
		textField_13.setBounds(248, 108, 125, 20);
		textField_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					URUN_ARAMA arm ;
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
					arm = new URUN_ARAMA();
					arm.setVisible(true);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
					textField_13.setText(oac.stk_kodu);	
				}
			}
		});

		panel_6.add(textField_13);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		//panel_8.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Imalat_rapor", null, panel_8, null);

		JLabel lblEvrakNo = new JLabel("Evrak No");
		lblEvrakNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEvrakNo.setBounds(10, 11, 69, 14);
		panel_8.add(lblEvrakNo);

		textField_14 = new Obs_TextFIeld();
		textField_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_14.setColumns(10);
		textField_14.setBounds(90, 8, 104, 20);
		panel_8.add(textField_14);

		JLabel label_1 = new JLabel("Tarih");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(10, 39, 58, 14);
		panel_8.add(label_1);

		dateChooser_14 = new JDateChooser();
		dateChooser_14.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_14.setDate(new Date());
			}
		});
		dateChooser_14.setBounds(90, 33, 125, 20);
		dateChooser_14.setDateFormatString("dd.MM.yyyy");
		dateChooser_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_14.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_8.add(dateChooser_14);

		JLabel lblBarkod = new JLabel("Barkod");
		lblBarkod.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBarkod.setBounds(10, 64, 58, 14);
		panel_8.add(lblBarkod);

		textField_16 = new Obs_TextFIeld();
		textField_16.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_16.setColumns(10);
		textField_16.setBounds(90, 58, 125, 20);
		panel_8.add(textField_16);

		JLabel lblUrunKodu_1 = new JLabel("Urun Kodu");
		lblUrunKodu_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUrunKodu_1.setBounds(10, 89, 69, 14);
		panel_8.add(lblUrunKodu_1);

		textField_17 = new Obs_TextFIeld();
		textField_17.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_17.setColumns(10);
		textField_17.setBounds(90, 83, 125, 20);
		panel_8.add(textField_17);

		textField_21 = new Obs_TextFIeld();
		textField_21.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_21.setText("ZZZZZZZZZZ");
		textField_21.setColumns(10);
		textField_21.setBounds(248, 8, 104, 20);
		panel_8.add(textField_21);

		dateChooser_15 = new JDateChooser();
		dateChooser_15.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_15.setDate(new Date());
			}
		});
		dateChooser_15.setBounds(248, 33, 125, 20);
		dateChooser_15.setDateFormatString("dd.MM.yyyy");
		dateChooser_15.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_15.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel_8.add(dateChooser_15);

		textField_22 = new Obs_TextFIeld();
		textField_22.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_22.setText("ZZZZZZZZZZZZ");
		textField_22.setColumns(10);
		textField_22.setBounds(248, 58, 125, 20);
		panel_8.add(textField_22);

		textField_23 = new Obs_TextFIeld();
		textField_23.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_23.setText("ZZZZZZZZZZZZ");
		textField_23.setColumns(10);
		textField_23.setBounds(248, 83, 125, 20);
		panel_8.add(textField_23);

		JLabel lblUrunanaGrup = new JLabel("Urun Ana Grup");
		lblUrunanaGrup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUrunanaGrup.setBounds(459, 11, 104, 14);
		panel_8.add(lblUrunanaGrup);

		JLabel lblUrunAltGrup = new JLabel("Urun Alt Grup");
		lblUrunAltGrup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUrunAltGrup.setBounds(459, 39, 104, 14);
		panel_8.add(lblUrunAltGrup);

		comboBox_9 = new JComboBox<String>();
		//comboBox_9.setForeground(new Color(0, 0, 128));
		comboBox_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_10,comboBox_9 .getItemAt(comboBox_9 .getSelectedIndex()));
			}
		});
		comboBox_9.setBounds(566, 7, 149, 22);
		panel_8.add(comboBox_9);

		comboBox_10 = new JComboBox<String>();
		//comboBox_10.setForeground(new Color(0, 0, 128));
		comboBox_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_10.setEnabled(false);
		comboBox_10.setBounds(566, 35, 149, 22);
		panel_8.add(comboBox_10);

		comboBox_11 = new JComboBox<String>();
		//comboBox_11.setForeground(new Color(0, 0, 128));
		comboBox_11.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_11.setBounds(566, 81, 149, 22);
		panel_8.add(comboBox_11);

		JLabel label_9 = new JLabel("Depo");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_9.setBounds(459, 85, 46, 14);
		panel_8.add(label_9);

		JLabel lblRecete = new JLabel("Recete");
		lblRecete.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRecete.setBounds(10, 114, 69, 14);
		panel_8.add(lblRecete);

		textField_27 = new Obs_TextFIeld();
		textField_27.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_27.setColumns(10);
		textField_27.setBounds(90, 108, 125, 20);
		panel_8.add(textField_27);

		textField_28 = new Obs_TextFIeld();
		textField_28.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_28.setText("ZZZZZZZZZZZZ");
		textField_28.setColumns(10);
		textField_28.setBounds(248, 108, 125, 20);
		panel_8.add(textField_28);

		JLabel label = new JLabel("Ana Grup");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(10, 143, 58, 14);
		panel_8.add(label);

		comboBox_12 = new JComboBox<String>();
		//comboBox_12.setForeground(new Color(0, 0, 128));
		comboBox_12.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_13,comboBox_12 .getItemAt(comboBox_12 .getSelectedIndex()));
			}
		});
		comboBox_12.setBounds(90, 139, 174, 22);
		panel_8.add(comboBox_12);

		JLabel label_2 = new JLabel("Alt Grup");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(10, 172, 69, 14);
		panel_8.add(label_2);

		comboBox_13 = new JComboBox<String>();
		//comboBox_13.setForeground(new Color(0, 0, 128));
		comboBox_13.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_13.setEnabled(false);
		comboBox_13.setBounds(90, 168, 174, 22);
		panel_8.add(comboBox_13);

		JPanel panel_9 = new JPanel();
		//panel_9.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Stok_Rapor", null, panel_9, null);
		panel_9.setLayout(null);

		JLabel label_3 = new JLabel("Tarih");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(10, 17, 58, 14);
		panel_9.add(label_3);

		dateChooser_16 = new JDateChooser();
		dateChooser_16.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_16.setDate(new Date());
			}
		});
		dateChooser_16.setBounds(79, 11, 125, 20);
		dateChooser_16.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_16.setDateFormatString("dd.MM.yyyy");
		dateChooser_16.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_9.add(dateChooser_16);

		dateChooser_17 = new JDateChooser();
		dateChooser_17.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_17.setDate(new Date());
			}
		});
		dateChooser_17.setBounds(248, 11, 125, 20);
		dateChooser_17.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_17.setDateFormatString("dd.MM.yyyy");
		dateChooser_17.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel_9.add(dateChooser_17);

		JLabel lblUrunKodu_2 = new JLabel("Urun Kodu");
		lblUrunKodu_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUrunKodu_2.setBounds(10, 42, 69, 14);
		panel_9.add(lblUrunKodu_2);

		JLabel lblEvrakNo_1 = new JLabel("Evrak No");
		lblEvrakNo_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEvrakNo_1.setBounds(10, 67, 69, 14);
		panel_9.add(lblEvrakNo_1);

		textField_18 = new Obs_TextFIeld();
		textField_18.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_18.setColumns(10);
		textField_18.setBounds(79, 61, 125, 20);
		panel_9.add(textField_18);

		textField_19 = new Obs_TextFIeld();
		textField_19.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_19.setColumns(10);
		textField_19.setBounds(79, 36, 125, 20);
		panel_9.add(textField_19);

		textField_20 = new Obs_TextFIeld();
		textField_20.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_20.setText("ZZZZZZZZZZZZ");
		textField_20.setColumns(10);
		textField_20.setBounds(248, 36, 125, 20);
		panel_9.add(textField_20);

		textField_24 = new Obs_TextFIeld();
		textField_24.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_24.setText("ZZZZZZZZZZZZ");
		textField_24.setColumns(10);
		textField_24.setBounds(248, 61, 125, 20);
		panel_9.add(textField_24);

		JLabel label_4 = new JLabel("Ana Grup");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(10, 96, 58, 14);
		panel_9.add(label_4);

		comboBox_14 = new JComboBox<String>();
		//comboBox_14.setForeground(new Color(0, 0, 128));
		comboBox_14.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_15,comboBox_14 .getItemAt(comboBox_14 .getSelectedIndex()));
			}
		});
		comboBox_14.setBounds(79, 92, 174, 22);
		panel_9.add(comboBox_14);

		JLabel label_5 = new JLabel("Alt Grup");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setBounds(10, 125, 69, 14);
		panel_9.add(label_5);

		comboBox_15 = new JComboBox<String>();
		//comboBox_15.setForeground(new Color(0, 0, 128));
		comboBox_15.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_15.setEnabled(false);
		comboBox_15.setBounds(79, 121, 174, 22);
		panel_9.add(comboBox_15);

		JLabel label_6 = new JLabel("Depo");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(10, 154, 46, 14);
		panel_9.add(label_6);

		comboBox_16 = new JComboBox<String>();
		//comboBox_16.setForeground(new Color(0, 0, 128));
		comboBox_16.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_16.setBounds(79, 150, 174, 22);
		panel_9.add(comboBox_16);

		JLabel label_7 = new JLabel("Urun Ana Grup");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(464, 10, 104, 14);
		panel_9.add(label_7);

		comboBox_17 = new JComboBox<String>();
		//comboBox_17.setForeground(new Color(0, 0, 128));
		comboBox_17.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_18,comboBox_17 .getItemAt(comboBox_17 .getSelectedIndex()));
			}
		});
		comboBox_17.setBounds(571, 6, 149, 22);
		panel_9.add(comboBox_17);

		JLabel label_8 = new JLabel("Urun Alt Grup");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_8.setBounds(464, 38, 104, 14);
		panel_9.add(label_8);

		comboBox_18 = new JComboBox<String>();
		//comboBox_18.setForeground(new Color(0, 0, 128));
		comboBox_18.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_18.setEnabled(false);
		comboBox_18.setBounds(571, 34, 149, 22);
		panel_9.add(comboBox_18);

		JPanel panel_10 = new JPanel();
		//panel_10.setBorder(new TitledBorder(null, "Gruplama", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Gruplama", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setBounds(484, 100, 256, 68);
		panel_9.add(panel_10);
		panel_10.setLayout(null);

		comboBox_19 = new JComboBox<String>();
		comboBox_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(	comboBox_19.getItemAt(comboBox_19.getSelectedIndex()).toString().equals("Urun Kodu"))
					chckbxNewCheckBox_2.setEnabled(true);
				else
				{
					chckbxNewCheckBox_2.setEnabled(false);
					chckbxNewCheckBox_2.setSelected(false);
				}
			}
		});
		//comboBox_19.setForeground(new Color(0, 0, 128));
		comboBox_19.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_19.setModel(new DefaultComboBoxModel<String>(new String[] {"Urun Kodu", "Ana_Grup_Alt_Grup"}));
		comboBox_19.setBounds(40, 25, 195, 22);
		panel_10.add(comboBox_19);

		chckbxNewCheckBox = new JCheckBox("Depo Hareketleri Dahil");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxNewCheckBox.setBounds(75, 179, 174, 23);
		panel_9.add(chckbxNewCheckBox);

		chckbxNewCheckBox_1 = new JCheckBox("Uretim Fisleri Dahil");
		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxNewCheckBox_1.setSelected(true);
		chckbxNewCheckBox_1.setBounds(75, 202, 194, 23);
		panel_9.add(chckbxNewCheckBox_1);

		chckbxNewCheckBox_2 = new JCheckBox("Onceki Tarih Bakiyeli");
		chckbxNewCheckBox_2.setBounds(484, 179, 175, 23);
		chckbxNewCheckBox_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_9.add(chckbxNewCheckBox_2);

		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		//panel_11.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Stok_Detay", null, panel_11, null);

		JLabel label_10 = new JLabel("Tarih");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_10.setBounds(10, 17, 58, 14);
		panel_11.add(label_10);

		dateChooser_18 = new JDateChooser();
		dateChooser_18.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_18.setDate(new Date());
			}
		});
		dateChooser_18.setBounds(79, 11, 125, 20);
		dateChooser_18.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_18.setDateFormatString("dd.MM.yyyy");
		dateChooser_18.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_11.add(dateChooser_18);

		dateChooser_19 = new JDateChooser();
		dateChooser_19.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_19.setDate(new Date());
			}
		});
		dateChooser_19.setBounds(248, 11, 125, 20);
		dateChooser_19.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_19.setDateFormatString("dd.MM.yyyy");
		dateChooser_19.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel_11.add(dateChooser_19);

		JLabel label_11 = new JLabel("Urun Kodu");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_11.setBounds(10, 42, 69, 14);
		panel_11.add(label_11);

		JLabel label_12 = new JLabel("Evrak No");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_12.setBounds(10, 67, 69, 14);
		panel_11.add(label_12);

		textField_25 = new Obs_TextFIeld();
		textField_25.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_25.setColumns(10);
		textField_25.setBounds(79, 61, 125, 20);
		panel_11.add(textField_25);

		textField_26 = new Obs_TextFIeld();
		textField_26.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_26.setColumns(10);
		textField_26.setBounds(79, 36, 125, 20);
		panel_11.add(textField_26);

		textField_29 = new Obs_TextFIeld();
		textField_29.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_29.setText("ZZZZZZZZZZZZ");
		textField_29.setColumns(10);
		textField_29.setBounds(248, 36, 125, 20);
		panel_11.add(textField_29);

		textField_30 = new Obs_TextFIeld();
		textField_30.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_30.setText("ZZZZZZZZZZZZ");
		textField_30.setColumns(10);
		textField_30.setBounds(248, 61, 125, 20);
		panel_11.add(textField_30);

		JLabel label_13 = new JLabel("Ana Grup");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_13.setBounds(10, 121, 58, 14);
		panel_11.add(label_13);

		comboBox_20 = new JComboBox<String>();
		//comboBox_20.setForeground(new Color(0, 0, 128));
		comboBox_20.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_21,comboBox_20 .getItemAt(comboBox_20 .getSelectedIndex()));
			}
		});
		comboBox_20.setBounds(79, 117, 150, 22);
		panel_11.add(comboBox_20);

		JLabel label_14 = new JLabel("Alt Grup");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_14.setBounds(248, 121, 69, 14);
		panel_11.add(label_14);

		comboBox_21 = new JComboBox<String>();
		//comboBox_21.setForeground(new Color(0, 0, 128));
		comboBox_21.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_21.setEnabled(false);
		comboBox_21.setBounds(317, 117, 150, 22);
		panel_11.add(comboBox_21);

		JLabel label_15 = new JLabel("Depo");
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_15.setBounds(10, 154, 46, 14);
		panel_11.add(label_15);

		comboBox_22 = new JComboBox<String>();
		//comboBox_22.setForeground(new Color(0, 0, 128));
		comboBox_22.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_22.setBounds(79, 150, 150, 22);
		panel_11.add(comboBox_22);

		JLabel label_16 = new JLabel("Urun Ana Grup");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_16.setBounds(464, 10, 104, 14);
		panel_11.add(label_16);

		comboBox_23 = new JComboBox<String>();
		//comboBox_23.setForeground(new Color(0, 0, 128));
		comboBox_23.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_24,comboBox_23 .getItemAt(comboBox_23 .getSelectedIndex()));
			}
		});
		comboBox_23.setBounds(571, 6, 149, 22);
		panel_11.add(comboBox_23);

		JLabel label_17 = new JLabel("Urun Alt Grup");
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_17.setBounds(464, 38, 104, 14);
		panel_11.add(label_17);

		comboBox_24 = new JComboBox<String>();
		//comboBox_24.setForeground(new Color(0, 0, 128));
		comboBox_24.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_24.setEnabled(false);
		comboBox_24.setBounds(571, 34, 149, 22);
		panel_11.add(comboBox_24);

		checkBox = new JCheckBox("Depo Hareketleri Dahil");
		checkBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkBox.setBounds(75, 179, 174, 23);
		panel_11.add(checkBox);

		checkBox_1 = new JCheckBox("Uretim Fisleri Dahil");
		checkBox_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkBox_1.setSelected(true);
		checkBox_1.setBounds(75, 202, 194, 23);
		panel_11.add(checkBox_1);

		JLabel lblNewLabel_34 = new JLabel("Turu");
		lblNewLabel_34.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_34.setBounds(464, 96, 46, 14);
		panel_11.add(lblNewLabel_34);

		comboBox_25 = new JComboBox<String>();
		//comboBox_25.setForeground(new Color(0, 0, 128));
		comboBox_25.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_25.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Giren", "Cikan"}));
		comboBox_25.setBounds(571, 92, 149, 22);
		panel_11.add(comboBox_25);

		JLabel label_11_1 = new JLabel("Hesap Kodu");
		label_11_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_11_1.setBounds(10, 92, 69, 14);
		panel_11.add(label_11_1);

		textField_80 = new Obs_TextFIeld(12);
		textField_80.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_80.setColumns(10);
		textField_80.setBounds(79, 89, 125, 20);
		panel_11.add(textField_80);

		textField_81 = new Obs_TextFIeld(12);
		textField_81.setText("ZZZZZZZZZZZZ");
		textField_81.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_81.setColumns(10);
		textField_81.setBounds(248, 89, 125, 20);
		panel_11.add(textField_81);

		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		//panel_12.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Grup_Rapor", null, panel_12, null);

		JLabel label_18 = new JLabel("Tarih");
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_18.setBounds(10, 17, 58, 14);
		panel_12.add(label_18);

		dateChooser_20 = new JDateChooser();
		dateChooser_20.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_20.setDate(new Date());
			}
		});
		dateChooser_20.setBounds(90, 11, 125, 20);
		dateChooser_20.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_20.setDateFormatString("dd.MM.yyyy");
		dateChooser_20.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_12.add(dateChooser_20);

		dateChooser_21 = new JDateChooser();
		dateChooser_21.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_21.setDate(new Date());
			}
		});
		dateChooser_21.setBounds(248, 11, 125, 20);
		dateChooser_21.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_21.setDateFormatString("dd.MM.yyyy");
		dateChooser_21.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel_12.add(dateChooser_21);

		JLabel label_19 = new JLabel("Urun Kodu");
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_19.setBounds(10, 42, 69, 14);
		panel_12.add(label_19);

		JLabel lblHesapKodu = new JLabel("Hesap Kodu");
		lblHesapKodu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHesapKodu.setBounds(10, 67, 82, 14);
		panel_12.add(lblHesapKodu);

		textField_31 = new Obs_TextFIeld();
		textField_31.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_31.setColumns(10);
		textField_31.setBounds(90, 61, 125, 20);
		textField_31.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						textField_31.setText( oac.hsp_hsp_kodu);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel_12.add(textField_31);

		textField_32 = new Obs_TextFIeld();
		textField_32.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_32.setColumns(10);
		textField_32.setBounds(90, 36, 125, 20);
		panel_12.add(textField_32);

		textField_33 = new Obs_TextFIeld();
		textField_33.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_33.setText("ZZZZZZZZZZZZ");
		textField_33.setColumns(10);
		textField_33.setBounds(248, 36, 125, 20);
		panel_12.add(textField_33);

		textField_34 = new Obs_TextFIeld();
		textField_34.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_34.setText("ZZZZZZZZZZZZ");
		textField_34.setColumns(10);
		textField_34.setBounds(248, 61, 125, 20);
		textField_34.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						textField_34.setText( oac.hsp_hsp_kodu);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel_12.add(textField_34);

		JLabel label_24 = new JLabel("Urun Ana Grup");
		label_24.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_24.setBounds(401, 15, 104, 14);
		panel_12.add(label_24);

		comboBox_29 = new JComboBox<String>();
		//comboBox_29.setForeground(new Color(0, 0, 128));
		comboBox_29.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_30,comboBox_29 .getItemAt(comboBox_29 .getSelectedIndex()));
			}
		});
		comboBox_29.setBounds(520, 11, 184, 22);
		panel_12.add(comboBox_29);

		JLabel label_25 = new JLabel("Urun Alt Grup");
		label_25.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_25.setBounds(401, 43, 104, 14);
		panel_12.add(label_25);

		comboBox_30 = new JComboBox<String>();
		//comboBox_30.setForeground(new Color(0, 0, 128));
		comboBox_30.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_30.setEnabled(false);
		comboBox_30.setBounds(520, 39, 184, 22);
		panel_12.add(comboBox_30);

		chckbxIstenenAy = new JCheckBox("Istenen Ay");
		chckbxIstenenAy.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxIstenenAy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxIstenenAy.isSelected())
				{
					textField_37.setVisible(true);
				}
				else
				{
					textField_37.setVisible(false);
				}
			}
		});
		chckbxIstenenAy.setBounds(398, 113, 107, 23);
		panel_12.add(chckbxIstenenAy);

		chckbxDovizeCevirme = new JCheckBox("Dovize Cevirme");
		chckbxDovizeCevirme.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxDovizeCevirme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxDovizeCevirme.isSelected())
				{
					comboBox_32.setVisible(true);
					comboBox_33.setVisible(true);
				}
				else
				{
					comboBox_32.setVisible(false);
					comboBox_33.setVisible(false);
				}
			}
		});
		chckbxDovizeCevirme.setBounds(398, 139, 116, 23);
		//chckbxDovizeCevirme.setVisible(false);
		panel_12.add(chckbxDovizeCevirme);

		JLabel lblSinif = new JLabel("Sinif");
		lblSinif.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSinif.setBounds(10, 92, 69, 14);
		panel_12.add(lblSinif);

		textField_35 = new Obs_TextFIeld();
		textField_35.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_35.setColumns(10);
		textField_35.setBounds(90, 86, 125, 20);
		panel_12.add(textField_35);

		textField_36 = new Obs_TextFIeld();
		textField_36.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_36.setText("ZZZZZZZZZZZZ");
		textField_36.setColumns(10);
		textField_36.setBounds(248, 86, 125, 20);
		panel_12.add(textField_36);

		JLabel lblBirim = new JLabel("Birim");
		lblBirim.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBirim.setBounds(10, 117, 69, 14);
		panel_12.add(lblBirim);

		comboBox_26 = new JComboBox<String>();
		//comboBox_26.setForeground(new Color(0, 0, 128));
		comboBox_26.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_26.setModel(new DefaultComboBoxModel<String>(new String[] {"Tutar", "Miktar", "Agirlik"}));
		comboBox_26.setBounds(90, 113, 149, 22);
		panel_12.add(comboBox_26);

		JLabel lblGruplama = new JLabel("Gruplama");
		lblGruplama.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGruplama.setBounds(10, 143, 69, 14);
		panel_12.add(lblGruplama);

		comboBox_27 = new JComboBox<String>();
		//comboBox_27.setForeground(new Color(0, 0, 128));
		comboBox_27.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_27.setModel(new DefaultComboBoxModel<String>(new String[] {"Urun Kodu", "Urun Kodu-Yil", "Hesap Kodu", "Yil", "Hesap Kodu-Yil", "Yil_Ay", "Urun_Ana_Grup", "Urun_Ana_Grup_Yil"}));
		comboBox_27.setSelectedIndex(3);
		comboBox_27.setBounds(90, 139, 149, 22);
		panel_12.add(comboBox_27);

		JLabel lblStunlar = new JLabel("Stunlar");
		lblStunlar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblStunlar.setBounds(10, 172, 69, 14);
		panel_12.add(lblStunlar);

		comboBox_28 = new JComboBox<String>();
		//comboBox_28.setForeground(new Color(0, 0, 128));
		comboBox_28.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_28.setModel(new DefaultComboBoxModel<String>(new String[] {"Yil", "Ay", "Gun", "Ana Grup", "Hesap Kodu"}));
		comboBox_28.setSelectedIndex(1);
		comboBox_28.setBounds(90, 168, 149, 22);
		panel_12.add(comboBox_28);

		JLabel lblUrunOzelKod = new JLabel("Urun Ozel Kod 1");
		lblUrunOzelKod.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUrunOzelKod.setBounds(401, 76, 113, 14);
		panel_12.add(lblUrunOzelKod);

		comboBox_31 = new JComboBox<String>();
		//comboBox_31.setForeground(new Color(0, 0, 128));
		comboBox_31.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_31.setBounds(520, 72, 184, 22);
		panel_12.add(comboBox_31);

		textField_37 = new Obs_TextFIeld();
		textField_37.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_37.setBounds(520, 114, 58, 20);
		textField_37.setVisible(false);
		panel_12.add(textField_37);
		textField_37.setColumns(10);

		comboBox_32 = new JComboBox<String>();
		//comboBox_32.setForeground(new Color(0, 0, 128));
		comboBox_32.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_32.setModel(new DefaultComboBoxModel<String>(new String[] {"USD", "EUR"}));
		comboBox_32.setBounds(520, 139, 74, 22);
		//comboBox_32.setVisible(false);
		panel_12.add(comboBox_32);

		comboBox_33 = new JComboBox<String>();
		//comboBox_33.setForeground(new Color(0, 0, 128));
		comboBox_33.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_33.setModel(new DefaultComboBoxModel<String>(new String[] {"MA", "MS", "BA", "BS", "SA", "SS"}));
		comboBox_33.setBounds(520, 168, 74, 22);
		//comboBox_33.setVisible(false);
		panel_12.add(comboBox_33);

		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		//panel_13.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Imalat_Grup", null, panel_13, null);

		JLabel label_20 = new JLabel("Urun Kodu");
		label_20.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_20.setBounds(10, 17, 69, 14);
		panel_13.add(label_20);

		textField_39 = new Obs_TextFIeld();
		textField_39.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_39.setColumns(10);
		textField_39.setBounds(90, 11, 125, 20);
		panel_13.add(textField_39);

		textField_40 = new Obs_TextFIeld();
		textField_40.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_40.setText("ZZZZZZZZZZZZ");
		textField_40.setColumns(10);
		textField_40.setBounds(248, 11, 125, 20);
		panel_13.add(textField_40);

		JLabel label_22 = new JLabel("Tarih");
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_22.setBounds(10, 44, 58, 14);
		panel_13.add(label_22);

		dateChooser_22 = new JDateChooser();
		dateChooser_22.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dateChooser_22.setDate(new Date());
				}
			}
		});
		dateChooser_22.setBounds(90, 38, 125, 20);
		dateChooser_22.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_22.setDateFormatString("dd.MM.yyyy");
		dateChooser_22.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_13.add(dateChooser_22);

		dateChooser_23 = new JDateChooser();
		dateChooser_23.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dateChooser_23.setDate(new Date());
				}
			}
		});
		dateChooser_23.setBounds(248, 38, 125, 20);
		dateChooser_23.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_23.setDateFormatString("dd.MM.yyyy");
		dateChooser_23.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel_13.add(dateChooser_23);

		JLabel label_23 = new JLabel("Sinif");
		label_23.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_23.setBounds(10, 70, 69, 14);
		panel_13.add(label_23);

		textField_42 = new Obs_TextFIeld();
		textField_42.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_42.setColumns(10);
		textField_42.setBounds(90, 64, 125, 20);
		panel_13.add(textField_42);

		textField_43 = new Obs_TextFIeld();
		textField_43.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_43.setText("ZZZZZZZZZZZZ");
		textField_43.setColumns(10);
		textField_43.setBounds(248, 64, 125, 20);
		panel_13.add(textField_43);

		JLabel label_26 = new JLabel("Birim");
		label_26.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_26.setBounds(10, 96, 69, 14);
		panel_13.add(label_26);

		comboBox_34 = new JComboBox<String>();
		//comboBox_34.setForeground(new Color(0, 0, 128));
		comboBox_34.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_34.setModel(new DefaultComboBoxModel<String>(new String[] {"Agirlik", "Tutar", "Miktar"}));
		comboBox_34.setBounds(90, 92, 125, 22);
		panel_13.add(comboBox_34);

		JLabel label_27 = new JLabel("Gruplama");
		label_27.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_27.setBounds(10, 122, 69, 14);
		panel_13.add(label_27);

		comboBox_35 = new JComboBox<String>();
		//comboBox_35.setForeground(new Color(0, 0, 128));
		comboBox_35.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_35.setModel(new DefaultComboBoxModel<String>(new String[] {"Urun Kodu", "Yil_Ay", "Yil", "Ana_Grup", "Alt_Grup", "Alt_Grup_Yil", "Alt_Grup_Yil_Ay", "Depo"}));
		comboBox_35.setSelectedIndex(2);
		comboBox_35.setBounds(90, 118, 125, 22);
		panel_13.add(comboBox_35);

		JLabel label_28 = new JLabel("Stunlar");
		label_28.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_28.setBounds(10, 151, 69, 14);
		panel_13.add(label_28);

		comboBox_36 = new JComboBox<String>();
		//comboBox_36.setForeground(new Color(0, 0, 128));
		comboBox_36.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_36.setModel(new DefaultComboBoxModel<String>(new String[] {"Yil", "Ay", "Gun", "Ana Grup", "Depo"}));
		comboBox_36.setSelectedIndex(1);
		comboBox_36.setBounds(90, 147, 125, 22);
		panel_13.add(comboBox_36);

		JLabel label_31 = new JLabel("Ana Grup");
		label_31.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_31.setBounds(443, 110, 58, 14);
		panel_13.add(label_31);

		JLabel label_32 = new JLabel("Alt Grup");
		label_32.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_32.setBounds(443, 139, 69, 14);
		panel_13.add(label_32);

		comboBox_39 = new JComboBox<String>();
		//comboBox_39.setForeground(new Color(0, 0, 128));
		comboBox_39.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_39.setEnabled(false);
		comboBox_39.setBounds(512, 135, 174, 22);
		panel_13.add(comboBox_39);

		comboBox_40 = new JComboBox<String>();
		//comboBox_40.setForeground(new Color(0, 0, 128));
		comboBox_40.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_40.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_39,comboBox_40 .getItemAt(comboBox_40 .getSelectedIndex()));
			}
		});
		comboBox_40.setBounds(512, 106, 174, 22);
		panel_13.add(comboBox_40);

		comboBox_41 = new JComboBox<String>();
		//comboBox_41.setForeground(new Color(0, 0, 128));
		comboBox_41.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_41.setModel(new DefaultComboBoxModel<String>(new String[] {"Giren", "Cikan"}));
		comboBox_41.setBounds(512, 164, 117, 22);
		panel_13.add(comboBox_41);

		JPanel panel_14 = new JPanel();
		//panel_14.setBorder(new TitledBorder(null, "Urun Ana Grup / Alt Grup", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_14.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Urun Ana Grup / Alt Grup", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_14.setBounds(443, 11, 243, 88);
		panel_13.add(panel_14);
		panel_14.setLayout(null);

		comboBox_38 = new JComboBox<String>();
		//comboBox_38.setForeground(new Color(0, 0, 128));
		comboBox_38.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_38.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_37,comboBox_38 .getItemAt(comboBox_38 .getSelectedIndex()));
			}
		});
		comboBox_38.setBounds(32, 20, 184, 22);
		panel_14.add(comboBox_38);

		comboBox_37 = new JComboBox<String>();
		//comboBox_37.setForeground(new Color(0, 0, 128));
		comboBox_37.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_37.setBounds(32, 50, 184, 22);
		panel_14.add(comboBox_37);
		comboBox_37.setEnabled(false);

		JLabel lblTur_1 = new JLabel("Tur");
		lblTur_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTur_1.setBounds(443, 172, 69, 14);
		panel_13.add(lblTur_1);

		JPanel panel_15 = new JPanel();
		panel_15.setLayout(null);
		//panel_15.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Irsaliye_Rapor", null, panel_15, null);

		JLabel lblIrsaliyeNo = new JLabel("Irsaliye No");
		lblIrsaliyeNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblIrsaliyeNo.setBounds(10, 11, 69, 14);
		panel_15.add(lblIrsaliyeNo);

		textField_38 = new Obs_TextFIeld();
		textField_38.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_38.setColumns(10);
		textField_38.setBounds(90, 8, 104, 20);
		panel_15.add(textField_38);

		JLabel label_29 = new JLabel("Tarih");
		label_29.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_29.setBounds(10, 39, 58, 14);
		panel_15.add(label_29);

		dateChooser_24 = new JDateChooser();
		dateChooser_24.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_24.setDate(new Date());
			}
		});
		dateChooser_24.setBounds(90, 33, 125, 20);
		dateChooser_24.setDateFormatString("dd.MM.yyyy");
		dateChooser_24.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_24.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_15.add(dateChooser_24);

		JLabel label_30 = new JLabel("Cari Kodu");
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_30.setBounds(10, 64, 58, 14);
		panel_15.add(label_30);

		textField_41 = new Obs_TextFIeld();
		textField_41.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_41.setColumns(10);
		textField_41.setBounds(90, 58, 125, 20);
		textField_41.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						textField_41.setText( oac.hsp_hsp_kodu);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel_15.add(textField_41);

		JLabel label_33 = new JLabel("Adres Kodu");
		label_33.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_33.setBounds(10, 89, 69, 14);
		panel_15.add(label_33);

		textField_44 = new Obs_TextFIeld();
		textField_44.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_44.setColumns(10);
		textField_44.setBounds(90, 83, 125, 20);
		panel_15.add(textField_44);

		JLabel label_35 = new JLabel("Ozel Kod");
		label_35.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_35.setBounds(10, 139, 69, 14);
		panel_15.add(label_35);

		textField_46 = new Obs_TextFIeld();
		textField_46.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_46.setColumns(10);
		textField_46.setBounds(90, 133, 86, 20);
		panel_15.add(textField_46);

		JLabel lblFaturaNo = new JLabel("Fatura No");
		lblFaturaNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFaturaNo.setBounds(10, 164, 69, 14);
		panel_15.add(lblFaturaNo);

		textField_47 = new Obs_TextFIeld();
		textField_47.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_47.setColumns(10);
		textField_47.setBounds(90, 158, 86, 20);
		panel_15.add(textField_47);

		textField_48 = new Obs_TextFIeld();
		textField_48.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_48.setText("ZZZZZZZZZZ");
		textField_48.setColumns(10);
		textField_48.setBounds(248, 8, 104, 20);
		panel_15.add(textField_48);

		dateChooser_25 = new JDateChooser();
		dateChooser_25.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_25.setDate(new Date());
			}
		});
		dateChooser_25.setBounds(248, 33, 125, 20);
		dateChooser_25.setDateFormatString("dd.MM.yyyy");
		dateChooser_25.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_25.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel_15.add(dateChooser_25);

		textField_49 = new Obs_TextFIeld();
		textField_49.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_49.setText("ZZZZZZZZZZZZ");
		textField_49.setColumns(10);
		textField_49.setBounds(248, 58, 125, 20);
		textField_49.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						textField_49.setText( oac.hsp_hsp_kodu);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel_15.add(textField_49);

		textField_50 = new Obs_TextFIeld();
		textField_50.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_50.setText("ZZZZZZZZZZZZ");
		textField_50.setColumns(10);
		textField_50.setBounds(248, 83, 125, 20);
		panel_15.add(textField_50);

		textField_53 = new Obs_TextFIeld();
		textField_53.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_53.setText("ZZZZZZZZZZ");
		textField_53.setColumns(10);
		textField_53.setBounds(248, 158, 86, 20);
		panel_15.add(textField_53);

		JLabel label_37 = new JLabel("Ana Grup");
		label_37.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_37.setBounds(459, 11, 69, 14);
		panel_15.add(label_37);

		JLabel label_38 = new JLabel("Alt Grup");
		label_38.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_38.setBounds(459, 39, 69, 14);
		panel_15.add(label_38);

		comboBox_42 = new JComboBox<String>();
		//comboBox_42.setForeground(new Color(0, 0, 128));
		comboBox_42.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_42.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_43,comboBox_42 .getItemAt(comboBox_42 .getSelectedIndex()));
			}
		});
		comboBox_42.setBounds(566, 7, 149, 22);
		panel_15.add(comboBox_42);

		comboBox_43 = new JComboBox<String>();
		//comboBox_43.setForeground(new Color(0, 0, 128));
		comboBox_43.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_43.setEnabled(false);
		comboBox_43.setBounds(566, 35, 149, 22);
		panel_15.add(comboBox_43);

		comboBox_45 = new JComboBox<String>();
		//comboBox_45.setForeground(new Color(0, 0, 128));
		comboBox_45.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_45.setModel(new DefaultComboBoxModel<String>(new String[] {"","GIREN", "CIKAN"}));
		comboBox_45.setBounds(566, 91, 149, 22);
		panel_15.add(comboBox_45);

		JLabel label_40 = new JLabel("Turu");
		label_40.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_40.setBounds(459, 95, 46, 14);
		panel_15.add(label_40);

		JLabel label_41 = new JLabel("Urun Kodu");
		label_41.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_41.setBounds(10, 114, 69, 14);
		panel_15.add(label_41);

		textField_54 = new Obs_TextFIeld();
		textField_54.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_54.setColumns(10);
		textField_54.setBounds(90, 108, 125, 20);
		panel_15.add(textField_54);

		textField_55 = new Obs_TextFIeld();
		textField_55.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_55.setText("ZZZZZZZZZZZZ");
		textField_55.setColumns(10);
		textField_55.setBounds(248, 108, 125, 20);
		panel_15.add(textField_55);

		JPanel panel_16 = new JPanel();
		panel_16.setLayout(null);
		//panel_16.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Recete_Rapor", null, panel_16, null);

		JLabel lblReceteNo = new JLabel("Recete No");
		lblReceteNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblReceteNo.setBounds(10, 11, 69, 14);
		panel_16.add(lblReceteNo);

		textField_45 = new Obs_TextFIeld();
		textField_45.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_45.setColumns(10);
		textField_45.setBounds(90, 8, 104, 20);
		panel_16.add(textField_45);

		textField_58 = new Obs_TextFIeld();
		textField_58.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_58.setText("ZZZZZZZZZZ");
		textField_58.setColumns(10);
		textField_58.setBounds(248, 8, 104, 20);
		panel_16.add(textField_58);

		JLabel label_44 = new JLabel("Ana Grup");
		label_44.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_44.setBounds(10, 71, 69, 14);
		panel_16.add(label_44);

		JLabel label_45 = new JLabel("Alt Grup");
		label_45.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_45.setBounds(10, 99, 69, 14);
		panel_16.add(label_45);

		comboBox_44 = new JComboBox<String>();
		//comboBox_44.setForeground(new Color(0, 0, 128));
		comboBox_44.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_44.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_46,comboBox_44 .getItemAt(comboBox_44 .getSelectedIndex()));
			}
		});
		comboBox_44.setBounds(90, 67, 176, 22);
		panel_16.add(comboBox_44);

		comboBox_46 = new JComboBox<String>();
		//comboBox_46.setForeground(new Color(0, 0, 128));
		comboBox_46.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_46.setEnabled(false);
		comboBox_46.setBounds(90, 95, 176, 22);
		panel_16.add(comboBox_46);

		comboBox_47 = new JComboBox<String>();
		//comboBox_47.setForeground(new Color(0, 0, 128));
		comboBox_47.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_47.setModel(new DefaultComboBoxModel<String>(new String[] {"", "AKTIV", "PASIV"}));
		comboBox_47.setBounds(90, 124, 104, 22);
		panel_16.add(comboBox_47);

		JLabel label_46 = new JLabel("Turu");
		label_46.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_46.setBounds(10, 128, 46, 14);
		panel_16.add(label_46);

		JLabel label_47 = new JLabel("Urun Kodu");
		label_47.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_47.setBounds(10, 42, 69, 14);
		panel_16.add(label_47);

		textField_62 = new Obs_TextFIeld();
		textField_62.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_62.setColumns(10);
		textField_62.setBounds(90, 36, 125, 20);
		panel_16.add(textField_62);

		textField_63 = new Obs_TextFIeld();
		textField_63.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_63.setText("ZZZZZZZZZZZZ");
		textField_63.setColumns(10);
		textField_63.setBounds(248, 36, 125, 20);
		panel_16.add(textField_63);

		JPanel panel_17 = new JPanel();

		panel_17.setLayout(null);
		//panel_17.setBorder(new TitledBorder(null, "Urun Ana Grup / Alt Grup", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_17.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Urun Ana Grup / Alt Grup", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_17.setBounds(472, 11, 243, 88);
		panel_16.add(panel_17);

		comboBox_48 = new JComboBox<String>();
		//comboBox_48.setForeground(new Color(0, 0, 128));
		comboBox_48.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_48.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_49,comboBox_48 .getItemAt(comboBox_48 .getSelectedIndex()));
			}
		});
		comboBox_48.setBounds(32, 20, 184, 22);
		panel_17.add(comboBox_48);

		comboBox_49 = new JComboBox<String>();
		//comboBox_49.setForeground(new Color(0, 0, 128));
		comboBox_49.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_49.setEnabled(false);
		comboBox_49.setBounds(32, 50, 184, 22);
		panel_17.add(comboBox_49);

		JPanel panel_18 = new JPanel();
		panel_18.setLayout(null);
		//panel_18.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Ortalama_Fiat", null, panel_18, null);

		JLabel label_21 = new JLabel("Urun Kodu");
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_21.setBounds(10, 17, 69, 14);
		panel_18.add(label_21);

		textField_51 = new Obs_TextFIeld();
		textField_51.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_51.setColumns(10);
		textField_51.setBounds(90, 11, 125, 20);
		panel_18.add(textField_51);

		textField_52 = new Obs_TextFIeld();
		textField_52.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_52.setText("ZZZZZZZZZZZZ");
		textField_52.setColumns(10);
		textField_52.setBounds(248, 11, 125, 20);
		panel_18.add(textField_52);

		JLabel label_34 = new JLabel("Tarih");
		label_34.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_34.setBounds(10, 44, 58, 14);
		panel_18.add(label_34);

		dateChooser_26 = new JDateChooser();
		dateChooser_26.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_26.setDate(new Date());
			}
		});
		dateChooser_26.setBounds(90, 38, 125, 20);
		dateChooser_26.setDateFormatString("dd.MM.yyyy");
		dateChooser_26.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_26.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_18.add(dateChooser_26);

		dateChooser_27 = new JDateChooser();
		dateChooser_27.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_27.setDate(new Date());
			}
		});
		dateChooser_27.setBounds(248, 38, 125, 20);
		dateChooser_27.setDateFormatString("dd.MM.yyyy");
		dateChooser_27.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_27.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel_18.add(dateChooser_27);

		JLabel label_36 = new JLabel("Sinif");
		label_36.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_36.setBounds(10, 70, 69, 14);
		panel_18.add(label_36);

		textField_56 = new Obs_TextFIeld();
		textField_56.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_56.setColumns(10);
		textField_56.setBounds(90, 64, 125, 20);
		panel_18.add(textField_56);

		textField_57 = new Obs_TextFIeld();
		textField_57.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_57.setText("ZZZZZZZZZZZZ");
		textField_57.setColumns(10);
		textField_57.setBounds(248, 64, 125, 20);
		panel_18.add(textField_57);

		JLabel label_42 = new JLabel("Gruplama");
		label_42.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_42.setBounds(10, 126, 69, 14);
		panel_18.add(label_42);

		comboBox_51 = new JComboBox<String>();
		//comboBox_51.setForeground(new Color(0, 0, 128));
		comboBox_51.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_51.setModel(new DefaultComboBoxModel<String>(new String[] {"Urun Kodu", "Hesap Kodu", "Hesap Kodu-Ana_Alt_Grup", "Yil", "Yil_Ay", "Urun Ana Grup"}));
		comboBox_51.setBounds(90, 122, 216, 22);
		panel_18.add(comboBox_51);

		JPanel panel_19 = new JPanel();
		panel_19.setLayout(null);
		//panel_19.setBorder(new TitledBorder(null, "Urun Ana Grup / Alt Grup", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_19.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Urun Ana Grup / Alt Grup", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_19.setBounds(443, 11, 243, 88);
		panel_18.add(panel_19);

		comboBox_56 = new JComboBox<String>();
		//comboBox_56.setForeground(new Color(0, 0, 128));
		comboBox_56.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_56.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_57,comboBox_56 .getItemAt(comboBox_56 .getSelectedIndex()));
			}
		});
		comboBox_56.setBounds(32, 20, 184, 22);
		panel_19.add(comboBox_56);

		comboBox_57 = new JComboBox<String>();
		//comboBox_57.setForeground(new Color(0, 0, 128));
		comboBox_57.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_57.setEnabled(false);
		comboBox_57.setBounds(32, 50, 184, 22);
		panel_19.add(comboBox_57);

		JLabel lblHesapKodu_1 = new JLabel("Hesap Kodu");
		lblHesapKodu_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHesapKodu_1.setBounds(10, 97, 69, 14);
		panel_18.add(lblHesapKodu_1);

		textField_59 = new Obs_TextFIeld();
		textField_59.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_59.setColumns(10);
		textField_59.setBounds(90, 91, 125, 20);
		textField_59.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						textField_59.setText( oac.hsp_hsp_kodu);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel_18.add(textField_59);

		textField_60 = new Obs_TextFIeld();
		textField_60.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_60.setText("ZZZZZZZZZZZZ");
		textField_60.setColumns(10);
		textField_60.setBounds(248, 91, 125, 20);
		textField_60.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						textField_60.setText( oac.hsp_hsp_kodu);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel_18.add(textField_60);

		comboBox_50 = new JComboBox<String>();
		//comboBox_50.setForeground(new Color(0, 0, 128));
		comboBox_50.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_50.setModel(new DefaultComboBoxModel<String>(new String[] {"USD", "EUR"}));
		comboBox_50.setBounds(561, 118, 125, 22);
		panel_18.add(comboBox_50);

		lblDoviz = new JLabel("Doviz");
		lblDoviz.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDoviz.setBounds(443, 122, 69, 14);
		panel_18.add(lblDoviz);

		JPanel panel_20 = new JPanel();
		panel_20.setLayout(null);
		//panel_20.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Urun_Liste", null, panel_20, null);

		JLabel label_43 = new JLabel("Urun Kodu");
		label_43.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_43.setBounds(10, 16, 69, 14);
		panel_20.add(label_43);

		JLabel lblBarkod_1 = new JLabel("Barkod");
		lblBarkod_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBarkod_1.setBounds(10, 41, 69, 14);
		panel_20.add(lblBarkod_1);

		textField_61 = new Obs_TextFIeld();
		textField_61.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_61.setColumns(10);
		textField_61.setBounds(79, 35, 125, 20);
		panel_20.add(textField_61);

		textField_64 = new Obs_TextFIeld();
		textField_64.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_64.setColumns(10);
		textField_64.setBounds(79, 10, 125, 20);
		panel_20.add(textField_64);

		textField_65 = new Obs_TextFIeld();
		textField_65.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_65.setText("ZZZZZZZZZZZZ");
		textField_65.setColumns(10);
		textField_65.setBounds(248, 10, 125, 20);
		panel_20.add(textField_65);

		textField_66 = new Obs_TextFIeld();
		textField_66.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_66.setText("ZZZZZZZZZZZZ");
		textField_66.setColumns(10);
		textField_66.setBounds(248, 35, 125, 20);
		panel_20.add(textField_66);

		JLabel label_51 = new JLabel("Depo");
		label_51.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_51.setBounds(10, 120, 46, 14);
		panel_20.add(label_51);

		comboBox_54 = new JComboBox<String>();
		//comboBox_54.setForeground(new Color(0, 0, 128));
		comboBox_54.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_54.setBounds(79, 116, 174, 22);
		panel_20.add(comboBox_54);

		JLabel label_52 = new JLabel("Urun Ana Grup");
		label_52.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_52.setBounds(464, 10, 104, 14);
		panel_20.add(label_52);

		comboBox_55 = new JComboBox<String>();
		//comboBox_55.setForeground(new Color(0, 0, 128));
		comboBox_55.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_55.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_58,comboBox_55 .getItemAt(comboBox_55 .getSelectedIndex()));
			}
		});
		comboBox_55.setBounds(571, 6, 149, 22);
		panel_20.add(comboBox_55);

		JLabel label_53 = new JLabel("Urun Alt Grup");
		label_53.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_53.setBounds(464, 38, 104, 14);
		panel_20.add(label_53);

		comboBox_58 = new JComboBox<String>();
		//comboBox_58.setForeground(new Color(0, 0, 128));
		comboBox_58.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_58.setEnabled(false);
		comboBox_58.setBounds(571, 34, 149, 22);
		panel_20.add(comboBox_58);

		JLabel lblOzelKod = new JLabel("Ozel Kod 1");
		lblOzelKod.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOzelKod.setBounds(464, 96, 97, 14);
		panel_20.add(lblOzelKod);

		comboBox_59 = new JComboBox<String>();
		//comboBox_59.setForeground(new Color(0, 0, 128));
		comboBox_59.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_59.setBounds(571, 92, 149, 22);
		panel_20.add(comboBox_59);

		JLabel lblBirim_1 = new JLabel("Birim");
		lblBirim_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBirim_1.setBounds(10, 66, 69, 14);
		panel_20.add(lblBirim_1);

		JLabel lblSinif_1 = new JLabel("Sinif");
		lblSinif_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSinif_1.setBounds(10, 91, 69, 14);
		panel_20.add(lblSinif_1);

		textField_67 = new Obs_TextFIeld();
		textField_67.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_67.setColumns(10);
		textField_67.setBounds(79, 85, 125, 20);
		panel_20.add(textField_67);

		textField_68 = new Obs_TextFIeld();
		textField_68.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_68.setColumns(10);
		textField_68.setBounds(79, 60, 125, 20);
		panel_20.add(textField_68);

		textField_69 = new Obs_TextFIeld();
		textField_69.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_69.setText("ZZZZZZZZZZZZ");
		textField_69.setColumns(10);
		textField_69.setBounds(248, 60, 125, 20);
		panel_20.add(textField_69);

		textField_70 = new Obs_TextFIeld();
		textField_70.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_70.setText("ZZZZZZZZZZZZ");
		textField_70.setColumns(10);
		textField_70.setBounds(248, 85, 125, 20);
		panel_20.add(textField_70);

		JLabel lblMensei = new JLabel("Mensei");
		lblMensei.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMensei.setBounds(10, 149, 46, 14);
		panel_20.add(lblMensei);

		comboBox_52 = new JComboBox<String>();
		//comboBox_52.setForeground(new Color(0, 0, 128));
		comboBox_52.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_52.setBounds(79, 145, 174, 22);
		panel_20.add(comboBox_52);

		JLabel lblOzelKod_1 = new JLabel("Ozel Kod 2");
		lblOzelKod_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOzelKod_1.setBounds(464, 124, 97, 14);
		panel_20.add(lblOzelKod_1);

		comboBox_53 = new JComboBox<String>();
		//comboBox_53.setForeground(new Color(0, 0, 128));
		comboBox_53.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_53.setBounds(571, 120, 149, 22);
		panel_20.add(comboBox_53);

		JPanel panel_21 = new JPanel();
		panel_21.setLayout(null);
		//panel_21.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Envanter", null, panel_21, null);

		JLabel label_39 = new JLabel("Tarih");
		label_39.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_39.setBounds(10, 17, 58, 14);
		panel_21.add(label_39);

		dateChooser_28 = new JDateChooser();
		dateChooser_28.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_28.setDate(new Date());
			}
		});

		dateChooser_28.setDateFormatString("dd.MM.yyyy");
		dateChooser_28.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_28.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		dateChooser_28.setBounds(79, 11, 125, 20);
		panel_21.add(dateChooser_28);

		dateChooser_29 = new JDateChooser();
		dateChooser_29.setBounds(248, 11, 125, 20);
		dateChooser_29.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_29.setDate(new Date());
			}
		});
		dateChooser_29.setDateFormatString("dd.MM.yyyy");
		dateChooser_29.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_29.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel_21.add(dateChooser_29);

		JLabel label_48 = new JLabel("Urun Kodu");
		label_48.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_48.setBounds(10, 42, 69, 14);
		panel_21.add(label_48);

		JLabel label_49 = new JLabel("Evrak No");
		label_49.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_49.setBounds(10, 67, 69, 14);
		panel_21.add(label_49);

		textField_71 = new Obs_TextFIeld();
		textField_71.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_71.setColumns(10);
		textField_71.setBounds(79, 61, 125, 20);
		panel_21.add(textField_71);

		textField_72 = new Obs_TextFIeld();
		textField_72.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_72.setColumns(10);
		textField_72.setBounds(79, 36, 125, 20);
		panel_21.add(textField_72);

		textField_73 = new Obs_TextFIeld();
		textField_73.setText("ZZZZZZZZZZZZ");
		textField_73.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_73.setColumns(10);
		textField_73.setBounds(248, 36, 125, 20);
		panel_21.add(textField_73);

		textField_74 = new Obs_TextFIeld();
		textField_74.setText("ZZZZZZZZZZZZ");
		textField_74.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_74.setColumns(10);
		textField_74.setBounds(248, 61, 125, 20);
		panel_21.add(textField_74);

		JLabel label_50 = new JLabel("Ana Grup");
		label_50.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_50.setBounds(10, 96, 58, 14);
		panel_21.add(label_50);

		comboBox_60 = new JComboBox<String>();
		//comboBox_60.setForeground(new Color(0, 0, 128));
		comboBox_60.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_61,comboBox_60 .getItemAt(comboBox_60 .getSelectedIndex()));
			}
		});
		comboBox_60.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_60.setBounds(79, 92, 174, 22);
		panel_21.add(comboBox_60);

		JLabel label_54 = new JLabel("Alt Grup");
		label_54.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_54.setBounds(10, 125, 69, 14);
		panel_21.add(label_54);

		comboBox_61 = new JComboBox<String>();
		//comboBox_61.setForeground(new Color(0, 0, 128));
		comboBox_61.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_61.setEnabled(false);
		comboBox_61.setBounds(79, 121, 174, 22);
		panel_21.add(comboBox_61);

		JLabel label_55 = new JLabel("Depo");
		label_55.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_55.setBounds(10, 154, 46, 14);
		panel_21.add(label_55);

		comboBox_62 = new JComboBox<String>();
		//comboBox_62.setForeground(new Color(0, 0, 128));
		comboBox_62.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_62.setBounds(79, 150, 174, 22);
		panel_21.add(comboBox_62);

		JLabel label_56 = new JLabel("Urun Ana Grup");
		label_56.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_56.setBounds(464, 10, 104, 14);
		panel_21.add(label_56);

		comboBox_63 = new JComboBox<String>();
		//comboBox_63.setForeground(new Color(0, 0, 128));
		comboBox_63.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_64,comboBox_63 .getItemAt(comboBox_63 .getSelectedIndex()));
			}
		});
		comboBox_63.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_63.setBounds(571, 6, 149, 22);
		panel_21.add(comboBox_63);

		JLabel label_57 = new JLabel("Urun Alt Grup");
		label_57.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_57.setBounds(464, 38, 104, 14);
		panel_21.add(label_57);

		comboBox_64 = new JComboBox<String>();
		//comboBox_64.setForeground(new Color(0, 0, 128));
		comboBox_64.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_64.setEnabled(false);
		comboBox_64.setBounds(571, 34, 149, 22);
		panel_21.add(comboBox_64);

		checkBox_2 = new JCheckBox("Depo Hareketleri Dahil");
		checkBox_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkBox_2.setBounds(75, 179, 174, 23);
		panel_21.add(checkBox_2);

		checkBox_3 = new JCheckBox("Uretim Fisleri Dahil");
		checkBox_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkBox_3.setSelected(true);
		checkBox_3.setBounds(75, 202, 194, 23);
		panel_21.add(checkBox_3);

		JLabel lblGruplama_1 = new JLabel("Gruplama");
		lblGruplama_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGruplama_1.setBounds(464, 96, 74, 14);
		panel_21.add(lblGruplama_1);

		comboBox_65 = new JComboBox<String>();
		//comboBox_65.setForeground(new Color(0, 0, 128));
		comboBox_65.setModel(new DefaultComboBoxModel<String>(new String[] {"Urun Kodu"}));
		comboBox_65.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_65.setBounds(571, 92, 149, 22);
		panel_21.add(comboBox_65);

		JLabel lblFiatlama = new JLabel("Fiatlama");
		lblFiatlama.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFiatlama.setBounds(464, 150, 74, 14);
		panel_21.add(lblFiatlama);

		comboBox_66 = new JComboBox<String>();
		//comboBox_66.setForeground(new Color(0, 0, 128));
		comboBox_66.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (! comboBox_66.getSelectedItem().toString().equals("Agirlikli Ortalama"))
				{
					textField_73.setVisible(false);
					lblGruplama_1.setVisible(false);
					comboBox_65.setVisible(false);
				}
				else
				{
					textField_73.setVisible(true);
					lblGruplama_1.setVisible(true);
					comboBox_65.setVisible(true);
				}
			}
		});
		comboBox_66.setModel(new DefaultComboBoxModel<String>(new String[] {"Agirlikli Ortalama", "FIFO", "LIFO"}));
		comboBox_66.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_66.setBounds(571, 146, 149, 22);
		panel_21.add(comboBox_66);

		JPanel panel_22 = new JPanel();
		panel_22.setLayout(null);
		//panel_22.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Zayi", null, panel_22, null);

		JLabel label_58 = new JLabel("Tarih");
		label_58.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_58.setBounds(10, 17, 58, 14);
		panel_22.add(label_58);

		dateChooser_30 = new JDateChooser();
		dateChooser_30.setBounds(79, 11, 125, 20);
		dateChooser_30.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_30.setDate(new Date());
			}
		});
		dateChooser_30.setDateFormatString("dd.MM.yyyy");
		dateChooser_30.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_30.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_22.add(dateChooser_30);

		dateChooser_31 = new JDateChooser();
		dateChooser_31.setBounds(248, 11, 125, 20);
		dateChooser_31.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_31.setDate(new Date());
			}
		});
		dateChooser_31.setDateFormatString("dd.MM.yyyy");
		dateChooser_31.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_31.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel_22.add(dateChooser_31);

		JLabel label_59 = new JLabel("Urun Kodu");
		label_59.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_59.setBounds(10, 42, 69, 14);
		panel_22.add(label_59);

		JLabel label_60 = new JLabel("Evrak No");
		label_60.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_60.setBounds(10, 67, 69, 14);
		panel_22.add(label_60);

		textField_75 = new Obs_TextFIeld();
		textField_75.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_75.setColumns(10);
		textField_75.setBounds(79, 61, 125, 20);
		panel_22.add(textField_75);

		textField_76 = new Obs_TextFIeld();
		textField_76.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_76.setColumns(10);
		textField_76.setBounds(79, 36, 125, 20);
		panel_22.add(textField_76);

		textField_77 = new Obs_TextFIeld();
		textField_77.setText("ZZZZZZZZZZZZ");
		textField_77.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_77.setColumns(10);
		textField_77.setBounds(248, 36, 125, 20);
		panel_22.add(textField_77);

		textField_78 = new Obs_TextFIeld();
		textField_78.setText("ZZZZZZZZZZZZ");
		textField_78.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_78.setColumns(10);
		textField_78.setBounds(248, 61, 125, 20);
		panel_22.add(textField_78);

		JLabel label_61 = new JLabel("Ana Grup");
		label_61.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_61.setBounds(10, 96, 58, 14);
		panel_22.add(label_61);

		comboBox_67 = new JComboBox<String>();
		//comboBox_67.setForeground(new Color(0, 0, 128));
		comboBox_67.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_68,comboBox_67 .getItemAt(comboBox_67 .getSelectedIndex()));
			}
		});
		comboBox_67.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_67.setBounds(79, 92, 174, 22);
		panel_22.add(comboBox_67);

		JLabel label_62 = new JLabel("Alt Grup");
		label_62.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_62.setBounds(10, 125, 69, 14);
		panel_22.add(label_62);

		comboBox_68 = new JComboBox<String>();
		//comboBox_68.setForeground(new Color(0, 0, 128));
		comboBox_68.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_68.setEnabled(false);
		comboBox_68.setBounds(79, 121, 174, 22);
		panel_22.add(comboBox_68);

		JLabel label_63 = new JLabel("Depo");
		label_63.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_63.setBounds(10, 154, 46, 14);
		panel_22.add(label_63);

		comboBox_69 = new JComboBox<String>();
		//comboBox_69.setForeground(new Color(0, 0, 128));
		comboBox_69.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_69.setBounds(79, 150, 174, 22);
		panel_22.add(comboBox_69);

		JLabel label_64 = new JLabel("Urun Ana Grup");
		label_64.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_64.setBounds(464, 10, 104, 14);
		panel_22.add(label_64);

		comboBox_70 = new JComboBox<String>();
		//comboBox_70.setForeground(new Color(0, 0, 128));
		comboBox_70.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(comboBox_71,comboBox_70 .getItemAt(comboBox_70 .getSelectedIndex()));
			}
		});
		comboBox_70.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_70.setBounds(571, 6, 149, 22);
		panel_22.add(comboBox_70);

		JLabel label_65 = new JLabel("Urun Alt Grup");
		label_65.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_65.setBounds(464, 38, 104, 14);
		panel_22.add(label_65);

		comboBox_71 = new JComboBox<String>();
		//comboBox_71.setForeground(new Color(0, 0, 128));
		comboBox_71.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_71.setEnabled(false);
		comboBox_71.setBounds(571, 34, 149, 22);
		panel_22.add(comboBox_71);
		//*************************************************KUR ***************************************************
		JPanel panel_23 = new JPanel();
		//panel_23.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Kur_Grafik", null, panel_23, null);
		panel_23.setLayout(null);

		JLabel lblNewLabel_35 = new JLabel("Tarih");
		lblNewLabel_35.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_35.setBounds(26, 28, 62, 14);
		panel_23.add(lblNewLabel_35);

		dateChooser_32 = new JDateChooser();
		dateChooser_32.setBounds(110, 22, 107, 20);
		dateChooser_32.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_32.setDate(new Date());
			}
		});
		dateChooser_32.setDateFormatString("dd.MM.yyyy");
		dateChooser_32.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_32.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_23.add(dateChooser_32);

		dateChooser_32_1 = new JDateChooser();
		dateChooser_32_1.setBounds(239, 22, 107, 20);
		dateChooser_32_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_32_1.setDate(new Date());
			}
		});
		dateChooser_32_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_32_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_32_1.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		
		panel_23.add(dateChooser_32_1);

		JLabel lblNewLabel_35_1 = new JLabel("Cins");
		lblNewLabel_35_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_35_1.setBounds(26, 55, 62, 14);
		panel_23.add(lblNewLabel_35_1);

		comboBox_72 = new JComboBox<String>();
		comboBox_72.setFont(new Font("Tahoma", Font.BOLD, 12));
		//comboBox_72.setForeground(new Color(0, 0, 128));
		comboBox_72.setModel(new DefaultComboBoxModel<String>(new String[] {"MA", "MS", "SA", "SS", "BA", "BB"}));
		comboBox_72.setBounds(110, 90, 62, 22);
		panel_23.add(comboBox_72);

		comboBox_73 = new JComboBox<String>();
		comboBox_73.setFont(new Font("Tahoma", Font.BOLD, 12));
		//comboBox_73.setForeground(new Color(0, 0, 128));
		comboBox_73.setModel(new DefaultComboBoxModel<String>(new String[] {"Gun", "Ay", "Yil"}));
		comboBox_73.setBounds(110, 123, 62, 22);
		panel_23.add(comboBox_73);

		comboBox_74 = new JComboBox<String>();
		//comboBox_74.setForeground(new Color(25, 25, 112));
		comboBox_74.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_74.setModel(new DefaultComboBoxModel<String>(new String[] {"USD", "EUR"}));
		comboBox_74.setEditable(true);
		comboBox_74.setBounds(110, 53, 62, 22);
		panel_23.add(comboBox_74);

		//***********************************************GUNLUK ********************************************************************	
		JPanel panel_GUNLUK = new JPanel();
		//panel_GUNLUK.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Hazir_Gorev", null, panel_GUNLUK, null);
		panel_GUNLUK.setLayout(null);

		JLabel lblNewLabel_36 = new JLabel("Isim");
		lblNewLabel_36.setBounds(21, 23, 48, 14);
		panel_GUNLUK.add(lblNewLabel_36);

		cmbGrv_Isim = new JComboBox<String>();
		cmbGrv_Isim.setBounds(100, 20, 150, 22);
		panel_GUNLUK.add(cmbGrv_Isim);

		JLabel lblNewLabel_37 = new JLabel("Ilk Tarih");
		lblNewLabel_37.setBounds(21, 63, 65, 14);
		panel_GUNLUK.add(lblNewLabel_37);

		dateChooser_33 = new JDateChooser();
		dateChooser_33.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_33.setDateFormatString("dd.MM.yyyy");
		dateChooser_33.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		dateChooser_33.setBounds(100, 60, 150, 20);
		dateChooser_33.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_33.setDate(new Date());
			}
		});
		
		panel_GUNLUK.add(dateChooser_33);

		JLabel lblNewLabel_38 = new JLabel("Son Tarih");
		lblNewLabel_38.setBounds(285, 63, 65, 14);
		panel_GUNLUK.add(lblNewLabel_38);

		dateChooser_34 = new JDateChooser();
		dateChooser_34.setBounds(354, 62, 128, 20);
		dateChooser_34.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_34.setDateFormatString("dd.MM.yyyy");
		dateChooser_34.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		dateChooser_34.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_34.setDate(new Date());
			}
		});
		panel_GUNLUK.add(dateChooser_34);

		JLabel lblNewLabel_39 = new JLabel("Ilk Saat");
		lblNewLabel_39.setBounds(21, 99, 65, 14);
		panel_GUNLUK.add(lblNewLabel_39);

		JLabel lblNewLabel_40 = new JLabel("Son Saat");
		lblNewLabel_40.setBounds(285, 99, 65, 14);
		panel_GUNLUK.add(lblNewLabel_40);

		comboBox_75 = new JComboBox<String>();
		comboBox_75.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox_75.setModel(new DefaultComboBoxModel<String>(new String[] {"06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"}));

		comboBox_75.setBounds(100, 96, 75, 22);
		panel_GUNLUK.add(comboBox_75);

		comboBox_76 = new JComboBox<String>();
		comboBox_76.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox_76.setBounds(354, 96, 75, 22);
		comboBox_76.setModel(new DefaultComboBoxModel<String>(new String[] {"06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"}));
		comboBox_76.setSelectedItem("23:00");
		panel_GUNLUK.add(comboBox_76);


		//******************************** KERESTE GRUP *************************************************************************

		JPanel panel_KERESTE = new JPanel();
		//panel_KERESTE.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Kereste", null, panel_KERESTE, null);
		panel_KERESTE.setLayout(null);

		JLabel label_18_1 = new JLabel("Tarih");
		label_18_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_18_1.setBounds(10, 15, 60, 14);
		panel_KERESTE.add(label_18_1);

		JLabel label_19_1 = new JLabel("Urun Kodu");
		label_19_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_19_1.setBounds(10, 37, 69, 14);
		panel_KERESTE.add(label_19_1);

		JLabel lblHesapKodu_2 = new JLabel("Hesap Kodu");
		lblHesapKodu_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHesapKodu_2.setBounds(10, 59, 80, 14);
		panel_KERESTE.add(lblHesapKodu_2);

		JLabel lblBirim_2 = new JLabel("Birim");
		lblBirim_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBirim_2.setBounds(10, 135, 69, 14);
		panel_KERESTE.add(lblBirim_2);

		JLabel lblGruplama_2 = new JLabel("Gruplama");
		lblGruplama_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGruplama_2.setBounds(10, 161, 69, 14);
		panel_KERESTE.add(lblGruplama_2);

		JLabel lblStunlar_1 = new JLabel("Stunlar");
		lblStunlar_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblStunlar_1.setBounds(10, 190, 69, 14);
		panel_KERESTE.add(lblStunlar_1);

		dateChooser_20_1 = new JDateChooser();
		dateChooser_20_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_20_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_20_1.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		dateChooser_20_1.setBounds(90, 11, 125, 20);
		dateChooser_20_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_20_1.setDate(new Date());
			}
		});
		
		panel_KERESTE.add(dateChooser_20_1);

		dateChooser_21_1 = new JDateChooser();
		dateChooser_21_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_21_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_21_1.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		dateChooser_21_1.setBounds(248, 11, 125, 20);
		dateChooser_21_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_21_1.setDate(new Date());
			}
		});
		
		panel_KERESTE.add(dateChooser_21_1);

		textField_82 = new Obs_TextFIeld();
		textField_82.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_82.setColumns(10);
		textField_82.setBounds(90, 55, 125, 20);
		panel_KERESTE.add(textField_82);

		textField_83 = new Obs_TextFIeld();
		textField_83.setText("ZZZZZZZZZZZZ");
		textField_83.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_83.setColumns(10);
		textField_83.setBounds(248, 55, 125, 20);
		panel_KERESTE.add(textField_83);

		comboBox_26_1 = new JComboBox<String>();
		//comboBox_26_1.setForeground(new Color(0, 0, 128));
		comboBox_26_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_26_1.setModel(new DefaultComboBoxModel<String>(new String[] {"m3","Tutar", "Miktar"}));
		comboBox_26_1.setBounds(90, 129, 149, 22);
		comboBox_26_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hangi = comboBox_26_1.getItemAt(comboBox_26_1.getSelectedIndex())  ;
				if (hangi == "Tutar")
					chckbxDovizeCevirme_1.setVisible(true);
				else
					chckbxDovizeCevirme_1.setVisible(false);
			}
		});
		panel_KERESTE.add(comboBox_26_1);

		comboBox_27_1 = new JComboBox<String>();
		//comboBox_27_1.setForeground(new Color(0, 0, 128));
		comboBox_27_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_27_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Urun Kodu","Sinif" , "Sinif-Kal" ,"Sinif-Kal-Boy","Sinif-Kal-Gen", "Urun Kodu-Yil", "Yil",  "Yil-Ay","Paket-Sinif-Kal-Boy","Paket-Sinif-Kal-Gen","Kons-Paket-Sinif-Kal-Gen" ,"Kons-Paket-Sinif-Kal-Boy","Paket-Konsimento","Hesap-Kodu","Hesap-Kodu-Yil","Konsimento","Ortalama-Genislik"}));
		comboBox_27_1.setBounds(90, 155, 200, 22);
		comboBox_27_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hangi = comboBox_27_1.getItemAt(comboBox_27_1.getSelectedIndex())  ;
				if (hangi == "Sinif-Kal" || hangi == "Sinif-Kal-Boy" || hangi == "Sinif-Kal-Gen" || hangi == "Paket-Sinif-Kal-Gen" || hangi == "Paket-Sinif-Kal-Boy" || hangi == "Kons-Paket-Sinif-Kal-Gen")
					chckbxNewCheckBox_3.setVisible(true);
				else
					chckbxNewCheckBox_3.setVisible(false);
			}
		});
		panel_KERESTE.add(comboBox_27_1);

		comboBox_28_1 = new JComboBox<String>();
		//comboBox_28_1.setForeground(new Color(0, 0, 128));
		comboBox_28_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_28_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Yil", "Ay", "Gun", "Kalinlik", "Boy","Genislik","Sinif","Hesap-Kodu"}));
		//comboBox_28_1.setSelectedIndex(1);
		comboBox_28_1.setBounds(90, 184, 175, 22);
		panel_KERESTE.add(comboBox_28_1);

		formattedTextField = new JFormattedTextField();
		formattedTextField.setFont(new Font("Tahoma", Font.BOLD, 11));
		formattedTextField.setBounds(89, 33, 125, 20);
		MaskFormatter mask;
		try {
			mask = new MaskFormatter("AA-###-####-####");
			mask.install(formattedTextField);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		formattedTextField.setText("00-000-0000-0000");
		panel_KERESTE.add(formattedTextField);

		formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		formattedTextField_1.setBounds(248, 33, 125, 20);
		MaskFormatter maskk;
		try {
			maskk = new MaskFormatter("AA-###-####-####");
			maskk.install(formattedTextField_1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		formattedTextField_1.setText("ZZ-999-9999-9999");
		panel_KERESTE.add(formattedTextField_1);

		comboBox_77 = new JComboBox<String>();
		//comboBox_77.setForeground(new Color(0, 0, 128));
		comboBox_77.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_77.setModel(new DefaultComboBoxModel<String>(new String[] { "GIREN", "CIKAN","STOK"}));
		comboBox_77.setBounds(594, 114, 110, 22);
		panel_KERESTE.add(comboBox_77);

		lblNewLabel_41 = new JLabel("Turu");
		lblNewLabel_41.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_41.setBounds(520, 119, 46, 14);
		panel_KERESTE.add(lblNewLabel_41);

		comboBox_78 = new JComboBox<String>();
		//comboBox_78.setForeground(new Color(0, 0, 128));
		comboBox_78.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_78.setBounds(520, 11, 184, 22);
		comboBox_78.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ker_alt_grup_doldur(comboBox_79,comboBox_78 .getItemAt(comboBox_78 .getSelectedIndex()));
			}
		});
		panel_KERESTE.add(comboBox_78);

		comboBox_79 = new JComboBox<String>();
		//comboBox_79.setForeground(new Color(0, 0, 128));
		comboBox_79.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_79.setEnabled(false);
		comboBox_79.setBounds(520, 35, 184, 22);
		panel_KERESTE.add(comboBox_79);

		comboBox_80 = new JComboBox<String>();
		//comboBox_80.setForeground(new Color(0, 0, 128));
		comboBox_80.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_80.setBounds(520, 62, 184, 22);
		panel_KERESTE.add(comboBox_80);

		JLabel label_66 = new JLabel("Ana Grup");
		label_66.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_66.setBounds(401, 17, 104, 14);
		panel_KERESTE.add(label_66);

		JLabel label_67 = new JLabel("Alt Grup");
		label_67.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_67.setBounds(401, 41, 104, 14);
		panel_KERESTE.add(label_67);

		JLabel lblUrunOzelKod_1 = new JLabel("Ozel Kod");
		lblUrunOzelKod_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUrunOzelKod_1.setBounds(401, 68, 113, 14);
		panel_KERESTE.add(lblUrunOzelKod_1);

		chckbxDovizeCevirme_1 = new JCheckBox("Dovize Cevirme");
		chckbxDovizeCevirme_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxDovizeCevirme_1.setBounds(520, 145, 110, 23);
		chckbxDovizeCevirme_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxDovizeCevirme_1.isSelected())
				{
					comboBox_77_1.setVisible(true);
					comboBox_77_2.setVisible(true);
				}
				else
				{
					comboBox_77_1.setVisible(false);
					comboBox_77_2.setVisible(false);
				}
			}
		});
		chckbxDovizeCevirme_1.setVisible(false);
		panel_KERESTE.add(chckbxDovizeCevirme_1);
		///


		///
		comboBox_77_1 = new JComboBox<String>();
		//comboBox_77_1.setForeground(new Color(0, 0, 128));
		comboBox_77_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_77_1.setModel(new DefaultComboBoxModel<String>(new String[] {"USD", "EUR"}));
		comboBox_77_1.setBounds(646, 145, 58, 22);
		comboBox_77_1.setVisible(false);
		panel_KERESTE.add(comboBox_77_1);

		comboBox_77_2 = new JComboBox<String>();
		//comboBox_77_2.setForeground(new Color(0, 0, 128));
		comboBox_77_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_77_2.setModel(new DefaultComboBoxModel<String>(new String[] {"MA", "MS", "BA", "BS", "SA", "SS"}));
		comboBox_77_2.setBounds(646, 170, 58, 22);
		comboBox_77_2.setVisible(false);
		panel_KERESTE.add(comboBox_77_2);

		textField_89 = new Obs_TextFIeld();
		textField_89.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_89.setColumns(10);
		textField_89.setBounds(90, 77, 125, 20);
		panel_KERESTE.add(textField_89);

		textField_94 = new Obs_TextFIeld();
		textField_94.setText("ZZZZZZZZZZZZ");
		textField_94.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_94.setColumns(10);
		textField_94.setBounds(248, 77, 125, 20);
		panel_KERESTE.add(textField_94);

		JLabel lblNewLabel_42 = new JLabel("Konsimento");
		lblNewLabel_42.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_42.setBounds(10, 82, 81, 14);
		panel_KERESTE.add(lblNewLabel_42);

		chckbxNewCheckBox_3 = new JCheckBox("Ara Bolum Ayirma");
		chckbxNewCheckBox_3.setBounds(520, 190, 170, 23);
		chckbxNewCheckBox_3.setVisible(false);
		panel_KERESTE.add(chckbxNewCheckBox_3);

		comboBox_80_6 = new JComboBox<String>();
		//comboBox_80_6.setForeground(new Color(0, 0, 128));
		comboBox_80_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_80_6.setBounds(520, 88, 184, 22);
		panel_KERESTE.add(comboBox_80_6);

		JLabel lblNewLabel_44 = new JLabel("Depo");
		lblNewLabel_44.setBounds(401, 94, 48, 14);
		panel_KERESTE.add(lblNewLabel_44);

		textField_99 = new Obs_TextFIeld();
		textField_99.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_99.setColumns(10);
		textField_99.setBounds(90, 100, 99, 20);
		panel_KERESTE.add(textField_99);

		textField_100 = new Obs_TextFIeld();
		textField_100.setText("ZZZZZZZZZZZZ");
		textField_100.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_100.setColumns(10);
		textField_100.setBounds(248, 99, 125, 20);
		panel_KERESTE.add(textField_100);

		JLabel lblNewLabel_45 = new JLabel("Evrak");
		lblNewLabel_45.setFont(new Font ("Tahoma",Font.PLAIN,11));
		lblNewLabel_45.setBounds(10, 105, 69, 14);
		panel_KERESTE.add(lblNewLabel_45);

		/// KERESTE DETAY ***************************************************************************

		JPanel panel_KERESTE_DETAY = new JPanel();
		//panel_KERESTE_DETAY.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Kereste_Detay", null, panel_KERESTE_DETAY, null);
		panel_KERESTE_DETAY.setLayout(null);

		JLabel label_18_1_1 = new JLabel("Tarih");
		label_18_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_18_1_1.setBounds(10, 17, 58, 14);
		panel_KERESTE_DETAY.add(label_18_1_1);

		JLabel label_19_1_1 = new JLabel("Urun Kodu");
		label_19_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_19_1_1.setBounds(10, 36, 69, 14);
		panel_KERESTE_DETAY.add(label_19_1_1);

		JLabel lblHesapKodu_2_1 = new JLabel("Hesap Kodu");
		lblHesapKodu_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHesapKodu_2_1.setBounds(10, 83, 82, 14);
		panel_KERESTE_DETAY.add(lblHesapKodu_2_1);

		dateChooser_20_1_1 = new JDateChooser();
		dateChooser_20_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_20_1_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_20_1_1.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		dateChooser_20_1_1.setBounds(100, 11, 125, 20);
		dateChooser_20_1_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_20_1_1.setDate(new Date());
			}
		});
		
		panel_KERESTE_DETAY.add(dateChooser_20_1_1);

		dateChooser_21_1_1 = new JDateChooser();
		dateChooser_21_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_21_1_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_21_1_1.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		dateChooser_21_1_1.setBounds(258, 11, 125, 20);
		dateChooser_21_1_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_21_1_1.setDate(new Date());
			}
		});
		
		panel_KERESTE_DETAY.add(dateChooser_21_1_1);

		formattedTextField_2 = new JFormattedTextField();
		try {
			mask = new MaskFormatter("AA-###-####-####");
			mask.install(formattedTextField_2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		formattedTextField_2.setText("00-000-0000-0000");
		formattedTextField_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		formattedTextField_2.setBounds(100, 33, 125, 20);
		panel_KERESTE_DETAY.add(formattedTextField_2);

		formattedTextField_1_1 = new JFormattedTextField();
		try {
			mask = new MaskFormatter("AA-###-####-####");
			mask.install(formattedTextField_1_1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		formattedTextField_1_1.setText("ZZ-999-9999-9999");
		formattedTextField_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		formattedTextField_1_1.setBounds(258, 33, 125, 20);
		panel_KERESTE_DETAY.add(formattedTextField_1_1);

		textField_84 = new Obs_TextFIeld();
		textField_84.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_84.setColumns(10);
		textField_84.setBounds(100, 77, 125, 20);
		panel_KERESTE_DETAY.add(textField_84);

		textField_85 = new Obs_TextFIeld();
		textField_85.setText("ZZZZZZZZZZZZ");
		textField_85.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_85.setColumns(10);
		textField_85.setBounds(258, 77, 125, 20);
		panel_KERESTE_DETAY.add(textField_85);

		dateChooser_20_1_1_1 = new JDateChooser();
		dateChooser_20_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_20_1_1_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_20_1_1_1.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		dateChooser_20_1_1_1.setBounds(480, 11, 125, 20);
		dateChooser_20_1_1_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_20_1_1_1.setDate(new Date());
			}
		});
		
		panel_KERESTE_DETAY.add(dateChooser_20_1_1_1);

		textField_86 = new Obs_TextFIeld();
		textField_86.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_86.setColumns(10);
		textField_86.setBounds(480, 33, 125, 20);
		panel_KERESTE_DETAY.add(textField_86);

		textField_87 = new Obs_TextFIeld();
		textField_87.setText("ZZZZZZZZZZZZ");
		textField_87.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_87.setColumns(10);
		textField_87.setBounds(638, 33, 125, 20);
		panel_KERESTE_DETAY.add(textField_87);

		dateChooser_21_1_1_1 = new JDateChooser();
		dateChooser_21_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_21_1_1_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_21_1_1_1.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		dateChooser_21_1_1_1.setBounds(638, 11, 125, 20);
		dateChooser_21_1_1_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_21_1_1_1.setDate(new Date());
			}
		});
		
		panel_KERESTE_DETAY.add(dateChooser_21_1_1_1);

		JLabel lblCtarih = new JLabel("C_Tarih");
		lblCtarih.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCtarih.setBounds(390, 17, 58, 14);
		panel_KERESTE_DETAY.add(lblCtarih);

		JLabel lblHesapKodu_2_1_1 = new JLabel("C_Hesap Kodu");
		lblHesapKodu_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHesapKodu_2_1_1.setBounds(390, 39, 82, 14);
		panel_KERESTE_DETAY.add(lblHesapKodu_2_1_1);

		JLabel lblHesapKodu_2_2 = new JLabel("Paket No");
		lblHesapKodu_2_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHesapKodu_2_2.setBounds(10, 61, 82, 14);
		panel_KERESTE_DETAY.add(lblHesapKodu_2_2);

		textField_88 = new Obs_TextFIeld();
		textField_88.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_88.setColumns(10);
		textField_88.setBounds(100, 55, 125, 20);
		panel_KERESTE_DETAY.add(textField_88);

		textField_88_1 = new Obs_TextFIeld();
		textField_88_1.setText("ZZZZZZZZZZ");
		textField_88_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_88_1.setColumns(10);
		textField_88_1.setBounds(258, 55, 125, 20);
		panel_KERESTE_DETAY.add(textField_88_1);

		comboBox_78_1 = new JComboBox<String>();
		//comboBox_78_1.setForeground(new Color(0, 0, 128));
		comboBox_78_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_78_1.setBounds(100, 123, 125, 22);
		comboBox_78_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ker_alt_grup_doldur(comboBox_79_1,comboBox_78_1 .getItemAt(comboBox_78_1 .getSelectedIndex()));
			}
		});
		panel_KERESTE_DETAY.add(comboBox_78_1);

		comboBox_79_1 = new JComboBox<String>();
		//comboBox_79_1.setForeground(new Color(0, 0, 128));
		comboBox_79_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_79_1.setEnabled(false);
		comboBox_79_1.setBounds(258, 123, 125, 22);
		panel_KERESTE_DETAY.add(comboBox_79_1);

		comboBox_80_1 = new JComboBox<String>();
		//comboBox_80_1.setForeground(new Color(0, 0, 128));
		comboBox_80_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_80_1.setBounds(100, 173, 151, 22);
		panel_KERESTE_DETAY.add(comboBox_80_1);


		lblGrup = new JLabel("Gruplama");
		lblGrup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGrup.setBounds(10, 203, 89, 14);
		panel_KERESTE_DETAY.add(lblGrup);

		comboBox_84 = new JComboBox<String>();
		comboBox_84.setModel(new DefaultComboBoxModel<String>(new String[] {"Urun Kodu","Konsimento","Hesap-Kodu","Ana_Grup-Alt_Grup"}));
		//comboBox_84.setForeground(new Color(0, 0, 128));
		comboBox_84.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_84.setBounds(100, 200, 200, 22);
		panel_KERESTE_DETAY.add(comboBox_84);

		JLabel label_66_1 = new JLabel("Ana Grup");
		label_66_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_66_1.setBounds(10, 129, 89, 14);
		panel_KERESTE_DETAY.add(label_66_1);

		JLabel lblAgrup = new JLabel("A_G");
		lblAgrup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAgrup.setBounds(227, 128, 31, 14);
		panel_KERESTE_DETAY.add(lblAgrup);

		JLabel lblUrunOzelKod_1_1 = new JLabel("Ozel Kod");
		lblUrunOzelKod_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUrunOzelKod_1_1.setBounds(10, 177, 89, 14);
		panel_KERESTE_DETAY.add(lblUrunOzelKod_1_1);

		JLabel lblHesapKodu_2_3 = new JLabel("Evrak No");
		lblHesapKodu_2_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHesapKodu_2_3.setBounds(10, 105, 82, 14);
		panel_KERESTE_DETAY.add(lblHesapKodu_2_3);

		textField_90 = new Obs_TextFIeld();
		textField_90.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_90.setColumns(10);
		textField_90.setBounds(100, 99, 125, 20);
		panel_KERESTE_DETAY.add(textField_90);

		textField_91 = new Obs_TextFIeld();
		textField_91.setText("ZZZZZZZZZZZZ");
		textField_91.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_91.setColumns(10);
		textField_91.setBounds(258, 99, 125, 20);
		panel_KERESTE_DETAY.add(textField_91);

		JLabel lblHesapKodu_2_1_2 = new JLabel("C_Evrak No");
		lblHesapKodu_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHesapKodu_2_1_2.setBounds(390, 61, 82, 14);
		panel_KERESTE_DETAY.add(lblHesapKodu_2_1_2);

		textField_92 = new Obs_TextFIeld();
		textField_92.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_92.setColumns(10);
		textField_92.setBounds(480, 55, 125, 20);
		panel_KERESTE_DETAY.add(textField_92);

		textField_93 = new Obs_TextFIeld();
		textField_93.setText("ZZZZZZZZZZZZ");
		textField_93.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_93.setColumns(10);
		textField_93.setBounds(638, 55, 125, 20);
		panel_KERESTE_DETAY.add(textField_93);

		JLabel label_66_2 = new JLabel("C_Ana Grup");
		label_66_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_66_2.setBounds(390, 86, 89, 14);
		panel_KERESTE_DETAY.add(label_66_2);

		JLabel label_67_2 = new JLabel("C_Alt Grup");
		label_67_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_67_2.setBounds(390, 111, 89, 14);
		panel_KERESTE_DETAY.add(label_67_2);

		JLabel lblUrunOzelKod_1_2 = new JLabel("C_Ozel Kod");
		lblUrunOzelKod_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUrunOzelKod_1_2.setBounds(390, 136, 89, 14);
		panel_KERESTE_DETAY.add(lblUrunOzelKod_1_2);

		comboBox_80_2 = new JComboBox<String>();
		//comboBox_80_2.setForeground(new Color(0, 0, 128));
		comboBox_80_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_80_2.setBounds(480, 130, 151, 22);
		panel_KERESTE_DETAY.add(comboBox_80_2);

		comboBox_79_2 = new JComboBox<String>();
		//comboBox_79_2.setForeground(new Color(0, 0, 128));
		comboBox_79_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_79_2.setEnabled(false);
		comboBox_79_2.setBounds(480, 105, 151, 22);
		panel_KERESTE_DETAY.add(comboBox_79_2);

		comboBox_78_2 = new JComboBox<String>();
		//comboBox_78_2.setForeground(new Color(0, 0, 128));
		comboBox_78_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_78_2.setBounds(480, 80, 151, 22);
		comboBox_78_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ker_alt_grup_doldur(comboBox_79_2,comboBox_78_2 .getItemAt(comboBox_78_2 .getSelectedIndex()));
			}
		});
		panel_KERESTE_DETAY.add(comboBox_78_2);

		JLabel lblUrunOzelKod_1_3 = new JLabel("Depo");
		lblUrunOzelKod_1_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUrunOzelKod_1_3.setBounds(10, 154, 89, 14);
		panel_KERESTE_DETAY.add(lblUrunOzelKod_1_3);

		comboBox_80_3 = new JComboBox<String>();
		//comboBox_80_3.setForeground(new Color(0, 0, 128));
		comboBox_80_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_80_3.setBounds(100, 148, 125, 22);
		panel_KERESTE_DETAY.add(comboBox_80_3);

		JLabel lblUrunOzelKod_1_4 = new JLabel("C_Depo");
		lblUrunOzelKod_1_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUrunOzelKod_1_4.setBounds(390, 161, 89, 14);
		panel_KERESTE_DETAY.add(lblUrunOzelKod_1_4);

		comboBox_80_4 = new JComboBox<String>();
		//comboBox_80_4.setForeground(new Color(0, 0, 128));
		comboBox_80_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_80_4.setBounds(480, 155, 125, 22);
		panel_KERESTE_DETAY.add(comboBox_80_4);

		comboBox_7_1 = new JComboBox<String>();
		//comboBox_7_1.setForeground(new Color(0, 0, 128));
		comboBox_7_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_7_1.setBounds(638, 153, 125, 22);
		comboBox_7_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Fatura No", "Firma Kodu", "Fatura_No_Tarih"}));
		comboBox_7_1.setVisible(false);
		panel_KERESTE_DETAY.add(comboBox_7_1);

		comboBox_8_1 = new JComboBox<String>();
		//comboBox_8_1.setForeground(new Color(0, 0, 128));
		comboBox_8_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_8_1.setBounds(638, 179, 125, 22);
		comboBox_8_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Cari_Firma", "Adres_Firma"}));
		comboBox_8_1.setVisible(false);
		panel_KERESTE_DETAY.add(comboBox_8_1);

		comboBox_81 = new JComboBox<String>();
		comboBox_81.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hangi = comboBox_81.getItemAt(comboBox_81.getSelectedIndex())  ;
				if (hangi == "GIREN")
				{
					dateChooser_20_1_1_1.setEnabled(false);
					dateChooser_21_1_1_1.setEnabled(false);
					textField_86.setEnabled(false);
					textField_87.setEnabled(false);
					textField_92.setEnabled(false);
					textField_93.setEnabled(false);
					comboBox_78_2.setEnabled(false);
					comboBox_79_2.setEnabled(false);
					comboBox_80_2.setEnabled(false);
					comboBox_80_4.setEnabled(false);
				}
				else
				{
					dateChooser_20_1_1_1.setEnabled(true);
					dateChooser_21_1_1_1.setEnabled(true);
					textField_86.setEnabled(true);
					textField_87.setEnabled(true);
					textField_92.setEnabled(true);
					textField_93.setEnabled(true);
					comboBox_78_2.setEnabled(true);
					comboBox_79_2.setEnabled(true);
					comboBox_80_2.setEnabled(true);
					comboBox_80_4.setEnabled(true);
				}

			}
		});
		comboBox_81.setModel(new DefaultComboBoxModel<String>(new String[] {"GIREN", "CIKAN"}));
		//comboBox_81.setForeground(new Color(0, 0, 128));
		comboBox_81.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_81.setBounds(638, 100, 125, 22);
		comboBox_81.setVisible(false);
		panel_KERESTE_DETAY.add(comboBox_81);


		/// KERESTE ORTALAMA FIAT*******************************************************************************

		JPanel panel_KERESTE_ORTFIAT = new JPanel();
		//panel_KERESTE_ORTFIAT.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Kereste_Ort_Fiat", null, panel_KERESTE_ORTFIAT, null);
		panel_KERESTE_ORTFIAT.setLayout(null);

		JLabel label_18_1_2 = new JLabel("Tarih");
		label_18_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_18_1_2.setBounds(10, 26, 58, 14);
		panel_KERESTE_ORTFIAT.add(label_18_1_2);


		dateChooser_20_1_2 = new JDateChooser();
		dateChooser_20_1_2.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		dateChooser_20_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_20_1_2.setDateFormatString("dd.MM.yyyy");
		dateChooser_20_1_2.setBounds(90, 20, 125, 20);
		dateChooser_20_1_2.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_20_1_2.setDate(new Date());
			}
		});
		
		panel_KERESTE_ORTFIAT.add(dateChooser_20_1_2);

		dateChooser_21_1_2 = new JDateChooser();
		dateChooser_21_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_21_1_2.setDateFormatString("dd.MM.yyyy");
		dateChooser_21_1_2.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		dateChooser_21_1_2.setBounds(248, 20, 125, 20);
		dateChooser_21_1_2.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_21_1_2.setDate(new Date());
			}
		});
		
		panel_KERESTE_ORTFIAT.add(dateChooser_21_1_2);

		JLabel label_19_1_2 = new JLabel("Urun Kodu");
		label_19_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_19_1_2.setBounds(10, 48, 69, 14);
		panel_KERESTE_ORTFIAT.add(label_19_1_2);


		formattedTextField_3 = new JFormattedTextField();
		try {
			mask = new MaskFormatter("AA-###-####-####");
			mask.install(formattedTextField_3);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		formattedTextField_3.setText("00-000-0000-0000");
		formattedTextField_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		formattedTextField_3.setBounds(89, 42, 125, 20);
		panel_KERESTE_ORTFIAT.add(formattedTextField_3);

		formattedTextField_1_2 = new JFormattedTextField();
		try {
			mask = new MaskFormatter("AA-###-####-####");
			mask.install(formattedTextField_1_2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		formattedTextField_1_2.setText("ZZ-999-9999-9999");
		formattedTextField_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		formattedTextField_1_2.setBounds(248, 42, 125, 20);
		panel_KERESTE_ORTFIAT.add(formattedTextField_1_2);

		JLabel lblHesapKodu_2_4 = new JLabel("Hesap Kodu");
		lblHesapKodu_2_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHesapKodu_2_4.setBounds(10, 70, 80, 14);
		panel_KERESTE_ORTFIAT.add(lblHesapKodu_2_4);

		JLabel lblNewLabel_42_1 = new JLabel("Konsimento");
		lblNewLabel_42_1.setBounds(10, 92, 80, 14);
		lblNewLabel_42_1.setFont(new Font("Tahoma",Font.PLAIN,11));
		panel_KERESTE_ORTFIAT.add(lblNewLabel_42_1);

		textField_95 = new Obs_TextFIeld();
		textField_95.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_95.setColumns(10);
		textField_95.setBounds(90, 86, 125, 20);
		panel_KERESTE_ORTFIAT.add(textField_95);

		textField_96 = new Obs_TextFIeld();
		textField_96.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_96.setColumns(10);
		textField_96.setBounds(90, 64, 125, 20);
		panel_KERESTE_ORTFIAT.add(textField_96);

		textField_97 = new Obs_TextFIeld();
		textField_97.setText("ZZZZZZZZZZZZ");
		textField_97.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_97.setColumns(10);
		textField_97.setBounds(248, 64, 125, 20);
		panel_KERESTE_ORTFIAT.add(textField_97);

		textField_98 = new Obs_TextFIeld();
		textField_98.setText("ZZZZZZZZZZZZ");
		textField_98.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_98.setColumns(10);
		textField_98.setBounds(248, 86, 125, 20);
		panel_KERESTE_ORTFIAT.add(textField_98);

		JLabel lblGruplama_2_1 = new JLabel("Gruplama");
		lblGruplama_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGruplama_2_1.setBounds(10, 143, 69, 14);
		panel_KERESTE_ORTFIAT.add(lblGruplama_2_1);

		comboBox_27_1_1 = new JComboBox<String>();
		//comboBox_27_1_1.setForeground(new Color(0, 0, 128));
		comboBox_27_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_27_1_1.setBounds(90, 137, 201, 22);
		comboBox_27_1_1.setModel(new DefaultComboBoxModel<String>(new String[] { "Sinif","Sinif-Kal" , "Sinif-Boy" ,"Sinif-Gen" ,"Kodu", "Konsimento", "Hesap Kodu", "Hesap Kodu-Ana_Alt_Grup", "Yil", "Yil_Ay", "Ana Grup"}));
		panel_KERESTE_ORTFIAT.add(comboBox_27_1_1);

		JLabel label_66_3 = new JLabel("Ana Grup");
		label_66_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_66_3.setBounds(415, 25, 104, 14);
		panel_KERESTE_ORTFIAT.add(label_66_3);

		comboBox_78_3 = new JComboBox<String>();
		//comboBox_78_3.setForeground(new Color(0, 0, 128));
		comboBox_78_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_78_3.setBounds(534, 20, 184, 22);
		comboBox_78_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ker_alt_grup_doldur(comboBox_79_3,comboBox_78_3 .getItemAt(comboBox_78_3 .getSelectedIndex()));
			}
		});
		panel_KERESTE_ORTFIAT.add(comboBox_78_3);

		comboBox_79_3 = new JComboBox<String>();
		//comboBox_79_3.setForeground(new Color(0, 0, 128));
		comboBox_79_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_79_3.setEnabled(false);
		comboBox_79_3.setBounds(534, 45, 184, 22);
		panel_KERESTE_ORTFIAT.add(comboBox_79_3);

		JLabel label_67_1 = new JLabel("Alt Grup");
		label_67_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_67_1.setBounds(415, 51, 104, 14);
		panel_KERESTE_ORTFIAT.add(label_67_1);

		JLabel lblUrunOzelKod_1_5 = new JLabel("Ozel Kod");
		lblUrunOzelKod_1_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUrunOzelKod_1_5.setBounds(415, 76, 113, 14);
		panel_KERESTE_ORTFIAT.add(lblUrunOzelKod_1_5);

		comboBox_80_5 = new JComboBox<String>();
		//comboBox_80_5.setForeground(new Color(0, 0, 128));
		comboBox_80_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_80_5.setBounds(534, 70, 184, 22);
		panel_KERESTE_ORTFIAT.add(comboBox_80_5);

		comboBox_82 = new JComboBox<String>();
		//comboBox_82.setForeground(new Color(0, 0, 128));
		comboBox_82.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_82.setModel(new DefaultComboBoxModel<String>(new String[] {"USD", "EUR"}));
		comboBox_82.setBounds(535, 165, 69, 22);
		comboBox_82.setVisible(false);
		panel_KERESTE_ORTFIAT.add(comboBox_82);

		JLabel lblNewLabel_43 = new JLabel("Turu");
		lblNewLabel_43.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_43.setBounds(415, 116, 46, 14);
		panel_KERESTE_ORTFIAT.add(lblNewLabel_43);

		comboBox_83 = new JComboBox<String>();
		//comboBox_83.setForeground(new Color(0, 0, 128));
		comboBox_83.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_83.setModel(new DefaultComboBoxModel<String>(new String[] { "GIREN", "CIKAN","STOK"}));
		comboBox_83.setBounds(534, 111, 110, 22);
		panel_KERESTE_ORTFIAT.add(comboBox_83);

		chckbxNewCheckBox_5 = new JCheckBox("Doviz Kurlu");
		chckbxNewCheckBox_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox_5.isSelected())
					comboBox_82.setVisible(true) ;
				else
					comboBox_82.setVisible(false) ;
			}
		});
		chckbxNewCheckBox_5.setBounds(534, 139, 110, 23);
		panel_KERESTE_ORTFIAT.add(chckbxNewCheckBox_5);
		
		//********************************************************* TAHSILAT FISI
		JPanel panel_24 = new JPanel();
		tabbedPane.addTab("Tahsilat", null, panel_24, null);
		panel_24.setLayout(null);
		
		comboBox_85 = new JComboBox<String>();
		comboBox_85.setModel(new DefaultComboBoxModel<String>(new String[] {"Hepsi", "Tahsilat", "Tediye"}));
		comboBox_85.setBounds(70, 23, 103, 22);
		panel_24.add(comboBox_85);
		
		comboBox_86 = new JComboBox<String>();
		comboBox_86.setModel(new DefaultComboBoxModel<String>(new String[] {"Hepsi", "Nakit", "Cek", "Kredi Karti"}));
		comboBox_86.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(comboBox_86.getSelectedIndex() == 0 || comboBox_86.getSelectedIndex() == 3)
					comboBox_87.setEnabled(true);
				else
				{
					comboBox_87.setEnabled(false);
					comboBox_87.setSelectedIndex(0);
				}
			}
		});
		comboBox_86.setBounds(70, 56, 103, 22);
		panel_24.add(comboBox_86);
		
		
		comboBox_87 = new JComboBox<String>();
		comboBox_87.setBounds(70, 89, 200, 22);
		panel_24.add(comboBox_87);
		
		dateChooser_35 = new JDateChooser();
		dateChooser_35.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_35.setDateFormatString("dd.MM.yyyy");
		dateChooser_35.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		dateChooser_35.setBounds(70, 133, 120, 20);
		dateChooser_35.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_35.setDate(new Date());
			}
		});
		
		panel_24.add(dateChooser_35);
		
		dateChooser_36 = new JDateChooser();
		dateChooser_36.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_36.setDateFormatString("dd.MM.yyyy");
		dateChooser_36.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		dateChooser_36.setBounds(234, 133, 120, 20);
		dateChooser_36.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_36.setDate(new Date());
			}
		});

		panel_24.add(dateChooser_36);
		
		textTaheno1 = new Obs_TextFIeld(15);
		textTaheno1.setBounds(70, 166, 135, 20);
		textTaheno1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_24.add(textTaheno1);
		textTaheno1.setColumns(10);
		
		textTaheno2 = new Obs_TextFIeld(15);
		textTaheno2.setFont(new Font("Tahoma", Font.BOLD, 11));
		textTaheno2.setText("ZZZZZZZZZZZZZZZ");
		textTaheno2.setBounds(234, 166, 135, 20);
		panel_24.add(textTaheno2);
		textTaheno2.setColumns(10);
		
		textTahck1 = new Obs_TextFIeld(12);
		textTahck1.setBounds(70, 192, 120, 20);
		textTahck1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textTahck1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						textTahck1.setText( oac.hsp_hsp_kodu);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel_24.add(textTahck1);
		textTahck1.setColumns(10);
		
		textTahck2 = new Obs_TextFIeld(12);
		textTahck2.setFont(new Font("Tahoma", Font.BOLD, 11));
		textTahck2.setText("ZZZZZZZZZZZZ");
		textTahck2.setBounds(234, 192, 120, 20);
		textTahck2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						textTahck2.setText( oac.hsp_hsp_kodu);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel_24.add(textTahck2);
		textTahck2.setColumns(10);
		
		JLabel lblNewLabel_46 = new JLabel("Cins");
		lblNewLabel_46.setBounds(10, 27, 46, 14);
		panel_24.add(lblNewLabel_46);
		
		JLabel lblNewLabel_47 = new JLabel("Tur");
		lblNewLabel_47.setBounds(10, 60, 46, 14);
		panel_24.add(lblNewLabel_47);
		
		JLabel lblNewLabel_48 = new JLabel("Tarih");
		lblNewLabel_48.setBounds(10, 133, 46, 14);
		panel_24.add(lblNewLabel_48);
		
		JLabel lblNewLabel_49 = new JLabel("Evrak No");
		lblNewLabel_49.setBounds(10, 169, 61, 14);
		panel_24.add(lblNewLabel_49);
		
		JLabel lblNewLabel_50 = new JLabel("Cari Kod");
		lblNewLabel_50.setBounds(10, 195, 61, 14);
		panel_24.add(lblNewLabel_50);
		
		JLabel lblNewLabel_51 = new JLabel("Pos");
		lblNewLabel_51.setBounds(10, 93, 61, 14);
		panel_24.add(lblNewLabel_51);
		
		
		//**********************************************************************OK BUTTON **********************************
		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		splitPane.setRightComponent(buttonPane);
		//getContentPane().add(buttonPane, BorderLayout.SOUTH);

		
	
		okButton = new JButton("Yenile");
		okButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		okButton.setIcon(new ImageIcon(FILTRE.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		okButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				if(dateChooser.getDate() == null) return;
				if(dateChooser_1.getDate() == null) return;
				if(dateChooser_2.getDate() == null) return;
				if(dateChooser_2_1.getDate() == null) return;
				if(dateChooser_3.getDate() == null) return;
				if(dateChooser_4.getDate() == null) return;
				if(dateChooser_5.getDate() == null) return;
				if(dateChooser_5_1.getDate() == null) return;
				if(dateChooser_35.getDate() == null) return;//TAHSILAT
				if(dateChooser_36.getDate() == null) return;

				if(dateChooser_6.getDate() == null) return; // Cek Rapor
				if(dateChooser_6_1.getDate() == null) return;
				if(dateChooser_7.getDate() == null) return;
				if(dateChooser_8.getDate() == null) return;
				if(dateChooser_9.getDate() == null) return;
				if(dateChooser_10.getDate() == null) return;
				if(dateChooser_6_2.getDate() == null) return;
				if(dateChooser_6_2_1.getDate() == null) return;

				if(dateChooser_11.getDate() == null) return; // Kur 
				if(dateChooser_11_1.getDate() == null) return;
				if(dateChooser_32.getDate() == null) return;
				if(dateChooser_32_1.getDate() == null) return;

				if(dateChooser_12.getDate() == null) return; // STOK
				if(dateChooser_13.getDate() == null) return;
				if(dateChooser_14.getDate() == null) return;
				if(dateChooser_15.getDate() == null) return;
				if(dateChooser_16.getDate() == null) return;
				if(dateChooser_17.getDate() == null) return;
				if(dateChooser_18.getDate() == null) return;
				if(dateChooser_19.getDate() == null) return;
				if(dateChooser_20.getDate() == null) return;
				if(dateChooser_21.getDate() == null) return;
				if(dateChooser_22.getDate() == null) return;
				if(dateChooser_23.getDate() == null) return;
				if(dateChooser_24.getDate() == null) return;
				if(dateChooser_25.getDate() == null) return;
				if(dateChooser_26.getDate() == null) return;
				if(dateChooser_27.getDate() == null) return;
				if(dateChooser_28.getDate() == null) return;
				if(dateChooser_29.getDate() == null) return;
				if(dateChooser_30.getDate() == null) return;
				if(dateChooser_31.getDate() == null) return;
				if(dateChooser_33.getDate() == null) return;//GUNLUK
				if(dateChooser_34.getDate() == null) return;

				if(dateChooser_20_1.getDate() == null) return;//KERESTE
				if(dateChooser_21_1.getDate() == null) return;
				if(dateChooser_20_1_1.getDate() == null) return;
				if(dateChooser_21_1_1.getDate() == null) return;
				if(dateChooser_20_1_1_1.getDate() == null) return;
				if(dateChooser_21_1_1_1.getDate() == null) return;
				if(dateChooser_20_1_2.getDate() == null) return;
				if(dateChooser_21_1_2.getDate() == null) return;
				int sayfa = tabbedPane.getSelectedIndex();
				GuiUtil.setWaitCursor(getContentPane(),true);
				if (sayfa == 0) 
				{
					GuiUtil.setWaitCursor(EKSTRE.pane,true);
					EKSTRE.hisset();   // Cari Ekstre
					//OBS_MAIN.btnNewButton_23.setEnabled(true);
					GuiUtil.setWaitCursor(EKSTRE.pane,false);
				}
				else if (sayfa == 1) 
				{
					if (GLOBAL.hangi_mizan.equals("ozel")) 
					{
						GuiUtil.setWaitCursor(OZEL_MIZAN.splitPane,true);
						OZEL_MIZAN.hisset();
						GuiUtil.setWaitCursor(OZEL_MIZAN.splitPane,false);
					}
					else if (GLOBAL.hangi_mizan.equals("normal")) 
					{
						GuiUtil.setWaitCursor(MIZAN.splitPane,true);
						MIZAN.hisset();
						GuiUtil.setWaitCursor(MIZAN.splitPane,false);
					}
					else if (GLOBAL.hangi_mizan.equals("karton")) 
					{
						GuiUtil.setWaitCursor(KARTON_MIZAN.splitPane,true);
						KARTON_MIZAN.hisset();
						GuiUtil.setWaitCursor(KARTON_MIZAN.splitPane,false);
					}
				}
				else if (sayfa == 2) 
				{
					GuiUtil.setWaitCursor(DVZ_CEVIRME.splitPane,true);
					DVZ_CEVIRME.hisset();   // Cari  Dovize Cevirme
					//OBS_MAIN.btnNewButton_23.setEnabled(true);
					GuiUtil.setWaitCursor(DVZ_CEVIRME.splitPane,false);
				}
				else if (sayfa == 3) 
				{
					GuiUtil.setWaitCursor(ARAMA.splitPane,true);
					ARAMA.hisset();   // Cari ARAMA
					GuiUtil.setWaitCursor(ARAMA.splitPane,false);
				}
				else if (sayfa == 4) 
				{
					GuiUtil.setWaitCursor(GUNLUK_ISLEM.splitPane,true);
					GUNLUK_ISLEM.hisset();   // Gunluk Islem
					GuiUtil.setWaitCursor(GUNLUK_ISLEM.splitPane,false);
				}
				else if (sayfa == 5) 
				{
					GuiUtil.setWaitCursor(CEK_RAPOR.scrollPane,true);
					CEK_RAPOR.hisset();   // Cek Rapor
					GuiUtil.setWaitCursor(CEK_RAPOR.scrollPane,false);
				}
				else if (sayfa == 6) 
				{
					GuiUtil.setWaitCursor(KUR_RAPORLAMA.scrollPane,true);
					KUR_RAPORLAMA.hisset();   // Kur Rapor
					GuiUtil.setWaitCursor(KUR_RAPORLAMA.scrollPane,false);
				}
				else if (sayfa == 7) 
				{
					GuiUtil.setWaitCursor(FATURA_RAPOR.splitPane,true);
					FATURA_RAPOR.yenile();   // Fatura Rapor
					GuiUtil.setWaitCursor(FATURA_RAPOR.splitPane,false);
				}
				else if (sayfa == 8) 
				{
					GuiUtil.setWaitCursor(IMALAT_RAPORLAMA.splitPane,true);
					IMALAT_RAPORLAMA.hisset();   // Fatura Rapor
					GuiUtil.setWaitCursor(IMALAT_RAPORLAMA.splitPane,false);
				}
				else if (sayfa == 9) 
				{
					GuiUtil.setWaitCursor(STOK_RAPOR.splitPane,true);
					//OBS_MAIN.btnNewButton_23.setEnabled(true);
					STOK_RAPOR.hisset();   // Stok Rapor
					GuiUtil.setWaitCursor(STOK_RAPOR.splitPane,false);
				}
				else if (sayfa == 10) 
				{
					GuiUtil.setWaitCursor(STOK_DETAY.splitPane,true);
					STOK_DETAY.hisset();   // Stok Detay
					GuiUtil.setWaitCursor(STOK_DETAY.splitPane,false);
				}
				else if (sayfa == 11) 
				{
					GuiUtil.setWaitCursor(GRUP_RAPOR.splitPane,true);
					GRUP_RAPOR.yenile();   // GRUP_RAPOR
					GuiUtil.setWaitCursor(GRUP_RAPOR.splitPane,false);
				}
				else if (sayfa == 12) 
				{
					GuiUtil.setWaitCursor(IMALAT_GRUP_RAPOR.splitPane,true);
					IMALAT_GRUP_RAPOR.yenile();   // IMA GRUP_RAPOR
					GuiUtil.setWaitCursor(IMALAT_GRUP_RAPOR.splitPane,false);
				}
				else if (sayfa == 13) 
				{
					GuiUtil.setWaitCursor(IRSALIYE_RAPOR.splitPane,true);
					IRSALIYE_RAPOR.filtrele();   // IMA GRUP_RAPOR
					GuiUtil.setWaitCursor(IRSALIYE_RAPOR.splitPane,false);
				}
				else if (sayfa == 14) 
				{
					GuiUtil.setWaitCursor(RECETE_RAPOR.splitPane,true);
					RECETE_RAPOR.hisset();   // IMA GRUP_RAPOR
					GuiUtil.setWaitCursor(RECETE_RAPOR.splitPane,false);
				}
				else if (sayfa == 15) 
				{
					GuiUtil.setWaitCursor(ORTALAMA_FIAT.splitPane,true);
					ORTALAMA_FIAT.yenile();   // ORT FIAT
					GuiUtil.setWaitCursor(ORTALAMA_FIAT.splitPane,false);
				}
				else if (sayfa == 16) 
				{
					GuiUtil.setWaitCursor(URUN_LISTE.splitPane,true);
					URUN_LISTE.filtrele();   // ORT FIAT
					GuiUtil.setWaitCursor(URUN_LISTE.splitPane,false);
				}
				else if (sayfa == 17) 
				{
					GuiUtil.setWaitCursor(ENVANTER.splitPane,true);
					if (comboBox_66.getItemAt(comboBox_66.getSelectedIndex()).equals("Agirlikli Ortalama"))
						ENVANTER.hisset();   // ORT FIAT
					else if (comboBox_66.getItemAt(comboBox_66.getSelectedIndex()).equals("FIFO"))
						ENVANTER.fifo();   // ORT FIAT
					else if (comboBox_66.getItemAt(comboBox_66.getSelectedIndex()).equals("LIFO"))
						ENVANTER.lifo();   // ORT FIAT
					GuiUtil.setWaitCursor(ENVANTER.splitPane,false);
				}
				else if (sayfa == 18) 
				{
					GuiUtil.setWaitCursor(ZAYI_RAPOR.splitPane,true);
					ZAYI_RAPOR.hisset();   // ZAYI RAPOR
					GuiUtil.setWaitCursor(ZAYI_RAPOR.splitPane,false);
				}
				else if (sayfa == 19) 
				{
					GuiUtil.setWaitCursor(KUR_GRAFIK.scrollPane,true);
					KUR_GRAFIK.filtrele();   // KUR GRAFIK
					GuiUtil.setWaitCursor(KUR_GRAFIK.scrollPane,false);
				}
				else if (sayfa == 20) 
				{
					GuiUtil.setWaitCursor(HAZIR_GOREVLER.scrollPane,true);
					HAZIR_GOREVLER.hisset();   // KUR GRAFIK
					GuiUtil.setWaitCursor(HAZIR_GOREVLER.scrollPane,false);
				}
				else if (sayfa == 21) 
				{
					GuiUtil.setWaitCursor(KER_GRUP_RAPOR.splitPane,true);
					KER_GRUP_RAPOR.yenile();   // 
					GuiUtil.setWaitCursor(KER_GRUP_RAPOR.splitPane,false);
				}
				else if (sayfa == 22) 
				{
					if (GLOBAL.hangi_fatura.equals("detay")) 
					{
						GuiUtil.setWaitCursor(KER_DETAY.splitPane,true);
						KER_DETAY.hisset();   // 
						GuiUtil.setWaitCursor(KER_DETAY.splitPane,false);
					}
					else if (GLOBAL.hangi_fatura.equals("fatura")) 
					{
						GuiUtil.setWaitCursor(KER_FAT_RAPOR.splitPane,true);
						KER_FAT_RAPOR.yenile();   
						GuiUtil.setWaitCursor(KER_FAT_RAPOR.splitPane,false);
					}
					else if (GLOBAL.hangi_fatura.equals("envanter")) 
					{
						GuiUtil.setWaitCursor(KER_ENVANTER.splitPane,true);
						KER_ENVANTER.hisset();   // 
						GuiUtil.setWaitCursor(KER_ENVANTER.splitPane,false);
					}

				}
				else if (sayfa == 23) 
				{
					GuiUtil.setWaitCursor(KER_ORT_FIAT.splitPane,true);
					KER_ORT_FIAT.yenile();   // 
					GuiUtil.setWaitCursor(KER_ORT_FIAT.splitPane,false);
				}
				else if (sayfa == 24) 
				{
					GuiUtil.setWaitCursor(TAH_DOKUM.splitPane,true);
					TAH_DOKUM.yenile();   // 
					GuiUtil.setWaitCursor(TAH_DOKUM.splitPane,false);
				}
				GuiUtil.setWaitCursor(getContentPane(),false);
				dispose();
			}
		});
		okButton.setActionCommand("Yenile");
		okButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Escape" )
					dispose();
			}
		});

		buttonPane.add(okButton);
		//getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Vazgec");
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		cancelButton.setIcon(new ImageIcon(FILTRE.class.getResource("/ICONLAR/exit.png")));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Escape" )
					dispose();
			}
		});

		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		sayfa_ac(GLOBAL.filtre_sayfa );
		if (GLOBAL.filtre_sayfa == 0)
			txtkodu.requestFocus();
		for(int i = 0;i <= tabbedPane.getTabCount() - 1;i++)
			tabbedPane.setEnabledAt(i, false);
	}
	public void isimoku_ekstre()  {
		try
		{
			hpl = new String[3];
			hpl = isim(txtkodu.getText());
			lblNewLabel_1.setText(hpl[0].toString());
			lblNewLabel_2.setText(hpl[1].toString());
			EKSTRE.lblNewLabel.setText(txtkodu.getText());
			EKSTRE.lblNewLabel_1.setText(hpl[0].toString());
			EKSTRE.lblNewLabel_1_1.setText(hpl[1].toString());
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(10000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	public void isimoku_dvz()  {
		try
		{
			hpl = new String[3];
			hpl = isim(txtdvz.getText());
			lblNewLabel_1_1.setText(hpl[0].toString());
			lblNewLabel_2_1.setText(hpl[1].toString());
			DVZ_CEVIRME.lblkod.setText(txtdvz.getText());
			DVZ_CEVIRME.lblunvan.setText(hpl[0].toString());
			DVZ_CEVIRME.lblcins.setText(hpl[1].toString());
			if (chckbxNewCheckBox_4.isSelected())
				DVZ_CEVIRME.lblcevrilen.setText("");
			else
				DVZ_CEVIRME.lblcevrilen.setText(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()) + " / " + comboBox_2.getItemAt(comboBox_2.getSelectedIndex()));
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(10000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	public void sayfa_ac (int syfa)
	{
		tabbedPane.setSelectedIndex(syfa);
		if (syfa == 7)
		{
			ana_grup_doldur(comboBox_3);
			depo_doldur(comboBox_5);
		}
		else if (syfa == 8)
		{
			ana_grup_doldur(comboBox_9);
			ana_grup_doldur(comboBox_12);
			depo_doldur(comboBox_11);
		}
		else if (syfa == 9)
		{
			ana_grup_doldur(comboBox_14);
			ana_grup_doldur(comboBox_17);
			depo_doldur(comboBox_16);
		}
		else if (syfa == 10)
		{
			ana_grup_doldur(comboBox_20);
			ana_grup_doldur(comboBox_23);
			depo_doldur(comboBox_22);
		}
		else if (syfa == 11)
		{
			ana_grup_doldur(comboBox_29);
			oz1_doldur(comboBox_31);
		}
		else if (syfa == 12)
		{
			ana_grup_doldur(comboBox_38);
			ana_grup_doldur(comboBox_40);
		}
		else if (syfa == 13)
			ana_grup_doldur(comboBox_42);
		else if (syfa == 14)
		{
			ana_grup_doldur(comboBox_44);
			ana_grup_doldur(comboBox_48);
		}
		else if (syfa == 15)
			ana_grup_doldur(comboBox_56);
		else if (syfa == 16)
		{
			ana_grup_doldur(comboBox_55);
			depo_doldur(comboBox_54);
			mensei_doldur(comboBox_52);
			oz1_doldur(comboBox_59);
			oz2_doldur(comboBox_53);
		}
		else if (syfa == 17)
		{
			ana_grup_doldur(comboBox_60);
			ana_grup_doldur(comboBox_63);
			depo_doldur(comboBox_62);
		}
		else if (syfa == 18)
		{
			ana_grup_doldur(comboBox_67);
			ana_grup_doldur(comboBox_70);
			depo_doldur(comboBox_69);
		}
		else if (syfa == 20)
			gun_isim_doldur();
		else if (syfa == 21)
		{
			ker_ana_grup_doldur(comboBox_78);
			ker_oz1_doldur(comboBox_80);
			ker_depo_doldur(comboBox_80_6);
		}
		else if (syfa == 22)
		{
			ker_ana_grup_doldur(comboBox_78_1);
			ker_ana_grup_doldur(comboBox_78_2);
			ker_oz1_doldur(comboBox_80_1);
			ker_oz1_doldur(comboBox_80_2);
			ker_depo_doldur(comboBox_80_3);
			ker_depo_doldur(comboBox_80_4);
		}
		else if (syfa == 23)
		{
			ker_ana_grup_doldur(comboBox_78_3);
			ker_oz1_doldur(comboBox_80_5);
		}
		else if (syfa == 24)
			pos_doldur();
		
	}
	public static void kapat ()
	{
		okButton.doClick();
	}
	private void ana_grup_doldur(JComboBox<String> box)
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			box .removeAllItems();
			ResultSet rs=null;
			rs = f_Access.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
			if (!rs.isBeforeFirst() ) {  
				getContentPane().setCursor(oac.DEFAULT_CURSOR);

				box .addItem("");
				box.setSelectedItem("");
				return;
			} 
			box .addItem("");
			box.addItem("Bos Olanlar");
			while (rs.next())
				box .addItem(rs.getString("ANA_GRUP"));
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(10000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void alt_grup_doldur(JComboBox<String> box,String altgrp)
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			box.removeAllItems();
			box .addItem("");
			ResultSet rs=null;
			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN",  altgrp);
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				int in1 = rs.getInt("AGID_Y");
				rs =null;
				rs = f_Access.stk_kod_alt_grup_degisken_oku(in1);
			}
			if (!rs.isBeforeFirst() ) {  
				box.setSelectedItem("");
				box.setEnabled(false);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			} 
			else
			{
				while (rs.next())
					box .addItem(rs.getString("ALT_GRUP"));
				box.setSelectedItem(0);
				box.setEnabled(true);
			}
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(10000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void ker_ana_grup_doldur(JComboBox<String> box)
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			box .removeAllItems();
			ResultSet rs=null;
			rs = ker_Access.ker_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
			if (!rs.isBeforeFirst() ) {  
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				box .addItem("");
				box.setSelectedItem("");
				return;
			} 
			box .addItem("");
			box.addItem("Bos Olanlar");
			while (rs.next())
				box .addItem(rs.getString("ANA_GRUP"));
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(10000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void ker_alt_grup_doldur(JComboBox<String> box,String altgrp)
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			box.removeAllItems();
			box .addItem("");
			ResultSet rs=null;

			rs = ker_Access.ker_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN",  altgrp);
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				int in1 = rs.getInt("AGID_Y");
				rs =null;
				rs = ker_Access.ker_kod_alt_grup_degisken_oku(in1);
			}
			if (!rs.isBeforeFirst() ) {  
				box.setSelectedItem("");
				box.setEnabled(false);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			} 
			else
			{
				while (rs.next())
					box .addItem(rs.getString("ALT_GRUP"));
				box.setSelectedItem(0);
				box.setEnabled(true);
			}
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(10000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void depo_doldur(JComboBox<String> box)
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			box.removeAllItems();
			ResultSet rs = null;
			rs = f_Access.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
			if (!rs.isBeforeFirst() ) {  
				box.addItem("");
				box.addItem("Bos Olanlar");
				box.setSelectedItem("");
			}
			else
			{
				box.addItem("");
				box.addItem("Bos Olanlar");
				while (rs.next())
					box.addItem(rs.getString("DEPO"));
			}
			box.setSelectedItem("");
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(10000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void ker_depo_doldur(JComboBox<String> box)
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			box.removeAllItems();
			ResultSet rs = null;
			rs = ker_Access.ker_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
			if (!rs.isBeforeFirst() ) {  
				box.addItem("");
				box.addItem("Bos Olanlar");
				box.setSelectedItem("");
			}
			else
			{
				box.addItem("");
				box.addItem("Bos Olanlar");
				while (rs.next())
					box.addItem(rs.getString("DEPO"));
			}
			box.setSelectedItem("");
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(10000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void oz1_doldur(JComboBox<String> box)
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			box.removeAllItems();
			ResultSet rs = null;
			rs =f_Access.stk_kod_degisken_oku("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN");
			if (!rs.isBeforeFirst() ) {  
				box.addItem("");
				box.setSelectedItem("");
			}
			else
			{
				box.addItem("");
				box.addItem("Bos Olanlar");
				while (rs.next())
					box.addItem(rs.getString("OZEL_KOD_1"));
			}
			box.setSelectedItem("");
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(10000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void oz2_doldur(JComboBox<String> box)
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			box.removeAllItems();
			ResultSet rs = null;
			rs = f_Access.stk_kod_degisken_oku("OZEL_KOD_2", "OZ2ID_Y", "OZ_KOD_2_DEGISKEN");
			if (!rs.isBeforeFirst() ) {  
				box.addItem("");
				box.setSelectedItem("");
			}
			else
			{
				box.addItem("");
				box.addItem("Bos Olanlar");
				while (rs.next())
					box.addItem(rs.getString("OZEL_KOD_2"));
			}
			box.setSelectedItem("");
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(10000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void ker_oz1_doldur(JComboBox<String> box)
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			box.removeAllItems();
			ResultSet rs = null;
			rs =ker_Access.ker_kod_degisken_oku("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN");
			if (!rs.isBeforeFirst() ) {  
				box.addItem("");
				box.setSelectedItem("");
			}
			else
			{
				box.addItem("");
				box.addItem("Bos Olanlar");
				while (rs.next())
					box.addItem(rs.getString("OZEL_KOD_1"));
			}
			box.setSelectedItem("");
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(10000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void gun_isim_doldur()
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			cmbGrv_Isim.removeAllItems();
			ResultSet rs = null;
			rs =  g_Access.isim_oku();
			if (!rs.isBeforeFirst() ) {  
				cmbGrv_Isim.addItem("");
				cmbGrv_Isim.setSelectedItem("");
			}
			else
			{
				cmbGrv_Isim.addItem("");
				while (rs.next())
					cmbGrv_Isim.addItem(rs.getString("ISIM"));
			}
			cmbGrv_Isim.setSelectedItem("");
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(10000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void mensei_doldur(JComboBox<String> box)
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			box.removeAllItems();
			ResultSet rs = null;
			rs = f_Access.stk_kod_degisken_oku("MENSEI", "MEID_Y", "MENSEI_DEGISKEN");
			if (!rs.isBeforeFirst() ) {  
				box.addItem("");
				box.setSelectedItem("");
			}
			else
			{
				box.addItem("");
				box.addItem("Bos Olanlar");
				while (rs.next())
					box.addItem(rs.getString("MENSEI"));
			}
			box.setSelectedItem("");
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(10000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void pos_doldur() 
	{
		try {
			comboBox_87.removeAllItems();
			ResultSet	rs = null;
			comboBox_87.addItem("Hepsi");
			rs = c_Access.pos_banka_oku();
			if (!rs.isBeforeFirst() )
				return;
			else
			{
				while (rs.next())
					comboBox_87.addItem(rs.getString("POS_BANKA"));
			}
		} catch (Exception e) {

		}
	}
	private static String[] isim(String kod)  {
		String [] sonuc = {"","",""}  ;
		try
		{
			ResultSet	rs = null;
			rs = c_Access.hesap_adi_oku(kod);
			if (!rs.isBeforeFirst() ) {  
				sonuc [0]= "" ;
				sonuc [1]= "" ;
				sonuc [2]= "F" ;
			} 
			else
			{
				rs.next();
				sonuc [0] = rs.getString("UNVAN");
				sonuc [1]=rs.getString("HESAP_CINSI");
				sonuc [2]= "T" ;
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(10000,Notifications.Type.ERROR,ex.getMessage() );
		}
		return sonuc;
	}
	private void mWAIT()
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
		GuiUtil.setWaitCursor( tabbedPane,true);
		GuiUtil.setWaitCursor( textField,true);
		GuiUtil.setWaitCursor( textField_1,true);
		GuiUtil.setWaitCursor( textField_2,true);
		GuiUtil.setWaitCursor( textField_3,true);
		GuiUtil.setWaitCursor( textField_4,true);
		GuiUtil.setWaitCursor( textField_5,true);
		GuiUtil.setWaitCursor( textField_79,true);
	}
	private void mDEFAULT()
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		GuiUtil.setWaitCursor( tabbedPane,false);
		GuiUtil.setWaitCursor( textField,false);
		GuiUtil.setWaitCursor( textField_1,false);
		GuiUtil.setWaitCursor( textField_2,false);
		GuiUtil.setWaitCursor( textField_3,false);
		GuiUtil.setWaitCursor( textField_4,false);
		GuiUtil.setWaitCursor( textField_5,false);
		GuiUtil.setWaitCursor( textField_79,false);
	}
}
//https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/text/MaskFormatter.html
