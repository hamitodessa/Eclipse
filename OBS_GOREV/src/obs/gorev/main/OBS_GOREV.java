package obs.gorev.main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


import OBS_C_2025.BAGLAN;
import OBS_C_2025.CONNECT;
import OBS_C_2025.CheckListItem;
import OBS_C_2025.CheckListRenderer;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GOREV_GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.IConnection;
import OBS_C_2025.IKUR;
import OBS_C_2025.ILOGGER;
import OBS_C_2025.OBS_ORTAK_MSSQL;
import OBS_C_2025.OBS_ORTAK_MYSQL;
import OBS_C_2025.SIFRE_DONDUR;
import OBS_C_2025.Server_Bilgi;
import OBS_C_2025.USER_ISLEMLERI;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerDateModel;

import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import OBS_C_2025.KUR_MSSQL;
import OBS_C_2025.KUR_MYSQL;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({"unused","deprecation","rawtypes","unchecked"})
public class OBS_GOREV extends JDialog  {

	private static final long serialVersionUID = 1L;
	GLOBAL glb = new GLOBAL();
	GOREV_GLOBAL grvglb = new GOREV_GLOBAL();
	private JSpinner timeBaslangic;
	private static DefaultListModel<CheckListItem> model ;
	BAGLAN bAGLAN = new BAGLAN();
	boolean KUR_DOS_VAR;
	private static boolean KUR_CONN;

	private static IKUR _IKur;
	private static ILOGGER[] _IKur_Loger = {};
	boolean surucubilgi = false;

	public IConnection _IKurCon ;
	public USER_ISLEMLERI uSER_ISL = new USER_ISLEMLERI();
	public SIFRE_DONDUR sDONDUR = new     SIFRE_DONDUR();
	private JTextField textKurKullanici;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OBS_GOREV frame = new OBS_GOREV();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */

	public OBS_GOREV() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setTitle("OBS INDIRME");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		//MetalLookAndFeel.setCurrentTheme(new  DefaultMetalTheme());
		//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
		JPanel panelalt = new JPanel();
		panelalt.setPreferredSize(new Dimension(0,30));
		getContentPane().add(panelalt, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Kur Takip", null, panel_1, null);
		panel_1.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Aktif / Pasif");
		chckbxNewCheckBox.setBounds(21, 29, 99, 23);
		panel_1.add(chckbxNewCheckBox);
		
		JLabel lblNewLabel = new JLabel("Gorev Saati");
		lblNewLabel.setBounds(21, 76, 73, 14);
		panel_1.add(lblNewLabel);
		
		timeBaslangic = new JSpinner( new SpinnerDateModel() );
		timeBaslangic.setBounds(118, 71, 75, 25);
		panel_1.add(timeBaslangic);
		JSpinner.DateEditor de_timeBaslangic = new JSpinner.DateEditor(timeBaslangic, "HH:mm");
		timeBaslangic.setEditor(de_timeBaslangic);
		Date qweDate = new Date();
		qweDate.setHours(00);
		qweDate.setMinutes(00);
		timeBaslangic.setValue(qweDate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(287, 11, 136, 285);
		panel_1.add(scrollPane);
		
		model = new DefaultListModel<>();
		JList list = new JList(model);
		list.setCellRenderer(new CheckListRenderer());
		list.setBounds(289, 12, 134, 283);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
			
				JList list = (JList) event.getSource();
				int index = list.locationToIndex(event.getPoint());// Get index of item
				CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);
				item.setSelected(!item.isSelected()); // Toggle selected state
				list.repaint(list.getCellBounds(index, index));// Repaint cell
				
			}
		});
		model.addElement( new CheckListItem("USD",""));
		model.addElement( new CheckListItem("EUR",""));
		model.addElement( new CheckListItem("RUB",""));
		model.addElement( new CheckListItem("GBR",""));
		model.addElement( new CheckListItem("CHF",""));
		model.addElement( new CheckListItem("SEK",""));
		model.addElement( new CheckListItem("NOK",""));
		model.addElement( new CheckListItem("SAR",""));
	
		list.repaint();
		
		scrollPane.setViewportView(list);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("OBS Kur Kullanici");
		lblNewLabel_1.setBounds(10, 36, 91, 14);
		panel.add(lblNewLabel_1);
		
		textKurKullanici = new JTextField();
		textKurKullanici.setBounds(111, 33, 173, 20);
		panel.add(textKurKullanici);
		textKurKullanici.setColumns(10);
		
		JButton btnNewButton = new JButton("Kaydet");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textKurKullanici.getText().equals("")) return;
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					grvglb.bilgi_kayit(textKurKullanici.getText());
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(111, 251, 89, 23);
		panel.add(btnNewButton);
		
		
		//***********************************************************************************
		try {
			glb.gorev_surucu_kontrol();
			// gorev ilk ise kontrol yap
			ResultSet rSet = grvglb.gorev_bilgi_oku();
			if (!rSet.isBeforeFirst() ) {  
   				//Dosya Bos
   			} 
			else {
				rSet.next();
				textKurKullanici.setText(rSet.getString("OBS_KULLANICI"));
				calisma_dizini_oku() ;
				tabbedPane.setSelectedIndex(0);
			}
			//
			
		
			
			
			
			
			

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}
	void calisma_dizini_oku() 
	{
		try 
		{
			bAGLAN.cONNECT("hamit");//BAGLAN.kurDizin.kULLANICI
			cONN_AKTAR(BAGLAN.kurDizin.hAN_SQL);
			mODUL_AKTAR( BAGLAN.kurDizin.hAN_SQL);
			String hangi_sql =  BAGLAN.kurDizin.hAN_SQL;
			if(hangi_sql.equals("") )
			{
				surucubilgi = false ;
				return ;
			}
			surucubilgi = true ;
			kur_calisma_dizini_oku();
			
			System.out.println(KUR_CONN +"=="+ KUR_DOS_VAR);
		} catch (Exception e) {
		
		}
	}
	void kur_calisma_dizini_oku() throws ClassNotFoundException, SQLException
	{
		CONNECT s_CONN = new CONNECT( _IKurCon);
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setIns(BAGLAN.kurDizin.iNSTANCE); 
		sBilgi.setKull(BAGLAN.kurDizin.kULLANICI) ;
		sBilgi.setSifre(BAGLAN.kurDizin.sIFRESI);
		sBilgi.setPort(BAGLAN.kurDizin.sERVER);
		sBilgi.setServer( BAGLAN.kurDizin.sERVER);
		sBilgi.setDb("OK_Kur" + BAGLAN.kurDizin.kOD);
		if (BAGLAN.kurDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(sBilgi) == true)   
			{
				KUR_CONN = true ;
				if (s_CONN.Dosya_kontrol_L( sBilgi) == false)
				{
					KUR_DOS_VAR = false;
				}
				else
				{
					KUR_DOS_VAR = true;
				}
			}
			else
			{
				KUR_CONN = false;
			}
		}
		else
		{
			if (s_CONN.Server_kontrol_S(sBilgi) == true)   
			{
				KUR_CONN = true ;
				if (s_CONN.Dosya_kontrol_S( sBilgi) == false)
				{
					KUR_DOS_VAR = false;
				}
				else
				{
					KUR_DOS_VAR = true;
				}
			}
			else
			{
				KUR_CONN = false;
			}
		}
	}
	private void cONN_AKTAR(String hangi)
	{
		switch(hangi) {
		case "MS SQL":
			_IKurCon = new OBS_ORTAK_MSSQL() ;
			break;
		case "MY SQL":
			_IKurCon = new OBS_ORTAK_MYSQL() ;
			break;	
		}
	}
	private void mODUL_AKTAR(String hangi)
	{
		switch(hangi) {
		case "MS SQL":
			_IKur =  new KUR_MSSQL();
			break;
		case "MY SQL":
			_IKur =  new KUR_MYSQL();
			break;	
		}
	}
}
