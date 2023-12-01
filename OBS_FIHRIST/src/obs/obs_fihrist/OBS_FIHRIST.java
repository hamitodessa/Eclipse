package obs.obs_fihrist;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.CONNECT;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.OBS_ORTAK_MSSQL;
import OBS_C_2025.OBS_ORTAK_MYSQL;
import OBS_C_2025.OBS_ORTAK_SQLITE;
import OBS_C_2025.Server_Bilgi;
import fih.FIHRIST_ACCESS;
import fih.FIHRIST_MSSQL;
import fih.FIHRIST_MYSQL;
import fih.FIHRIST_SQLITE;
import javazoom.jl.player.Player;
import obs.classes.ComponentResizer;
import obs.classes.aNA_Class;
import obs.obs_fihrist.form.MainForm;
import obs.obs_fihrist.other.FormAyarlar;
import obs.obs_fihrist.other.FormFihrist;
import obs.obs_fihrist.other.Title_Bar;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.InputStream;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.UIManager;

import raven.toast.Notifications;

@SuppressWarnings("serial")
public class OBS_FIHRIST extends javax.swing.JFrame {

	public static OBS_FIHRIST app;
	public  MainForm mainForm;
	public  FormFihrist formFihrist;
	public FormAyarlar formAyarlar;
	public static Title_Bar title_Bar;
	private Rectangle maxBounds;
	int x ,y ;
	
	boolean FIH_DOS_VAR;
	BAGLAN bAGLAN = new BAGLAN();
	aNA_Class oac = new aNA_Class();
	FIHRIST_ACCESS  fih_Access ;
	boolean surucubilgi = false;
	

	public OBS_FIHRIST() {
		initComponents();
		//setSize(new Dimension(1200, 600));
		setLocationRelativeTo(null);
		mainForm = new MainForm();
		title_Bar = new Title_Bar();
		formFihrist = new FormFihrist();
		formAyarlar = new FormAyarlar();
		setContentPane(mainForm);
		
		Notifications.getInstance().setJFrame(this);

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
				OBS_FIHRIST.app.setLocation(xx-x,yy-y);
			}
		});
		//basla();
		
	}
	private void basla()
	{
		
		kontrol();
		System.out.println("QQQQQQQ="+oac._IFihrist);
		System.out.println(surucubilgi + "---"+  oac.FIHRIST_CONN );
		if(surucubilgi && oac.FIHRIST_CONN  && FIH_DOS_VAR)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			mainForm.showForm(formFihrist);
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		else {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			mainForm.showForm(formAyarlar);
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	public static void showForm(Component component) {
		System.out.println("48");
		component.applyComponentOrientation(app.getComponentOrientation());
		app.mainForm.showForm(component);
	}

	public static void logout() {
		// FlatAnimatedLafChange.showSnapshot();
		//app.setContentPane(app.mainForm);
		// app.mainForm.applyComponentOrientation(app.getComponentOrientation());
		// SwingUtilities.updateComponentTreeUI(app.mainForm);
		//  FlatAnimatedLafChange.hideSnapshotWithAnimation();
		System.exit(1);
	}

	public static void setSelectedMenu(int index, int subIndex) 
	{
		app.mainForm.setSelectedMenu(index, subIndex);
	}

	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 719, Short.MAX_VALUE)
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 521, Short.MAX_VALUE)
				);

		pack();
	}

	public static void main(String args[]) {
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("obs.theme");
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
		FlatMacDarkLaf.setup();
		java.awt.EventQueue.invokeLater(() -> {
			app = new OBS_FIHRIST();
			 ComponentResizer cr  = new ComponentResizer();
		     // cr.setMinimumSize(new Dimension(1366, 768));
		     //cr.setMaximumSize(new Dimension(1366, 768));
		     cr.registerComponent(app);
		     cr.setSnapSize(new Dimension(10, 10));
			 //app.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		     app.setBounds(0, 0, 1200, 600);
			app.setVisible(true);
		});
	}

	public static void mesaj_goster(int zaman, Notifications.Type tipType , String mesaj)
	{
		InputStream stream = null ;
		try {
			Notifications.getInstance().show(tipType,Notifications.Location.BOTTOM_RIGHT ,zaman ,mesaj);
			stream = OBS_FIHRIST.class.getClassLoader().getResourceAsStream("obs/icon/png/hata.mp3"); //whts
			Player player = new Player(stream);
			player.play();
			if(stream != null)
				stream.close();
		} catch (Exception ex) {
		}
	}
	public void minimized()
	{
		setState(Frame.ICONIFIED);

	}
	public  void buyult()
	{
		if(getExtendedState() == Frame.MAXIMIZED_BOTH){
			title_Bar.btnNewButton_1.setIcon(title_Bar.iconRestore.toIcon() );
			setBounds(0, 0, 1366, 768);
		}
		else {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			title_Bar.btnNewButton_1.setIcon(title_Bar.iconMax.toIcon() );
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
	public  void kontrol()
	{
		GLOBAL.surucu_kontrol();
		calisma_dizini_oku() ;

		if(! surucubilgi) // Bilgi Yok
		{
			//mesaj_goster(5000,Notifications.Type.WARNING, "fffffffff");
		return;
		}
		
	}
	void calisma_dizini_oku() 
	{
		try 
		{
			bAGLAN.cONNECT("Admin");
			cONN_AKTAR(BAGLAN.fihDizin.hAN_SQL);
			mODUL_AKTAR( BAGLAN.fihDizin.hAN_SQL);
			String hangi_sql =  BAGLAN.fihDizin.hAN_SQL;
			if(hangi_sql.equals("") )
			{
				surucubilgi = false ;
				return ;
			}
			surucubilgi = true ;
			fihrist_calisma_dizini_oku();
		} catch (Exception e) {
		
		}
	}
	void fihrist_calisma_dizini_oku() throws ClassNotFoundException, SQLException
	{
		System.out.println("--"+oac._IFihrist);
		CONNECT s_CONN = new CONNECT( oac._IFihristCon);
		if (BAGLAN.fihDizin.yER.equals(""))
		{
			oac.FIHRIST_CONN  = false;
			FIH_DOS_VAR = false;
			return;
		}
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setIns(BAGLAN.fihDizin.iNSTANCE); 
		sBilgi.setKull(BAGLAN.fihDizin.kULLANICI) ;
		sBilgi.setSifre(BAGLAN.fihDizin.sIFRESI);
		sBilgi.setPort(BAGLAN.fihDizin.sERVER);
		sBilgi.setServer( BAGLAN.fihDizin.sERVER);
		sBilgi.setDb("OK_Fih" + BAGLAN.fihDizin.kOD);
		if (BAGLAN.fihDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(sBilgi) == true)   
			{
				if (s_CONN.Dosya_kontrol_L( sBilgi) == false)
				{
					FIH_DOS_VAR = false;
				}
				else
				{
					FIH_DOS_VAR = true;
					oac.FIHRIST_CONN = true ;
				}
			}
			else
			{
				oac.FIHRIST_CONN = false;
			}
		}
		else
		{
			if (s_CONN.Server_kontrol_S(sBilgi) == true)   
			{
				if (s_CONN.Dosya_kontrol_S( sBilgi) == false)
				{
					FIH_DOS_VAR = false;
				}
				else
				{
					FIH_DOS_VAR = true;
					oac.FIHRIST_CONN = true ;
				}
			}
			else
			{
				oac.FIHRIST_CONN = false;
			}
		}
	}

	private void cONN_AKTAR(String hangi)
	{
		if(hangi.equals("MS SQL"))
		{
			oac._IFihristCon = new OBS_ORTAK_MSSQL() ;
		}
		else if(hangi.equals("MY SQL"))
		{
			oac._IFihristCon = new OBS_ORTAK_MYSQL() ;
		}
		else if(hangi.equals("SQ LITE"))
		{
			oac._IFihristCon = new OBS_ORTAK_SQLITE() ;
		}
	}
	private void mODUL_AKTAR(String hangi)
	{
		if(hangi.equals("MS SQL"))
		{
			oac._IFihrist =  new FIHRIST_MSSQL();
		}
		else if(hangi.equals("MY SQL"))
		{
			oac._IFihrist =  new FIHRIST_MYSQL();
		}
		else if(hangi.equals("SQ LITE"))
		{
			oac._IFihrist =  new FIHRIST_SQLITE();
		}
	}
}
