package obs.backup.main;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;


import OBS_C_2025.BACKUP_GLOBAL;

import OBS_C_2025.GLOBAL;
import OBS_C_2025.JTextFieldLimit;
import javazoom.jl.player.Player;
import obs.backup.gorev.gOREV_TAKIP;
import obs.backup.other.Bilgilendirme;
import obs.backup.other.ServerBilgileri;
import obs.backup.other.SunucuAyarlari;
import obs.backup.other.Title_Bar;
import obs.backup.other.YedeklemeAraligi;
import raven.toast.Notifications;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.InputStream;
import java.security.PublicKey;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import OBS_C_2025.SIFRE_DONDUR;

@SuppressWarnings({ "unchecked", "rawtypes","static-access" })
public class OBS_BACKUP extends JFrame {

	GLOBAL glb = new GLOBAL();
	static BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	private JPanel container ;
	private JPanel panel_2 ;
	private JTabbedPane tabbedPane ;
	int x ,y ;
	private static YedeklemeAraligi yedekaraligiPanel;
	private static SunucuAyarlari sunucuayarPanel;
	private static Bilgilendirme bilgilendirmePanel;
	private static ServerBilgileri serverBilgileriPanel;
	
	private static JTextField txtEmir;
	private JTextArea textAciklama;
	private JButton btnServer ;
	private JButton btnSurucuSec ;
	private JButton btnDosyaSec ;
	
	private static JCheckBox chckbxDurum;
	private JCheckBox chckbxServerDosya ;
	
	private static JLabel lblNewLabel_6 ;
	private static JLabel lblNewLabel_5;


	private static JList list;
	private static DefaultListModel<CheckListItem> model ;
	
	/**
	 * Hamit.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OBS_BACKUP frame = new OBS_BACKUP();
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
	
	@SuppressWarnings("serial")
	public OBS_BACKUP() {
		setUndecorated(true);
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("obs.backup.theme");
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
		FlatMacDarkLaf.setup();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX(); 
				y = e.getY(); 
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xx = e.getXOnScreen();
				int yy = e.getYOnScreen(); 
				setLocation(xx-x,yy-y);
			}
		});
		
		setBounds(100, 100, 900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(new Title_Bar(this), BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(150,0));
		splitPane.setLeftComponent(panel);
		
		JButton btnGorevler = new JButton("Gorevler");
		btnGorevler.setPreferredSize(new Dimension(0,25));
		btnGorevler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				container.removeAll();
					gOREV_TAKIP x = new gOREV_TAKIP("hamit");
					//x.setBounds(0, 0, 400, 100);
					
					container.add(x);
					gOREV_TAKIP x2 = new gOREV_TAKIP("aden");
					//x2.setBounds(0, 110, 400, 100);
					container.add(x2);
//				for (int i = 0; i < 15; i++) {
//					gOREV_TAKIP x = new gOREV_TAKIP(String.valueOf(i + 1));
//			          x.setLocation(0, 45 *i);
//			         container.add(x);
//			      }
					pack();
				}
			});
		panel.setLayout(new GridLayout(20, 1, 0, 0));
		panel.add(btnGorevler);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Component[] components = container.getComponents();

				for (Component component : components) {
					System.out.println(component.getName());
					if (component.getName().toString().equals("hamit")) {


						JPanel qweJPanel = (JPanel) component ; 

						Component[] componentt = qweJPanel.getComponents();
						for (Component compo : componentt) {

							System.out.println(compo.getName());
						}

					}


					// }
				}
			}
		});
		
		JButton btnYeni_Gorev = new JButton("Yeni Gorev");
		btnYeni_Gorev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		panel.add(btnYeni_Gorev);
		btnNewButton_1.setPreferredSize(new Dimension(0,25));
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("alt pan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
			}
		});
		panel.add(btnNewButton);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
//		  final boolean showTabsHeader = false; tabbedPane.setUI(new
//		  javax.swing.plaf.metal.MetalTabbedPaneUI() {
//		  @Override protected int calculateTabAreaHeight(int tabPlacement, int
//		  horizRunCount, int maxTabHeight) { if (showTabsHeader) {return
//		  super.calculateTabAreaHeight(tabPlacement, horizRunCount, maxTabHeight); }
//		  else {return 0;} } protected void paintTabArea(Graphics g,int
//		  tabPlacement,int selectedIndex){} });
//
		  
		
		splitPane.setRightComponent(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Gorevler", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitPane_1, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 128, 128));
		panel_2.setPreferredSize(new Dimension(0,75));
		splitPane_1.setRightComponent(panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		
		splitPane_1.setLeftComponent(scrollPane);
		
		container = new JPanel(); 
		scrollPane.setViewportView(container);
	
		container.setLayout(new GridLayout(10, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Yeni Gorev", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel_3.add(tabbedPane_1, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		tabbedPane_1.addTab("Genel", null, panel_4, null);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_2 = new JSplitPane();
		panel_4.add(splitPane_2, BorderLayout.CENTER);
		
		
		JPanel panel_10 = new JPanel();
		splitPane_2.setRightComponent(panel_10);
		panel_10.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Durum");
		lblNewLabel.setBounds(26, 68, 86, 14);
		panel_10.add(lblNewLabel);
		
		chckbxDurum = new JCheckBox("");
		chckbxDurum.setBounds(150, 68, 99, 23);
		panel_10.add(chckbxDurum);
		
		JLabel lblNewLabel_1 = new JLabel("Emir Ismi");
		lblNewLabel_1.setBounds(26, 100, 99, 14);
		panel_10.add(lblNewLabel_1);
		
		txtEmir = new JTextField();
		txtEmir.setBounds(150, 100, 219, 20);
		panel_10.add(txtEmir);
		txtEmir.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Aciklama");
		lblNewLabel_2.setBounds(26, 150, 99, 14);
		panel_10.add(lblNewLabel_2);
		
		textAciklama = new JTextArea();
		textAciklama.setBounds(150, 150, 219, 77);
		textAciklama.setDocument(new JTextFieldLimit(50));
		//textAciklama.setBorder(txtEmir.getBorder());
		Border borderr = BorderFactory.createLineBorder(new Color(235,235,235));
	
		
		textAciklama.setBorder(BorderFactory.createCompoundBorder(borderr, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		panel_10.add(textAciklama);
		
		
		chckbxServerDosya = new JCheckBox("Sql Server / Diger Dosya Yedekleme");
		chckbxServerDosya.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxServerDosya.isSelected())
				{
					btnServer.setVisible(true);
					btnDosyaSec.setVisible(false);
					btnSurucuSec.setVisible(false);
				}
				else {
					btnServer.setVisible(false);
					btnDosyaSec.setVisible(true);
					btnSurucuSec.setVisible(true);
				}
			}
		});
		chckbxServerDosya.setToolTipText("");
		chckbxServerDosya.setBounds(150, 302, 250, 23);
		panel_10.add(chckbxServerDosya);
		
		JLabel lblNewLabel_4 = new JLabel("Dosya Sayisi");
		lblNewLabel_4.setBounds(26, 373, 100, 14);
		panel_10.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("0");
		lblNewLabel_5.setBounds(150, 373, 48, 14);
		panel_10.add(lblNewLabel_5);
		
		JButton btnNewButton_4 = new JButton("Kayit");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					genelKayit();
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(317, 579, 89, 23);
		panel_10.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Cikis");
		btnNewButton_5.setBounds(218, 579, 89, 23);
		panel_10.add(btnNewButton_5);
		
		JPanel panel_9 = new JPanel();
		panel_9.setPreferredSize(new Dimension(300,0));
		splitPane_2.setLeftComponent(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_9.add(splitPane_3, BorderLayout.CENTER);
		
		JPanel panel_11 = new JPanel();
		panel_11.setPreferredSize(new Dimension(0,75));
		splitPane_3.setLeftComponent(panel_11);
		panel_11.setLayout(null);
		
		lblNewLabel_6 = new JLabel(".......");
		lblNewLabel_6.setBounds(10, 11, 121, 14);
		panel_11.add(lblNewLabel_6);
		
		btnDosyaSec = new JButton("Dosya Sec");
		btnDosyaSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Dosya Seciniz");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Dosya Sec");
				chooser.setApproveButtonToolTipText("Dosya Sec");
				//FileNameExtensionFilter docFilter = new FileNameExtensionFilter(".docx", "Microsoft Word Documents");
				//chooser.addChoosableFileFilter(docFilter);
				
				//chooser.addChoosableFileFilter(new FileNameExtensionFilter("Dosyalar", "jpg", "png", "gif", "bmp","pdf","txt","xls","xlsx","doc","docx","db","xml","mdb","accdb"));
				chooser.setApproveButtonMnemonic('s');
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					model.addElement( new CheckListItem(chooser.getSelectedFile().getName(),chooser.getSelectedFile().getParent()));
					list.repaint();
				}
				else {
				}
			}
		});
	
		btnDosyaSec.setBounds(2, 41, 146, 30);
		panel_11.add(btnDosyaSec);
		
		btnSurucuSec = new JButton("Surucu Sec");
		btnSurucuSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Surucu Seciniz");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Surucu Sec");
				chooser.setApproveButtonToolTipText("Surucu Sec");
				chooser.setApproveButtonMnemonic('s');
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					model.addElement( new CheckListItem(chooser.getSelectedFile().toString(),"Surucu"));
					list.repaint();
				}
				else {
					// System.out.println("No Selection ");
				}
			}
		});
		btnSurucuSec.setBounds(147, 41, 148, 30);
		panel_11.add(btnSurucuSec);
		
		btnServer = new JButton("Server Baglanti");
		btnServer.setVisible(false);
		btnServer.setBounds(2, 41, 290, 30);
		panel_11.add(btnServer);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_3.setRightComponent(scrollPane_1);

		model = new DefaultListModel<>();
		
		//model.addElement( new CheckListItem("apple","C:\\"));
		list = new JList(model);
				
				
		list.setCellRenderer(new CheckListRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				JList list = (JList) event.getSource();
				int index = list.locationToIndex(event.getPoint());// Get index of item

				CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);
				item.setSelected(!item.isSelected()); // Toggle selected state
				list.repaint(list.getCellBounds(index, index));// Repaint cell
				if (list.getModel().getSize() != 0) 
				{
					int dosyaSayi = 0 ;
					for (int i =0; i< list.getModel().getSize(); i++)
					{
						item = (CheckListItem) list.getModel().getElementAt(i);
						if(item.isSelected)
						{
							dosyaSayi +=1 ;
						}
					} 
					lblNewLabel_5.setText(Integer.toString(dosyaSayi));
				}
			}
		});
		list.setDropTarget(new DropTarget() {
			
			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					List<File> droppedFiles = (List<File>) evt .getTransferable().getTransferData(  DataFlavor.javaFileListFlavor);
					if(droppedFiles.size() > 1){
						mesaj_goster(5000,Notifications.Type.WARNING,  "Tek Seferde 1 Dosya Ekleyebilirsiniz.....!!");
					}
					else{
						File droppedFile = (File) droppedFiles.get(0);
						model.addElement( new CheckListItem(droppedFile.getName(),droppedFile.getParent() ));
						list.repaint();
					}
				} catch (Exception ex) {
					mesaj_goster(15000,Notifications.Type.ERROR, ex.getMessage() );
				}
			}
		});	
		scrollPane_1.setViewportView(list);

		
		
		
//		if (list.getModel().getSize() != 0) 
//		{
//			for (int i =0; i< list.getModel().getSize(); i++)
//			{
//				CheckListItem item = (CheckListItem) list.getModel().getElementAt(i);
//				
//				System.out.println(item.isSelected + "=="+    item.surucu() + "=="+item.toString());
//			//	System.out.println(item.surucu() + "=="+list.getModel().getElementAt(i).toString());
//			} 
//		}

		
		
		
		sunucuayarPanel = new SunucuAyarlari();
		tabbedPane_1.addTab("Surucu Ayarlari", null,sunucuayarPanel, null);
		bilgilendirmePanel = new Bilgilendirme();
		tabbedPane_1.addTab("Bilgilendirme", null, bilgilendirmePanel, null);
		
		yedekaraligiPanel = new YedeklemeAraligi();
		tabbedPane_1.addTab("Yedekleme Araligi", null, yedekaraligiPanel, null);
		
		JPanel panel_8 = new JPanel();
		tabbedPane_1.addTab("Emir Kopyala", null, panel_8, null);
		
		serverBilgileriPanel = new ServerBilgileri();
		tabbedPane_1.addTab("Server Bilgileri", null, serverBilgileriPanel, null);
	 	try {
			glb.backup_surucu_kontrol();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public void genelKayit() throws ClassNotFoundException, SQLException
	{
		 if (txtEmir.getText().toString().equals("")) return;
		 if (contentPane.getCursor() == Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) ) return ;
		 Boolean drm = false;
		 if (yedekaraligiPanel.chckbxPtesi.isSelected())
		     drm = true;
		 else if (yedekaraligiPanel.chckbxSali.isSelected())
		     drm = true;
		 else if (yedekaraligiPanel.chckbxCarsamba.isSelected())
		     drm = true;
		 else if (yedekaraligiPanel.chckbxPersembe.isSelected())
		     drm = true;
		 else if (yedekaraligiPanel.chckbxCuma.isSelected())
		     drm = true;
		 else if (yedekaraligiPanel.chckbxCumartesi.isSelected())
		     drm = true;
		 else if (yedekaraligiPanel.chckbxPazar.isSelected())
		     drm = true;
		 if (drm == false) // ' Isaretli olan yok 
		 {
			 chckbxDurum.setSelected(false);
			mesaj_goster(5000,Notifications.Type.WARNING, "Yedekleme Icin Gun secilmediginden " + System.lineSeparator() + System.lineSeparator()  + "Emir durumu Pasiv olarak Degistirildi");
		 }
		 boolean sondurum = false;
		 boolean kontrol = false;
		 Date ilkkayit =  new Date();
		 String mesaj = "";
		 Date sonyuk = new Date();
		 try
		 {
			 contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		     if (chckbxServerDosya.isSelected()) //= True Then ' SQL DOSYALARI
		     {
		         bckp.db_adi_kayit_sil(txtEmir.getText());
		         for (int i = 0; i <= list.getModel().getSize() - 1; i++)
		         {
		        	 CheckListItem item = (CheckListItem) list.getModel().getElementAt(i);
		             if (item.isSelected)
		             {
		                 bckp.db_ismi_kayit(txtEmir.getText(), item.toString());
		                 bckp.diger_dosya_adi_kayit_sil(txtEmir.getText());
		             }
		         }
		     }
		     else
		     {
		         bckp.diger_dosya_adi_kayit_sil(txtEmir.getText());
		         for (int i = 0; i <= list.getModel().getSize()  - 1; i++)
		         {
		        	 CheckListItem item = (CheckListItem) list.getModel().getElementAt(i);
		             if (item.isSelected)
		             {
		                 bckp.diger_dosya_ismi_kayit(txtEmir.getText(), item.toString(),  item.surucu());
		                 bckp.db_adi_kayit_sil(txtEmir.getText());
		                 bckp.server_kayit_sil(txtEmir.getText());
		             }
		         }
		         bckp.log_kayit(txtEmir.getText(), new Date(), "Emir Islemi Kaydedildi...");
		     }
		     ResultSet rs ;
		     rs = bckp.emir_bilgi(txtEmir.getText());
		     if (!rs.isBeforeFirst() ) {  
		    	  kontrol = false;
		     }
		     else
		     {
		    	 rs.next();
		         sondurum =  rs.getBoolean("SON_DURUM");
		         sonyuk =  rs.getDate("SON_YUKLEME");
		         ilkkayit = rs.getDate("OLUSTURMA");
		         mesaj = rs.getString("MESAJ");
		         kontrol = true;
		     }
		    
		     bckp.genel_kayit_sil(txtEmir.getText());
		     if (kontrol)
		     {
		         bckp.genel_kayit(txtEmir.getText(), chckbxDurum.isSelected(), textAciklama.getText(), lblNewLabel_6.getText(), sondurum, sonyuk, chckbxServerDosya.isSelected(), mesaj, ilkkayit);
		     }
		     else
		     {
		         bckp.genel_kayit(txtEmir.getText(), chckbxDurum.isSelected(), textAciklama.getText(), lblNewLabel_6.getText(), false, sonyuk,  chckbxServerDosya.isSelected(), mesaj, ilkkayit);
		     }
		   
		     contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		 }
		 catch (Exception ex)
		 {
			 contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		     bckp.log_kayit(txtEmir.getText(), new Date(), ex.getMessage());
			mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
		 }
 	}
	public static void yedeklemeKaydet() throws ClassNotFoundException, SQLException
	{
		Boolean drm = false; // Yedekleme
		try
		{
		    if (yedekaraligiPanel.chckbxPtesi.isSelected())
		        drm = true;
		    else if (yedekaraligiPanel.chckbxSali.isSelected())
		        drm = true;
		    else if (yedekaraligiPanel.chckbxCarsamba.isSelected())
		        drm = true;
		    else if (yedekaraligiPanel.chckbxPersembe.isSelected())
		        drm = true;
		    else if (yedekaraligiPanel.chckbxCuma.isSelected())
		        drm = true;
		    else if (yedekaraligiPanel.chckbxCumartesi.isSelected())
		        drm = true;
		    else if (yedekaraligiPanel.chckbxPazar.isSelected())
		        drm = true;
		    if (drm == false) // ' Isaretli olan yok 
		    {
				 chckbxDurum.setSelected(false);
					mesaj_goster(5000,Notifications.Type.WARNING, "Yedekleme Icin Gun secilmediginden " + System.lineSeparator() + System.lineSeparator()  + "Emir durumu Pasiv olarak Degistirildi");
		    }
		    Date date = (Date) (yedekaraligiPanel.timeBaslangic.getValue());
		    Date date2 = (Date) (yedekaraligiPanel.timeBitis.getValue());
		    if (date.after(date2) )
		    {
				mesaj_goster(5000,Notifications.Type.WARNING,  "Bitis Zamani Baslangic Zamanindan Kucuk olamaz");
		        return;
		    }
		   
		    bckp.yedekleme_kayit_sil(txtEmir.getText());
		   
		    bckp.yedekleme_ismi_kayit(txtEmir.getText(), yedekaraligiPanel.textHerDakka.getText(), yedekaraligiPanel.chckbxPtesi.isSelected(),
		    		yedekaraligiPanel.chckbxSali.isSelected(), 
		    		yedekaraligiPanel.chckbxCarsamba.isSelected(), 
		    		yedekaraligiPanel.chckbxPersembe.isSelected(),
		    		yedekaraligiPanel.chckbxCuma.isSelected(),
		    		yedekaraligiPanel.chckbxCumartesi.isSelected(),
		    		yedekaraligiPanel.chckbxPazar.isSelected(), date, date2);
		    bckp.log_kayit(txtEmir.getText(), new Date(), "Emir Yedekleme Bilgileri  Kaydedildi...");
		    
		}
		catch (Exception ex)
		{
			  bckp.log_kayit(txtEmir.getText(), new Date(), ex.getMessage());
				mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());	
				}
	}
	public static void BilgilendirmeKaydet() throws ClassNotFoundException, SQLException
	{
		 try
		 {
		    bckp.bilgilendirme_kayit_sil(txtEmir.getText());
		    SIFRE_DONDUR sdon = new SIFRE_DONDUR();
			String response =sdon.sDONDUR(bilgilendirmePanel.textSifre);
		    bckp.bilgilendirme_ismi_kayit(txtEmir.getText(),bilgilendirmePanel.chckbxAktifPasif.isSelected() ,bilgilendirmePanel.chckbxIslem.isSelected(), bilgilendirmePanel.chckbxHata.isSelected(), bilgilendirmePanel.textGonIsim.getText(), bilgilendirmePanel.textGonHesap.getText() , bilgilendirmePanel.textAlici.getText(), bilgilendirmePanel.textKonu.getText(), bilgilendirmePanel.textSmtp.getText(), bilgilendirmePanel.textPort.getText(),bilgilendirmePanel.textKull.getText(), response, bilgilendirmePanel.chckbxSSL.isSelected(), bilgilendirmePanel.chckbxTSL.isSelected());
		    bckp.log_kayit(txtEmir.getText(), new Date(), "Bilgilendirme Bilgileri  Kaydedildi...");
		 }
		 catch (Exception ex)
		 {
			bckp.log_kayit(txtEmir.getText(), new Date(), ex.getMessage());
			mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());			 }
	}
	public static void sunucuKaydet() throws ClassNotFoundException, SQLException
	{
		 try
		 {
			 if (txtEmir.getText().toString().equals("")) return;
			 System.out.println("549");
		     bckp.ftp_kayit_sil(txtEmir.getText().toString());
		     String neresi = "";
		     if ( sunucuayarPanel.chckbxFtp.isSelected())
		     {
		         neresi = "FTP";
		     }
		     else
		     {
		         neresi = "SUR";
		     }
		     SIFRE_DONDUR sdon = new SIFRE_DONDUR();
		     bckp.ftp_ismi_kayit(txtEmir.getText().toString(), sunucuayarPanel.textHost.getText(), sunucuayarPanel.textKull.getText(), sdon.sDONDUR(sunucuayarPanel.textSifre), sunucuayarPanel.textFtpSurucu.getText(),  sunucuayarPanel.textPort.getText(),Integer.parseInt( sunucuayarPanel.textZmnasm.getText()) , sunucuayarPanel.textEskisilme.getText(), neresi,  sunucuayarPanel.textSurucu.getText());
		     bckp.log_kayit(txtEmir.getText().toString(),new Date(), "Emir FTP Bilgileri  Kaydedildi...");
		    
		 }
		 catch (Exception ex)
		 {
		     bckp.log_kayit(txtEmir.getText().toString(), new Date(), ex.getMessage());
		     mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
		 }
		 
	}
	public static void Server_Kayit() throws ClassNotFoundException, SQLException
	{
		 if (txtEmir.getText().toString().equals(""))
		 {
			 mesaj_goster(5000,Notifications.Type.ERROR, "Emir Adi Bos Olamaz");
		     return;
		 }
		 SIFRE_DONDUR sdon = new SIFRE_DONDUR();
			String response =sdon.sDONDUR(serverBilgileriPanel.textMSsifre);
			
		 bckp.MsSql_baglan( serverBilgileriPanel.textMSServer.getText() ,serverBilgileriPanel.textMSkull.getText(),response,serverBilgileriPanel.textMSPort.getText());
		 ResultSet rs;
		 rs = bckp.db_ismi();
		     list.removeAll();
		     lblNewLabel_5.setText(Integer.toString(0));
		     while (rs.next())
		     {
		    	
		    	 model.addElement( new CheckListItem(rs.getString("name"),""));
		     }
		 rs = bckp.db_liste(txtEmir.getText().toString());
		
		     int sayi = list.getModel().getSize() - 1;
		     int dosyaSAYI = 0;
		     for (int r = 0; r <= sayi; r++)
		     {
		         while (rs.next())
		         {
		        	 CheckListItem item = (CheckListItem) list.getModel().getElementAt(r);
//						
//						System.out.println(item.isSelected + "=="+    item.surucu() + "=="+item.toString());
		             if (item.toString().equals(rs.getString("DB_ADI")))
		             {
		            	
		            	 model.remove(r);
		            	 item.setSelected(true);
		            	
		            	 model.insertElementAt(item, dosyaSAYI);
		               
		                 dosyaSAYI += 1;
		             }
		         }
		      list.repaint();
		     }
		
		 bckp.log_kayit(txtEmir.getText().toString(), new Date(), "Veritabani Isimleri yuklendi...");
		 
			 lblNewLabel_6.setText("Ms Sql");
		
		 try
		 {
		     bckp.server_kayit_sil(txtEmir.getText().toString());
		     bckp.server_ismi_kayit(txtEmir.getText().toString(), serverBilgileriPanel.textMSServer.getText(), true, true, serverBilgileriPanel.textMSkull.getText(), response, "Ms Sql", "");
		     bckp.log_kayit(txtEmir.getText().toString(), new Date(), "SQL SERVER Instance Bilgileri Kaydedildi...");
		     bckp.instance_update(txtEmir.getText().toString(), "Ms Sql");
		     
		   
		     
		 }
		 catch (Exception ex)
		 {
			 bckp.log_kayit(txtEmir.getText().toString(), new Date(), ex.getMessage());
		     mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());		 }
	}
	public static void mesaj_goster(int zaman, Notifications.Type tipType , String mesaj)
	{
		InputStream stream = null ;
		try {
			Notifications.getInstance().show(tipType,Notifications.Location.BOTTOM_RIGHT ,zaman ,mesaj);
			stream = OBS_BACKUP.class.getClassLoader().getResourceAsStream("obs/backup/dosya/hata.mp3"); //whts
			Player player = new Player(stream);
			player.play();
			if(stream != null)
				stream.close();
		} catch (Exception ex) {
		}
	}
	public static void okuma(String isim)
	{
		System.out.println(isim);
	}
	@Override
	   public Dimension getPreferredSize() {
	      Dimension superSz = super.getPreferredSize();
	      if (isPreferredSizeSet()) {
	         return superSz;
	      }
	      return new Dimension(900, 700);
	   }
	class CheckListItem {

		  private String[] label = {"",""};
		 
		 
		  private boolean isSelected = false;

		  public CheckListItem(String label ,String name) {
		    this.label[0] = label;
		    this.label[1] = name ;
		  }

		  public boolean isSelected() {
		    return isSelected;
		  }

		  public void setSelected(boolean isSelected) {
		    this.isSelected = isSelected;
		  }

		  @Override
		  public String toString() {
			 return  label[0]  ;
		  }
		  
		  public String surucu(){
				    return label[1];
			}
		}

		@SuppressWarnings("serial")
		class CheckListRenderer extends JCheckBox implements ListCellRenderer {
		  public Component getListCellRendererComponent(JList list, Object value,
		      int index, boolean isSelected, boolean hasFocus) {
		    setEnabled(list.isEnabled());
		    setSelected(((CheckListItem) value).isSelected());
		    setFont(list.getFont());
		    setBackground(list.getBackground());
		    setForeground(list.getForeground());
		    setText(value.toString());
		  
		    return this;
		  }
		}
}
