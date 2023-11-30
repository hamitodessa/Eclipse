package obs.obs_fihrist;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javazoom.jl.player.Player;
import obs.classes.ComponentResizer;
import obs.obs_fihrist.form.MainForm;
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
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.UIManager;

import raven.toast.Notifications;

@SuppressWarnings("serial")
public class OBS_FIHRIST extends javax.swing.JFrame {

	public static OBS_FIHRIST app;
	public final MainForm mainForm;
	public final FormFihrist formFihrist;
	public static Title_Bar title_Bar;
	private Rectangle maxBounds;
	int x ,y ;

	public OBS_FIHRIST() {
		initComponents();
		//setSize(new Dimension(1366, 768));
		

		setLocationRelativeTo(null);
		mainForm = new MainForm();
		title_Bar = new Title_Bar();
		formFihrist = new FormFihrist();

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
		basla();
		
	}
	private void basla()
	{

		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		mainForm.showForm(formFihrist);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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

	public static void setSelectedMenu(int index, int subIndex) {

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
}
