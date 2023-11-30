package obs.obs_fihrist;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javazoom.jl.player.Player;
import obs.obs_fihrist.form.MainForm;
import obs.obs_fihrist.other.FormFihrist;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.UIManager;

import raven.toast.Notifications;

@SuppressWarnings("serial")
public class OBS_FIHRIST extends javax.swing.JFrame {

    public static OBS_FIHRIST app;
    public final MainForm mainForm;
    public final FormFihrist formFihrist;
    private Rectangle maxBounds;
    public OBS_FIHRIST() {
        initComponents();
        setSize(new Dimension(1366, 768));
      
        setLocationRelativeTo(null);
        mainForm = new MainForm();
        formFihrist = new FormFihrist();
        setContentPane(mainForm);
        Notifications.getInstance().setJFrame(this);
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
           
             //app.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
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
			 setBounds(0, 0, 1366, 768);
	        }
		 else {
			 setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		
		// 
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
