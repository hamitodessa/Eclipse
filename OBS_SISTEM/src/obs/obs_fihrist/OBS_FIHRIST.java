package obs.obs_fihrist;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javazoom.jl.player.Player;
import obs.obs_fihrist.form.MainForm;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.InputStream;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import raven.toast.Notifications;

@SuppressWarnings("serial")
public class OBS_FIHRIST extends javax.swing.JFrame {

    private static OBS_FIHRIST app;
    private final MainForm mainForm;

    public OBS_FIHRIST() {
        initComponents();
        setSize(new Dimension(1366, 768));
       
        setLocationRelativeTo(null);
        mainForm = new MainForm();
        setContentPane(mainForm);
        Notifications.getInstance().setJFrame(this);
        basla();
    }
    private void basla()
    {
    	OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.ERROR, "basla");
    }
    public static void showForm(Component component) {
        component.applyComponentOrientation(app.getComponentOrientation());
        app.mainForm.showForm(component);
    }

    public static void login() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.mainForm);
        app.mainForm.applyComponentOrientation(app.getComponentOrientation());
        setSelectedMenu(0, 0);
        app.mainForm.hideMenu();
        SwingUtilities.updateComponentTreeUI(app.mainForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
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
			stream = OBS_FIHRIST.class.getClassLoader().getResourceAsStream("DOSYA/hata.mp3"); //whts
			Player player = new Player(stream);
			player.play();
			if(stream != null)
				stream.close();
		} catch (Exception ex) {
		}
	}
}
